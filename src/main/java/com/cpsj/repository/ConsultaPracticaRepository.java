package com.cpsj.repository;

import com.cpsj.domain.ConsultaPractica;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ConsultaPractica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsultaPracticaRepository extends JpaRepository<ConsultaPractica, Long> {

}
