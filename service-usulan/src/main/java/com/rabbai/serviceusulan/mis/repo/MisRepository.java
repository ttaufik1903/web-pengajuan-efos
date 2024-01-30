package com.rabbai.serviceusulan.mis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceusulan.mis.domain.Mis;

@Repository
@EnableJpaRepositories

public interface MisRepository extends JpaRepository<Mis, String> {
    Mis findByNorek(String norek);

}