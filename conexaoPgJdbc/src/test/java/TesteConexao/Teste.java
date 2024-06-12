package TesteConexao;

import org.junit.Test;

import ConexaoJDBC.conexaoPG;

public class Teste {
	
	@Test
	public void initBanco() {
   
		conexaoPG.getConnection();
		
	}


}
