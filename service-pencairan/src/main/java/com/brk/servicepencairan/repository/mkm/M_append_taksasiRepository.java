package com.brk.servicepencairan.repository.mkm;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brk.servicepencairan.models.mkm.M_append_taksasi;



@Repository
@EnableJpaRepositories
public interface M_append_taksasiRepository extends JpaRepository<M_append_taksasi, String> {
	@Modifying
    @Query(value = "DELETE FROM m_append_taksasi WHERE \"id_agunan\"=:id_agunan", nativeQuery = true)
    int deleteByIdLoan(@Param("id_agunan") String id_agunan);
	
	@Query(value = "SELECT * FROM m_append_taksasi WHERE \"id_agunan\"=:id_agunan", nativeQuery = true)
	List<M_append_taksasi> findListByIdLoan(@Param("id_agunan") String id_agunan);
	
	@Query(value = "SELECT * FROM m_append_taksasi WHERE \"id_agunan\"=:id_agunan", nativeQuery = true)
	M_append_taksasi findByIdLoan(@Param("id_agunan") String id_agunan);
	
	
}

