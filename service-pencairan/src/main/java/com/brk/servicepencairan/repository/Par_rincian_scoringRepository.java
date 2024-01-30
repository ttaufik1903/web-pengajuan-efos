package com.brk.servicepencairan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brk.servicepencairan.models.RincianScoring;


@Repository
@EnableJpaRepositories
public interface Par_rincian_scoringRepository extends JpaRepository<RincianScoring, Integer> {

    @Query(value = "SELECT * FROM par_rincian_scoring WHERE \"id_m_scoring\"=:id_m_scoring and \"value\"=:value", nativeQuery = true)
    RincianScoring findByIdAndValue(@Param("id_m_scoring") String id_m_scoring, @Param("value") String value);
 
}

