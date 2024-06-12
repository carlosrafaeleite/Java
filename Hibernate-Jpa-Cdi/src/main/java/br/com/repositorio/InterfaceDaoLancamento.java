package br.com.repositorio;

import java.util.List;

import br.com.model.Lancamento;

public interface InterfaceDaoLancamento {
	
	List<Lancamento> consultar(Long idUser);

}
