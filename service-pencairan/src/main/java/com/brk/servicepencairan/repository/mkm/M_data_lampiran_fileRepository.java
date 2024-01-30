package com.brk.servicepencairan.repository.mkm;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brk.servicepencairan.models.Data_lampiran_file;
import com.brk.servicepencairan.models.mkm.M_data_lampiran_file;

@Repository
@EnableJpaRepositories
public interface M_data_lampiran_fileRepository extends JpaRepository<M_data_lampiran_file, String> {
	@Transactional
	@Modifying
    @Query(value = "DELETE FROM m_data_lampiran_file WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    int deleteByIdLoan(@Param("id_loan") String id_loan);
	
	@Query(value = "SELECT * FROM m_data_lampiran_file WHERE \"id_loan\"=:id_loan", nativeQuery = true)
	List<M_data_lampiran_file> findByIdLoan(@Param("id_loan") String id_loan);
	

	@Query(value = "SELECT * FROM m_data_lampiran_file WHERE \"id\"=:id", nativeQuery = true)
	M_data_lampiran_file findByIdSelect(@Param("id") Integer id);
	
	@Transactional
	@Modifying
    @Query(value = "UPDATE m_data_lampiran_file SET ceklis_admin=null WHERE \"id_loan\"=:id_loan", nativeQuery = true)
    void updateceklistByIdLoan(@Param("id_loan") String id_loan);

}

