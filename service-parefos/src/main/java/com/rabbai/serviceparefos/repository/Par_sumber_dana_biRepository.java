package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_sumber_dana_bi;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_sumber_dana_biRepository extends JpaRepository<Par_sumber_dana_bi, String> {

    @Query(value = "SELECT * FROM pelaporan.par_sumber_dana_bi WHERE \"id\"=:id", nativeQuery = true)
    Par_sumber_dana_bi findBySingleId(@Param("id") String id);
    
    @Query(value = "SELECT * FROM pelaporan.par_sumber_dana_bi WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_sumber_dana_bi> findByid(@Param("id") String id);
    
    @Query(value = "SELECT * FROM pelaporan.par_sumber_dana_bi WHERE \"id\"=:id", nativeQuery = true)
    List<Par_sumber_dana_bi> findByIdData(@Param("id") String id);

    @Query(
            value = "SELECT * FROM pelaporan.par_sumber_dana_bi ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM pelaporan.par_sumber_dana_bi",
            nativeQuery = true)
    Page<Par_sumber_dana_bi> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM pelaporan.par_sumber_dana_bi WHERE name ~* :keyword" +
                    " OR id ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM pelaporan.par_sumber_dana_bi WHERE name ~* :keyword" +
                    " OR id ~* :keyword",
            nativeQuery = true)
    Page<Par_sumber_dana_bi> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM pelaporan.par_sumber_dana_bi",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM pelaporan.par_sumber_dana_bi", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM pelaporan.par_sumber_dana_bi WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") String id);
}

