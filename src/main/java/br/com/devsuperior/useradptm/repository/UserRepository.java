package br.com.devsuperior.useradptm.repository;

import br.com.devsuperior.useradptm.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByAtivoTrue();
}
