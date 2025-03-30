package com.todolistjava.to_do_list.dtos;

import jakarta.validation.constraints.NotBlank;

public class ToDoRequestDTO {
    //para entradas

    @NotBlank(message = "O nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "A descrição não pode estar vazia")
    private String descricao;

    public ToDoRequestDTO() {}

    public ToDoRequestDTO(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}