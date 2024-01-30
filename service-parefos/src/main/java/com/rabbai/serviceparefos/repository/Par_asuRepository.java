package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_asu;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_asuRepository extends JpaRepository<Par_asu, String> {

    @Query(value = "SELECT * FROM par_asu WHERE \"id\"=:id", nativeQuery = true)
    Par_asu findByIdAsu(@Param("id") String id);
    
    @Query(value = "SELECT * FROM par_asu WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_asu> findByid(@Param("id") String id);

    @Query(
            value = "SELECT * FROM par_asu ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_asu",
            nativeQuery = true)
    Page<Par_asu> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_asu WHERE name ~* :keyword" +
                    " OR ket ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_asu WHERE name ~* :keyword" +
                    " ket ~* :keyword",
            nativeQuery = true)
    Page<Par_asu> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);
    
    Page<Par_asu> findByName(String name, Pageable pageable);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_asu",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_asu", nativeQuery = true)
    Integer getCount();
}

