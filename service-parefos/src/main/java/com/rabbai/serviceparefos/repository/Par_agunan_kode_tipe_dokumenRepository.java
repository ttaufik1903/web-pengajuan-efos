package com.rabbai.serviceparefos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_agunan_kode_tipe_dokumen;

@Repository
@EnableJpaRepositories
public interface Par_agunan_kode_tipe_dokumenRepository extends JpaRepository<Par_agunan_kode_tipe_dokumen, String> {

}
