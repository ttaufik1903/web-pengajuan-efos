package com.taufik.servicereporting.repository.mkm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.mkm.M_realisasi_ao;

@Repository
@EnableJpaRepositories
public interface M_realisasi_aoRepository
		extends JpaRepository<M_realisasi_ao, String> {
	@Query(value = "SELECT id, name, id_cabang, target_konsumer, sum(realisasi) as realisasi FROM view_realisasi_ao_mkm WHERE (TO_CHAR(approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2) GROUP BY id, name, id_cabang, target_konsumer", nativeQuery = true)
	List<M_realisasi_ao> findAllByStatusLoan(String tgl1, String tgl2);

	
}
