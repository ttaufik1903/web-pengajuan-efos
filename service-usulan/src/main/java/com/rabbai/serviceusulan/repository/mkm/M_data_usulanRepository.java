package com.rabbai.serviceusulan.repository.mkm;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rabbai.serviceusulan.models.Usulan;
import com.rabbai.serviceusulan.models.mkm.M_data_usulan;


@Repository
@EnableJpaRepositories
public interface M_data_usulanRepository extends JpaRepository<M_data_usulan, String> {

	@Query(value = "SELECT max(\"m_data_usulan\".id) FROM \"m_data_usulan\" where \"id_cab\"=:kodecabang and substr(\"m_data_usulan\".id, 0, 12) =:id",nativeQuery = true)
    String getMaxLoan(@Param("id") String id, @Param("kodecabang") String kodecabang);
	
	@Query(value = "SELECT max(\"m_data_usulan\".id) FROM \"m_data_usulan\" where \"id_loan\"=:id_loan",nativeQuery = true)
    String lastLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM m_data_usulan WHERE \"id\"=:id and \"id_debitur\"=:id_debitur", nativeQuery = true)
	M_data_usulan findByIdAndIdDebitur(@Param("id") String id,@Param("id_debitur") String id_debitur);
	
	@Query(value = "SELECT * FROM m_data_usulan WHERE \"id\"=:id", nativeQuery = true)
	M_data_usulan findByIdUsulan(@Param("id") String id);
	
	@Query(value = "SELECT * FROM m_data_usulan WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	M_data_usulan findByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM m_data_usulan WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	List<M_data_usulan> findAllByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM m_data_usulan WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	Optional<M_data_usulan> findByIdLoans(@Param("id_loan") String id_loan);
	
	//######
	@Query(value = "SELECT * FROM m_data_usulan WHERE \"id_debitur\"=:id_debitur", nativeQuery = true)
	M_data_usulan findByKtpDebitur(@Param("id_debitur") String id_debitur);
    
    @Query(value = "SELECT * FROM m_data_usulan WHERE \"id_debitur\"=:id_debitur ORDER BY \"id\" DESC", nativeQuery = true)
    List<M_data_usulan> findByKtp(@Param("id_debitur") String id_debitur);

    @Query(
            value = "SELECT * FROM m_data_usulan where \"id_cab\"=:kodecabang ORDER BY \"id\" DESC",
            countQuery = "SELECT count(*) FROM m_data_usulan where \"id_cab\"=:kodecabang",
            nativeQuery = true)
    Page<M_data_usulan> findAllDebiturWithPagination(Pageable pageable, @Param("kodecabang") String kodecabang);

    @Query(
            value = "SELECT * FROM m_data_usulan INNER JOIN m_data_debitur ON (m_data_usulan.id_debitur = m_data_debitur.id) WHERE m_data_usulan.id_debitur ~* :keyword" +
                    " OR m_data_debitur.nama ~* :keyword OR m_data_debitur.tmp_lahir ~* :keyword OR m_data_usulan.id ~* :keyword and m_data_usulan.id_cab=:kodecabang" +
                    " ORDER BY m_data_usulan.id DESC",
            countQuery = "SELECT count(*) FROM m_data_usulan INNER JOIN m_data_debitur ON (m_data_usulan.id_debitur = m_data_debitur.id) WHERE m_data_usulan.id_debitur ~* :keyword" +
                    " OR m_data_debitur.nama ~* :keyword OR m_data_debitur.tmp_lahir ~* :keyword OR m_data_usulan.id ~* :keyword and m_data_usulan.id_cab=:kodecabang",
            nativeQuery = true)
    Page<M_data_usulan> findKeywordDebiturWithPagination(Pageable pageable, @Param("keyword") String keyword, @Param("kodecabang") String kodecabang);
    
    @Query(value = "SELECT count(*) FROM m_data_usulan where \"id_cab\"=:kodecabang",nativeQuery = true)
    Integer getCount(@Param("kodecabang") String kodecabang);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE m_data_usulan SET \"status\"=:status, \"review_by\"=:review_by, "
    		+ " \"review_nama\"=:review_nama, \"review_date\"=:review_date, \"review_desc\"=:review_desc "
    		+ "where \"id_loan\"=:id_loan",nativeQuery = true)
    Integer updateUsulan(@Param("status") String status, @Param("review_by") String review_by, @Param("review_nama") String review_nama,
    		@Param("review_date") LocalDateTime review_date, @Param("review_desc") String review_desc, @Param("id_loan") String id_loan);
    
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE m_data_usulan SET \"status\"=:status, \"review2_by\"=:review2_by, "
    		+ " \"review2_nama\"=:review2_nama, \"review2_date\"=:review2_date, \"review2_desc\"=:review2_desc "
    		+ "where \"id_loan\"=:id_loan",nativeQuery = true)
    Integer updateUsulanPimpinan(@Param("status") String status, @Param("review2_by") String review_by, @Param("review2_nama") String review_nama,
    		@Param("review2_date") LocalDateTime review_date, @Param("review2_desc") String review_desc, @Param("id_loan") String id_loan);
 
}

