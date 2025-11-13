package com.login.signinAPI.service;

import com.login.signinAPI.dto.UsuarioRequestDTO;
import com.login.signinAPI.dto.UsuarioResponseDTO;
import com.login.signinAPI.entity.Usuario;
import com.login.signinAPI.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Cadastrar usuarios
    public String saveUser(UsuarioRequestDTO user){
        Usuario usuario = new Usuario(user.getNome(), user.getEmail(), user.getPassword());
        usuarioRepository.save(usuario);
        return "Usuario salvo com sucesso";
    }

    // Login com email e senha
    public String findUser(Usuario user){
        Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
        if (findUser == null) {
            return "Logado com sucesso";
        } else {
            if (findUser.getPassword().equals(user.getPassword())) {
                return "Logado com sucesso";
            } else {
                return "Senha está incorreta";
            }
        }
    }

    // Listar todos os usuarios

    // Metodo novo com lambda
    public List<UsuarioResponseDTO> listUsers(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> usuariosDTO = usuarios.stream()
                .map(usuario -> new UsuarioResponseDTO(usuario))
                .toList();
        return usuariosDTO;
    }

//    Metodo antigo sem lambda
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
    public String deleteUser(int id){
        if (!usuarioRepository.existsById(id)) {
            return "Usuario nao encontrado";
        } else {
            usuarioRepository.deleteById(id);
            return "Usuario deletado com sucesso";
        }
    }

    // Procurar pelo ID
    public UsuarioResponseDTO searchUser(@PathVariable int id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return null;
        }
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(usuario.get());
        return usuarioResponseDTO;
    }

    // Atualizar o usuario
    public String updateUser(int id, UsuarioRequestDTO user) {
        if (!usuarioRepository.existsById(id)) {
            return "Usuario nao encontrado";
        } else {
            Usuario updateUser = usuarioRepository.findById(id).get();
            updateUser.setNome(user.getNome());
            updateUser.setEmail(user.getEmail());
            usuarioRepository.save(updateUser);
            return "Usuario deletado com sucesso";
        }
    }

}
