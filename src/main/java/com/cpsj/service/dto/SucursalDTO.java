package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Sucursal entity.
 */
public class SucursalDTO implements Serializable {

    private Long id;

    private String nombreSucursal;

    private Integer numeroSucursal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public Integer getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setNumeroSucursal(Integer numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SucursalDTO sucursalDTO = (SucursalDTO) o;
        if (sucursalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sucursalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SucursalDTO{" +
            "id=" + getId() +
            ", nombreSucursal='" + getNombreSucursal() + "'" +
            ", numeroSucursal=" + getNumeroSucursal() +
            "}";
    }
}
