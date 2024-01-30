package com.rabbai.serviceusulan.repository.mkm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceusulan.models.mkm.M_data_neraca;
import com.rabbai.serviceusulan.models.mkm.M_data_wallet;


@Repository
@EnableJpaRepositories
public interface M_data_walletRepository extends JpaRepository<M_data_wallet, Integer> {
	@Query(value = "SELECT * FROM m_data_wallet WHERE \"id\"=:id", nativeQuery = true)
	M_data_wallet findByIdWallet(@Param("id") Integer id);
}

