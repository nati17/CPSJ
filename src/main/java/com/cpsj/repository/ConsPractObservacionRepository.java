package com.cpsj.repository;

import com.cpsj.domain.ConsPractObservacion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ConsPractObservacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsPractObservacionRepository extends JpaRepository<ConsPractObservacion, Long> {

}
