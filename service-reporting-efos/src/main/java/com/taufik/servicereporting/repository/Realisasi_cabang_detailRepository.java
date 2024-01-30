package com.taufik.servicereporting.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Realisasi_cabang_detail;


@Repository
@EnableJpaRepositories
public interface Realisasi_cabang_detailRepository
		extends JpaRepository<Realisasi_cabang_detail, String> {
	@Query(value = "SELECT * FROM view_realisasi_cabang WHERE id=:id and DATE(approve_cair1_date) BETWEEN :tgl1 and :tgl2", nativeQuery = true)
	List<Realisasi_cabang_detail> findAllByGroup(String id, LocalDate tgl1 , LocalDate tgl2);

	
}
