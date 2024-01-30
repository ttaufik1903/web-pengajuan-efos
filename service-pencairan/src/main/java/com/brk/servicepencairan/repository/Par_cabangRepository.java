package com.brk.servicepencairan.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brk.servicepencairan.models.Par_cabang;
import com.brk.servicepencairan.models.mkm.M_data_bi;


@Repository
@EnableJpaRepositories
public interface Par_cabangRepository extends JpaRepository<Par_cabang, String> {

	 @Query(value = "SELECT * FROM par_cabang WHERE \"id\"=:id", nativeQuery = true)
	 Par_cabang findByIdCab(@Param("id") String id);
}

