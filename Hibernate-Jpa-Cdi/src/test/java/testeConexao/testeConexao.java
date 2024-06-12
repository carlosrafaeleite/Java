package testeConexao;


import javax.persistence.Persistence;

public class testeConexao {
	
	public static void main(String[] args) {
		
		Persistence.createEntityManagerFactory("Hibernate-Jpa-Cdi");
		
		
	}
	
}
