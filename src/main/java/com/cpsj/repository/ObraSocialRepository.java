package com.cpsj.repository;

import com.cpsj.domain.ObraSocial;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ObraSocial entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ObraSocialRepository extends JpaRepository<ObraSocial, Long> {

}
