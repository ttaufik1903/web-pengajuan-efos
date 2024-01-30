package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_provinsi;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_provinsiRepository extends JpaRepository<Par_provinsi, Integer> {

	@Query(value = "SELECT * FROM par_provinsi WHERE \"id\"=:id", nativeQuery = true)
	Par_provinsi findBySingleId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_provinsi WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_provinsi> findByid(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM par_provinsi ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_provinsi",
            nativeQuery = true)
    Page<Par_provinsi> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_provinsi WHERE name ~* :keyword" +
                    " OR ket ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_provinsi WHERE name ~* :keyword" +
                    " ket ~* :keyword",
            nativeQuery = true)
    Page<Par_provinsi> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);

    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_provinsi",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_provinsi", nativeQuery = true)
    Integer getCount();
}

