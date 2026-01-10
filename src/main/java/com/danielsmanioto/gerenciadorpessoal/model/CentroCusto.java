package com.danielsmanioto.gerenciadorpessoal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name = "centro_custo")
public class CentroCusto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public CentroCusto() {
    }

    public CentroCusto(String descricao, Long id) {
        this.descricao = descricao;
        this.id = id;
    }

    @NotBlank
    @Column(nullable = false)
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CentroCusto that = (CentroCusto) o;
        return Objects.equals(id, that.id) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao);
    }
}
