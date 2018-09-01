package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Diagnostico entity.
 */
public class DiagnosticoDTO implements Serializable {

    private Long id;

    private String idDiagnostico;

    private String codigoDiagnostico;

    private String nombreDiagnostico;

    private String descripcionDiagnostico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(String idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getCodigoDiagnostico() {
        return codigoDiagnostico;
    }

    public void setCodigoDiagnostico(String codigoDiagnostico) {
        this.codigoDiagnostico = codigoDiagnostico;
    }

    public String getNombreDiagnostico() {
        return nombreDiagnostico;
    }

    public void setNombreDiagnostico(String nombreDiagnostico) {
        this.nombreDiagnostico = nombreDiagnostico;
    }

    public String getDescripcionDiagnostico() {
        return descripcionDiagnostico;
    }

    public void setDescripcionDiagnostico(String descripcionDiagnostico) {
        this.descripcionDiagnostico = descripcionDiagnostico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DiagnosticoDTO diagnosticoDTO = (DiagnosticoDTO) o;
        if (diagnosticoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), diagnosticoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DiagnosticoDTO{" +
            "id=" + getId() +
            ", idDiagnostico='" + getIdDiagnostico() + "'" +
            ", codigoDiagnostico='" + getCodigoDiagnostico() + "'" +
            ", nombreDiagnostico='" + getNombreDiagnostico() + "'" +
            ", descripcionDiagnostico='" + getDescripcionDiagnostico() + "'" +
            "}";
    }
}
