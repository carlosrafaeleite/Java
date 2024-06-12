package repositorio;

import java.util.List;

import model.Lancamento;

public interface InterfaceDaoLancamento {
	
	List<Lancamento> consultar(Long idUser);

}
