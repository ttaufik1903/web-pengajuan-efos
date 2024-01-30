package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_qaca_detail;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_qaca_detailRepository extends JpaRepository<Par_qaca_detail, Integer> {

    @Query(value = "SELECT * FROM par_qaca_detail WHERE \"id\"=:id", nativeQuery = true)
    Par_qaca_detail findBydata(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_qaca_detail WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_qaca_detail> findByQaca(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_qaca_detail where \"type\"=:type ORDER BY dukument_id ASC", nativeQuery = true)
    List<Par_qaca_detail> findSemua(@Param("type") Integer type);


    @Query(
            value = "SELECT * FROM par_qaca_detail ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_qaca_detail",
            nativeQuery = true)
    Page<Par_qaca_detail> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_qaca_detail WHERE name ~* :keyword" +
                    " OR dukument_id ~* :keyword OR id ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_qaca_detail WHERE name ~* :keyword" +
                    " OR dukument_id ~* :keyword OR id ~* :keyword",
            nativeQuery = true)
    Page<Par_qaca_detail> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);
    


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_qaca_detail",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_qaca_detail", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM par_qaca_detail WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") String id);
}

