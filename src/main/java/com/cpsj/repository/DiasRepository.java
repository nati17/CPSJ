package com.cpsj.repository;

import com.cpsj.domain.Dias;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dias entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DiasRepository extends JpaRepository<Dias, Long> {

}
