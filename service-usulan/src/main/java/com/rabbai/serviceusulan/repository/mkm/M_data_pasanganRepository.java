package com.rabbai.serviceusulan.repository.mkm;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceusulan.models.mkm.M_data_pasangan;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface M_data_pasanganRepository extends JpaRepository<M_data_pasangan, Integer> {

    @Query(value = "SELECT * FROM m_data_pasangan WHERE \"id\"=:id", nativeQuery = true)
    M_data_pasangan findByKtpPasangan(@Param("id") String id);
    
    @Query(value = "SELECT * FROM m_data_pasangan WHERE \"id_debitur\"=:id_debitur and \"id\"=:ktp", nativeQuery = true)
    M_data_pasangan findByIdDebiturandKtpPasangan(@Param("id_debitur") String id_debitur, @Param("ktp") String ktp);
    
    @Query(value = "SELECT * FROM m_data_pasangan WHERE \"id\"=:ktp", nativeQuery = true)
    Optional<M_data_pasangan> findByKtp(@Param("ktp") String ktp);
    
    @Query(value = "SELECT * FROM m_data_pasangan WHERE \"id_debitur\"=:id_debitur", nativeQuery = true)
    List<M_data_pasangan> findByIdDebitur(@Param("id_debitur") String id_debitur);

    @Query(
            value = "SELECT * FROM m_data_pasangan ORDER BY \"datepost_open\" DESC",
            countQuery = "SELECT count(*) FROM m_data_pasangan",
            nativeQuery = true)
    Page<M_data_pasangan> findAllDebiturWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM m_data_pasangan WHERE id ~* :keyword" +
                    " OR nama ~* :keyword OR npwp ~* :keyword" +
                    " ORDER BY ktp DESC",
            countQuery = "SELECT count(*) FROM m_data_pasangan WHERE id ~* :keyword" +
            		" OR nama ~* :keyword OR npwp ~* :keyword",
            nativeQuery = true)
    Page<M_data_pasangan> findKeywordDebiturWithPagination(Pageable pageable, @Param("keyword") String keyword);

    @Query(value = "SELECT MAX(\"ktp\") as \"id\" FROM m_data_pasangan",nativeQuery = true)
    Integer getMaxId();

    @Query(value = "SELECT count(*) FROM m_data_pasangan",nativeQuery = true)
    Integer getCount();
}

