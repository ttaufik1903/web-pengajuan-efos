package com.rabbai.serviceprospek.controllers;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Logger;

import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbai.serviceprospek.models.CifData;
import com.rabbai.serviceprospek.models.Data_prospek;
import com.rabbai.serviceprospek.models.Debitur;
import com.rabbai.serviceprospek.models.Harta;
import com.rabbai.serviceprospek.models.Hub_perbankan_detail;
import com.rabbai.serviceprospek.models.Loan;
import com.rabbai.serviceprospek.models.Mail;
import com.rabbai.serviceprospek.models.Pasangan;
import com.rabbai.serviceprospek.models.Pekerjaan;
import com.rabbai.serviceprospek.models.RincianScoring;
import com.rabbai.serviceprospek.models.ScoringLoan;
import com.rabbai.serviceprospek.repository.DebiturRepository;
import com.rabbai.serviceprospek.repository.HartaRepository;
import com.rabbai.serviceprospek.repository.Hub_perbankan_detailRepository;
import com.rabbai.serviceprospek.repository.LoanRepository;
import com.rabbai.serviceprospek.repository.Par_rincian_scoringRepository;
import com.rabbai.serviceprospek.repository.PasanganRepository;
import com.rabbai.serviceprospek.repository.PekerjaanRepository;
import com.rabbai.serviceprospek.repository.ProspekRepository;
import com.rabbai.serviceprospek.repository.ScoringLoanRepository;
import com.rabbai.serviceprospek.services.CoreBankingService;
import com.rabbai.serviceprospek.services.MailService;

@RestController
public class ProspekController {

	@Autowired
	MailService notificationService;

	@Autowired
	ProspekRepository prospekRepository;

	@Autowired
	LoanRepository loanRepository;

	@Autowired
	DebiturRepository debiturRepository;

	@Autowired
	CoreBankingService cbsService;

	@Autowired
	PekerjaanRepository pekerjaanRepository;

	@Autowired
	PasanganRepository pasanganRepository;

	@Autowired
	Hub_perbankan_detailRepository hub_perbankan_detailRepository;

	@Autowired
	HartaRepository hartaRepository;

	@Autowired
	Par_rincian_scoringRepository par_rincian_scoringRepository;

	@Autowired
	ScoringLoanRepository scoringloanRepository;

	Page<Data_prospek> pageInsts;

	private static Logger LOGGER = Logger.getLogger(ProspekController.class.getName());

	@PostMapping("/prospek/pengajuan")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> getPengajuan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND PENGAJUAN NASABAH ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String ktp = param.get("ktp");
		String nama = param.get("nama");
		String npwp = param.get("npwp");
		String status_pernikahan = param.get("status_pernikahan");
		String tmp_lahir = param.get("tmp_lahir");
		LocalDate tgl_lahir = LocalDate.parse(param.get("tgl_lahir"), formatter);
		Integer kelamin = Integer.parseInt(param.get("kelamin"));
		String alamat = param.get("alamat");
		Integer cab = Integer.valueOf(param.get("cab"));
		String no_telp = param.get("no_telp");
		String email = param.get("email");
		String tujuan_pembiayaan = param.get("tujuan_pembiayaan");
		Double plafon_pengajuan = Double.valueOf(param.get("plafon_pengajuan"));
		Integer tenor_pengajuan = Integer.valueOf(param.get("tenor_pengajuan"));
		String jenis_pembiayaan = param.get("jenis_pembiayaan").toUpperCase();
		String id_jenis_pembiayaan = param.get("id_jenis_pembiayaan");
		String pekerjaan = param.get("pekerjaan");
		Double penghasilan = Double.valueOf(param.get("penghasilan"));

		DateTimeFormatter formatteryear = DateTimeFormatter.ofPattern("yyyy");
		LocalDate year = LocalDate.now();
		if (ktp.length() > 16) {
			response.put("kode", "05");
			response.put("pesan", "Nomor KTP tidak sesuai");
		}

		if (kelamin != 0 || kelamin != 1) {
			response.put("kode", "05");
			response.put("pesan", "Jenis Kelamin tidak sesuai");
		}

		try {
			Data_prospek data = prospekRepository.findByKtpDebitur(ktp, "0");
			if (data == null) {
				String format_tiket = "T." + cab + "." + year.format(formatteryear) + ".";
				System.out.println("############ format_tiket " + format_tiket);

				String lastLoan = prospekRepository.getMax(format_tiket, cab);
				System.out.println("############ lastLoan " + lastLoan);
				BigInteger maxLoan = new BigInteger("1");
				if (lastLoan != null) {
					maxLoan = new BigInteger(lastLoan.substring(11, 21)).add(new BigInteger("1"));
				}

				String noReg = String.format("%10s", maxLoan).replace(' ', '0');
				String no_tiket = format_tiket + noReg;

				Data_prospek nasabah = new Data_prospek();
				nasabah.setKtp(ktp);
				nasabah.setNama(nama);
				nasabah.setNpwp(npwp);
				nasabah.setStatus_pernikahan(status_pernikahan);
				nasabah.setTmp_lahir(tmp_lahir);
				nasabah.setTgl_lahir(tgl_lahir);
				nasabah.setKelamin(kelamin);
				nasabah.setAlamat(alamat);
				nasabah.setCab(cab);
				nasabah.setDatepost_open(LocalDateTime.now());
				nasabah.setStatus("0");
				nasabah.setNo_telp(no_telp);
				nasabah.setEmail(email);
				nasabah.setTujuan_pembiayaan(tujuan_pembiayaan);
				nasabah.setNo_tiket(no_tiket);
				nasabah.setPlafon_pengajuan(plafon_pengajuan);
				nasabah.setTenor_pengajuan(tenor_pengajuan);
				nasabah.setJenis_pembiayaan(jenis_pembiayaan.toUpperCase());
				nasabah.setId_jenis_pembiayaan(Integer.parseInt(id_jenis_pembiayaan));
				nasabah.setPekerjaan(pekerjaan);
				nasabah.setPenghasilan(penghasilan);

				try {
					prospekRepository.save(nasabah);
				} catch (Exception e) {
					e.printStackTrace();
					response.put("kode", "05");
					response.put("pesan", "DATA TIDAK SESUAI");
				}

				try {
					Mail mail = new Mail();
					mail.setTo(email);
					mail.setSubject("Pengajuan Pembiayaan Bank Riau Kepri");

					Map model = new HashMap();
					model.put("no_tiket", no_tiket);
					model.put("ktp", ktp);
					model.put("nama", nama);
					model.put("tmp_lahir", tmp_lahir);
					model.put("tgl_lahir", tgl_lahir);
					model.put("alamat", alamat);
					model.put("tujuan_pembiayaan", tujuan_pembiayaan);
					model.put("no_telp", no_telp);
					model.put("plafon_pengajuan", plafon_pengajuan);
					model.put("tenor_pengajuan", tenor_pengajuan);
					mail.setModel(model);

					notificationService.sendEmailProspekHtml(mail);
					response.put("kode", "00");
					response.put("pesan", "Pengajuan Berhasil, Silahkan Cek Email Anda. Nomor Tiket " + no_tiket);
					response.put("nomor_tiket", no_tiket);

				} catch (Exception e) {
					response.put("kode", "00");
					response.put("pesan", "Pengajuan Berhasil. Nomor Tiket " + no_tiket);
					response.put("nomor_tiket", no_tiket);
				}

//				response.put("kode", "00");
//				response.put("pesan", "Pengajuan Berhasil, Silahkan Cek Email Anda. Nomor Tiket "+no_tiket);
//				response.put("Nomor Tiket", no_tiket);
			} else {
				System.out.println("##### Sudah ada ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. Anda sudah mengajukan permohonan sebelumnya dengan Nomor Tiket "
						+ data.getNo_tiket() + "  mohon tunggu sampai dihubungi petugas kami ya...");

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND PENGAJUAN NASABAH ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/prospek/caritiket")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getTiket(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND CARI TIKET ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();

		String no_tiket = param.get("no_tiket");

		try {
			Data_prospek data = prospekRepository.findByNoTiket2(no_tiket);
			if (data == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Tiket " + no_tiket + " Tidak Ditemukan");

			} else {
				String sts = "";
				if (data.getStatus().equals("0")) {
					sts = "PENGAJUAN ANDA SEDANG DALAM ANTRIAN";
				} else if (data.getStatus().equals("1")) {
					sts = "PENGAJUAN ANDA SEDANG DALAM PROSES";
				} else if (data.getStatus().equals("2")) {
					sts = "PENGAJUAN PEMBIAYAAN ANDA DITERIMA";
				} else if (data.getStatus().equals("3")) {
					sts = "MAAF PENGAJUAN PEMBIAYAAN ANDA DITOLAK";
				} else {
					sts = "";
				}
				response.put("kode", "00");
				response.put("pesan", sts);
				response.put("dataPermohonan", data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND CARI TIKET ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/prospek/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listDebitur(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND LIST PROSPEK ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));
		Integer cab = Integer.valueOf(param.get("cab"));
		String cab2 = param.get("cab").toString();

		String jenis_pembiayaan = "KONSUMER";
		try {
			List<Data_prospek> data = new ArrayList<Data_prospek>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;

			if (keyword != null) {
				if (cab2.equals("0000") || cab2.equals("001") || cab2.equals("002") || cab2.equals("003")
						|| cab2.equals("004") || cab2.equals("005") || cab2.equals("100")) {
					pageInsts = prospekRepository.findKeywordDebiturAllWithPagination(paging, keyword,
							jenis_pembiayaan.toUpperCase());
					filtered = prospekRepository.getCountAll(jenis_pembiayaan.toUpperCase());
				} else {
					pageInsts = prospekRepository.findKeywordDebiturWithPagination(paging, keyword, cab,
							jenis_pembiayaan.toUpperCase());
					filtered = prospekRepository.getCount(cab, jenis_pembiayaan.toUpperCase());
				}

			} else {
				if (cab2.equals("0000") || cab2.equals("001") || cab2.equals("002") || cab2.equals("003")
						|| cab2.equals("004") || cab2.equals("005") || cab2.equals("100")) {
					pageInsts = prospekRepository.findAllDebiturWithPaginationAll(paging,
							jenis_pembiayaan.toUpperCase());
					filtered = pageInsts.getNumberOfElements();
				} else {
					pageInsts = prospekRepository.findAllDebiturWithPagination(paging, cab,
							jenis_pembiayaan.toUpperCase());
					filtered = pageInsts.getNumberOfElements();
				}
			}

			data = pageInsts.getContent();

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("prospek", data);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			LOGGER.info("\n ************** RESPONSE TO FRONTEND LIST PROSPEK ************** " + "@ "
					+ objectMapper.writeValueAsString(response) + " \n");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			LOGGER.info("\n ************** RESPONSE TO FRONTEND LIST PROSPEK ************** " + "@ "
					+ objectMapper.writeValueAsString(response) + " \n");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/prospek/inquiryprospekbyid")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getProspek(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND INQUIRY PROSPEK BY ID ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String no_tiket = param.get("no_tiket");
		String jenis_pembiayaan = "KONSUMER";
		try {
			Data_prospek prospek = prospekRepository.findByNoTiket(no_tiket, jenis_pembiayaan.toUpperCase());
			if (prospek != null) {

				LOGGER.info("!! PROSPEK (INQUIRY PROSPEK BY ID) !! " + "@ " + objectMapper.writeValueAsString(no_tiket)
						+ " \n");

				System.out.println("##### TAMPILKAN SCORING ##### ");
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
				response.put("prospek", prospek);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Tiket " + no_tiket + "  ini gak ada, coba yang lain ya !!!");

			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND INQUIRY PROSPEK BY ID ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/prospek/aksi")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setAksi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND CALLING ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String no_tiket = param.get("no_tiket");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String ket = param.get("ket");
		String jenis_pembiayaan = "KONSUMER";
		try {
			Data_prospek prospek = prospekRepository.findByNoTiket(no_tiket, jenis_pembiayaan.toUpperCase());

			if (prospek != null) {
				if (Integer.parseInt(prospek.getStatus()) == 0) {
					System.out.println("##### DATA LOAN ##### ");
					prospek.setStatus("1");
					prospek.setCalling_by(user_id);
					prospek.setCalling_date(LocalDateTime.now());
					prospek.setKeterangan(ket);
					prospekRepository.save(prospek);

					response.put("kode", "00");
					response.put("pesan", "BERHASIL");
				} else {
					response.put("kode", "13");
					response.put("pesan", "Oops.. Sudah pernah di Hubungi!!!");
				}
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Tiket " + no_tiket + "  ini gak ada, coba yang lain ya !!!");

			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND CALLING ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/prospek/proses")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> getProses(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND PROSES PROSPEK ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String ktp = param.get("ktp");
		Data_prospek prospek = prospekRepository.findByKtpDebitur(ktp, "1");
		try {
			List<Loan> exist = loanRepository.findByKtp(ktp);
			LOGGER.info("!!! INQUIRY DEBITUR CEK LOAN !!! " + "@ " + objectMapper.writeValueAsString(exist) + " \n");
			System.out.println("##### LOAN IS PRESENT ???? ##### " + exist.size());
			if (exist.size() > 0 && (exist.get(0).getStatus().equals("12") || exist.get(0).getStatus().equals("20")
					|| exist.get(0).getStatus().equals("21") || exist.get(0).getStatus().equals("1"))) {
				Debitur debitur = debiturRepository.findByKtpDebitur(ktp);
				if (debitur != null) {
					Loan dataloan = new Loan();
					dataloan.setTujuan_pembiayaan(prospek.getTujuan_pembiayaan());
					dataloan.setPlafon_pengajuan(prospek.getPlafon_pengajuan());
					dataloan.setTenor_pengajuan(prospek.getTenor_pengajuan());

					System.out.println("##### TAMPILKAN DEBITUR 0##### ");
					response.put("kode", "00");
					response.put("pesan", "BERHASIL");
					response.put("calondebitur", debitur);
					response.put("jenis_pengajuan", prospek.getId_jenis_pembiayaan());
					response.put("loan", dataloan);
				} else {
					// CEK BV
					ArrayList<CifData> data = cbsService.inquiryAllData(ktp);
					System.out.print("%%%%%%%% BV Data SIZE TIDAK ADA DI DEBITUR " + data.size() + "\n");
					// JIKA ADA SIMPAN DI DB DAN TAMPILKAN KEDEPAN
					// JIKA TIDAK ADA BALAS RC10
					if (data.size() == 0) {

						System.out.println("##### TIDAK ADA DI BV TAMPILKAN INPUTAN NASABAH 1 ##### ");
						Debitur deb = new Debitur();
						deb.setKtp(prospek.getKtp());
						deb.setNama(prospek.getNama());
						deb.setNpwp(prospek.getNpwp());
						deb.setStatus_pernikahan(prospek.getStatus_pernikahan());
						deb.setTmp_lahir(prospek.getTmp_lahir());
						deb.setTgl_lahir(prospek.getTgl_lahir());
						deb.setKelamin(prospek.getKelamin());
						deb.setAlamat(prospek.getAlamat());
						deb.setNo_telp(prospek.getNo_telp());

						Loan dataloan = new Loan();
						dataloan.setTujuan_pembiayaan(prospek.getTujuan_pembiayaan());
						dataloan.setPlafon_pengajuan(prospek.getPlafon_pengajuan());
						dataloan.setTenor_pengajuan(prospek.getTenor_pengajuan());

						response.put("kode", "00");
						response.put("pesan", "BERHASIL");
						response.put("calondebitur", deb);
						response.put("jenis_pengajuan", prospek.getId_jenis_pembiayaan());

						response.put("loan", dataloan);
					} else {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
						String date = data.get(0).getTGL_LAHIR().trim();
						LocalDate tgllahir = LocalDate.parse(date, formatter);
						Integer kel = 0;
						if (data.get(0).getKELAMIN().trim().equals("M")
								|| data.get(0).getKELAMIN().trim().equals("L")) {
							kel = 1;
						} else {
							kel = 0;
						}

						String almt = data.get(0).getALAMAT();
						if (almt != null) {
							almt = almt.trim();
						}

						Debitur deb = new Debitur();
						deb.setKtp(data.get(0).getKTP().trim());
						deb.setNama(data.get(0).getNAMA().trim());
						deb.setGolongan_deb(data.get(0).getGOL_DEB().trim());
						deb.setPassport(data.get(0).getPASSPORT().trim());
						deb.setNpwp(data.get(0).getNPWP().trim());
						deb.setPendidikan(Integer.parseInt(data.get(0).getPENDIDIKAN().trim()));
						deb.setStatus_pernikahan(data.get(0).getSTATUS_PERNIKAHAN().trim());
						deb.setTmp_lahir(data.get(0).getTMP_LAHIR().trim());
						deb.setTgl_lahir(tgllahir);
						deb.setKelamin(kel);
						deb.setKebangsaan(Integer.parseInt(data.get(0).getKEBANGSAAN().trim()));
						deb.setAlamat(almt);
						deb.setAlamat_ktp(almt);
						deb.setNo_telp(data.get(0).getNO_TELP().trim());

						Pekerjaan kerja = new Pekerjaan();
						kerja.setId_debitur(data.get(0).getKTP().trim());
						kerja.setProfesi(data.get(0).getPROFESI().trim());
						kerja.setNama_instansi(data.get(0).getNAMA_INSTANSI().trim());

//						kerja.setDatepost_open(LocalDateTime.now());

						List<Pekerjaan> kerjaan = new ArrayList<>();
						kerjaan.add(kerja);
						deb.setPekerjaan(kerjaan);

						if (data.get(0).getSTATUS_PERNIKAHAN().trim().equals("M")) {
							String psngn = data.get(0).getNAMA_PASANGAN();
							if (psngn != null) {
								psngn = psngn.trim();
							}
							Pasangan pas = new Pasangan();
							long unixTime = System.currentTimeMillis();
							String id_transaksi = String.valueOf(unixTime).substring(3);
//							pas.setKtp(id_transaksi);
//							pas.setId_debitur(ktp);
							pas.setNama(psngn);
//							pas.setToken(id_transaksi);
							List<Pasangan> pasangan = new ArrayList<>();
							pasangan.add(pas);
							deb.setPasangan(pasangan);
						} else {
							List<Pasangan> pasangan = new ArrayList<>();
							deb.setPasangan(pasangan);
						}
						Loan dataloan = new Loan();
						dataloan.setTujuan_pembiayaan(prospek.getTujuan_pembiayaan());
						dataloan.setPlafon_pengajuan(prospek.getPlafon_pengajuan());
						dataloan.setTenor_pengajuan(prospek.getTenor_pengajuan());

						System.out.println("##### TAMPILKAN DEBITUR 1##### ");
						response.put("kode", "00");
						response.put("pesan", "BERHASIL");
						response.put("calondebitur", deb);
						response.put("jenis_pengajuan", prospek.getId_jenis_pembiayaan());
						response.put("loan", dataloan);
					}

				}
			} else if (exist.size() > 0
					&& (!exist.get(0).getStatus().equals("12") || !exist.get(0).getStatus().equals("20")
							|| !exist.get(0).getStatus().equals("21") || !exist.get(0).getStatus().equals("1"))) {

				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "88");
				response.put("pesan",
						"Oops.. Nomor KTP " + ktp + " ini sudah ada, Selesaikan dulu pengajuan sebelumnya ya !!!");
			} else {
				// CEK BV
				ArrayList<CifData> data = cbsService.inquiryAllData(ktp);
				System.out.print("%%%%%%%% BV Data SIZE TIDAK ADA DI LOAN " + data.size() + "\n");
				// JIKA ADA SIMPAN DI DB DAN TAMPILKAN KEDEPAN
				// JIKA TIDAK ADA BALAS RC10
				if (data.size() == 0) {

					System.out.println("##### TIDAK ADA DI BV TAMPILKAN INPUTAN NASABAH 2 ##### ");
					Debitur deb = new Debitur();
					deb.setKtp(prospek.getKtp());
					deb.setNama(prospek.getNama());
					deb.setNpwp(prospek.getNpwp());
					deb.setStatus_pernikahan(prospek.getStatus_pernikahan());
					deb.setTmp_lahir(prospek.getTmp_lahir());
					deb.setTgl_lahir(prospek.getTgl_lahir());
					deb.setKelamin(prospek.getKelamin());
					deb.setAlamat(prospek.getAlamat());
					deb.setNo_telp(prospek.getNo_telp());

					Loan dataloan = new Loan();
					dataloan.setTujuan_pembiayaan(prospek.getTujuan_pembiayaan());
					dataloan.setPlafon_pengajuan(prospek.getPlafon_pengajuan());
					dataloan.setTenor_pengajuan(prospek.getTenor_pengajuan());

					response.put("kode", "00");
					response.put("pesan", "BERHASIL");
					response.put("jenis_pengajuan", prospek.getId_jenis_pembiayaan());

					response.put("calondebitur", deb);
					response.put("loan", dataloan);
				} else {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
					String date = data.get(0).getTGL_LAHIR().trim();
					LocalDate tgllahir = LocalDate.parse(date, formatter);
					Integer kel = 0;
					if (data.get(0).getKELAMIN().trim().equals("M") || data.get(0).getKELAMIN().trim().equals("L")) {
						kel = 1;
					} else {
						kel = 0;
					}
					String almt = data.get(0).getALAMAT();
					if (almt != null) {
						almt = almt.trim();
					}

					System.out.println("##### NAMA INSTANSINYA ADALAH ##### " + data.get(0).getNAMA_INSTANSI());
					Debitur debitur = debiturRepository.findByKtpDebitur(ktp);
					if (debitur != null) {
						System.out.println("##### TAMPILKAN DEBITUR 2##### ");
						response.put("kode", "00");
						response.put("pesan", "BERHASIL");
						response.put("calondebitur", debitur);
						response.put("jenis_pengajuan", prospek.getId_jenis_pembiayaan());

					} else {
						Debitur deb = new Debitur();
						deb.setKtp(data.get(0).getKTP().trim());
						deb.setNama(data.get(0).getNAMA().trim());
						deb.setGolongan_deb(data.get(0).getGOL_DEB().trim());
						deb.setPassport(data.get(0).getPASSPORT().trim());
						deb.setNpwp(data.get(0).getNPWP().trim());
						deb.setPendidikan(Integer.parseInt(data.get(0).getPENDIDIKAN().trim()));
						deb.setStatus_pernikahan(data.get(0).getSTATUS_PERNIKAHAN().trim());
						deb.setTmp_lahir(data.get(0).getTMP_LAHIR().trim());
						deb.setTgl_lahir(tgllahir);
						deb.setKelamin(kel);
						deb.setKebangsaan(Integer.parseInt(data.get(0).getKEBANGSAAN().trim()));
						deb.setAlamat(almt);
						deb.setAlamat_ktp(almt);
						deb.setNo_telp(data.get(0).getNO_TELP().trim());

//						Pekerjaan kerja = new Pekerjaan();
//						kerja.setId_debitur(data.get(0).getKTP().trim());
//						kerja.setProfesi(data.get(0).getPROFESI().trim());
//						kerja.setNama_instansi(data.get(0).getNAMA_INSTANSI().trim());

//						List<Pekerjaan> kerjaan = new ArrayList<>();
//						kerjaan.add(kerja);
//						deb.setPekerjaan(kerjaan);

						if (data.get(0).getSTATUS_PERNIKAHAN().trim().equals("M")) {
							String psngn = data.get(0).getNAMA_PASANGAN();
							if (psngn != null) {
								psngn = psngn.trim();
							}
							Pasangan pas = new Pasangan();
							long unixTime = System.currentTimeMillis();
							String id_transaksi = String.valueOf(unixTime).substring(3);
//							pas.setKtp(id_transaksi);
//							pas.setId_debitur(ktp);
							pas.setNama(psngn);
//							pas.setToken(id_transaksi);
							List<Pasangan> pasangan = new ArrayList<>();
							pasangan.add(pas);
							deb.setPasangan(pasangan);
						} else {
							List<Pasangan> pasangan = new ArrayList<>();
							deb.setPasangan(pasangan);
						}
						Loan dataloan = new Loan();
						dataloan.setTujuan_pembiayaan(prospek.getTujuan_pembiayaan());
						dataloan.setPlafon_pengajuan(prospek.getPlafon_pengajuan());
						dataloan.setTenor_pengajuan(prospek.getTenor_pengajuan());

						response.put("kode", "00");
						response.put("pesan", "BERHASIL");
						response.put("loan", dataloan);
						response.put("calondebitur", deb);
						response.put("jenis_pengajuan", prospek.getId_jenis_pembiayaan());
						LOGGER.info(
								"\n ************** RESPONSE TO FRONTEND PROSES PROSPEK + JENIS PENGAJUAN 2 ************** "
										+ "@ " + objectMapper.writeValueAsString(response) + " \n");
						return new ResponseEntity<>(response, HttpStatus.OK);
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND PROSES PROSPEK ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/prospek/save")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> saveProspek(@Valid @RequestBody String param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> response = new HashMap<>();

		try {

			JSONObject jsonObject = new JSONObject(param.toString());
			String json = jsonObject.toString();
			LOGGER.info(
					"\n ************** REQUEST FROM FRONTEND SAVE CALON DEBITUR ************** " + "@ " + json + " \n");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			DateTimeFormatter formatteryear = DateTimeFormatter.ofPattern("yyyy");
			LocalDate year = LocalDate.now();
			Integer tahun_ini = Integer.parseInt(year.format(formatteryear).toString());
			String ktp = jsonObject.getString("ktp");
			String nama = jsonObject.getString("nama");
			String golongan_deb = jsonObject.getString("golongan_deb");
			String tempat_lahir = jsonObject.getString("tmp_lahir");
			Integer kelamin = Integer.parseInt(jsonObject.getString("kelamin"));
			LocalDate tgl_lahir = LocalDate.parse(jsonObject.getString("tgl_lahir"), formatter);
			Integer penghasilan = Integer.parseInt(jsonObject.getString("penghasilan"));
			Integer tahun_lahir = Integer.valueOf(jsonObject.getString("tgl_lahir").substring(0, 4));
			Integer bulan_lahir = Integer.valueOf(jsonObject.getString("tgl_lahir").substring(5, 7));
			Integer hari_lahir = Integer.valueOf(jsonObject.getString("tgl_lahir").substring(8, 10));

			LocalDate start = LocalDate.of(tahun_lahir, bulan_lahir, hari_lahir);
			LocalDate end = LocalDate.now();
			long umur = ChronoUnit.YEARS.between(start, end);
			String alamat = jsonObject.getString("alamat");
			Integer hubungan_bank = Integer.valueOf(jsonObject.getString("hubungan_bank"));
			String status_pernikahan = jsonObject.getString("status_pernikahan");
			Integer tanggungan = Integer.valueOf(jsonObject.getString("tanggungan"));
			String status_tempat_tinggal = jsonObject.getString("status_tempat_tinggal");
			Integer cab = Integer.valueOf(jsonObject.getString("cab"));
			String userid = jsonObject.getString("userid");
			String user_nama = jsonObject.getString("user_nama");

			String format_id_loan = "R." + cab + "." + year.format(formatteryear) + ".";
			String lastLoan = loanRepository.getMaxLoan(format_id_loan);
			Integer maxLoan = 1;
			if (lastLoan != null) {
				maxLoan = Integer.parseInt(lastLoan.substring(11, 18)) + 1;
			}
			String noReg = String.format("%7s", maxLoan).replace(' ', '0');
			String id_loan = format_id_loan + noReg;
			Integer kuasa_pemotongan = Integer.parseInt(jsonObject.getString("kuasa_pemotongan"));
			String persentase_pemotong = jsonObject.getString("persentase_kuasa_pemotongan");
			if (jsonObject.getString("persentase_kuasa_pemotongan").equals("")) {
				persentase_pemotong = "0";
			}
			Integer persentase_kuasa_pemotongan = Integer.parseInt(persentase_pemotong);

			Double nilai_harta_thp_kredit = Double.valueOf("0");
			if (jsonObject.getString("nilai_harta_thp_kredit").equals(null)
					|| jsonObject.getString("nilai_harta_thp_kredit").equals("")) {
				nilai_harta_thp_kredit = null;
			} else {
				nilai_harta_thp_kredit = Double.valueOf(jsonObject.getString("nilai_harta_thp_kredit"));
			}
			Double saldo_rata_bulanan = Double.valueOf("0");
			if (jsonObject.getString("saldo_rata_bulanan").equals(null)
					|| jsonObject.getString("saldo_rata_bulanan").equals("")) {
				saldo_rata_bulanan = null;
			} else {
				saldo_rata_bulanan = Double.valueOf(jsonObject.getString("saldo_rata_bulanan"));
			}
			Double angsuran_pengajuan = Double.valueOf("0");
			if (jsonObject.getString("angsuran_pengajuan").equals(null)
					|| jsonObject.getString("angsuran_pengajuan").equals("")) {
				angsuran_pengajuan = null;
			} else {
				angsuran_pengajuan = Double.valueOf(jsonObject.getString("angsuran_pengajuan"));
			}
			String slik_ref_pengguna = jsonObject.getString("slik_ref_pengguna");
			String slik_nomor_laporan = jsonObject.getString("slik_nomor_laporan");
			LocalDate slik_tgl_data_terakhir = LocalDate.parse(jsonObject.getString("slik_tgl_data_terakhir"),
					formatter);
			LocalDate slik_tgl_permintaan = LocalDate.parse(jsonObject.getString("slik_tgl_permintaan"), formatter);
			Integer id_kategori_produk = Integer.parseInt(jsonObject.getString("id_kategori_produk"));
			Integer id_plan = Integer.parseInt(jsonObject.getString("id_plan"));

			Double harga_perolehan = Double.valueOf("0");
			if (jsonObject.getString("harga_perolehan").equals(null)
					|| jsonObject.getString("harga_perolehan").equals("")) {
				harga_perolehan = null;
			} else {
				harga_perolehan = Double.valueOf(jsonObject.getString("harga_perolehan"));
			}

			Double pajak = Double.valueOf("0");
			if (jsonObject.getString("pajak").equals(null) || jsonObject.getString("pajak").equals("")) {
				pajak = null;
			} else {
				pajak = Double.valueOf(jsonObject.getString("pajak"));
			}

			Double diskon = Double.valueOf("0");
			if (jsonObject.getString("diskon").equals(null) || jsonObject.getString("diskon").equals("")) {
				diskon = null;
			} else {
				diskon = Double.valueOf(jsonObject.getString("diskon"));
			}

			Double uang_muka = Double.valueOf("0");
			if (jsonObject.getString("diskon").equals(null) || jsonObject.getString("diskon").equals("")) {
				uang_muka = null;
			} else {
				uang_muka = Double.valueOf(jsonObject.getString("uang_muka"));
			}

			Integer is_take_over = Integer.parseInt(jsonObject.getString("is_take_over"));
			String take_over_nama_bank = jsonObject.getString("take_over_nama_bank");
			Double take_over_total = Double.valueOf("0");
			if (jsonObject.getString("take_over_total").equals(null)
					|| jsonObject.getString("take_over_total").equals("")) {
				take_over_total = null;
			} else {
				take_over_total = Double.valueOf(jsonObject.getString("take_over_total"));
			}
			String norek = jsonObject.getString("norek");
			String nama_bank = jsonObject.getString("nama_bank");
			String atas_nama = jsonObject.getString("atas_nama");
			Integer status_rekening = 0;
			if (jsonObject.get("status_rekening").equals(null) || jsonObject.get("status_rekening").equals("")) {
				System.out.println("status_rekening null");
				status_rekening = null;
			} else {
				status_rekening = Integer.parseInt(jsonObject.getString("status_rekening"));
			}
			String sistem_byr_cust = jsonObject.getString("sistem_byr_cust");
			String sistem_byr_supp = jsonObject.getString("sistem_byr_supp");
			String profesi = jsonObject.getString("profesi");
			String bidang_usaha = jsonObject.getString("bidang_usaha");
			String nama_instansi = jsonObject.getString("nama_instansi");
			String status_perusahaan = jsonObject.getString("status_perusahaan");
			String jabatan = jsonObject.getString("jabatan");
			String kolektif = jsonObject.getString("kolektif");

			Double gaji = Double.valueOf("0");
			if (jsonObject.get("gaji").equals(null) || jsonObject.get("gaji").equals("")) {
				gaji = null;
			} else {
				gaji = Double.valueOf(jsonObject.getString("gaji"));
			}
			Double tunjangan = Double.valueOf("0");
			if (jsonObject.get("tunjangan").equals(null) || jsonObject.get("tunjangan").equals("")) {
				tunjangan = null;
			} else {
				tunjangan = Double.valueOf(jsonObject.getString("tunjangan"));
			}
			Integer tahun_bekerja = Integer.parseInt(jsonObject.getString("tahun_bekerja"));

			Double netto = Double.valueOf("0");
			if (jsonObject.get("netto").equals(null) || jsonObject.get("netto").equals("")) {
				netto = null;
			} else {
				netto = Double.valueOf(jsonObject.getString("netto"));
			}

			Double potongan = Double.valueOf("0");
			if (jsonObject.get("potongan").equals(null) || jsonObject.get("potongan").equals("")) {
				potongan = null;
			} else {
				potongan = Double.valueOf(jsonObject.getString("potongan"));
			}
			Float margin_pengajuan = Float.valueOf(jsonObject.getString("margin_pengajuan"));

			Double plafon_pengajuan = Double.valueOf("0");
			if (jsonObject.get("potongan").equals(null) || jsonObject.get("potongan").equals("")) {
				plafon_pengajuan = null;
			} else {
				plafon_pengajuan = Double.valueOf(jsonObject.getString("plafon_pengajuan"));
			}
			Integer tenor_pengajuan = Integer.parseInt(jsonObject.getString("tenor_pengajuan"));
			String ideb = jsonObject.getString("ideb");
			String tujuan_pembiayaan = jsonObject.getString("tujuan_pembiayaan");

			String nama_pasangan = jsonObject.getString("nama_pasangan");
			String ktp_pasangan = jsonObject.getString("ktp_pasangan");
			String snetto_pasangan = jsonObject.getString("netto_pasangan");
			if (snetto_pasangan.equals("")) {
				snetto_pasangan = "0";
			}
			String sgaji_pasangan = jsonObject.getString("gaji_pasangan");
			if (sgaji_pasangan.equals("")) {
				sgaji_pasangan = "0";
			}
			String stunjangan_pasangan = jsonObject.getString("tunjangan_pasangan");
			if (stunjangan_pasangan.equals("")) {
				stunjangan_pasangan = "0";
			}
			String spotongan_pasangan = jsonObject.getString("potongan_pasangan");
			if (spotongan_pasangan.equals("")) {
				spotongan_pasangan = "0";
			}
			Double netto_pasangan = Double.valueOf(snetto_pasangan);
			Double gaji_pasangan = Double.valueOf(sgaji_pasangan);
			Double tunjangan_pasangan = Double.valueOf(stunjangan_pasangan);
			Double potongan_pasangan = Double.valueOf(spotongan_pasangan);
			String token_pasangan = jsonObject.getString("token_pasangan");
			if (token_pasangan.equals("") || token_pasangan.equals(null)) {
				long unixTime = System.currentTimeMillis();
				String id_transaksi = String.valueOf(unixTime).substring(3);
				token_pasangan = id_transaksi;
			}
			String sistem_byr_cust_pasangan = jsonObject.getString("sistem_byr_cust_pasangan");
			String sistem_byr_supp_pasangan = jsonObject.getString("sistem_byr_supp_pasangan");

			Double biaya_tanggungan = Double.valueOf("0");
			if (jsonObject.get("biaya_tanggungan").equals(null) || jsonObject.get("biaya_tanggungan").equals("")) {
				biaya_tanggungan = null;
			} else {
				biaya_tanggungan = Double.valueOf(jsonObject.getString("biaya_tanggungan"));
			}
			String kode_referal = jsonObject.getString("kode_referal");

			JSONArray hub_perbankan_detail = new JSONArray(jsonObject.getString("hub_perbankan_detail"));

			JSONArray harta_nasabah = new JSONArray(jsonObject.getString("harta"));
			Float exp_rate = Float.valueOf("0");
			if (jsonObject.get("exp_rate").equals(null) || jsonObject.get("exp_rate").equals("")) {
				exp_rate = null;
			} else {
				exp_rate = Float.valueOf(jsonObject.getString("exp_rate"));
			}

//			Debitur exist = debiturRepository.findByKtpDebitur(ktp);
			List<Loan> exist = loanRepository.findByKtp(ktp);
			LOGGER.info("!!! CEK LOAN SAVE DEBITUR !!! " + "@ " + objectMapper.writeValueAsString(exist) + " \n");
			System.out.println("##### LOAN IS PRESENT ???? ##### " + exist.size());
			Data_prospek prospek = prospekRepository.findByKtpDebitur(ktp, "1");
			if (exist.size() == 0) {
				Debitur debitur = new Debitur();
				debitur.setKtp(ktp);
				debitur.setNama(nama);
				debitur.setGolongan_deb(golongan_deb);
				debitur.setHubungan_bank(hubungan_bank);
				debitur.setStatus_pernikahan(status_pernikahan);
				debitur.setTmp_lahir(tempat_lahir);
				debitur.setTgl_lahir(tgl_lahir);
				debitur.setAlamat(alamat);
				debitur.setTanggungan(tanggungan);
				debitur.setStatus_tempat_tinggal(status_tempat_tinggal);
				debitur.setCab_open(cab);
				debitur.setUserid_open(userid);
				debitur.setDatepost_open(LocalDateTime.now());
				debitur.setBiaya_tanggungan(biaya_tanggungan);
				debitur.setKelamin(kelamin);
				debitur.setPenghasilan(penghasilan);
				debiturRepository.save(debitur);

				Loan loan = new Loan();
				loan.setId(id_loan);
				loan.setId_debitur(ktp);
				loan.setInput_by(userid);
				loan.setInput_nama(user_nama);
				loan.setInput_date(LocalDateTime.now());
				loan.setPlafon_pengajuan(plafon_pengajuan);
				loan.setTenor_pengajuan(tenor_pengajuan);
				loan.setIdeb(ideb);
				loan.setId_kategori_produk(id_kategori_produk);
				loan.setTujuan_pembiayaan(tujuan_pembiayaan);
				loan.setId_cab(cab.toString());
				loan.setMargin_pengajuan(margin_pengajuan);
				loan.setAngsuran_pengajuan(angsuran_pengajuan);
				loan.setKuasa_pemotongan(kuasa_pemotongan);
				loan.setPersentase_kuasa_pemotongan(persentase_kuasa_pemotongan);
				loan.setSaldo_rata_bulanan(saldo_rata_bulanan);
				loan.setKode_referal(kode_referal);
				loan.setSlik_ref_pengguna(slik_ref_pengguna);
				loan.setSlik_nomor_laporan(slik_nomor_laporan);
				loan.setSlik_tgl_data_terakhir(slik_tgl_data_terakhir);
				loan.setSlik_tgl_permintaan(slik_tgl_permintaan);
				loan.setNilai_harta_thp_kredit(nilai_harta_thp_kredit);
				loan.setId_plan(id_plan);
				loan.setHarga_perolehan(harga_perolehan);
				loan.setPajak(pajak);
				loan.setDiskon(diskon);
				loan.setUang_muka(uang_muka);
				loan.setIs_take_over(is_take_over);
				loan.setTake_over_nama_bank(take_over_nama_bank);
				loan.setTake_over_total(take_over_total);
				loan.setNorek(norek);
				loan.setNama_bank(nama_bank);
				loan.setAtas_nama(atas_nama);
				loan.setStatus_rekening(status_rekening);
				loan.setExp_rate(exp_rate);
				loan.setNo_tiket(prospek.getNo_tiket());
				loanRepository.save(loan);

				Pekerjaan pekerjaan = new Pekerjaan();
				pekerjaan.setId_debitur(ktp);
				pekerjaan.setProfesi(profesi);
				pekerjaan.setBidang_usaha(bidang_usaha);
				pekerjaan.setNama_instansi(nama_instansi);
				pekerjaan.setStatus_perusahaan(status_perusahaan);
				pekerjaan.setJabatan(jabatan);
				pekerjaan.setKolektif(kolektif);
				pekerjaan.setGaji(gaji);
				pekerjaan.setTunjangan(tunjangan);
				pekerjaan.setTahun_bekerja(tahun_bekerja);
				pekerjaan.setCab_open(cab);
				pekerjaan.setUserid_open(userid);
				pekerjaan.setDatepost_open(LocalDateTime.now());
				pekerjaan.setId_loan(id_loan);
				pekerjaan.setNetto(netto);
				pekerjaan.setPotongan(potongan);
				pekerjaan.setSistem_byr_cust(sistem_byr_cust);
				pekerjaan.setSistem_byr_supp(sistem_byr_supp);
				System.out.println("%%%%%%%%% NETTO " + netto);
				System.out.println("%%%%%%%%% potongan " + potongan);
				System.out.println("%%%%%%%%% tahun_bekerja " + tahun_bekerja);
				pekerjaanRepository.save(pekerjaan);

				if (status_pernikahan.equals("M")) {
					Pasangan pasangan = pasanganRepository.findByIdDebiturandKtpPasangan(ktp, ktp_pasangan);
					if (pasangan.equals(null)) {
						Pasangan newpasangan = new Pasangan();
						newpasangan.setKtp(ktp_pasangan);
						newpasangan.setNama(nama_pasangan);
						newpasangan.setGaji(gaji_pasangan);
						newpasangan.setTunjangan(tunjangan_pasangan);
						newpasangan.setPotongan(potongan_pasangan);
						newpasangan.setNetto(netto_pasangan);
						newpasangan.setCab_open(cab);
						newpasangan.setUserid_open(userid);
						newpasangan.setDatepost_open(LocalDateTime.now());
						newpasangan.setId_debitur(ktp);
						newpasangan.setSistem_byr_cust(sistem_byr_cust_pasangan);
						newpasangan.setSistem_byr_supp(sistem_byr_supp_pasangan);
						pasanganRepository.save(newpasangan);
					} else {
						pasangan.setKtp(ktp_pasangan);
						pasangan.setNama(nama_pasangan);
						pasangan.setGaji(gaji_pasangan);
						pasangan.setTunjangan(tunjangan_pasangan);
						pasangan.setPotongan(potongan_pasangan);
						pasangan.setNetto(netto_pasangan);
						pasangan.setCab_open(cab);
						pasangan.setUserid_open(userid);
						pasangan.setDatepost_open(LocalDateTime.now());
						pasangan.setId_debitur(ktp);
						pasangan.setSistem_byr_cust(sistem_byr_cust_pasangan);
						pasangan.setSistem_byr_supp(sistem_byr_supp_pasangan);
						pasanganRepository.save(pasangan);
					}
				}

				if (hub_perbankan_detail.length() >= 1) {
					hub_perbankan_detailRepository.deleteByIdLoan(id_loan);
					for (int i = 0; i < hub_perbankan_detail.length(); i++) {
						JSONObject aArray = hub_perbankan_detail.getJSONObject(i);
						LOGGER.info("!! GET DETAIL HUB BANK !! " + "@ " + i + " \n");

						Hub_perbankan_detail hub = new Hub_perbankan_detail();
						hub.setId_loan(id_loan);
						hub.setPeriode(aArray.getString("periode"));
						hub.setDebet(Double.valueOf(aArray.getString("debet")));
						hub.setKredit(Double.valueOf(aArray.getString("kredit")));
						hub.setSaldo(Double.valueOf(aArray.getString("saldo")));
						hub_perbankan_detailRepository.save(hub);

					}
				}

				if (harta_nasabah.length() >= 1) {
					hartaRepository.deleteByIdLoan(id_loan);
					for (int i = 0; i < harta_nasabah.length(); i++) {
						JSONObject aArray = harta_nasabah.getJSONObject(i);
						LOGGER.info("!! GET DETAIL HUB BANK !! " + "@ " + i + " \n");

						Harta har = new Harta();
						har.setId_loan(id_loan);
						har.setJenis(aArray.getString("jenis"));
						har.setKeterangan(aArray.getString("keterangan"));
						har.setPerkiraan_nilai(Double.valueOf(aArray.getString("perkiraan_nilai")));
						hartaRepository.save(har);

					}
				}

				prospek.setStatus("2");
				prospek.setProses_by(userid);
				prospek.setProses_date(LocalDateTime.now());
				prospekRepository.save(prospek);
//############################################################################################
				String value = "0";

				if (kolektif.equals("3")) {
					value = "1";
				} else if (kolektif.equals("2")) {
					value = "2";
				} else if (kolektif.equals("1")) {
					value = "3";
				}
				RincianScoring kol = par_rincian_scoringRepository.findByIdAndValue("A", value);

				if (profesi.equals("01") || profesi.equals("23") || profesi.equals("35")) {
					value = "1";
				} else if (profesi.equals("02") || profesi.equals("15") || profesi.equals("22") || profesi.equals("30")
						|| profesi.equals("31")) {
					value = "2";
				} else if (profesi.equals("07") || profesi.equals("08")) {
					value = "3";
				} else {
					value = "4";
				}
				RincianScoring kerja = par_rincian_scoringRepository.findByIdAndValue("B", value);

				if (umur <= 25) {
					value = "1";
				} else if (umur >= 26 && umur <= 40) {
					value = "2";
				} else if (umur >= 41 && umur <= 50) {
					value = "3";
				} else if (umur > 50) {
					value = "4";
				}
				RincianScoring age = par_rincian_scoringRepository.findByIdAndValue("C", value);

				if (status_pernikahan.equals("B")) {
					value = "1";
				} else if (status_pernikahan.equals("M")) {
					value = "2";
				} else if (status_pernikahan.equals("D")) {
					value = "3";
				}
				RincianScoring sts_prnkhn = par_rincian_scoringRepository.findByIdAndValue("D", value);

				if (tanggungan < 3) {
					value = "1";
				} else if (tanggungan >= 3 && tanggungan <= 5) {
					value = "2";
				} else if (tanggungan > 5) {
					value = "3";
				}
				RincianScoring sTanggungan = par_rincian_scoringRepository.findByIdAndValue("E", value);

				if (status_tempat_tinggal.equals("A")) {
					value = "1";
				} else {
					value = "2";
				}
				RincianScoring sTmptinggal = par_rincian_scoringRepository.findByIdAndValue("F", value);

				Double penghsln = netto / angsuran_pengajuan;
				int nPenghasilan = penghsln.intValue();
				if (nPenghasilan < 3) {
					value = "1";
				} else if (nPenghasilan >= 3 && nPenghasilan <= 6) {
					value = "2";
				} else if (nPenghasilan > 6) {
					value = "3";
				}
				RincianScoring sPenghasilan = par_rincian_scoringRepository.findByIdAndValue("G", value);

				Double penghsln_psng = netto_pasangan / angsuran_pengajuan;
				int nPenghasilan_ps = penghsln_psng.intValue();
				if (nPenghasilan_ps < 1) {
					value = "1";
				} else if (nPenghasilan_ps >= 1 && nPenghasilan_ps <= 3) {
					value = "2";
				} else if (nPenghasilan_ps > 3) {
					value = "3";
				}
				RincianScoring sPenghasilan_ps = par_rincian_scoringRepository.findByIdAndValue("H", value);

				Integer tahun_kerja = tahun_ini - tahun_bekerja;
				if (tahun_kerja < 3) {
					value = "1";
				} else if (tahun_kerja >= 3 && tahun_kerja <= 10) {
					value = "2";
				} else if (tahun_kerja > 10) {
					value = "3";
				}
				RincianScoring sThnKerja = par_rincian_scoringRepository.findByIdAndValue("I", value);

				Double kewajiban_lain = biaya_tanggungan / angsuran_pengajuan;
				int nKewajiban_lain = kewajiban_lain.intValue();
				if (nKewajiban_lain < 1) {
					value = "1";
				} else if (nKewajiban_lain >= 1 && nKewajiban_lain <= 3) {
					value = "2";
				} else if (nKewajiban_lain > 3) {
					value = "3";
				}
				RincianScoring sKewajiban_lain = par_rincian_scoringRepository.findByIdAndValue("J", value);

				if (kuasa_pemotongan == 1) {
					value = "1";
				} else if (kuasa_pemotongan == 0) {
					value = "2";
				}
				RincianScoring sKuasa = par_rincian_scoringRepository.findByIdAndValue("K", value);

				if (persentase_kuasa_pemotongan == 0) {
					value = "1";
				} else if (persentase_kuasa_pemotongan == 1) {
					value = "2";
				} else if (persentase_kuasa_pemotongan == 2) {
					value = "3";
				}
				RincianScoring sPersentase = par_rincian_scoringRepository.findByIdAndValue("L", value);

				Double harta = nilai_harta_thp_kredit / plafon_pengajuan;
				int nHarta = harta.intValue();
				if (nHarta < 1) {
					value = "1";
				} else if (nHarta >= 1 && nHarta <= 2) {
					value = "2";
				} else if (nHarta > 2) {
					value = "3";
				}
				RincianScoring sHarta = par_rincian_scoringRepository.findByIdAndValue("M", value);

				Double saldo = saldo_rata_bulanan / angsuran_pengajuan;
				int nSaldo = saldo.intValue();
				if (nSaldo > 3) {
					value = "1";
				} else if (nSaldo >= 1 && nSaldo <= 3) {
					value = "3";
				} else if (nSaldo > 3) {
					value = "2";
				}
				RincianScoring sSaldo = par_rincian_scoringRepository.findByIdAndValue("N", value);

				List<String[]> queryList = new ArrayList<>();
				String[] A = { kol.getId().toString(), kol.getSkor() };
				String[] B = { kerja.getId().toString(), kerja.getSkor() };
				String[] C = { age.getId().toString(), age.getSkor() };
				String[] D = { sts_prnkhn.getId().toString(), sts_prnkhn.getSkor() };
				String[] E = { sTanggungan.getId().toString(), sTanggungan.getSkor() };
				String[] F = { sTmptinggal.getId().toString(), sTmptinggal.getSkor() };
				String[] G = { sPenghasilan.getId().toString(), sPenghasilan.getSkor() };
				String[] H = { sPenghasilan_ps.getId().toString(), sPenghasilan_ps.getSkor() };
				String[] I = { sThnKerja.getId().toString(), sThnKerja.getSkor() };
				String[] J = { sKewajiban_lain.getId().toString(), sKewajiban_lain.getSkor() };
				String[] K = { sKuasa.getId().toString(), sKuasa.getSkor() };
				String[] L = { sPersentase.getId().toString(), sPersentase.getSkor() };
				String[] M = { sHarta.getId().toString(), sHarta.getSkor() };
				String[] N = { sSaldo.getId().toString(), sSaldo.getSkor() };
				queryList.add(A);
				queryList.add(B);
				queryList.add(C);
				queryList.add(D);
				queryList.add(E);
				queryList.add(F);
				queryList.add(G);
				queryList.add(H);
				queryList.add(I);
				queryList.add(J);
				queryList.add(K);
				queryList.add(L);
				queryList.add(M);
				queryList.add(N);

				scoringloanRepository.deleteByIdLoan(id_loan);

				for (String[] queryParam : queryList) {
					ScoringLoan scor = new ScoringLoan();
					scor.setId_loan(id_loan);
					scor.setId_rincian_scoring(Integer.parseInt(queryParam[0]));
					scor.setNilai(Integer.parseInt(queryParam[1]));
					scoringloanRepository.save(scor);
				}

				Integer total = scoringloanRepository.getTotalNilai(id_loan);
				String status = "3";
				if (ideb.equals("0")) {
					status = "2";
				} else {
					if (total < 17) {
						status = "1";
					} else if (total >= 17) {
						status = "2";
					}
				}

				loan.setStatus(status);
				loanRepository.save(loan);

//############################################################################################				
				System.out.println("##### SAVE ##### ");
				response.put("kode", "00");
				response.put("pesan", "SAVE DEBITUR BERHASIL");
				response.put("id_loan", id_loan);
			} else if (exist.size() > 0
					&& (exist.get(0).getStatus().equals("12") || exist.get(0).getStatus().equals("18")
							|| exist.get(0).getStatus().equals("1") || exist.get(0).getStatus().equals("19"))) {
				Debitur debitur = debiturRepository.findByKtpDebitur(ktp);
				debitur.setNama(nama);
				debitur.setGolongan_deb(golongan_deb);
				debitur.setStatus_pernikahan(status_pernikahan);
				debitur.setTmp_lahir(tempat_lahir);
				debitur.setTgl_lahir(tgl_lahir);
				debitur.setAlamat(alamat);
				debitur.setTanggungan(tanggungan);
				debitur.setStatus_tempat_tinggal(status_tempat_tinggal);
				debitur.setCab_open(cab);
				debitur.setUserid_open(userid);
				debitur.setDatepost_open(LocalDateTime.now());
				debitur.setBiaya_tanggungan(biaya_tanggungan);
				debitur.setKelamin(kelamin);
				debitur.setHubungan_bank(hubungan_bank);
				debitur.setPenghasilan(penghasilan);
				debiturRepository.save(debitur);

				Loan loan = new Loan();
				loan.setId(id_loan);
				loan.setId_debitur(ktp);
				loan.setInput_by(userid);
				loan.setInput_nama(user_nama);
				loan.setInput_date(LocalDateTime.now());
				loan.setPlafon_pengajuan(plafon_pengajuan);
				loan.setTenor_pengajuan(tenor_pengajuan);
				loan.setIdeb(ideb);
				loan.setId_kategori_produk(id_kategori_produk);
				loan.setTujuan_pembiayaan(tujuan_pembiayaan);
				loan.setId_cab(cab.toString());
				loan.setMargin_pengajuan(margin_pengajuan);
				loan.setAngsuran_pengajuan(angsuran_pengajuan);
				loan.setKuasa_pemotongan(kuasa_pemotongan);
				loan.setPersentase_kuasa_pemotongan(persentase_kuasa_pemotongan);
				loan.setSaldo_rata_bulanan(saldo_rata_bulanan);
				loan.setKode_referal(kode_referal);
				loan.setSlik_ref_pengguna(slik_ref_pengguna);
				loan.setSlik_nomor_laporan(slik_nomor_laporan);
				loan.setSlik_tgl_data_terakhir(slik_tgl_data_terakhir);
				loan.setSlik_tgl_permintaan(slik_tgl_permintaan);
				loan.setNilai_harta_thp_kredit(nilai_harta_thp_kredit);
				loan.setId_plan(id_plan);
				loan.setHarga_perolehan(harga_perolehan);
				loan.setPajak(pajak);
				loan.setDiskon(diskon);
				loan.setUang_muka(uang_muka);
				loan.setIs_take_over(is_take_over);
				loan.setTake_over_nama_bank(take_over_nama_bank);
				loan.setTake_over_total(take_over_total);
				loan.setNorek(norek);
				loan.setNama_bank(nama_bank);
				loan.setAtas_nama(atas_nama);
				loan.setStatus_rekening(status_rekening);
				loan.setExp_rate(exp_rate);
				loan.setNo_tiket(prospek.getNo_tiket());
				loanRepository.save(loan);

				Pekerjaan pekerjaan = new Pekerjaan();
				pekerjaan.setId_debitur(ktp);
				pekerjaan.setProfesi(profesi);
				pekerjaan.setBidang_usaha(bidang_usaha);
				pekerjaan.setNama_instansi(nama_instansi);
				pekerjaan.setStatus_perusahaan(status_perusahaan);
				pekerjaan.setJabatan(jabatan);
				pekerjaan.setKolektif(kolektif);
				pekerjaan.setGaji(gaji);
				pekerjaan.setTunjangan(tunjangan);
				pekerjaan.setTahun_bekerja(tahun_bekerja);
				pekerjaan.setCab_open(cab);
				pekerjaan.setUserid_open(userid);
				pekerjaan.setDatepost_open(LocalDateTime.now());
				pekerjaan.setId_loan(id_loan);
				pekerjaan.setNetto(netto);
				pekerjaan.setPotongan(potongan);
				pekerjaan.setSistem_byr_cust(sistem_byr_cust);
				pekerjaan.setSistem_byr_supp(sistem_byr_supp);
				System.out.println("%%%%%%%%% NETTO " + netto);
				System.out.println("%%%%%%%%% potongan " + potongan);
				System.out.println("%%%%%%%%% tahun_bekerja " + tahun_bekerja);
				pekerjaanRepository.save(pekerjaan);

				if (status_pernikahan.equals("M")) {
					Pasangan pasangan = pasanganRepository.findByIdDebiturandKtpPasangan(ktp, ktp_pasangan);
					pasangan.setKtp(ktp_pasangan);
					pasangan.setNama(nama_pasangan);
					pasangan.setGaji(gaji_pasangan);
					pasangan.setTunjangan(tunjangan_pasangan);
					pasangan.setPotongan(potongan_pasangan);
					pasangan.setNetto(netto_pasangan);
					pasangan.setCab_edit(cab);
					pasangan.setUserid_edit(userid);
					pasangan.setDatepost_edit(LocalDateTime.now());
					pasangan.setId_debitur(ktp);
					pasangan.setToken(token_pasangan);
					pasangan.setSistem_byr_cust(sistem_byr_cust_pasangan);
					pasangan.setSistem_byr_supp(sistem_byr_supp_pasangan);
					pasanganRepository.save(pasangan);
				}

				if (hub_perbankan_detail.length() >= 1) {
					hub_perbankan_detailRepository.deleteByIdLoan(id_loan);
					for (int i = 0; i < hub_perbankan_detail.length(); i++) {
						JSONObject aArray = hub_perbankan_detail.getJSONObject(i);
						LOGGER.info("!! GET DETAIL HUB BANK !! " + "@ " + i + " \n");
						Hub_perbankan_detail hub = new Hub_perbankan_detail();
						hub.setId_loan(id_loan);
						hub.setPeriode(aArray.getString("periode"));
						hub.setDebet(Double.valueOf(aArray.getString("debet")));
						hub.setKredit(Double.valueOf(aArray.getString("kredit")));
						hub.setSaldo(Double.valueOf(aArray.getString("saldo")));
						hub_perbankan_detailRepository.save(hub);
					}
				}

				if (harta_nasabah.length() >= 1) {
					hartaRepository.deleteByIdLoan(id_loan);
					for (int i = 0; i < harta_nasabah.length(); i++) {
						JSONObject aArray = harta_nasabah.getJSONObject(i);
						LOGGER.info("!! GET DETAIL HUB BANK !! " + "@ " + i + " \n");

						Harta har = new Harta();
						har.setId_loan(id_loan);
						har.setJenis(aArray.getString("jenis"));
						har.setKeterangan(aArray.getString("keterangan"));
						har.setPerkiraan_nilai(Double.valueOf(aArray.getString("perkiraan_nilai")));
						hartaRepository.save(har);

					}
				}

				prospek.setStatus("2");
				prospek.setProses_by(userid);
				prospek.setProses_date(LocalDateTime.now());
				prospekRepository.save(prospek);
				String value = "0";

				if (kolektif.equals("3")) {
					value = "1";
				} else if (kolektif.equals("2")) {
					value = "2";
				} else if (kolektif.equals("1")) {
					value = "3";
				}
				RincianScoring kol = par_rincian_scoringRepository.findByIdAndValue("A", value);

				if (profesi.equals("01") || profesi.equals("23") || profesi.equals("35")) {
					value = "1";
				} else if (profesi.equals("02") || profesi.equals("15") || profesi.equals("22") || profesi.equals("30")
						|| profesi.equals("31")) {
					value = "2";
				} else if (profesi.equals("07") || profesi.equals("08")) {
					value = "3";
				} else {
					value = "4";
				}
				RincianScoring kerja = par_rincian_scoringRepository.findByIdAndValue("B", value);

				if (umur <= 25) {
					value = "1";
				} else if (umur >= 26 && umur <= 40) {
					value = "2";
				} else if (umur >= 41 && umur <= 50) {
					value = "3";
				} else if (umur > 50) {
					value = "4";
				}
				RincianScoring age = par_rincian_scoringRepository.findByIdAndValue("C", value);

				if (status_pernikahan.equals("B")) {
					value = "1";
				} else if (status_pernikahan.equals("M")) {
					value = "2";
				} else if (status_pernikahan.equals("D")) {
					value = "3";
				}
				RincianScoring sts_prnkhn = par_rincian_scoringRepository.findByIdAndValue("D", value);

				if (tanggungan < 3) {
					value = "1";
				} else if (tanggungan >= 3 && tanggungan <= 5) {
					value = "2";
				} else if (tanggungan > 5) {
					value = "3";
				}
				RincianScoring sTanggungan = par_rincian_scoringRepository.findByIdAndValue("E", value);

				if (status_tempat_tinggal.equals("A")) {
					value = "1";
				} else {
					value = "2";
				}
				RincianScoring sTmptinggal = par_rincian_scoringRepository.findByIdAndValue("F", value);

				Double penghsln = netto / angsuran_pengajuan;
				int nPenghasilan = penghsln.intValue();
				if (nPenghasilan < 3) {
					value = "1";
				} else if (nPenghasilan >= 3 && nPenghasilan <= 6) {
					value = "2";
				} else if (nPenghasilan > 6) {
					value = "3";
				}
				RincianScoring sPenghasilan = par_rincian_scoringRepository.findByIdAndValue("G", value);

				Double penghsln_psng = netto_pasangan / angsuran_pengajuan;
				int nPenghasilan_ps = penghsln_psng.intValue();
				if (nPenghasilan_ps < 1) {
					value = "1";
				} else if (nPenghasilan_ps >= 1 && nPenghasilan_ps <= 3) {
					value = "2";
				} else if (nPenghasilan_ps > 3) {
					value = "3";
				}
				RincianScoring sPenghasilan_ps = par_rincian_scoringRepository.findByIdAndValue("H", value);

				Integer tahun_kerja = tahun_ini - tahun_bekerja;
				if (tahun_kerja < 3) {
					value = "1";
				} else if (tahun_kerja >= 3 && tahun_kerja <= 10) {
					value = "2";
				} else if (tahun_kerja > 10) {
					value = "3";
				}
				RincianScoring sThnKerja = par_rincian_scoringRepository.findByIdAndValue("I", value);

				Double kewajiban_lain = biaya_tanggungan / angsuran_pengajuan;
				int nKewajiban_lain = kewajiban_lain.intValue();
				if (nKewajiban_lain < 1) {
					value = "1";
				} else if (nKewajiban_lain >= 1 && nKewajiban_lain <= 3) {
					value = "2";
				} else if (nKewajiban_lain > 3) {
					value = "3";
				}
				RincianScoring sKewajiban_lain = par_rincian_scoringRepository.findByIdAndValue("J", value);

				if (kuasa_pemotongan == 1) {
					value = "1";
				} else if (kuasa_pemotongan == 0) {
					value = "2";
				}
				RincianScoring sKuasa = par_rincian_scoringRepository.findByIdAndValue("K", value);

				if (persentase_kuasa_pemotongan == 0) {
					value = "1";
				} else if (persentase_kuasa_pemotongan == 1) {
					value = "2";
				} else if (persentase_kuasa_pemotongan == 2) {
					value = "3";
				}
				RincianScoring sPersentase = par_rincian_scoringRepository.findByIdAndValue("L", value);

				Double harta = nilai_harta_thp_kredit / plafon_pengajuan;
				int nHarta = harta.intValue();
				if (nHarta < 1) {
					value = "1";
				} else if (nHarta >= 1 && nHarta <= 2) {
					value = "2";
				} else if (nHarta > 2) {
					value = "3";
				}
				RincianScoring sHarta = par_rincian_scoringRepository.findByIdAndValue("M", value);

				Double saldo = saldo_rata_bulanan / angsuran_pengajuan;
				int nSaldo = saldo.intValue();
				if (nSaldo > 3) {
					value = "1";
				} else if (nSaldo >= 1 && nSaldo <= 3) {
					value = "3";
				} else if (nSaldo > 3) {
					value = "2";
				}
				RincianScoring sSaldo = par_rincian_scoringRepository.findByIdAndValue("N", value);

				List<String[]> queryList = new ArrayList<>();
				String[] A = { kol.getId().toString(), kol.getSkor() };
				String[] B = { kerja.getId().toString(), kerja.getSkor() };
				String[] C = { age.getId().toString(), age.getSkor() };
				String[] D = { sts_prnkhn.getId().toString(), sts_prnkhn.getSkor() };
				String[] E = { sTanggungan.getId().toString(), sTanggungan.getSkor() };
				String[] F = { sTmptinggal.getId().toString(), sTmptinggal.getSkor() };
				String[] G = { sPenghasilan.getId().toString(), sPenghasilan.getSkor() };
				String[] H = { sPenghasilan_ps.getId().toString(), sPenghasilan_ps.getSkor() };
				String[] I = { sThnKerja.getId().toString(), sThnKerja.getSkor() };
				String[] J = { sKewajiban_lain.getId().toString(), sKewajiban_lain.getSkor() };
				String[] K = { sKuasa.getId().toString(), sKuasa.getSkor() };
				String[] L = { sPersentase.getId().toString(), sPersentase.getSkor() };
				String[] M = { sHarta.getId().toString(), sHarta.getSkor() };
				String[] N = { sSaldo.getId().toString(), sSaldo.getSkor() };
				queryList.add(A);
				queryList.add(B);
				queryList.add(C);
				queryList.add(D);
				queryList.add(E);
				queryList.add(F);
				queryList.add(G);
				queryList.add(H);
				queryList.add(I);
				queryList.add(J);
				queryList.add(K);
				queryList.add(L);
				queryList.add(M);
				queryList.add(N);

				scoringloanRepository.deleteByIdLoan(id_loan);

				for (String[] queryParam : queryList) {
					ScoringLoan scor = new ScoringLoan();
					scor.setId_loan(id_loan);
					scor.setId_rincian_scoring(Integer.parseInt(queryParam[0]));
					scor.setNilai(Integer.parseInt(queryParam[1]));
					scoringloanRepository.save(scor);
				}

				Integer total = scoringloanRepository.getTotalNilai(id_loan);
				String status = "3";
				if (ideb.equals("0")) {
					status = "2";
				} else {
					if (total < 17) {
						status = "1";
					} else if (total >= 17) {
						status = "2";
					}
				}

				loan.setStatus(status);
				loanRepository.save(loan);

//############################################################################################	
				System.out.println("##### SAVE ##### ");
				response.put("kode", "00");
				response.put("pesan", "SAVE DEBITUR BERHASIL");
				response.put("id_loan", id_loan);

			} else {
				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "88");
				response.put("pesan",
						"Oops.. Nomor KTP " + ktp + " ini sudah ada, Selesaikan dulu pengajuannya ya !!!");

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND SAVE CALON DEBITUR ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
