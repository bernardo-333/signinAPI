package com.login.signinAPI.dto;

import jakarta.validation.constraints.*;

public class ProdutoRequestDTO {

    @NotBlank(message = "O campo não pode ser vazio")
    private String nome;

    @NotNull(message = "O campo não pode ser vazio")
    @Positive(message = "O preço deve ser maior que zero")
    private double preco;

    @Min(value = 1, message = "A quantidade mínima é 1")
    @Max(value = 1000, message = "A quantidade máxima é 1000")
    private int quantidade;

    public ProdutoRequestDTO() {
    }

    public ProdutoRequestDTO(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
