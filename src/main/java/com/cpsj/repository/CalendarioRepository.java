package com.cpsj.repository;

import com.cpsj.domain.Calendario;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Calendario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Long> {

}
