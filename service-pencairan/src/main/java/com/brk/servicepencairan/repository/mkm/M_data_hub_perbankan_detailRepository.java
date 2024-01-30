package com.brk.servicepencairan.repository.mkm;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brk.servicepencairan.models.mkm.M_data_hub_perbankan_detail;

@Repository
@EnableJpaRepositories
public interface M_data_hub_perbankan_detailRepository extends JpaRepository<M_data_hub_perbankan_detail, String> {
	@Modifying
    @Query(value = "DELETE FROM m_data_hub_perbankan_detail WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    int deleteByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM m_data_hub_perbankan_detail WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	List<M_data_hub_perbankan_detail> findByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM m_data_hub_perbankan_detail WHERE \"id_loan\"=:id_loan and \"periode\"=:periode", nativeQuery = true)
	M_data_hub_perbankan_detail findBySingleId(@Param("id_loan") String id_loan, @Param("periode") String periode);
	
	
}

