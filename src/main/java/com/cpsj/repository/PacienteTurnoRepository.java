package com.cpsj.repository;

import com.cpsj.domain.PacienteTurno;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PacienteTurno entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PacienteTurnoRepository extends JpaRepository<PacienteTurno, Long> {

}
