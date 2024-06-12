package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoTelefone;
import DAO.DaoUsuario;
import beans.telUserBeans;
import beans.usuarioBeans;

@WebServlet("/telefoneServlets")
public class telefoneServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoTelefone gravaTelefone = new DaoTelefone();
	private DaoUsuario gravaUsuario = new DaoUsuario();
	

	public telefoneServlets() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * segunda forma de fazer sem consulta parametros diretos
		 * 
		 * String userId = request.getParameter("userId"); String userNome =
		 * request.getParameter("usernome");
		 * 
		 * request.getSession().setAttribute("userId", userId);
		 * request.setAttribute("usernome", userNome);
		 * 
		 * 
		 * RequestDispatcher enviarpagina =
		 * request.getRequestDispatcher("/cadTelefone.jsp");
		 * 
		 * // request.setAttribute("telefone", gravaTelefone.Listar());
		 * 
		 * request.setAttribute("userEscolhido", userId);
		 * request.setAttribute("nomeEscolhido", userNome);
		 * 
		 * request.setAttribute("msg", "Salvo Com Sucesso");
		 * 
		 * enviarpagina.forward(request, response);
		 */

		try {

			String userId = request.getParameter("userId");
			String acao = request.getParameter("acao");
			
			if(userId != null) {
				
			
			usuarioBeans usuarioBean = DaoUsuario.consultar(userId);
			
			if(acao.equalsIgnoreCase("addFone")){
			
			request.getSession().setAttribute("userEscolhido", usuarioBean);
			request.setAttribute("userEscolhido", usuarioBean.getId());
			request.setAttribute("nomeEscolhido", usuarioBean.getLogin());
			
			RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadTelefone.jsp");

			try {
				request.setAttribute("telefones", gravaTelefone.ListarTelefone(usuarioBean.getId()));
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			enviarpagina.forward(request, response);
			
			//fim do add fones -->> inicio deletar
			
			}else if(acao.equalsIgnoreCase("deleteFone")) {
				
				String foneId = request.getParameter("foneId");
				gravaTelefone.deletarTelefone(Integer.parseInt(foneId));
				
				usuarioBeans user = (usuarioBeans) request.getSession().getAttribute("userEscolhido");
				
				
				RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadTelefone.jsp");
				request.setAttribute("userEscolhido", user.getId());
				request.setAttribute("nomeEscolhido", user.getLogin());

				try {
					
					request.setAttribute("telefones", gravaTelefone.ListarTelefone(user.getId()));
				} catch (Exception e) {
					
					e.printStackTrace();
				}

				request.setAttribute("msg", "Removido Com Sucesso");
				enviarpagina.forward(request, response);
			
				
			}
			}else {
				RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadUsuario.jsp");
				request.setAttribute("usuario", gravaUsuario.Listar());
				enviarpagina.forward(request, response);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


			usuarioBeans user = (usuarioBeans) request.getSession().getAttribute("userEscolhido");
			
			
			String tel = request.getParameter("telefone");
			String tipoTel = request.getParameter("tipos");
			String acao = request.getParameter("acao");
			
			if(acao == null || (acao != null && !acao.equalsIgnoreCase("voltar")) ) {
				
			
			
			
			if(tel == null || tel != null && tel.isEmpty()) {
			
				
				request.setAttribute("userEscolhido",user.getId());
				request.setAttribute("nomeEscolhido",user.getLogin());

				RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadTelefone.jsp");
				try {
					request.setAttribute("telefones", gravaTelefone.ListarTelefone(user.getId()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("msg", "O Campo Telefone não Pode ser Vazio");
				
				enviarpagina.forward(request, response);
				
			
			
			}else {
				
			
			
			try {

			telUserBeans tels = new telUserBeans();

			tels.setTefoneUser(tel);
			tels.setTipoTelefone(tipoTel);
			tels.setIdUser(user.getId());
			
			gravaTelefone.salvarTelefone(tels);
			
			request.getSession().setAttribute("userEscolhido", user);
			request.setAttribute("userEscolhido",user.getId());
			request.setAttribute("nomeEscolhido",user.getLogin());

			RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadTelefone.jsp");
			request.setAttribute("telefones", gravaTelefone.ListarTelefone(user.getId()));
			request.setAttribute("msg", "Salvo Com Sucesso");
			
			enviarpagina.forward(request, response);
			

		} catch (Exception e) {

			e.printStackTrace();
		}
			}
			
			}else {

				RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadUsuario.jsp");
				try {
					request.setAttribute("usuario", gravaUsuario.Listar());
					enviarpagina.forward(request, response);
					
			}catch (Exception e) {

				e.printStackTrace();
			}
			}
	}
			
				
			}
	


