package com.rabbai.serviceparefos.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import com.netflix.discovery.converters.Auto;
import com.rabbai.serviceparefos.models.*;
import com.rabbai.serviceparefos.repository.*;

@RestController
public class Parameter_mkmController {

	@Autowired Par_tujuan_pembiayaanRepository par_tujuan_pembiayaanRepository;
	@Autowired Par_agunan_bank_atau_nasabahRepository par_agunan_bank_atau_nasabahRepository;
	@Autowired Par_agunan_sifat_agunanRepository par_agunan_sifat_agunanRepository;
	@Autowired Par_agunan_basel2_jenis_agunanRepository par_agunan_basel2_jenis_agunanRepository;
	@Autowired Par_agunan_cakupan_kewajibanRepository par_agunan_cakupan_kewajibanRepository;
	@Autowired Par_agunan_cash_non_cashRepository par_agunan_cash_non_cashRepository;
	@Autowired Par_agunan_izin_pengkaitanRepository par_agunan_izin_pengkaitanRepository;
	@Autowired Par_agunan_jenis_agunan_biRepository par_agunan_jenis_agunan_biRepository;
	@Autowired Par_agunan_jenis_agunan_ppapRepository par_agunan_jenis_agunan_ppapRepository;
	@Autowired Par_agunan_jenis_agunanRepository par_agunan_jenis_agunanRepository;
	@Autowired Par_agunan_kegunaan_agunanRepository par_agunan_kegunaan_agunanRepository;
	@Autowired Par_agunan_kode_kanalRepository par_agunan_kode_kanalRepository;
	@Autowired Par_agunan_kode_pengikatan_internalRepository par_agunan_kode_pengikatan_internalRepository;
	@Autowired Par_agunan_kode_pengikatan_notarialRepository par_agunan_kode_pengikatan_notarialRepository;
	@Autowired Par_agunan_kode_tipe_dokumenRepository par_agunan_kode_tipe_dokumenRepository;
	@Autowired Par_agunan_penerbit_agunanRepository par_agunan_penerbit_agunanRepository;
	@Autowired Par_agunan_penilaian_olehRepository par_agunan_penilaian_olehRepository;
	@Autowired Par_agunan_prioritas_agunanRepository par_agunan_prioritas_agunanRepository;
	@Autowired Par_agunan_sifat_agunanRepository Par_agunan_sifat_agunanRepository;
	@Autowired Par_agunan_status_agunanRepository par_agunan_status_agunanRepository;
	@Autowired Par_agunan_status_akutansi_agunRepository par_agunan_status_akutansi_agunRepository;
	@Autowired Par_bv_kode_notarisRepository par_bv_kode_notarisRepository;
	@Autowired Par_agamaRepository par_agamaRepository;
	@Autowired Par_kode_marginRepository par_kode_marginRepository;
	@Autowired Par_kode_aset_ijarahRepository par_kode_aset_ijarahRepository;
	@Autowired Par_bi_status_piutangRepository2 par_bi_status_piutangRepository2;
	@Autowired Par_rating_pihaklawanRepository par_rating_pihaklawanRepository;
	@Autowired Par_lokasi_penggunaRepository par_lokasi_penggunaRepo;
	@Autowired Par_bagian_dijaminRepository par_bagian_dijaminRepository;
	@Autowired Par_jenis_aktiva_ijarahRepository par_jenis_aktiva_ijarahRepository;
	@Autowired Par_bv_jenis_agunanRepository par_bv_jenis_agunanRepository;
	@Autowired Par_bv_tujuan_garansiRepository par_bv_tujuan_garansiRepo;
	@Autowired Par_bv_karakteristik_akadRepository par_bv_karakteristik_akadRepository;
	@Autowired Par_bv_hubungan_produsenRepository par_bv_hubungan_produsenRepository;
	@Autowired Par_bv_jenis_asuransiRepository Par_bv_jenis_asuransiRepository;
	@Autowired Par_bv_terkaitRepository par_bv_terkaitRepository;
	@Autowired Par_bv_tujuan_kreditRepository par_bv_tujuan_kreditRepository;
	@Autowired Par_bv_sifat_kreditRepository par_bv_sifat_kreditRepository;
	@Autowired Par_bv_bagian_dijaminRepository par_bv_bagian_dijaminRepository;
	@Autowired Par_bv_golongan_piutangRepository par_bv_golongan_piutangRepository;
	@Autowired Par_bv_jenis_aktivaRepository par_bv_jenis_aktivaRepository;
	@Autowired Par_bv_golongan_debiturRepository par_bv_golongan_debiturRepository;
	@Autowired Par_bv_kode_kondisiRepository par_bv_kode_kondisiRepository;
	@Autowired Par_bi_sifatAkadRepository par_bi_sifat_akadRepository;
	@Autowired Par_bv_status_piutangRepository par_bv_status_piutangRepository;
	@Autowired Par_bv_sumber_danaRepository par_bv_sumber_danaRepository;
	@Autowired Par_bv_sektor_ekonomiRepository par_bv_sektor_ekonomiRepository;
	@Autowired Par_bv_jenis_garansiRepository par_bv_jenis_garansiRepository;
	@Autowired Par_bv_kode_sebab_macetRepository par_bv_kode_sebab_macetRepository;
	@Autowired Par_bv_kode_frekuensi_restrukRepository par_bv_kode_frekuensi_restrukRepository;
	@Autowired Par_bv_kode_persentasemarginRepository par_bv_kode_persentasemarginRepository;
	@Autowired Par_bidang_usahaRepository par_bidang_usahaRepository;
	@Autowired Par_dati2Repository par_dati2Repository;
	@Autowired Par_kebangsaanRepository par_kebangsaanRepository;
	@Autowired Par_kecRepository par_kecRepository;
	@Autowired Par_kelRepository par_kelRepository;
	@Autowired Par_kelaminRepository par_kelaminRepository;
	@Autowired Par_pendidikanRepository par_pendidikanRepository;
	@Autowired Par_profesiRepository par_profesiRepository;
	@Autowired Par_provinsiRepository par_provinsiRepository;
	@Autowired Par_status_perusahaanRepository par_status_perusahaanRepository;
	@Autowired Par_statusRepository par_statusRepository;
	@Autowired Par_planRepository par_planRepository;
	@Autowired Par_tempat_tinggalRepository par_tempat_tinggalRepository;
	@Autowired Par_akadRepository par_akadRepository;
	@Autowired Par_produkRepository par_produkRepository;
	@Autowired Par_sub_produkRepository par_sub_produkRepository;
	@Autowired Par_m_scoringRepository par_m_scoringRepository;
	@Autowired Par_rincian_scoringRepository par_rincian_scoringRepository;
	@Autowired Par_kategoriprodukRepository par_kategoriprodukRepository;
	@Autowired Par_ceklistRepository par_ceklistRepository;
	@Autowired LoanRepository loanRepository;
	@Autowired Par_dinasRepository par_dinasRepository;
	@Autowired Par_sub_dinasRepository par_sub_dinasRepository;
	@Autowired Par_sub_sub_dinasRepository par_sub_sub_dinasRepository;
	@Autowired Par_cabangRepository par_cabangRepository;
	@Autowired Par_template_dokumenRepository par_template_dokumenRepository;
	@Autowired Par_kategori_portofolioRepository par_bv_kateportfolioRepo;
	@Autowired Par_bagi_hasilRepository par_bagi_hasilRepository;
	@Autowired Par_gol_pihak_lawanRepository par_gol_pihak_lawanRepository;
	@Autowired Par_jenis_asetRepository par_jenis_asetRepository;
	@Autowired Par_jenis_suku_imbalanRepository par_jenis_suku_imbalanRepository;
	@Autowired Par_jenis_valutaRepository par_jenis_valutaRepository;
	@Autowired Par_kategori_portofolioRepository par_kategori_portofolioRepository;
	@Autowired Par_kategori_debiturRepository par_kategori_debiturRepository;
	@Autowired Par_kualitasRepository par_kualitasRepository;
	@Autowired Par_lembaga_pemeringkatRepository par_lembaga_pemeringkatRepository;
	@Autowired Par_metode_amortisasiRepository par_metode_amortisasiRepository;
	@Autowired Par_orientasi_penggunaanRepository par_orientasi_penggunaanRepository;
	@Autowired Par_periode_sewaRepository par_periode_sewaRepository;
	@Autowired Par_program_pemerintahRepository par_program_pemerintahRepository;
	@Autowired Par_sandi_biRepository par_sandi_biRepository;
	@Autowired Par_sektor_ekonomiRepository par_sektor_ekonomiRepository;
	@Autowired Par_sektor_ekonomi_kurRepository par_sektor_ekonomi_kurRepository;
	@Autowired Par_sifat_investasiRepository par_sifat_investasiRepository;
	@Autowired Par_sifat_pembiayaanRepository par_sifat_pembiayaanRepository;
	@Autowired Par_skim_pembiayaanRepository par_skim_pembiayaanRepository;
	@Autowired Par_sumber_danaRepository par_sumber_danaRepository;
	@Autowired Par_brokerRepository par_brokerRepository;
	@Autowired Par_asuRepository par_asuRepository;
	@Autowired Par_jns_agunanRepository par_jns_agunanRepository;
	@Autowired Par_qaca_groupRepository par_qaca_groupRepository;
	@Autowired Par_qaca_detailRepository par_qaca_detailRepository;
	@Autowired Par_bv_kode_notarisRepository par_bv_notarisRepo;
	@Autowired Par_bv_status_debiturRepository par_bv_statusDebiturRepo;
	@Autowired Par_bv_kategori_debiturRepository par_bv_kategoriDebRepo;
	@Autowired Par_bv_jenis_penggunaanRepository par_bv_jenisPenggunaanRepo;
	@Autowired Par_bv_orientasi_penggunaanRepository par_bv_orientasiRepo;
	@Autowired Par_bv_sifat_piutangRepository par_bv_sifat_piutangRepo;
	@Autowired Par_bv_jenis_piutangRepository par_bv_jenis_piutangRepo;
	@Autowired Par_bv_jenis_akadRepository par_jenis_akadRepo;
	@Autowired Par_bi_sifatAkadRepository par_bi_sifat_akad;
	@Autowired Par_bv_cara_restrukRepository par_bv_cara_restrukRepository;
	@Autowired Par_bv_takeover_dariRepository par_bv_takeover_dariRepository;
	@Autowired Par_code_officerRepository par_code_officerRepository;
	@Autowired Par_kode_referalRepository par_kode_referalRepository;
	@Autowired Par_sumber_dana_biRepository par_sumber_dana_biRepository;
	@Autowired Par_kode_sektor_ekonomi_biRepository par_kode_sektor_ekonomi_biRepository;
	@Autowired Par_gol_debitur_sidRepository par_gol_debitur_sidRepository;
	@Autowired Par_jenis_penggunaan_biRepository par_jenis_penggunaan_biRepository;
	@Autowired Par_bv_sifat_piutangRepository par_bv_sifat_piutangRepository;
	@Autowired Par_badan_hukumRepository par_badan_hukumRepository;

	
	@GetMapping("/mkm/parameter/getpartemplate")
	public ResponseEntity<Map<String, Object>> getpartemplate(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_template_dokumen> par = par_template_dokumenRepository.findByIdTemplate("MKM");
			if (par != null) {
				System.out.println("##### TAMPILKAN TEMPLATE ##### ");
				response.put("template", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/mkm/parameter/getparwallet/{id}")
	public ResponseEntity<Map<String, Object>> getparwallet(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_kategori_produk> kategori_produk = par_kategoriprodukRepository.findAll();
			List<Par_kelamin> kelamin = par_kelaminRepository.findAll();
			List<Par_tujuan_pembiayaan> tujuan_pembiayaan = par_tujuan_pembiayaanRepository.findByIdTemp(id);
			List<Par_template_dokumen> par = par_template_dokumenRepository.findByIdTemplate("MKM");
			if (par != null) {
				System.out.println("##### TAMPILKAN TEMPLATE ##### ");
				response.put("par_template", par);
				response.put("kelamin", kelamin);
				response.put("kategori_produk", kategori_produk);
				response.put("tujuan_pembiayaan", tujuan_pembiayaan);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/mkm/parameter/getpartemplatescoring/{id}")
	public ResponseEntity<Map<String, Object>> getpartemplatescoring(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_m_scoring> m_scoring = par_m_scoringRepository.findByStatusAndType("1", id);
			
			if (m_scoring.size()>0) {
				System.out.println("##### TAMPILKAN TEMPLATE SCORING ##### ");

				response.put("mscoring", m_scoring);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/mkm/parameter/getparscoring/{id}")
	public ResponseEntity<Map<String, Object>> getparscoring(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			String id_manajemen_usaha = "";
			String id_komposisi_modal = "";
			String id_kepemilikan_usaha = "";
			String id_s_kepemilikan_agunan = "";
			String id_s_jenis_agunan = "";
			String id_s_status_kepemilikan_agunan = "";
			String id_s_kriteria_agunan = "";
			String id_kondisi_usaha = "";
			String id_prospek_usaha = "";
			String id_pengaruh_iklim_usaha = "";
			String id_penjualan = "";
			String id_usaha_menghasilkan_laba = "";
			String id_s_hubungan_bank = "";
			if (id == 2) {
				id_manajemen_usaha = "X";
				id_komposisi_modal = "Y";
				id_kepemilikan_usaha = "Z";
				id_s_kepemilikan_agunan = "3";
				id_s_jenis_agunan = "4";
				id_s_status_kepemilikan_agunan = "5";
				id_s_kriteria_agunan = "6";
				id_kondisi_usaha = "9";
				id_prospek_usaha = "10";
				id_pengaruh_iklim_usaha = "11";
				id_penjualan = "35";
				id_usaha_menghasilkan_laba = "36";
				id_s_hubungan_bank = "S";
			} else if (id == 3) {
				id_manajemen_usaha = "21";
				id_komposisi_modal = "22";
				id_kepemilikan_usaha = "23";
				id_s_kepemilikan_agunan = "26";
				id_s_jenis_agunan = "27";
				id_s_status_kepemilikan_agunan = "28";
				id_s_kriteria_agunan = "29";
				id_kondisi_usaha = "32";
				id_prospek_usaha = "33";
				id_pengaruh_iklim_usaha = "34";
				id_penjualan = "35";
				id_usaha_menghasilkan_laba = "36";
				id_s_hubungan_bank = "16";
			} else if (id == 4) {
				id_manajemen_usaha = "49";
				id_komposisi_modal = "50";
				id_kepemilikan_usaha = "51";
				id_s_kepemilikan_agunan = "54";
				id_s_jenis_agunan = "55";
				id_s_status_kepemilikan_agunan = "56";
				id_s_kriteria_agunan = "57";
				id_kondisi_usaha = "60";
				id_prospek_usaha = "61";
				id_pengaruh_iklim_usaha = "62";
				id_penjualan = "35";
				id_usaha_menghasilkan_laba = "36";
				id_s_hubungan_bank = "44";
			}
			
			List<Par_tempat_tinggal> tempat_tinggal = par_tempat_tinggalRepository.findAll();
			List<Par_pendidikan> pendidikan = par_pendidikanRepository.findAll();
			List<Par_bidang_usaha> bidang = par_bidang_usahaRepository.findAll();
			List<Par_asu> asuransi = par_asuRepository.findAll();
			List<Par_rincian_scoring> manajemen_usaha = par_rincian_scoringRepository.findByIdMScoring(id_manajemen_usaha);
			List<Par_rincian_scoring> komposisi_modal = par_rincian_scoringRepository.findByIdMScoring(id_komposisi_modal);
			List<Par_rincian_scoring> kepemilikan_usaha = par_rincian_scoringRepository.findByIdMScoring(id_kepemilikan_usaha);
			List<Par_rincian_scoring> s_kepemilikan_agunan = par_rincian_scoringRepository.findByIdMScoring(id_s_kepemilikan_agunan);
			List<Par_rincian_scoring> s_hubungan_bank = par_rincian_scoringRepository.findByIdMScoring(id_s_hubungan_bank);
			List<Par_rincian_scoring> s_jenis_agunan = par_rincian_scoringRepository.findByIdMScoring(id_s_jenis_agunan);
			List<Par_rincian_scoring> s_status_kepemilikan_agunan = par_rincian_scoringRepository.findByIdMScoring(id_s_status_kepemilikan_agunan);
			List<Par_rincian_scoring> s_kriteria_agunan = par_rincian_scoringRepository.findByIdMScoring(id_s_kriteria_agunan);
			List<Par_rincian_scoring> kondisi_usaha = par_rincian_scoringRepository.findByIdMScoring(id_kondisi_usaha);
			List<Par_rincian_scoring> prospek_usaha = par_rincian_scoringRepository.findByIdMScoring(id_prospek_usaha);
			List<Par_rincian_scoring> pengaruh_iklim_usaha = par_rincian_scoringRepository.findByIdMScoring(id_pengaruh_iklim_usaha);
			List<Par_rincian_scoring> penjualan = par_rincian_scoringRepository.findByIdMScoring(id_penjualan);
			List<Par_rincian_scoring> usaha_menghasilkan_laba = par_rincian_scoringRepository.findByIdMScoring(id_usaha_menghasilkan_laba);
			List<Par_kategori_produk> kategori_produk = par_kategoriprodukRepository.findAll();
			List<Par_kelamin> kelamin = par_kelaminRepository.findAll();
			List<Par_tujuan_pembiayaan> tujuan_pembiayaan = par_tujuan_pembiayaanRepository.findByIdTemp(id);
			List<Par_template_dokumen> par = par_template_dokumenRepository.findByIdTemplate("MKM");
			List<Par_provinsi> provinsi = par_provinsiRepository.findAll();
			List<Par_jns_agunan> jenis_agunan = par_jns_agunanRepository.findAll();
			

				response.put("status_tempat_tinggal", tempat_tinggal);
				response.put("pendidikan", pendidikan);
				response.put("bidang", bidang);
				response.put("asuransi", asuransi);
				response.put("manajemen_usaha", manajemen_usaha);
				response.put("komposisi_modal", komposisi_modal);
				response.put("kepemilikan_usaha", kepemilikan_usaha);
				response.put("s_kepemilikan_agunan", s_kepemilikan_agunan);
				response.put("s_hubungan_bank", s_hubungan_bank);
				response.put("s_jenis_agunan", s_jenis_agunan);
				response.put("jenis_agunan", jenis_agunan);
				response.put("s_status_kepemilikan_agunan", s_status_kepemilikan_agunan);
				response.put("s_kriteria_agunan", s_kriteria_agunan);
				response.put("kondisi_usaha", kondisi_usaha);
				response.put("prospek_usaha", prospek_usaha);
				response.put("pengaruh_iklim_usaha", pengaruh_iklim_usaha);
				response.put("penjualan", penjualan);
				response.put("usaha_menghasilkan_laba", usaha_menghasilkan_laba);
				response.put("par_template", par);
				response.put("kelamin", kelamin);
				response.put("kategori_produk", kategori_produk);
				response.put("tujuan_pembiayaan", tujuan_pembiayaan);
				response.put("provinsi", provinsi);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/mkm/parameter/getparagunan")
	public ResponseEntity<Map<String, Object>> getparagunan(
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_agunan_cash_non_cash> cash_non_cash = par_agunan_cash_non_cashRepository.findAll();
			List<Par_agunan_penerbit_agunan> penerbit_agunan = par_agunan_penerbit_agunanRepository.findAll();
			List<Par_agunan_penilaian_oleh> penilaian_oleh = par_agunan_penilaian_olehRepository.findAll();
			List<Par_asu> kode_asuransi1 = par_asuRepository.findAll();
			List<Par_agunan_kode_pengikatan_internal> kode_pengikatan_internal = par_agunan_kode_pengikatan_internalRepository
					.findAll();
			List<Par_agunan_kode_pengikatan_notarial> kode_pengikatan_notarial = par_agunan_kode_pengikatan_notarialRepository
					.findAll();
			List<Par_provinsi> provinsi = par_provinsiRepository.findAll();
			List<Par_agunan_izin_pengkaitan> izin_pengkaitan = par_agunan_izin_pengkaitanRepository.findAll();
			List<Par_agunan_prioritas_agunan> prioritas_agunan = par_agunan_prioritas_agunanRepository.findAll();
			List<Par_agunan_cakupan_kewajiban> cakupan_kewajiban = par_agunan_cakupan_kewajibanRepository.findAll();
			List<Par_agunan_status_agunan> status_agunan = par_agunan_status_agunanRepository.findAll();
			List<Par_agunan_status_akutansi_agun> status_akutansi_agunan = par_agunan_status_akutansi_agunRepository
					.findAll();
			List<Par_agunan_bank_atau_nasabah> bank_atau_nasabah = par_agunan_bank_atau_nasabahRepository.findAll();
			List<Par_agunan_kegunaan_agunan> kegunaan_agunan = par_agunan_kegunaan_agunanRepository.findAll();
			List<Par_agunan_jenis_agunan_bi> jenis_agunan_bi = par_agunan_jenis_agunan_biRepository.findAll();
			List<Par_agunan_kode_kanal> kode_kanal = par_agunan_kode_kanalRepository.findAll();
			List<Par_agunan_kode_tipe_dokumen> kode_tipe_dokumen = par_agunan_kode_tipe_dokumenRepository.findAll();
			List<Par_agunan_jenis_agunan_ppap> jenis_agunan_ppap = par_agunan_jenis_agunan_ppapRepository.findAll();
			List<Par_agunan_basel2_jenis_agunan> basel2_jenis_agunan = par_agunan_basel2_jenis_agunanRepository
					.findAll();
			List<Par_agunan_sifat_agunan> sifat_agunan = par_agunan_sifat_agunanRepository.findAll();

			System.out.println("##### TAMPILKAN ALL ##### ");
			response.put("cash_non_cash", cash_non_cash);
			response.put("penerbit_agunan", penerbit_agunan);
			response.put("penilaian_oleh", penilaian_oleh);
			response.put("kode_asuransi1", kode_asuransi1);
			response.put("kode_pengikatan_internal", kode_pengikatan_internal);
			response.put("kode_pengikatan_notarial", kode_pengikatan_notarial);
			response.put("provinsi", provinsi);
			response.put("izin_pengkaitan", izin_pengkaitan);
			response.put("prioritas_agunan", prioritas_agunan);
			response.put("cakupan_kewajiban", cakupan_kewajiban);
			response.put("status_agunan", status_agunan);
			response.put("status_akutansi_agunan", status_akutansi_agunan);
			response.put("bank_atau_nasabah", bank_atau_nasabah);
			response.put("kegunaan_agunan", kegunaan_agunan);
			response.put("jenis_agunan_bi", jenis_agunan_bi);
			response.put("kode_kanal", kode_kanal);
			response.put("kode_tipe_dokumen", kode_tipe_dokumen);
			response.put("jenis_agunan_ppap", jenis_agunan_ppap);
			response.put("basel2_jenis_agunan", basel2_jenis_agunan);
			response.put("sifat_agunan", sifat_agunan);

			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/mkm/parameter/getparnasabah/{cabang}/{id}")
	public ResponseEntity<Map<String, Object>> getparnasabah(@PathVariable String cabang, @PathVariable Integer id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_agama> agama = par_agamaRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
			List<Par_status> status = par_statusRepository.findAll();
			List<Par_tempat_tinggal> tempat_tinggal = par_tempat_tinggalRepository.findAll();
			List<Par_pendidikan> pendidikan = par_pendidikanRepository.findAll();
			List<Par_kebangsaan> kebangsaan = par_kebangsaanRepository.findAll();
			List<Par_provinsi> provinsi = par_provinsiRepository.findAll();
			List<Par_profesi> profesi = par_profesiRepository.findAll();
			List<Par_status_perusahaan> statusperusahaan = par_status_perusahaanRepository.findAll();
			List<Par_bidang_usaha> bidang = par_bidang_usahaRepository.findAll();
			List<Par_bv_takeover_dari> take_over_dari = par_bv_takeover_dariRepository.findAll();
			List<Par_kelamin> kelamin = par_kelaminRepository.findAll();
			List<Par_bv_kode_notaris> kode_notaris = par_bv_kode_notarisRepository.findAll();

			List<Par_kategori_produk> kategori_produk = par_kategoriprodukRepository.findAll();
			List<Par_broker> broker = par_brokerRepository.findAll();
			List<Par_asu> asuransi = par_asuRepository.findAll();
			List<Par_jns_agunan> jenis_agunan = par_jns_agunanRepository.findAll();

			List<Par_dinas> par_dinas = par_dinasRepository.findByIdCabang(cabang);
			List<Par_kode_aset_ijarah> kode_aset_ijarah = par_kode_aset_ijarahRepository.findAll();
			List<Par_tujuan_pembiayaan> tujuan_pembiayaan = par_tujuan_pembiayaanRepository.findByIdTemp(1);
			List<Par_code_officer> code_officer = par_code_officerRepository.findByCabang(cabang);
			List<Par_kode_referal> kode_referal = par_kode_referalRepository.findAll();
			
			String id_manajemen_usaha = "";
			String id_komposisi_modal = "";
			String id_kepemilikan_usaha = "";
			String id_s_kepemilikan_agunan = "";
			String id_s_jenis_agunan = "";
			String id_s_status_kepemilikan_agunan = "";
			String id_s_kriteria_agunan = "";
			String id_kondisi_usaha = "";
			String id_prospek_usaha = "";
			String id_pengaruh_iklim_usaha = "";
			String id_penjualan = "";
			String id_usaha_menghasilkan_laba = "";
			String id_s_hubungan_bank = "";
			if (id == 2) {
				id_manajemen_usaha = "X";
				id_komposisi_modal = "Y";
				id_kepemilikan_usaha = "Z";
				id_s_kepemilikan_agunan = "3";
				id_s_jenis_agunan = "4";
				id_s_status_kepemilikan_agunan = "5";
				id_s_kriteria_agunan = "6";
				id_kondisi_usaha = "9";
				id_prospek_usaha = "10";
				id_pengaruh_iklim_usaha = "11";
				id_penjualan = "35";
				id_usaha_menghasilkan_laba = "36";
				id_s_hubungan_bank = "S";
			} else if (id == 3) {
				id_manajemen_usaha = "21";
				id_komposisi_modal = "22";
				id_kepemilikan_usaha = "23";
				id_s_kepemilikan_agunan = "26";
				id_s_jenis_agunan = "27";
				id_s_status_kepemilikan_agunan = "28";
				id_s_kriteria_agunan = "29";
				id_kondisi_usaha = "32";
				id_prospek_usaha = "33";
				id_pengaruh_iklim_usaha = "34";
				id_penjualan = "35";
				id_usaha_menghasilkan_laba = "36";
				id_s_hubungan_bank = "16";
			} else if (id == 4) {
				id_manajemen_usaha = "49";
				id_komposisi_modal = "50";
				id_kepemilikan_usaha = "51";
				id_s_kepemilikan_agunan = "54";
				id_s_jenis_agunan = "55";
				id_s_status_kepemilikan_agunan = "56";
				id_s_kriteria_agunan = "57";
				id_kondisi_usaha = "60";
				id_prospek_usaha = "61";
				id_pengaruh_iklim_usaha = "62";
				id_penjualan = "35";
				id_usaha_menghasilkan_laba = "36";
				id_s_hubungan_bank = "44";
			}
			List<Par_rincian_scoring> manajemen_usaha = par_rincian_scoringRepository.findByIdMScoring(id_manajemen_usaha);
			List<Par_rincian_scoring> komposisi_modal = par_rincian_scoringRepository.findByIdMScoring(id_komposisi_modal);
			List<Par_rincian_scoring> kepemilikan_usaha = par_rincian_scoringRepository.findByIdMScoring(id_kepemilikan_usaha);
			List<Par_rincian_scoring> s_kepemilikan_agunan = par_rincian_scoringRepository.findByIdMScoring(id_s_kepemilikan_agunan);
			List<Par_rincian_scoring> s_hubungan_bank = par_rincian_scoringRepository.findByIdMScoring(id_s_hubungan_bank);
			List<Par_rincian_scoring> s_jenis_agunan = par_rincian_scoringRepository.findByIdMScoring(id_s_jenis_agunan);
			List<Par_rincian_scoring> s_status_kepemilikan_agunan = par_rincian_scoringRepository.findByIdMScoring(id_s_status_kepemilikan_agunan);
			List<Par_rincian_scoring> s_kriteria_agunan = par_rincian_scoringRepository.findByIdMScoring(id_s_kriteria_agunan);
			List<Par_rincian_scoring> kondisi_usaha = par_rincian_scoringRepository.findByIdMScoring(id_kondisi_usaha);
			List<Par_rincian_scoring> prospek_usaha = par_rincian_scoringRepository.findByIdMScoring(id_prospek_usaha);
			List<Par_rincian_scoring> pengaruh_iklim_usaha = par_rincian_scoringRepository.findByIdMScoring(id_pengaruh_iklim_usaha);
			List<Par_rincian_scoring> penjualan = par_rincian_scoringRepository.findByIdMScoring(id_penjualan);
			List<Par_rincian_scoring> usaha_menghasilkan_laba = par_rincian_scoringRepository.findByIdMScoring(id_usaha_menghasilkan_laba);
			List<Par_template_dokumen> par = par_template_dokumenRepository.findByIdTemplate("MKM");

			response.put("take_over_dari", take_over_dari);
			response.put("agama", agama);
			response.put("bidang", bidang);
			response.put("kebangsaan", kebangsaan);
			response.put("kelamin", kelamin);
			response.put("pendidikan", pendidikan);
			response.put("profesi", profesi);
			response.put("provinsi", provinsi);
			response.put("statusperusahaan", statusperusahaan);
			response.put("status", status);
			response.put("tempat_tinggal", tempat_tinggal);
			response.put("kategori_produk", kategori_produk);
			response.put("kode_aset_ijarah", kode_aset_ijarah);
			response.put("tujuan_pembiayaan", tujuan_pembiayaan);
			response.put("broker", broker);
			response.put("asuransi", asuransi);
			response.put("jenis_agunan", jenis_agunan);
			response.put("kode_notaris", kode_notaris);
			response.put("dinas", par_dinas);
			response.put("kode_referal", kode_referal);
			response.put("code_officer", code_officer);
			
			response.put("manajemen_usaha", manajemen_usaha);
			response.put("komposisi_modal", komposisi_modal);
			response.put("kepemilikan_usaha", kepemilikan_usaha);
			response.put("s_kepemilikan_agunan", s_kepemilikan_agunan);
			response.put("s_hubungan_bank", s_hubungan_bank);
			response.put("s_jenis_agunan", s_jenis_agunan);
			response.put("s_status_kepemilikan_agunan", s_status_kepemilikan_agunan);
			response.put("s_kriteria_agunan", s_kriteria_agunan);
			response.put("kondisi_usaha", kondisi_usaha);
			response.put("prospek_usaha", prospek_usaha);
			response.put("pengaruh_iklim_usaha", pengaruh_iklim_usaha);
			response.put("penjualan", penjualan);
			response.put("usaha_menghasilkan_laba", usaha_menghasilkan_laba);
			response.put("par_template", par);

			response.put("kode", "00");
			response.put("pesan", "BERHASIL");

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/mkm/parameter/getparsandipelaporan/{cabang}")
	public ResponseEntity<Map<String, Object>> getAllSandiBi(@PathVariable String cabang,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_bagi_hasil> bagi_hasil = par_bagi_hasilRepository.findAll();
			List<Par_gol_pihak_lawan> gol_pihak_lawan = par_gol_pihak_lawanRepository.findAll();
			List<Par_jenis_aset> jenis_aset = par_jenis_asetRepository.findAll();
			List<Par_jenis_suku_imbalan> jenis_suku_imbalan = par_jenis_suku_imbalanRepository.findAll();
			List<Par_jenis_valuta> jenis_valuta = par_jenis_valutaRepository.findAll();
			List<Par_kategori_portofolio> kategori_portofolio = par_kategori_portofolioRepository.findAll();
			List<Par_kategori_debitur> kategori_debitur = par_kategori_debiturRepository.findAll();
			List<Par_kualitas> kualitas = par_kualitasRepository.findAll();
			List<Par_lembaga_pemeringkat> lembaga_pemeringkat = par_lembaga_pemeringkatRepository.findAll();
			List<Par_metode_amortisasi> metode_amortisasi = par_metode_amortisasiRepository.findAll();
			List<Par_orientasi_penggunaan> orientasi_penggunaan = par_orientasi_penggunaanRepository.findAll();
			List<Par_periode_sewa> periode_sewa = par_periode_sewaRepository.findAll();
			List<Par_program_pemerintah> program_pemerintah = par_program_pemerintahRepository.findAll();
			List<Par_sandi_bi> sandi_bi = par_sandi_biRepository.findAll();
			List<Par_sektor_ekonomi> sektor_ekonomi = par_sektor_ekonomiRepository.findAll();
			List<Par_sektor_ekonomi_kur> sektor_ekonomi_kur = par_sektor_ekonomi_kurRepository.findAll();
			List<Par_sifat_investasi> sifat_investasi = par_sifat_investasiRepository.findAll();
			List<Par_sifat_pembiayaan> sifat_pembiayaan = par_sifat_pembiayaanRepository.findAll();
			List<Par_skim_pembiayaan> skim_pembiayaan = par_skim_pembiayaanRepository.findAll();
			List<Par_sumber_dana> sumber_dana = par_sumber_danaRepository.findAll();
			List<Par_sandi_bi> lokasi_pengguna = par_sandi_biRepository.findAll();

			List<Par_bv_status_debitur> status_debitur = par_bv_statusDebiturRepo.findAll();
			List<Par_bv_jenis_penggunaan> jenis_penggunaan = par_bv_jenisPenggunaanRepo.findAll();
			List<Par_bv_jenis_piutang> jenis_piutang = par_bv_jenis_piutangRepo.findAll();
			List<Par_bv_status_piutang> status_piutang = par_bv_status_piutangRepository.findAll();
			List<Par_bv_golongan_debitur> golongan_debitur = par_bv_golongan_debiturRepository.findAll();
			List<Par_bv_jenis_aktiva> jenis_aktiva = par_bv_jenis_aktivaRepository.findAll();
			List<Par_bv_golongan_piutang> golongan_piutang = par_bv_golongan_piutangRepository.findAll();
			List<Par_bv_sifat_kredit> sifat_kredit = par_bv_sifat_kreditRepository.findAll();
			List<Par_bv_tujuan_kredit> tujuan_kredit = par_bv_tujuan_kreditRepository.findAll();
			List<Par_bv_jenis_agunan> jenis_agunan = par_bv_jenis_agunanRepository.findAll();
			List<Par_bv_tujuan_garansi> tujuan_garansi = par_bv_tujuan_garansiRepo.findAll();
			List<Par_jenis_aktiva_ijarah> jenis_aktiva_ijarah = par_jenis_aktiva_ijarahRepository.findAll();
			List<Par_bagian_dijamin> bagian_dijamin = par_bagian_dijaminRepository.findAll();
			List<Par_rating_pihak_lawan> rating_pihak_lawan = par_rating_pihaklawanRepository.findAll();
			List<Par_bv_jenis_akad> jenis_akad = par_jenis_akadRepo.findAll();
			List<Par_bi_status_piutang> bi_status_piutang = par_bi_status_piutangRepository2.findAll();
			List<Par_bi_sifat_akad> bi_sifat_akad = par_bi_sifat_akadRepository.findAll();
			List<Par_bv_cara_restruk> cara_restruktur = par_bv_cara_restrukRepository.findAll();
			List<Par_bv_kode_sebab_macet> kode_sebab_macet = par_bv_kode_sebab_macetRepository.findAll();
			List<Par_bv_kode_frekuensirestruk> freq_restruktur = par_bv_kode_frekuensi_restrukRepository.findAll();
			List<Par_bv_jenis_garansi> jenis_garansi = par_bv_jenis_garansiRepository.findAll();
			List<Par_bv_kode_kondisi> kode_kondisi = par_bv_kode_kondisiRepository.findAll();
			List<Par_sumber_dana_bi> sumber_dana_bi = par_sumber_dana_biRepository.findAll();
			List<Par_kode_sektor_ekonomi_bi> kode_sektor_ekonomi_bi = par_kode_sektor_ekonomi_biRepository.findAll();
			List<Par_gol_debitur_sid> gol_debitur_sid = par_gol_debitur_sidRepository.findAll();
			List<Par_jenis_penggunaan_bi> par_jenis_penggunaan_bi = par_jenis_penggunaan_biRepository.findAll();
			List<Par_bv_kategori_debitur> par_bv_kategori_debitur = par_bv_kategoriDebRepo.findAll();
			List<Par_bv_sifat_piutang> par_bv_sifat_piutang = par_bv_sifat_piutangRepository.findAll();
			List<Par_badan_hukum> par_badan_hukum = par_badan_hukumRepository.findAll();

			response.put("bagi_hasil", bagi_hasil);
			response.put("gol_pihak_lawan", gol_pihak_lawan);
			response.put("jenis_aset", jenis_aset);
			response.put("jenis_suku_imbalan", jenis_suku_imbalan);
			response.put("jenis_valuta", jenis_valuta);
			response.put("kategori_portofolio", kategori_portofolio);
			response.put("kategori_debitur", kategori_debitur);
			response.put("kualitas", kualitas);
			response.put("lembaga_pemeringkat", lembaga_pemeringkat);
			response.put("metode_amortisasi", metode_amortisasi);
			response.put("orientasi_penggunaan", orientasi_penggunaan);
			response.put("periode_sewa", periode_sewa);
			response.put("program_pemerintah", program_pemerintah);
			response.put("sandi_bi", sandi_bi);
			response.put("sektor_ekonomi", sektor_ekonomi);
			response.put("sektor_ekonomi_kur", sektor_ekonomi_kur);
			response.put("sifat_investasi", sifat_investasi);
			response.put("sifat_pembiayaan", sifat_pembiayaan);
			response.put("sumber_dana", sumber_dana);
			response.put("status_debitur", status_debitur);
			response.put("jenis_penggunaan", jenis_penggunaan);
			response.put("jenis_piutang", jenis_piutang);
			response.put("status_piutang", status_piutang);
			response.put("golongan_debitur", golongan_debitur);
			response.put("jenis_aktiva", jenis_aktiva);
			response.put("golongan_piutang", golongan_piutang);
			response.put("sifat_kredit_bi", sifat_kredit);
			response.put("bi_tujuan_kredit", tujuan_kredit);
			response.put("jenis_agunan", jenis_agunan);
			response.put("tujuan_garansi", tujuan_garansi);
			response.put("jenis_aktiva_ijarah", jenis_aktiva_ijarah);
			response.put("skim_pembiayaan", skim_pembiayaan);
			response.put("bagian_dijamin", bagian_dijamin);
			response.put("rating_pihak_lawan", rating_pihak_lawan);
			response.put("jenis_akad", jenis_akad);
			response.put("bi_status_piutang", bi_status_piutang);
			response.put("bi_sifat_akad", bi_sifat_akad);
			response.put("cara_restruktur", cara_restruktur);
			response.put("kode_sebab_macet", kode_sebab_macet);
			response.put("freq_restruktur", freq_restruktur);
			response.put("jenis_garansi", jenis_garansi);
			response.put("kode_kondisi", kode_kondisi);
			response.put("lokasi_pengguna", lokasi_pengguna);
			response.put("sumber_dana_bi", sumber_dana_bi);
			response.put("kode_sektor_ekonomi_bi", kode_sektor_ekonomi_bi);
			response.put("gol_debitur_sid", gol_debitur_sid);
			response.put("jenis_penggunaan_bi", par_jenis_penggunaan_bi);
			response.put("kategori_debitur", par_bv_kategori_debitur);
			response.put("sifat_piutang", par_bv_sifat_piutang);
			response.put("badan_hukum", par_badan_hukum);
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/mkm/parameter/getpardokumenpersyaratan/{id}")
	public ResponseEntity<Map<String, Object>> getpardokumenpersyaratan(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_ceklist> pardokumenpersyaratan = par_ceklistRepository.findListByIdTemplate(id);
			
			if (pardokumenpersyaratan.size()>0) {

				response.put("pardokumenpersyaratan", pardokumenpersyaratan);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/mkm/parameter/getparqaca/{id}")
	public ResponseEntity<Map<String, Object>> getparqaca(@PathVariable Integer id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_qaca_group> par_qaca_group = par_qaca_groupRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
			List<Par_qaca_detail> par_qaca_detail = par_qaca_detailRepository.findSemua(id);
			

			System.out.println("##### TAMPILKAN ALL ##### ");
			response.put("par_qaca_group", par_qaca_group);
			response.put("par_qaca_detail", par_qaca_detail);
			

			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
