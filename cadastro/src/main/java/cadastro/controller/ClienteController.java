package cadastro.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cadastro.model.Cliente;
import cadastro.model.Login;
import cadastro.repository.ClienteRepository;


@Controller
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public ModelAndView cadcliente() {
		
		ModelAndView nAndView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<Cliente> Itcliente = clienteRepository.findAll();
		nAndView.addObject("clientes", Itcliente);
		nAndView.addObject("clienteobj", new Cliente() );
		
		return nAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "*/salvarcliente")
	public ModelAndView salvarcliente(Cliente cliente) {
		
		clienteRepository.save(cliente);
		ModelAndView nAndView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<Cliente> Itcliente = clienteRepository.findAll();
		nAndView.addObject("clientes", Itcliente);
		nAndView.addObject("clienteobj", new Cliente() );
		return nAndView;	
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listarcliente")
	public ModelAndView listCliente() {
		
		ModelAndView nAndView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<Cliente> Itcliente = clienteRepository.findAll();
		nAndView.addObject("clientes", Itcliente);
		nAndView.addObject("clienteobj", new Cliente() );
		return nAndView;	
		
	}
	@GetMapping("/editarcliente/{idcliente}")
	public ModelAndView editLogin(@PathVariable("idcliente") long idCliente) {
		
		ModelAndView nAndView = new ModelAndView("cadastro/cadastropessoa");
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		nAndView.addObject("clienteobj", cliente.get() );
		
		return nAndView;
		
	}
	
	@GetMapping("/excluircliente/{idcliente}")
	public ModelAndView exclCliente(@PathVariable("idcliente") long idCliente) {
		
		clienteRepository.deleteById(idCliente);
		ModelAndView nAndView = new ModelAndView("cadastro/cadastropessoa");
		nAndView.addObject("clientes", clienteRepository.findAll());
		nAndView.addObject("clienteobj", new Cliente() );
		
		return nAndView;
		
	}
	
	@PostMapping("/pesquisarcliente")
	public ModelAndView pesquisarCliente(@RequestParam("consultarCliente") String consultarCliente) {
		
		ModelAndView nAndView = new ModelAndView("cadastro/cadastropessoa");
		nAndView.addObject("clientes", clienteRepository.FindClienteByName(consultarCliente));
		nAndView.addObject("clienteobj", new Cliente() );
		
		return nAndView;
		
	}
	
	@GetMapping("/telefones/{idcliente}")
	public ModelAndView telefones(@PathVariable("idcliente") long idcliente) {
		
		ModelAndView nAndView = new ModelAndView("cadastro/telefones");
		Optional<Cliente> cliente = clienteRepository.findById(idcliente);
		nAndView.addObject("clienteobj", cliente.get());
		return nAndView;
		
	}
	

}
