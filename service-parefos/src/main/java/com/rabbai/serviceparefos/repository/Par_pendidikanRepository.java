package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_pendidikan;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_pendidikanRepository extends JpaRepository<Par_pendidikan, Integer> {

	@Query(value = "SELECT * FROM par_pendidikan WHERE \"id\"=:id", nativeQuery = true)
	Par_pendidikan findBySingleId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_pendidikan WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_pendidikan> findByid(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM par_pendidikan ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_pendidikan",
            nativeQuery = true)
    Page<Par_pendidikan> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_pendidikan WHERE name ~* :keyword" +
                    " OR ket ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_pendidikan WHERE name ~* :keyword" +
                    " ket ~* :keyword",
            nativeQuery = true)
    Page<Par_pendidikan> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);

    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_pendidikan",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_pendidikan", nativeQuery = true)
    Integer getCount();
}

