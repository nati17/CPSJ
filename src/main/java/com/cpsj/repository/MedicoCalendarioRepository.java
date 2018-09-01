package com.cpsj.repository;

import com.cpsj.domain.MedicoCalendario;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MedicoCalendario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedicoCalendarioRepository extends JpaRepository<MedicoCalendario, Long> {

}
