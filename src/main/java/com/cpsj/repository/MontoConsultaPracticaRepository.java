package com.cpsj.repository;

import com.cpsj.domain.MontoConsultaPractica;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MontoConsultaPractica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MontoConsultaPracticaRepository extends JpaRepository<MontoConsultaPractica, Long> {

}
