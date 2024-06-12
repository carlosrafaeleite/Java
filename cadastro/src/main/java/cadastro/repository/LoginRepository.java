package cadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cadastro.model.Login;

@Repository
@Transactional
public interface LoginRepository extends CrudRepository<Login, Long> {
	
	@Query("select p from Login p where p.loginUser like %?1%")
	List<Login> FindLoginByName(String loginUser);

}
