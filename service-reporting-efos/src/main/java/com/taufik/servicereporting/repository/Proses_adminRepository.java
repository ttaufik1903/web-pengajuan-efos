package com.taufik.servicereporting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Proses_admin;
import com.taufik.servicereporting.models.Proses_ao;
import com.taufik.servicereporting.models.Realisasi_ao;

@Repository
@EnableJpaRepositories
public interface Proses_adminRepository
		extends JpaRepository<Proses_admin, String> {
	@Query(value = "SELECT id, cabang, count(id_loan) as noa, sum(plafon_disetujui) as realisasi FROM view_proses_admin_cabang WHERE (TO_CHAR(input_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2) GROUP BY id, cabang", nativeQuery = true)
	List<Proses_admin> findAllByStatusLoan(String tgl1, String tgl2);

	
}
