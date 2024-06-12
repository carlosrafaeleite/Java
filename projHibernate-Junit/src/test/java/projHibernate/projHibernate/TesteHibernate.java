package projHibernate.projHibernate;


import java.util.List;

import org.junit.Test;

import dao.DaoGenerico;
import model.LoginUsuario;
import model.TelefoneLogin;

public class TesteHibernate {
	
	
	//criação de tabelas @Entity
	
	@Test
	public void testeConnectionHirbernate() {
		
		HibernateUtil.getEntityManager(); 
				
	}
	
	
	
	@Test
	public void InserirLogin(){
		
		DaoGenerico<LoginUsuario> daoGenerico = new DaoGenerico<LoginUsuario>();
		LoginUsuario usuario = new LoginUsuario();
		usuario.setNomeLogin("zoio");
		usuario.setSenhaLogin("0987");
		usuario.setEmail("zoio.com");
		daoGenerico.salvar(usuario);	
	}
	
	

	@Test
	public void InserirTelefone(){
		
		DaoGenerico daoGenerico = new DaoGenerico();
		LoginUsuario usuario = (LoginUsuario) daoGenerico.consulta(4L, LoginUsuario.class);
		TelefoneLogin telefoneLogin = new TelefoneLogin();
		telefoneLogin.setNumero("270899");
		telefoneLogin.setTipoTel("fixo");
		telefoneLogin.setUsuario(usuario);
		daoGenerico.salvar(telefoneLogin);
	}
	
	
	@Test
	public void PesquisaLogin() {
		
		DaoGenerico<LoginUsuario> daoGenerico = new DaoGenerico<LoginUsuario>();
		LoginUsuario usuario = new LoginUsuario();
		usuario.setId(2L);
		usuario = daoGenerico.pesquisar(usuario);
		System.out.println(usuario.getId()+" "+usuario.getNomeLogin()+" "+usuario.getSenhaLogin()+" "+usuario.getEmail());
	
	}

	
	@Test
	public void ConsultaLogin() {
		
		DaoGenerico<LoginUsuario> daoGenerico = new DaoGenerico<LoginUsuario>();
		LoginUsuario usuario = daoGenerico.consulta(2L, LoginUsuario.class);
		System.out.println(usuario.getId()+" "+usuario.getNomeLogin()+" "+usuario.getSenhaLogin()+" "+usuario.getEmail());
	
	}
	
	@Test
	public void ConsultaTelefone() {
		
		DaoGenerico daoGenerico = new DaoGenerico();
		LoginUsuario usuario = (LoginUsuario) daoGenerico.consulta(4L, LoginUsuario.class);
		
		for (TelefoneLogin telefone : usuario.getTelefoneLogins()) {
			
		System.out.println(telefone.getId()+" "+telefone.getNumero()+" "+telefone.getTipoTel()+" "+telefone.getUsuario().getNomeLogin());
			
	}
	}
	
			
	
	@Test
	public void AtualizaLogin() {
		
		DaoGenerico<LoginUsuario> daoGenerico = new DaoGenerico<LoginUsuario>();
		LoginUsuario usuario = daoGenerico.consulta(2L, LoginUsuario.class);
		usuario.setNomeLogin("Bernardo");
		usuario.setSenhaLogin("456");
		usuario.setEmail("ber@nardo.com");
		daoGenerico.Atualizar(usuario);	
		System.out.println(usuario.getId()+" "+usuario.getNomeLogin()+" "+usuario.getSenhaLogin()+" "+usuario.getEmail());
	
	}
	
	
	@Test
	public void ExcluirLogin() {
		
		DaoGenerico<LoginUsuario> daoGenerico = new DaoGenerico<LoginUsuario>();
		LoginUsuario usuario = daoGenerico.consulta(5L, LoginUsuario.class);
		daoGenerico.excluir(usuario);

	}
	
	
	@Test
	public void ListagemLogin() {
		
		DaoGenerico<LoginUsuario> daoGenerico = new DaoGenerico<LoginUsuario>();
		List<LoginUsuario> listagem = daoGenerico.Listagem(LoginUsuario.class);
		
	for (LoginUsuario usuario : listagem) {
		
		System.out.println(usuario.getId()+" "+usuario.getNomeLogin()+" "+usuario.getSenhaLogin()+" "+usuario.getEmail());
		System.out.println("---------------------");
		
	}
	}
	
	@SuppressWarnings("unchecked") //The expression of type List needs unchecked conversion to conform to List<LoginUsuario>
	@Test
	public void ListaLogin() {
		
		DaoGenerico<LoginUsuario> daoGenerico = new DaoGenerico<LoginUsuario>();
		//List<LoginUsuario> listar = daoGenerico.getEntityManager().createQuery("from LoginUsuario where nomelogin = 'carlos'").getResultList();
		//List<LoginUsuario> listar = daoGenerico.getEntityManager().createQuery("from LoginUsuario order by id").setMaxResults(3).getResultList();
		//List<LoginUsuario> listar = daoGenerico.getEntityManager().createQuery("from LoginUsuario").getResultList();
		List<LoginUsuario> listar = daoGenerico.getEntityManager().createQuery("from LoginUsuario where nomelogin = :nome").setParameter("nome", "kiara").getResultList();
		
		
	for (LoginUsuario usuario : listar) {
		
		System.out.println(usuario.getId()+" "+usuario.getNomeLogin()+" "+usuario.getSenhaLogin()+" "+usuario.getEmail());
		System.out.println("---------------------");
		
	}
	}
	
	
	@SuppressWarnings("unchecked")//: The expression of type List needs unchecked conversion to conform to List<LoginUsuario>
	@Test
	public void NameQueryLogin() {
		
		DaoGenerico<LoginUsuario> daoGenerico = new DaoGenerico<LoginUsuario>();
		// List<LoginUsuario> listar = daoGenerico.getEntityManager().createNamedQuery("queryLoginUsuario").getResultList();
		List<LoginUsuario> listar = daoGenerico.getEntityManager().createNamedQuery("queryLoginUsuarioNome").setParameter("nomeLogin", "kiara").getResultList();
		
		for (LoginUsuario usuario : listar) {
			
			System.out.println(usuario.getId()+" "+usuario.getNomeLogin()+" "+usuario.getSenhaLogin()+" "+usuario.getEmail());
			System.out.println("---------------------");
	}
	}
}
              
                        
	