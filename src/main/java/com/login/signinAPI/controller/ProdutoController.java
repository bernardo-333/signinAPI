package com.login.signinAPI.controller;

import com.login.signinAPI.dto.ProdutoRequestDTO;
import com.login.signinAPI.dto.ProdutoResponseDTO;
import com.login.signinAPI.entity.Produto;
import com.login.signinAPI.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<?> saveProduct(@Valid @RequestBody ProdutoRequestDTO product){
        Produto produto = new Produto(product.getNome(),product.getQuantidade(),product.getPreco());
        produtoRepository.save(produto);
        return ResponseEntity.ok(produto);
    }

    // Listar todos os usuarios
    @GetMapping
    public ResponseEntity<?> listProducts(){
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponseDTO> produtosDTO = produtos.stream().map(produto -> new ProdutoResponseDTO(produto)).toList();
        return ResponseEntity.ok(produtosDTO);
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
        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO(produto.get());
        return ResponseEntity.ok(produtoResponseDTO);
    }

    // Atualizar o produto
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id,@Valid @RequestBody ProdutoRequestDTO product) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Produto não encontrado");
        } else {
            Produto updateUser = produtoRepository.findById(id).get();
            updateUser.setNome(product.getNome());
            updateUser.setPreco(product.getPreco());
            produtoRepository.save(updateUser);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Produto deletado com sucesso");
        }
    }
    }
