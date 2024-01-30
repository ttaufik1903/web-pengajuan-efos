package com.brk.servicepencairan.repository.mkm;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brk.servicepencairan.models.mkm.M_data_usulan;


@Repository
@EnableJpaRepositories
public interface M_data_usulanRepository extends JpaRepository<M_data_usulan, String> {

	@Query(value = "SELECT max(\"m_data_usulan\".id) FROM \"m_data_usulan\" where substr(\"m_data_usulan\".id, 0, 12) =:id",nativeQuery = true)
    String getMaxLoan(@Param("id") String id);
	
	@Query(value = "SELECT * FROM m_data_usulan WHERE \"id\"=:id and \"id_debitur\"=:id_debitur", nativeQuery = true)
	M_data_usulan findByIdAndIdDebitur(@Param("id") String id,@Param("id_debitur") String id_debitur);
	
	@Query(value = "SELECT * FROM m_data_usulan WHERE \"id\"=:id", nativeQuery = true)
	M_data_usulan findByIdUsulan(@Param("id") String id);
	
	@Query(value = "SELECT * FROM data_usulan WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	M_data_usulan findByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM m_data_usulan WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	Optional<M_data_usulan> findByIdLoans(@Param("id_loan") String id_loan);
	
	//######
	@Query(value = "SELECT * FROM m_data_usulan WHERE \"id_debitur\"=:id_debitur", nativeQuery = true)
	M_data_usulan findByKtpDebitur(@Param("id_debitur") String id_debitur);
    
    @Query(value = "SELECT * FROM m_data_usulan WHERE \"id_debitur\"=:id_debitur ORDER BY \"id\" DESC", nativeQuery = true)
    List<M_data_usulan> findByKtp(@Param("id_debitur") String id_debitur);

    @Query(
            value = "SELECT * FROM m_data_usulan ORDER BY \"id\" DESC",
            countQuery = "SELECT count(*) FROM m_data_usulan",
            nativeQuery = true)
    Page<M_data_usulan> findAllDebiturWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM m_data_usulan INNER JOIN m_data_debitur ON (m_data_usulan.id_debitur = m_data_debitur.id) WHERE m_data_usulan.id_debitur ~* :keyword" +
                    " OR m_data_debitur.nama ~* :keyword OR m_data_usulan.id ~* :keyword" +
                    " ORDER BY m_data_usulan.id DESC",
            countQuery = "SELECT count(*) FROM m_data_usulan INNER JOIN m_data_debitur ON (m_data_usulan.id_debitur = m_data_debitur.id) WHERE m_data_usulan.id_debitur ~* :keyword" +
                    " OR data_debitur_i.nama ~* :keyword OR m_data_usulan.id ~* :keyword",
            nativeQuery = true)
    Page<M_data_usulan> findKeywordDebiturWithPagination(Pageable pageable, @Param("keyword") String keyword);
    
    @Query(value = "SELECT count(*) FROM m_data_usulan",nativeQuery = true)
    Integer getCount();
	//######
 
}

