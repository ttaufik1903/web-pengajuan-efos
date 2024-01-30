package com.taufik.servicereporting.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Par_kel;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_kelRepository extends JpaRepository<Par_kel, Long> {

    @Query(value = "SELECT * FROM par_kel WHERE \"id\"=:id", nativeQuery = true)
    Par_kel findBySingleId(@Param("id") Long id);
    
    @Query(value = "SELECT * FROM par_kel WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_kel> findByid(@Param("id") Long id);
    
    @Query(value = "SELECT * FROM par_kel WHERE \"id_kec\"=:id_kec", nativeQuery = true)
    List<Par_kel> findByIdKel(@Param("id_kec") Integer id_kec);

    @Query(
            value = "SELECT * FROM par_kel ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_kec",
            nativeQuery = true)
    Page<Par_kel> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT par_kel.* FROM par_kel INNER JOIN par_kec ON (par_kel.id_kec = par_kec.id) WHERE par_kel.name ~* :keyword" +
                    " OR par_kel.ket ~* :keyword OR CAST(par_kel.id AS text) ~* :keyword OR CAST(par_kel.id_kec AS text) ~* :keyword" +
                    " OR par_kec.name ~* :keyword ORDER BY par_kel.id ASC",
            countQuery = "SELECT count(par_kel.*) FROM par_kel INNER JOIN par_kec ON (par_kel.id_kec = par_kec.id) WHERE par_kel.name ~* :keyword" +
                    " OR par_kel.ket ~* :keyword OR CAST(par_kel.id AS text) ~* :keyword OR CAST(par_kel.id_kec AS text) ~* :keyword OR par_kec.name ~* :keyword",
            nativeQuery = true)
    Page<Par_kel> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_kel",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_kel", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM par_kel WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") Long id);
}

