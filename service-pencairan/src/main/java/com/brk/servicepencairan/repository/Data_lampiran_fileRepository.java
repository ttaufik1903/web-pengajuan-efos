package com.brk.servicepencairan.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brk.servicepencairan.models.Data_lampiran_file;

@Transactional
@Repository
@EnableJpaRepositories
public interface Data_lampiran_fileRepository extends JpaRepository<Data_lampiran_file, String> {
	@Modifying
    @Query(value = "DELETE FROM data_lampiran_file WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    int deleteByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM data_lampiran_file WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	List<Data_lampiran_file> findByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM data_lampiran_file WHERE \"id\"=:id", nativeQuery = true)
	Data_lampiran_file findByIdSelect(@Param("id") Integer id);
	
	@Query(value = "SELECT * FROM data_lampiran_file WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	Data_lampiran_file findByIdloan(@Param("id_loan") String id_loan);
	
	@Modifying
    @Query(value = "UPDATE data_lampiran_file SET ceklis_admin=null WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    void updateceklistByIdLoan(@Param("id_loan") String id_loan);

}

