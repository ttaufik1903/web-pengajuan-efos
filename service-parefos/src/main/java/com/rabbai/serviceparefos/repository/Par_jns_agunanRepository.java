package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_jns_agunan;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_jns_agunanRepository extends JpaRepository<Par_jns_agunan, String> {

    @Query(value = "SELECT * FROM par_jns_agunan WHERE \"id\"=:id", nativeQuery = true)
    Par_jns_agunan findByIdJenis(@Param("id") String id);
    
    @Query(value = "SELECT * FROM par_jns_agunan WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_jns_agunan> findByid(@Param("id") String id);

    @Query(
            value = "SELECT * FROM par_jns_agunan ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM Par_jns_agunan",
            nativeQuery = true)
    Page<Par_jns_agunan> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_jns_agunan WHERE name ~* :keyword" +
                    " OR ket ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_jns_agunan WHERE name ~* :keyword" +
                    " ket ~* :keyword",
            nativeQuery = true)
    Page<Par_jns_agunan> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);
    
    Page<Par_jns_agunan> findByName(String name, Pageable pageable);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_jns_agunan",nativeQuery = true)
    String getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_jns_agunan", nativeQuery = true)
    String getCount();
}

