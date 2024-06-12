package br.com.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


import br.com.model.Estados;
import br.com.model.Usuario;
@Named
public class ImplementacaoUsuario implements InterfaceDaoUsuario {
	
	@Inject
	private EntityManager entityManager;


	@Override
	public Usuario consultaUsuario(String login, String senha) {
		
		Usuario usuario = null;
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		usuario = (Usuario) entityManager.createQuery("select p from Usuario p where p.nomeUsuario = '"+ login + "' and p.senhaUsuario = '"+ senha + "'").getSingleResult();
		
		transaction.commit(); 
		
		return usuario;
	}

	@Override
	public List<SelectItem> listaEstados() {
		
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		List<Estados> estados = entityManager.createQuery("from Estados").getResultList();
		
		for (Estados estado : estados) {
			selectItems.add(new SelectItem(estado, estado.getNome()));
			
		}
		return selectItems;
	}
	
	

}
