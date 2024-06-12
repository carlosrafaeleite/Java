package br.com.repositorio;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.model.Usuario;

public interface InterfaceDaoUsuario {
	
	Usuario consultaUsuario(String login, String senha);
	
	List<SelectItem> listaEstados();

}
