package com.cpsj.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Bebida.
 */
@Entity
@Table(name = "bebida")
public class Bebida implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "valor", nullable = false)
    private String valor;

    @ManyToMany
    @JoinTable(name = "bebida_values",
               joinColumns = @JoinColumn(name = "bebidas_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "values_id", referencedColumnName = "id"))
    private Set<AntecedentesPersonales> values = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public Bebida valor(String valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Set<AntecedentesPersonales> getValues() {
        return values;
    }

    public Bebida values(Set<AntecedentesPersonales> antecedentesPersonales) {
        this.values = antecedentesPersonales;
        return this;
    }

    public Bebida addValues(AntecedentesPersonales antecedentesPersonales) {
        this.values.add(antecedentesPersonales);
        return this;
    }

    public Bebida removeValues(AntecedentesPersonales antecedentesPersonales) {
        this.values.remove(antecedentesPersonales);
        return this;
    }

    public void setValues(Set<AntecedentesPersonales> antecedentesPersonales) {
        this.values = antecedentesPersonales;
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
        Bebida bebida = (Bebida) o;
        if (bebida.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bebida.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Bebida{" +
            "id=" + getId() +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
