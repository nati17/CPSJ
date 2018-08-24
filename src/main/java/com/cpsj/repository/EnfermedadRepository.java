package com.cpsj.repository;

import com.cpsj.domain.Enfermedad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Enfermedad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnfermedadRepository extends JpaRepository<Enfermedad, Long> {

    @Query(value = "select distinct enfermedad from Enfermedad enfermedad left join fetch enfermedad.values",
        countQuery = "select count(distinct enfermedad) from Enfermedad enfermedad")
    Page<Enfermedad> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct enfermedad from Enfermedad enfermedad left join fetch enfermedad.values")
    List<Enfermedad> findAllWithEagerRelationships();

    @Query("select enfermedad from Enfermedad enfermedad left join fetch enfermedad.values where enfermedad.id =:id")
    Optional<Enfermedad> findOneWithEagerRelationships(@Param("id") Long id);

}
