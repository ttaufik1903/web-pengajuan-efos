package com.taufik.servicereporting.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Realisasi_qacakonsumtif_per_cabang;



@Repository
@EnableJpaRepositories
public interface Realisasi_qacakonsumtif_per_cabangRepository
		extends JpaRepository<Realisasi_qacakonsumtif_per_cabang, String> {
	
	@Query(value = "SELECT count(id) as noa, id_cab, cabang FROM view_qacakonsumtif_report WHERE (TO_CHAR(approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2) and status_qaca=:status_qaca GROUP BY id_cab, cabang", nativeQuery = true)
	List<Realisasi_qacakonsumtif_per_cabang> findAllByCabang(LocalDate tgl1, LocalDate tgl2, String status_qaca);
	
	@Query(value = "SELECT count(id) as noa, id_cab, cabang FROM view_qacakonsumtif_report WHERE (TO_CHAR(approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2) GROUP BY id_cab, cabang", nativeQuery = true)
	List<Realisasi_qacakonsumtif_per_cabang> findAllByCabangNoStatus(LocalDate tgl1, LocalDate tgl2);
	
	@Query(value = "SELECT count(id) as noa, id_cab, cabang FROM view_qacakonsumtif_report WHERE (TO_CHAR(approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2) and status_qaca IS NULL GROUP BY id_cab, cabang", nativeQuery = true)
	List<Realisasi_qacakonsumtif_per_cabang> findAllByCabangNull(LocalDate tgl1, LocalDate tgl2);

}
