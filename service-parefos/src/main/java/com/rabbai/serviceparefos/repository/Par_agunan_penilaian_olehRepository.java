package com.rabbai.serviceparefos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.rabbai.serviceparefos.models.Par_agunan_penerbit_agunan;
import com.rabbai.serviceparefos.models.Par_agunan_penilaian_oleh;

@Repository
@EnableJpaRepositories
public interface Par_agunan_penilaian_olehRepository extends JpaRepository<Par_agunan_penilaian_oleh, String> {

}
