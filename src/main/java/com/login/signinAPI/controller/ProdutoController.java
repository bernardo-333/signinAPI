package com.login.signinAPI.controller;

import com.login.signinAPI.entity.Produto;
import com.login.signinAPI.entity.Usuario;
import com.login.signinAPI.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProdutoController {

    // Injeção de dependencias
    @Autowired
    ProdutoRepository produtoRepository;

    // Cadastrar usuarios
    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Produto product){
        Produto produto = new Produto(product.getNome(),product.getQuantidade(),product.getPreco());
        produtoRepository.save(produto);
        return ResponseEntity.ok(produto);
    }

    // Listar todos os usuarios
    @GetMapping
    public ResponseEntity<?> listProducts(){
        List<Produto> produtos = produtoRepository.findAll();
        return ResponseEntity.ok(produtos);
    }

    // Deletar Usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable int id){
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario nao encontrado");
        } else {
            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario deletado com sucesso");
        }
    }

    // Procurar pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> searchProduct(@PathVariable int id){
        Optional<Produto> produto = produtoRepository.findById(id);
        return ResponseEntity.ok(produto);
    }

    // Atualizar o produto
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Produto product) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Produto não encontrado");
        } else {
            Produto updateUser = produtoRepository.findById(id).get();
            updateUser.setNome(product.getNome());
            updateUser.setPreco(product.getPreco());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Produto deletado com sucesso");
        }
    }
    }
