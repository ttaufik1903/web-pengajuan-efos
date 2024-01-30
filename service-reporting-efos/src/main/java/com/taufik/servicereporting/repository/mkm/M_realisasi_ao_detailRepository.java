package com.taufik.servicereporting.repository.mkm;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.mkm.M_realisasi_ao_detail;

@Repository
@EnableJpaRepositories
public interface M_realisasi_ao_detailRepository
		extends JpaRepository<M_realisasi_ao_detail, String> {
	@Query(value = "SELECT * FROM view_realisasi_ao_mkm WHERE id=:id and DATE(approve_cair1_date) BETWEEN :tgl1 and :tgl2", nativeQuery = true)
	List<M_realisasi_ao_detail> findAllByStatusLoan(String id, LocalDate tgl1 , LocalDate tgl2);

	
}
