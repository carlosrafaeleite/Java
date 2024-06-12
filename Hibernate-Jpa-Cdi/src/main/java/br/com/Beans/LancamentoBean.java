package br.com.Beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.Dao.DaoGenerico;
import br.com.model.Lancamento;
import br.com.model.Usuario;
import br.com.repositorio.ImplementacaoLancamento;
import br.com.repositorio.InterfaceDaoLancamento;




@Named(value = "lancamentobean")
@javax.faces.view.ViewScoped
public class LancamentoBean {
	
	
	private Lancamento lancamento = new Lancamento();
	
	private List<Lancamento> listaLancamento = new ArrayList<Lancamento>();
	@Inject
	private DaoGenerico<Lancamento> daoGenerico;
	@Inject
	private InterfaceDaoLancamento daoLancamento;
	
	
	
	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public DaoGenerico<Lancamento> getDaoGenerico() {
		return daoGenerico;
	}
	public void setDaoGenerico(DaoGenerico<Lancamento> daoGenerico) {
		this.daoGenerico = daoGenerico;
	}
	public List<Lancamento> getListaLancamento() {
		return listaLancamento;
	}
	public void setListaLancamento(List<Lancamento> listaLancamento) {
		this.listaLancamento = listaLancamento;
	}
	
	//metodos de persistencia
	

	public String salvar() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Usuario usuarioLogin = (Usuario) externalContext.getSessionMap().get("usuarioLogado");
		lancamento.setUsuario(usuarioLogin);
		daoGenerico.salvar(lancamento);
		lancamento = new Lancamento();
		menssagensAcao("Salvo com Sucesso");
		carregarlancamento();
			
		return"";
	}
	
	public String novoLancamento() {
		
		lancamento = new Lancamento();
		
		return"";
	}

	public String deletandoLancamento() {
		daoGenerico.delete(lancamento);
		lancamento = new Lancamento();
		menssagensAcao("Removido com Sucesso");
		carregarlancamento();
		
		return"";
	}
	@PostConstruct //carrega método ao iniciar à aplicaçao
	public void carregarlancamento() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Usuario usuarioLogin = (Usuario) externalContext.getSessionMap().get("usuarioLogado");
		listaLancamento = (List<Lancamento>) daoLancamento.consultar(usuarioLogin.getId());
		
		
	}
	
	public String atualizandoLancamento() {
		
		lancamento = daoGenerico.Atualizar(lancamento);
		daoGenerico.Atualizar(lancamento);
		lancamento = new Lancamento();
		menssagensAcao("Atualizado com Sucesso");
		carregarlancamento();
		return "";
	}
	
	public void menssagensAcao(String msg) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(msg);
		context.addMessage(null, message);
	}
	
	

}
