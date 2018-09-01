package com.cpsj.repository;

import com.cpsj.domain.Digitalizacion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Digitalizacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DigitalizacionRepository extends JpaRepository<Digitalizacion, Long> {

}
