package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.usuarioBeans;
import conexao.SingleConection;

public class DaoUsuario {

	private static Connection conexao;

	public DaoUsuario() {

		conexao = SingleConection.getconection();

	}

	public void salvarUsuario(usuarioBeans usuario) {

		try {

			String sql = "INSERT INTO tab_login (login,senha,cep,rua,bairro,cidade,estado,ibge,ativo,sexo,cargo,foto,contet,fotoMiniatura) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement smt;
			smt = conexao.prepareStatement(sql);
			smt.setString(1, usuario.getLogin());
			smt.setString(2, usuario.getSenha());
			smt.setString(3, usuario.getCep());
			smt.setString(4, usuario.getRua());
			smt.setString(5, usuario.getBairro());
			smt.setString(6, usuario.getCidade());
			smt.setString(7, usuario.getEstado());
			smt.setString(8, usuario.getIbge());
			smt.setBoolean(9, usuario.isAtivo());
			smt.setString(10, usuario.getSexo());
			smt.setString(11, usuario.getCargo());
			
			smt.setString(12, usuario.getFotoBase64());
			smt.setString(13,usuario.getContentType());
			smt.setString(14,usuario.getFotoBase64Miniatura());
			smt.execute();
			conexao.commit();

		} catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}
	
	public List<usuarioBeans> ListarCliente(String consulta) throws Exception {
		List<usuarioBeans> listarCliente = new ArrayList<>();

		String sql = "select * from tab_login where login <> 'adm' and login like '%"+consulta+"%'";
		PreparedStatement smt = conexao.prepareStatement(sql);
		ResultSet resultado = smt.executeQuery();

		while (resultado.next()) {

			usuarioBeans listaUsuario = new usuarioBeans();
			listaUsuario.setId(resultado.getInt("id"));
			listaUsuario.setLogin(resultado.getString("login"));
			listaUsuario.setSenha(resultado.getString("senha"));
			listaUsuario.setCep(resultado.getString("cep"));
			listaUsuario.setRua(resultado.getString("rua"));
			listaUsuario.setBairro(resultado.getString("bairro"));
			listaUsuario.setCidade(resultado.getString("cidade"));
			listaUsuario.setEstado(resultado.getString("estado"));
			listaUsuario.setIbge(resultado.getString("ibge"));
			//listaUsuario.setFotoBase64(resultado.getString("foto"));
			listaUsuario.setContentType(resultado.getString("contet"));
			listaUsuario.setFotoBase64Miniatura(resultado.getString("fotoMiniatura"));
			listaUsuario.setAtivo(resultado.getBoolean("ativo"));
			listaUsuario.setSexo(resultado.getString("sexo"));
			listarCliente.add(listaUsuario);

		}

		return listarCliente;

	}

	public List<usuarioBeans> Listar() throws Exception {
		List<usuarioBeans> listar = new ArrayList<>();

		String sql = "select * from tab_login where login <> 'adm'";
		PreparedStatement smt = conexao.prepareStatement(sql);
		ResultSet resultado = smt.executeQuery();

		while (resultado.next()) {

			usuarioBeans listaUsuario = new usuarioBeans();
			listaUsuario.setId(resultado.getInt("id"));
			listaUsuario.setLogin(resultado.getString("login"));
			listaUsuario.setSenha(resultado.getString("senha"));
			listaUsuario.setCep(resultado.getString("cep"));
			listaUsuario.setRua(resultado.getString("rua"));
			listaUsuario.setBairro(resultado.getString("bairro"));
			listaUsuario.setCidade(resultado.getString("cidade"));
			listaUsuario.setEstado(resultado.getString("estado"));
			listaUsuario.setIbge(resultado.getString("ibge"));
			//listaUsuario.setFotoBase64(resultado.getString("foto"));
			listaUsuario.setContentType(resultado.getString("contet"));
			listaUsuario.setFotoBase64Miniatura(resultado.getString("fotoMiniatura"));
			
			listaUsuario.setAtivo(resultado.getBoolean("ativo"));
			listaUsuario.setSexo(resultado.getString("sexo"));

			listar.add(listaUsuario);

		}

		return listar;

	}

	public void deletar(String id) {

		try {
			String sql = "delete from tab_login where id = '" + id + "'and login <> 'adm'";
			PreparedStatement psm;
			psm = conexao.prepareStatement(sql);
			psm.execute();
			conexao.commit();
		} catch (SQLException e) {

			e.printStackTrace();
			try {
				conexao.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}

	}

	public static usuarioBeans consultar(String user) throws SQLException {
		String sql = "select * from tab_login where id = '" + user + "'and login <> 'adm'";
		PreparedStatement psm = conexao.prepareStatement(sql);
		ResultSet resultado = psm.executeQuery();

		if (resultado.next()) {

			usuarioBeans usuario = new usuarioBeans();
			usuario.setId(resultado.getInt("id"));
			usuario.setLogin(resultado.getString("login"));
			usuario.setSenha(resultado.getString("senha"));
			usuario.setCep(resultado.getString("cep"));
			usuario.setRua(resultado.getString("rua"));
			usuario.setBairro(resultado.getString("bairro"));
			usuario.setCidade(resultado.getString("cidade"));
			usuario.setEstado(resultado.getString("estado"));
			usuario.setIbge(resultado.getString("ibge"));
			usuario.setFotoBase64(resultado.getString("foto"));
			usuario.setContentType(resultado.getString("contet"));
			usuario.setFotoBase64Miniatura(resultado.getString("fotoMiniatura"));
			usuario.setAtivo(resultado.getBoolean("ativo"));
			usuario.setSexo(resultado.getString("sexo"));
			usuario.setCargo(resultado.getString("cargo"));
			
			return usuario;
		}
		return null;
	}
	
	
	
	public static boolean validar(String login) throws SQLException {
		String sql = "select  count(1) as qtd from tab_login where login = '" + login + "'";
		PreparedStatement psm = conexao.prepareStatement(sql);
		ResultSet resultado = psm.executeQuery();

		if (resultado.next()) {

			return resultado.getInt ("qtd") <=0;
			
		}
		return false;
	}
	
	public static boolean validarUpdate(String login, String id) throws SQLException {
		String sql = "select  count(1) as qtd from tab_login where login = '" + login + "' and id <> " + id;
		PreparedStatement psm = conexao.prepareStatement(sql);
		ResultSet resultado = psm.executeQuery();

		if (resultado.next()) {

			return resultado.getInt ("qtd") <=0;
			
		}
		return false;
	}

	public static void atualizar(usuarioBeans usuario) {

		try {
			/*
			
			String sql = "update tab_login set login = ?, senha =? , cep =?, rua =? , bairro =? , cidade =? , estado =?, ibge =?, foto=?, contet=?, fotoMiniatura=? where id = '" + usuario.getId() + "'";
			PreparedStatement smt = conexao.prepareStatement(sql);
			smt.setString(1, usuario.getLogin());
			smt.setString(2, usuario.getSenha());
			smt.setString(3, usuario.getCep());
			smt.setString(4, usuario.getRua());
			smt.setString(5, usuario.getBairro());
			smt.setString(6, usuario.getCidade());
			smt.setString(7, usuario.getEstado());
			smt.setString(8, usuario.getIbge());
			
			smt.setString(9, usuario.getFotoBase64());
			smt.setString(10,usuario.getContentType());
			
			smt.setString(11,usuario.getFotoBase64Miniatura());
			
			smt.executeUpdate();
			conexao.commit();
		
			//inicio da resolução
			
			
			*/
		
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tab_login SET login = ?, senha =? , cep =?, rua =? , bairro =? , cidade =? , estado =?, ibge =?, ativo =?, sexo =?, cargo =?");
			if (usuario.isAtualizarImagem()) {
				sql.append(", foto=?, contet=?, fotoMiniatura=?");
			}
			sql.append(" WHERE id = " + usuario.getId());
			PreparedStatement smt = conexao.prepareStatement(sql.toString());
			smt.setString(1, usuario.getLogin());
			smt.setString(2, usuario.getSenha());
			smt.setString(3, usuario.getCep());
			smt.setString(4, usuario.getRua());
			smt.setString(5, usuario.getBairro());
			smt.setString(6, usuario.getCidade());
			smt.setString(7, usuario.getEstado());
			smt.setString(8, usuario.getIbge());
			smt.setBoolean(9, usuario.isAtivo());
			smt.setString(10, usuario.getSexo());
			smt.setString(11, usuario.getCargo());
			if (usuario.isAtualizarImagem()) {
				smt.setString(12, usuario.getFotoBase64());
				smt.setString(13, usuario.getContentType());
				smt.setString(14, usuario.getFotoBase64Miniatura());
			}
			smt.executeUpdate();
			conexao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

	}

}
