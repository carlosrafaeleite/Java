package br.com.springBoot_rest.br.com.springBoot_rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Esse e meu nome " + name + "!";
    }
    
    @RequestMapping(value = "/testemapeamento/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String Testemapeamento(@PathVariable String name) {
    	
    	//TESTE DE CONEXAO
    	Usuario usuario = new Usuario();
    	usuario.setNome(name);
    	usuariorepository.save(usuario);//grava no bd
    	return "Testando mapeamanto " + name;
    }
    
    @GetMapping(value = "listatodos")//primeiro metodo de API
    @ResponseBody//retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	
    	List<Usuario> usuarios=  usuariorepository.findAll();//executa a consulta
	    return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);//retorna um json
    	
    }


}
