package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.produtoBeans;
import conexao.SingleConection;

public class DaoProduto {
	
	
	private static Connection conexao;

	public DaoProduto() {

		conexao = SingleConection.getconection();

	}
	
	public void salvarProduto(produtoBeans produto) {

		try {

			String sql = "INSERT INTO tab_produto (nomeProduto,quantProduto,valorProduto) values(?,?,?)";

			PreparedStatement smt = conexao.prepareStatement(sql);
			smt.setString(1, produto.getNomeProduto());
			smt.setFloat (2, produto.getQuantProduto());
			smt.setFloat(3, produto.getValorProduto());
		
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
	
	public List<produtoBeans> ListarProduto() throws Exception {
		List<produtoBeans> listar = new ArrayList<>();

		String sql = "select * from tab_produto";
		PreparedStatement smt = conexao.prepareStatement(sql);
		ResultSet resultado = smt.executeQuery();

		while (resultado.next()) {

			produtoBeans listarProduto = new produtoBeans();
			listarProduto.setIdProduto(resultado.getInt("idProduto"));
			listarProduto.setNomeProduto(resultado.getString("nomeProduto"));
			listarProduto.setQuantProduto(resultado.getFloat ("quantProduto"));
			listarProduto.setValorProduto(resultado.getFloat("valorProduto"));

			listar.add(listarProduto);

		}

		return listar;

	}
	
	public void deletarProduto(String idProduto) {

		try {
			String sql = "delete from tab_produto where idProduto = '" + idProduto + "'";
			PreparedStatement psm = conexao.prepareStatement(sql);
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
	
	
	
	public static produtoBeans consultarProduto(String produtos) throws SQLException {
		String sql = "select * from tab_produto where nomeProduto = '" + produtos + "'";
		PreparedStatement psm = conexao.prepareStatement(sql);
		ResultSet resultado = psm.executeQuery();

		if (resultado.next()) {

			produtoBeans produto = new produtoBeans();
			produto.setIdProduto(resultado.getInt("idProduto"));
			produto.setNomeProduto(resultado.getString("nomeProduto"));
			produto.setQuantProduto(resultado.getFloat("quantProduto"));
			produto.setValorProduto(resultado.getFloat("valorProduto"));
			return produto;
		}
		return null;
	}
	
	public static boolean validarProduto(String produto) throws SQLException {
		String sql = "select  count(1) as qtd from tab_produto where nomeProduto = '" + produto + "'";
		PreparedStatement psm = conexao.prepareStatement(sql);
		ResultSet resultado = psm.executeQuery();

		if (resultado.next()) {

			return resultado.getInt ("qtd") <=0;
			
		}
		return false;
	}
	
	public static boolean validarUpdateProduto(String produto, String idProduto) throws SQLException {
		String sql = "select  count(1) as qtd from tab_produto where nomeProduto = '" + produto + "' and idProduto <> " + idProduto;
		PreparedStatement psm = conexao.prepareStatement(sql);
		ResultSet resultado = psm.executeQuery();

		if (resultado.next()) {

			return resultado.getInt ("qtd") <=0;
			
		}
		return false;
	}
	
	public static void atualizarProduto(produtoBeans produto) {

		try {
			String sql = "update tab_produto set nomeProduto = ?, quantProduto =?, valorProduto =? where idProduto = '" + produto.getIdProduto()+ "'";
			PreparedStatement smt = conexao.prepareStatement(sql);
			
			
			smt.setString(1, produto.getNomeProduto());
			smt.setFloat (2, produto.getQuantProduto());
			smt.setFloat(3, produto.getValorProduto());
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
