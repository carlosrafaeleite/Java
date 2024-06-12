package servlets;

import java.io.IOException;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoProduto;

import beans.produtoBeans;

/**
 * Servlet implementation class produtoServlets
 */
@WebServlet("/produtoServlets")
public class produtoServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private DaoProduto gravaProduto = new DaoProduto();
	

	
    public produtoServlets() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listarTodos";
		String prod = request.getParameter("prod");
		String produto = request.getParameter("produto");
		
		 
		
		if(acao.equalsIgnoreCase("delete")) {
			
			gravaProduto.deletarProduto(prod);
			
			RequestDispatcher enviaPagina = request.getRequestDispatcher("/cadProduto.jsp");
			request.setAttribute("msg", "Produto"+" " + produto+" " + " excluido");
			
			try {
				request.setAttribute("produto", gravaProduto.ListarProduto());
			} catch (Exception e) {
				e.printStackTrace();
			}
			enviaPagina.forward(request, response);
			
		}else if(acao.equalsIgnoreCase("editar")) {
			
		
			
			 
			try {
				
				produtoBeans beanProd = DaoProduto.consultarProduto(prod);
				
				RequestDispatcher enviaPagina = request.getRequestDispatcher("/cadProduto.jsp");
				request.setAttribute("prod", beanProd);
				enviaPagina.forward(request, response);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}else if(acao.equalsIgnoreCase("listarTodos")) {
			
			RequestDispatcher enviaPagina = request.getRequestDispatcher("/cadProduto.jsp");
			
			try {
				request.setAttribute("produto", gravaProduto.ListarProduto());
			} catch (Exception e) {
				e.printStackTrace();
			}
			enviaPagina.forward(request, response);
		}
			
			
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if(acao !=null && acao.equalsIgnoreCase("reset")) {
			
			RequestDispatcher enviaPagina = request.getRequestDispatcher("/cadProduto.jsp");
			
			try {
				
				request.setAttribute("produto", gravaProduto.ListarProduto());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			enviaPagina.forward(request, response);
			doGet(request, response);
			
		}
		 String idProduto = request.getParameter("idProduto");
		 String nomeProduto = request.getParameter("nomeProduto");
		 String quantProduto = request.getParameter("quantProduto");
		 String valorProduto = request.getParameter("valorProduto");
		 
		 produtoBeans produto = new produtoBeans();
		 
		 produto.setIdProduto(!idProduto.isEmpty() ? Integer.parseInt(idProduto): 0);
		 
		 produto.setNomeProduto(nomeProduto);
		 produto.setQuantProduto(Float.parseFloat(quantProduto));
		 
		
		 valorProduto = valorProduto.replace("R$" , "");
		 valorProduto = valorProduto.replaceAll("\\." , "");
		 valorProduto = valorProduto.replaceAll("\\," , ".");
		 
	
		
	   	 produto.setValorProduto(Float.parseFloat(valorProduto));
		
		 boolean podeInserir = true;
		 
		 try {
			 if(nomeProduto == null || nomeProduto.isEmpty() && quantProduto == null || quantProduto.isEmpty()&& valorProduto == null || valorProduto.isEmpty() ) {
					request.setAttribute("msg", "Nenhum Campo Deve Estar Vazio");
			 }else 
			
			 if(idProduto == null || idProduto.isEmpty() && !DaoProduto.validarProduto(nomeProduto)) {
				 
				 request.setAttribute("msg", "Produto Já Existe");
				 
			 }
			 
			 if(idProduto == null || idProduto.isEmpty() && DaoProduto.validarProduto(nomeProduto)) {
				 
				 gravaProduto.salvarProduto(produto);
				 request.setAttribute("msg", "Salvo com Sucesso");
				 
			 } else if(idProduto != null && !idProduto.isEmpty()) {
					if(!DaoProduto.validarUpdateProduto(nomeProduto, idProduto)){
						
						request.setAttribute("msg", "Produto já esta cadastrado");
					}else {
						DaoProduto.atualizarProduto(produto);
						request.setAttribute("msg", "Produto Atualizado com Sucesso");
					}
					
					if(podeInserir) {
						request.setAttribute("produto", produto);
					}
				 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 RequestDispatcher enviaPagina = request.getRequestDispatcher("/cadProduto.jsp");
		 
		 try {
			 
			 request.setAttribute("produto", gravaProduto.ListarProduto());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		enviaPagina.forward(request, response);
		
		doGet(request, response);
	}
	
	
	

}
