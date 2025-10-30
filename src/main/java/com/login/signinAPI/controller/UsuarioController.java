package com.login.signinAPI.controller;

import com.login.signinAPI.entity.Usuario;
import com.login.signinAPI.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UsuarioController {

    // Injeção de dependencias
    @Autowired
    UsuarioRepository usuarioRepository;

    // Cadastrar usuarios
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody Usuario user){
        Usuario usuario = new Usuario(user.getNome(), user.getEmail(), user.getPassword());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    // Login com email e senha
    @PostMapping("/{email}")
    public ResponseEntity<?> findUser(@PathVariable Usuario user){
        Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
        if (findUser == null) {
            return ResponseEntity.ok("Logado com sucesso");
        } else {
            if (findUser.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok("Logado com sucesso");
            } else {
                return ResponseEntity.ok("Senha está incorreta");
            }
        }
    }

    // Listar todos os usuarios
    @GetMapping
    public ResponseEntity<?> listUsers(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // Deletar Usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        Optional<Usuario> findDelete = usuarioRepository.findById(id);
        if (findDelete.isPresent()) {
            usuarioRepository.delete(findDelete);

        } else {

            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario deletado com sucesso");
        }
    }

    // Procurar pelo ID
    @GetMapping("procurar/{id}")
    public ResponseEntity<?> searchUser(@PathVariable int id){
        Usuario findId = usuarioRepository.findById(id);
        return ResponseEntity.ok(findId);
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody Usuario user) {
        Usuario findUpdate = usuarioRepository.findById(id);
    if (findUpdate == null) {
        return ResponseEntity.ok("Usuario nao encontrado");
    } else {
        findUpdate.setNome(user.getNome());
        findUpdate.setEmail(user.getEmail());
        usuarioRepository.save(findUpdate);
        return ResponseEntity.ok("Usuario atualizado com sucesso");
    }



    }



    }
