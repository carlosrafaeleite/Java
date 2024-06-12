package Controllers;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import daos.DaoEstado;
import models.Estados;
import util.Util;

@Named(value = "controleEstado")
@ViewScoped
public class EstadoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	DaoEstado<Estados> dao;
	Estados objeto;

	public EstadoController() {

	}

	public String Listar() {

		//return "/privado/estado/listar?faces-redirect=true";
		return "listar?faces-redirect=true";

	}

	public void novo() {

		objeto = new Estados();
	}

	public void alterar(Object Id) {

		try {
			objeto = dao.getObjectById(Id);
			dao.merge(objeto);
			Util.messagemInformacao("Foi Alterado Com Sucesso");
		} catch (Exception e) {
			Util.menssagemErro("Não pode ser Alterado " + Util.getMenssagemErro(e));
		}
	}
	
	public void excluir(Object Id) {

		try {
			objeto = dao.getObjectById(Id);
			dao.remove(objeto);
			Util.messagemInformacao("Foi Removido Com Sucesso");
		} catch (Exception e) {
			Util.menssagemErro("Não pode ser Removido " + Util.getMenssagemErro(e));
		}
	}

	public void salvar() {

		try {
			if(objeto.getId() == null ) {
				dao.persist(objeto);
				Util.messagemInformacao("Foi Salvo Com Sucesso");
			}else {
				dao.merge(objeto);	
				Util.messagemInformacao("Foi Alterado Com Sucesso");
			}
			
		} catch (Exception e) {
			Util.menssagemErro("Nenhuma Ação foi Executada" + Util.getMenssagemErro(e));
		}
	}
	
	public DaoEstado<Estados> getDao() {
		return dao;
	}

	public void setDao(DaoEstado<Estados> dao) {
		this.dao = dao;
	}

	public Estados getObjeto() {
		return objeto;
	}

	public void setObjeto(Estados objeto) {
		this.objeto = objeto;
	}

}
