package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MontoConsultaPractica.
 */
@Entity
@Table(name = "monto_consulta_practica")
public class MontoConsultaPractica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_monto_cons_pract")
    private String idMontoConsPract;

    @Column(name = "monto")
    private String monto;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdMontoConsPract() {
        return idMontoConsPract;
    }

    public MontoConsultaPractica idMontoConsPract(String idMontoConsPract) {
        this.idMontoConsPract = idMontoConsPract;
        return this;
    }

    public void setIdMontoConsPract(String idMontoConsPract) {
        this.idMontoConsPract = idMontoConsPract;
    }

    public String getMonto() {
        return monto;
    }

    public MontoConsultaPractica monto(String monto) {
        this.monto = monto;
        return this;
    }

    public void setMonto(String monto) {
        this.monto = monto;
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
        MontoConsultaPractica montoConsultaPractica = (MontoConsultaPractica) o;
        if (montoConsultaPractica.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), montoConsultaPractica.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MontoConsultaPractica{" +
            "id=" + getId() +
            ", idMontoConsPract='" + getIdMontoConsPract() + "'" +
            ", monto='" + getMonto() + "'" +
            "}";
    }
}
