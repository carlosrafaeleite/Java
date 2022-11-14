package br.com.springBoot_rest.br.com.springBoot_rest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springBoot_rest.br.com.springBoot_rest.model.Usuario;

@Repository

public interface Usuariorepository extends JpaRepository<Usuario, Long> {

}
