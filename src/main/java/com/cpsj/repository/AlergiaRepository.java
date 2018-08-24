package com.cpsj.repository;

import com.cpsj.domain.Alergia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Alergia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlergiaRepository extends JpaRepository<Alergia, Long> {

    @Query(value = "select distinct alergia from Alergia alergia left join fetch alergia.values",
        countQuery = "select count(distinct alergia) from Alergia alergia")
    Page<Alergia> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct alergia from Alergia alergia left join fetch alergia.values")
    List<Alergia> findAllWithEagerRelationships();

    @Query("select alergia from Alergia alergia left join fetch alergia.values where alergia.id =:id")
    Optional<Alergia> findOneWithEagerRelationships(@Param("id") Long id);

}
