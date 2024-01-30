package com.rabbai.serviceusulan.repository.mkm;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceusulan.models.Data_bi;
import com.rabbai.serviceusulan.models.mkm.M_data_bi;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface M_data_biRepository extends JpaRepository<M_data_bi, String> {

    @Query(value = "SELECT * FROM pelaporan.m_data_bi WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    M_data_bi findByKtpDebitur(@Param("id_loan") String id_loan);
    
    @Query(value = "SELECT * FROM pelaporan.m_data_bi WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    M_data_bi findByIdLoan(@Param("id_loan") String id_loan);
    
    @Query(value = "SELECT * FROM pelaporan.m_data_bi WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    Optional<M_data_bi> findByKtp(@Param("id_loan") String id_loan);

    @Query(
            value = "SELECT * FROM pelaporan.m_data_bi",
            countQuery = "SELECT count(*) FROM pelaporan.m_data_bi",
            nativeQuery = true)
    Page<M_data_bi> findAllDebiturWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM pelaporan.m_data_bi WHERE id_loan ~* :keyword" +
                    " ORDER BY id_loan DESC",
            countQuery = "SELECT count(*) FROM pelaporan.m_data_bi WHERE id_loan ~* :keyword",
            nativeQuery = true)
    Page<M_data_bi> findKeywordDebiturWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id_loan\") as \"id_loan\" FROM pelaporan.m_data_bi",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM pelaporan.m_data_bi",nativeQuery = true)
    Integer getCount();
}

