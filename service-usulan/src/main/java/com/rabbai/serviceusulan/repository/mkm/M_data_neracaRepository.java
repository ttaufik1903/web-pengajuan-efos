package com.rabbai.serviceusulan.repository.mkm;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceusulan.models.mkm.M_data_neraca;


@Repository
@EnableJpaRepositories
public interface M_data_neracaRepository extends JpaRepository<M_data_neraca, Integer> {
	@Query(value = "SELECT * FROM m_data_neraca WHERE \"id\"=:id", nativeQuery = true)
	M_data_neraca findByIdNeraca(@Param("id") Integer id);
}

