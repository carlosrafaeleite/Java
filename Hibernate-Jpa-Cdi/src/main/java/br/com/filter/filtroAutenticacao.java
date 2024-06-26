package br.com.filter;

import java.io.IOException;

import javax.inject.Inject;
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

import br.com.Util.HibernateUtil;
import br.com.model.Usuario;

@WebFilter(urlPatterns = {"/*"})
public class filtroAutenticacao implements Filter {
	
	
	@Inject
	private HibernateUtil hibernateUtil;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		hibernateUtil.getEntityManager();
	}
	
	


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		//String usuarioLogado = (String) session.getAttribute("usuarioLogado");
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		
		String url = req.getServletPath();
		
		if (!url.equalsIgnoreCase("index.xhtml") && usuarioLogado == null){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.xhtml");
			dispatcher.forward(request, response);
			return;
		}else {
			// executa as ações do request e do response
			chain.doFilter(request, response);
		}
		
		
	}

	@Override
	public void destroy() {
		
		
	}

}
