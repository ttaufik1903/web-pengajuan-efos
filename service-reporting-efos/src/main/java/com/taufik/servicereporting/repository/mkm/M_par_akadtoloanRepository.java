package com.taufik.servicereporting.repository.mkm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.mkm.M_par_akad_toloan;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface M_par_akadtoloanRepository extends JpaRepository<M_par_akad_toloan, Integer> {

	@Query(" SELECT DISTINCT d FROM M_par_akad_toloan d JOIN fetch d.plans e JOIN fetch e.loans l where l.status = :status and (TO_CHAR(l.approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2)")
	List<M_par_akad_toloan> findAllByStatusLoan(String status, String tgl1, String tgl2);
	
	@Query(value = "SELECT * from par_akad p inner join par_plan c on c.id_akad=p.id "
			+ "inner join m_data_loan l on l.id_plan=c.id where l.status = :status "
			+ "and TO_CHAR(l.cair_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2", nativeQuery = true)
//			+ "and l.cair_date BETWEEN :tgl1 and :tgl2", nativeQuery = true)
	List<M_par_akad_toloan> findReportingByAkad(String status, String tgl1, String tgl2);
}
