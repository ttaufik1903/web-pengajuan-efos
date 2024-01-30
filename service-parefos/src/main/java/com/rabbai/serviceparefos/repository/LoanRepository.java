package com.rabbai.serviceparefos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceparefos.models.Loan;

@Repository
@EnableJpaRepositories
public interface LoanRepository extends JpaRepository<Loan, String> {

}

