package com.taufik.servicereporting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Par_cabang;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_cabangRepository extends CrudRepository<Par_cabang, String> {


	@Query(value = "SELECT * FROM par_cabang WHERE \"id\"=:id", nativeQuery = true)
	Par_cabang findByKdCab(@Param("id") String id);

	@Query(value = "SELECT * FROM par_cabang WHERE \"id\"=:id", nativeQuery = true)
	Optional<Par_cabang> findByKd(@Param("id") String id);

	@Query(value = "SELECT * FROM par_cabang WHERE \"cab_induk\"=:cab_induk", nativeQuery = true)
	List<Par_cabang> findByCabInduk(@Param("cab_induk") String cab_induk);

	@Query(value = "SELECT * FROM par_cabang WHERE \"jenis_cab\"=:jenis_cab", nativeQuery = true)
	List<Par_cabang> findByJenisCab(@Param("jenis_cab") String jenis_cab);

	@Query(value = "SELECT * FROM par_cabang ORDER BY \"id\" ASC", countQuery = "SELECT count(*) FROM par_cabang", nativeQuery = true)
	Page<Par_cabang> findAllWithPagination(Pageable pageable);

	@Query(value = "SELECT * FROM par_cabang WHERE cabang ~* :keyword"
			+ " OR jenis_cab ~* :keyword OR id ~* :keyword OR kota ~* :keyword"
			+ " ORDER BY id ASC", countQuery = "SELECT count(*) FROM par_cabang WHERE cabang ~* :keyword"
					+ " jenis_cab ~* :keyword OR id ~* :keyword OR kota ~* :keyword", nativeQuery = true)
	Page<Par_cabang> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);

	@Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_cabang", nativeQuery = true)
	Integer getMaxId();

	@Query(value = "SELECT count(*) FROM par_cabang", nativeQuery = true)
	Integer getCount();

	@Modifying
	@Query(value = "DELETE FROM par_cabang WHERE \"id\"=:id", nativeQuery = true)
	int deleteBySingleId(@Param("id") String id);
}
