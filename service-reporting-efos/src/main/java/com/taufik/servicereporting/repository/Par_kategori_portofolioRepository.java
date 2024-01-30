package com.taufik.servicereporting.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Par_kategori_portofolio;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_kategori_portofolioRepository extends JpaRepository<Par_kategori_portofolio, Integer> {

    @Query(value = "SELECT * FROM pelaporan.par_kategori_portofolio WHERE \"id\"=:id", nativeQuery = true)
    Par_kategori_portofolio findBySingleId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM pelaporan.par_kategori_portofolio WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_kategori_portofolio> findByid(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM pelaporan.par_kategori_portofolio WHERE \"id\"=:id", nativeQuery = true)
    List<Par_kategori_portofolio> findByIdData(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM pelaporan.par_kategori_portofolio ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM pelaporan.par_kategori_portofolio",
            nativeQuery = true)
    Page<Par_kategori_portofolio> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM pelaporan.par_kategori_portofolio WHERE name ~* :keyword" +
                    " OR id ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM pelaporan.par_kategori_portofolio WHERE name ~* :keyword" +
                    " OR id ~* :keyword",
            nativeQuery = true)
    Page<Par_kategori_portofolio> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM pelaporan.par_kategori_portofolio",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM pelaporan.par_kategori_portofolio", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM pelaporan.par_kategori_portofolio WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") Integer id);
}

