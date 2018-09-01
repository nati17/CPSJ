package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MontoConsultaPractica entity.
 */
public class MontoConsultaPracticaDTO implements Serializable {

    private Long id;

    private String idMontoConsPract;

    private String monto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdMontoConsPract() {
        return idMontoConsPract;
    }

    public void setIdMontoConsPract(String idMontoConsPract) {
        this.idMontoConsPract = idMontoConsPract;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MontoConsultaPracticaDTO montoConsultaPracticaDTO = (MontoConsultaPracticaDTO) o;
        if (montoConsultaPracticaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), montoConsultaPracticaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MontoConsultaPracticaDTO{" +
            "id=" + getId() +
            ", idMontoConsPract='" + getIdMontoConsPract() + "'" +
            ", monto='" + getMonto() + "'" +
            "}";
    }
}
