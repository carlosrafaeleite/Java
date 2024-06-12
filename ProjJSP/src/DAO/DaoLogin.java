package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexao.SingleConection;

public class DaoLogin {

	private Connection conexao;
	
	public DaoLogin() {
		conexao = SingleConection.getconection();
	}
	
	public boolean validaLogin(String login, String senha) throws Exception{
		
		String sql = "select * from tab_login where login = '"+login+"' and senha='"+senha+"'";
		
		PreparedStatement stm = conexao.prepareStatement(sql);
		ResultSet resultado = stm.executeQuery();
		
		if(resultado.next()) {
			return true;
		}else {
			return false;
		}
	}
}
