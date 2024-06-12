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

import beans.Usuario;

@WebFilter(urlPatterns = {"/pages/*"})
public class FilterAutenticacao implements Filter{
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession secao = req.getSession();
		Usuario user = (Usuario) secao.getAttribute("usuario");
		
		String url = req.getServletPath();
		
		if(user == null && !url.equalsIgnoreCase("/pages/AutenticacaoUser")) {//usuario nao logado
			RequestDispatcher dispacher = request.getRequestDispatcher("/pages/CapturandoUsuario.jsp?url=" +url);
			dispacher.forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException{
		
	}
	

}
