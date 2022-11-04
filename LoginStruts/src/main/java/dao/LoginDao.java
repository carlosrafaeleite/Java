package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

import conexao.conexaoPG;
import pojo.LoginInfo;

public class LoginDao {
	
	
	public static boolean userValido(LoginInfo userDetalhes) {
		
		
		boolean valida = true;
		
		try {
			
			Connection conn = conexaoPG.getConnection();
			Statement stm = (Statement) conn.createStatement();
			ResultSet rs = ((java.sql.Statement) stm).executeQuery("select * from estudo where nome = '"+userDetalhes.getNome()+"' AND senha= '"+userDetalhes.getSenha()+"'");
			while(rs.next()) {
				valida = true;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		
		return valida;
		
		
		
	}
	
	
	

}
