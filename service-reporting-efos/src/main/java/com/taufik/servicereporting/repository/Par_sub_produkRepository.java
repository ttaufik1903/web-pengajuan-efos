package com.taufik.servicereporting.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Par_sub_produk;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_sub_produkRepository extends JpaRepository<Par_sub_produk, Integer> {

    @Query(value = "SELECT * FROM par_sub_produk WHERE \"id\"=:id", nativeQuery = true)
    Par_sub_produk findBySingleId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_sub_produk WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_sub_produk> findByid(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_sub_produk WHERE \"id_produk\"=:id_produk", nativeQuery = true)
    List<Par_sub_produk> findByIdProduk(@Param("id_produk") Integer id_produk);

    @Query(
            value = "SELECT * FROM par_sub_produk ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_sub_produk",
            nativeQuery = true)
    Page<Par_sub_produk> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_sub_produk WHERE name ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM par_sub_produk WHERE name ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword",
            nativeQuery = true)
    Page<Par_sub_produk> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_sub_produk",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_sub_produk", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM par_sub_produk WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") Integer id);
}

