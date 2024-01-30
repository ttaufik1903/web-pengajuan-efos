package com.rabbai.serviceusulan.repository.mkm;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceusulan.models.MScoringLoan;


@Repository
@EnableJpaRepositories
public interface MScoringLoanRepository extends JpaRepository<MScoringLoan, String> {
	@Modifying
    @Query(value = "DELETE FROM m_scoring_loan WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    int deleteByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM m_scoring_loan WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	List<MScoringLoan> findByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT SUM(\"nilai\") as \"nilai\" FROM m_scoring_loan WHERE \"id_loan\"=:id_loan",nativeQuery = true)
    Integer getTotalNilai(@Param("id_loan") String id_loan);
	
}

