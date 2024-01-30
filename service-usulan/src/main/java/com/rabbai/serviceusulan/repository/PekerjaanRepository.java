package com.rabbai.serviceusulan.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceusulan.models.Pekerjaan;

@Repository
@EnableJpaRepositories
public interface PekerjaanRepository extends JpaRepository<Pekerjaan, Integer> {

	@Query(value = "SELECT * FROM data_pekerjaan WHERE \"id_debitur\"=:id_debitur and \"id_loan\"=:id_loan", nativeQuery = true)
	Pekerjaan findByIdDebiturAndIdLoan(@Param("id_debitur") String id_debitur,@Param("id_loan") String id_loan);
}

