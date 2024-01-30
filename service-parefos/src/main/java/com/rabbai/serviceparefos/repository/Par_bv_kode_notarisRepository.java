package com.rabbai.serviceparefos.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rabbai.serviceparefos.models.Par_bv_kode_notaris;

public interface Par_bv_kode_notarisRepository extends JpaRepository<Par_bv_kode_notaris, String>{

	@Query(value = "SELECT * FROM parameter.par_bv_kode_notaris WHERE \"id\"=:id", nativeQuery = true)
	Par_bv_kode_notaris findBySingleId(@Param("id") String id);
    
    @Query(value = "SELECT * FROM parameter.par_bv_kode_notaris WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_bv_kode_notaris> findByid(@Param("id") String id);

    @Query(
            value = "SELECT * FROM parameter.par_bv_kode_notaris ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_bv_kode_notaris",
            nativeQuery = true)
    Page<Par_bv_kode_notaris> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM parameter.par_bv_kode_notaris WHERE id ~* :keyword" +
                    " OR keterangan ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM parameter.par_bv_kode_notaris WHERE id ~* :keyword" +
                    " keterangan ~* :keyword",
            nativeQuery = true)
    Page<Par_bv_kode_notaris> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);

    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM parameter.par_bv_kode_notaris",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM parameter.par_bv_kode_notaris", nativeQuery = true)
    Integer getCount();
}
