package br.com.devsuperior.useradptm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devsuperior.useradptm.entities.User;
import br.com.devsuperior.useradptm.repository.UserRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	UserRepository userRepository;

	@GetMapping
	public List<User> findAll() {
		if(userRepository.findAllByAtivoTrue() != null) {
			List<User> usuarios = userRepository.findAllByAtivoTrue();
			return usuarios;
		}else {
			return null;
		}
	
	}

	@GetMapping(value = "/{id}")
	public User findById(@PathVariable Long id) {
		User usuario = userRepository.findById(id).get();
		return usuario;
	}

	@PostMapping
	@Transactional
	public User insert(@RequestBody User user) {
		User userInsert = userRepository.save(user);
		userInsert.setAtivo(true);
		return userInsert;
	}
	@DeleteMapping
	public String deleteAll() {
		userRepository.deleteAll();
		
		return "USUARIOS DELETADOS";
	}

	@DeleteMapping(value = "/{id}")
	@Transactional
	public Object deleteById(@PathVariable Long id) {
		try {
			User usuario = userRepository.findById(id).get();
			if (usuario.isAtivo()) {
				usuario.excluir();
				return "OBJETO: " + usuario.getId() + " DELETADO";
			} else {
				return "OBJETO JÁ DELETADO ou INEXISTENTE";
			}
		} catch (Exception e) {
			return "OBJETO: " + id + " INCORRETO OU INEXISTENTE";
		}

	}

	@PutMapping()
	public User putByUser(@RequestBody User user) {
		User userInsert = userRepository.save(user); //salva o usuario
		return userInsert;
	}
}
