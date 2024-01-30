package com.rabbai.serviceparefos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_agunan_jenis_agunan_ppap;
  
@Repository
@EnableJpaRepositories
public interface Par_agunan_jenis_agunan_ppapRepository extends JpaRepository<Par_agunan_jenis_agunan_ppap, String> {

}
