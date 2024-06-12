package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.telUserBeans;
import conexao.SingleConection;

public class DaoTelefone {

	private static Connection conexao;

	public DaoTelefone() {

		conexao = SingleConection.getconection();

	}

	public void salvarTelefone(telUserBeans telefones) {

		try {

			String sql = "INSERT INTO tab_telefoneUser (idUser,telefoneUser,tipoTelefone) values(?,?,?)";

			PreparedStatement smt = conexao.prepareStatement(sql);

			smt.setInt(1, telefones.getIdUser());
			smt.setString(2, telefones.getTefoneUser());
			smt.setString(3, telefones.getTipoTelefone());

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

	public List<telUserBeans> ListarTelefone(int users) throws Exception {
		List<telUserBeans> listar = new ArrayList<telUserBeans>();

		String sql = "select * from tab_telefoneUser where idUser = '" + users + "'";
		PreparedStatement smt = conexao.prepareStatement(sql);
		ResultSet resultado = smt.executeQuery();

		while (resultado.next()) {

			telUserBeans listarTelefone = new telUserBeans();

			listarTelefone.setIdFoneUser(resultado.getInt("idFoneUser"));
			listarTelefone.setIdUser(resultado.getInt("idUser"));
			listarTelefone.setTefoneUser(resultado.getString("telefoneUser"));
			listarTelefone.setTipoTelefone(resultado.getString("tipoTelefone"));

			listar.add(listarTelefone);

		}

		return listar;

	}

	public void deletarTelefone(int idTel) {

		try {
			String sql = "delete from tab_telefoneUser where idFoneUser = '" + idTel + "'";
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

}
