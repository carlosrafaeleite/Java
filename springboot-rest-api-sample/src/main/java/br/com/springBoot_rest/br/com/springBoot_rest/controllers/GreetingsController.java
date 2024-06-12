package br.com.springBoot_rest.br.com.springBoot_rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springBoot_rest.br.com.springBoot_rest.model.Usuario;
import br.com.springBoot_rest.br.com.springBoot_rest.repository.Usuariorepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired
	private Usuariorepository usuariorepository;

	
	/**
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Esse e meu nome " + name + "!";
	}
 
	
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */
	
	@RequestMapping(value = "/testemapeamento/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String Testemapeamento(@PathVariable String name) {

		// TESTE DE CONEXAO
		Usuario usuario = new Usuario();
		usuario.setNome(name);
		usuariorepository.save(usuario);// grava no bd
		return "Testando mapeamanto " + name;
	}

	@GetMapping(value = "listatodos") // primeiro metodo de API
	@ResponseBody // retorna os dados para o corpo da resposta	
	public ResponseEntity<List<Usuario>> listaUsuario() {

		List<Usuario> usuarios = usuariorepository.findAll();// executa a consulta
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);// retorna um json

	}

	@PostMapping(value = ("salvar")) // mapeia a url
	@ResponseBody // descricao da resposta
	public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) { // recebe os dados para salvar

		Usuario user = usuariorepository.save(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

	}

	@DeleteMapping(value = ("excluir")) // mapeia a url
	@ResponseBody // descricao da resposta
	public ResponseEntity<String> deletarUsuario(@RequestParam Long iduser) { // recebe os dados para deletar

		usuariorepository.deleteById(iduser);
		return new ResponseEntity<String>("Excluido com Sucesso", HttpStatus.OK);

	}

	@GetMapping(value = ("pesquisar")) // mapeia a url
	@ResponseBody // descricao da resposta
	public ResponseEntity<Usuario> Pesquisar(@RequestParam(name = "iduser") Long iduser) { // recebe os dados para pesquisa

		Usuario usuario = usuariorepository.findById(iduser).get();
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

	}
	
	
	@GetMapping(value = ("pesquisarNome")) // mapeia a url
	@ResponseBody // descricao da resposta
	public ResponseEntity<List<Usuario>> PesquisarNome(@RequestParam(name = "nome") String  nome) { // recebe os dados para pesquisa

		List<Usuario> usuario = usuariorepository.pesquisarNome(nome.trim().toUpperCase());
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);

	}	
	
	
	@PutMapping(value = ("atualizar")) // mapeia a url
	@ResponseBody // descricao da resposta
	public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario) { // recebe os dados para salvar

		Usuario user = usuariorepository.saveAndFlush(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

	}

}
