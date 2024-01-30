package com.rabbai.serviceprospek.mis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceprospek.mis.domain.Mis;

@Repository
@EnableJpaRepositories

public interface MisRepository extends JpaRepository<Mis, String> {
    Mis findByNorek(String norek);

}