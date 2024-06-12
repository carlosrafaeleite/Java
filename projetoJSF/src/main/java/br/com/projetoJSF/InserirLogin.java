package br.com.projetoJSF;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "inserirCli")
@RequestScoped

public class InserirLogin {

	private PessoaBean pessoa = new PessoaBean();

	private List<PessoaBean> pessoas = new ArrayList<PessoaBean>();

	public void adicionarPessoa() {

		pessoas.add(pessoa);
		pessoa = new PessoaBean();

	}

	public PessoaBean getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaBean pessoa) {
		this.pessoa = pessoa;
	}

	public List<PessoaBean> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaBean> pessoas) {
		this.pessoas = pessoas;
	}

	public String SalvarLogin() {

		SalvandoLogin.Salvar(pessoa);
		System.out.println("Salvou");
		return "principal";

	}

}
