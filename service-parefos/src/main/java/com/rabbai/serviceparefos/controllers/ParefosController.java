package com.rabbai.serviceparefos.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import com.rabbai.serviceparefos.models.*;
import com.rabbai.serviceparefos.repository.*;

@RestController
public class ParefosController {

	@Autowired
	Par_tujuan_pembiayaanRepository par_tujuan_pembiayaanRepository;
	@Autowired
	Par_agunan_bank_atau_nasabahRepository par_agunan_bank_atau_nasabahRepository;
	@Autowired
	Par_agunan_sifat_agunanRepository par_agunan_sifat_agunanRepository;
	@Autowired
	Par_agunan_basel2_jenis_agunanRepository par_agunan_basel2_jenis_agunanRepository;
	@Autowired
	Par_agunan_cakupan_kewajibanRepository par_agunan_cakupan_kewajibanRepository;
	@Autowired
	Par_agunan_cash_non_cashRepository par_agunan_cash_non_cashRepository;
	@Autowired
	Par_agunan_izin_pengkaitanRepository par_agunan_izin_pengkaitanRepository;
	@Autowired
	Par_agunan_jenis_agunan_biRepository par_agunan_jenis_agunan_biRepository;
	@Autowired
	Par_agunan_jenis_agunan_ppapRepository par_agunan_jenis_agunan_ppapRepository;
	@Autowired
	Par_agunan_jenis_agunanRepository par_agunan_jenis_agunanRepository;
	@Autowired
	Par_agunan_kegunaan_agunanRepository par_agunan_kegunaan_agunanRepository;
	@Autowired
	Par_agunan_kode_kanalRepository par_agunan_kode_kanalRepository;
	@Autowired
	Par_agunan_kode_pengikatan_internalRepository par_agunan_kode_pengikatan_internalRepository;
	@Autowired
	Par_agunan_kode_pengikatan_notarialRepository par_agunan_kode_pengikatan_notarialRepository;
	@Autowired
	Par_agunan_kode_tipe_dokumenRepository par_agunan_kode_tipe_dokumenRepository;
	@Autowired
	Par_agunan_penerbit_agunanRepository par_agunan_penerbit_agunanRepository;
	@Autowired
	Par_agunan_penilaian_olehRepository par_agunan_penilaian_olehRepository;
	@Autowired
	Par_agunan_prioritas_agunanRepository par_agunan_prioritas_agunanRepository;
	@Autowired
	Par_agunan_sifat_agunanRepository Par_agunan_sifat_agunanRepository;
	@Autowired
	Par_agunan_status_agunanRepository par_agunan_status_agunanRepository;
	@Autowired
	Par_agunan_status_akutansi_agunRepository par_agunan_status_akutansi_agunRepository;
	@Autowired
	Par_bv_kode_notarisRepository par_bv_kode_notarisRepository;
	@Autowired
	Par_agamaRepository par_agamaRepository;
	@Autowired
	Par_kode_marginRepository par_kode_marginRepository;
	@Autowired
	Par_kode_aset_ijarahRepository par_kode_aset_ijarahRepository;
	@Autowired
	Par_bi_status_piutangRepository2 par_bi_status_piutangRepository2;
	@Autowired
	Par_rating_pihaklawanRepository par_rating_pihaklawanRepository;
	@Autowired
	Par_lokasi_penggunaRepository par_lokasi_penggunaRepo;
	@Autowired
	Par_bagian_dijaminRepository par_bagian_dijaminRepository;
	@Autowired
	Par_jenis_aktiva_ijarahRepository par_jenis_aktiva_ijarahRepository;
	@Autowired
	Par_bv_jenis_agunanRepository par_bv_jenis_agunanRepository;
	@Autowired
	Par_bv_tujuan_garansiRepository par_bv_tujuan_garansiRepo;
	@Autowired
	Par_bv_karakteristik_akadRepository par_bv_karakteristik_akadRepository;
	@Autowired
	Par_bv_hubungan_produsenRepository par_bv_hubungan_produsenRepository;
	@Autowired
	Par_bv_jenis_asuransiRepository Par_bv_jenis_asuransiRepository;
	@Autowired
	Par_bv_terkaitRepository par_bv_terkaitRepository;
	@Autowired
	Par_bv_tujuan_kreditRepository par_bv_tujuan_kreditRepository;
	@Autowired
	Par_bv_sifat_kreditRepository par_bv_sifat_kreditRepository;
	@Autowired
	Par_bv_bagian_dijaminRepository par_bv_bagian_dijaminRepository;
	@Autowired
	Par_bv_golongan_piutangRepository par_bv_golongan_piutangRepository;
	@Autowired
	Par_bv_jenis_aktivaRepository par_bv_jenis_aktivaRepository;
	@Autowired
	Par_bv_golongan_debiturRepository par_bv_golongan_debiturRepository;
	@Autowired
	Par_bv_kode_kondisiRepository par_bv_kode_kondisiRepository;
	@Autowired
	Par_bv_sifat_akadRepository par_bv_sifat_akadRepository;
	@Autowired
	Par_bi_sifatAkadRepository par_bi_sifat_akadRepository;
	@Autowired
	Par_bv_status_piutangRepository par_bv_status_piutangRepository;
	@Autowired
	Par_bv_sumber_danaRepository par_bv_sumber_danaRepository;
	@Autowired
	Par_bv_sektor_ekonomiRepository par_bv_sektor_ekonomiRepository;
	@Autowired
	Par_bv_jenis_garansiRepository par_bv_jenis_garansiRepository;
	@Autowired
	Par_bv_kode_sebab_macetRepository par_bv_kode_sebab_macetRepository;
	@Autowired
	Par_bv_kode_frekuensi_restrukRepository par_bv_kode_frekuensi_restrukRepository;
	@Autowired
	Par_bv_kode_persentasemarginRepository par_bv_kode_persentasemarginRepository;
	@Autowired
	Par_bv_kode_program_pemerintahRepository par_bv_kode_program_pemerintahRepository;
	@Autowired
	Par_bidang_usahaRepository par_bidang_usahaRepository;
	@Autowired
	Par_dati2Repository par_dati2Repository;
	@Autowired
	Par_kebangsaanRepository par_kebangsaanRepository;
	@Autowired
	Par_kecRepository par_kecRepository;
	@Autowired
	Par_kelRepository par_kelRepository;
	@Autowired
	Par_kelaminRepository par_kelaminRepository;
	@Autowired
	Par_pendidikanRepository par_pendidikanRepository;
	@Autowired
	Par_profesiRepository par_profesiRepository;
	@Autowired
	Par_provinsiRepository par_provinsiRepository;
	@Autowired
	Par_status_perusahaanRepository par_status_perusahaanRepository;
	@Autowired
	Par_statusRepository par_statusRepository;
	@Autowired
	Par_planRepository par_planRepository;
	@Autowired
	Par_tempat_tinggalRepository par_tempat_tinggalRepository;
	@Autowired
	Par_akadRepository par_akadRepository;
	@Autowired
	Par_produkRepository par_produkRepository;
	@Autowired
	Par_sub_produkRepository par_sub_produkRepository;
	@Autowired
	Par_m_scoringRepository par_m_scoringRepository;
	@Autowired
	Par_rincian_scoringRepository par_rincian_scoringRepository;
	@Autowired
	Par_kategoriprodukRepository par_kategoriprodukRepository;
	@Autowired
	Par_ceklistRepository par_ceklistRepository;
	@Autowired
	LoanRepository loanRepository;
	@Autowired
	Par_dinasRepository par_dinasRepository;
	@Autowired
	Par_sub_dinasRepository par_sub_dinasRepository;
	@Autowired
	Par_sub_sub_dinasRepository par_sub_sub_dinasRepository;
	@Autowired
	Par_cabangRepository par_cabangRepository;
	@Autowired
	Par_template_dokumenRepository par_template_dokumenRepository;
	@Autowired
	Par_kategori_portofolioRepository par_bv_kateportfolioRepo;
	@Autowired
	Par_bagi_hasilRepository par_bagi_hasilRepository;
	@Autowired
	Par_gol_pihak_lawanRepository par_gol_pihak_lawanRepository;
	@Autowired
	Par_jenis_asetRepository par_jenis_asetRepository;
	@Autowired
	Par_jenis_suku_imbalanRepository par_jenis_suku_imbalanRepository;
	@Autowired
	Par_jenis_valutaRepository par_jenis_valutaRepository;
	@Autowired
	Par_kategori_portofolioRepository par_kategori_portofolioRepository;
	@Autowired
	Par_kategori_debiturRepository par_kategori_debiturRepository;
	@Autowired
	Par_kualitasRepository par_kualitasRepository;
	@Autowired
	Par_lembaga_pemeringkatRepository par_lembaga_pemeringkatRepository;
	@Autowired
	Par_metode_amortisasiRepository par_metode_amortisasiRepository;
	@Autowired
	Par_orientasi_penggunaanRepository par_orientasi_penggunaanRepository;
	@Autowired
	Par_periode_sewaRepository par_periode_sewaRepository;
	@Autowired
	Par_program_pemerintahRepository par_program_pemerintahRepository;
	@Autowired
	Par_sandi_biRepository par_sandi_biRepository;
	@Autowired
	Par_sektor_ekonomiRepository par_sektor_ekonomiRepository;
	@Autowired
	Par_sektor_ekonomi_kurRepository par_sektor_ekonomi_kurRepository;
	@Autowired
	Par_sifat_investasiRepository par_sifat_investasiRepository;
	@Autowired
	Par_sifat_pembiayaanRepository par_sifat_pembiayaanRepository;
	@Autowired
	Par_skim_pembiayaanRepository par_skim_pembiayaanRepository;
	@Autowired
	Par_sumber_danaRepository par_sumber_danaRepository;
	@Autowired
	Par_brokerRepository par_brokerRepository;
	@Autowired
	Par_asuRepository par_asuRepository;
	@Autowired
	Par_jns_agunanRepository par_jns_agunanRepository;
	@Autowired
	Par_qaca_groupRepository par_qaca_groupRepository;
	@Autowired
	Par_qaca_detailRepository par_qaca_detailRepository;
	@Autowired
	Par_bv_status_debiturRepository par_bv_statusDebiturRepo;
	@Autowired
	Par_bv_kategori_debiturRepository par_bv_kategoriDebRepo;
	@Autowired
	Par_bv_jenis_penggunaanRepository par_bv_jenisPenggunaanRepo;
	@Autowired
	Par_bv_orientasi_penggunaanRepository par_bv_orientasiRepo;
	@Autowired
	Par_bv_sifat_piutangRepository par_bv_sifat_piutangRepo;
	@Autowired
	Par_bv_jenis_piutangRepository par_bv_jenis_piutangRepo;
	@Autowired
	Par_bv_jenis_akadRepository par_jenis_akadRepo;
	@Autowired
	Par_bi_sifatAkadRepository par_bi_sifat_akad;
	@Autowired
	Par_bv_cara_restrukRepository par_bv_cara_restrukRepository;
	@Autowired
	Par_bv_takeover_dariRepository par_bv_takeover_dariRepository;
	@Autowired
	Par_code_officerRepository par_code_officerRepository;
	@Autowired
	Par_kode_referalRepository par_kode_referalRepository;
	@Autowired
	Par_sumber_dana_biRepository par_sumber_dana_biRepository;
	@Autowired
	Par_kode_sektor_ekonomi_biRepository par_kode_sektor_ekonomi_biRepository;
	@Autowired
	Par_gol_debitur_sidRepository par_gol_debitur_sidRepository;
	@Autowired
	Par_jenis_penggunaan_biRepository par_jenis_penggunaan_biRepository;
	@Autowired
	Par_bv_sifat_piutangRepository par_bv_sifat_piutangRepository;
	@Autowired
	Par_badan_hukumRepository par_badan_hukumRepository;
	Logger LOGGER = LoggerFactory.getLogger(ParefosController.class);

	@GetMapping("/parameter/inquiryall/{cabang}/{jenis_pengajuans}")
	public ResponseEntity<Map<String, Object>> getAllParam(@PathVariable String cabang,
			@PathVariable String jenis_pengajuans, @AuthenticationPrincipal Jwt jwt)
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
			Integer  jenis_pengajuan = Integer.parseInt(jenis_pengajuans);

//			List<Par_dinas> par_dinas = par_dinasRepository.findByIdCabang(cabang);
			List<Par_cabang> par_cabang = par_cabangRepository.findAll();
			List<Par_kode_aset_ijarah> kode_aset_ijarah = par_kode_aset_ijarahRepository.findAll();
			List<Par_tujuan_pembiayaan> tujuan_pembiayaan = par_tujuan_pembiayaanRepository
					.findByIdTemp(jenis_pengajuan);
			List<Par_code_officer> code_officer = par_code_officerRepository.findByCabang(cabang);
			List<Par_kode_referal> kode_referal = par_kode_referalRepository.findAll();

			List<Par_rincian_scoring> append_scoring1 = new ArrayList<>();
			List<Par_rincian_scoring> append_scoring2 = new ArrayList<>();
			List<Par_rincian_scoring> append_scoring3 = new ArrayList<>();
			List<Par_rincian_scoring> append_scoring4 = new ArrayList<>();

			if (jenis_pengajuan.equals(5)) { /*IF PKB */
				append_scoring1 = par_rincian_scoringRepository.findByIdMScoringAndType("133", jenis_pengajuan);
				append_scoring2 = par_rincian_scoringRepository.findByIdMScoringAndType("134", jenis_pengajuan);
				append_scoring3 = par_rincian_scoringRepository.findByIdMScoringAndType("143", jenis_pengajuan);
				append_scoring4 = par_rincian_scoringRepository.findByIdMScoringAndType("144", jenis_pengajuan);
			} else if (jenis_pengajuan.equals(6)) {
				append_scoring1 = par_rincian_scoringRepository.findByIdMScoringAndType("105", jenis_pengajuan);
				append_scoring2 = par_rincian_scoringRepository.findByIdMScoringAndType("106", jenis_pengajuan);
				append_scoring3 = par_rincian_scoringRepository.findByIdMScoringAndType("115", jenis_pengajuan);
				append_scoring4 = par_rincian_scoringRepository.findByIdMScoringAndType("116", jenis_pengajuan);
			} else if (jenis_pengajuan.equals(8)) {
				append_scoring1 = par_rincian_scoringRepository.findByIdMScoringAndType("119", jenis_pengajuan);
				append_scoring2 = par_rincian_scoringRepository.findByIdMScoringAndType("120", jenis_pengajuan);
				append_scoring3 = par_rincian_scoringRepository.findByIdMScoringAndType("129", jenis_pengajuan);
				append_scoring4 = par_rincian_scoringRepository.findByIdMScoringAndType("130", jenis_pengajuan);
			}

			response.put("take_over_dari", take_over_dari);

			System.out.println("##### TAMPILKAN ALL ##### ");
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
			response.put("cabang", par_cabang);
			response.put("kode_referal", kode_referal);
			response.put("code_officer", code_officer);
			response.put("append_scoring1", append_scoring1);
			response.put("append_scoring2", append_scoring2);
			response.put("append_scoring3", append_scoring3);
			response.put("append_scoring4", append_scoring4);

			response.put("kode", "00");
			response.put("pesan", "BERHASIL");

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/inquiryallsandibi/{cabang}")
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

			System.out.println("##### TAMPILKAN ALL ##### ");
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

	@GetMapping("/parameter/inquiryallagunan/{cabang}")
	public ResponseEntity<Map<String, Object>> inquiryallagunan(@PathVariable String cabang,
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

	@PostMapping("/parameter/agama/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listAgama(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_agama> agama = new ArrayList<Par_agama>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_agama> pageInsts;

			if (keyword != null) {
				pageInsts = par_agamaRepository.findKeywordWithPagination(paging, keyword);
//                    Page<Par_agama> pageAll = par_agamaRepository.findAllWithPagination(paging);
				filtered = par_agamaRepository.getCount();
			} else {
				pageInsts = par_agamaRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			agama = pageInsts.getContent();

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("agama", agama);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/parameter/agama/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getAgama(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_agama par = par_agamaRepository.findByIdAgama(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN AGAMA ##### ");
				response.put("agama", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/agama/inquiryall")
	public ResponseEntity<Map<String, Object>> getAgamaAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_agama> par = par_agamaRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
			if (par != null) {
				System.out.println("##### TAMPILKAN AGAMA ##### ");
				response.put("agama", par);
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

	@PostMapping("/parameter/agama/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addAgama(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		String userid = param.get("userid");

		try {
			Par_agama exist = par_agamaRepository.findByIdAgama(id);
			if (exist == null) {
				Par_agama par = new Par_agama();
				par.setId(id);
				par.setName(name);
				par.setKet("Created");
				par.setUserid(userid);
				par.setDatepost(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");

				par_agamaRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/agama/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateAgama(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		String userid = param.get("userid");
		try {
			Optional<Par_agama> exist = par_agamaRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_agama par = par_agamaRepository.findByIdAgama(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setUserid(userid);
				par.setDatepost_update(LocalDateTime.now());
				par.setKet("Updated");
				System.out.println("##### UPDATE ##### ");
				par_agamaRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/agama/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteAgama(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_agama par = par_agamaRepository.findByIdAgama(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_agamaRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END AGAMA

	@PostMapping("/parameter/bidang/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listBidang(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_bidang_usaha> bidang = new ArrayList<Par_bidang_usaha>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_bidang_usaha> pageInsts;

			if (keyword != null) {
				pageInsts = par_bidang_usahaRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_bidang_usahaRepository.getCount();
			} else {
				pageInsts = par_bidang_usahaRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			bidang = pageInsts.getContent();

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("bidang_usaha", bidang);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/parameter/bidang/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getBidang(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			Par_bidang_usaha par = par_bidang_usahaRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN BIDANG USAHA ##### ");
				response.put("bidang_usaha", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/bidang/inquiryall")
	public ResponseEntity<Map<String, Object>> getBidangAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bidang_usaha> par = par_bidang_usahaRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN BIDANG USAHA ##### ");
				response.put("bidang_usaha", par);
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

	@PostMapping("/parameter/bidang/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addBidang(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String userid = param.get("userid");

		try {
			Par_bidang_usaha exist = par_bidang_usahaRepository.findBySingleId(id);
			if (exist == null) {
				Par_bidang_usaha par = new Par_bidang_usaha();
				par.setId(id);
				par.setName(name);
				par.setKet("Created");
				par.setUserid(userid);
				par.setDatepost(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");

				par_bidang_usahaRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/bidang/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateBidang(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String userid = param.get("userid");
		try {
			Optional<Par_bidang_usaha> exist = par_bidang_usahaRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_bidang_usaha par = par_bidang_usahaRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setUserid(userid);
				par.setDatepost_update(LocalDateTime.now());
				par.setKet("Updated");
				System.out.println("##### UPDATE ##### ");
				par_bidang_usahaRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/bidang/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteAgama(@PathVariable String id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {

		Map<String, Object> response = new HashMap<>();
		try {
			Par_bidang_usaha par = par_bidang_usahaRepository.findBySingleId(id);
			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_bidang_usahaRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ######################################################################################################################
	// END BIDANG
	@PostMapping("/parameter/dati/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listDati(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_dati2> dati = new ArrayList<Par_dati2>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_dati2> pageInsts;

			if (keyword != null) {
				pageInsts = par_dati2Repository.findKeywordWithPagination(paging, keyword);
				filtered = par_dati2Repository.getCount();
			} else {
				pageInsts = par_dati2Repository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			dati = pageInsts.getContent();

			System.out.println("Par " + dati);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("dati", dati);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/parameter/dati/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getDati(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_dati2 par = par_dati2Repository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN DATI2 ##### ");
				response.put("dati", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/dati/inquirybyidprovinsi")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getDatiByIdProvinsi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id_provinsi = Integer.parseInt(param.get("id_provinsi"));
		try {
			List<Par_dati2> par = par_dati2Repository.findByIdProvinsi(id_provinsi);
			if (par != null) {
				System.out.println("##### TAMPILKAN DATI2 ##### ");
				response.put("dati", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id_provinsi + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/dati/inquiryall")
	public ResponseEntity<Map<String, Object>> getDatiAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_dati2> par = par_dati2Repository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN DATI2 ##### ");
				response.put("dati", par);
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

	@PostMapping("/parameter/dati/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addDati(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		Integer id_provinsi = Integer.parseInt(param.get("id_provinsi"));
		Integer id_bi = Integer.parseInt(param.get("id_bi"));
		String name = param.get("name");
		String userid = param.get("userid");

		try {
			Par_dati2 exist = par_dati2Repository.findBySingleId(id);
			if (exist == null) {
				Par_dati2 par = new Par_dati2();
				par.setId(id);
				par.setId_provinsi(id_provinsi);
				par.setId_bi(id_bi);
				par.setName(name);
				par.setKet("Created");
				par.setUserid(userid);
				par.setDatepost(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");

				par_dati2Repository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/dati/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateDati(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		Integer id_provinsi = Integer.parseInt(param.get("id_provinsi"));
		Integer id_bi = Integer.parseInt(param.get("id_bi"));
		String name = param.get("name");
		String userid = param.get("userid");
		try {
			Optional<Par_dati2> exist = par_dati2Repository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_dati2 par = par_dati2Repository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setId_provinsi(id_provinsi);
				par.setId_bi(id_bi);
				par.setName(name);
				par.setUserid(userid);
				par.setDatepost_update(LocalDateTime.now());
				par.setKet("Updated");
				System.out.println("##### UPDATE ##### ");
				par_dati2Repository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/dati/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteDati(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {

		Par_dati2 par = par_dati2Repository.findBySingleId(id);
		Map<String, Object> response = new HashMap<>();

		if (par == null) {
			response.put("kode", "10");
			response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
		} else {
//        	par_dati2Repository.delete(par);
			par_dati2Repository.deleteBySingleId(id);
			response.put("kode", "00");
			response.put("pesan", "BERHASIL DI HAPUS");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END DATI

	@PostMapping("/parameter/kebangsaan/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listKebangsaan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_kebangsaan> kebangsaan = new ArrayList<Par_kebangsaan>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_kebangsaan> pageInsts;

			if (keyword != null) {
				pageInsts = par_kebangsaanRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_kebangsaanRepository.getCount();
			} else {
				pageInsts = par_kebangsaanRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			kebangsaan = pageInsts.getContent();
			System.out.println("Par " + kebangsaan);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("kebangsaan", kebangsaan);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/parameter/kebangsaan/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getKebangsaan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_kebangsaan par = par_kebangsaanRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN KEBANGSAAN ##### ");
				response.put("kebangsaan", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/kebangsaan/inquiryall")
	public ResponseEntity<Map<String, Object>> getKebangsaanAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_kebangsaan> par = par_kebangsaanRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN KEBANGSAAN ##### ");
				response.put("kebangsaan", par);
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

	@PostMapping("/parameter/kebangsaan/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addKebangsaan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		String userid = param.get("userid");

		try {
			Par_kebangsaan exist = par_kebangsaanRepository.findBySingleId(id);
			if (exist == null) {
				Par_kebangsaan par = new Par_kebangsaan();
				par.setId(id);
				par.setName(name);
				par.setKet("Created");
				par.setUserid(userid);
				par.setDatepost(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");

				par_kebangsaanRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/kebangsaan/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateKebangsaan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		String userid = param.get("userid");
		try {
			Optional<Par_kebangsaan> exist = par_kebangsaanRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_kebangsaan par = par_kebangsaanRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setUserid(userid);
				par.setDatepost_update(LocalDateTime.now());
				par.setKet("Updated");
				System.out.println("##### UPDATE ##### ");
				par_kebangsaanRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/kebangsaan/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteKebangsaan(@PathVariable Integer id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_kebangsaan par = par_kebangsaanRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_kebangsaanRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END KEBANGSAAN

	@PostMapping("/parameter/kec/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listKec(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_kec> kec = new ArrayList<Par_kec>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_kec> pageInsts;

			if (keyword != null) {
				pageInsts = par_kecRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_kecRepository.getCount();
			} else {
				pageInsts = par_kecRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			kec = pageInsts.getContent();
			System.out.println("Par " + kec);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("kecamatan", kec);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/kec/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getKec(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_kec par = par_kecRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN KECAMATAN ##### ");
				response.put("kecamatan", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/kec/inquirybyidkabupaten")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getKecByIdKab(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			List<Par_kec> par = par_kecRepository.findByIdKab(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN KECAMATAN ##### ");
				response.put("kecamatan", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/kec/inquiryall")
	public ResponseEntity<Map<String, Object>> getKecAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_kec> par = par_kecRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN KECAMATAN ##### ");
				response.put("kecamatan", par);
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

	@PostMapping("/parameter/kec/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addKec(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		Integer id_dati2 = Integer.parseInt(param.get("id_dati2"));
		String name = param.get("name");
		String userid = param.get("userid");

		try {
			Par_kec exist = par_kecRepository.findBySingleId(id);
			if (exist == null) {
				Par_kec par = new Par_kec();
				par.setId(id);
				par.setId_dati2(id_dati2);
				par.setName(name);
				par.setKet("Created");
				par.setUserid(userid);
				par.setDatepost(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");

				par_kecRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/kec/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateKec(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		Integer id_dati2 = Integer.parseInt(param.get("id_dati2"));
		String name = param.get("name");
		String userid = param.get("userid");
		try {
			Optional<Par_kec> exist = par_kecRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_kec par = par_kecRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setId_dati2(id_dati2);
				par.setName(name);
				par.setUserid(userid);
				par.setDatepost_update(LocalDateTime.now());
				par.setKet("Updated");
				System.out.println("##### UPDATE ##### ");
				par_kecRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/kec/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteKec(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_kec par = par_kecRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
//        	par_kecRepository.delete(par);
				par_kecRepository.deleteBySingleId(id);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END KEC

	@PostMapping("/parameter/kel/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listKel(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_kel> kel = new ArrayList<Par_kel>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_kel> pageInsts;

			if (keyword != null) {
				pageInsts = par_kelRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_kelRepository.getCount();
			} else {
				pageInsts = par_kelRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			kel = pageInsts.getContent();
			System.out.println("Par " + kel);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("kelurahan", kel);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/kel/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getKel(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Long id = Long.valueOf(param.get("id"));
		try {
			Par_kel par = par_kelRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN KELURAHAN ##### ");
				response.put("kelurahan", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/kel/inquirybyidkecamatan")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getKelByIdKec(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			List<Par_kel> par = par_kelRepository.findByIdKel(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN KELURAHAN ##### ");
				response.put("kelurahan", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/kel/inquiryall")
	public ResponseEntity<Map<String, Object>> getKelAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_kel> par = par_kelRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN KELURAHAN ##### ");
				response.put("kelurahan", par);
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

	@PostMapping("/parameter/kel/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addKel(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Long id = Long.valueOf(param.get("id"));
		Integer id_kec = Integer.parseInt(param.get("id_kec"));
		String name = param.get("name");
		String userid = param.get("userid");

		try {
			Par_kel exist = par_kelRepository.findBySingleId(id);
			if (exist == null) {
				Par_kel par = new Par_kel();
				par.setId(id);
				par.setId_kec(id_kec);
				par.setName(name);
				par.setKet("Created");
				par.setUserid(userid);
				par.setDatepost(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");

				par_kelRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/kel/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateKel(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Long id = Long.valueOf(param.get("id"));
		Integer id_kec = Integer.parseInt(param.get("id_kec"));
		String name = param.get("name");
		String userid = param.get("userid");
		try {
			Optional<Par_kel> exist = par_kelRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_kel par = par_kelRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setId_kec(id_kec);
				par.setName(name);
				par.setUserid(userid);
				par.setDatepost_update(LocalDateTime.now());
				par.setKet("Updated");
				System.out.println("##### UPDATE ##### ");
				par_kelRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/kel/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteKel(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_kel par = par_kelRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_kelRepository.deleteBySingleId(id);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/kelamin/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listKelamin(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_kelamin> kelamin = new ArrayList<Par_kelamin>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_kelamin> pageInsts;

			if (keyword != null) {
				pageInsts = par_kelaminRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_kelaminRepository.getCount();
			} else {
				pageInsts = par_kelaminRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			kelamin = pageInsts.getContent();
			System.out.println("Par " + kelamin);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("kelamin", kelamin);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/parameter/kelamin/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getKelamin(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_kelamin par = par_kelaminRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN KELAMIN ##### \n");
				response.put("kelamin", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/kelamin/inquiryall")
	public ResponseEntity<Map<String, Object>> getKelaminAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_kelamin> par = par_kelaminRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN KELAMIN ##### ");
				response.put("kelamin", par);
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

	@PostMapping("/parameter/kelamin/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addKelamin(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		String userid = param.get("userid");

		try {
			Par_kelamin exist = par_kelaminRepository.findBySingleId(id);
			if (exist == null) {
				Par_kelamin par = new Par_kelamin();
				par.setId(id);
				par.setName(name);
				par.setKet("Created");
				par.setUserid(userid);
				par.setDatepost(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");

				par_kelaminRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/kelamin/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateKelamin(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		String userid = param.get("userid");
		try {
			Optional<Par_kelamin> exist = par_kelaminRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_kelamin par = par_kelaminRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setUserid(userid);
				par.setDatepost_update(LocalDateTime.now());
				par.setKet("Updated");
				System.out.println("##### UPDATE ##### ");
				par_kelaminRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/kelamin/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteKelamin(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_kelamin par = par_kelaminRepository.findBySingleId(id);
			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_kelaminRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ######################################################################################################################
	// END KELAMIN

	@PostMapping("/parameter/pendidikan/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listPendidikan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_pendidikan> pendidikan = new ArrayList<Par_pendidikan>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_pendidikan> pageInsts;

			if (keyword != null) {
				pageInsts = par_pendidikanRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_pendidikanRepository.getCount();
			} else {
				pageInsts = par_pendidikanRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			pendidikan = pageInsts.getContent();
			System.out.println("Par " + pendidikan);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("pendidikan", pendidikan);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/parameter/pendidikan/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getPendidikan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_pendidikan par = par_pendidikanRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN PENDIDIKAN ##### \n");
				response.put("pendidikan", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/pendidikan/inquiryall")
	public ResponseEntity<Map<String, Object>> getPendidikanAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_pendidikan> par = par_pendidikanRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN PENDIDIKAN ##### ");
				response.put("pendidikan", par);
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

	@PostMapping("/parameter/pendidikan/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addPendidikan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		String userid = param.get("userid");

		try {
			Par_pendidikan exist = par_pendidikanRepository.findBySingleId(id);
			if (exist == null) {
				Par_pendidikan par = new Par_pendidikan();
				par.setId(id);
				par.setName(name);
				par.setKet("Created");
				par.setUserid(userid);
				par.setDatepost(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");

				par_pendidikanRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/pendidikan/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatePendidikan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		String userid = param.get("userid");
		try {
			Optional<Par_pendidikan> exist = par_pendidikanRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_pendidikan par = par_pendidikanRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setUserid(userid);
				par.setDatepost_update(LocalDateTime.now());
				par.setKet("Updated");
				System.out.println("##### UPDATE ##### ");
				par_pendidikanRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/pendidikan/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletePendidikan(@PathVariable Integer id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_pendidikan par = par_pendidikanRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_pendidikanRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ######################################################################################################################
	// END PENDIDIKAN

	@PostMapping("/parameter/profesi/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listProfesi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_profesi> profesi = new ArrayList<Par_profesi>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_profesi> pageInsts;

			if (keyword != null) {
				pageInsts = par_profesiRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_profesiRepository.getCount();
			} else {
				pageInsts = par_profesiRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			profesi = pageInsts.getContent();
			System.out.println("Par " + profesi);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("profesi", profesi);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/parameter/profesi/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getProfesi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			Par_profesi par = par_profesiRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN PROFESI ##### \n");
				response.put("profesi", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/profesi/inquiryall")
	public ResponseEntity<Map<String, Object>> getProfesiAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_profesi> par = par_profesiRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN PROFESI ##### ");
				response.put("profesi", par);
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

	@PostMapping("/parameter/profesi/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addProfesi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String userid = param.get("userid");

		try {
			Par_profesi exist = par_profesiRepository.findBySingleId(id);
			if (exist == null) {
				Par_profesi par = new Par_profesi();
				par.setId(id);
				par.setName(name);
				par.setKet("Created");
				par.setUserid(userid);
				par.setDatepost(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");

				par_profesiRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/profesi/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateProfesi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String userid = param.get("userid");
		try {
			Optional<Par_profesi> exist = par_profesiRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_profesi par = par_profesiRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setUserid(userid);
				par.setDatepost_update(LocalDateTime.now());
				par.setKet("Updated");
				System.out.println("##### UPDATE ##### ");
				par_profesiRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/profesi/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteProfesi(@PathVariable String id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_profesi par = par_profesiRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_profesiRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ######################################################################################################################
	// END PROFESI

	@PostMapping("/parameter/provinsi/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listProvinsi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_provinsi> provinsi = new ArrayList<Par_provinsi>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_provinsi> pageInsts;

			if (keyword != null) {
				pageInsts = par_provinsiRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_provinsiRepository.getCount();
			} else {
				pageInsts = par_provinsiRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			provinsi = pageInsts.getContent();
			System.out.println("Par " + provinsi);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("provinsi", provinsi);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/parameter/provinsi/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getProvinsi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_provinsi par = par_provinsiRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN PROVINSI ##### \n");
				response.put("provinsi", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/provinsi/inquiryall")
	public ResponseEntity<Map<String, Object>> getProvinsiAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_provinsi> par = par_provinsiRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN PROVINSI ##### ");
				response.put("provinsi", par);
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

	@PostMapping("/parameter/provinsi/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addProvinsi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		String userid = param.get("userid");

		try {
			Par_provinsi exist = par_provinsiRepository.findBySingleId(id);
			if (exist == null) {
				Par_provinsi par = new Par_provinsi();
				par.setId(id);
				par.setName(name);
				par.setKet("Created");
				par.setUserid(userid);
				par.setDatepost(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");

				par_provinsiRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/provinsi/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateProvinsi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		String userid = param.get("userid");
		try {
			Optional<Par_provinsi> exist = par_provinsiRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_provinsi par = par_provinsiRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setUserid(userid);
				par.setDatepost_update(LocalDateTime.now());
				par.setKet("Updated");
				System.out.println("##### UPDATE ##### ");
				par_provinsiRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/provinsi/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteProvinsi(@PathVariable Integer id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {

			Par_provinsi par = par_provinsiRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_provinsiRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ######################################################################################################################
	// END PROVINSI

	@PostMapping("/parameter/statusperusahaan/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> liststatusperusahaan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_status_perusahaan> status_perusahaan = new ArrayList<Par_status_perusahaan>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_status_perusahaan> pageInsts;

			if (keyword != null) {
				pageInsts = par_status_perusahaanRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_status_perusahaanRepository.getCount();
			} else {
				pageInsts = par_status_perusahaanRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			status_perusahaan = pageInsts.getContent();
			System.out.println("Par " + status_perusahaan);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("status_perusahaan", status_perusahaan);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/parameter/statusperusahaan/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getstatusperusahaan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			Par_status_perusahaan par = par_status_perusahaanRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN STATUS PERUSAHAAN ##### \n");
				response.put("status_perusahaan", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/statusperusahaan/inquiryall")
	public ResponseEntity<Map<String, Object>> getstatusperusahaan(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_status_perusahaan> par = par_status_perusahaanRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN STATUS PERUSAHAAN ##### ");
				response.put("status_perusahaan", par);
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

	@PostMapping("/parameter/statusperusahaan/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addstatusperusahaan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String userid = param.get("userid");

		try {
			Par_status_perusahaan exist = par_status_perusahaanRepository.findBySingleId(id);
			if (exist == null) {
				Par_status_perusahaan par = new Par_status_perusahaan();
				par.setId(id);
				par.setName(name);
				par.setKet("Created");
				par.setUserid(userid);
				par.setDatepost(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");

				par_status_perusahaanRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/statusperusahaan/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatestatus_perusahaan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String userid = param.get("userid");
		try {
			Optional<Par_status_perusahaan> exist = par_status_perusahaanRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_status_perusahaan par = par_status_perusahaanRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setUserid(userid);
				par.setDatepost_update(LocalDateTime.now());
				par.setKet("Updated");
				System.out.println("##### UPDATE ##### ");
				par_status_perusahaanRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/statusperusahaan/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletestatusperusahaan(@PathVariable String id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_status_perusahaan par = par_status_perusahaanRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_status_perusahaanRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ######################################################################################################################
	// END STATUS PERUSAHAAN

	@PostMapping("/parameter/status/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> liststatus(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_status> status = new ArrayList<Par_status>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_status> pageInsts;

			if (keyword != null) {
				pageInsts = par_statusRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_statusRepository.getCount();
			} else {
				pageInsts = par_statusRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			status = pageInsts.getContent();
			System.out.println("Par " + status);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("status", status);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/parameter/status/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getstatus(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			Par_status par = par_statusRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN STATUS ##### \n");
				response.put("status", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/status/inquiryall")
	public ResponseEntity<Map<String, Object>> getstatus(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_status> par = par_statusRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN STATUS ##### ");
				response.put("status", par);
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

	@PostMapping("/parameter/status/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addstatus(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String userid = param.get("userid");

		try {
			Par_status exist = par_statusRepository.findBySingleId(id);
			if (exist == null) {
				Par_status par = new Par_status();
				par.setId(id);
				par.setName(name);
				par.setKet("Created");
				par.setUserid(userid);
				par.setDatepost(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");

				par_statusRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/status/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatestatus(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String userid = param.get("userid");
		try {
			Optional<Par_status> exist = par_statusRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_status par = par_statusRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setUserid(userid);
				par.setDatepost_update(LocalDateTime.now());
				par.setKet("Updated");
				System.out.println("##### UPDATE ##### ");
				par_statusRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/status/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletestatus(@PathVariable String id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_status par = par_statusRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_statusRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ######################################################################################################################
	// END STATUS

	@PostMapping("/parameter/plan/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listplan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_plan> plan = new ArrayList<Par_plan>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_plan> pageInsts;

			if (keyword != null) {
				pageInsts = par_planRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_planRepository.getCount();
			} else {
				pageInsts = par_planRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			plan = pageInsts.getContent();
			System.out.println("Par " + plan);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("plan", plan);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/plan/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getplan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_plan par = par_planRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN PLAN ##### ");
				response.put("plan", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/plan/inquirybyidsubproduk")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getplanbyidsubproduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id_subproduk = Integer.parseInt(param.get("id_subproduk"));
		Integer id_template_dokumen = Integer.parseInt(param.get("id_template_dokumen"));
		try {
			List<Par_plan> par = par_planRepository.findByIdSubProduk(id_subproduk, id_template_dokumen);
			if (par != null) {
				System.out.println("##### TAMPILKAN plan ##### ");
				response.put("plan", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id Sub Produk " + id_subproduk + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/plan/inquiryall")
	public ResponseEntity<Map<String, Object>> getplanAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_plan> par = par_planRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN plan ##### ");
				response.put("plan", par);
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

	@PostMapping("/parameter/plan/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addplan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		Integer id_akad = Integer.parseInt(param.get("id_akad"));
		Integer id_sub_produk = Integer.parseInt(param.get("id_sub_produk"));
		Integer id_template_dokumen = Integer.parseInt(param.get("id_template_dokumen"));
		String name = param.get("name");
		String template_notisi = param.get("template_notisi");

		try {
			Par_plan exist = par_planRepository.findBySingleId(id);
			if (exist == null) {
				Par_plan par = new Par_plan();
				par.setId(id);
				par.setId_akad(id_akad);
				par.setId_sub_produk(id_sub_produk);
				par.setName(name);
				par.setId_template_dokumen(id_template_dokumen);
				par.setTemplate_notisi(template_notisi);

				System.out.println("##### SAVE ##### ");

				par_planRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Plan " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/plan/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateplan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		Integer id_akad = Integer.parseInt(param.get("id_akad"));
		Integer id_sub_produk = Integer.parseInt(param.get("id_sub_produk"));
		Integer id_template_dokumen = Integer.parseInt(param.get("id_template_dokumen"));
		String name = param.get("name");
		String template_notisi = param.get("template_notisi");
		try {
			Optional<Par_plan> exist = par_planRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_plan par = par_planRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setId_akad(id_akad);
				par.setId_sub_produk(id_sub_produk);
				par.setName(name);
				par.setId_template_dokumen(id_template_dokumen);
				par.setTemplate_notisi(template_notisi);
				System.out.println("##### UPDATE ##### ");
				par_planRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Plan " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/plan/delete/{plan}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteplan(@PathVariable Integer plan, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_plan par = par_planRepository.findBySingleId(plan);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_planRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END plan

	@PostMapping("/parameter/akad/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listakad(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_akad> akad = new ArrayList<Par_akad>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_akad> pageInsts;

			if (keyword != null) {
				pageInsts = par_akadRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_akadRepository.getCount();
			} else {
				pageInsts = par_akadRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			akad = pageInsts.getContent();
			System.out.println("Par " + akad);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("akad", akad);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/akad/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getakad(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_akad par = par_akadRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN AKAD ##### ");
				response.put("akad", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/akad/inquiryall")
	public ResponseEntity<Map<String, Object>> getakadAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_akad> par = par_akadRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN AKAD ##### ");
				response.put("akad", par);
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

	@PostMapping("/parameter/akad/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addakad(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");

		try {
			Par_akad exist = par_akadRepository.findBySingleId(id);
			if (exist == null) {
				Par_akad par = new Par_akad();
				par.setId(id);
				par.setName(name);

				System.out.println("##### SAVE ##### ");

				par_akadRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Akad " + name + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/akad/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateakad(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		try {
			Optional<Par_akad> exist = par_akadRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_akad par = par_akadRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				System.out.println("##### UPDATE ##### ");
				par_akadRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/akad/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteakad(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_akad par = par_akadRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_akadRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END AKAD

	@PostMapping("/parameter/produk/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listproduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_produk> produk = new ArrayList<Par_produk>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_produk> pageInsts;

			if (keyword != null) {
				pageInsts = par_produkRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_produkRepository.getCount();
			} else {
				pageInsts = par_produkRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			produk = pageInsts.getContent();
			System.out.println("Par " + produk);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("produk", produk);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/produk/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getproduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_produk par = par_produkRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN PRODUK ##### ");
				response.put("produk", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/produk/inquirybyidkategoriproduk")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> inquirybyidkategoriproduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			List<Par_produk> par = par_produkRepository.findByIdKategoriProduk(id);
			if (par.size() > 0) {
				System.out.println("##### TAMPILKAN KATEGORI PRODUK ##### ");
				response.put("produk", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/produk/inquiryall")
	public ResponseEntity<Map<String, Object>> getprodukAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_produk> par = par_produkRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN PRODUK ##### ");
				response.put("produk", par);
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

	@PostMapping("/parameter/produk/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addproduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		Integer id_kategori_produk = Integer.parseInt(param.get("id_kategori_produk"));
		String name = param.get("name");

		try {
			Par_produk exist = par_produkRepository.findBySingleId(id);
			if (exist == null) {
				Par_produk par = new Par_produk();
				par.setId(id);
				par.setName(name);
				par.setId_kategori_produk(id_kategori_produk);

				System.out.println("##### SAVE ##### ");

				par_produkRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/produk/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateproduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		Integer id_kategori_produk = Integer.parseInt(param.get("id_kategori_produk"));
		String name = param.get("name");
		try {
			Optional<Par_produk> exist = par_produkRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_produk par = par_produkRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setId_kategori_produk(id_kategori_produk);
				System.out.println("##### UPDATE ##### ");
				par_produkRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/produk/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteproduk(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_produk par = par_produkRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_produkRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/subproduk/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listsubproduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_sub_produk> sub_produk = new ArrayList<Par_sub_produk>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_sub_produk> pageInsts;

			if (keyword != null) {
				pageInsts = par_sub_produkRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_sub_produkRepository.getCount();
			} else {
				pageInsts = par_sub_produkRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			sub_produk = pageInsts.getContent();
			System.out.println("Par " + sub_produk);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("subproduk", sub_produk);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/subproduk/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getsubproduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_sub_produk par = par_sub_produkRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN SUB PRODUK ##### ");
				response.put("subproduk", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/subproduk/inquirybyidproduk")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getsubprodukbyidproduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /parameter/subproduk/inquirybyidproduk ************** "
				+ "@ " + objectMapper.writeValueAsString(param) + " \n");
		Integer id_produk = Integer.parseInt(param.get("id_produk"));
		Integer id_template = Integer.parseInt(param.get("id_template_dokumen"));
		try {
			List<Par_sub_produk> par = par_sub_produkRepository.findByIdProdukandTemplate(id_produk, id_template);
			if (par.size() > 0) {
				System.out.println("##### TAMPILKAN SUB PRODUK ##### ");
				response.put("subproduk", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id Produk " + id_produk + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		LOGGER.info("\n ************** RESPONSE FROM FRONTEND /parameter/subproduk/inquirybyidproduk ************** "
				+ "@ " + objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/subproduk/inquiryall")
	public ResponseEntity<Map<String, Object>> getsubprodukAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_sub_produk> par = par_sub_produkRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN SUB PRODUK ##### ");
				response.put("subproduk", par);
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

	@PostMapping("/parameter/subproduk/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addsubproduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		Integer id_produk = Integer.parseInt(param.get("id_produk"));
		String name = param.get("name");

		try {
			Par_sub_produk exist = par_sub_produkRepository.findBySingleId(id);
			if (exist == null) {
				Par_sub_produk par = new Par_sub_produk();
				par.setId(id);
				par.setName(name);
				par.setId_produk(id_produk);

				System.out.println("##### SAVE ##### ");

				par_sub_produkRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/subproduk/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatesubproduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		Integer id_produk = Integer.parseInt(param.get("id_produk"));
		String name = param.get("name");
		try {
			Optional<Par_sub_produk> exist = par_sub_produkRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_sub_produk par = par_sub_produkRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setId_produk(id_produk);

				System.out.println("##### UPDATE ##### ");
				par_sub_produkRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/subproduk/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletesubproduk(@PathVariable Integer id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {

		Par_sub_produk par = par_sub_produkRepository.findBySingleId(id);
		Map<String, Object> response = new HashMap<>();

		if (par == null) {
			response.put("kode", "10");
			response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
		} else {
			par_sub_produkRepository.delete(par);
			response.put("kode", "00");
			response.put("pesan", "BERHASIL DI HAPUS");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END SUBPRODUK

	@PostMapping("/parameter/tempattinggal/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listtempattinggal(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_tempat_tinggal> tempattinggal = new ArrayList<Par_tempat_tinggal>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_tempat_tinggal> pageInsts;

			if (keyword != null) {
				pageInsts = par_tempat_tinggalRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_tempat_tinggalRepository.getCount();
			} else {
				pageInsts = par_tempat_tinggalRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			tempattinggal = pageInsts.getContent();
			System.out.println("Par " + tempattinggal);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("tempattinggal", tempattinggal);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/tempattinggal/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> gettempattinggal(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			Par_tempat_tinggal par = par_tempat_tinggalRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN TEMPAT TINGGAL ##### ");
				response.put("tempattinggal", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/tempattinggal/inquiryall")
	public ResponseEntity<Map<String, Object>> gettempattinggalAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_tempat_tinggal> par = par_tempat_tinggalRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN TEMPAT TINGGAL ##### ");
				response.put("tempattinggal", par);
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

	@PostMapping("/parameter/tempattinggal/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addtempattinggal(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");

		try {
			Par_tempat_tinggal exist = par_tempat_tinggalRepository.findBySingleId(id);
			if (exist == null) {
				Par_tempat_tinggal par = new Par_tempat_tinggal();
				par.setId(id);
				par.setName(name);

				System.out.println("##### SAVE ##### ");

				par_tempat_tinggalRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/tempattinggal/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatetempattinggal(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		try {
			Optional<Par_tempat_tinggal> exist = par_tempat_tinggalRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_tempat_tinggal par = par_tempat_tinggalRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				System.out.println("##### UPDATE ##### ");
				par_tempat_tinggalRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/tempattinggal/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletetempattinggal(@PathVariable String id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {

		Par_tempat_tinggal par = par_tempat_tinggalRepository.findBySingleId(id);
		Map<String, Object> response = new HashMap<>();

		if (par == null) {
			response.put("kode", "10");
			response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
		} else {
			par_tempat_tinggalRepository.delete(par);
			response.put("kode", "00");
			response.put("pesan", "BERHASIL DI HAPUS");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END TEMPAT TINGGAL

	@PostMapping("/parameter/mscoring/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listmscoring(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_m_scoring> m_scoring = new ArrayList<Par_m_scoring>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_m_scoring> pageInsts;

			if (keyword != null) {
				pageInsts = par_m_scoringRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_m_scoringRepository.getCount();
			} else {
				pageInsts = par_m_scoringRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			m_scoring = pageInsts.getContent();
			System.out.println("Par " + m_scoring);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("mscoring", m_scoring);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/mscoring/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getmscoring(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			Par_m_scoring par = par_m_scoringRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN MASTER SCORING ##### ");
				response.put("mscoring", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/mscoring/inquiryall")
	public ResponseEntity<Map<String, Object>> getmscoringAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_m_scoring> par = par_m_scoringRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN MASTER SCORING ##### ");
				response.put("mscoring", par);
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

	@GetMapping("/parameter/mscoring/inquiryallwithrincian/{id_template_dokumen}")
	public ResponseEntity<Map<String, Object>> getmscoringAllWithRincian(@PathVariable Integer id_template_dokumen,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_m_scoring> par = par_m_scoringRepository.findByStatusAndType("1", id_template_dokumen);
			if (par != null) {
				System.out.println("##### TAMPILKAN MASTER SCORING DENGAN RINCIAN ##### ");
				response.put("mscoring", par);
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

	@PostMapping("/parameter/mscoring/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addmscoring(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String status = param.get("status");

		try {
			Par_m_scoring exist = par_m_scoringRepository.findBySingleId(id);
			if (exist == null) {
				Par_m_scoring par = new Par_m_scoring();
				par.setId(id);
				par.setName(name);
				par.setStatus(status);

				System.out.println("##### SAVE ##### ");

				par_m_scoringRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/mscoring/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatemscoring(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String status = param.get("status");
		try {
			Optional<Par_m_scoring> exist = par_m_scoringRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_m_scoring par = par_m_scoringRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setStatus(status);
				System.out.println("##### UPDATE ##### ");
				par_m_scoringRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/mscoring/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletemscoring(@PathVariable String id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {

		Par_m_scoring par = par_m_scoringRepository.findBySingleId(id);
		Map<String, Object> response = new HashMap<>();

		if (par == null) {
			response.put("kode", "10");
			response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
		} else {
			par_m_scoringRepository.delete(par);
			response.put("kode", "00");
			response.put("pesan", "BERHASIL DI HAPUS");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END M SCORING

	@PostMapping("/parameter/rincianscoring/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listrincianscoring(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_rincian_scoring> rincianscoring = new ArrayList<Par_rincian_scoring>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_rincian_scoring> pageInsts;

			if (keyword != null) {
				pageInsts = par_rincian_scoringRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_rincian_scoringRepository.getCount();
			} else {
				pageInsts = par_rincian_scoringRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			rincianscoring = pageInsts.getContent();
			System.out.println("Par " + rincianscoring);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("rincianscoring", rincianscoring);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/rincianscoring/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getrincianscoring(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_rincian_scoring par = par_rincian_scoringRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN RINCIAN SCORING ##### ");
				response.put("rincianscoring", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/rincianscoring/inquirybyidmscoring")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getrincianbyidmscoring(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			List<Par_rincian_scoring> par = par_rincian_scoringRepository.findByIdMScoring(id);
			if (par.size() > 0) {
				System.out.println("##### TAMPILKAN RINCIAN SCORING ##### ");
				response.put("rincianscoring", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/rincianscoring/inquiryall")
	public ResponseEntity<Map<String, Object>> getrincianscoringAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_rincian_scoring> par = par_rincian_scoringRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN RINCIAN SCORING ##### ");
				response.put("rincianscoring", par);
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

	@PostMapping("/parameter/rincianscoring/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addrincianscoring(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String id_m_scoring = param.get("id_m_scoring");
		String rincian = param.get("rincian");
		String skor = param.get("skor");

		try {
			Par_rincian_scoring exist = par_rincian_scoringRepository.findBySingleId(id);
			if (exist == null) {
				Par_rincian_scoring par = new Par_rincian_scoring();
				par.setId(id);
				par.setId_m_scoring(id_m_scoring);
				par.setRincian(rincian);
				par.setSkor(skor);

				System.out.println("##### SAVE ##### ");

				par_rincian_scoringRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/rincianscoring/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updaterincianscoring(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String id_m_scoring = param.get("id_m_scoring");
		String rincian = param.get("rincian");
		String skor = param.get("skor");
		try {
			Optional<Par_rincian_scoring> exist = par_rincian_scoringRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_rincian_scoring par = par_rincian_scoringRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setId_m_scoring(id_m_scoring);
				par.setRincian(rincian);
				par.setSkor(skor);

				System.out.println("##### UPDATE ##### ");
				par_rincian_scoringRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/rincianscoring/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleterincianscoring(@PathVariable Integer id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {

		Par_rincian_scoring par = par_rincian_scoringRepository.findBySingleId(id);
		Map<String, Object> response = new HashMap<>();

		if (par == null) {
			response.put("kode", "10");
			response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
		} else {
			par_rincian_scoringRepository.delete(par);
			response.put("kode", "00");
			response.put("pesan", "BERHASIL DI HAPUS");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END RINCIAN SCORING

	@PostMapping("/parameter/kategoriproduk/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listkategoriproduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_kategori_produk> kategori_produk = new ArrayList<Par_kategori_produk>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_kategori_produk> pageInsts;

			if (keyword != null) {
				pageInsts = par_kategoriprodukRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_produkRepository.getCount();
			} else {
				pageInsts = par_kategoriprodukRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			kategori_produk = pageInsts.getContent();

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("kategori_produk", kategori_produk);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/kategoriproduk/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getkategoriproduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_kategori_produk par = par_kategoriprodukRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN KATEGORI PRODUK ##### ");
				response.put("kategori_produk", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/kategoriproduk/inquiryall")
	public ResponseEntity<Map<String, Object>> getkategoriprodukAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_kategori_produk> par = par_kategoriprodukRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN KATEGORI PRODUK ##### ");
				response.put("kategori_produk", par);
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

	@PostMapping("/parameter/kategoriproduk/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addkategoriproduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");

		try {
			Par_kategori_produk exist = par_kategoriprodukRepository.findBySingleId(id);
			if (exist == null) {
				Par_kategori_produk par = new Par_kategori_produk();
				par.setId(id);
				par.setName(name);

				System.out.println("##### SAVE ##### ");

				par_kategoriprodukRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/kategoriproduk/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatekategoriproduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		try {
			Optional<Par_kategori_produk> exist = par_kategoriprodukRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_kategori_produk par = par_kategoriprodukRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				System.out.println("##### UPDATE ##### ");
				par_kategoriprodukRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/kategoriproduk/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletekategoriproduk(@PathVariable Integer id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {

		Par_kategori_produk par = par_kategoriprodukRepository.findBySingleId(id);
		Map<String, Object> response = new HashMap<>();

		if (par == null) {
			response.put("kode", "10");
			response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
		} else {
			par_kategoriprodukRepository.delete(par);
			response.put("kode", "00");
			response.put("pesan", "BERHASIL DI HAPUS");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END KATEGORI PRODUK

	@PostMapping("/parameter/ceklist/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listCeklist(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_ceklist> ceklist = new ArrayList<Par_ceklist>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_ceklist> pageInsts;

			if (keyword != null) {
				pageInsts = par_ceklistRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_ceklistRepository.getCount();
			} else {
				pageInsts = par_ceklistRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			ceklist = pageInsts.getContent();
			System.out.println("Par " + ceklist);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("ceklist", ceklist);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/parameter/ceklist/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getCeklist(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_ceklist par = par_ceklistRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN CHECKLIST ##### \n");
				response.put("ceklist", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/ceklist/inquiryall")
	public ResponseEntity<Map<String, Object>> getCeklistAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_ceklist> par = par_ceklistRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN CEKLIST ##### ");
				response.put("ceklist", par);
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

	@PostMapping("/parameter/ceklist/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addCeklist(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id_kategori_produk = Integer.parseInt(param.get("id_kategori_produk"));
		Boolean is_mandatory = Boolean.valueOf(param.get("is_mandatory"));
		String name = param.get("name");

		try {

			Par_ceklist par = new Par_ceklist();
			par.setName(name);
			par.setId_kategori_produk(id_kategori_produk);
			par.setIs_mandatory(is_mandatory);
			System.out.println("##### SAVE ##### ");

			par_ceklistRepository.save(par);

			response.put("kode", "00");
			response.put("pesan", "BERHASIL TAMBAH");

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/ceklist/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateCeklist(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		Integer id_kategori_produk = Integer.parseInt(param.get("id_kategori_produk"));
		Boolean is_mandatory = Boolean.valueOf(param.get("is_mandatory"));
		String name = param.get("name");
		try {
			Optional<Par_ceklist> exist = par_ceklistRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_ceklist par = par_ceklistRepository.findBySingleId(exist.get().getId());
				par.setName(name);
				par.setId_kategori_produk(id_kategori_produk);
				par.setIs_mandatory(is_mandatory);
				System.out.println("##### UPDATE ##### ");
				par_ceklistRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/ceklist/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteCeklist(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {

		Par_ceklist par = par_ceklistRepository.findBySingleId(id);
		Map<String, Object> response = new HashMap<>();

		if (par == null) {
			response.put("kode", "10");
			response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
		} else {
			par_ceklistRepository.delete(par);
			response.put("kode", "00");
			response.put("pesan", "BERHASIL DI HAPUS");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ######################################################################################################################
	// END CEKLIST

	@PostMapping("/parameter/dinas/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listdinas(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));
		String cab = param.get("cab");

		try {
			List<Par_dinas> dinas = new ArrayList<Par_dinas>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_dinas> pageInsts;

			if (keyword != null) {
				pageInsts = par_dinasRepository.findKeywordWithPagination(paging, keyword, cab);
				filtered = par_dinasRepository.getCount(cab);
			} else {
				pageInsts = par_dinasRepository.findAllWithPagination(paging, cab);
				filtered = pageInsts.getNumberOfElements();
			}

			dinas = pageInsts.getContent();
			System.out.println("Par " + dinas);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("dinas", dinas);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/parameter/dinas/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getdinas(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String id_cabang = param.get("id_cabang");
		try {
			Par_dinas par = par_dinasRepository.findBySingleId(id, id_cabang);
			if (par != null) {
				System.out.println("##### TAMPILKAN dinas ##### \n");
				response.put("dinas", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/dinas/inquirybycabang")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getdinasbycabang(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id_cabang = param.get("id_cabang");
		try {
			List<Par_dinas> par = par_dinasRepository.findByIdCabang(id_cabang);
			if (par != null) {
				System.out.println("##### TAMPILKAN dinas ##### \n");
				response.put("dinas", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id_cabang + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/dinas/inquiryall")
	public ResponseEntity<Map<String, Object>> getdinasAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_dinas> par = par_dinasRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN dinas ##### ");
				response.put("dinas", par);
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

	@PostMapping("/parameter/dinas/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> adddinas(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String id_cabang = param.get("id_cabang");

		try {
			Par_dinas exist = par_dinasRepository.findBySingleId(id, id_cabang);
			if (exist == null) {
				Par_dinas par = new Par_dinas();
				par.setId(id);
				par.setName(name);
				par.setId_cabang(id_cabang);
				System.out.println("##### SAVE ##### ");

				par_dinasRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/dinas/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatedinas(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String id_cabang = param.get("id_cabang");
		try {
			Optional<Par_dinas> exist = par_dinasRepository.findByid(id, id_cabang);
			if (exist.isPresent() && exist.get().getId().equals(id) && exist.get().getId_cabang().equals(id_cabang)) {
				Par_dinas par = par_dinasRepository.findBySingleId(exist.get().getId(), exist.get().getId_cabang());
				par.setId(id);
				par.setName(name);
				par.setId_cabang(id_cabang);
				System.out.println("##### UPDATE ##### ");
				par_dinasRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/dinas/delete/{id_cabang}/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletedinas(@PathVariable String id_cabang, @PathVariable String id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {

			Par_dinas par = par_dinasRepository.findBySingleId(id, id_cabang);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_dinasRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ######################################################################################################################
	// END DINAS

	@PostMapping("/parameter/sub_dinas/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listsub_dinas(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));
		String cab = param.get("cab");

		try {
			List<Par_sub_dinas> sub_dinas = new ArrayList<Par_sub_dinas>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_sub_dinas> pageInsts;

			if (keyword != null) {
				pageInsts = par_sub_dinasRepository.findKeywordWithPagination(paging, keyword, cab);
				filtered = par_sub_dinasRepository.getCount(cab);
			} else {
				pageInsts = par_sub_dinasRepository.findAllWithPagination(paging, cab);
				filtered = pageInsts.getNumberOfElements();
			}

			sub_dinas = pageInsts.getContent();
			System.out.println("Par " + sub_dinas);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("sub_dinas", sub_dinas);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/sub_dinas/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getsub_dinas(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String id_cabang = param.get("id_cabang");
		try {
			Par_sub_dinas par = par_sub_dinasRepository.findBySingleId(id, id_cabang);
			if (par != null) {
				System.out.println("##### TAMPILKAN sub_dinas ##### ");
				response.put("sub_dinas", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/sub_dinas/inquirybyiddinas")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getsub_dinasByIdKab(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String id_cabang = param.get("id_cabang");
		try {
			List<Par_sub_dinas> par = par_sub_dinasRepository.findByIdDinas(id, id_cabang);
			if (par != null) {
				System.out.println("##### TAMPILKAN SUB DINAS ##### ");
				response.put("sub_dinas", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/sub_dinas/inquiryall")
	public ResponseEntity<Map<String, Object>> getsub_dinasAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_sub_dinas> par = par_sub_dinasRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN SUB DINAS ##### ");
				response.put("sub_dinas", par);
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

	@PostMapping("/parameter/sub_dinas/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addsub_dinas(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String id_par_kode_dinas = param.get("id_par_kode_dinas");
		String name = param.get("name");
		String id_cabang = param.get("id_cabang");

		try {
			Par_sub_dinas exist = par_sub_dinasRepository.findBySingleId(id, id_cabang);
			if (exist == null) {
				Par_sub_dinas par = new Par_sub_dinas();
				par.setId(id);
				par.setId_par_kode_dinas(id_par_kode_dinas);
				par.setName(name);
				par.setId_cabang(id_cabang);
				System.out.println("##### SAVE ##### ");

				par_sub_dinasRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/sub_dinas/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatesub_dinas(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String id_par_kode_dinas = param.get("id_par_kode_dinas");
		String name = param.get("name");
		String id_cabang = param.get("id_cabang");
		try {
			Optional<Par_sub_dinas> exist = par_sub_dinasRepository.findByid(id, id_cabang);
			if (exist.isPresent() && exist.get().getId().equals(id) && exist.get().getId_cabang().equals(id_cabang)) {
				Par_sub_dinas par = par_sub_dinasRepository.findBySingleId(exist.get().getId(),
						exist.get().getId_cabang());
				par.setId(id);
				par.setId_par_kode_dinas(id_par_kode_dinas);
				par.setName(name);
				par.setId_cabang(id_cabang);
				System.out.println("##### UPDATE ##### ");
				par_sub_dinasRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/sub_dinas/delete/{id_cabang}/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletesub_dinas(@PathVariable String id_cabang, @PathVariable String id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_sub_dinas par = par_sub_dinasRepository.findBySingleId(id, id_cabang);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
//        	par_kecRepository.delete(par);
				par_sub_dinasRepository.deleteBySingleId(id);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END SUB DINAS

	@PostMapping("/parameter/sub_sub_dinas/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listsub_sub_dinas(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));
		String cab = param.get("cab");

		try {
			List<Par_sub_sub_dinas> kec = new ArrayList<Par_sub_sub_dinas>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_sub_sub_dinas> pageInsts;

			if (keyword != null) {
				pageInsts = par_sub_sub_dinasRepository.findKeywordWithPagination(paging, keyword, cab);
				filtered = par_sub_sub_dinasRepository.getCount(cab);
			} else {
				pageInsts = par_sub_sub_dinasRepository.findAllWithPagination(paging, cab);
				filtered = pageInsts.getNumberOfElements();
			}

			kec = pageInsts.getContent();
			System.out.println("Par " + kec);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("sub_sub_dinas", kec);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/sub_sub_dinas/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getsub_sub_dinas(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String id_cabang = param.get("id_cabang");
		try {
			Par_sub_sub_dinas par = par_sub_sub_dinasRepository.findBySingleId(id, id_cabang);
			if (par != null) {
				System.out.println("##### TAMPILKAN SUB SUB DINAS ##### ");
				response.put("sub_sub_dinas", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/sub_sub_dinas/inquirybyidsubdinas")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getsub_sub_dinasByIdSubDinas(
			@Valid @RequestBody Map<String, String> param, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String id_cabang = param.get("id_cabang");
		try {
			List<Par_sub_sub_dinas> par = par_sub_sub_dinasRepository.findByIdSubDinas(id, id_cabang);
			if (par != null) {
				System.out.println("##### TAMPILKAN SUB SUB DINAS ##### ");
				response.put("sub_sub_dinas", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/sub_sub_dinas/inquiryall")
	public ResponseEntity<Map<String, Object>> getsub_sub_dinasAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_sub_sub_dinas> par = par_sub_sub_dinasRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN SUB SUB DINAS ##### ");
				response.put("sub_sub_dinas", par);
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

	@PostMapping("/parameter/sub_sub_dinas/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addsub_sub_dinas(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String id_sub_dinas = param.get("id_sub_dinas");
		String name = param.get("name");
		String id_cabang = param.get("id_cabang");

		try {
//			Par_sub_sub_dinas exist = par_sub_sub_dinasRepository.findBySingleId(id, id_cabang);
//			if (exist == null) {
			Par_sub_sub_dinas par = new Par_sub_sub_dinas();
			par.setId_sub_sub_dinas(id);
			par.setName(name);
			par.setId_sub_dinas(id_sub_dinas);
			par.setId_cabang(id_cabang);
			System.out.println("##### SAVE ##### ");

			par_sub_sub_dinasRepository.save(par);

			response.put("kode", "00");
			response.put("pesan", "BERHASIL TAMBAH");
//			} else {
//				System.out.println("##### SUDAH ADA ##### ");
//				response.put("kode", "10");
//				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
//			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/sub_sub_dinas/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatesub_sub_dinas(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");

		String id_sub_dinas = param.get("id_sub_dinas");
		String name = param.get("name");
		String id_cabang = param.get("id_cabang");
		try {

			Par_sub_sub_dinas exist = par_sub_sub_dinasRepository.findBySingleId(Integer.parseInt(id), id_cabang);
			if (exist != null) {
				exist.setName(name);
				exist.setId_sub_dinas(id_sub_dinas);
				exist.setId_cabang(id_cabang);
				System.out.println("##### UPDATE ##### ");
				par_sub_sub_dinasRepository.save(exist);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/sub_sub_dinas/delete/{id_cabang}/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletesub_sub_dinas(@PathVariable String id_cabang,
			@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_sub_sub_dinas par = par_sub_sub_dinasRepository.findBySingleId(id, id_cabang);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
//        	par_sub_sub_dinasRepository.delete(par);
				par_sub_sub_dinasRepository.deleteBySingleId(id);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/cabang/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listCabang(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_cabang> cab = new ArrayList<Par_cabang>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_cabang> pageInsts;

			if (keyword != null) {
				pageInsts = par_cabangRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_cabangRepository.getCount();
			} else {
				pageInsts = par_cabangRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			cab = pageInsts.getContent();
			System.out.println("Par " + cab);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("cabang", cab);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/cabang/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getCabang(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			Par_cabang par = par_cabangRepository.findByKdCab(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN CABANG ##### ");
				response.put("cabang", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/cabang/listcabangbywilayah")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listcabangbywilayah(@AuthenticationPrincipal Jwt jwt,
			@Valid @RequestBody Map<String, String> param)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			LOGGER.info("###### KOTA YANG DIPILIH ######### " + param.get("kota").toString());
			List<Par_cabang> par = par_cabangRepository.findCabangByWilayah(param.get("kota").toString());
			if (par != null) {
				System.out.println("##### TAMPILKAN CABANG ##### " + par.toString());
				response.put("cabang", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. data kosong !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/listkabupatencabang")
	public ResponseEntity<Map<String, Object>> listkabupatencabang(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<String> par = par_cabangRepository.findDistictKabupataen();
			if (par != null) {
				System.out.println("##### TAMPILKAN CABANG ##### " + par.toString());
				response.put("kota", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. data kosong !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/cabang/inquirybycabinduk")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getCabangByCabInduk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			List<Par_cabang> par = par_cabangRepository.findByCabInduk(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN CABANG INDUK ##### ");
				response.put("cabang", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/cabang/inquirybyjeniscab")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getCabangByJenisCab(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String jenis_cab = param.get("jenis_cab");
		try {
			List<Par_cabang> par = par_cabangRepository.findByJenisCab(jenis_cab);
			if (par != null) {
				System.out.println("##### TAMPILKAN JENIS ##### ");
				response.put("cabang", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data " + jenis_cab + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/inquirykabupatencabang")
	public ResponseEntity<Map<String, Object>> inquirykabupatencabang(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_cabang> par = par_cabangRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN CABANG ##### ");
				response.put("cabang", par);
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

	@GetMapping("/parameter/cabang/inquiryall")
	public ResponseEntity<Map<String, Object>> getCabAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_cabang> par = par_cabangRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN CABANG ##### ");
				response.put("cabang", par);
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

	@GetMapping("/parameter/tujuanpembiayaan/inquiryall")
	public ResponseEntity<Map<String, Object>> tujuanpembiayaanAl(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_tujuan_pembiayaan> par = par_tujuan_pembiayaanRepository.findByIdTemp(1);
			if (par != null) {
				System.out.println("##### TAMPILKAN TUJUAN PEMBIAYAAN ##### ");
				response.put("tujuanpembiayaan", par);
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

	@GetMapping("/parameter/tujuanpembiayaan/inquiryalldata")
	public ResponseEntity<Map<String, Object>> inquiryalldatatujuan(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<String> par = par_tujuan_pembiayaanRepository.findDistictTujuanPembiayaan();
			if (par != null) {
				System.out.println("##### TAMPILKAN TUJUAN PEMBIAYAAN ##### ");
				response.put("tujuanpembiayaan", par);
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

	@GetMapping("/parameter/tujuanpembiayaan/inquiryall/{jenis}")
	public ResponseEntity<Map<String, Object>> inquiryallKonsumer(@PathVariable String jenis,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_tujuan_pembiayaan> par = par_tujuan_pembiayaanRepository.findByIdType(jenis);
			if (par != null) {
				System.out.println("##### TAMPILKAN TUJUAN PEMBIAYAAN ##### ");
				response.put("tujuanpembiayaan", par);
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

	@PostMapping("/parameter/cabang/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addCabang(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String id = param.get("id");
		String cabang = param.get("cabang");
		String alamat = param.get("alamat");
		String kota = param.get("kota");
		String inisial = param.get("inisial");
		String jenis_cab = param.get("jenis_cab");
		String no_surat_direksi = param.get("no_surat_direksi");
		LocalDate no_surat_direksi_date = null;
		if (!param.get("no_surat_direksi_date").equals("")) {
			no_surat_direksi_date = LocalDate.parse(param.get("no_surat_direksi_date"), formatter);
		}
		String nm_notaris = param.get("nm_notaris");
		String kota_notaris = param.get("kota_notaris");
		String cab_induk = param.get("cab_induk");
		String is_pinbag = param.get("is_pinbag");
		Double limit_konsumtif = Double.valueOf(param.get("limit_konsumtif"));
		Double limit_produktif = Double.valueOf(param.get("limit_produktif"));
		String level_approve = param.get("level_approve");
		String userid = param.get("userid");
		try {
			Par_cabang exist = par_cabangRepository.findByKdCab(id);
			if (exist == null) {
				Par_cabang par = new Par_cabang();
				par.setId(id);
				par.setCabang(cabang);
				par.setAlamat(alamat);
				par.setKota(kota);
				par.setInisial(inisial);
				par.setJenis_cab(jenis_cab);
				par.setNo_surat_direksi(no_surat_direksi);
				par.setNo_surat_direksi_date(no_surat_direksi_date);
				par.setNm_notaris(nm_notaris);
				par.setKota_notaris(kota_notaris);
				par.setCab_induk(cab_induk);
				par.setIs_pinbag(is_pinbag);
				par.setLimit_konsumtif(limit_konsumtif);
				par.setLimit_produktif(limit_produktif);
				par.setLevel_approve(level_approve);
				par.setInput_by(userid);
				par.setInput_date(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");
				par_cabangRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/cabang/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateCabang(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String id = param.get("id");
		String cabang = param.get("cabang");
		String alamat = param.get("alamat");
		String kota = param.get("kota");
		String inisial = param.get("inisial");
		String jenis_cab = param.get("jenis_cab");
		String no_surat_direksi = param.get("no_surat_direksi");
		LocalDate no_surat_direksi_date = null;
		if (!param.get("no_surat_direksi_date").equals("")) {
			no_surat_direksi_date = LocalDate.parse(param.get("no_surat_direksi_date"), formatter);
		}
		String nm_notaris = param.get("nm_notaris");
		String kota_notaris = param.get("kota_notaris");
		String cab_induk = param.get("cab_induk");
		String is_pinbag = param.get("is_pinbag");
		Double limit_konsumtif = Double.valueOf(param.get("limit_konsumtif"));
		Double limit_produktif = Double.valueOf(param.get("limit_produktif"));
		String level_approve = param.get("level_approve");
		String userid = param.get("userid");
		try {
			Optional<Par_cabang> exist = par_cabangRepository.findByKd(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_cabang par = par_cabangRepository.findByKdCab(exist.get().getId());
				par.setId(id);
				par.setCabang(cabang);
				par.setAlamat(alamat);
				par.setKota(kota);
				par.setInisial(inisial);
				par.setJenis_cab(jenis_cab);
				par.setNo_surat_direksi(no_surat_direksi);
				par.setNo_surat_direksi_date(no_surat_direksi_date);
				par.setNm_notaris(nm_notaris);
				par.setKota_notaris(kota_notaris);
				par.setCab_induk(cab_induk);
				par.setIs_pinbag(is_pinbag);
				par.setLimit_konsumtif(limit_konsumtif);
				par.setLimit_produktif(limit_produktif);
				par.setLevel_approve(level_approve);
				par.setUpdate_by(userid);
				par.setUpdate_date(LocalDateTime.now());
				System.out.println("##### UPDATE ##### ");
				par_cabangRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/komparisi/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateKomparisi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String userid = param.get("userid");
		String cabang = param.get("cabang");
		String no_telp = param.get("no_telp");
		String email = param.get("email");
		String no_surat_direksi = param.get("no_surat_direksi");
		String nm_notaris = param.get("nm_notaris");
		String kota_notaris = param.get("kota_notaris");
		String no_akta = param.get("no_akta");
		String tgl_akta = param.get("tgl_akta");
		String nama_pimpinan = param.get("nama_pimpinan");
		String no_ktp_pimpinan = param.get("no_ktp_pimpinan");
		String tempat_lahir_pimpinan = param.get("tempat_lahir_pimpinan");
		String tanggal_lahir_pimpinan = param.get("tanggal_lahir_pimpinan");
		String alamat_pimpinan = param.get("alamat_pimpinan");
		String nama_kantor = param.get("nama_kantor");
		String alamat = param.get("alamat");
		String kota = param.get("kota");

		LocalDate no_surat_direksi_date = param.get("no_surat_direksi_date").equals("") ? null
				: LocalDate.parse(param.get("no_surat_direksi_date"), formatter);

		try {
			Optional<Par_cabang> exist = par_cabangRepository.findByKd(cabang);
			if (exist.isPresent()) {
				Par_cabang par = par_cabangRepository.findByKdCab(exist.get().getId());

				par.setNo_surat_direksi(no_surat_direksi);
				par.setNo_telp(no_telp);
				par.setEmail(email);
				par.setNo_surat_direksi_date(no_surat_direksi_date);
				par.setNm_notaris(nm_notaris);
				par.setKota_notaris(kota_notaris);
				par.setNo_akta(no_akta);
				par.setTgl_akta(tgl_akta);
				par.setNama_pimpinan(nama_pimpinan);
				par.setNo_ktp_pimpinan(no_ktp_pimpinan);
				par.setTempat_lahir_pimpinan(tempat_lahir_pimpinan);
				par.setTanggal_lahir_pimpinan(tanggal_lahir_pimpinan);
				par.setAlamat_pimpinan(alamat_pimpinan);
				par.setCabang(nama_kantor);
				par.setAlamat(alamat);
				par.setKota(kota);

				par.setUpdate_by(userid);
				par.setUpdate_date(LocalDateTime.now());
				System.out.println("##### UPDATE ##### ");
				par_cabangRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Cabang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/cabang/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteCabang(@PathVariable String id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_cabang par = par_cabangRepository.findByKdCab(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
//        	par_kecRepository.delete(par);
				par_cabangRepository.deleteBySingleId(id);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END PAR CABANG

	@GetMapping("/parameter/template/inquiryall")
	public ResponseEntity<Map<String, Object>> gettemplate(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_template_dokumen> par = par_template_dokumenRepository.findByIdTemplate("KONSUMER");
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

	@PostMapping("/parameter/templatedokumen/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listtemplateOk(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_template_dokumen> template = new ArrayList<Par_template_dokumen>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_template_dokumen> pageInsts;

			if (keyword != null) {
				pageInsts = par_template_dokumenRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_template_dokumenRepository.getCount();
			} else {
				pageInsts = par_template_dokumenRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			template = pageInsts.getContent();
			System.out.println("Par " + template);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("template_dokumen", template);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/templatedokumen/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getTemplate(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_template_dokumen par = par_template_dokumenRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN TEMPLATE ##### ");
				response.put("template_dokumen", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/templatedokumen/inquiryall")
	public ResponseEntity<Map<String, Object>> getTemplateAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_template_dokumen> par = par_template_dokumenRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN TEMPLATE ##### ");
				response.put("template_dokumen", par);
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

	@PostMapping("/parameter/templatedokumen/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addtemplate_dokumen(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		String userid = param.get("userid");
		try {
			Par_template_dokumen exist = par_template_dokumenRepository.findBySingleId(id);
			if (exist == null) {
				Par_template_dokumen par = new Par_template_dokumen();
				par.setId(id);
				par.setName(name);
				par.setInput_by(userid);
				par.setInput_date(LocalDateTime.now());

				System.out.println("##### SAVE ##### ");

				par_template_dokumenRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/templatedokumen/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatetemplate_dokumen(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		String userid = param.get("userid");
		try {
			Optional<Par_template_dokumen> exist = par_template_dokumenRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_template_dokumen par = par_template_dokumenRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setUpdate_by(userid);
				par.setUpdate_date(LocalDateTime.now());
				System.out.println("##### UPDATE ##### ");
				par_template_dokumenRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Id " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/templatedokumen/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletetemplate_dokumen(@PathVariable Integer id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_template_dokumen par = par_template_dokumenRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_template_dokumenRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END TEMPLATE

	@PostMapping("/parameter/broker/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listBroker(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_broker> broker = new ArrayList<Par_broker>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_broker> pageInsts;

			if (keyword != null) {
				pageInsts = par_brokerRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_brokerRepository.getCount();
			} else {
				pageInsts = par_brokerRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			broker = pageInsts.getContent();
			System.out.println("Par " + broker);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("broker", broker);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/broker/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getbroker(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			Par_broker par = par_brokerRepository.findByIdBroker(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN broker ##### ");
				response.put("broker", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/broker/inquiryall")
	public ResponseEntity<Map<String, Object>> getbrokerAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_broker> par = par_brokerRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN broker ##### ");
				response.put("broker", par);
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

	@PostMapping("/parameter/broker/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addbroker(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String id = param.get("id");
		String broker = param.get("broker");
		String ket = param.get("ket");
		String userid = param.get("userid");
		try {
			Par_broker exist = par_brokerRepository.findByIdBroker(id);
			if (exist == null) {
				Par_broker par = new Par_broker();
				par.setId(id);
				par.setName(broker);
				par.setUserid(userid);
				par.setDatepost(LocalDateTime.now());
				par.setKet(ket);
				System.out.println("##### SAVE ##### ");
				par_brokerRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/broker/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatebroker(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String id = param.get("id");
		String broker = param.get("broker");
		String ket = param.get("ket");
		String userid = param.get("userid");
		try {
			Optional<Par_broker> exist = par_brokerRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_broker par = par_brokerRepository.findByIdBroker(exist.get().getId());
				par.setId(id);
				par.setName(broker);
				par.setUserid(userid);
				par.setDatepost_update(LocalDateTime.now());
				par.setKet(ket);
				System.out.println("##### UPDATE ##### ");
				par_brokerRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/broker/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletebroker(@PathVariable String id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_broker par = par_brokerRepository.findByIdBroker(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
//        	par_kecRepository.delete(par);
				par_brokerRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END PAR BROKER

	@PostMapping("/parameter/asuransi/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listasuransi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_asu> asuransi = new ArrayList<Par_asu>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_asu> pageInsts;

			if (keyword != null) {
				pageInsts = par_asuRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_asuRepository.getCount();
			} else {
				pageInsts = par_asuRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			asuransi = pageInsts.getContent();
			System.out.println("Par " + asuransi);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("asuransi", asuransi);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/asuransi/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getasuransi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			Par_asu par = par_asuRepository.findByIdAsu(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN asuransi ##### ");
				response.put("broker", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/asuransi/inquiryall")
	public ResponseEntity<Map<String, Object>> getasuransiAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_asu> par = par_asuRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN asuransi ##### ");
				response.put("asuransi", par);
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

	@PostMapping("/parameter/asuransi/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addasuransi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String id = param.get("id");
		String broker = param.get("broker");
		String ket = param.get("ket");
		String userid = param.get("userid");
		try {
			Par_asu exist = par_asuRepository.findByIdAsu(id);
			if (exist == null) {
				Par_asu par = new Par_asu();
				par.setId(id);
				par.setName(broker);
				par.setUserid(userid);
				par.setDatepost(LocalDateTime.now());
				par.setKet(ket);
				System.out.println("##### SAVE ##### ");
				par_asuRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/asuransi/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateasuransi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String id = param.get("id");
		String broker = param.get("broker");
		String ket = param.get("ket");
		String userid = param.get("userid");
		try {
			Optional<Par_asu> exist = par_asuRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_asu par = par_asuRepository.findByIdAsu(exist.get().getId());
				par.setId(id);
				par.setName(broker);
				par.setUserid(userid);
				par.setDatepost_update(LocalDateTime.now());
				par.setKet(ket);
				System.out.println("##### UPDATE ##### ");
				par_asuRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/asuransi/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteasuransi(@PathVariable String id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_asu par = par_asuRepository.findByIdAsu(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
//        	par_kecRepository.delete(par);
				par_asuRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END PAR ASURANSI

	@PostMapping("/parameter/bagihasil/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listBagiHasil(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_bagi_hasil> bagihasil = new ArrayList<Par_bagi_hasil>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_bagi_hasil> pageInsts;

			if (keyword != null) {
				pageInsts = par_bagi_hasilRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_bagi_hasilRepository.getCount();
			} else {
				pageInsts = par_bagi_hasilRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			bagihasil = pageInsts.getContent();
			System.out.println("Par " + bagihasil);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("bagi_hasil", bagihasil);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/bagihasil/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getbagihasil(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			Par_bagi_hasil par = par_bagi_hasilRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN BAGI HASIL ##### ");
				response.put("bagi_hasil", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/bagihasil/inquiryall")
	public ResponseEntity<Map<String, Object>> getBagiHasilAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bagi_hasil> par = par_bagi_hasilRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN BAGI HASIL ##### ");
				response.put("bagi_hasil", par);
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

	@PostMapping("/parameter/bagihasil/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addbagihasil(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		String id = param.get("id");
		String name = param.get("name");

		try {
			Par_bagi_hasil exist = par_bagi_hasilRepository.findBySingleId(id);
			if (exist == null) {
				Par_bagi_hasil par = new Par_bagi_hasil();
				par.setId(id);
				par.setName(name);
				System.out.println("##### SAVE ##### ");
				par_bagi_hasilRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/bagihasil/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatebagihasil(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		try {
			Optional<Par_bagi_hasil> exist = par_bagi_hasilRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_bagi_hasil par = par_bagi_hasilRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				System.out.println("##### UPDATE ##### ");
				par_bagi_hasilRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/bagihasil/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletebagihasil(@PathVariable String id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_bagi_hasil par = par_bagi_hasilRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_bagi_hasilRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END PAR BAGI HASIL

	@PostMapping("/parameter/golpihaklawan/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listgolpihaklawan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_gol_pihak_lawan> data = new ArrayList<Par_gol_pihak_lawan>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_gol_pihak_lawan> pageInsts;

			if (keyword != null) {
				pageInsts = par_gol_pihak_lawanRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_gol_pihak_lawanRepository.getCount();
			} else {
				pageInsts = par_gol_pihak_lawanRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			data = pageInsts.getContent();
			System.out.println("Par " + data);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("gol_pihak_lawan", data);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/golpihaklawan/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getgolpihaklawan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			Par_gol_pihak_lawan par = par_gol_pihak_lawanRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN Par_gol_pihak_lawan ##### ");
				response.put("gol_pihak_lawan", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/golpihaklawan/inquiryall")
	public ResponseEntity<Map<String, Object>> getgolpihaklawan(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_gol_pihak_lawan> par = par_gol_pihak_lawanRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN Par_gol_pihak_lawan ##### ");
				response.put("gol_pihak_lawan", par);
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

	@PostMapping("/parameter/golpihaklawan/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addgolpihaklawan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		String id = param.get("id");
		String name = param.get("name");

		try {
			Par_gol_pihak_lawan exist = par_gol_pihak_lawanRepository.findBySingleId(id);
			if (exist == null) {
				Par_gol_pihak_lawan par = new Par_gol_pihak_lawan();
				par.setId(id);
				par.setName(name);
				System.out.println("##### SAVE ##### ");
				par_gol_pihak_lawanRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/golpihaklawan/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updategolpihaklawan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		try {
			Optional<Par_gol_pihak_lawan> exist = par_gol_pihak_lawanRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_gol_pihak_lawan par = par_gol_pihak_lawanRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				System.out.println("##### UPDATE ##### ");
				par_gol_pihak_lawanRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/golpihaklawan/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletegolpihaklawan(@PathVariable String id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_gol_pihak_lawan par = par_gol_pihak_lawanRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_gol_pihak_lawanRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END PAR GOL PIHAK LAWAN

	@PostMapping("/parameter/jenisaset/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listJenisAset(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_jenis_aset> data = new ArrayList<Par_jenis_aset>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_jenis_aset> pageInsts;

			if (keyword != null) {
				pageInsts = par_jenis_asetRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_jenis_asetRepository.getCount();
			} else {
				pageInsts = par_jenis_asetRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			data = pageInsts.getContent();
			System.out.println("Par " + data);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("jenis_aset", data);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/jenisaset/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getJenisAset(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			Par_jenis_aset par = par_jenis_asetRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN JENIS ASET ##### ");
				response.put("jenis_aset", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/jenisaset/inquiryall")
	public ResponseEntity<Map<String, Object>> getJenisAset(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_jenis_aset> par = par_jenis_asetRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN JENIS ASET ##### ");
				response.put("jenis_aset", par);
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

	@PostMapping("/parameter/jenisaset/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addJenisAset(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		String id = param.get("id");
		String name = param.get("name");

		try {
			Par_jenis_aset exist = par_jenis_asetRepository.findBySingleId(id);
			if (exist == null) {
				Par_jenis_aset par = new Par_jenis_aset();
				par.setId(id);
				par.setName(name);
				System.out.println("##### SAVE ##### ");
				par_jenis_asetRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/jenisaset/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatejenisaset(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		try {
			Optional<Par_jenis_aset> exist = par_jenis_asetRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_jenis_aset par = par_jenis_asetRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				System.out.println("##### UPDATE ##### ");
				par_jenis_asetRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/jenisaset/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletejenisaset(@PathVariable String id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_jenis_aset par = par_jenis_asetRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_jenis_asetRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END PAR JENIS ASET

	@PostMapping("/parameter/jenissukuimbalan/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listJenisSukuImbalan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_jenis_suku_imbalan> data = new ArrayList<Par_jenis_suku_imbalan>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_jenis_suku_imbalan> pageInsts;

			if (keyword != null) {
				pageInsts = par_jenis_suku_imbalanRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_jenis_suku_imbalanRepository.getCount();
			} else {
				pageInsts = par_jenis_suku_imbalanRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			data = pageInsts.getContent();
			System.out.println("Par " + data);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("jenis_suku_imbalan", data);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/jenissukuimbalan/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getJenisSukuImbalan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_jenis_suku_imbalan par = par_jenis_suku_imbalanRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN JENIS SUKU IMBALAN ##### ");
				response.put("jenis_suku_imbalan", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/jenissukuimbalan/inquiryall")
	public ResponseEntity<Map<String, Object>> getJenisSukuImbalan(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_jenis_suku_imbalan> par = par_jenis_suku_imbalanRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN JENIS SUKU IMBALAN ##### ");
				response.put("jenis_suku_imbalan", par);
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

	@PostMapping("/parameter/jenissukuimbalan/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addJenisSukuImbalan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");

		try {
			Par_jenis_suku_imbalan exist = par_jenis_suku_imbalanRepository.findBySingleId(id);
			if (exist == null) {
				Par_jenis_suku_imbalan par = new Par_jenis_suku_imbalan();
				par.setId(id);
				par.setName(name);
				System.out.println("##### SAVE ##### ");
				par_jenis_suku_imbalanRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/jenissukuimbalan/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatejenissukuimbalan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		try {
			Optional<Par_jenis_suku_imbalan> exist = par_jenis_suku_imbalanRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_jenis_suku_imbalan par = par_jenis_suku_imbalanRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				System.out.println("##### UPDATE ##### ");
				par_jenis_suku_imbalanRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/jenissukuimbalan/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletejenissukuimbalan(@PathVariable Integer id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_jenis_suku_imbalan par = par_jenis_suku_imbalanRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_jenis_suku_imbalanRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END PAR JENIS SUKU IMBALAN

	@PostMapping("/parameter/jenisvaluta/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listJenisValuta(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_jenis_valuta> data = new ArrayList<Par_jenis_valuta>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_jenis_valuta> pageInsts;

			if (keyword != null) {
				pageInsts = par_jenis_valutaRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_jenis_valutaRepository.getCount();
			} else {
				pageInsts = par_jenis_valutaRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			data = pageInsts.getContent();
			System.out.println("Par " + data);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("jenis_valuta", data);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/jenisvaluta/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getJenisValuta(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_jenis_valuta par = par_jenis_valutaRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN JENIS VALUTA ##### ");
				response.put("jenis_valuta", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/jenisvaluta/inquiryall")
	public ResponseEntity<Map<String, Object>> getJenisValuta(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_jenis_valuta> par = par_jenis_valutaRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN JENIS VALUTA ##### ");
				response.put("jenis_valuta", par);
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

	@PostMapping("/parameter/jenisvaluta/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addJenisValuta(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		String code = param.get("code");

		try {
			Par_jenis_valuta exist = par_jenis_valutaRepository.findBySingleId(id);
			if (exist == null) {
				Par_jenis_valuta par = new Par_jenis_valuta();
				par.setId(id);
				par.setName(name);
				par.setCode(code);
				System.out.println("##### SAVE ##### ");
				par_jenis_valutaRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/jenisvaluta/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatejenisvaluta(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		String code = param.get("code");
		try {
			Optional<Par_jenis_valuta> exist = par_jenis_valutaRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_jenis_valuta par = par_jenis_valutaRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setCode(code);
				System.out.println("##### UPDATE ##### ");
				par_jenis_valutaRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/jenisvaluta/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletejenisvaluta(@PathVariable Integer id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_jenis_valuta par = par_jenis_valutaRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_jenis_valutaRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END PAR JENIS VALUTA

	@PostMapping("/parameter/kategoriportofolio/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listKategoriPortofolio(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_kategori_portofolio> data = new ArrayList<Par_kategori_portofolio>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_kategori_portofolio> pageInsts;

			if (keyword != null) {
				pageInsts = par_kategori_portofolioRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_kategori_portofolioRepository.getCount();
			} else {
				pageInsts = par_kategori_portofolioRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			data = pageInsts.getContent();
			System.out.println("Par " + data);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("kategori_portofolio", data);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/kategoriportofolio/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getKategoriPortofolio(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_kategori_portofolio par = par_kategori_portofolioRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN KATEGORI PORTOFOLIO ##### ");
				response.put("kategori_portofolio", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/kategoriportofolio/inquiryall")
	public ResponseEntity<Map<String, Object>> getKategoriPortofolio(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_kategori_portofolio> par = par_kategori_portofolioRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN KATEGORI PORTOFOLIO ##### ");
				response.put("kategori_portofolio", par);
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

	@PostMapping("/parameter/kategoriportofolio/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addKategoriPortofolio(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");

		try {
			Par_kategori_portofolio exist = par_kategori_portofolioRepository.findBySingleId(id);
			if (exist == null) {
				Par_kategori_portofolio par = new Par_kategori_portofolio();
				par.setId(id);
				par.setName(name);
				System.out.println("##### SAVE ##### ");
				par_kategori_portofolioRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/kategoriportofolio/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatekategoriportofolio(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String name = param.get("name");
		try {
			Optional<Par_kategori_portofolio> exist = par_kategori_portofolioRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_kategori_portofolio par = par_kategori_portofolioRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				System.out.println("##### UPDATE ##### ");
				par_kategori_portofolioRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/kategoriportofolio/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteKategoriPortofolio(@PathVariable Integer id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_kategori_portofolio par = par_kategori_portofolioRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_kategori_portofolioRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END PAR KATEGORI PORTOFOLIO

	@PostMapping("/parameter/qacagroup/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listQacaGroup(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_qaca_group> data = new ArrayList<Par_qaca_group>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_qaca_group> pageInsts;

			if (keyword != null) {
				pageInsts = par_qaca_groupRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_kategori_portofolioRepository.getCount();
			} else {
				pageInsts = par_qaca_groupRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			data = pageInsts.getContent();
			System.out.println("Par " + data);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("qaca_group", data);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/qacagroup/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getqacagroup(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			Par_qaca_group par = par_qaca_groupRepository.findBydata(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN QACA GROUP ##### ");
				response.put("qaca_group", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/qacagroup/inquiryall/{id}")
	public ResponseEntity<Map<String, Object>> getqacagroup(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_qaca_group> par = par_qaca_groupRepository.findSemua(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN QACA GROUP ##### ");
				response.put("qaca_group", par);
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

	@PostMapping("/parameter/qacagroup/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addqacagroup(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		String id = param.get("id");
		String name = param.get("name");
		String ket = param.get("ket");
		String user_id = param.get("user_id");
		try {
			Par_qaca_group exist = par_qaca_groupRepository.findBydata(id);
			if (exist == null) {
				Par_qaca_group par = new Par_qaca_group();
				par.setId(id);
				par.setName(name);
				par.setKet(ket);
				par.setUserid(user_id);
				par.setDatepost(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");
				par_qaca_groupRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/qacagroup/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateqacagroup(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String ket = param.get("ket");
		String user_id = param.get("user_id");
		try {
			Optional<Par_qaca_group> exist = par_qaca_groupRepository.findByQaca(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_qaca_group par = par_qaca_groupRepository.findBydata(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setKet(ket);
				par.setUserid(user_id);
				par.setDatepost_update(LocalDateTime.now());
				System.out.println("##### UPDATE ##### ");
				par_qaca_groupRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/qacagroup/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteqacagroup(@PathVariable String id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_qaca_group par = par_qaca_groupRepository.findBydata(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_qaca_groupRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END PAR QACA GROUP

	@PostMapping("/parameter/qacadetail/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listqacadetail(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_qaca_detail> data = new ArrayList<Par_qaca_detail>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_qaca_detail> pageInsts;

			if (keyword != null) {
				pageInsts = par_qaca_detailRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_qaca_detailRepository.getCount();
			} else {
				pageInsts = par_qaca_detailRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			data = pageInsts.getContent();
			System.out.println("Par " + data);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("qaca_detail", data);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/qacadetail/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getqacadetail(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		try {
			Par_qaca_detail par = par_qaca_detailRepository.findBydata(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN QACA DETAIL ##### ");
				response.put("qaca_detail", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/qacadetail/inquiryall/{id}")
	public ResponseEntity<Map<String, Object>> getqacadetail(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_qaca_detail> par = par_qaca_detailRepository.findSemua(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN QACA DETAIL ##### ");
				response.put("qaca_detail", par);
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

	@PostMapping("/parameter/qacadetail/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addqacadetail(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		Integer id = Integer.parseInt(param.get("id"));
		String document_id = param.get("document_id");
		String name = param.get("name");

		try {
			Par_qaca_detail exist = par_qaca_detailRepository.findBydata(id);
			if (exist == null) {
				Par_qaca_detail par = new Par_qaca_detail();
				par.setId(id);
				par.setDukument_id(document_id);
				par.setName(name);
				System.out.println("##### SAVE ##### ");
				par_qaca_detailRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/qacadetail/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateqacadetail(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		Integer id = Integer.parseInt(param.get("id"));
		String document_id = param.get("document_id");
		String name = param.get("name");
		try {
			Optional<Par_qaca_detail> exist = par_qaca_detailRepository.findByQaca(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_qaca_detail par = par_qaca_detailRepository.findBydata(exist.get().getId());
				par.setId(id);
				par.setDukument_id(document_id);
				par.setName(name);
				System.out.println("##### UPDATE ##### ");
				par_qaca_detailRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/qacadetail/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteqacadetail(@PathVariable Integer id,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_qaca_detail par = par_qaca_detailRepository.findBydata(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_qaca_detailRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// #########################################################################################################################
	// END PAR QACA DETAIL

//	@GetMapping("/parameter/kode_officer/inquiryall")
//	public ResponseEntity<Map<String, Object>> getlmcuof(@AuthenticationPrincipal Jwt jwt)
//			throws ParserConfigurationException, SAXException, IOException, SQLException {
//		Map<String, Object> response = new HashMap<>();
//
//		try {
//			List<Par_bv_kode_officer> par = par_bv_lmcuofRepo.findAll();
//			if (par != null) {
//				System.out.println("##### TAMPILKAN KODE OFFICER ##### ");
//				response.put("kode_officer", par);
//				response.put("kode", "00");
//				response.put("pesan", "BERHASIL");
//			} else {
//				System.out.println("##### TIDAK ADA ##### ");
//				response.put("kode", "10");
//				response.put("pesan", "Oops.. Data ini gak ada, coba yang lain ya !!!");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.put("kode", "05");
//			response.put("pesan", "DATA TIDAK SESUAI");
//		}
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}

	@GetMapping("/parameter/statusdebitur/inquiryall")
	public ResponseEntity<Map<String, Object>> getstatusdebitur(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_status_debitur> par = par_bv_statusDebiturRepo.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN QACA DETAIL ##### ");
				response.put("status_debitur", par);
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

	@GetMapping("/parameter/kategoridebitur/inquiryall")
	public ResponseEntity<Map<String, Object>> getKategoridebitur(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_kategori_debitur> par = par_bv_kategoriDebRepo.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN QACA DETAIL ##### ");
				response.put("kategori_debitur", par);
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

	@GetMapping("/parameter/kategoriportfolio/inquiryall")
	public ResponseEntity<Map<String, Object>> getKategoriportfolio(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_kategori_portofolio> par = par_bv_kateportfolioRepo.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN KATEGORI PORTFOLIO ##### ");
				response.put("kategori_portfolio", par);
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

	@GetMapping("/parameter/jenispenggunaan/inquiryall")
	public ResponseEntity<Map<String, Object>> getJenisPenggunaan(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_jenis_penggunaan> par = par_bv_jenisPenggunaanRepo.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN JENIS PENGGUNAAN ##### ");
				response.put("jenis_penggunaan", par);
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

	@GetMapping("/parameter/orientasipengguna/inquiryall")
	public ResponseEntity<Map<String, Object>> getOrientasiPenggunaan(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_orientasi_penggunaan> par = par_bv_orientasiRepo.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN ORIENTASI PENGGUNAAN ##### ");
				response.put("orientasi_penggunaan", par);
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

	@GetMapping("/parameter/sifatpiutang/inquiryall")
	public ResponseEntity<Map<String, Object>> getSifatPiutang(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_sifat_piutang> par = par_bv_sifat_piutangRepo.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN SIFAT PIUTANG ##### ");
				response.put("sifat_piutang", par);
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

	@GetMapping("/parameter/jenispiutang/inquiryall")
	public ResponseEntity<Map<String, Object>> getJenispiutang(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_jenis_piutang> par = par_bv_jenis_piutangRepo.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN SIFAT PIUTANG ##### ");
				response.put("jenis_piutang", par);
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

	@GetMapping("/parameter/jenisakad/inquiryall")
	public ResponseEntity<Map<String, Object>> getJenisAkad(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_jenis_akad> par = par_jenis_akadRepo.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN AKAD ##### ");
				response.put("jenis_akad", par);
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

	@GetMapping("/parameter/cararestruk/inquiryall")
	public ResponseEntity<Map<String, Object>> getpar_bv_cara_restrukRepository(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_cara_restruk> par = par_bv_cara_restrukRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN AKAD ##### ");
				response.put("cara_restruk", par);
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

	@GetMapping("/parameter/takeoverdari/inquiryall")
	public ResponseEntity<Map<String, Object>> gettakeoverdari(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_takeover_dari> par = par_bv_takeover_dariRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN TAKE OVER FROM ##### ");
				response.put("take_over_dari", par);
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

	@GetMapping("/parameter/kodeprogrampemerintah/inquiryall")
	public ResponseEntity<Map<String, Object>> getProgram_pemerintah(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_kode_program_pemerintah> par = par_bv_kode_program_pemerintahRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN kode program pemerintah ##### ");
				response.put("kode_program_pemerintah", par);
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

	@GetMapping("/parameter/kodesebabmacet/inquiryall")
	public ResponseEntity<Map<String, Object>> getKodesebabmace(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_kode_sebab_macet> par = par_bv_kode_sebab_macetRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN kodesebabmacet ##### ");
				response.put("kodesebabmace", par);
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

	@GetMapping("/parameter/persentasemargin/inquiryall/{jenis}")
	public ResponseEntity<Map<String, Object>> getPersentasemargin(@PathVariable String jenis,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_kode_persentasemargin> par = par_bv_kode_persentasemarginRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN persentasemargin ##### ");
				response.put("persentase_margin", par);
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

	@GetMapping("/parameter/frekuensirestruk/inquiryall")
	public ResponseEntity<Map<String, Object>> getFrekuensiRestruk(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_kode_frekuensirestruk> par = par_bv_kode_frekuensi_restrukRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN frekuensi_restruk ##### ");
				response.put("frekuensi_restruk", par);
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

	@GetMapping("/parameter/sumberdana/inquiryall")
	public ResponseEntity<Map<String, Object>> getSumberDana(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_sumber_dana> par = par_bv_sumber_danaRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN SUMBER DANA ##### ");
				response.put("sumber_dana", par);
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

	@GetMapping("/parameter/jenisgaransi/inquiryall")
	public ResponseEntity<Map<String, Object>> getJenisGaransi(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_jenis_garansi> par = par_bv_jenis_garansiRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN JENIS GARANSI ##### ");
				response.put("jenis_garansi", par);
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

	@GetMapping("/parameter/sektorekonomi/inquiryall")
	public ResponseEntity<Map<String, Object>> getSektorEkonomi(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_sektor_ekonomi> par = par_bv_sektor_ekonomiRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN SEKTOR EKONOMI ##### ");
				response.put("sektor_ekonomi", par);
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

	@GetMapping("/parameter/kodekondisi/inquiryall")
	public ResponseEntity<Map<String, Object>> getKodeKondisi(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_bv_kode_kondisi> par = par_bv_kode_kondisiRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN KODE KONDISI ##### ");
				response.put("kode_kondisi", par);
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

	@GetMapping("/parameter/statuspiutang/inquiryall")
	public ResponseEntity<Map<String, Object>> getStatusPiutang(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_bv_status_piutang> par = par_bv_status_piutangRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN STATUS PIUTANG ##### ");
				response.put("status_piutang", par);
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

	@GetMapping("/parameter/sifatakad/inquiryall")
	public ResponseEntity<Map<String, Object>> getSifatAkad(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_bv_sifat_akad> par = par_bv_sifat_akadRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN SIFAT AKAD ##### ");
				response.put("sifat_akad", par);
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

	@GetMapping("/parameter/golongandebitur/inquiryall")
	public ResponseEntity<Map<String, Object>> getGolonganDebitur(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_bv_golongan_debitur> par = par_bv_golongan_debiturRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN GOLONGAN DEBITUR ##### ");
				response.put("golongan_debitur", par);
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

	@GetMapping("/parameter/jenisaktiva/inquiryall")
	public ResponseEntity<Map<String, Object>> getJenisAktiva(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_bv_jenis_aktiva> par = par_bv_jenis_aktivaRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN GOLONGAN DEBITUR ##### ");
				response.put("jenis_aktiva", par);
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

	@GetMapping("/parameter/bagiandijamin/inquiryall")
	public ResponseEntity<Map<String, Object>> getBagianDijamin(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_bagian_dijamin> par = par_bv_bagian_dijaminRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN GOLONGAN DEBITUR ##### ");
				response.put("bagian_dijamin", par);
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

	@GetMapping("/parameter/golonganpiutang/inquiryall")
	public ResponseEntity<Map<String, Object>> getGolonganPiutang(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_bv_golongan_piutang> par = par_bv_golongan_piutangRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN GOLONGAN PIUTANG ##### ");
				response.put("golongan_piutang", par);
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

	@GetMapping("/parameter/sifatkredit/inquiryall")
	public ResponseEntity<Map<String, Object>> getSifatKredit(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_bv_sifat_kredit> par = par_bv_sifat_kreditRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN SIFAT KREDIT ##### ");
				response.put("sifat_kredit", par);
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

	@GetMapping("/parameter/tujuankredit/inquiryall")
	public ResponseEntity<Map<String, Object>> getTujuanKredit(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_bv_tujuan_kredit> par = par_bv_tujuan_kreditRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN TUJUAN KREDIT ##### ");
				response.put("tujuan_kredit", par);
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

	@GetMapping("/parameter/terkait/inquiryall")
	public ResponseEntity<Map<String, Object>> getTerkait(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_bv_terkait> par = par_bv_terkaitRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN terkait ##### ");
				response.put("pihak_terkait", par);
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

	@GetMapping("/parameter/jenisasuransi/inquiryall")
	public ResponseEntity<Map<String, Object>> getJenisAsuransi(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_bv_jenis_asuransi> par = Par_bv_jenis_asuransiRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN  ##### ");
				response.put("jenis_asuransi", par);
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

	@GetMapping("/parameter/hubunganprodusen/inquiryall")
	public ResponseEntity<Map<String, Object>> geHubunganprodusen(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_bv_hubungan_produsen> par = par_bv_hubungan_produsenRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN  ##### ");
				response.put("hubungan_produsen", par);
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

	@GetMapping("/parameter/karakteristikakad/inquiryall")
	public ResponseEntity<Map<String, Object>> geKarakteristik(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_bv_karakteristik_akad> par = par_bv_karakteristik_akadRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN  ##### ");
				response.put("hubungan_produsen", par);
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

	@GetMapping("/parameter/kodemargin/inquiryall/{inisial}")
	public ResponseEntity<Map<String, Object>> geKarakteristik(@PathVariable String inisial,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_kode_margin> par = par_kode_marginRepository.findAllByInisial(inisial);
			if (par != null) {
				System.out.println("##### TAMPILKAN  ##### ");
				response.put("par_kode_margin", par);
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

	@GetMapping("/parameter/kodeasetijarah/inquiryall")
	public ResponseEntity<Map<String, Object>> kodeasetijarah(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_kode_aset_ijarah> par = par_kode_aset_ijarahRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN  ##### ");
				response.put("par_kode_aset_ijarah", par);
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

	// =========================================== OFFICER
	// ============================================================
	@PostMapping("/parameter/officer/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listofficer(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_code_officer> officer = new ArrayList<Par_code_officer>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_code_officer> pageInsts;

			if (keyword != null) {
				pageInsts = par_code_officerRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_code_officerRepository.getCount();
			} else {
				pageInsts = par_code_officerRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			officer = pageInsts.getContent();
			System.out.println("Par " + officer);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("officer", officer);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/parameter/officer/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getofficer(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String id_cabang = param.get("id_cabang");
		try {
			Par_code_officer par = par_code_officerRepository.findBySingleId(id, id_cabang);
			if (par != null) {
				System.out.println("##### TAMPILKAN dinas ##### \n");
				response.put("officer", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/officer/inquirybycabang")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getofficerbycabang(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id_cabang = param.get("id_cabang");
		try {
			List<Par_code_officer> par = par_code_officerRepository.findByCabang(id_cabang);
			if (par != null) {
				System.out.println("##### TAMPILKAN officer ##### \n");
				response.put("officer", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id_cabang + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/officer/inquiryall")
	public ResponseEntity<Map<String, Object>> getofficerAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_code_officer> par = par_code_officerRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN officer ##### ");
				response.put("officer", par);
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

	@PostMapping("/parameter/officer/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addofficer(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String id_cabang = param.get("id_cabang");
		String userid = param.get("userid");

		try {
			Par_code_officer exist = par_code_officerRepository.findBySingleId(id, id_cabang);
			if (exist == null) {
				Par_code_officer par = new Par_code_officer();
				par.setId(id);
				par.setName(name);
				par.setId_cabang(id_cabang);
				par.setInput_by(userid);
				par.setInput_date(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");

				par_code_officerRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/officer/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateofficer(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String id_cabang = param.get("id_cabang");
		String userid = param.get("userid");
		try {
			Optional<Par_code_officer> exist = par_code_officerRepository.findByKdOfficer(id, id_cabang);
			if (exist.isPresent() && exist.get().getId().equals(id) && exist.get().getId_cabang().equals(id_cabang)) {
				Par_code_officer par = par_code_officerRepository.findBySingleId(exist.get().getId(),
						exist.get().getId_cabang());
				par.setId(id);
				par.setName(name);
				par.setId_cabang(id_cabang);
				par.setUpdate_by(userid);
				par.setUpdate_date(LocalDateTime.now());
				System.out.println("##### UPDATE ##### ");
				par_code_officerRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/officer/delete/{id}/{id_cabang}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteofficer(@PathVariable String id, @PathVariable String id_cabang,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {

			int par = par_code_officerRepository.deleteBySingleId(id, id_cabang);

			if (par == 0) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
//				par_code_officerRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// =========================================== END OFFICER
	// ========================================================

	// =========================================== REFERRAL
	// ============================================================
	@PostMapping("/parameter/referral/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listreferral(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_kode_referal> referral = new ArrayList<Par_kode_referal>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_kode_referal> pageInsts;

			if (keyword != null) {
				pageInsts = par_kode_referalRepository.findKeywordWithPagination(paging, keyword);
				filtered = par_kode_referalRepository.getCount();
			} else {
				pageInsts = par_kode_referalRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			referral = pageInsts.getContent();
			System.out.println("Par " + referral);

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("referral", referral);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/parameter/referral/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getreferral(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String id_cabang = param.get("id_cabang");
		try {
			Par_kode_referal par = par_kode_referalRepository.findBySingleId(id, id_cabang);
			if (par != null) {
				System.out.println("##### TAMPILKAN referral ##### \n");
				response.put("referral", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/referral/inquirybycabang")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getreferralbycabang(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id_cabang = param.get("id_cabang");
		try {
			List<Par_kode_referal> par = par_kode_referalRepository.findByCabang(id_cabang);
			if (par != null) {
				System.out.println("##### TAMPILKAN referral ##### \n");
				response.put("referral", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id_cabang + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/referral/inquiryall")
	public ResponseEntity<Map<String, Object>> getreferralAll(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_kode_referal> par = par_kode_referalRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN referral ##### ");
				response.put("referral", par);
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

	@PostMapping("/parameter/referral/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addreferral(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String id_cabang = param.get("id_cabang");
		String userid = param.get("userid");
		Double target_konsumer = Double.parseDouble(param.get("target_konsumer"));
		Double target_mkm = Double.parseDouble(param.get("target_mkm"));
		try {
			Par_kode_referal exist = par_kode_referalRepository.findBySingleId(id, id_cabang);
			if (exist == null) {
				Par_kode_referal par = new Par_kode_referal();
				par.setId(id);
				par.setName(name);
				par.setId_cabang(id_cabang);
				par.setInput_by(userid);
				par.setInput_date(LocalDateTime.now());
				par.setTarget_konsumer(target_konsumer);
				par.setTarget_mkm(target_mkm);
				System.out.println("##### SAVE ##### ");

				par_kode_referalRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/referral/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatereferral(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String id_cabang = param.get("id_cabang");
		String userid = param.get("userid");
		Double target_konsumer = Double.parseDouble(param.get("target_konsumer"));
		Double target_mkm = Double.parseDouble(param.get("target_mkm"));
		try {
			Optional<Par_kode_referal> exist = par_kode_referalRepository.findByKdReferal(id, id_cabang);
			if (exist.isPresent() && exist.get().getId().equals(id) && exist.get().getId_cabang().equals(id_cabang)) {
				Par_kode_referal par = par_kode_referalRepository.findBySingleId(exist.get().getId(),
						exist.get().getId_cabang());
				par.setId(id);
				par.setName(name);
				par.setId_cabang(id_cabang);
				par.setUpdate_by(userid);
				par.setUpdate_date(LocalDateTime.now());
				par.setTarget_konsumer(target_konsumer);
				par.setTarget_mkm(target_mkm);
				System.out.println("##### UPDATE ##### ");
				par_kode_referalRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/referral/delete/{id}/{id_cabang}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletereferral(@PathVariable String id, @PathVariable String id_cabang,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {

			Par_kode_referal par = par_kode_referalRepository.findBySingleId(id, id_cabang);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_kode_referalRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// =========================================== END REFERRAL
	// ========================================================

	// =========================================== NOTARIS
	// ===============================================================
	@PostMapping("/parameter/notaris/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listnotaris(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Par_bv_kode_notaris> notaris = new ArrayList<Par_bv_kode_notaris>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Par_bv_kode_notaris> pageInsts;

			if (keyword != null) {
				pageInsts = par_bv_kode_notarisRepository.findKeywordWithPagination(paging, keyword);
//                    Page<Par_agama> pageAll = par_agamaRepository.findAllWithPagination(paging);
				filtered = par_bv_kode_notarisRepository.getCount();
			} else {
				pageInsts = par_bv_kode_notarisRepository.findAllWithPagination(paging);
				filtered = pageInsts.getNumberOfElements();
			}

			notaris = pageInsts.getContent();

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("notaris", notaris);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/parameter/notaris/inquiry")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getnotaris(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		try {
			Par_bv_kode_notaris par = par_bv_kode_notarisRepository.findBySingleId(id);
			if (par != null) {
				System.out.println("##### TAMPILKAN NOTARIS ##### ");
				response.put("notaris", par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/notaris/inquiryall")
	public ResponseEntity<Map<String, Object>> getnotaris(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Par_bv_kode_notaris> par = par_bv_kode_notarisRepository.findAll();
			if (par != null) {
				System.out.println("##### TAMPILKAN NOTARIS ##### ");
				response.put("kode_notaris", par);
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

	@PostMapping("/parameter/notaris/tambah")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> addnotaris(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String userid = param.get("userid");

		try {
			Par_bv_kode_notaris exist = par_bv_kode_notarisRepository.findBySingleId(id);
			if (exist == null) {
				Par_bv_kode_notaris par = new Par_bv_kode_notaris();
				par.setId(id);
				par.setName(name);
				par.setInput_by(userid);
				par.setInput_date(LocalDateTime.now());
				System.out.println("##### SAVE ##### ");

				par_bv_kode_notarisRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL TAMBAH");
			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini sudah ada !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/parameter/notaris/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatenotaris(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		String id = param.get("id");
		String name = param.get("name");
		String userid = param.get("userid");
		try {
			Optional<Par_bv_kode_notaris> exist = par_bv_kode_notarisRepository.findByid(id);
			if (exist.isPresent() && exist.get().getId().equals(id)) {
				Par_bv_kode_notaris par = par_bv_kode_notarisRepository.findBySingleId(exist.get().getId());
				par.setId(id);
				par.setName(name);
				par.setUpdate_by(userid);
				par.setUpdate_date(LocalDateTime.now());
				System.out.println("##### UPDATE ##### ");
				par_bv_kode_notarisRepository.save(par);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL UPDATE");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. ID " + id + " ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/notaris/delete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deletenotaris(@PathVariable String id, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			Par_bv_kode_notaris par = par_bv_kode_notarisRepository.findBySingleId(id);

			if (par == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data Tidak Ditemukan !!!");
			} else {
				par_bv_kode_notarisRepository.delete(par);
				response.put("kode", "00");
				response.put("pesan", "BERHASIL DI HAPUS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/parameter/jenispembiayaan/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> templatedokumenListOk(@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Par_template_dokumen> listTemplate = par_template_dokumenRepository.findAll();
			System.out.println("##### TAMPILKAN JENIS PEMBIAYAAN ##### " + listTemplate);
			response.put("listTemplate", listTemplate);
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
