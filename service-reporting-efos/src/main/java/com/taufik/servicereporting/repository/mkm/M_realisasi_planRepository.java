package com.taufik.servicereporting.repository.mkm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.mkm.M_realisasi_plan;



@Repository
@EnableJpaRepositories
public interface M_realisasi_planRepository
		extends JpaRepository<M_realisasi_plan, String> {
	@Query(value = "SELECT id, name, count(id) as noa, sum(plafon_disetujui) as realisasi FROM view_realisasi_plan_mkm WHERE (TO_CHAR(approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2) GROUP BY id, name", nativeQuery = true)
	List<M_realisasi_plan> findAllByGroup(String tgl1, String tgl2);

}
