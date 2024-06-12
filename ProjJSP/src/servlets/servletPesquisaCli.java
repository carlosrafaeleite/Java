package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoUsuario;
import beans.usuarioBeans;

/**
 * Servlet implementation class servletPesquisaCli
 */
@WebServlet("/servletPesquisaCli")
public class servletPesquisaCli extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario usuario = new DaoUsuario();

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String descricao = request.getParameter("consultar");
		
		if(descricao !=null) {
			try {
				List<usuarioBeans> listaPesquisa = usuario.ListarCliente(descricao);
				RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadUsuario.jsp");

				try {
					request.setAttribute("msg", "Pesquisa Concluida");
					request.setAttribute("usuario",listaPesquisa);
					enviarpagina.forward(request, response);
				} catch (Exception e) {

					e.printStackTrace();
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
		}
		
	}

}
