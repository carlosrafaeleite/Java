package repositorio;

import java.util.List;

import javax.faces.model.SelectItem;

import model.Usuario;

public interface InterfaceDaoUsuario {
	
	Usuario consultaUsuario(String login, String senha);
	
	List<SelectItem> listaEstados();

}
