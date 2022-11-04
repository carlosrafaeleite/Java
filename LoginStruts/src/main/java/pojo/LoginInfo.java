package pojo;

public class LoginInfo {
	

	private String nome;
	private String senha;
	
	public LoginInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public LoginInfo(String nome, String senha ) {
		super();
		this.nome = nome;
		this.senha = senha;
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
		return "LoginInfo [nome=" + nome + ", senha=" + senha + "]";
	}
	
	

}
