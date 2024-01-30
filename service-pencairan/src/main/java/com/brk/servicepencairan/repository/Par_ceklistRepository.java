package com.brk.servicepencairan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brk.servicepencairan.models.Par_ceklist;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface Par_ceklistRepository extends JpaRepository<Par_ceklist, Integer> {

	@Query(value = "SELECT * FROM par_ceklist WHERE \"id\"=:id", nativeQuery = true)
	Par_ceklist findBySingleId(@Param("id") Integer id);
	
	@Query(value = "SELECT * FROM par_ceklist WHERE \"id_template_dokumen\"=:id_template_dokumen", nativeQuery = true)
	Par_ceklist findByIdTemplate(@Param("id_template_dokumen") Integer id_template_dokumen);

	@Query(value = "SELECT * FROM par_ceklist WHERE \"id_template_dokumen\"=:id_template_dokumen", nativeQuery = true)
	List<Par_ceklist> findListByIdTemplate(@Param("id_template_dokumen") Integer id_template_dokumen);
	
	
}
