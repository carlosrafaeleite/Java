package action;

import com.opensymphony.xwork2.ActionSupport;

import dao.LoginDao;
import pojo.LoginInfo;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String senha;

	
	public String execute() {
		
		@SuppressWarnings("unused")
		String statuscodigo= "";
		
		boolean valida = LoginDao.userValido(new LoginInfo(nome, senha));
		
		if(valida) {
			statuscodigo = "success";
		}else {
			
			statuscodigo = "input";

		}
	
		return "statuscodigo";

	}
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "LoginAction [nome=" + nome + ", senha=" + senha + "]";
	}


	

}
