package com.rabbai.serviceparefos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_agunan_penerbit_agunan;
import com.rabbai.serviceparefos.models.Par_agunan_prioritas_agunan;

@Repository
@EnableJpaRepositories
public interface Par_agunan_penerbit_agunanRepository extends JpaRepository<Par_agunan_penerbit_agunan, String> {

}
