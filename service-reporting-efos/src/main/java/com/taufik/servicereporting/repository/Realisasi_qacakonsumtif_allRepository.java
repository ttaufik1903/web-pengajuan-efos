package com.taufik.servicereporting.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Realisasi_qacakonsumtif_detail;
import com.taufik.servicereporting.models.Realisasi_qacakonsumtif_per_cabang;

@Repository
@EnableJpaRepositories
public interface Realisasi_qacakonsumtif_allRepository
		extends JpaRepository<Realisasi_qacakonsumtif_detail, String> {

	@Query(value = "SELECT * FROM view_qacakonsumtif_report WHERE (TO_CHAR(approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2) and status_qaca=:status_qaca", nativeQuery = true)
	List<Realisasi_qacakonsumtif_detail> findAllByQaca(LocalDate tgl1, LocalDate tgl2, String status_qaca);
	
	@Query(value = "SELECT * FROM view_qacakonsumtif_report WHERE (TO_CHAR(approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2)", nativeQuery = true)
	List<Realisasi_qacakonsumtif_detail> findAllByQaca(LocalDate tgl1, LocalDate tgl2);
	
	@Query(value = "SELECT * FROM view_qacakonsumtif_report WHERE (TO_CHAR(approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2) and status_qaca IS NULL ", nativeQuery = true)
	List<Realisasi_qacakonsumtif_detail> findAllByQacaNull(LocalDate tgl1, LocalDate tgl2);
	
	@Query(value = "SELECT * FROM view_qacakonsumtif_report WHERE (TO_CHAR(approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2) and status_qaca=:status_qaca and id_cab=:id_cab", nativeQuery = true)
	List<Realisasi_qacakonsumtif_detail> findAllByQacaCab(LocalDate tgl1, LocalDate tgl2, String status_qaca, String id_cab);
	
	@Query(value = "SELECT * FROM view_qacakonsumtif_report WHERE (TO_CHAR(approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2) and id_cab=:id_cab", nativeQuery = true)
	List<Realisasi_qacakonsumtif_detail> findAllByQacaCab(LocalDate tgl1, LocalDate tgl2, String id_cab);
	
	@Query(value = "SELECT * FROM view_qacakonsumtif_report WHERE (TO_CHAR(approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2) and status_qaca IS NULL and id_cab=:id_cab", nativeQuery = true)
	List<Realisasi_qacakonsumtif_detail> findAllByQacaCabNull(LocalDate tgl1, LocalDate tgl2, String id_cab);

}