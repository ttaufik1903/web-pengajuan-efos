package com.rabbai.serviceprospek.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceprospek.models.Data_lampiran_file;

@Repository
@EnableJpaRepositories
public interface Data_lampiran_fileRepository extends JpaRepository<Data_lampiran_file, String> {
	@Modifying
    @Query(value = "DELETE FROM data_lampiran_file WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    int deleteByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM data_lampiran_file WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	List<Data_lampiran_file> findByIdLoan(@Param("id_loan") String id_loan);

}

