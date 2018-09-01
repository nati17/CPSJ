package com.cpsj.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.cpsj.domain.enumeration.DiasSemanaEnum;

/**
 * A Dias.
 */
@Entity
@Table(name = "dias")
public class Dias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "valor", nullable = false)
    private DiasSemanaEnum valor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiasSemanaEnum getValor() {
        return valor;
    }

    public Dias valor(DiasSemanaEnum valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(DiasSemanaEnum valor) {
        this.valor = valor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dias dias = (Dias) o;
        if (dias.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dias.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Dias{" +
            "id=" + getId() +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
