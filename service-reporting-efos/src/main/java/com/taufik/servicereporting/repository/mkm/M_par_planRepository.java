package com.taufik.servicereporting.repository.mkm;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.mkm.M_par_plan;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface M_par_planRepository extends JpaRepository<M_par_plan, Integer> {
	

	@Query("select distinct p from M_par_plan p join fetch p.loans pc where pc.status = :status and (TO_CHAR(pc.approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2)")
    List<M_par_plan> findAllByStatusLoan(String status, String tgl1, String tgl2);

}

