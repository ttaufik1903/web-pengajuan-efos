package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_code_officer;
import com.rabbai.serviceparefos.models.Par_kode_referal;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_kode_referalRepository extends JpaRepository<Par_kode_referal, String> {

    @Query(value = "SELECT * FROM par_kode_referal WHERE \"id\"=:id", nativeQuery = true)
    Par_kode_referal findByReferal(@Param("id") String id);
    
    @Query(value = "SELECT * FROM par_kode_referal WHERE \"id\"=:id and \"id_cabang\"=:id_cabang", nativeQuery = true)
    Par_kode_referal findBySingleId(@Param("id") String id, @Param("id_cabang") String id_cabang);
    
    @Query(value = "SELECT * FROM par_kode_referal WHERE \"id\"=:id and \"id_cabang\"=:id_cabang", nativeQuery = true)
    Optional<Par_kode_referal> findByKdReferal(@Param("id") String id, @Param("id_cabang") String id_cabang);
    
    @Query(value = "SELECT * FROM par_kode_referal WHERE \"id\"=:id", nativeQuery = true)
    List<Par_kode_referal> findByidReferal (@Param("id") String id);
    
    @Query(value = "SELECT * FROM par_kode_referal WHERE \"id_cabang\"=:id_cabang", nativeQuery = true)
    List<Par_kode_referal> findByCabang(@Param("id_cabang") String id_cabang);

    @Query(
            value = "SELECT * FROM par_kode_referal ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_kode_referal",
            nativeQuery = true)
    Page<Par_kode_referal> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_kode_referal WHERE id ~* :keyword" +
                    " OR name ~* :keyword OR id_cabang ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_kode_referal WHERE id ~* :keyword" +
                    " OR name ~* :keyword OR id_cabang ~* :keyword",
            nativeQuery = true)
    Page<Par_kode_referal> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);
    


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_kode_referal",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_kode_referal", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM par_kode_referal WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") String id);
}

