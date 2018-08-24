package com.cpsj.repository;

import com.cpsj.domain.Intolerancia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Intolerancia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IntoleranciaRepository extends JpaRepository<Intolerancia, Long> {

    @Query(value = "select distinct intolerancia from Intolerancia intolerancia left join fetch intolerancia.values",
        countQuery = "select count(distinct intolerancia) from Intolerancia intolerancia")
    Page<Intolerancia> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct intolerancia from Intolerancia intolerancia left join fetch intolerancia.values")
    List<Intolerancia> findAllWithEagerRelationships();

    @Query("select intolerancia from Intolerancia intolerancia left join fetch intolerancia.values where intolerancia.id =:id")
    Optional<Intolerancia> findOneWithEagerRelationships(@Param("id") Long id);

}
