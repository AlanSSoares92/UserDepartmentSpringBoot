package br.com.devsuperior.useradptm.controller;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List<User> findAll(){
        List<User> usuarios = userRepository.findAllByAtivoTrue();
        return usuarios;
    }

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable Long id){
        User usuario = userRepository.findById(id).get();
        return usuario;
    }
    @PostMapping
    @Transactional
    public User insert(@RequestBody User user){
        User userInsert = userRepository.save(user);

        return userInsert;
    }
    
    @DeleteMapping(value = "/{id}")
    @Transactional
    public void deleteById(@PathVariable Long id) {
        User usuario = userRepository.findById(id).get();
        usuario.excluir();

    }
}