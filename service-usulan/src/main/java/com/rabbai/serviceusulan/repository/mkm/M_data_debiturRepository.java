package com.rabbai.serviceusulan.repository.mkm;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceusulan.models.mkm.M_data_debitur;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface M_data_debiturRepository extends JpaRepository<M_data_debitur, Integer> {

    @Query(value = "SELECT * FROM m_data_debitur WHERE \"id\"=:id", nativeQuery = true)
    M_data_debitur findByKtpDebitur(@Param("id") String id);
    
    @Query(value = "SELECT * FROM m_data_debitur WHERE \"id\"=:id", nativeQuery = true)
    M_data_debitur findByKtpDeb(@Param("id") String id);
    
    @Query(value = "SELECT * FROM m_data_debitur WHERE \"id\"=:id", nativeQuery = true)
    Optional<M_data_debitur> findByKtp(@Param("id") String id);

    @Query(
            value = "SELECT * FROM m_data_debitur ORDER BY \"datepost_open\" DESC",
            countQuery = "SELECT count(*) FROM m_data_debitur",
            nativeQuery = true)
    Page<M_data_debitur> findAllDebiturWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM m_data_debitur WHERE id ~* :keyword" +
                    " OR nama ~* :keyword OR npwp ~* :keyword" +
                    " ORDER BY id DESC",
            countQuery = "SELECT count(*) FROM m_data_debitur WHERE id ~* :keyword" +
            		" OR nama ~* :keyword OR npwp ~* :keyword",
            nativeQuery = true)
    Page<M_data_debitur> findKeywordDebiturWithPagination(Pageable pageable, @Param("keyword") String keyword);

//    @Query(value = "SELECT * FROM par_instansi WHERE instansi_jenis =:instansi_jenis AND " +
//            "(LOWER(instansi_nama) LIKE :keyword" +
//            " OR LOWER(instansi_alamat) LIKE :keyword" +
//            " OR LOWER(instansi_kode) LIKE :keyword)" +
//            " ORDER BY instansi_id DESC",
//            countQuery = "SELECT count(*) FROM par_instansi WHERE instansi_jenis =:instansi_jenis AND" +
//                    " (LOWER(instansi_nama) LIKE :keyword" +
//                    " OR LOWER(instansi_alamat) LIKE :keyword" +
//                    " OR LOWER(instansi_kode) LIKE :keyword)",
//            nativeQuery = true)
//    Page<Instansis> findByJenisKeyword(Pageable pageable, @Param("keyword") String keyword, @Param("instansi_jenis") String instansi_jenis);
//
//    @Query(value = "SELECT * FROM par_instansi WHERE instansi_jenis =:instansi_jenis",
//            countQuery = "SELECT count(*) FROM par_instansi WHERE instansi_jenis =:instansi_jenis",
//            nativeQuery = true)
//    Page<Instansis> findByJenisVa(Pageable pageable, @Param("instansi_jenis") String instansi_jenis);
//
    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM m_data_debitur",nativeQuery = true)
    Integer getMaxId();
    
    
    
    @Query(value = "SELECT count(*) FROM m_data_debitur",nativeQuery = true)
    Integer getCount();
}

