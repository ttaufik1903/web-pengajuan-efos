package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_sifat_investasi;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_sifat_investasiRepository extends JpaRepository<Par_sifat_investasi, String> {

    @Query(value = "SELECT * FROM pelaporan.par_sifat_investasi WHERE \"id\"=:id", nativeQuery = true)
    Par_sifat_investasi findBySingleId(@Param("id") String id);
    
    @Query(value = "SELECT * FROM pelaporan.par_sifat_investasi WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_sifat_investasi> findByid(@Param("id") String id);
    
    @Query(value = "SELECT * FROM pelaporan.par_sifat_investasi WHERE \"id\"=:id", nativeQuery = true)
    List<Par_sifat_investasi> findByIdData(@Param("id") String id);

    @Query(
            value = "SELECT * FROM pelaporan.par_sifat_investasi ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM pelaporan.par_sifat_investasi",
            nativeQuery = true)
    Page<Par_sifat_investasi> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM pelaporan.par_sifat_investasi WHERE name ~* :keyword" +
                    " OR id ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM pelaporan.par_sifat_investasi WHERE name ~* :keyword" +
                    " OR id ~* :keyword",
            nativeQuery = true)
    Page<Par_sifat_investasi> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM pelaporan.par_sifat_investasi",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM pelaporan.par_sifat_investasi", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM pelaporan.par_sifat_investasi WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") String id);
}

