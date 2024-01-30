package com.rabbai.serviceprospek.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceprospek.models.Harta;


@Repository
@EnableJpaRepositories
public interface HartaRepository extends JpaRepository<Harta, Integer> {

	@Query(value = "SELECT * FROM data_harta_i WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	List<Harta> findByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM data_harta_i WHERE \"id_loan\"=:id_loan and \"jenis\"=:jenis", nativeQuery = true)
	Harta findByIdLoanAndJenis(@Param("id_loan") String id_loan, @Param("jenis") String jenis);
	
	@Modifying
    @Query(value = "DELETE FROM data_harta_i WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    int deleteByIdLoan(@Param("id_loan") String id_loan);

}

