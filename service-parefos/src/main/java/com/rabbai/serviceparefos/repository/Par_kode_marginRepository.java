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
import com.rabbai.serviceparefos.models.Par_kode_margin;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_kode_marginRepository extends JpaRepository<Par_kode_margin, String> {
	//@Query("select p from Par_kode_margin p JOIN  Par_akad q where q.inisial = :inisial")
	@Query("select p from Par_kode_margin p left join fetch p.par_akads q where q.inisial = :inisial")
	List<Par_kode_margin> findAllByInisial(String inisial);
}
