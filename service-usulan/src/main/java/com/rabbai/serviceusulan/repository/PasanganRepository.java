package com.rabbai.serviceusulan.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceusulan.models.Pasangan;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface PasanganRepository extends JpaRepository<Pasangan, Integer> {

    @Query(value = "SELECT * FROM data_pasangan WHERE \"ktp\"=:ktp", nativeQuery = true)
    Pasangan findByKtpPasangan(@Param("ktp") String ktp);
    
    @Query(value = "SELECT * FROM data_pasangan WHERE \"id_debitur\"=:id_debitur and \"ktp\"=:ktp", nativeQuery = true)
    Pasangan findByIdDebiturandKtpPasangan(@Param("id_debitur") String id_debitur, @Param("ktp") String ktp);
    
    @Query(value = "SELECT * FROM data_pasangan WHERE \"ktp\"=:ktp", nativeQuery = true)
    Optional<Pasangan> findByKtp(@Param("ktp") String ktp);

    @Query(
            value = "SELECT * FROM data_pasangan ORDER BY \"datepost_open\" DESC",
            countQuery = "SELECT count(*) FROM data_pasangan",
            nativeQuery = true)
    Page<Pasangan> findAllDebiturWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM data_pasangan WHERE ktp ~* :keyword" +
                    " OR nama ~* :keyword OR npwp ~* :keyword" +
                    " ORDER BY ktp DESC",
            countQuery = "SELECT count(*) FROM data_pasangan WHERE ktp ~* :keyword" +
            		" OR nama ~* :keyword OR npwp ~* :keyword",
            nativeQuery = true)
    Page<Pasangan> findKeywordDebiturWithPagination(Pageable pageable, @Param("keyword") String keyword);

    @Query(value = "SELECT MAX(\"ktp\") as \"ktp\" FROM data_pasangan",nativeQuery = true)
    Integer getMaxId();

    @Query(value = "SELECT count(*) FROM data_pasangan",nativeQuery = true)
    Integer getCount();
}

