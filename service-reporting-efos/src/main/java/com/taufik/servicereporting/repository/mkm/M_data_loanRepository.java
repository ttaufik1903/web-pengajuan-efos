package com.taufik.servicereporting.repository.mkm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.mkm.M_data_loan;

@Repository
@EnableJpaRepositories
public interface M_data_loanRepository extends JpaRepository<M_data_loan, String> {
	@Query("SELECT u FROM M_data_loan u WHERE u.status =:status and (TO_CHAR(u.approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2)")
	List<M_data_loan> findByStatus(String status, String tgl1, String tgl2);
	
	
}
