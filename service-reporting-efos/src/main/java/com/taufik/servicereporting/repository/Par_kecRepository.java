package com.taufik.servicereporting.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Par_kec;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_kecRepository extends JpaRepository<Par_kec, Integer> {

    @Query(value = "SELECT * FROM par_kec WHERE \"id\"=:id", nativeQuery = true)
    Par_kec findBySingleId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_kec WHERE \"id_dati2\"=:id_dati2", nativeQuery = true)
    List<Par_kec> findByIdKab(@Param("id_dati2") Integer id_dati2);
    
    @Query(value = "SELECT * FROM par_kec WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_kec> findByid(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM par_kec ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_kec",
            nativeQuery = true)
    Page<Par_kec> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT par_kec.* FROM par_kec INNER JOIN par_dati2 ON (par_kec.id_dati2 = par_dati2.id) WHERE par_kec.name ~* :keyword" +
                    " OR par_kec.ket ~* :keyword OR CAST(par_kec.id AS text) ~* :keyword OR CAST(par_kec.id_dati2 AS text) ~* :keyword" +
                    " OR CAST(par_dati2.name AS text) ~* :keyword ORDER BY par_kec.id ASC",
            countQuery = "SELECT count(par_kec.*) FROM par_kec INNER JOIN par_dati2 ON (par_kec.id_dati2 = par_dati2.id) WHERE par_kec.name ~* :keyword" +
                    " OR par_kec.ket ~* :keyword OR CAST(par_kec.id AS text) ~* :keyword OR CAST(par_kec.id_dati2 AS text) ~* :keyword" +
                    " OR CAST(par_dati2.name AS text) ~* :keyword",
            nativeQuery = true)
    Page<Par_kec> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_kec",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_kec", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM par_kec WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") Integer id);
}

