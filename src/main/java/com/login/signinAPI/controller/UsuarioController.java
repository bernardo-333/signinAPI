package com.login.signinAPI.controller;

import com.login.signinAPI.dto.UsuarioRequestDTO;
import com.login.signinAPI.entity.Usuario;
import com.login.signinAPI.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<?> saveUser(@Valid @RequestBody UsuarioRequestDTO user){
        Usuario usuario = new Usuario(user.getNome(), user.getEmail(), user.getPassword());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    // Login com email e senha
    @PostMapping("/login")
    public ResponseEntity<?> findUser(@RequestBody Usuario user){
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
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario nao encontrado");
        } else {
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario deletado com sucesso");
        }
    }

    // Procurar pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> searchUser(@PathVariable int id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return ResponseEntity.ok(usuario);
    }

    // Atualizar o usur
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody Usuario user) {

        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario nao encontrado");
        } else {
            Usuario updateUser = usuarioRepository.findById(id).get();
            updateUser.setNome(user.getNome());
            updateUser.setEmail(user.getEmail());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario deletado com sucesso");
        }
    }



    }
