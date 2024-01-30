package com.brk.servicepencairan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.brk.servicepencairan.models.Users;

@Repository
@EnableJpaRepositories
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String instansi_kode);
}
