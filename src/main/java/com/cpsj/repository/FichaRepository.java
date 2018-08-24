package com.cpsj.repository;

import com.cpsj.domain.Ficha;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Ficha entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {

}
