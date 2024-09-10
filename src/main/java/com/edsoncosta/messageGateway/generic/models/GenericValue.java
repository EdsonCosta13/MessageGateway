package com.edsoncosta.messageGateway.generic.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GenericValue extends GenericId {

    @Column(nullable = false)
    private String nome;
    private String descricao;

    public GenericValue(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public GenericValue() {
    }

}
