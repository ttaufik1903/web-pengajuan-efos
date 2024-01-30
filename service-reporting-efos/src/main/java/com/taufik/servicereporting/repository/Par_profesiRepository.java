package com.taufik.servicereporting.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Par_profesi;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_profesiRepository extends JpaRepository<Par_profesi, String> {

	@Query(value = "SELECT * FROM par_profesi WHERE \"id\"=:id", nativeQuery = true)
	Par_profesi findBySingleId(@Param("id") String id);
    
    @Query(value = "SELECT * FROM par_profesi WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_profesi> findByid(@Param("id") String id);

    @Query(
            value = "SELECT * FROM par_profesi ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_profesi",
            nativeQuery = true)
    Page<Par_profesi> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_profesi WHERE name ~* :keyword" +
                    " OR ket ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_profesi WHERE name ~* :keyword" +
                    " ket ~* :keyword",
            nativeQuery = true)
    Page<Par_profesi> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);

    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_profesi",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_profesi", nativeQuery = true)
    Integer getCount();
}

