package com.rabbai.serviceusulan.repository.mkm;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceusulan.models.mkm.M_data_loan;


@Repository
@EnableJpaRepositories
public interface M_data_loanRepository extends JpaRepository<M_data_loan, String> {

	@Query(value = "SELECT max(\"m_data_loan\".id) FROM \"m_data_loan\" where id_cab=:kodecabang and  substr(\"m_data_loan\".id, 0, 12) =:id", nativeQuery = true)
	String getMaxLoan(@Param("id") String id, @Param("kodecabang") String kodecabang);

	@Query(value = "SELECT * FROM m_data_loan WHERE \"id\"=:id and id_cab=:kodecabang and \"id_debitur\"=:id_debitur", nativeQuery = true)
	M_data_loan findByIdAndIdDebitur(@Param("id") String id, @Param("id_debitur") String id_debitur, @Param("kodecabang") String kodecabang);

	@Query(value = "SELECT * FROM m_data_loan WHERE \"id\"=:id", nativeQuery = true)
	M_data_loan findByIdLoan(@Param("id") String id);

	// ######
	@Query(value = "SELECT * FROM m_data_loan WHERE \"id_debitur\"=:id_debitur", nativeQuery = true)
	M_data_loan findByKtpDebitur(@Param("id_debitur") String id_debitur);

	@Query(value = "SELECT * FROM m_data_loan WHERE \"id_debitur\"=:id_debitur  ORDER BY \"id\" DESC", nativeQuery = true)
	List<M_data_loan> findByKtp(@Param("id_debitur") String id_debitur);

	@Query(value = "SELECT * FROM m_data_loan where id_cab=:kodecabang ORDER BY \"id\" DESC", countQuery = "SELECT count(*) FROM m_data_loan where id_cab=:kodecabang", nativeQuery = true)
	Page<M_data_loan> findAllDebiturWithPagination(Pageable pageable, @Param("kodecabang") String kodecabang);

	@Query(value = "SELECT * FROM m_data_loan INNER JOIN m_data_debitur ON (m_data_loan.id_debitur = m_data_debitur.id) WHERE m_data_loan.id_debitur ~* :keyword"
			+ " OR m_data_debitur.nama ~* :keyword OR m_data_loan.id ~* :keyword OR m_data_debitur.tmp_lahir ~* :keyword and m_data_loan.id_cab=:kodecabang"
			+ " ORDER BY m_data_loan.id DESC", 
			countQuery = "SELECT count(*) FROM m_data_loan INNER JOIN m_data_debitur ON (m_data_loan.id_debitur = m_data_debitur.id) WHERE m_data_loan.id_debitur ~* :keyword"
					+ " OR m_data_debitur.nama ~* :keyword OR m_data_loan.id ~* :keyword OR m_data_debitur.tmp_lahir ~* :keyword", nativeQuery = true)
	Page<M_data_loan> findKeywordDebiturWithPagination(Pageable pageable, @Param("keyword") String keyword,
			@Param("kodecabang") String kodecabang);

	@Query(value = "SELECT count(*) FROM m_data_loan where id_cab=:kodecabang ", nativeQuery = true)
	Integer getCount(@Param("kodecabang") String kodecabang);

	@Modifying
	@Transactional
	@Query("update M_data_loan u set u.status = :status where u.id = :id")
	void updateStatusUsulanLoan(@Param("status") String status, @Param("id") String id);
	
	// ######

	@Query(value = "SELECT * FROM m_data_loan where status=:status and id_cab=:kodecabang  ORDER BY \"id\" DESC", countQuery = "SELECT count(*) FROM m_data_loan where status=:status", nativeQuery = true)
	Page<M_data_loan> findAllDebiturWithPaginationSts(Pageable pageable, @Param("status") String status,
			@Param("kodecabang") String kodecabang);

	@Query(value = "SELECT * FROM m_data_loan INNER JOIN m_data_debitur ON (m_data_loan.id_debitur = m_data_debitur.id) WHERE"
			+ " OR m_data_debitur.nama ~* :keyword OR m_data_loan.id ~* :keyword OR m_data_debitur.tmp_lahir ~* :keyword OR m_data_loan.id_debitur ~* :keyword and m_data_loan.status=:status and m_data_loan.id_cab=:kodecabang"
			+ " ORDER BY m_data_loan.id DESC", 
			countQuery = "SELECT count(*) FROM m_data_loan INNER JOIN m_data_debitur ON (m_data_loan.id_debitur = m_data_debitur.id) WHERE"
					+ " OR m_data_debitur.nama ~* :keyword OR m_data_loan.id ~* :keyword OR m_data_debitur.tmp_lahir ~* :keyword OR m_data_loan.id_debitur ~* :keyword and m_data_loan.status=:status and m_data_loan.id_cab=:kodecabang", nativeQuery = true)
	Page<M_data_loan> findKeywordDebiturWithPaginationSts(Pageable pageable, @Param("keyword") String keyword,
			@Param("status") String status, @Param("kodecabang") String kodecabang);

	@Query(value = "SELECT count(*) FROM m_data_loan where status=:status and id_cab=:kodecabang ", nativeQuery = true)
	Integer getCountSts(@Param("status") String status, @Param("kodecabang") String kodecabang);

}
