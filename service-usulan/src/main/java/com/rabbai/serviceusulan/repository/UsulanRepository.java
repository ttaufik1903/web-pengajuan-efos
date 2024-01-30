package com.rabbai.serviceusulan.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceusulan.models.Usulan;

@Repository
@EnableJpaRepositories
public interface UsulanRepository extends JpaRepository<Usulan, String> {

	@Query(value = "SELECT max(\"data_usulan\".id) FROM \"data_usulan\" where \"id_cab\"=:kodecabang and substr(\"data_usulan\".id, 0, 12) =:id",nativeQuery = true)
    String getMaxLoan(@Param("id") String id, @Param("kodecabang") String kodecabang);
	
	@Query(value = "SELECT max(\"data_usulan\".id) FROM \"data_usulan\" where \"id_loan\"=:id_loan",nativeQuery = true)
    String lastLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM data_usulan WHERE \"id\"=:id and \"id_debitur\"=:id_debitur", nativeQuery = true)
	Usulan findByIdAndIdDebitur(@Param("id") String id,@Param("id_debitur") String id_debitur);
	
	@Query(value = "SELECT * FROM data_usulan WHERE \"id\"=:id", nativeQuery = true)
	Usulan findByIdLoan(@Param("id") String id);
	
	//######
	@Query(value = "SELECT * FROM data_usulan WHERE \"id_debitur\"=:id_debitur", nativeQuery = true)
	Usulan findByKtpDebitur(@Param("id_debitur") String id_debitur);
    
    @Query(value = "SELECT * FROM data_usulan WHERE \"id_debitur\"=:id_debitur ORDER BY \"id\" DESC", nativeQuery = true)
    List<Usulan> findByKtp(@Param("id_debitur") String id_debitur);

    @Query(
            value = "SELECT * FROM data_usulan where \"id_cab\"=:kodecabang ORDER BY \"id\" DESC",
            countQuery = "SELECT count(*) FROM data_usulan where \"id_cab\"=:kodecabang",
            nativeQuery = true)
    Page<Usulan> findAllDebiturWithPagination(Pageable pageable, @Param("kodecabang") String kodecabang);

    @Query(
            value = "SELECT * FROM data_usulan INNER JOIN data_debitur_i ON (data_usulan.id_debitur = data_debitur_i.ktp) WHERE data_usulan.id_debitur ~* :keyword" +
                    " OR data_debitur_i.nama ~* :keyword OR data_debitur_i.tmp_lahir ~* :keyword OR data_usulan.id ~* :keyword and data_usulan.id_cab=:kodecabang" +
                    " ORDER BY data_usulan.id DESC",
            countQuery = "SELECT count(*) FROM data_usulan INNER JOIN data_debitur_i ON (data_usulan.id_debitur = data_debitur_i.ktp) WHERE data_usulan.id_debitur ~* :keyword" +
                    " OR data_debitur_i.nama ~* :keyword OR data_debitur_i.tmp_lahir ~* :keyword OR data_usulan.id ~* :keyword) and data_usulan.id_cab=:kodecabang",
            nativeQuery = true)
    Page<Usulan> findKeywordDebiturWithPagination(Pageable pageable, @Param("keyword") String keyword, @Param("kodecabang") String kodecabang);
    
    @Query(value = "SELECT count(*) FROM data_usulan where \"id_cab\"=:kodecabang",nativeQuery = true)
    Integer getCount(@Param("kodecabang") String kodecabang);
	//######
 
}

