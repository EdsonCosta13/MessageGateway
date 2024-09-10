package com.edsoncosta.messageGateway.generic.dto;


/******************************
 * Created by: Edson Costa
 * Date: 08/07/2024
 * Time: 12:42 PM
 ******************************/

public class GenericValueRequestDTO <E> extends AbstractRequestDTO<E> {
    private String nome;
    private String descricao;

    @Override
    public E mapToEntity() {
        return null;
    }

    public String getNome() {
        return nome;
    }

    public GenericValueRequestDTO<E> setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public GenericValueRequestDTO<E> setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }
}
