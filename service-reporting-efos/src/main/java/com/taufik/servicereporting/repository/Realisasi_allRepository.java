package com.taufik.servicereporting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Realisasi_all;



@Repository
@EnableJpaRepositories
public interface Realisasi_allRepository
		extends JpaRepository<Realisasi_all, String> {
	@Query(value = "SELECT count(id) as noa, sum(plafon_disetujui) as realisasi FROM view_realisasi_cabang WHERE (TO_CHAR(approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2) ", nativeQuery = true)
	List<Realisasi_all> findAllByGroup(String tgl1, String tgl2);

}