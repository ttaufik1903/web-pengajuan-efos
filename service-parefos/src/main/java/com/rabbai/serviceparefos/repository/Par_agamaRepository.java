package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_agama;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_agamaRepository extends JpaRepository<Par_agama, Integer> {

    @Query(value = "SELECT * FROM par_agama WHERE \"id\"=:id", nativeQuery = true)
    Par_agama findByIdAgama(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_agama WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_agama> findByid(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM par_agama ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_agama",
            nativeQuery = true)
    Page<Par_agama> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_agama WHERE name ~* :keyword" +
                    " OR ket ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_agama WHERE name ~* :keyword" +
                    " ket ~* :keyword",
            nativeQuery = true)
    Page<Par_agama> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);
    
    Page<Par_agama> findByName(String name, Pageable pageable);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_agama",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_agama", nativeQuery = true)
    Integer getCount();
}

