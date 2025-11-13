package com.login.signinAPI.controller;

import com.login.signinAPI.dto.ProdutoRequestDTO;
import com.login.signinAPI.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProdutoController {

    // Injeção de dependencias
    @Autowired
    ProdutoService produtoService;

    // Cadastrar usuarios
    @PostMapping
    public ResponseEntity<?> saveProduct(@Valid @RequestBody ProdutoRequestDTO product){
        return ResponseEntity.ok(produtoService.saveProduct(product));
    }

    // Listar todos os usuarios
    @GetMapping
    public ResponseEntity<?> listProducts(){
        return ResponseEntity.ok(produtoService.listProducts());
    }

    // Deletar Usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable int id){
        return ResponseEntity.ok(produtoService.deleteProduto(id));
    }

    // Procurar pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> searchProduct(@PathVariable int id){
        return ResponseEntity.ok(produtoService.searchProduct(id));
    }

    // Atualizar o produto
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id,@Valid @RequestBody ProdutoRequestDTO product) {
        return ResponseEntity.ok(produtoService.updateProduct(id,product));
    }
    }
