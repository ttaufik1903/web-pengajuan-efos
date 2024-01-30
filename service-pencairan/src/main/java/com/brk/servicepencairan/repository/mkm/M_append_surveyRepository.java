package com.brk.servicepencairan.repository.mkm;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brk.servicepencairan.models.mkm.M_append_survey;


@Repository
@EnableJpaRepositories
public interface M_append_surveyRepository extends JpaRepository<M_append_survey, String> {
	@Transactional
	@Modifying
    @Query(value = "DELETE FROM m_append_survey WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    int deleteByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM m_append_survey WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	List<M_append_survey> findListByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM m_append_survey WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	M_append_survey findByIdLoan(@Param("id_loan") String id_loan);
	
	
}

