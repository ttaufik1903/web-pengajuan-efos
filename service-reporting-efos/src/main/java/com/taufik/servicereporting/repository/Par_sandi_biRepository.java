package com.taufik.servicereporting.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Par_sandi_bi;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_sandi_biRepository extends JpaRepository<Par_sandi_bi, String> {

    @Query(value = "SELECT * FROM pelaporan.par_sandi_bi WHERE \"id\"=:id", nativeQuery = true)
    Par_sandi_bi findBySingleId(@Param("id") String id);
    
    @Query(value = "SELECT * FROM pelaporan.par_sandi_bi WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_sandi_bi> findByid(@Param("id") String id);
    
    @Query(value = "SELECT * FROM pelaporan.par_sandi_bi WHERE \"id\"=:id", nativeQuery = true)
    List<Par_sandi_bi> findByIdData(@Param("id") String id);

    @Query(
            value = "SELECT * FROM pelaporan.par_sandi_bi ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM pelaporan.par_sandi_bi",
            nativeQuery = true)
    Page<Par_sandi_bi> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM pelaporan.par_sandi_bi WHERE name ~* :keyword" +
                    " OR id ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM pelaporan.par_sandi_bi WHERE name ~* :keyword" +
                    " OR id ~* :keyword",
            nativeQuery = true)
    Page<Par_sandi_bi> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM pelaporan.par_sandi_bi",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM pelaporan.par_sandi_bi", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM pelaporan.par_sandi_bi WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") String id);
}

