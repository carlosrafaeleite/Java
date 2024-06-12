package filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import user.Usuario;

@WebFilter(urlPatterns = {"/pages/acesso.jsp"})
public class FilterAutenticacao implements Filter{
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession secao = req.getSession();
		Usuario user = (Usuario) secao.getAttribute("usuario");
		
		if(user == null) {//usuario nao logado
			
			RequestDispatcher dispacher = request.getRequestDispatcher("pages/CapturandoUsuario.jsp");
			dispacher.forward(request, response);
			
			return;
		}
				
		chain.doFilter(request, response);
		System.out.println("aeeeeee");
		
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException{
		
	}
	

}
