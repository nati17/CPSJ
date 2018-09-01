package com.cpsj.repository;

import com.cpsj.domain.Enfermedad;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Enfermedad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnfermedadRepository extends JpaRepository<Enfermedad, Long> {

}
