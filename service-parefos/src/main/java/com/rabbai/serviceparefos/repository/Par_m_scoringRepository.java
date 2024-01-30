package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_m_scoring;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_m_scoringRepository extends JpaRepository<Par_m_scoring, String> {

    @Query(value = "SELECT * FROM par_m_scoring WHERE \"id\"=:id", nativeQuery = true)
    Par_m_scoring findBySingleId(@Param("id") String id);
    
    @Query(value = "SELECT * FROM par_m_scoring WHERE \"status\"=:status and \"type\"=:type", nativeQuery = true)
    List<Par_m_scoring> findByStatusAndType(@Param("status") String status, @Param("type") Integer type);
    
    @Query(value = "SELECT * FROM par_m_scoring WHERE \"status\"=:status and \"id\"=:id", nativeQuery = true)
    List<Par_m_scoring> findByStatusAndTemplate(@Param("status") String status, @Param("id") String id);
    
    @Query(value = "SELECT a.* FROM par_m_scoring a INNER JOIN par_rincian_scoring b ON a.id = b.id_m_scoring"
    		+" WHERE a.\"status\"=:status and a.\"type\"=:type and b.\"id_m_scoring\"=:id_m_scoring", nativeQuery = true)
    List<Par_m_scoring> findByStatusAndTypeAndIdRincian(@Param("status") String status, @Param("type") Integer type, @Param("id_m_scoring") String id_m_scoring);
    
    
    @Query(value = "SELECT * FROM par_m_scoring WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_m_scoring> findByid(@Param("id") String id);
    
    @Query(
            value = "SELECT * FROM par_m_scoring ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_m_scoring",
            nativeQuery = true)
    Page<Par_m_scoring> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_m_scoring WHERE name ~* :keyword" +
                    " OR id ~* :keyword OR status ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM par_m_scoring WHERE name ~* :keyword" +
                    " OR id ~* :keyword OR status ~* :keyword",
            nativeQuery = true)
    Page<Par_m_scoring> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_m_scoring",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_m_scoring", nativeQuery = true)
    Integer getCount();
}

