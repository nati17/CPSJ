package com.cpsj.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Intolerancia.
 */
@Entity
@Table(name = "intolerancia")
public class Intolerancia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "valor", nullable = false)
    private String valor;

    @ManyToMany
    @JoinTable(name = "intolerancia_values",
               joinColumns = @JoinColumn(name = "intolerancias_id", referencedColumnName = "id"),
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

    public Intolerancia valor(String valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Set<AntecedentesPersonales> getValues() {
        return values;
    }

    public Intolerancia values(Set<AntecedentesPersonales> antecedentesPersonales) {
        this.values = antecedentesPersonales;
        return this;
    }

    public Intolerancia addValues(AntecedentesPersonales antecedentesPersonales) {
        this.values.add(antecedentesPersonales);
        return this;
    }

    public Intolerancia removeValues(AntecedentesPersonales antecedentesPersonales) {
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
        Intolerancia intolerancia = (Intolerancia) o;
        if (intolerancia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), intolerancia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Intolerancia{" +
            "id=" + getId() +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
