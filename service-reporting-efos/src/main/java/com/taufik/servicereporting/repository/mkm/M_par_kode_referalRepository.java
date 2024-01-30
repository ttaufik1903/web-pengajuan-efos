package com.taufik.servicereporting.repository.mkm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.mkm.M_par_kode_referal;

@Repository
@EnableJpaRepositories
public interface M_par_kode_referalRepository extends JpaRepository<M_par_kode_referal, String> {

	@Query("select distinct p from M_par_kode_referal p join fetch p.loans pc where pc.status = :status and (TO_CHAR(pc.approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2)")
    List<M_par_kode_referal> findAllByStatusLoan(String status, String tgl1, String tgl2);
	
}
