package cadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import cadastro.model.Funcionario;
import cadastro.repository.FuncionarioRepository;

@Controller
public class FunconarioController {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrofuncionario")
	public  ModelAndView cadFuncionario() {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrofuncionario");
		return andView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarfuncionario")
	public String salvarFuncionario(Funcionario funcionario, Model model) {
		
		funcionarioRepository.save(funcionario);
		model.addAttribute("mensagem", "Funcionário cadastrado com sucesso!");
		
		return "cadastro/cadastrofuncionario";
		
		
	}

}
