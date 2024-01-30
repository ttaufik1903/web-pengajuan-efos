package com.rabbai.serviceusulan.repository.mkm;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceusulan.models.mkm.M_data_harta;


@Repository
@EnableJpaRepositories
public interface M_data_hartaRepository extends JpaRepository<M_data_harta, Integer> {

	@Query(value = "SELECT * FROM m_data_harta WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	List<M_data_harta> findByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM m_data_harta WHERE \"id_loan\"=:id_loan and \"jenis\"=:jenis", nativeQuery = true)
	M_data_harta findByIdLoanAndJenis(@Param("id_loan") String id_loan, @Param("jenis") String jenis);
	
	@Modifying
    @Query(value = "DELETE FROM m_data_harta WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    int deleteByIdLoan(@Param("id_loan") String id_loan);

}

