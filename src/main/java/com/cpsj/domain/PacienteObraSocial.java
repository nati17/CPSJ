package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PacienteObraSocial.
 */
@Entity
@Table(name = "paciente_obra_social")
public class PacienteObraSocial implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_paciente")
    private String idPaciente;

    @Column(name = "id_o_social")
    private String idOSocial;

    @Column(name = "nro_afiliado")
    private String nroAfiliado;

    @Column(name = "plan_afiliado")
    private String planAfiliado;

    @Column(name = "vigencia")
    private String vigencia;

    @Column(name = "estado")
    private Boolean estado;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public PacienteObraSocial idPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
        return this;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdOSocial() {
        return idOSocial;
    }

    public PacienteObraSocial idOSocial(String idOSocial) {
        this.idOSocial = idOSocial;
        return this;
    }

    public void setIdOSocial(String idOSocial) {
        this.idOSocial = idOSocial;
    }

    public String getNroAfiliado() {
        return nroAfiliado;
    }

    public PacienteObraSocial nroAfiliado(String nroAfiliado) {
        this.nroAfiliado = nroAfiliado;
        return this;
    }

    public void setNroAfiliado(String nroAfiliado) {
        this.nroAfiliado = nroAfiliado;
    }

    public String getPlanAfiliado() {
        return planAfiliado;
    }

    public PacienteObraSocial planAfiliado(String planAfiliado) {
        this.planAfiliado = planAfiliado;
        return this;
    }

    public void setPlanAfiliado(String planAfiliado) {
        this.planAfiliado = planAfiliado;
    }

    public String getVigencia() {
        return vigencia;
    }

    public PacienteObraSocial vigencia(String vigencia) {
        this.vigencia = vigencia;
        return this;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public Boolean isEstado() {
        return estado;
    }

    public PacienteObraSocial estado(Boolean estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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
        PacienteObraSocial pacienteObraSocial = (PacienteObraSocial) o;
        if (pacienteObraSocial.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pacienteObraSocial.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PacienteObraSocial{" +
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
