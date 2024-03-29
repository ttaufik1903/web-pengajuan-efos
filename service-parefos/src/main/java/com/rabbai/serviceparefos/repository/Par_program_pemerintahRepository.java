package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_program_pemerintah;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_program_pemerintahRepository extends JpaRepository<Par_program_pemerintah, String> {

    @Query(value = "SELECT * FROM pelaporan.par_program_pemerintah WHERE \"id\"=:id", nativeQuery = true)
    Par_program_pemerintah findBySingleId(@Param("id") String id);
    
    @Query(value = "SELECT * FROM pelaporan.par_program_pemerintah WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_program_pemerintah> findByid(@Param("id") String id);
    
    @Query(value = "SELECT * FROM pelaporan.par_program_pemerintah WHERE \"id\"=:id", nativeQuery = true)
    List<Par_program_pemerintah> findByIdData(@Param("id") String id);

    @Query(
            value = "SELECT * FROM pelaporan.par_program_pemerintah ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM pelaporan.par_program_pemerintah",
            nativeQuery = true)
    Page<Par_program_pemerintah> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM pelaporan.par_program_pemerintah WHERE name ~* :keyword" +
                    " OR id ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM pelaporan.par_program_pemerintah WHERE name ~* :keyword" +
                    " OR id ~* :keyword",
            nativeQuery = true)
    Page<Par_program_pemerintah> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM pelaporan.par_program_pemerintah",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM pelaporan.par_program_pemerintah", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM pelaporan.par_program_pemerintah WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") String id);
}

