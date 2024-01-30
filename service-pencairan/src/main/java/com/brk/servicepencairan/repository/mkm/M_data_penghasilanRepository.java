package com.brk.servicepencairan.repository.mkm;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brk.servicepencairan.models.mkm.M_data_penghasilan;


@Repository
@EnableJpaRepositories
public interface M_data_penghasilanRepository extends JpaRepository<M_data_penghasilan, Integer> {

	@Query(value = "SELECT * FROM m_data_penghasilan WHERE \"id_debitur\"=:id_debitur and \"id_loan\"=:id_loan", nativeQuery = true)
	M_data_penghasilan findByIdDebiturAndIdLoan(@Param("id_debitur") String id_debitur,@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM m_data_penghasilan WHERE \"id\"=:id ", nativeQuery = true)
	M_data_penghasilan findByIdPenghasilan(@Param("id") Integer id);
}

