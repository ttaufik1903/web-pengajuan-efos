package com.taufik.servicereporting.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Par_kebangsaan;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_kebangsaanRepository extends JpaRepository<Par_kebangsaan, Integer> {

    @Query(value = "SELECT * FROM par_kebangsaan WHERE \"id\"=:id", nativeQuery = true)
    Par_kebangsaan findBySingleId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_kebangsaan WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_kebangsaan> findByid(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM par_kebangsaan ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_kebangsaan",
            nativeQuery = true)
    Page<Par_kebangsaan> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_kebangsaan WHERE name ~* :keyword" +
                    " OR ket ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_kebangsaan WHERE name ~* :keyword" +
                    " ket ~* :keyword",
            nativeQuery = true)
    Page<Par_kebangsaan> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_kebangsaan",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_kebangsaan", nativeQuery = true)
    Integer getCount();
}

