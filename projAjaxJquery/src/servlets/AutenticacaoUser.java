package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Usuario;


@WebServlet("/pages/AutenticacaoUser")
public class AutenticacaoUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AutenticacaoUser() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(Boolean.parseBoolean(request.getParameter("sair"))) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession secao = req.getSession();
			secao.invalidate();
			response.sendRedirect("../index.jsp");
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println(request.getParameter("login"));
		//System.out.println(request.getParameter("senha"));
		
		String nome = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		if(nome != null && senha != null && nome.equalsIgnoreCase("rafa") && senha.equalsIgnoreCase("123")  ) {
			Usuario user =  new Usuario();
			user.setLogin(nome);
			user.setSenha(senha);
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession secao = req.getSession();
			secao.setAttribute("usuario", user);
			//RequestDispatcher dispacher = request.getRequestDispatcher("/pages/acesso.jsp");
			RequestDispatcher dispacher = request.getRequestDispatcher(url);
			dispacher.forward(request, response);
			
		}else{
			
			RequestDispatcher dispacher = request.getRequestDispatcher("/pages/CapturandoUsuario.jsp");
			request.setAttribute("msg", "Login e Senha Obrigatórios");
			dispacher.forward(request, response);
		}
	
	}

}
