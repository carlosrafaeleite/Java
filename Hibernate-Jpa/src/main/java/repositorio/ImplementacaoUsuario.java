package repositorio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import Util.HibernateUtil;
import model.Estados;
import model.Usuario;

public class ImplementacaoUsuario implements InterfaceDaoUsuario {

	@Override
	public Usuario consultaUsuario(String login, String senha) {
		
		Usuario usuario = null;
		EntityManager entityManager = HibernateUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		usuario = (Usuario) entityManager.createQuery("select p from Usuario p where p.nomeUsuario = '"+ login + "' and p.senhaUsuario = '"+ senha + "'").getSingleResult();
		
		transaction.commit(); 
		
		return usuario;
	}

	@Override
	public List<SelectItem> listaEstados() {
		
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		
		EntityManager entityManager = HibernateUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		List<Estados> estados = entityManager.createQuery("from Estados").getResultList();
		
		for (Estados estado : estados) {
			selectItems.add(new SelectItem(estado, estado.getNome()));
			
		}
		return selectItems;
	}
	
	

}
