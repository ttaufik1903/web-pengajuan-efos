package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_tempat_tinggal;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_tempat_tinggalRepository extends JpaRepository<Par_tempat_tinggal, String> {

    @Query(value = "SELECT * FROM par_tempat_tinggal WHERE \"id\"=:id", nativeQuery = true)
    Par_tempat_tinggal findBySingleId(@Param("id") String id);
    
    @Query(value = "SELECT * FROM par_tempat_tinggal WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_tempat_tinggal> findByid(@Param("id") String id);

    @Query(
            value = "SELECT * FROM par_tempat_tinggal ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_tempat_tinggal",
            nativeQuery = true)
    Page<Par_tempat_tinggal> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_tempat_tinggal WHERE name ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_tempat_tinggal WHERE name ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword",
            nativeQuery = true)
    Page<Par_tempat_tinggal> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);
    
    Page<Par_tempat_tinggal> findByName(String name, Pageable pageable);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_tempat_tinggal",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_tempat_tinggal", nativeQuery = true)
    Integer getCount();
}

