package repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Util.HibernateUtil;
import model.Lancamento;

public class ImplementacaoLancamento implements InterfaceDaoLancamento {

	@Override
	public List<Lancamento> consultar(Long idUser) {
		
		List<Lancamento> lista = null;
		EntityManager entityManager = HibernateUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		lista = entityManager.createQuery("from Lancamento where usuario.id = " + idUser).getResultList();
		transaction.commit();
		
		return lista;
	}

}
