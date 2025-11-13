package com.login.signinAPI.controller;

import com.login.signinAPI.dto.UsuarioRequestDTO;
import com.login.signinAPI.entity.Usuario;
import com.login.signinAPI.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UsuarioController {

    // Injeção de dependencias
    @Autowired
    UsuarioService usuarioService;

    // Cadastrar usuarios
    @PostMapping
    public ResponseEntity<?> saveUser(@Valid @RequestBody UsuarioRequestDTO user){
        return ResponseEntity.ok(usuarioService.saveUser(user));
    }

    // Login com email e senha
    @PostMapping("/login")
    public ResponseEntity<?> findUser(@RequestBody Usuario user){
        return ResponseEntity.ok(usuarioService.findUser(user));
    }

    // Listar todos os usuarios

    // Metodo novo com lambda
    @GetMapping
    public ResponseEntity<?> listUsers(){
        return ResponseEntity.ok(usuarioService.listUsers());
    }

//    Metodo antigo sem lambda
//    @GetMapping
//    public ResponseEntity<?> listUsers(){
//        List<Usuario> usuarios = usuarioRepository.findAll();
//        List<UsuarioResponseDTO> usuariosDTO = new ArrayList<>();
//        for(Usuario u: usuarios) {
//            UsuarioResponseDTO dto = new UsuarioResponseDTO(u.getNome(), u.getEmail());
//            usuariosDTO.add(dto);
//        }
//        return ResponseEntity.ok(usuariosDTO);
//    }


    // Deletar Usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        return ResponseEntity.ok(usuarioService.deleteUser(id));
    }

    // Procurar pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> searchUser(@PathVariable int id){
        return ResponseEntity.ok(usuarioService.searchUser(id));
    }

    // Atualizar o usur
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UsuarioRequestDTO user) {
            return ResponseEntity.ok(usuarioService.updateUser(id, user));
    }
    }


