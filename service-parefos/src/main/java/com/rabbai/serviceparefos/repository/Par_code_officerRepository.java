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

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_code_officerRepository extends JpaRepository<Par_code_officer, String> {

    @Query(value = "SELECT * FROM par_code_officer WHERE \"id\"=:id", nativeQuery = true)
    Par_code_officer findByOfficer(@Param("id") String id);
    
    @Query(value = "SELECT * FROM par_code_officer WHERE \"id\"=:id and \"id_cabang\"=:id_cabang", nativeQuery = true)
    Par_code_officer findBySingleId(@Param("id") String id,@Param("id_cabang") String id_cabang);
    
    @Query(value = "SELECT * FROM par_code_officer WHERE \"id\"=:id and \"id_cabang\"=:id_cabang", nativeQuery = true)
    Optional<Par_code_officer> findByKdOfficer(@Param("id") String id, @Param("id_cabang") String id_cabang);
    
    @Query(value = "SELECT * FROM par_code_officer WHERE \"id_cabang\"=:id_cabang", nativeQuery = true)
    List<Par_code_officer> findByCabang(@Param("id_cabang") String id_cabang);
    
    
    @Query(value = "SELECT * FROM par_code_officer WHERE \"id\"=:id", nativeQuery = true)
    List<Par_code_officer> findByIdOfficer (@Param("id") String id);

    @Query(
            value = "SELECT * FROM par_code_officer ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_code_officer",
            nativeQuery = true)
    Page<Par_code_officer> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_code_officer WHERE id ~* :keyword OR id_cabang ~* :keyword" +
                    " OR name ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_code_officer WHERE" +
                    " name ~* :keyword OR id ~* :keyword OR id_cabang ~* :keyword",
            nativeQuery = true)
    Page<Par_code_officer> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);
    


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_code_officer",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_code_officer", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM par_code_officer WHERE \"id\"=:id and \"id_cabang\"=:id_cabang", nativeQuery = true)
    int deleteBySingleId(@Param("id") String id, @Param("id_cabang") String id_cabang);
}

