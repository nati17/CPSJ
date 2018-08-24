package com.cpsj.repository;

import com.cpsj.domain.Regimen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Regimen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegimenRepository extends JpaRepository<Regimen, Long> {

    @Query(value = "select distinct regimen from Regimen regimen left join fetch regimen.values",
        countQuery = "select count(distinct regimen) from Regimen regimen")
    Page<Regimen> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct regimen from Regimen regimen left join fetch regimen.values")
    List<Regimen> findAllWithEagerRelationships();

    @Query("select regimen from Regimen regimen left join fetch regimen.values where regimen.id =:id")
    Optional<Regimen> findOneWithEagerRelationships(@Param("id") Long id);

}
