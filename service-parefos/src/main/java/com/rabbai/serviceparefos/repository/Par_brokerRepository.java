package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_broker;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_brokerRepository extends JpaRepository<Par_broker, String> {

    @Query(value = "SELECT * FROM par_broker WHERE \"id\"=:id", nativeQuery = true)
    Par_broker findByIdBroker(@Param("id") String id);
    
    @Query(value = "SELECT * FROM par_broker WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_broker> findByid(@Param("id") String id);

    @Query(
            value = "SELECT * FROM par_broker ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_broker",
            nativeQuery = true)
    Page<Par_broker> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_broker WHERE name ~* :keyword" +
                    " OR ket ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_broker WHERE name ~* :keyword" +
                    " ket ~* :keyword",
            nativeQuery = true)
    Page<Par_broker> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);
    
    Page<Par_broker> findByName(String name, Pageable pageable);


    @Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_broker",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_broker", nativeQuery = true)
    Integer getCount();
}

