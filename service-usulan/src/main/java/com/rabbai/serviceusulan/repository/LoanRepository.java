package com.rabbai.serviceusulan.repository;


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

import com.rabbai.serviceusulan.models.Loan;

@Repository
@EnableJpaRepositories
public interface LoanRepository extends JpaRepository<Loan, String> {

	@Query(value = "SELECT max(\"data_loan_i\".id) FROM \"data_loan_i\" where substr(\"data_loan_i\".id, 0, 12) =:id",nativeQuery = true)
    String getMaxLoan(@Param("id") String id);
	
	@Query(value = "SELECT * FROM data_loan_i WHERE \"id\"=:id and \"id_debitur\"=:id_debitur", nativeQuery = true)
	Loan findByIdAndIdDebitur(@Param("id") String id,@Param("id_debitur") String id_debitur);
	

	@Query(value = "SELECT * FROM data_loan_i WHERE \"id\"=:id", nativeQuery = true)
    Loan findByIdLoan(@Param("id") String id);
	
	 
	@Modifying
	@Transactional
	@Query("update Loan u set u.status = :status where u.id = :id")
	void updateStatusUsulanLoan(@Param("status") String status, @Param("id") String id);
	
	//######
	@Query(value = "SELECT * FROM data_loan_i WHERE \"id_debitur\"=:id_debitur", nativeQuery = true)
	Loan findByKtpDebitur(@Param("id_debitur") String id_debitur);
    
    @Query(value = "SELECT * FROM data_loan_i WHERE \"id_debitur\"=:id_debitur ORDER BY \"input_date\" DESC", nativeQuery = true)
    List<Loan> findByKtp(@Param("id_debitur") String id_debitur);

    @Query(
            value = "SELECT * FROM data_loan_i ORDER BY \"input_date\" DESC",
            countQuery = "SELECT count(*) FROM data_loan_i",
            nativeQuery = true)
    Page<Loan> findAllDebiturWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM data_loan_i INNER JOIN data_debitur_i ON (data_loan_i.id_debitur = data_debitur_i.ktp) WHERE data_loan_i.id_debitur ~* :keyword" +
                    " OR data_debitur_i.nama ~* :keyword OR data_debitur_i.npwp ~* :keyword" +
                    " ORDER BY data_loan_i.input_date DESC",
            countQuery = "SELECT count(*) FROM data_loan_i INNER JOIN data_debitur_i ON (data_loan_i.id_debitur = data_debitur_i.ktp) WHERE data_loan_i.id_debitur ~* :keyword" +
                    " OR data_debitur_i.nama ~* :keyword OR data_debitur_i.npwp ~* :keyword",
            nativeQuery = true)
    Page<Loan> findKeywordDebiturWithPagination(Pageable pageable, @Param("keyword") String keyword);
    
    @Query(value = "SELECT count(*) FROM data_loan_i",nativeQuery = true)
    Integer getCount();
	//######
 
}

