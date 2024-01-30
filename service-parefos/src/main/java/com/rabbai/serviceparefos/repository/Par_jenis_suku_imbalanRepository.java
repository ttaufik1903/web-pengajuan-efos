package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_jenis_suku_imbalan;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_jenis_suku_imbalanRepository extends JpaRepository<Par_jenis_suku_imbalan, Integer> {

    @Query(value = "SELECT * FROM pelaporan.par_jenis_suku_imbalan WHERE \"id\"=:id", nativeQuery = true)
    Par_jenis_suku_imbalan findBySingleId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM pelaporan.par_jenis_suku_imbalan WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_jenis_suku_imbalan> findByid(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM pelaporan.par_jenis_suku_imbalan WHERE \"id\"=:id", nativeQuery = true)
    List<Par_jenis_suku_imbalan> findByIdData(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM pelaporan.par_jenis_suku_imbalan ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM pelaporan.par_jenis_suku_imbalan",
            nativeQuery = true)
    Page<Par_jenis_suku_imbalan> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM pelaporan.par_jenis_suku_imbalan WHERE name ~* :keyword" +
                    " OR id ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM pelaporan.par_jenis_suku_imbalan WHERE name ~* :keyword" +
                    " OR id ~* :keyword",
            nativeQuery = true)
    Page<Par_jenis_suku_imbalan> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM pelaporan.par_jenis_suku_imbalan",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM pelaporan.par_jenis_suku_imbalan", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM pelaporan.par_jenis_suku_imbalan WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") Integer id);
}

