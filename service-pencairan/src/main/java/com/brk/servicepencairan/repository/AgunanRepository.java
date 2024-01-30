package com.brk.servicepencairan.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brk.servicepencairan.models.Agunan;


@Repository
@EnableJpaRepositories
public interface AgunanRepository extends JpaRepository<Agunan, Integer> {

	@Query(value = "SELECT * FROM data_agunan_i WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	List<Agunan> findByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM data_agunan_i WHERE \"id_loan\"=:id_loan and \"status_vlink\"=:status_vlink", nativeQuery = true)
	List<Agunan> findByIdLoanAndSts(@Param("id_loan") String id_loan, @Param("status_vlink") String status_vlink);
	
	@Query(value = "SELECT * FROM data_agunan_i WHERE \"id_loan\"=:id_loan and \"no_bukti\"=:no_bukti", nativeQuery = true)
	Agunan findByIdLoanAndBukti(@Param("id_loan") String id_loan, @Param("no_bukti") String no_bukti);
	
	@Query(value = "SELECT * FROM data_agunan_i WHERE \"urut\"=:urut", nativeQuery = true)
	Agunan findByUrut(@Param("urut") Integer urut);
}

