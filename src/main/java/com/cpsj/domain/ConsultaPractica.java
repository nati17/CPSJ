package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ConsultaPractica.
 */
@Entity
@Table(name = "consulta_practica")
public class ConsultaPractica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attribute")
    private String attribute;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttribute() {
        return attribute;
    }

    public ConsultaPractica attribute(String attribute) {
        this.attribute = attribute;
        return this;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
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
        ConsultaPractica consultaPractica = (ConsultaPractica) o;
        if (consultaPractica.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), consultaPractica.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConsultaPractica{" +
            "id=" + getId() +
            ", attribute='" + getAttribute() + "'" +
            "}";
    }
}
