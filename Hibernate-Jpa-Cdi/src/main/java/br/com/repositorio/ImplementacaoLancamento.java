package br.com.repositorio;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


import br.com.model.Lancamento;

@Named
public class ImplementacaoLancamento implements InterfaceDaoLancamento {
	
	@Inject
	private EntityManager entityManager;


	@Override
	public List<Lancamento> consultar(Long idUser) {
		
		List<Lancamento> lista = null;
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		lista = entityManager.createQuery("from Lancamento where usuario.id = " + idUser).getResultList();
		transaction.commit();
		
		return lista;
	}

}
