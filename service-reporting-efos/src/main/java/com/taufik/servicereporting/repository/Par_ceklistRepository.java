package com.taufik.servicereporting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.servicereporting.models.Par_ceklist;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Par_ceklistRepository extends JpaRepository<Par_ceklist, Integer> {

	@Query(value = "SELECT * FROM par_ceklist WHERE \"id\"=:id", nativeQuery = true)
	Par_ceklist findBySingleId(@Param("id") Integer id);
	
	@Query(value = "SELECT * FROM par_ceklist WHERE \"id_kategori_produk\"=:id_kategori_produk", nativeQuery = true)
	Par_ceklist findByIdKategoriProduk(@Param("id_kategori_produk") Integer id_kategori_produk);

	@Query(value = "SELECT * FROM par_ceklist WHERE \"id\"=:id", nativeQuery = true)
	Optional<Par_ceklist> findByid(@Param("id") Integer id);

	@Query(value = "SELECT * FROM par_ceklist ORDER BY \"id\" ASC", countQuery = "SELECT count(*) FROM par_profesi", nativeQuery = true)
	Page<Par_ceklist> findAllWithPagination(Pageable pageable);

	@Query(value = "SELECT * FROM par_ceklist WHERE \"id_kategori_produk\"=:id_kategori_produk", nativeQuery = true)
	List<Par_ceklist> findListByIdKategoriProduk(@Param("id_kategori_produk") Integer id_kategori_produk);
	

	@Query(value = "SELECT * FROM par_ceklist WHERE name ~* :keyword" + " OR CAST(id_kategori_produk AS text) ~*  :keyword"
			+ " ORDER BY id ASC", countQuery = "SELECT count(*) FROM par_ceklist WHERE name ~* :keyword"
					+ " OR CAST(id_kategori_produk AS text) ~* :keyword", nativeQuery = true)
	Page<Par_ceklist> findKeywordWithPagination(Pageable pageable, @Param("keyword") String keyword);

	@Query(value = "SELECT MAX(\"id\") as \"id\" FROM par_ceklist", nativeQuery = true)
	Integer getMaxId();

	@Query(value = "SELECT count(*) FROM par_ceklist", nativeQuery = true)
	Integer getCount();
}
