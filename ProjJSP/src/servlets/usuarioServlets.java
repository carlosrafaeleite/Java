package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoLogin;


@WebServlet("/usuarioServlets")
public class usuarioServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoLogin daologin = new DaoLogin();

	public usuarioServlets() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			String login = request.getParameter("Login");
			String senha = request.getParameter("Senha");
			
			if(login !=null && !login.isEmpty() && senha != null !=senha.isEmpty()) {
				
						
			if (daologin.validaLogin(login, senha)) {
				
				RequestDispatcher enviarpagina = request.getRequestDispatcher("LogouCerto.jsp");
				enviarpagina.forward(request, response);

			} else {
				RequestDispatcher enviarpagina = request.getRequestDispatcher("LogouErrado.jsp");
				enviarpagina.forward(request, response);

			}
			}else {
				RequestDispatcher enviarpagina = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Insira um login e senha");
				
				enviarpagina.forward(request, response);
				
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		doGet(request, response);
	}

}
