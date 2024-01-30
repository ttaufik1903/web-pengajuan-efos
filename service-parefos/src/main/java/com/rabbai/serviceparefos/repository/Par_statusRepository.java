package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_status;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_statusRepository extends JpaRepository<Par_status, String> {

	@Query(value = "SELECT * FROM par_status WHERE \"id\"=:id", nativeQuery = true)
	Par_status findBySingleId(@Param("id") String id);
    
    @Query(value = "SELECT * FROM par_status WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_status> findByid(@Param("id") String id);

    @Query(
            value = "SELECT * FROM par_status ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_status",
            nativeQuery = true)
    Page<Par_status> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_status WHERE name ~* :keyword" +
                    " OR ket ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_status WHERE name ~* :keyword" +
                    " ket ~* :keyword",
            nativeQuery = true)
    Page<Par_status> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);

    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_status",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_status", nativeQuery = true)
    Integer getCount();
}

