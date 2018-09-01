package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PacienteObraSocial entity.
 */
public class PacienteObraSocialDTO implements Serializable {

    private Long id;

    private String idPaciente;

    private String idOSocial;

    private String nroAfiliado;

    private String planAfiliado;

    private String vigencia;

    private Boolean estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdOSocial() {
        return idOSocial;
    }

    public void setIdOSocial(String idOSocial) {
        this.idOSocial = idOSocial;
    }

    public String getNroAfiliado() {
        return nroAfiliado;
    }

    public void setNroAfiliado(String nroAfiliado) {
        this.nroAfiliado = nroAfiliado;
    }

    public String getPlanAfiliado() {
        return planAfiliado;
    }

    public void setPlanAfiliado(String planAfiliado) {
        this.planAfiliado = planAfiliado;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public Boolean isEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PacienteObraSocialDTO pacienteObraSocialDTO = (PacienteObraSocialDTO) o;
        if (pacienteObraSocialDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pacienteObraSocialDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PacienteObraSocialDTO{" +
            "id=" + getId() +
            ", idPaciente='" + getIdPaciente() + "'" +
            ", idOSocial='" + getIdOSocial() + "'" +
            ", nroAfiliado='" + getNroAfiliado() + "'" +
            ", planAfiliado='" + getPlanAfiliado() + "'" +
            ", vigencia='" + getVigencia() + "'" +
            ", estado='" + isEstado() + "'" +
            "}";
    }
}
