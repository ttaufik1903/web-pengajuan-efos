package com.taufik.servicereporting.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Par_kualitas;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_kualitasRepository extends JpaRepository<Par_kualitas, String> {

    @Query(value = "SELECT * FROM pelaporan.par_kualitas WHERE \"id\"=:id", nativeQuery = true)
    Par_kualitas findBySingleId(@Param("id") String id);
    
    @Query(value = "SELECT * FROM pelaporan.par_kualitas WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_kualitas> findByid(@Param("id") String id);
    
    @Query(value = "SELECT * FROM pelaporan.par_kualitas WHERE \"id\"=:id", nativeQuery = true)
    List<Par_kualitas> findByIdData(@Param("id") String id);

    @Query(
            value = "SELECT * FROM pelaporan.par_kualitas ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM pelaporan.par_kualitas",
            nativeQuery = true)
    Page<Par_kualitas> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM pelaporan.par_kualitas WHERE name ~* :keyword" +
                    " OR id ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM pelaporan.par_kualitas WHERE name ~* :keyword" +
                    " OR id ~* :keyword",
            nativeQuery = true)
    Page<Par_kualitas> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM pelaporan.par_kualitas",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM pelaporan.par_kualitas", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM pelaporan.par_kualitas WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") String id);
}

