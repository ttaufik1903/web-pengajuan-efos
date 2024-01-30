package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_dinas;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_dinasRepository extends JpaRepository<Par_dinas, String> {

    @Query(value = "SELECT * FROM par_dinas WHERE \"id\"=:id and \"id_cabang\"=:id_cabang", nativeQuery = true)
    Par_dinas findBySingleId(@Param("id") String id, @Param("id_cabang") String id_cabang);
    
    @Query(value = "SELECT * FROM par_dinas WHERE \"id\"=:id and \"id_cabang\"=:id_cabang", nativeQuery = true)
    Optional<Par_dinas> findByid(@Param("id") String id, @Param("id_cabang") String id_cabang);
    
    @Query(value = "SELECT * FROM par_dinas WHERE \"id_cabang\"=:id_cabang", nativeQuery = true)
    List<Par_dinas> findByIdCabang(@Param("id_cabang") String id_cabang);

    @Query(
            value = "SELECT * FROM par_dinas WHERE id_cabang=:id_cabang ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_dinas WHERE id_cabang=:id_cabang",
            nativeQuery = true)
    Page<Par_dinas> findAllWithPagination(Pageable pageable, @Param("id_cabang") String id_cabang);

    @Query(
            value = "SELECT * FROM par_dinas WHERE id_cabang=:id_cabang and name ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM par_dinas WHERE id_cabang=:id_cabang and name ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword",
            nativeQuery = true)
    Page<Par_dinas> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword, @Param("id_cabang") String id_cabang);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_dinas",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_dinas WHERE id_cabang=:id_cabang", nativeQuery = true)
    Integer getCount(@Param("id_cabang") String id_cabang);
    
    @Modifying
    @Query(value = "DELETE FROM par_dinas WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") String id);
}

