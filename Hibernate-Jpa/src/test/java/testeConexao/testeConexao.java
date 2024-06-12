package testeConexao;


import javax.persistence.Persistence;

import org.junit.Test;

public class testeConexao {
	@Test
	public void testeHibernate() {
		
		Persistence.createEntityManagerFactory("Hibernate-Jpa");
		
		
	}
	
}
