package Class_Java;




import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;



@ManagedBean(name = "inserirCli")
@RequestScoped

public class insereCliente {

	private Cliente cliente = new Cliente();
	private List<Cliente> clientes = new ArrayList<>();
	
	public void adicionacli() {
		
			
		clientes.add(cliente);	
		cliente = new Cliente();
	
		                    
	}


	public Cliente getCliente() {
	
		return cliente;
	
	}

	public void setCliente(Cliente cliente) {
		
		this.cliente = cliente;
		System.out.println(clientes);
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	
	public void salvando() {
		
		
		
		try {
			
			salvar.salvaCliente(cliente);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		
	}
	
	

	
}


