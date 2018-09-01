package com.cpsj.repository;

import com.cpsj.domain.MedicoObraSocial;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MedicoObraSocial entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedicoObraSocialRepository extends JpaRepository<MedicoObraSocial, Long> {

}
