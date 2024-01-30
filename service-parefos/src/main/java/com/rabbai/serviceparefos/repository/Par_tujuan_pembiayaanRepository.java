package com.rabbai.serviceparefos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_tujuan_pembiayaan;

@Repository
@EnableJpaRepositories
public interface Par_tujuan_pembiayaanRepository extends JpaRepository<Par_tujuan_pembiayaan, Integer> {

	@Query(value = "SELECT DISTINCT name FROM parameter.par_tujuan_pembiayaan order by name asc", nativeQuery = true)
	List<String> findDistictTujuanPembiayaan();

	@Query(value = "SELECT * FROM parameter.par_tujuan_pembiayaan WHERE \"id_template_dokumen\"=:id_template_dokumen", nativeQuery = true)
	List<Par_tujuan_pembiayaan> findByIdTemp(@Param("id_template_dokumen") Integer id_template_dokumen);

	@Query(value = "SELECT * FROM parameter.par_tujuan_pembiayaan WHERE \"type\"=:type", nativeQuery = true)
	List<Par_tujuan_pembiayaan> findByIdType(@Param("type") String type);

}
