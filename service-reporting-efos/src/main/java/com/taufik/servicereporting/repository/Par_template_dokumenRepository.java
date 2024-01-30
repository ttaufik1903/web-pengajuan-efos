package com.taufik.servicereporting.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Par_template_dokumen;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_template_dokumenRepository extends JpaRepository<Par_template_dokumen, Integer> {

    @Query(value = "SELECT * FROM par_template_dokumen WHERE \"id\"=:id", nativeQuery = true)
    Par_template_dokumen findBySingleId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_template_dokumen WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_template_dokumen> findByid(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_template_dokumen WHERE \"id\"=:id", nativeQuery = true)
    List<Par_template_dokumen> findByIdTemplate(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM par_template_dokumen ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_akad",
            nativeQuery = true)
    Page<Par_template_dokumen> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_template_dokumen WHERE name ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM par_template_dokumen WHERE name ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword",
            nativeQuery = true)
    Page<Par_template_dokumen> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_template_dokumen",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_template_dokumen", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM par_template_dokumen WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") Integer id);
}

