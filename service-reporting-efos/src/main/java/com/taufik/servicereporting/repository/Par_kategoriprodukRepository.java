package com.taufik.servicereporting.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Par_kategori_produk;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_kategoriprodukRepository extends JpaRepository<Par_kategori_produk, Integer> {

    @Query(value = "SELECT * FROM par_kategori_produk WHERE \"id\"=:id", nativeQuery = true)
    Par_kategori_produk findBySingleId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_kategori_produk WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_kategori_produk> findByid(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_kategori_produk WHERE \"id\"=:id", nativeQuery = true)
    List<Par_kategori_produk> findByIdKategoriProduk(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM par_kategori_produk ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_kategori_produk",
            nativeQuery = true)
    Page<Par_kategori_produk> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_kategori_produk WHERE name ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM par_kategori_produk WHERE name ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword",
            nativeQuery = true)
    Page<Par_kategori_produk> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_kategori_produk",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_kategori_produk", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM Par_produk WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") Integer id);
}

