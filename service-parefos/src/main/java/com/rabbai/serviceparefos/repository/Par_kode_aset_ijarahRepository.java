package com.rabbai.serviceparefos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_akad;
import com.rabbai.serviceparefos.models.Par_kode_aset_ijarah;
import com.rabbai.serviceparefos.models.Par_kode_margin;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_kode_aset_ijarahRepository extends JpaRepository<Par_kode_aset_ijarah, String> {
	 
}
