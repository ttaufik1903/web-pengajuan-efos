package com.rabbai.serviceparefos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.rabbai.serviceparefos.models.Par_agunan_cakupan_kewajiban;

@Repository
@EnableJpaRepositories
public interface Par_agunan_cakupan_kewajibanRepository extends JpaRepository<Par_agunan_cakupan_kewajiban, String> {

}
