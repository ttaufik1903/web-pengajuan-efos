package com.rabbai.serviceparefos.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Par_plan;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_planRepository extends JpaRepository<Par_plan, Integer> {

    @Query(value = "SELECT * FROM par_plan WHERE \"id\"=:id", nativeQuery = true)
    Par_plan findBySingleId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM par_plan WHERE \"id\"=:id", nativeQuery = true)
    Optional<Par_plan> findByid(@Param("id") Integer id);

    @Query(value = "SELECT * FROM par_plan WHERE \"id_sub_produk\"=:id_sub_produk and \"id_template_dokumen\"=:id_template_dokumen", nativeQuery = true)
    List<Par_plan> findByIdSubProduk(@Param("id_sub_produk") Integer id_sub_produk, @Param("id_template_dokumen") Integer id_template_dokumen);
    
    @Query(
            value = "SELECT * FROM par_plan ORDER BY \"id\" ASC",
            countQuery = "SELECT count(*) FROM par_plan",
            nativeQuery = true)
    Page<Par_plan> findAllWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM par_plan WHERE name ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword OR CAST(id_akad AS text) ~* :keyword OR CAST(id_sub_produk AS text) ~* :keyword" +
                    " ORDER BY id ASC",
            countQuery = "SELECT count(*) FROM par_plan WHERE name ~* :keyword" +
                    " OR CAST(id AS text) ~* :keyword OR CAST(id_akad AS text) ~* :keyword OR CAST(id_sub_produk AS text) ~* :keyword",
            nativeQuery = true)
    Page<Par_plan> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);


    @Query(value = "SELECT MAX(\"id\") as \"plan\" FROM par_plan",nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT count(*) FROM par_plan", nativeQuery = true)
    Integer getCount();
}

