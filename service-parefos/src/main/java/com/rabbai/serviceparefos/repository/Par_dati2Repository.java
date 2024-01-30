package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_dati2;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_dati2Repository extends JpaRepository<Par_dati2, Integer> {

    @Query(value = "SELECT * FROM par_dati2 WHERE \"id\"=:id", nativeQuery = true)
    Par_dati2 findBySingleId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_dati2 WHERE \"id_provinsi\"=:id_provinsi", nativeQuery = true)
    List<Par_dati2> findByIdProvinsi(@Param("id_provinsi") Integer id_provinsi);
    
    @Query(value = "SELECT * FROM par_dati2 WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_dati2> findByid(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM par_dati2 ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_dati2",
            nativeQuery = true)
    Page<Par_dati2> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT par_dati2.* FROM par_dati2 INNER JOIN par_provinsi ON (par_dati2.id_provinsi = par_provinsi.id) WHERE par_dati2.name ~* :keyword" +
                    " OR par_dati2.ket ~* :keyword OR CAST(par_dati2.id AS text) ~* :keyword OR CAST(par_dati2.id_bi AS text) ~* :keyword" +
                    " OR CAST(par_dati2.id_provinsi AS text) ~* :keyword OR par_provinsi.name ~* :keyword ORDER BY par_dati2.id ASC",
            countQuery = "SELECT count(par_dati2.*) FROM par_dati2 INNER JOIN par_provinsi ON (par_dati2.id_provinsi = par_provinsi.id) WHERE par_dati2.name ~* :keyword" +
                    " OR par_dati2.ket ~* :keyword OR CAST(par_dati2.id AS text) ~* :keyword OR CAST(par_dati2.id_bi AS text) ~* :keyword" +
                    " OR CAST(par_dati2.id_provinsi AS text) ~* :keyword OR par_provinsi.name ~* :keyword",
            nativeQuery = true)
    Page<Par_dati2> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_dati2",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_dati2", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM par_dati2 WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") Integer id);
}

