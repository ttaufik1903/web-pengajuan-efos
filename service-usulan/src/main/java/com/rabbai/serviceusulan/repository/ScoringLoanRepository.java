package com.rabbai.serviceusulan.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceusulan.models.ScoringLoan;

@Repository
@EnableJpaRepositories
public interface ScoringLoanRepository extends JpaRepository<ScoringLoan, String> {
	@Modifying
    @Query(value = "DELETE FROM scoring_loan WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    int deleteByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM scoring_loan WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	List<ScoringLoan> findByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT SUM(\"nilai\") as \"nilai\" FROM scoring_loan WHERE \"id_loan\"=:id_loan",nativeQuery = true)
    Integer getTotalNilai(@Param("id_loan") String id_loan);
	
}

