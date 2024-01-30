package com.taufik.servicereporting.repository.mkm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.mkm.M_par_cabang;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface M_par_cabangRepository extends CrudRepository<M_par_cabang, String> {


	@Query("select distinct p from M_par_cabang p join fetch p.loans pc where pc.status = :status and (TO_CHAR(pc.approve_cair1_date, 'YYYY-MM-DD') BETWEEN :tgl1 and :tgl2)")
    List<M_par_cabang> findAllByStatusLoan(String status, String tgl1, String tgl2);
	
	
	@Query(value = "SELECT * FROM m_par_cabang WHERE \"id\"=:id", nativeQuery = true)
	M_par_cabang findByKdCab(@Param("id") String id);

	@Query(value = "SELECT * FROM m_par_cabang WHERE \"id\"=:id", nativeQuery = true)
	Optional<M_par_cabang> findByKd(@Param("id") String id);

	@Query(value = "SELECT * FROM m_par_cabang WHERE \"cab_induk\"=:cab_induk", nativeQuery = true)
	List<M_par_cabang> findByCabInduk(@Param("cab_induk") String cab_induk);

	@Query(value = "SELECT * FROM m_par_cabang WHERE \"jenis_cab\"=:jenis_cab", nativeQuery = true)
	List<M_par_cabang> findByJenisCab(@Param("jenis_cab") String jenis_cab);

	@Query(value = "SELECT * FROM m_par_cabang ORDER BY \"id\" ASC", countQuery = "SELECT count(*) FROM par_cabang", nativeQuery = true)
	Page<M_par_cabang> findAllWithPagination(Pageable pageable);

	@Query(value = "SELECT * FROM m_par_cabang WHERE cabang ~* :keyword"
			+ " OR jenis_cab ~* :keyword OR id ~* :keyword OR kota ~* :keyword"
			+ " ORDER BY id ASC", countQuery = "SELECT count(*) FROM m_par_cabang WHERE cabang ~* :keyword"
					+ " jenis_cab ~* :keyword OR id ~* :keyword OR kota ~* :keyword", nativeQuery = true)
	Page<M_par_cabang> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);

	@Query(value = "SELECT MAX(\"id\") as \"id\" FROM m_par_cabang", nativeQuery = true)
	Integer getMaxId();

	@Query(value = "SELECT count(*) FROM m_par_cabang", nativeQuery = true)
	Integer getCount();

	@Modifying
	@Query(value = "DELETE FROM par_cabang WHERE \"id\"=:id", nativeQuery = true)
	int deleteBySingleId(@Param("id") String id);
}
