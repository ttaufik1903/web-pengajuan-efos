package com.taufik.servicereporting.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Realisasi_qacakonsumtif_rekap;



@Repository
@EnableJpaRepositories
public interface Realisasi_qacakonsumtif_rekapRepository
		extends JpaRepository<Realisasi_qacakonsumtif_rekap, String> {
	@Query(value = "SELECT count(id) as noa FROM view_qacakonsumtif_report WHERE (TO_CHAR(approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2) and status_qaca=:status_qaca", nativeQuery = true)
	Realisasi_qacakonsumtif_rekap findAllNoa(LocalDate tgl1, LocalDate tgl2, String status_qaca);
	
	@Query(value = "SELECT count(id) as noa FROM view_qacakonsumtif_report WHERE (TO_CHAR(approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2)", nativeQuery = true)
	Realisasi_qacakonsumtif_rekap findAllNoa(LocalDate tgl1, LocalDate tgl2);
	
	@Query(value = "SELECT count(id) as noa FROM view_qacakonsumtif_report WHERE (TO_CHAR(approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2) and status_qaca IS NULL", nativeQuery = true)
	Realisasi_qacakonsumtif_rekap findAllNoaNull(LocalDate tgl1, LocalDate tgl2);

}
