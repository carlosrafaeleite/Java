package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;




@Entity

@NamedQueries({
	
	@NamedQuery(name = "queryLoginUsuario", query = "select u from LoginUsuario u"),
	@NamedQuery(name = "queryLoginUsuarioNome", query = "select u from LoginUsuario u where u.nomeLogin = :nomeLogin")
	
})

public class LoginUsuario  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nomeLogin;
	private String senhaLogin;
	private String email;
	
	@OneToMany(mappedBy = "usuario")
	private List<TelefoneLogin> telefoneLogins;

	public String getEmail() {
		return email;
	}
	public List<TelefoneLogin> getTelefoneLogins() {
		return telefoneLogins;
	}
	public void setTelefoneLogins(List<TelefoneLogin> telefoneLogins) {
		this.telefoneLogins = telefoneLogins;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeLogin() {
		return nomeLogin;
	}
	public void setNomeLogin(String nomeLogin) {
		this.nomeLogin = nomeLogin;
	}
	public String getSenhaLogin() {
		return senhaLogin;
	}
	public void setSenhaLogin(String senhaLogin) {
		this.senhaLogin = senhaLogin;
	}
	@Override
	public String toString() {
		return "LoginUsuario [id=" + id + ", nomeLogin=" + nomeLogin + ", senhaLogin=" + senhaLogin + ", email=" + email
				+ "]";
	}
	
	
	

}	
