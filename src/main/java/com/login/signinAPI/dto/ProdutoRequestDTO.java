package com.login.signinAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProdutoRequestDTO {

    @NotBlank(message = "O campo não pode ser vazio")
    private String nome;

    @NotBlank(message = "O campo não pode ser vazio")
    private String preco;

    @Size(min = 1, max = 4, message = "A quantidade deve ter entre 1 a 4 caracteres")
    private String quantidade;

    public ProdutoRequestDTO() {
    }

    public ProdutoRequestDTO(String nome, String preco, String quantidade) {
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

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}
