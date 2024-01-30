package com.brk.servicepencairan.repository.mkm;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brk.servicepencairan.models.Agunan;
import com.brk.servicepencairan.models.mkm.M_data_agunan;

@Repository
@EnableJpaRepositories
public interface M_data_agunanRepository extends JpaRepository<M_data_agunan, Integer> {

	@Query(value = "SELECT * FROM m_data_agunan WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	List<M_data_agunan> findByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM m_data_agunan WHERE \"id_loan\"=:id_loan and \"status_vlink\"=:status_vlink", nativeQuery = true)
	List<M_data_agunan> findByIdLoanAndSts(@Param("id_loan") String id_loan, @Param("status_vlink") String status_vlink);
	
	@Query(value = "SELECT * FROM m_data_agunan WHERE \"id_loan\"=:id_loan and \"no_bukti\"=:no_bukti", nativeQuery = true)
	M_data_agunan findByIdLoanAndBukti(@Param("id_loan") String id_loan, @Param("no_bukti") String no_bukti);
	
	@Query(value = "SELECT * FROM m_data_agunan WHERE \"urut\"=:urut", nativeQuery = true)
	M_data_agunan findByUrut(@Param("urut") Integer urut);
	
	@Modifying
    @Query(value = "DELETE FROM m_data_agunan WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    int deleteByIdLoan(@Param("id_loan") String id_loan);
	
}

