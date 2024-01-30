package com.rabbai.serviceprospek.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceprospek.models.Hub_perbankan_detail;


@Repository
@EnableJpaRepositories
public interface Hub_perbankan_detailRepository extends JpaRepository<Hub_perbankan_detail, String> {
	@Modifying
    @Query(value = "DELETE FROM data_hub_perbankan_detail WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    int deleteByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM data_hub_perbankan_detail WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	List<Hub_perbankan_detail> findByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM data_hub_perbankan_detail WHERE \"id_loan\"=:id_loan and \"periode\"=:periode", nativeQuery = true)
	Hub_perbankan_detail findBySingleId(@Param("id_loan") String id_loan, @Param("periode") String periode);
	
	
}

