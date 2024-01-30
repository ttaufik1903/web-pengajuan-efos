package com.brk.servicepencairan.repository.mkm;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brk.servicepencairan.models.mkm.M_data_aspek_keuangan;


@Repository
@EnableJpaRepositories
public interface M_data_aspek_keuanganRepository extends JpaRepository<M_data_aspek_keuangan, Integer> {
	
	@Query(value = "SELECT * FROM m_data_aspek_keuangan WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	List<M_data_aspek_keuangan> findByIdLoan(@Param("id_loan") String id_loan);
}

