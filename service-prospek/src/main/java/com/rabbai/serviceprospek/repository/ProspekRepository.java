package com.rabbai.serviceprospek.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabbai.serviceprospek.models.Data_prospek;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ProspekRepository extends JpaRepository<Data_prospek, Integer> {

	@Query(value = "SELECT max(\"data_prospek\".no_tiket) FROM \"data_prospek\" where cab=:cab and substr(\"data_prospek\".no_tiket, 0, 12) =:no_tiket", nativeQuery = true)
	String getMax(@Param("no_tiket") String no_tiket, @Param("cab") Integer cab);

	@Query(value = "SELECT * FROM data_prospek WHERE \"ktp\"=:ktp and status=:status", nativeQuery = true)
	Data_prospek findByKtpDebitur(@Param("ktp") String ktp, @Param("status") String status);

	@Query(value = "SELECT * FROM data_prospek WHERE \"no_tiket\"=:no_tiket and \"jenis_pembiayaan\"=:jenis_pembiayaan", nativeQuery = true)
	Data_prospek findByNoTiket(@Param("no_tiket") String no_tiket, @Param("jenis_pembiayaan") String jenis_pembiayaan);

	@Query(value = "SELECT * FROM data_prospek WHERE \"no_tiket\"=:no_tiket", nativeQuery = true)
	Data_prospek findByNoTiket2(@Param("no_tiket") String no_tiket);

	@Query(value = "SELECT * FROM data_prospek WHERE \"ktp\"=:ktp", nativeQuery = true)
	Data_prospek findByKtpDeb(@Param("ktp") String ktp);

	@Query(value = "SELECT * FROM data_prospek WHERE \"ktp\"=:ktp", nativeQuery = true)
	Optional<Data_prospek> findByKtp(@Param("ktp") String ktp);

	@Query(value = "SELECT * FROM data_prospek WHERE cab=:cab and jenis_pembiayaan=:jenis_pembiayaan ORDER BY \"datepost_open\" DESC", countQuery = "SELECT count(*) FROM data_debitur_i WHERE cab=:cab", nativeQuery = true)
	Page<Data_prospek> findAllDebiturWithPagination(Pageable pageable, @Param("cab") Integer cab,
			@Param("jenis_pembiayaan") String jenis_pembiayaan);
	
	@Query(value = "SELECT * FROM data_prospek WHERE  jenis_pembiayaan=:jenis_pembiayaan ORDER BY \"datepost_open\" DESC", countQuery = "SELECT count(*) FROM data_debitur_i WHERE cab=:cab", nativeQuery = true)
	Page<Data_prospek> findAllDebiturWithPaginationAll(Pageable pageable, 
			@Param("jenis_pembiayaan") String jenis_pembiayaan);

	@Query(value = "SELECT * FROM data_prospek WHERE cab=:cab and jenis_pembiayaan=:jenis_pembiayaan and ktp ~* :keyword"
			+ " OR nama ~* :keyword OR npwp ~* :keyword OR no_tiket ~* :keyword OR email ~* :keyword OR no_telp ~* :keyword OR tmp_lahir ~* :keyword"
			+ " OR CAST(plafon_pengajuan AS text) ~* :keyword OR CAST(tenor_pengajuan AS text) ~* :keyword OR CAST(tgl_lahir AS text) ~* :keyword ORDER BY ktp DESC", countQuery = "SELECT count(*) FROM data_prospek WHERE cab=:cab and jenis_pembiayaan=:jenis_pembiayaan and ktp ~* :keyword"
					+ " OR nama ~* :keyword OR npwp ~* :keyword OR no_tiket ~* :keyword OR email ~* :keyword OR no_telp ~* :keyword OR tmp_lahir ~* :keyword"
					+ " OR CAST(plafon_pengajuan AS text) ~* :keyword OR CAST(tenor_pengajuan AS text) ~* :keyword OR CAST(tgl_lahir AS text) ~* :keyword", nativeQuery = true)
	Page<Data_prospek> findKeywordDebiturWithPagination(Pageable pageable, @Param("keyword") String keyword,
			@Param("cab") Integer cab, @Param("jenis_pembiayaan") String jenis_pembiayaan);
	
	

	@Query(value = "SELECT * FROM data_prospek WHERE cab=:cab and jenis_pembiayaan=:jenis_pembiayaan and ktp ~* :keyword"
			+ " OR nama ~* :keyword OR npwp ~* :keyword OR no_tiket ~* :keyword OR email ~* :keyword OR no_telp ~* :keyword OR tmp_lahir ~* :keyword"
			+ " OR CAST(plafon_pengajuan AS text) ~* :keyword OR CAST(tenor_pengajuan AS text) ~* :keyword OR CAST(tgl_lahir AS text) ~* :keyword ORDER BY ktp DESC", countQuery = "SELECT count(*) FROM data_prospek WHERE   jenis_pembiayaan=:jenis_pembiayaan and ktp ~* :keyword"
					+ " OR nama ~* :keyword OR npwp ~* :keyword OR no_tiket ~* :keyword OR email ~* :keyword OR no_telp ~* :keyword OR tmp_lahir ~* :keyword"
					+ " OR CAST(plafon_pengajuan AS text) ~* :keyword OR CAST(tenor_pengajuan AS text) ~* :keyword OR CAST(tgl_lahir AS text) ~* :keyword", nativeQuery = true)
	Page<Data_prospek> findKeywordDebiturAllWithPagination(Pageable pageable, @Param("keyword") String keyword,
			@Param("jenis_pembiayaan") String jenis_pembiayaan);
//
//    @Query(value = "SELECT MAX(\"ktp\") as \"ktp\" FROM data_prospek",nativeQuery = true)
//    Integer getMaxId();

	@Query(value = "SELECT count(*) FROM data_prospek WHERE cab=:cab and jenis_pembiayaan=:jenis_pembiayaan", nativeQuery = true)
	Integer getCount(@Param("cab") Integer cab, @Param("jenis_pembiayaan") String jenis_pembiayaan);

	@Query(value = "SELECT count(*) FROM data_prospek WHERE  jenis_pembiayaan=:jenis_pembiayaan", nativeQuery = true)
	Integer getCountAll(@Param("jenis_pembiayaan") String jenis_pembiayaan);

//    @Query(value = "SELECT count(*) FROM data_prospek WHERE cab=:cab and jenis_pembiayaan=:jenis_pembiayaan",nativeQuery = true)
//    Integer getCount(@Param("cab") String cab, @Param("jenis_pembiayaan") String jenis_pembiayaan);
}
