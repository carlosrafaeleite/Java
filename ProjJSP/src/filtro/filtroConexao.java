package filtro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import conexao.SingleConection;

@WebFilter(urlPatterns = {"/*"} )
public class filtroConexao implements javax.servlet.Filter{

	
	
	private static Connection connection;
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		try {
		chain.doFilter(arg0, arg1);
		connection.commit();
		}catch(Exception e){
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException{
		
		connection = SingleConection.getconection();
		
		
	}

	

}
