package com.cpsj.repository;

import com.cpsj.domain.CodigoPostal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CodigoPostal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CodigoPostalRepository extends JpaRepository<CodigoPostal, Long> {
    @Query(value = "SELECT p FROM CodigoPostal p WHERE p.nombreCiudad like %?1% or p.codigo like %?1%")
    Page<CodigoPostal> findByNombreCiudadContaining(String query, Pageable pageable);

}
