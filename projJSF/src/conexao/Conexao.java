package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	private static  String URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=AulaWeb";
	private static  String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String login = "ConexaoDB";
	private static  String senha = "270899";
	private static Connection conexao = null ;
	
	
	static {
		conectar();
		 
	}
	
	public Conexao() {
		conectar();
	}
	
	
	private static void conectar() {
		
		try {
			
			if (conexao == null) {
				
				Class.forName(DRIVER);
				conexao =  DriverManager.getConnection(URL, login, senha);
				//conexao.setAutoCommit(false);
				System.out.println("deu certo");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static Connection getconection() {
		return conexao;
		
	}
	
	

}