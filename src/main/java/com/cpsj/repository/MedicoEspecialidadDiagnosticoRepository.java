package com.cpsj.repository;

import com.cpsj.domain.MedicoEspecialidadDiagnostico;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MedicoEspecialidadDiagnostico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedicoEspecialidadDiagnosticoRepository extends JpaRepository<MedicoEspecialidadDiagnostico, Long> {

}
