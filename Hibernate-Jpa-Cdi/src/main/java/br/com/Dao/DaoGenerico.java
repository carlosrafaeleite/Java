package br.com.Dao;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.Util.HibernateUtil;




@Named
public class DaoGenerico <E>  {
	
	
	@Inject
	private EntityManager entityManager;
	@Inject
	private HibernateUtil hibernateUtil;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}


	public void salvar(E entidade) {
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
		
	}
	

	public E Atualizar(E entidade) {
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E entidadeAtualizada = entityManager.merge(entidade);
		transaction.commit();
		return entidadeAtualizada;
	}
	
 //erro de cast
	@SuppressWarnings("unchecked") //Unchecked cast from capture#1-of ? extends  
	public E pesquisar(E entidade) {
		
		Object id = hibernateUtil.getPrimaryKey(entidade);
		E e = (E) entityManager.find(entidade.getClass(), id);
		return e;

	}
	
	public E consulta(Long id,Class<E> entidade) {
	
		E e = (E) entityManager.find(entidade, id);
		return e;

	}
	
	
	public void excluir(E entidade) {
	
		Object id = hibernateUtil.getPrimaryKey(entidade);
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.createNativeQuery("delete from " + entidade.getClass().getSimpleName().toLowerCase() + " where id = " + id).executeUpdate(); 
		transaction.commit(); 

	}
	
	public void delete(E entidade) {
		
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Object id = hibernateUtil.getPrimaryKey(entidade);
		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id).executeUpdate(); 
		transaction.commit(); 

	}
	

	@SuppressWarnings("unchecked") //The expression of type List needs unchecked conversion to conform to List<E>
	public List<E> Listagem(Class<E> entidade){
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		List<E> lista = entityManager.createQuery("from " + entidade.getName()).getResultList();
		transaction.commit();
		return lista;
		
	}
	
public List<E> listando(Class<E> entidade) {
		
	
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		List<E> retorno = entityManager.createQuery("from " + entidade.getClass().getCanonicalName()).getResultList();
		transaction.commit(); 
		return retorno;

	}
	
	
	
	
	
			
}
