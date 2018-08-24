package com.cpsj.repository;

import com.cpsj.domain.Bebida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Bebida entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BebidaRepository extends JpaRepository<Bebida, Long> {

    @Query(value = "select distinct bebida from Bebida bebida left join fetch bebida.values",
        countQuery = "select count(distinct bebida) from Bebida bebida")
    Page<Bebida> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct bebida from Bebida bebida left join fetch bebida.values")
    List<Bebida> findAllWithEagerRelationships();

    @Query("select bebida from Bebida bebida left join fetch bebida.values where bebida.id =:id")
    Optional<Bebida> findOneWithEagerRelationships(@Param("id") Long id);

}
