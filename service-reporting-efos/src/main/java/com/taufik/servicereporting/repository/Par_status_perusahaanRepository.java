package com.taufik.servicereporting.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Par_status_perusahaan;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_status_perusahaanRepository extends JpaRepository<Par_status_perusahaan, String> {

	@Query(value = "SELECT * FROM par_status_perusahaan WHERE \"id\"=:id", nativeQuery = true)
	Par_status_perusahaan findBySingleId(@Param("id") String id);
    
    @Query(value = "SELECT * FROM par_status_perusahaan WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_status_perusahaan> findByid(@Param("id") String id);

    @Query(
            value = "SELECT * FROM par_status_perusahaan ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_status_perusahaan",
            nativeQuery = true)
    Page<Par_status_perusahaan> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_status_perusahaan WHERE name ~* :keyword" +
                    " OR ket ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_status_perusahaan WHERE name ~* :keyword" +
                    " ket ~* :keyword",
            nativeQuery = true)
    Page<Par_status_perusahaan> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);

    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_status_perusahaan",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_status_perusahaan", nativeQuery = true)
    Integer getCount();
}

