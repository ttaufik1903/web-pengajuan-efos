package com.rabbai.serviceusulan.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceusulan.models.Par_cabang;


@Repository
@EnableJpaRepositories
public interface Par_cabangRepository extends JpaRepository<Par_cabang, String> {

	
}

