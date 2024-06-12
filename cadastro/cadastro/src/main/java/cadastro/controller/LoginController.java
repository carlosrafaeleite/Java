package cadastro.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cadastro.model.Login;
import cadastro.repository.LoginRepository;

@Controller
public class LoginController {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrologin")
	public ModelAndView cadlogin() {
		
		ModelAndView nAndView = new ModelAndView("cadastro/cadastrologin");
		Iterable<Login> Itlogin = loginRepository.findAll();
		nAndView.addObject("logins", Itlogin);
		nAndView.addObject("loginobj", new Login() );
		
		return nAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "*/salvarlogin")
	public ModelAndView salvar(Login login) {
			
		loginRepository.save(login);
		ModelAndView nAndView = new ModelAndView("cadastro/cadastrologin");
		Iterable<Login> Itlogin = loginRepository.findAll();
		nAndView.addObject("logins", Itlogin);
		nAndView.addObject("loginobj", new Login() );
		return nAndView;	
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listarlogin")
	public ModelAndView listLogin() {
		
		ModelAndView nAndView = new ModelAndView("cadastro/cadastrologin");
		Iterable<Login> Itlogin = loginRepository.findAll();
		nAndView.addObject("logins", Itlogin);
		nAndView.addObject("loginobj", new Login() );
		return nAndView;
		
	}
	@GetMapping("/editarlogin/{idlogin}")
	public ModelAndView editLogin(@PathVariable("idlogin") long idlogin) {
		
		ModelAndView nAndView = new ModelAndView("cadastro/cadastrologin");
		Optional<Login> login = loginRepository.findById(idlogin);
		nAndView.addObject("loginobj", login.get());
		
		return nAndView;
		
	}
	
	@GetMapping("/excluirlogin/{idlogin}")
	public ModelAndView exclLogin(@PathVariable("idlogin") long idlogin) {
		
		loginRepository.deleteById(idlogin);
		ModelAndView nAndView = new ModelAndView("cadastro/cadastrologin");
		nAndView.addObject("logins", loginRepository.findAll());
		nAndView.addObject("loginobj", new Login() );
		
		return nAndView;
		
	}
	
	@PostMapping("/pesquisarlogin")
	public ModelAndView pesquisarLogin(@RequestParam("consultarUser") String consultarUser) {
		
		ModelAndView nAndView = new ModelAndView("cadastro/cadastrologin");
		nAndView.addObject("logins", loginRepository.FindLoginByName(consultarUser));
		nAndView.addObject("loginobj", new Login() );
		
		return nAndView;
		
	}
	
	@GetMapping("/telefones/{idlogin}")
	public ModelAndView telefones(@PathVariable("idlogin") long idlogin) {
		
		ModelAndView nAndView = new ModelAndView("cadastro/telefones");
		Optional<Login> login = loginRepository.findById(idlogin);
		nAndView.addObject("loginobj", login.get());
		return nAndView;
		
	}
	

}
