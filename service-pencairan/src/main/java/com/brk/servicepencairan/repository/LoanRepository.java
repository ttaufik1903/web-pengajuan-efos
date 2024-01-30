package com.brk.servicepencairan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brk.servicepencairan.models.Loan;

@Repository
@EnableJpaRepositories
public interface LoanRepository extends JpaRepository<Loan, String> {

	@Query(value = "SELECT max(substr(\"data_loan_i\".nomor_akad, 0, 8)) FROM \"data_loan_i\" where \"id_cab\"=:id_cab and substr(\"data_loan_i\".nomor_akad, 14, 4)=:tahun", nativeQuery = true)
	String getMaxNoAkad(@Param("id_cab") String id_cab, @Param("tahun") String tahun);

	@Query(value = "SELECT * FROM data_loan_i WHERE \"id\"=:id and \"id_debitur\"=:id_debitur", nativeQuery = true)
	Loan findByIdAndIdDebitur(@Param("id") String id, @Param("id_debitur") String id_debitur);

	@Query(value = "SELECT * FROM data_loan_i WHERE \"id\"=:id", nativeQuery = true)
	Loan findByIdLoan(@Param("id") String id);

	// ######
	@Query(value = "SELECT * FROM data_loan_i WHERE \"id_debitur\"=:id_debitur", nativeQuery = true)
	Loan findByKtpDebitur(@Param("id_debitur") String id_debitur);

	@Query(value = "SELECT * FROM data_loan_i WHERE \"id_debitur\"=:id_debitur ORDER BY \"input_date\" DESC", nativeQuery = true)
	List<Loan> findByKtp(@Param("id_debitur") String id_debitur);

	@Query(value = "SELECT * FROM data_loan_i where CAST(\"status\" as INTEGER) >= 15 and \"id_cab\"=:kodecabang ORDER BY \"input_date\" DESC", countQuery = "SELECT count(*) FROM data_loan_i where CAST(\"status\" as INTEGER) >= 15 and id_cab=:kodecabang", nativeQuery = true)
	Page<Loan> findAllDebiturWithPagination(Pageable pageable, @Param("kodecabang") String kodecabang);

	@Query(value = "SELECT * FROM data_loan_i INNER JOIN data_debitur_i ON (data_loan_i.id_debitur = data_debitur_i.ktp) WHERE data_loan_i.id_debitur ~* :keyword"
			+ " OR data_debitur_i.nama ~* :keyword OR data_loan_i.id ~* :keyword OR data_debitur_i.tmp_lahir ~* :keyword"
			+ "  and CAST(data_loan_i.status as INTEGER) >= 15 and id_cab=:kodecabang  ORDER BY data_loan_i.input_date DESC", 
			countQuery = "SELECT count(*) FROM data_loan_i INNER JOIN data_debitur_i ON (data_loan_i.id_debitur = data_debitur_i.ktp) WHERE data_loan_i.id_debitur ~* :keyword"
					+ " OR data_debitur_i.nama ~* :keyword OR data_loan_i.id ~* :keyword OR data_debitur_i.tmp_lahir ~* :keyword"
					+ " and CAST(data_loan_i.status as INTEGER) >= 15 and id_cab=:kodecabang", nativeQuery = true)
	Page<Loan> findKeywordDebiturWithPagination(Pageable pageable, @Param("keyword") String keyword,
			@Param("kodecabang") String kodecabang);

	@Query(value = "SELECT count(*) FROM data_loan_i where id_cab=:kodecabang", nativeQuery = true)
	Integer getCount(@Param("kodecabang") String kodecabang);

}
