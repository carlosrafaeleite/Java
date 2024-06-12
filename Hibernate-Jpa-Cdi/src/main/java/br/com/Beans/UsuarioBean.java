package br.com.Beans;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;



import com.google.gson.Gson;

import br.com.Dao.DaoGenerico;
import br.com.Util.HibernateUtil;
import br.com.model.Cidades;
import br.com.model.Estados;
import br.com.model.Usuario;
import br.com.repositorio.ImplementacaoUsuario;
import br.com.repositorio.InterfaceDaoUsuario;



@Named(value = "usuariobean")
@javax.faces.view.ViewScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private List<Usuario> listaUsusario = new ArrayList<Usuario>();
	
	@Inject
	private DaoGenerico<Usuario> daoGenerico;
	@Inject
	private InterfaceDaoUsuario interfaceDaoUsuario;
	
	
	private HibernateUtil hibernateUtil;
	
	private List<SelectItem> estados;
	private List<SelectItem> cidades;
	
	

	
	
	public List<SelectItem> getCidades() {
		return cidades;
	}

	public void setCidades(List<SelectItem> cidades) {
		this.cidades = cidades;
	}

	public List<SelectItem> getEstados() {

		estados = interfaceDaoUsuario.listaEstados();

		return estados;
	}

	

	public List<Usuario> getListaUsusario() {
		return listaUsusario;
	}

	public void setListaUsusario(List<Usuario> listaUsusario) {
		this.listaUsusario = listaUsusario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DaoGenerico<Usuario> getDaoGenerico() {
		return daoGenerico;
	}

	public void setDaoGenerico(DaoGenerico<Usuario> daoGenerico) {
		this.daoGenerico = daoGenerico;
	}

	// metodos persistencia

	public String salvando() {

		daoGenerico.salvar(usuario);
		usuario = new Usuario();
		CarregaListagem();

		return "";
	}

	public String inserindo() {

		usuario = new Usuario();
		return "";
	}

	public String excluindo() {

		daoGenerico.excluir(usuario);
		usuario = new Usuario();
		CarregaListagem();
		return "";
	}

	public String atualizando() {
		
		usuario = daoGenerico.Atualizar(usuario);
		daoGenerico.Atualizar(usuario);
		CarregaListagem();
		return "";
	}

	public String deletando() {

		daoGenerico.delete(usuario);
		usuario = new Usuario();
		CarregaListagem();
		return "";

	}

	public String pesquisando() {

		CarregaListagem();
		return "";

	}

	public void CarregaListagem() {

		listaUsusario = daoGenerico.Listagem(Usuario.class);

	}

	public String logando() {

		Usuario usuarioLogin = interfaceDaoUsuario.consultaUsuario(usuario.getNomeUsuario(), usuario.getSenhaUsuario());
		if (usuarioLogin != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", usuarioLogin);
			return "principal.xhtml";
		}

		return "index.xhtml";

	}

	public String deslogar() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().remove("usuarioLogado");
		HttpServletRequest httpServletRequest = (HttpServletRequest) context.getCurrentInstance().getExternalContext()
				.getRequest();
		httpServletRequest.getSession().invalidate();

		return "index.xhtml";

	}

	public boolean permicaoAcesso(String acesso) {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Usuario usuarioLogin = (Usuario) externalContext.getSessionMap().get("usuarioLogado");

		return usuarioLogin.getPerfil().equals(acesso);

	}

	public String logado() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Usuario pessoaLogada = (Usuario) externalContext.getSessionMap().get("usuarioLogado");

		return pessoaLogada.getNomeUsuario();
	}

	public void menssagemErro(String msg) {

		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(msg);
		context.addMessage(null, message);
	}

	public void pesquisaCep(AjaxBehaviorEvent event) {

		try {

			URL url = new URL("https://viacep.com.br/ws/" + usuario.getCep() + "/json/");
			URLConnection connection = url.openConnection();
			InputStream inputStream = connection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

			String ceps = "";
			StringBuilder jsonCep = new StringBuilder();

			while ((ceps = bufferedReader.readLine()) != null) {
				jsonCep.append(ceps);

			}

			Usuario gson = new Gson().fromJson(jsonCep.toString(), Usuario.class);

			usuario.setCep(gson.getCep());
			usuario.setLogradouro(gson.getLogradouro());
			usuario.setComplemento(gson.getComplemento());
			usuario.setBairro(gson.getBairro());
			usuario.setLocalidade(gson.getLocalidade());
			usuario.setUf(gson.getUf());

		} catch (Exception e) {
			e.printStackTrace();
			menssagemErro("Erro ao se conectar com WebService");
		}

	}

	public void carregaCidades(AjaxBehaviorEvent event) {

		//String codigoEstado = (String) event.getComponent().getAttributes().get("submittedValue"); //carrega somente o codigo do estado
		//System.out.println("aqui esta o codigo estado" + codigoEstado);
		
		Estados estados = (Estados)((HtmlSelectOneMenu)event.getSource()).getValue();

	//	if (estados != null) {

			//Estados estados = HibernateUtil.getEntityManager().find(Estados.class, Long.parseLong(codigoEstado)); //consulta no banco
			
			if(estados != null) {
				
				usuario.setEstados(estados);
				
				List<Cidades> cidades = (List<Cidades>) hibernateUtil.getEntityManager().createQuery("from Cidades where estados.id="+estados.getId()).getResultList();
				List<SelectItem> selectItensCidades = new ArrayList<SelectItem>();
				
				for (Cidades cidade : cidades) {
					
					selectItensCidades.add(new SelectItem(cidade, cidade.getNome()));
				}
				
				setCidades(selectItensCidades);		
				
			}

		//}

	}
	
	public void editar() {
		
		if(usuario.getCidades() != null) {
			Estados estados = usuario.getCidades().getEstados();
			usuario.setEstados(estados);
			
		}
		
	}
	
	public String editando() {	
		editar();
		return null;
		
	}

}
