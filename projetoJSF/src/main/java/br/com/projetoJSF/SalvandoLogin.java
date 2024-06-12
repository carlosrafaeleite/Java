package br.com.projetoJSF;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;



public class SalvandoLogin {
	
	public static void Salvar(PessoaBean pessoa)   {
		
		try {
			
			Connection con = Conexao.getConnection();
			
			String sql = "INSERT INTO login (login,senha) values(?,?)";
			PreparedStatement smt;
			smt = con.prepareStatement(sql);
			smt.setString(1, pessoa.getLogin());
			smt.setString(2, pessoa.getSenha());
			smt.execute();
			con.commit();
			
		} catch (SQLException e) {
			System.err.println("gravou nada " + e);
			
		}
		
		
	}
	
	

}
