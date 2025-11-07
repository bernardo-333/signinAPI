package com.login.signinAPI.dto;

import com.login.signinAPI.entity.Produto;

public class ProdutoResponseDTO {

    private String nome;
    private double preco;
    private int quantidade;
    private double precoTotal;

    public ProdutoResponseDTO(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.quantidade = produto.getQuantidade();
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoTotal() {
        precoTotal = preco*quantidade;
        return precoTotal;
    }
}
