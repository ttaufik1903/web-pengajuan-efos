package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_bidang_usaha;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_bidang_usahaRepository extends JpaRepository<Par_bidang_usaha, String> {

    @Query(value = "SELECT * FROM par_bidang_usaha WHERE \"id\"=:id", nativeQuery = true)
    Par_bidang_usaha findBySingleId(@Param("id") String id);
    
    @Query(value = "SELECT * FROM par_bidang_usaha WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_bidang_usaha> findByid(@Param("id") String id);

    @Query(
            value = "SELECT * FROM par_bidang_usaha ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_bidang_usaha",
            nativeQuery = true)
    Page<Par_bidang_usaha> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_bidang_usaha WHERE name ~* :keyword" +
                    " OR ket ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_bidang_usaha WHERE name ~* :keyword" +
                    " ket ~* :keyword",
            nativeQuery = true)
    Page<Par_bidang_usaha> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_bidang_usaha",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_bidang_usaha", nativeQuery = true)
    Integer getCount();
}

