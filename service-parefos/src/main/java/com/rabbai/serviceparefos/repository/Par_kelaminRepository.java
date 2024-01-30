package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_kelamin;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_kelaminRepository extends JpaRepository<Par_kelamin, Integer> {

	@Query(value = "SELECT * FROM par_kelamin WHERE \"id\"=:id", nativeQuery = true)
    Par_kelamin findBySingleId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_kelamin WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_kelamin> findByid(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM par_kelamin ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_kelamin",
            nativeQuery = true)
    Page<Par_kelamin> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_kelamin WHERE name ~* :keyword" +
                    " OR ket ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_kelamin WHERE name ~* :keyword" +
                    " ket ~* :keyword",
            nativeQuery = true)
    Page<Par_kelamin> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_kelamin",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_kelamin", nativeQuery = true)
    Integer getCount();
}

