package com.taufik.servicereporting.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Par_kode_referal;
import com.taufik.servicereporting.models.Par_plan;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_planRepository extends JpaRepository<Par_plan, Integer> {
	

}

