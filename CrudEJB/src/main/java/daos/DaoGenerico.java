package daos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DaoGenerico<TIPO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TIPO> listaObjetos;
	private List<TIPO> listaTodos;

	@PersistenceContext(unitName = "CrudEJB")
	protected EntityManager em;
	protected Class classePersistente;

	public DaoGenerico() {

	}

	public List<TIPO> getListaObjetos() {
		String jpql = "from  " + classePersistente.getSimpleName();
		return em.createQuery(jpql).getResultList();
	}

	public List<TIPO> getListaTodos() {
		String jpql = "from  " + classePersistente.getSimpleName();
		return em.createQuery(jpql).getResultList();
	}

	public void persist(TIPO obj) throws Exception {

		em.persist(obj);

	}

	public void merge(TIPO obj) throws Exception {

		em.merge(obj);

	}

	public void remove(TIPO obj) throws Exception {

		Object ObjRemovido = em.merge(obj);
		em.remove(ObjRemovido);

	}
	
	public TIPO getObjectById(Object id) throws Exception{
		
		return (TIPO) em.find(classePersistente, id);
				
		
	}
	

	public void setListaObjetos(List<TIPO> listaObjetos) {
		this.listaObjetos = listaObjetos;
	}

	public void setListaTodos(List<TIPO> listaTodos) {
		this.listaTodos = listaTodos;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Class getClassePersistente() {
		return classePersistente;
	}

	public void setClassePersistente(Class classePersistente) {
		this.classePersistente = classePersistente;
	}

}
