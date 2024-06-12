package util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Rafael
 */

public class Util implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String getMenssagemErro(Exception e) {

		while (e.getCause() != null) {
			e = (Exception) e.getCause();
		}

		String retorno = e.getMessage();
		if (retorno.contains("Restrição de Chave Estrangeira")) {
			retorno = "Registro não pode ser Apresentado";
		}
		return retorno;
	}

	public static void messagemInformacao(String menssagem) {

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true); 
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, menssagem, "");
		context.addMessage(null, msg);
	}
	
	public static void menssagemErro(String menssagem) {

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true); 
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, menssagem, "");
		context.addMessage(null, msg);
	}

}
