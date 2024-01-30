package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_rincian_scoring;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_rincian_scoringRepository extends JpaRepository<Par_rincian_scoring, Integer> {

    @Query(value = "SELECT * FROM par_rincian_scoring WHERE \"id\"=:id", nativeQuery = true)
    Par_rincian_scoring findBySingleId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_rincian_scoring WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_rincian_scoring> findByid(@Param("id") Integer id);

    @Query(value = "SELECT * FROM par_rincian_scoring WHERE \"id_m_scoring\"=:id_m_scoring", nativeQuery = true)
    List<Par_rincian_scoring> findByIdMScoring(@Param("id_m_scoring") String id_m_scoring);
    
    @Query(value = "SELECT * FROM par_rincian_scoring WHERE \"id_m_scoring\"=:id_m_scoring and \"type\"=:type", nativeQuery = true)
    List<Par_rincian_scoring> findByIdMScoringAndType(@Param("id_m_scoring") String id_m_scoring, @Param("type") Integer type);
    
    @Query(
            value = "SELECT * FROM par_rincian_scoring ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_rincian_scoring",
            nativeQuery = true)
    Page<Par_rincian_scoring> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_rincian_scoring WHERE rincian ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword OR id_m_scoring ~* :keyword OR skor ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT * FROM par_rincian_scoring WHERE rincian ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword OR id_m_scoring ~* :keyword OR skor ~* :keyword",
            nativeQuery = true)
    Page<Par_rincian_scoring> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_rincian_scoring",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_rincian_scoring", nativeQuery = true)
    Integer getCount();
}

