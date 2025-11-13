package com.login.signinAPI.service;

import com.login.signinAPI.dto.ProdutoRequestDTO;
import com.login.signinAPI.dto.ProdutoResponseDTO;
import com.login.signinAPI.entity.Produto;
import com.login.signinAPI.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Cadastrar usuarios
    public String saveProduct(ProdutoRequestDTO product) {
        Produto produto = new Produto(product.getNome(),product.getQuantidade(),product.getPreco());
        produtoRepository.save(produto);
        return "Produto salvo com sucesso";
    }

    // Listar todos os usuarios
    @GetMapping
    public ResponseEntity<?> listProducts(){
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponseDTO> produtosDTO = produtos.stream().map(produto -> new ProdutoResponseDTO(produto)).toList();
        return ResponseEntity.ok(produtosDTO);
    }

    // Deletar Usuario
    public String deleteProduto(int id){
        if (!produtoRepository.existsById(id)) {
            return "Usuario nao encontrado";
        } else {
            produtoRepository.deleteById(id);
            return "Usuario deletado com sucesso";
        }
    }

    // Procurar pelo ID
    public ProdutoResponseDTO searchProduct(int id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            return null;
        }
        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO(produto.get());
        return produtoResponseDTO;
    }

    // Atualizar o produto
    public String updateProduct(int id, ProdutoRequestDTO product) {
        if (!produtoRepository.existsById(id)) {
            return "Produto não encontrado";
        } else {
            Produto updateUser = produtoRepository.findById(id).get();
            updateUser.setNome(product.getNome());
            updateUser.setPreco(product.getPreco());
            produtoRepository.save(updateUser);
            return "Produto deletado com sucesso";
        }
    }
}
