package Class_Java;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;



import conexao.Conexao;

@ManagedBean(name = "salvaCli")
@RequestScoped



public class salvar extends Conexao {
	
	public static void salvaCliente(Cliente cliente){
		
		try {
		
			Connection con = Conexao.getconection();
		    

			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO login (login,senha) values(?,?)");

			preparedStatement.setString(1, cliente.getNomeCliente());
			preparedStatement.setString(2, cliente.getSenhaCliente());
			preparedStatement.execute();
			
			System.out.println("gravou com sucesso");
			
			
		
		} catch (SQLException ex) {
			
			System.err.println("gravou nada");


		}
		
	}
	
   

}
