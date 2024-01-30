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
import com.rabbai.serviceparefos.models.Par_qaca_group;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_qaca_groupRepository extends JpaRepository<Par_qaca_group, String> {

    @Query(value = "SELECT * FROM par_qaca_group WHERE \"id\"=:id", nativeQuery = true)
    Par_qaca_group findBydata(@Param("id") String id);
    
    @Query(value = "SELECT * FROM par_qaca_group WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_qaca_group> findByQaca(@Param("id") String id);
    
    @Query(value = "SELECT * FROM par_qaca_group where \"type\"=:type ORDER BY id ASC", nativeQuery = true)
    List<Par_qaca_group> findSemua(@Param("type") Integer type);


    @Query(
            value = "SELECT * FROM par_qaca_group ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_qaca_group",
            nativeQuery = true)
    Page<Par_qaca_group> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_qaca_group WHERE name ~* :keyword" +
                    " OR ket ~* :keyword OR id ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_qaca_group WHERE name ~* :keyword" +
                    " OR ket ~* :keyword OR id ~* :keyword",
            nativeQuery = true)
    Page<Par_qaca_group> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);
    


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_qaca_group",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_qaca_group", nativeQuery = true)
    Integer getCount();
    
    @Modifying
    @Query(value = "DELETE FROM par_qaca_group WHERE \"id\"=:id", nativeQuery = true)
    int deleteBySingleId(@Param("id") String id);
}

