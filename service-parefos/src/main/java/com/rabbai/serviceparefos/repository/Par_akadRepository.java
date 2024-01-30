package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_akad;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_akadRepository extends JpaRepository<Par_akad, Integer> {

    @Query(value = "SELECT * FROM par_akad WHERE \"id\"=:id", nativeQuery = true)
    Par_akad findBySingleId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_akad WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_akad> findByid(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_akad WHERE \"id\"=:id", nativeQuery = true)
    List<Par_akad> findByIdAkad(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM par_akad ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_akad",
            nativeQuery = true)
    Page<Par_akad> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_akad WHERE name ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM par_akad WHERE name ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword",
            nativeQuery = true)
    Page<Par_akad> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_akad",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_akad", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM par_akad WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") Integer id);
}

