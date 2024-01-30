package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_sub_sub_dinas;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_sub_sub_dinasRepository extends JpaRepository<Par_sub_sub_dinas, String> {

    @Query(value = "SELECT * FROM par_sub_sub_dinas WHERE \"id\"=:id and \"id_cabang\"=:id_cabang", nativeQuery = true)
    Par_sub_sub_dinas findBySingleId(@Param("id") Integer id, @Param("id_cabang") String id_cabang);
    
    @Query(value = "SELECT * FROM par_sub_sub_dinas WHERE \"id_sub_sub_dinas\"=:id_sub_sub_dinas and \"id_cabang\"=:id_cabang", nativeQuery = true)
    Optional<Par_sub_sub_dinas> findByid(@Param("id_sub_sub_dinas") String id_sub_sub_dinas, @Param("id_cabang") String id_cabang);
    
    @Query(value = "SELECT * FROM par_sub_sub_dinas WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_sub_sub_dinas> findByidrow(@Param("id") String id);
    
    @Query(value = "SELECT * FROM par_sub_sub_dinas WHERE \"id_sub_dinas\"=:id_sub_dinas and \"id_cabang\"=:id_cabang", nativeQuery = true)
    List<Par_sub_sub_dinas> findByIdSubDinas(@Param("id_sub_dinas") String id_sub_dinas, @Param("id_cabang") String id_cabang);
    
    @Query(value = "SELECT * FROM par_sub_sub_dinas WHERE \"id_cabang\"=:id_cabang", nativeQuery = true)
    List<Par_sub_sub_dinas> findByIdCabang(@Param("id_cabang") String id_cabang);

    @Query(
            value = "SELECT * FROM par_sub_sub_dinas WHERE id_cabang =:id_cabang ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_sub_sub_dinas WHERE id_cabang =:id_cabang",
            nativeQuery = true)
    Page<Par_sub_sub_dinas> findAllWithPagination(Pageable pageable, @Param("id_cabang") String id_cabang);

    @Query(
            value = "SELECT * FROM par_sub_sub_dinas WHERE id_cabang =:id_cabang AND (name ~* :keyword" +
                    " OR id_sub_sub_dinas ~* :keyword OR id_sub_dinas ~* :keyword)" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM par_sub_sub_dinas WHERE id_cabang =:id_cabang AND (name ~* :keyword" +
                    " OR id_sub_sub_dinas ~* :keyword OR id_sub_dinas ~* :keyword)",
            nativeQuery = true)
    Page<Par_sub_sub_dinas> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword, @Param("id_cabang") String id_cabang);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_sub_sub_dinas",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_sub_sub_dinas WHERE id_cabang =:id_cabang", nativeQuery = true)
    Integer getCount(@Param("id_cabang") String id_cabang);
    
    @Modifying
    @Query(value = "DELETE FROM par_sub_sub_dinas WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") Integer id);
}

