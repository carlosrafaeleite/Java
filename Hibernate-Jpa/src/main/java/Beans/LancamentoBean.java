package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

import Dao.DaoGenerico;
import model.Lancamento;
import model.Usuario;
import repositorio.ImplementacaoLancamento;
import repositorio.InterfaceDaoLancamento;



@ViewScoped
@ManagedBean(name = "lancamentobean")
public class LancamentoBean {
	
	
	private Lancamento lancamento = new Lancamento();
	private DaoGenerico<Lancamento> daoGenerico = new DaoGenerico<Lancamento>();
	private List<Lancamento> listaLancamento = new ArrayList<Lancamento>();
	private InterfaceDaoLancamento daoLancamento = new ImplementacaoLancamento();
	
	
	
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
