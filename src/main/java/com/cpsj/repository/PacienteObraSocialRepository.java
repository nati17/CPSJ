package com.cpsj.repository;

import com.cpsj.domain.PacienteObraSocial;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PacienteObraSocial entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PacienteObraSocialRepository extends JpaRepository<PacienteObraSocial, Long> {

}
