package br.com.springBoot_rest.br.com.springBoot_rest.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springBoot_rest.br.com.springBoot_rest.model.Usuario;

@Repository

public interface Usuariorepository extends JpaRepository<Usuario, Long> {
	
	@Query(value = "select u from Usuario u where upper(trim(u.nome))like %?1%")
	List<Usuario> pesquisarNome(String nome);
}