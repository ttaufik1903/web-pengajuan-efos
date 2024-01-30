package com.rabbai.serviceprospek.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceprospek.models.Debitur;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface DebiturRepository extends JpaRepository<Debitur, Integer> {

    @Query(value = "SELECT * FROM data_debitur_i WHERE \"ktp\"=:ktp", nativeQuery = true)
    Debitur findByKtpDebitur(@Param("ktp") String ktp);
    
    @Query(value = "SELECT * FROM data_debitur_i WHERE \"ktp\"=:ktp", nativeQuery = true)
    Debitur findByKtpDeb(@Param("ktp") String ktp);
    
    @Query(value = "SELECT * FROM data_debitur_i WHERE \"ktp\"=:ktp", nativeQuery = true)
    Optional<Debitur> findByKtp(@Param("ktp") String ktp);

    @Query(
            value = "SELECT * FROM data_debitur_i ORDER BY \"datepost_open\" DESC",
            countQuery = "SELECT count(*) FROM data_debitur_i",
            nativeQuery = true)
    Page<Debitur> findAllDebiturWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM data_debitur_i WHERE ktp ~* :keyword" +
                    " OR nama ~* :keyword OR npwp ~* :keyword" +
                    " ORDER BY ktp DESC",
            countQuery = "SELECT count(*) FROM data_debitur_i WHERE ktp ~* :keyword" +
            		" OR nama ~* :keyword OR npwp ~* :keyword",
            nativeQuery = true)
    Page<Debitur> findKeywordDebiturWithPagination(Pageable pageable, @Param("keyword") String keyword);

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
    @Query(value = "SELECT MAX(\"ktp\") as \"ktp\" FROM data_debitur_i",nativeQuery = true)
    Integer getMaxId();
    
    
    
    @Query(value = "SELECT count(*) FROM data_debitur_i",nativeQuery = true)
    Integer getCount();
}

