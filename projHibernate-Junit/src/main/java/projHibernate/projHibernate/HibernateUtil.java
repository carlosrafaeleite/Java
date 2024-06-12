package projHibernate.projHibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	
	public static EntityManagerFactory factory = null;
	
	static {
		
		init();
	}
	
	private static void init() {
		
		try {
			
			if(factory == null) {
				

				factory = Persistence.createEntityManagerFactory("projHibernate");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static EntityManager getEntityManager() {
		
		return factory.createEntityManager(); /*persistencia*/
		
	}
	
	public static Object getPrimaryKey(Object entity){ // Retorna a primay key
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}


}
