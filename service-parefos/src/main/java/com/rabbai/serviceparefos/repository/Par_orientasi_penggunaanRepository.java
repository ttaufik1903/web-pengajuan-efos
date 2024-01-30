package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_orientasi_penggunaan;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_orientasi_penggunaanRepository extends JpaRepository<Par_orientasi_penggunaan, String> {

    @Query(value = "SELECT * FROM pelaporan.par_orientasi_penggunaan WHERE \"id\"=:id", nativeQuery = true)
    Par_orientasi_penggunaan findBySingleId(@Param("id") String id);
    
    @Query(value = "SELECT * FROM pelaporan.par_orientasi_penggunaan WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_orientasi_penggunaan> findByid(@Param("id") String id);
    
    @Query(value = "SELECT * FROM pelaporan.par_orientasi_penggunaan WHERE \"id\"=:id", nativeQuery = true)
    List<Par_orientasi_penggunaan> findByIdData(@Param("id") String id);

    @Query(
            value = "SELECT * FROM pelaporan.par_orientasi_penggunaan ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM pelaporan.par_orientasi_penggunaan",
            nativeQuery = true)
    Page<Par_orientasi_penggunaan> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM pelaporan.par_orientasi_penggunaan WHERE name ~* :keyword" +
                    " OR id ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM pelaporan.par_orientasi_penggunaan WHERE name ~* :keyword" +
                    " OR id ~* :keyword",
            nativeQuery = true)
    Page<Par_orientasi_penggunaan> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM pelaporan.par_orientasi_penggunaan",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM pelaporan.par_orientasi_penggunaan", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM pelaporan.par_orientasi_penggunaan WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") String id);
}

