package com.login.signinAPI.controller;

import com.login.signinAPI.entity.Usuario;
import com.login.signinAPI.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping(value = "usuario/cadastro")
    public ResponseEntity<Usuario> saveUser(@RequestBody Usuario user){
        Usuario usuario = new Usuario(user.getNome(), user.getEmail(), user.getPassword());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("login")
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
}
