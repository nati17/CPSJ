package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Empresa entity.
 */
public class EmpresaDTO implements Serializable {

    private Long id;

    private String nombreEmpresa;

    private String direccionEmpresa;

    private String telefonoEmpresa;

    private String emailEmpresa;

    private Long sucursalId;

    private String sucursalNombreSucursal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }

    public Long getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public String getSucursalNombreSucursal() {
        return sucursalNombreSucursal;
    }

    public void setSucursalNombreSucursal(String sucursalNombreSucursal) {
        this.sucursalNombreSucursal = sucursalNombreSucursal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmpresaDTO empresaDTO = (EmpresaDTO) o;
        if (empresaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), empresaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmpresaDTO{" +
            "id=" + getId() +
            ", nombreEmpresa='" + getNombreEmpresa() + "'" +
            ", direccionEmpresa='" + getDireccionEmpresa() + "'" +
            ", telefonoEmpresa='" + getTelefonoEmpresa() + "'" +
            ", emailEmpresa='" + getEmailEmpresa() + "'" +
            ", sucursal=" + getSucursalId() +
            ", sucursal='" + getSucursalNombreSucursal() + "'" +
            "}";
    }
}
