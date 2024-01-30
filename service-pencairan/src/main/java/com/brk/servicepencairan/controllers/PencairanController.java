package com.brk.servicepencairan.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.brk.servicepencairan.models.Agunan;
import com.brk.servicepencairan.models.Data_bi;
import com.brk.servicepencairan.models.Data_lampiran_file;
import com.brk.servicepencairan.models.Debitur;
import com.brk.servicepencairan.models.Loan;
import com.brk.servicepencairan.models.Par_cabang;
import com.brk.servicepencairan.models.Par_plan;
import com.brk.servicepencairan.models.Par_sub_sub_dinas;
import com.brk.servicepencairan.models.Pekerjaan;
import com.brk.servicepencairan.models.ResponseMessage;
import com.brk.servicepencairan.repository.AgunanRepository;
import com.brk.servicepencairan.repository.Data_biRepository;
import com.brk.servicepencairan.repository.Data_lampiran_fileRepository;
import com.brk.servicepencairan.repository.DebiturRepository;
import com.brk.servicepencairan.repository.LoanRepository;
import com.brk.servicepencairan.repository.Par_akadRepository;
import com.brk.servicepencairan.repository.Par_cabangRepository;
import com.brk.servicepencairan.repository.Par_ceklistRepository;
import com.brk.servicepencairan.repository.Par_planRepository;
import com.brk.servicepencairan.repository.Par_rincian_scoringRepository;
import com.brk.servicepencairan.repository.Par_sub_sub_dinasRepository;
import com.brk.servicepencairan.repository.PasanganRepository;
import com.brk.servicepencairan.repository.PekerjaanRepository;
import com.brk.servicepencairan.repository.ScoringLoanRepository;
import com.brk.servicepencairan.repository.UsersRepository;
import com.brk.servicepencairan.repository.UsulanRepository;
import com.brk.servicepencairan.services.CoreBankingService;
import com.brk.servicepencairan.services.FilesStorageService;
import com.brk.servicepencairan.services.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
public class PencairanController {

	@Autowired DebiturRepository debiturRepository;
	@Autowired UsersRepository usersRepository;
	@Autowired PekerjaanRepository pekerjaanRepository;
	@Autowired LoanRepository loanRepository;
	@Autowired ScoringLoanRepository scoringloanRepository;
	@Autowired MailService notificationService;
	@Autowired FilesStorageService storageService;
	@Autowired PasanganRepository pasanganRepository;
	@Autowired Data_lampiran_fileRepository lampiran_fileRepository;
	@Autowired Par_ceklistRepository par_ceklistRepository;
	@Autowired Par_rincian_scoringRepository par_rincian_scoringRepository;
	@Autowired Par_cabangRepository par_cabangRepository;
	@Autowired UsulanRepository usulanRepository;
	@Autowired Data_biRepository data_biRepository;
	@Autowired Par_akadRepository par_akadRepository;
	@Autowired Par_planRepository par_planRepository;
	@Autowired AgunanRepository agunanRepository;
	@Autowired CoreBankingService cbsService;
	@Autowired Par_sub_sub_dinasRepository sub_sub_dinasRepository;
	
	@Value("${bv.ip}")
	private String ip;
	@Value("${bv.port}")
	private String port;
	

	@Value("${siap.url}")
	private String url_siap;
	@Value("${siap.url_token}")
	private String url_token;
	
	@Value("${siap.client_id}")
	private String client_id;
	@Value("${siap.client_secret}")
	private String client_secret;
	@Value("${siap.username}")
	private String username;
	@Value("${siap.password}")
	private String password;
	
//	private static Logger LOGGER = Logger.getLogger(PencairanController.class.getName());
	Logger LOGGER = LoggerFactory.getLogger(PencairanController.class);
	@PostMapping("/pencairan/calondebitur/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listDebitur(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND LIST CALON DEBITUR ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		String cab = param.get("cab");

		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<Loan> instansis = new ArrayList<Loan>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Loan> pageInsts;

			if (keyword != null) {
				pageInsts = loanRepository.findKeywordDebiturWithPagination(paging, keyword, cab);
				filtered = loanRepository.getCount(cab);
			} else {
				pageInsts = loanRepository.findAllDebiturWithPagination(paging, cab);
				filtered = pageInsts.getNumberOfElements();
			}
			instansis = pageInsts.getContent();

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("calondebitur", instansis);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			LOGGER.info("\n ************** RESPONSE TO FRONTEND LIST CALON DEBITUR ************** " + "@ "
					+ objectMapper.writeValueAsString(response) + " \n");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			LOGGER.info("\n ************** RESPONSE TO FRONTEND LIST CALON DEBITUR ************** " + "@ "
					+ objectMapper.writeValueAsString(response) + " \n");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/pencairan/calondebitur/inquiry")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> getDebitur(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND INQUIRY DEBITUR ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String ktp = param.get("ktp");
		try {
			List<Loan> exist = loanRepository.findByKtp(ktp);
			LOGGER.info("!!! INQUIRY DEBITUR CEK LOAN !!! " + "@ " + objectMapper.writeValueAsString(exist) + " \n");
			System.out.println("##### LOAN IS PRESENT ???? ##### " + exist.size());
			if (exist.size() > 0 && (exist.get(0).getStatus().equals("12") || exist.get(0).getStatus().equals("19")
					|| exist.get(0).getStatus().equals("1"))) {
				Debitur debitur = debiturRepository.findByKtpDebitur(ktp);
				if (debitur != null) {
					System.out.println("##### TAMPILKAN DEBITUR 0##### ");
					response.put("kode", "00");
					response.put("pesan", "BERHASIL");
					response.put("calondebitur", debitur);
				} else {

					System.out.println("##### TIDAK ADA ##### ");
					response.put("kode", "10");
					response.put("pesan", "Oops.. No KTP " + ktp + "  ini gak ada, coba yang lain ya !!!");
				}
			} else if (exist.size() > 0 && (!exist.get(0).getStatus().equals("12")
					|| !exist.get(0).getStatus().equals("19") || !exist.get(0).getStatus().equals("1"))) {

				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "88");
				response.put("pesan",
						"Oops.. Nomor KTP " + ktp + " ini sudah ada, Selesaikan dulu pengajuan sebelumnya ya !!!");
			} else {

				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No KTP " + ktp + "  ini gak ada, coba yang lain ya !!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND INQUIRY DEBITUR ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/pencairan/test")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> testPolis(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException, ParseException {
		Map<String, Object> response = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND TEST ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		String ktp = param.get("ktp");
		Loan deb = loanRepository.findByKtpDebitur(ktp);
		if (deb == null) {
			response.put("kode", "10");
			response.put("pesan", "KTP TIDAK DITEMUKAN");
			String resT = objectMapper.writeValueAsString(response);
			LOGGER.info("**** CARI DEBITUR **** " + resT + "\n");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			String token = getToken();
			String URI = url_siap;
			System.out.println("===== TOKEN ===== \n" + token);
			if (token.isEmpty()) {
				response.put("kode", "99");
				response.put("pesan", "GAGAL GET TOKEN");
				String resT = objectMapper.writeValueAsString(response);
				LOGGER.info("**** GAGAL GET TOKEN **** " + resT + "\n");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				JsonObject dJson = new JsonObject();
				dJson.addProperty("ktp", deb.getId_debitur());
				dJson.addProperty("plafond", deb.getPlafon_pengajuan().longValue());
				dJson.addProperty("tenor", deb.getTenor_pengajuan());

				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.set("Content-Type", "application/json");
				httpHeaders.set("Authorization", "Bearer " + token);

				HttpEntity<String> httpEntity = new HttpEntity<>(dJson.toString(), httpHeaders);
				System.out.println("httpEntity :" + httpEntity);
				LOGGER.info("######################### REQUEST TO SIAP ######################## " + ktp + " @ "
						+ dJson.toString() + " to [" + URI + "]" + " \n");
				ResponseEntity<String> hasil = restTemplate.postForEntity(URI, httpEntity, String.class);
				System.out.println("hasil :" + hasil);
				LOGGER.info("######################### RESPONSE FROM SIAP ######################## " + ktp + " @ "
						+ hasil + " \n");
				JsonObject jsonArray = new JsonParser().parse(hasil.getBody().toString()).getAsJsonObject();
				JsonObject result = jsonArray.getAsJsonObject();
//				JsonObject data = result.get("Result").getAsJsonObject();
				System.out.println("PESAN :" + result.get("pesan"));

				if (result.get("kode").getAsString().equals("00")) {
					response.put("kode", "00");
					response.put("pesan", result.get("pesan").getAsString());
				} else {
					response.put("kode", result.get("kode").getAsString());
					response.put("pesan", result.get("pesan").getAsString());
				}

			}
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND TEST ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

//PENCAIRAN
	@PostMapping("/pencairan/detaildebitur/generatenoakad")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setnoakad(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND GENERATE NO AKAD ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				DateTimeFormatter formatteryear = DateTimeFormatter.ofPattern("yyyy");
				LocalDate year = LocalDate.now();
				String produk = loan.getId_produk().toString();
				String sub_produk = loan.getId_sub_produk().toString();
				String jenis_penggunaan = "3";
				if (produk.equals("1")) {
					jenis_penggunaan = "3";
				} else if (produk.equals("2")) {
					if (sub_produk.equals("51")||sub_produk.equals("52")||sub_produk.equals("53")||sub_produk.equals("54")||
							sub_produk.equals("55")||sub_produk.equals("56")||sub_produk.equals("45")||sub_produk.equals("90")||
							sub_produk.equals("91")||sub_produk.equals("92")) {
						jenis_penggunaan = "1";
					} else if (sub_produk.equals("41")||sub_produk.equals("42")||sub_produk.equals("44")||sub_produk.equals("61")||
							sub_produk.equals("62")||sub_produk.equals("63")||sub_produk.equals("71")||sub_produk.equals("72")||
							sub_produk.equals("74")) {
						jenis_penggunaan = "2";
					}
						
				}
				String sektor_usaha = loan.getData_bi().get(0).getKode_akad();
				if (loan.getNomor_akad() == null) {
				
				String lastAkad = loanRepository.getMaxNoAkad(loan.getId_cab(), year.format(formatteryear).toString());
				Par_cabang inisial = par_cabangRepository.findByIdCab(loan.getId_cab());
				Optional<Par_plan> id_akad = par_planRepository.findById(loan.getId_plan());
				Integer maxAkad = 1;
				if (lastAkad != null) {
					maxAkad = Integer.parseInt(lastAkad) + 1;
				}
				String noReg = String.format("%7s", maxAkad).replace(' ', '0');

				String no_akad = noReg + "." + jenis_penggunaan + "." + sektor_usaha + "."
						+ year.format(formatteryear)+ "." +loan.getId_cab();
				

					if (Integer.parseInt(loan.getStatus()) >= 13) {
						System.out.println("##### DATA LOAN ##### ");
						loan.setNomor_akad(no_akad);
						loan.setAkad_by(user_nama);
						loanRepository.save(loan);
	
						response.put("kode", "00");
						response.put("pesan", "GENERATE BERHASIL");
						response.put("nomor_akad", no_akad);
					} else if (Integer.parseInt(loan.getStatus()) < 13) {
						response.put("kode", "17");
						response.put("pesan", "Oops.. Anda belum diperbolehkan akad !!!");
					} else {
						response.put("kode", "13");
						response.put("pesan", "Oops.. Status pembiayaan tidak sesuai!!!");
					}
				} else {
					String reg = loan.getNomor_akad().substring(0,7);
					String no_akad = reg + "." + jenis_penggunaan + "." + sektor_usaha + "."
							+ year.format(formatteryear)+ "." +loan.getId_cab();
					
					loan.setNomor_akad(no_akad);
					loan.setAkad_by(user_nama);
					loanRepository.save(loan);
					
					response.put("kode", "00");
					response.put("pesan", "SUDAH PERNAH REGISTER");
					response.put("nomor_akad", no_akad);
				}
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND GENERATE NO AKAD ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/pencairan/detaildebitur/generatetglakad")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> settglakad(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND GENERATE TGL AKAD ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String bawa_pasangan = param.get("bawa_pasangan");
		LocalDate date = param.get("tgl_akad").equals("") ? null : LocalDate.parse(param.get("tgl_akad"), formatter);
		LocalDateTime tgl_akad = date.atStartOfDay();
		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
			if (loan != null) {

//				LocalDateTime tgl_akad = LocalDateTime.now();
//				LocalDateTime tgl_akad = LocalDateTime.parse(now.format(formatter));

				if (Integer.parseInt(loan.getStatus()) >= 13) {
					System.out.println("##### DATA LOAN ##### ");
					loan.setAkad_by(user_nama);
					loan.setBawa_pasangan(bawa_pasangan);
					loan.setAkad_date(tgl_akad);
					loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "GENERATE BERHASIL");
					response.put("tanggal_akad", tgl_akad);
				} else if (Integer.parseInt(loan.getStatus()) < 13) {
					response.put("kode", "17");
					response.put("pesan", "Oops.. Anda belum diperbolehkan akad !!!");
				} else {
					response.put("kode", "13");
					response.put("pesan", "Oops.. Status pembiayaan tidak sesuai!!!");
				}
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND GENERATE TGL AKAD ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/pencairan/akad/submit")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setSubmit(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND SUBMIT AKAD ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String tgl_lahir = param.get("tgl_lahir");
		String benefit = param.get("benefit").toString().equals("") ? "1" : param.get("benefit").toString();
		String plafon = param.get("plafon");
		String tenor = param.get("tenor");
		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);

			if (loan != null) {
					String token = getToken();
					String URI = url_siap;
					System.out.println("===== TOKEN ===== \n" + token);
					if (token.isEmpty()) {
						response.put("kode", "99");
						response.put("pesan", "GAGAL GET TOKEN");
						String resT = objectMapper.writeValueAsString(response);
						LOGGER.info("**** GAGAL GET TOKEN **** " + resT + "\n");
						return new ResponseEntity<>(response, HttpStatus.OK);
					} else {
						JsonObject dJson = new JsonObject();
						dJson.addProperty("ktp", loan.getId_debitur());
						dJson.addProperty("plafond", plafon);
						dJson.addProperty("tenor", tenor);
						dJson.addProperty("tgl_lahir", tgl_lahir);
						dJson.addProperty("benefit", benefit);

						RestTemplate restTemplate = new RestTemplate();
						HttpHeaders httpHeaders = new HttpHeaders();
						httpHeaders.set("Content-Type", "application/json");
						httpHeaders.set("Authorization", "Bearer " + token);

						HttpEntity<String> httpEntity = new HttpEntity<>(dJson.toString(), httpHeaders);
						System.out.println("httpEntity :" + httpEntity);
						LOGGER.info("######################### REQUEST TO SIAP ######################## " + id_loan
								+ " @ " + dJson.toString() + " to [" + URI + "]" + " \n");
						ResponseEntity<String> hasil = restTemplate.postForEntity(URI, httpEntity, String.class);
						System.out.println("hasil :" + hasil);
						LOGGER.info("######################### RESPONSE FROM SIAP ######################## " + id_loan
								+ " @ " + hasil + " \n");
						JsonObject jsonArray = new JsonParser().parse(hasil.getBody().toString()).getAsJsonObject();
						JsonObject result = jsonArray.getAsJsonObject();
						System.out.println("PESAN :" + result.get("pesan"));

						if (result.get("kode").getAsString().equals("00")) {
							System.out.println("##### DATA LOAN ##### ");
							loan.setStatus("17");
							loan.setSubmit2_by(user_id);
							loan.setSubmit2_date(LocalDateTime.now());
							loanRepository.save(loan);

							response.put("kode", "00");
							response.put("pesan", "SUBMIT BERHASIL");
						} else {
							response.put("kode", result.get("kode").getAsString());
							response.put("pesan", result.get("pesan").getAsString());
						}

					}

				
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");

			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND SUBMIT AKAD ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/pencairan/akad/setnorekening")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setCair(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /pencairan/akad/setnorekening ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		BigInteger norek_pencairan = new BigInteger(param.get("norek_pencairan"));
		// spek vlink
		String nama_rekening_pencairan = param.get("nama_rekening_pencairan");
		String kode_rekening_pencairan = param.get("kode_rekening_pencairan");
		String norek_proceed = param.get("norek_proceed");
		String kode_rekening_proceed = param.get("kode_rekening_proceed");
		String nama_rekening_proceed = param.get("nama_rekening_proceed");
		String norek_loan = param.get("norek_loan");
		String kode_rekening_loan = param.get("kode_rekening_loan");
		String nama_rekening_loan = param.get("nama_rekening_loan");
		String norek_nasabah = param.get("norek_nasabah");
		String kode_rekening_nasabah = param.get("kode_rekening_nasabah");
		String nama_rekening_nasabah = param.get("nama_rekening_nasabah");
		String cif = param.get("cif");

		try {
			Loan loan = loanRepository.findByIdLoan(id_loan); 
			if (loan != null) {
				 
					System.out.println("##### DATA LOAN ##### ");
					loan.setStatus("16");
					loan.setCair_by(user_id);
					loan.setNorek_pencairan(norek_pencairan);
					// loan.setSubmit_nama(user_nama);
					loan.setCair_date(LocalDateTime.now());
					// spek vlink
					loan.setNama_rekening_pencairan(nama_rekening_pencairan);
					loan.setKode_rekening_pencairan(kode_rekening_pencairan);
					loan.setNorek_proceed(norek_proceed);
					loan.setKode_rekening_proceed(kode_rekening_proceed);
					loan.setNama_rekening_proceed(nama_rekening_proceed);
					loan.setNorek_loan(norek_loan);
					loan.setKode_rekening_loan(kode_rekening_loan);
					loan.setNama_rekening_loan(nama_rekening_loan);
					if (norek_nasabah.length()>0) {
						loan.setNorek_urbun(norek_nasabah);
						loan.setNama_rekening_urbun(nama_rekening_nasabah);
						loan.setKode_rekening_urbun(kode_rekening_nasabah);
					}
					
					
					loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "INPUT BERHASIL");
		            
				
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");

			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /pencairan/akad/setnorekening ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/pencairan/agunan/manual")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> cairManualAgunan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND KIRIM AGUNAN ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		Integer no_urut = Integer.parseInt(param.get("no_urut"));
		String id_loan = param.get("id_loan");
//		String user_id = param.get("user_id");
//		String user_nama = param.get("user_nama");
		Random random = new Random(System.nanoTime() % 100000);
		int randomInt = random.nextInt(1000000);
		Random random1 = new Random(System.nanoTime() % 100000);
		int randomInt1 = random1.nextInt(1000000);
		String stan = String.format("%6s", Integer.toString(randomInt)).replace(' ', '0');
		String stan2 = String.format("%6s", Integer.toString(randomInt1)).replace(' ', '0');

		DateFormat dateGMT = new SimpleDateFormat("MMddHHmmss");
		Date tanggal = new Date();
		dateGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
		String GMT = dateGMT.format(tanggal);

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMdd");
		LocalTime time = LocalTime.now();
		DateTimeFormatter formattertime = DateTimeFormatter.ofPattern("HHmmss");
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formattergmt = DateTimeFormatter.ofPattern("MMddHHmmss");
		LocalDate year = LocalDate.now();
		DateTimeFormatter formatteryear = DateTimeFormatter.ofPattern("yyyy");
		String rrn = stan + stan;
		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);

			if (loan != null) {
				Debitur debitur = debiturRepository.findByKtpDebitur(loan.getId_debitur());
				Agunan agunan = agunanRepository.findByUrut(no_urut);
				
				String no_collateral = String.format("%09d", 0);
				String cab_collateral = String.format("%5s", loan.getId_cab()).replace(' ', '0');
				String collateral_class = String.format("%-6s", (agunan.getJenis()==null) ? "" : agunan.getJenis());
				String last_val_date = (agunan.getTgl_penilaian_terakhir()==null) ? "0" : agunan.getTgl_penilaian_terakhir().toString().replace("-", "");
				String last_valuation_date = String.format("%8s", last_val_date).replace(" ", "0");
				String c_exp_date = (agunan.getTgl_expired_agunan()==null) ? "0" : agunan.getTgl_expired_agunan().toString().replace("-", "");
				
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				Calendar cal1 = Calendar.getInstance();
				Date date2 = cal1.getTime();
				cal1.setTime(date2);
				cal1.add(Calendar.MONTH, loan.getTenor_disetujui());
				date2 = cal1.getTime();
				String tgl_akhir_pembiayaan = dateFormat.format(date2);
				
				String coll_exp_date = String.format("%8s", tgl_akhir_pembiayaan).replace(" ", "0");
				String user_entry =  String.format("%-10s", (agunan.getUser_entry()==null) ? "" : agunan.getUser_entry());
				String collateral_desc =  String.format("%-256s", (agunan.getDeskripsi_agunan()==null) ? "" : agunan.getDeskripsi_agunan().replace("\u00B2", "2"));
				if(collateral_desc.length() > 256) collateral_desc = collateral_desc.substring(0, 256);
				String mortgage_priority =  String.format("%-1s", (agunan.getPrioritas_agunan()==null) ? "" : agunan.getPrioritas_agunan());
				String allow_accts_attached =  String.format("%-1s", (agunan.getIzin_pengkaitan()==null) ? "" : agunan.getIzin_pengkaitan());
				String coverage_of_obligation =  String.format("%-1s", (agunan.getCakupan_kewajiban()==null) ? "" : agunan.getCakupan_kewajiban());
				String coll_status =  String.format("%-1s", (agunan.getStatus_agunan()==null) ? "" : agunan.getStatus_agunan());
				String status_of_accounting =  String.format("%-1s", (agunan.getStatus_akutansi_agunan()==null) ? "" : agunan.getStatus_akutansi_agunan());
				String cust_or_bank =  String.format("%-1s", (agunan.getBank_atau_nasabah()==null) ? "" : agunan.getBank_atau_nasabah());
				String collateral_util =  String.format("%-1s", (agunan.getKegunaan_agunan()==null) ? "" : agunan.getKegunaan_agunan());
				String insurance_comp_code1 =  String.format("%-6s", (agunan.getKode_asuransi1()==null) ? "" : agunan.getKode_asuransi1());
				String insurance_comp_code2 =  String.format("%-6s", (agunan.getKode_asuransi2()==null) ? "" : agunan.getKode_asuransi2());
				String collateral_class_bi =  String.format("%-3s", (agunan.getJenis_agunan_bi()==null) ? "" : agunan.getJenis_agunan_bi());
				String penilaian_oleh =  String.format("%-1s", (agunan.getPenilaian_oleh()==null) ? "" : agunan.getPenilaian_oleh());
				//String jumlah_bayar_regular = String.format("%15s", loan.getAngsuran_disetujui().toString().replace(".", "")).replace(' ', '0');
				String ins_start_date = (agunan.getTgl_awal_asuransi1()==null) ? "0" : agunan.getTgl_awal_asuransi1().toString().replace("-", "");
				String insurance1_start_date =  String.format("%8s", ins_start_date).replace(" ", "0");
				String ins_end_date = (agunan.getTgl_akhir_asuransi1()==null) ? "0" : agunan.getTgl_akhir_asuransi1().toString().replace("-", "");
				String insurance1_end_date =  String.format("%8s", ins_end_date).replace(" ", "0");
				
				String insurance2_start_date =  String.format("%8s", "00000000");
				String insurance2_end_date =  String.format("%8s", "00000000");
				String telp_of_appraiser =  String.format("%11s", (agunan.getTelp_penilai()==null) ? "0" : agunan.getTelp_penilai()).replace(' ', '0');
				if(telp_of_appraiser.length() > 11) telp_of_appraiser = telp_of_appraiser.substring(0, 11);
				String appraiser_company =  String.format("%-40s", (agunan.getPerusahaan_penilai()==null) ? "" : agunan.getPerusahaan_penilai());
				String address1 =  String.format("%-35s", (agunan.getAlamat1()==null) ? "" : agunan.getAlamat1());
				if(address1.length() > 35) address1 = address1.substring(0, 35);
				String address2 =  String.format("%-35s", (agunan.getAlamat2()==null) ? "" : agunan.getAlamat2());
				if(address2.length() > 35) address2 = address2.substring(0, 35);
				String address3 =  String.format("%-35s", (agunan.getAlamat3()==null) ? "" : agunan.getAlamat3());
				if(address3.length() > 35) address3 = address3.substring(0, 35);
				String address4 =  String.format("%-35s", (agunan.getAlamat4()==null) ? "" : agunan.getAlamat4());
				if(address4.length() > 35) address4 = address4.substring(0, 35);

				String dati2 =  String.format("%-6s", (agunan.getBi_dati2()==null) ? "" : String.format("%4s", agunan.getBi_dati2()).replace(' ', '0'));
				String name_of_lawyer =  String.format("%-40s", (agunan.getNama_pengacara()==null) ? "" : agunan.getNama_pengacara());
				if(name_of_lawyer.length() > 40) name_of_lawyer = name_of_lawyer.substring(0, 40);
				System.out.println("############### collateral_value "+agunan.getHarga_pasar());
				System.out.println("############### landing_value "+agunan.getNilai_taksasi());
				System.out.println("############### collateral_limit "+agunan.getLimit_agunan_bi());
				System.out.println("############### insurance_amount1 "+agunan.getAsuransi_amount1());
				
				String collateral_value =  String.format("%13s", (agunan.getHarga_pasar()==null) ? "0" : agunan.getHarga_pasar().longValue()).replace(' ', '0')+"00";
				String landing_value =  String.format("%13s", (agunan.getNilai_taksasi()==null) ? "0" : agunan.getNilai_taksasi().longValue()).replace(' ', '0')+"00";
				String collateral_limit =  String.format("%13s", (agunan.getLimit_agunan_bi()==null) ? "0" : agunan.getLimit_agunan_bi().longValue()).replace(' ', '0')+"00";
				String insurance_amount1 =  String.format("%13s", (agunan.getAsuransi_amount1()==null) ? "0" : agunan.getAsuransi_amount1().longValue()).replace(' ', '0')+"00";
//				String insurance_amount2 =  String.format("%8s", (agunan.getAsuransi_amount2()==null) ? "0" : agunan.getAsuransi_amount2()).replace(' ', '0');
				
				LocalDateTime tgl_akad1 = loan.getAkad_date();
				DateTimeFormatter formattertglakad = DateTimeFormatter.ofPattern("yyyyMMdd");
				String tgl_akad_agunan = tgl_akad1.format(formattertglakad);
				
				String entry_date =  String.format("%8s", tgl_akad_agunan);
				String entry_workstation =  String.format("%-10s", (agunan.getWorkstation()==null) ? "" : agunan.getWorkstation());
				String date_last_maintenance =  String.format("%8s", tgl_akad_agunan).replace(' ', '0');
				String liquidation_amount =  String.format("%13s", (agunan.getLiquidation_amount()==null) ? "0" : agunan.getLiquidation_amount().longValue()).replace(' ', '0')+"00";
				String apht_dt = (agunan.getTgl_apht()==null) ? "0" : agunan.getTgl_apht().toString().replace("-", "");
				String apht_date =  String.format("%8s", apht_dt).replace(" ", "0");
				String apht_amount =  String.format("%13s", (agunan.getApht_amount()==null) ? "0" : agunan.getApht_amount().longValue()).replace(' ', '0')+"00";
				String channel_code =  String.format("%-6s", (agunan.getKode_kanal()==null) ? "" : agunan.getKode_kanal());
				String document_type_code =  String.format("%-6s", (agunan.getKode_tipe_dokumen()==null) ? "0" : agunan.getKode_tipe_dokumen());
				String document_number =  String.format("%-20s", (agunan.getNomor_dokumen()==null) ? "0" : agunan.getNomor_dokumen());
				String kode_pengikatan_internal =  String.format("%-6s", (agunan.getKode_pengikatan_internal()==null) ? "" : agunan.getKode_pengikatan_internal());
				String kode_pengikatan_notarial =  String.format("%-6s", (agunan.getKode_pengikatan_notarial()==null) ? "" : agunan.getKode_pengikatan_notarial());
				
				LocalDate tgl_pengikat_intern = agunan.getTgl_pengikatan_internal();
				LocalDate tgl_pengikat_notar = agunan.getTgl_pengikatan_notarial();
				DateTimeFormatter formattertglikat = DateTimeFormatter.ofPattern("yyyyMMdd");
				String tgl_peng_int = "";
				
				if (agunan.getTgl_pengikatan_internal()==null) {
					tgl_peng_int = "00000000";
				} else {
					tgl_peng_int = tgl_pengikat_intern.format(formattertglikat);
				}
				
				String tgl_peng_notar = "";
				if (agunan.getTgl_pengikatan_notarial()==null) {
					tgl_peng_notar = "00000000";
				} else {
					tgl_peng_notar = tgl_pengikat_notar.format(formattertglikat);;
				}
				
				String tgl_pengikatan_internal =  String.format("%8s", tgl_peng_int);
				String tgl_pengikatan_notarial =  String.format("%8s", tgl_peng_notar);
				String nomor_pengikatan_internal =  String.format("%-25s", (agunan.getNomor_pengikatan_internal()==null) ? "" : agunan.getNomor_pengikatan_internal());
				String nomor_pengikatan_notarial =  String.format("%-25s", (agunan.getNomor_pengikatan_notarial()==null) ? "" : agunan.getNomor_pengikatan_notarial());
				String jumlah_agunan =  String.format("%-40s", (agunan.getJumlah_agunan()==null) ? "" : agunan.getJumlah_agunan());
				String agree_date = agunan.getAgreement_date()==null ? " " : agunan.getAgreement_date().toString().replace("-", "");
				String agreement_date =  String.format("%8s", agree_date).replace(' ', '0');
				String jenis_agunan_ppap =  String.format("%-6s", (agunan.getJenis_agunan_ppap()==null) ? "" : agunan.getJenis_agunan_ppap());
				String date_collateral_registered =  String.format("%8s", tgl_akad_agunan);
				String utilized_amount =  String.format("%13s", (agunan.getUtilized_amount()==null) ? "" : agunan.getUtilized_amount().longValue()).replace(' ', '0')+"00";
				String cif = String.format("%15s", (debitur.getCif()==null) ? "" : debitur.getCif()).replace(' ', '0');
				String rek_loan = String.format("%11s", (loan.getNorek_loan()==null) ? "" : loan.getNorek_loan()).replace(' ', '0');
				String kodecab_loan = String.format("%5s", (loan.getId_cab()==null) ? "" : loan.getId_cab()).replace(' ', '0');
				String basel2 = String.format("%3s", (agunan.getBasel2_jenis_agunan()==null) ? "" : agunan.getBasel2_jenis_agunan()).replace(' ', '0');
				String sifat_agunan = String.format("%1s", (agunan.getSifat_agunan()==null) ? "" : agunan.getSifat_agunan()).replace(' ', '0');
				String pnrbt_agun = agunan.getPenerbit_agunan()==null ? " " : agunan.getPenerbit_agunan();
				String penerbit_agunan =  String.format("%3s", pnrbt_agun);
				String cash_non_cash = String.format("%1s", (agunan.getCash_non_cash()==null) ? "" : agunan.getCash_non_cash()).replace(' ', '0');
				String kepemilikan=  String.format("%-25s", (agunan.getKepemilikan()==null) ? "" : agunan.getKepemilikan());
				if(kepemilikan.length() > 25) kepemilikan = kepemilikan.substring(0, 25);
				String account =  String.format("%11s", (loan.getNorek_pencairan()==null) ? "" : loan.getNorek_pencairan()).replace(" ", "0");
				String entry_time = String.format("%6s", time.format(formattertime)).replace(' ', '0');
				
				String BIT120_agun = no_collateral+cab_collateral+collateral_class+last_valuation_date+coll_exp_date+user_entry+
						collateral_desc+mortgage_priority+allow_accts_attached+coverage_of_obligation+coll_status+status_of_accounting+
						cust_or_bank+collateral_util+insurance_comp_code1+insurance_comp_code2+collateral_class_bi+penilaian_oleh+
						insurance1_start_date+insurance1_end_date+insurance2_start_date+insurance2_end_date+telp_of_appraiser+
						appraiser_company+address1+address2+address3+address4+dati2+name_of_lawyer+collateral_value+landing_value+
						collateral_limit+insurance_amount1+entry_date+entry_workstation+date_last_maintenance+
						liquidation_amount+apht_date+apht_amount+channel_code+document_type_code+document_number+kode_pengikatan_internal+
						kode_pengikatan_notarial+tgl_pengikatan_internal+tgl_pengikatan_notarial+nomor_pengikatan_internal+
						nomor_pengikatan_notarial+jumlah_agunan+agreement_date+jenis_agunan_ppap+date_collateral_registered+
						utilized_amount+cif+rek_loan+kodecab_loan+basel2+sifat_agunan+penerbit_agunan+cash_non_cash+kepemilikan+account
						+entry_time;
					
					// ########################################
					String url1 = "http://"+ip+":"+port;
					JsonObject message1 = new JsonObject();
					JsonObject data1 = new JsonObject();
					data1.addProperty("mti", "0200");
					data1.addProperty("pan", "0000000000000000");
					data1.addProperty("pcode", "660005");
					data1.addProperty("amount", "000000000000");
					data1.addProperty("gmt", dateTime.format(formattergmt));
					data1.addProperty("stan", stan2);
					data1.addProperty("time", time.format(formattertime));
					data1.addProperty("date", date.format(formatter));
					data1.addProperty("merchant", "6010");
					data1.addProperty("acquirer", "627492");
					data1.addProperty("fwd", "600100");
					data1.addProperty("rrn", stan2 + stan2);
					data1.addProperty("termid", "A01");
					data1.addProperty("termloc", "EFOSBRK");
					data1.addProperty("termno", "000119");
					data1.addProperty("paydata", "");
					data1.addProperty("billdata", "AGUNAN " + id_loan);
					data1.addProperty("ccode", "360");
					data1.addProperty("infdata", year.format(formatteryear));
					data1.addProperty("fromacct", "");
					data1.addProperty("toacct", "");
					data1.addProperty("appendix1", BIT120_agun);
					message1.add("RiauTRX", data1);
					LOGGER.info("\n ************** JSON TO VLINK AGUNAN ************** " + "@ "
							+ message1.toString() + " \n");
					RestTemplate restTemplate1 = new RestTemplate();
					HttpHeaders httpHeaders1 = new HttpHeaders();
					httpHeaders1.set("Content-Type", "application/json");
					HttpEntity<String> httpEntity1 = new HttpEntity<>(message1.toString(), httpHeaders1);
			        String answer1 = restTemplate1.postForObject(url1, httpEntity1, String.class);

			        JsonObject jsonObject1 = new JsonParser().parse(answer1).getAsJsonObject();
		            jsonObject1 = jsonObject1.getAsJsonObject("RiauTRXResponse");
		            jsonObject1 = jsonObject1.getAsJsonObject("return");

		            System.out.println("Respon Dari VLINK AGUNAN : " + jsonObject1 + "\n\n");
					// ########################################
		            String rcode1 = jsonObject1.get("rcode").getAsString();
		            String reason1 = jsonObject1.get("reason").getAsString();
		            
		            if (rcode1.equals("00")) {
		            	response.put("kode", "00");
						response.put("pesan", "PROSES AGUNAN BERHASIL");
		            } else {
		            	response.put("kode", rcode1);
						response.put("pesan", reason1);
		            }
		            
		            agunan.setStatus_vlink(rcode1);
	            	agunanRepository.save(agunan);
				
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND SUBMIT AKAD ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/pencairan/cair/approve1")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setApprove1(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND APPROVE PENCAIRAN 1 ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String cab = param.get("cab");
		
		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);

			if (loan != null) {
				
				if (Integer.parseInt(loan.getStatus()) == 17) {
					Optional<Par_cabang> datacab = par_cabangRepository.findById(cab);
					String sts = "17";
					if (datacab.isPresent() && datacab.get().getJenis_cab().equals("CABANG")
							&& datacab.get().getLevel_approve().equals("4")) {
						sts = "18";
					} else {
						sts = "19";
					}

			
					System.out.println("##### DATA LOAN ##### ");
					loan.setStatus(sts);
					loan.setApprove_cair1_by(user_id);
					loan.setApprove_cair1_date(LocalDateTime.now());
					loan.setApprove_cair1_name(user_nama);
					loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "APPROVE PENCAIRAN BERHASIL");
		            
				} else if (Integer.parseInt(loan.getStatus()) < 17) {
					response.put("kode", "15");
					response.put("pesan", "Oops.. Debitur belum akad !!!");
				} else if (Integer.parseInt(loan.getStatus()) == 5) {
					response.put("kode", "16");
					response.put("pesan", "Oops.. Debitur belum di Review !!!");
				} else {
					response.put("kode", "13");
					response.put("pesan", "Oops.. Status pembiayaan tidak sesuai!!!");
				}
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND APPROVE PENCAIRAN 1 ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/pencairan/cair/approve2")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setApprove2(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND APPROVE PENCAIRAN 2 ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String cab = param.get("cab");
		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
//			Optional<Par_cabang> datacab = par_cabangRepository.findById(cab);
			String sts = "19";

			if (loan != null) {
				if (Integer.parseInt(loan.getStatus()) == 18) {
					System.out.println("##### DATA LOAN ##### ");
					loan.setStatus(sts);
					loan.setApprove_cair2_by(user_id);
					loan.setApprove_cair2_name(user_nama);
					loan.setApprove_cair2_date(LocalDateTime.now());
					loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "APPROVE BERHASIL");
				} else if (Integer.parseInt(loan.getStatus()) < 18) {
					response.put("kode", "15");
					response.put("pesan", "Oops.. Debitur belum di Aprove 1 !!!");
				} else if (Integer.parseInt(loan.getStatus()) == 5) {
					response.put("kode", "16");
					response.put("pesan", "Oops.. Debitur belum di Review !!!");
				} else {
					response.put("kode", "13");
					response.put("pesan", "Oops.. Status pembiayaan tidak sesuai!!!");
				}
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND APPROVE PENCAIRAN 2 ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/pencairan/cair/approve3")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setApprove3(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND APPROVE PENCAIRAN 3 ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String cab = param.get("cab");
		String user_nama = param.get("user_nama");
		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				if (Integer.parseInt(loan.getStatus()) == 19) {
					System.out.println("##### DATA LOAN ##### ");
					loan.setStatus("20");
					loan.setApprove_cair3_by(user_id);
					loan.setApprove_cair3_name(user_nama);
					loan.setApprove_cair3_date(LocalDateTime.now());
					loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "APPROVE BERHASIL");
				} else if (Integer.parseInt(loan.getStatus()) < 19) {
					response.put("kode", "15");
					response.put("pesan", "Oops.. Debitur belum di Aprove !!!");
				} else if (Integer.parseInt(loan.getStatus()) == 5) {
					response.put("kode", "16");
					response.put("pesan", "Oops.. Debitur belum di Review !!!");
				} else {
					response.put("kode", "13");
					response.put("pesan", "Oops.. Status pembiayaan tidak sesuai!!!");
				}
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND APPROVE 3 ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/pencairan/ceklist/save")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> saveCeklist(@Valid @RequestBody String param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> response = new HashMap<>();

		try {
			JSONObject jsonObject = new JSONObject(param.toString());
			String json = jsonObject.toString();
			LOGGER.info("\n ************** REQUEST FROM FRONTEND SAVE CEKLIST PENCAIRAN ************** " + "@ " + json
					+ " \n");

			JSONArray ceklis_admin = new JSONArray(jsonObject.getString("ceklis_admin"));
			Integer total = 0;
			if (ceklis_admin.length() >= 1) {
				for (int i = 0; i < ceklis_admin.length(); i++) {
					JSONObject aArray = ceklis_admin.getJSONObject(i);
					LOGGER.info("!! GET DETAIL CEKLIST ADMIN !! " + "@ " + i + " \n");
					Data_lampiran_file har = lampiran_fileRepository
							.findByIdSelect(Integer.valueOf(aArray.getString("id")));
					har.setCeklis_admin(Integer.valueOf(aArray.getString("status")));
					lampiran_fileRepository.save(har);

					Integer jlh = Integer.valueOf(aArray.getString("status"));
					total = total + jlh;
				}
			}
			System.out.println(total);
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND SAVE CEKLIST ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/pencairan/cair/proses1")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setProses1(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND PROSES PENCAIRAN 1 ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		ResponseMessage send = null;
		ResponseMessage send_agunan = null;
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String cab = param.get("cab");

	
		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				if (Integer.parseInt(loan.getStatus()) == 17) {
					send = posting(id_loan, user_id);
					String vlk = send.getKode();
					if (vlk.equals("00")) {
						System.out.println("##### DATA LOAN ##### ");
						loan.setStatus("20");
						loan.setApprove_cair1_by(user_id);
						loan.setApprove_cair1_date(LocalDateTime.now());
						loan.setApprove_cair1_name(user_nama);
						loan.setStatus_vlink("00");
						loanRepository.save(loan);

						send_agunan = posting_agunan(id_loan, user_id);
						String vlk_agunan = send_agunan.getKode();
						
						if (vlk_agunan.equals("00")) {
							response.put("kode", "00");
							response.put("pesan", "APPROVE BERHASIL");
			        	} else {
			        		response.put("kode", "00");
							response.put("pesan", "APPROVE PENCAIRAN BERHASIL, GAGAL POSTING AGUNAN SILAHKAN CEK DAN POSTING ULANG AGUNAN YANG BELUM DIPOSTING");
			        	}
					} else {
						response.put("kode", vlk);
						response.put("pesan", send.getPesan());
					}
					
		            
				} else if (Integer.parseInt(loan.getStatus()) < 17) {
					response.put("kode", "15");
					response.put("pesan", "Oops.. Debitur belum akad !!!");
				} else if (Integer.parseInt(loan.getStatus()) == 5) {
					response.put("kode", "16");
					response.put("pesan", "Oops.. Debitur belum di Review !!!");
				} else {
					response.put("kode", "13");
					response.put("pesan", "Oops.. Status pembiayaan tidak sesuai!!!");
				}
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			LOGGER.info("\n ************** RESPONSE TO FRONTEND(Exception) /pencairan/cair/proses1 ************** @ "+ e +  " \n");
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND PROSES PENCAIRAN 1 ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/pencairan/cair/proses2")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setProses2(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND PROSES PENCAIRAN 2 ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		ResponseMessage send = null;
		ResponseMessage send_agunan = null;
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String cab = param.get("cab");
		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);

			if (loan != null) {
				if (Integer.parseInt(loan.getStatus()) == 18) {
					send = posting(id_loan, user_id);
					String vlk = send.getKode();
					if (vlk.equals("00")) {
						System.out.println("##### DATA LOAN ##### ");
						loan.setStatus("20");
						loan.setApprove_cair2_by(user_id);
						loan.setApprove_cair2_name(user_nama);
						loan.setApprove_cair2_date(LocalDateTime.now());
						loan.setStatus_vlink("00");
						loanRepository.save(loan);
	
						send_agunan = posting_agunan(id_loan, user_id);
						String vlk_agunan = send_agunan.getKode();
						
						if (vlk_agunan.equals("00")) {
							response.put("kode", "00");
							response.put("pesan", "APPROVE BERHASIL");
			        	} else {
			        		response.put("kode", "00");
							response.put("pesan", "APPROVE PENCAIRAN BERHASIL, GAGAL POSTING AGUNAN SILAHKAN CEK DAN POSTING ULANG AGUNAN YANG BELUM DIPOSTING");
			        	}
					} else {
						response.put("kode", vlk);
						response.put("pesan", send.getPesan());
					}
				} else if (Integer.parseInt(loan.getStatus()) < 18) {
					response.put("kode", "15");
					response.put("pesan", "Oops.. Debitur belum di Aprove 1 !!!");
				} else if (Integer.parseInt(loan.getStatus()) == 5) {
					response.put("kode", "16");
					response.put("pesan", "Oops.. Debitur belum di Review !!!");
				} else {
					response.put("kode", "13");
					response.put("pesan", "Oops.. Status pembiayaan tidak sesuai!!!");
				}
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			LOGGER.info("\n ************** RESPONSE TO FRONTEND(Exception) /pencairan/cair/proses2 ************** @ "+ e +  " \n");
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND PROSES PENCAIRAN 2 ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/pencairan/cair/proses3")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setProses3(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND PROSES PENCAIRAN 3 ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		ResponseMessage send = null;
		ResponseMessage send_agunan = null;
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String cab = param.get("cab");
		String user_nama = param.get("user_nama");
		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
//				if (Integer.parseInt(loan.getStatus()) == 19) {
					send = posting(id_loan, user_id);
					String vlk = send.getKode();
					if (vlk.equals("00")) {
						System.out.println("##### DATA LOAN ##### ");
						loan.setStatus("20");
						loan.setApprove_cair3_by(user_id);
						loan.setApprove_cair3_name(user_nama);
						loan.setApprove_cair3_date(LocalDateTime.now());
						loan.setStatus_vlink("00");
						loanRepository.save(loan);
	
						send_agunan = posting_agunan(id_loan, user_id);
						String vlk_agunan = send_agunan.getKode();
						
						if (vlk_agunan.equals("00")) {
							response.put("kode", "00");
							response.put("pesan", "APPROVE BERHASIL");
			        	} else {
			        		response.put("kode", "00");
							response.put("pesan", "APPROVE PENCAIRAN BERHASIL, GAGAL POSTING AGUNAN SILAHKAN CEK DAN POSTING ULANG AGUNAN YANG BELUM DIPOSTING");
			        	}
					} else {
						response.put("kode", vlk);
						response.put("pesan", send.getPesan());
					}
//				} else if (Integer.parseInt(loan.getStatus()) < 19) {
//					response.put("kode", "15");
//					response.put("pesan", "Oops.. Debitur belum di Aprove !!!");
//				} else if (Integer.parseInt(loan.getStatus()) == 5) {
//					response.put("kode", "16");
//					response.put("pesan", "Oops.. Debitur belum di Review !!!");
//				} else {
//					response.put("kode", "13");
//					response.put("pesan", "Oops.. Status pembiayaan tidak sesuai!!!");
//				}
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			LOGGER.info("\n ************** RESPONSE TO FRONTEND(Exception) /pencairan/cair/proses3 ************** @ "+ e +  " \n");
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND PROSES PENCAIRAN 3 ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/pencairan/cair/cancel")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setCancel(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND CANCEL PENCAIRAN ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String cab = param.get("cab");
		String user_nama = param.get("user_nama");
		String keterangan = param.get("keterangan");

		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
			if (loan != null) {

				System.out.println("##### DATA LOAN ##### ");
				loan.setStatus("21");
				loan.setReview_by(user_nama);
				loan.setReview_date(LocalDateTime.now());
				loan.setReview_desc(keterangan);
				loanRepository.save(loan);

				response.put("kode", "00");
				response.put("pesan", "APPROVE BERHASIL");

			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND CANCEL PENCAIRAN ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public String getToken() {
		String token_value = "";

//		String client_id = "siap";
//		String client_secret = "8fd80e2b-f859-4f60-bcfa-734d35d179b4";
//		String username = "Admin";
//		String password = "SiapKeycloak@119";
		String grand_type = "password";
		String scope = "roles";

		try {
			System.out.println("LINK :" + url_token);
			JsonObject token = new JsonObject();
			token.addProperty("client_id", client_id);
			token.addProperty("client_secret", client_secret);
			token.addProperty("username", username);
			token.addProperty("password", password);
			token.addProperty("grand_type", grand_type);
			token.addProperty("scope", scope);

			MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
			requestBody.add("grant_type", grand_type);
			requestBody.add("client_id", client_id);
			requestBody.add("client_secret", client_secret);
			requestBody.add("username", username);
			requestBody.add("password", password);

			RestTemplate restToken = new RestTemplate();
			HttpHeaders httpHeaderstoken = new HttpHeaders();
			httpHeaderstoken.set("Content-Type", "application/x-www-form-urlencoded");

			HttpEntity<MultiValueMap<String, String>> httpEntitytoken = new HttpEntity<>(requestBody, httpHeaderstoken);
			LOGGER.info("######################### REQUEST TOKEN TO KEYKLOACK ######################## @ "
					+ requestBody.toString() + " to [" + url_token + "]" + " \n");
			System.out.println("httpEntity :" + httpEntitytoken);
			ResponseEntity<String> gettoken = restToken.postForEntity(url_token, httpEntitytoken, String.class);
			LOGGER.info("######################### RESPONSE TOKEN FROM KEYKLOACK ######################## @ " + gettoken
					+ " \n");
			System.out.println("hasil :" + gettoken);
			System.out.println("STATUS CODE :" + gettoken.getStatusCodeValue());
			System.out.println("BODY :" + gettoken.getBody());
			JsonObject jsonArraytoken = JsonParser.parseString(gettoken.getBody()).getAsJsonObject();
			token_value = jsonArraytoken.get("access_token").getAsString();
			System.out.println("token_value :" + token_value);
			return token_value;
		} catch (RestClientException e) {
			LOGGER.info("===== EXCEPTION REST CLIENT GET TOKEN =====" + e.toString());
			token_value = "0";
		} catch (Exception e) {
			LOGGER.info("===== EXCEPTION GET TOKEN =====" + e.toString());
			token_value = "1";
		}
		return token_value;

	}

	@PostMapping("/pencairan/akad/uploadarsip")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setUpload(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND UPLOAD ARSIP  ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String akad_file = param.get("akad_file");
		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				
					System.out.println("##### DATA LOAN ##### ");
					loan.setAkad_file(akad_file);
					loan.setUpload_akad_by(user_nama);
					loan.setUpload_akad_date(LocalDateTime.now());
					loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "UPLOAD BERHASIL");
				
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND UPLOAD ARSIP ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/pencairan/calondebitur/inquiryloanbyid")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getInquiryloan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND INQUIRY PENCAIRAN LOAN BY ID ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
			if (loan != null) {

				LOGGER.info("!! LOAN (INQUIRY LOAN BY ID) !! " + "@ " + objectMapper.writeValueAsString(loan) + " \n");

				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
				response.put("loan", loan);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");

			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND INQUIRY  PENCAIRAN LOAN BY ID ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/pencairan/loan")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> pencairanLoan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND PENCAIRAN LOAN ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				LocalDate now = LocalDate.now();
				String tgl_akad = now.format(formatter);

			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND PENCAIRAN LAON ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public ResponseMessage posting(String id_loan, String user_id)
			throws ParserConfigurationException, RestClientException, DOMException, SAXException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseMessage res = new ResponseMessage();
		
		Random random = new Random(System.nanoTime() % 100000);
		int randomInt = random.nextInt(1000000);
//		Random random1 = new Random(System.nanoTime() % 100000);
//		int randomInt1 = random1.nextInt(1000000);
		String stan = String.format("%6s", Integer.toString(randomInt)).replace(' ', '0');
//		String stan2 = String.format("%6s", Integer.toString(randomInt1)).replace(' ', '0');

		DateFormat dateGMT = new SimpleDateFormat("MMddHHmmss");
		Date tanggal = new Date();
		dateGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
		String GMT = dateGMT.format(tanggal);

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMdd");
		LocalTime time = LocalTime.now();
		DateTimeFormatter formattertime = DateTimeFormatter.ofPattern("HHmmss");
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formattergmt = DateTimeFormatter.ofPattern("MMddHHmmss");
		LocalDate year = LocalDate.now();
		DateTimeFormatter formatteryear = DateTimeFormatter.ofPattern("yyyy");
		String rrn = stan + stan;

		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
			Pekerjaan pekerjaan = pekerjaanRepository.findByIdDebiturAndIdLoan(loan.getId_debitur(), id_loan);
			Debitur debitur = debiturRepository.findByKtpDebitur(loan.getId_debitur());
			Data_bi bi = data_biRepository.findByIdLoan(id_loan);
//			Par_sub_sub_dinas sub_sub_dinas = sub_sub_dinasRepository.findBySingleId(Integer.parseInt(pekerjaan.getKode_sub_sub_dinas()));
				
					String nomorrekeningload = (loan.getNorek_loan()==null) ? "" : loan.getNorek_loan();
					System.out.println("@@@@@@@@@@@@@@@@@ "+ nomorrekeningload);
					String cab_pindahan = String.format("%5s", loan.getId_cab()).replace(' ', '0');
					String norek_loan = String.format("%11s", nomorrekeningload).replace(' ', '0');
					String currency = "   ";
					
					
					String nomor_jaminan = String.format("%-12s", (loan.getRahn_nomor()==null) ? "" : loan.getRahn_nomor());
					if(nomor_jaminan.length() > 12) nomor_jaminan = nomor_jaminan.substring(0, 12);
					LocalDate n22dt1 = loan.getRahn_tgl_awal()==null ? LocalDate.now() : loan.getRahn_tgl_awal();
					LocalDate n22dt2 = loan.getRahn_tgl_akhir()==null ? LocalDate.now() : loan.getRahn_tgl_akhir();
					DateTimeFormatter formatterrahn = DateTimeFormatter.ofPattern("yyyyMMdd");
					String tanggal_awal = String.format("%8s", n22dt1.format(formatterrahn)).replace(' ', '0'); 
					String tanggal_akhir = String.format("%8s", n22dt2.format(formatterrahn)).replace(' ', '0'); 
					String jumlah_bulan = String.format("%5s", loan.getRahn_jumlah_bulan()==null ? "0" : loan.getRahn_jumlah_bulan()).replace(' ', '0');
					String keterangan_jaminan = String.format("%-60s", (loan.getRahn_keterangan()==null) ? "" : loan.getRahn_keterangan());
					if(keterangan_jaminan.length() > 60) keterangan_jaminan = keterangan_jaminan.substring(0, 60);
					String nama_barang = String.format("%-4s", (loan.getRahn_nama_barang()==null) ? "0000" : loan.getRahn_nama_barang());
					String berat_ukuran_jumlah = String.format("%6s", (loan.getRahn_jumlah()==null) ? "" : loan.getRahn_jumlah()).replace(' ', '0'); 
					String harga_dasar_emas = String.format("%13s", (loan.getRahn_harga_dasar()==null) ? "0" : loan.getRahn_harga_dasar().longValue()).replace(' ', '0') + "00";
					String harga_taksiran = String.format("%13s", (loan.getRahn_taksiran()==null) ? "0" : loan.getRahn_taksiran().longValue()).replace(' ', '0') + "00";
					String persen_pembiayaan = String.format("%-7s", (loan.getRahn_persen_pembiayaan()==null) ? "0" : loan.getRahn_persen_pembiayaan().toString().replace(".", "")).replace(' ', '0');
					String max_pembiayaan =  String.format("%13s", (loan.getRahn_max_pembiayaan()==null) ? "0" : loan.getRahn_max_pembiayaan().longValue()).replace(' ', '0') + "00";
					String ujroh = String.format("%13s", (loan.getRahn_ujroh()==null) ? "0" : loan.getRahn_ujroh().longValue()).replace(' ', '0') + "00";

					String pl = loan.getId_plan().toString();
					if(pl.length() > 3) pl = pl.substring(0, 3);
					String plan = String.format("%3s", pl).replace(' ', '0');
					String kode_aset_ijarah = String.format("%-20s", (loan.getKode_aset_ijarah()==null) ? "" : loan.getKode_aset_ijarah());
					String jangka_waktu = String.format("%5s", loan.getTenor_disetujui()).replace(' ', '0');
					String nomor_akad = String.format("%-25s", (loan.getNomor_akad()==null) ? "" : loan.getNomor_akad());
					if(nomor_akad.length() > 25) nomor_akad = nomor_akad.substring(0, 25);
					String kode_officer_2 = String.format("%-5s", loan.getKode_officer_2()==null ? "" : loan.getKode_officer_2());
					if(kode_officer_2.length() > 5) kode_officer_2 = kode_officer_2.substring(0, 5);
					String cab_open = pekerjaan.getCab_open()==null ? loan.getId_cab() : pekerjaan.getCab_open().toString();
					String kode_cabang = String.format("%-6s", cab_open);
					String kode_sub_dinas = String.format("%-6s", pekerjaan.getKode_sub_dinas()==null ? "" : pekerjaan.getKode_sub_dinas());
					String kode_officer_1 = String.format("%-5s", loan.getKode_officer_1());
					if(kode_officer_1.length() > 5) kode_officer_1 = kode_officer_1.substring(0, 5);
					String kode_dinas = String.format("%-6s", pekerjaan.getKode_dinas()==null ? "" : pekerjaan.getKode_dinas());
					String kode_sub_sub_dinas = String.format("%-6s", pekerjaan.getSub_sub_dinas()==null ? "" : pekerjaan.getSub_sub_dinas());

					DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
					Calendar cal = Calendar.getInstance();
					Date date1 = cal.getTime();
					cal.setTime(date1);
					cal.add(Calendar.MONTH, 1);
					date1 = cal.getTime();
//					String tgl_awal_pembiayaan = dateFormat.format(tanggal);
					LocalDateTime tgl_akad = loan.getAkad_date();
					DateTimeFormatter formattertglakad = DateTimeFormatter.ofPattern("yyyyMMdd");
					String tgl_awal_pembiayaan = tgl_akad.format(formattertglakad);
//					String tgl_awal_pembiayaan = "20230601";

					Calendar cal1 = Calendar.getInstance();
					Date date2 = cal1.getTime();
					cal1.setTime(date2);
					cal1.add(Calendar.MONTH, loan.getTenor_disetujui());
					date2 = cal1.getTime();
					String tgl_akhir_pembiayaan = dateFormat.format(date2);
//					String tgl_akhir_pembiayaan = "20320714";

					String plafon = String.format("%13s", loan.getPlafon_disetujui().longValue()).replace(' ', '0') + "00";
					String nilai_pencairan = String.format("%13s", loan.getPlafon_disetujui().longValue()).replace(' ', '0') + "00";
					
					BigDecimal pembagi_margin = new BigDecimal(100);
					Double nsb_nsb = (loan.getNisbah_nasabah()==null) ? Double.parseDouble("0") : loan.getNisbah_nasabah();
					System.out.println("################## NISBAH NASABAH "+ nsb_nsb);
//					BigDecimal hsl_nsb_nsb = nsb_nsb.divide(pembagi_margin, 6, RoundingMode.CEILING);
					String nisbah_nasabah = String.format("%7s", nsb_nsb.toString().replace(".", "")+"00").replace(' ', '0');
					
					Double nsb_bnk = (loan.getNisbah_bank()==null) ? Double.parseDouble("0") : loan.getNisbah_bank();
//					BigDecimal hsl_nsb_bnk = nsb_bnk.divide(pembagi_margin, 6, RoundingMode.CEILING);
					String nisbah_bank = String.format("%7s",  nsb_bnk.toString().replace(".", "")+"00").replace(' ', '0');

					String payment_due_date = dateFormat.format(date1);
//					String payment_due_date = "20230701";
					String grace_period = String.format("%4s", (loan.getGrace_period()==null) ? " " : loan.getGrace_period()).replace(' ', '0');
					String frekuensi_pembayaran_margin = String.format("%5s", loan.getFrequensi_pembayaran_number())
							.replace(' ', '0');
					
					BigDecimal basis_point_m = new BigDecimal(loan.getBasis_point_margin());
					BigDecimal nilai_margin = loan.getNilai_margin();
					
					BigDecimal margin = nilai_margin.divide(pembagi_margin, 6, RoundingMode.HALF_UP);
					String basis_mark = loan.getBasis_point_margin_mark().toString();
					String bs_point_mrg = basis_mark.replace("+", "0")+loan.getBasis_point_margin();
					
					BigDecimal basis = basis_point_m.divide(pembagi_margin, 6, RoundingMode.HALF_UP);
					BigDecimal hasil_margin = null;
					if (basis_mark.equals("+")){
						hasil_margin = margin.add(basis);
					} else {
						  hasil_margin = margin.subtract(basis);
					}
					
					String kode_margin = String.format("%-7s", hasil_margin.toString().replace(".", "")).replace(' ', '0');
					kode_margin = kode_margin.substring(0,6)+"0";
					String basis_point_margin = String.format("%-5s", bs_point_mrg.toString().replace(".", "")).replace(' ', '0');
					String is_biaya_adm = loan.getIs_biaya_adm();
					
					BigDecimal persen_adm = new BigDecimal(loan.getPersentase_biaya_adm());
					BigDecimal persen_by_adm = persen_adm.divide(pembagi_margin, 6, RoundingMode.CEILING);
					String persen_biaya_adm = String.format("%-7s", persen_by_adm.toString().replace(".", "")).replace(' ', '0');
					persen_biaya_adm = persen_biaya_adm.substring(0,6)+"0";
					String total_pembayaran = String.format("%3s", loan.getTenor_disetujui()).replace(' ', '0');

//					LocalDateTime tgl_akad = loan.getAkad_date();
					DateTimeFormatter formatterday = DateTimeFormatter.ofPattern("dd");
					String payment_day_of_due = tgl_akad.format(formatterday);
					String biaya_administrasi = String.format("%13s", loan.getBiaya_adm().longValue()).replace(' ', '0') + "00";
					String blokir_saldo = loan.getBlokir_saldo();
					
					Double urbun = loan.getUang_muka()==null ? Double.valueOf("0") : loan.getUang_muka();
					String wakalah = loan.getIs_wakalah()==null ? " " : loan.getIs_wakalah();
					String wakalah_app_code = String.format("%1s", " " );
					String wakalah_account = String.format("%11s", " ").replace(' ', '0');
					if(wakalah.equals("N")) {
						wakalah_app_code = String.format("%1s", (loan.getKode_rekening_urbun()==null) ? " " : loan.getKode_rekening_urbun());
						wakalah_account = String.format("%11s", (loan.getNorek_urbun()==null) ? " " : loan.getNorek_urbun()).replace(' ', '0');
					} else {
						wakalah_app_code = String.format("%1s", (loan.getKode_rekening_wakalah()==null) ? " " : loan.getKode_rekening_wakalah());
						wakalah_account = String.format("%11s", (loan.getNorek_wakalah()==null) ? " " : loan.getNorek_wakalah()).replace(' ', '0');
					}
					
					String kode_aplikasi_wakalah = wakalah_app_code;
					String norek_wakalah = wakalah_account;
					
					String koderekcair = "";
					if (loan.getKode_rekening_pencairan().equals("W")) {
						koderekcair = "2";
					} else if (loan.getKode_rekening_pencairan().equals("S")) {
						koderekcair = "2";
					} else if (loan.getKode_rekening_pencairan().equals("D")) {
						koderekcair = "1";
					} else if (loan.getKode_rekening_pencairan().equals("E")) {
						koderekcair = "1";
					}
					
					String kode_rek_pencairan = String.format("%1s", koderekcair);
					String rek_pencairan = String.format("%11s", loan.getNorek_pencairan()).replace(' ', '0');
					String kode_rek_proceed = String.format("%1s", (loan.getKode_rekening_proceed()==null) ? " " :  loan.getKode_rekening_proceed());
//					String rek_proceed = String.format("%11s", (loan.getNorek_proceed()==null) ? " " :  loan.getNorek_proceed()).replace(' ', '0');
					String rek_proceed = String.format("%11s", (nomorrekeningload==null) ? " " :  nomorrekeningload).replace(' ', '0');
					String biaya_materai = String.format("%13s", loan.getBiaya_materai().longValue()).replace(' ', '0') + "00";
					String kode_broker = String.format("%-3s", loan.getKode_broker()).replace(' ', '0');
					String kode_asuransi = String.format("%-5s", (loan.getKode_asuransi()==null) ? "" : loan.getKode_asuransi());
					String kode_notaris = String.format("%-5s", (loan.getKode_notaris()==null) ? "" : loan.getKode_notaris());
					
					String jumlah_bayar_regular = String.format("%13s", " ").replace(' ', '0')+"00";
					String outstanding_pokok = String.format("%13s", " ").replace(' ', '0')+ "00";
					String pokok = String.format("%13s", " ").replace(' ', '0') + "00";
					String uang_muka = String.format("%13s", urbun.longValue()).replace(' ', '0') + "00";
					
//					String jumlah_bayar_regular = String.format("%13s", loan.getAngsuran_disetujui().longValue()).replace(' ', '0')+"00";
//					String outstanding_pokok = String.format("%13s", loan.getPlafon_disetujui().longValue()).replace(' ', '0')+ "00";
//					String pokok = String.format("%13s", loan.getPlafon_disetujui().longValue()).replace(' ', '0') + "00";
//					String uang_muka = String.format("%13s", (loan.getUang_muka()==null) ? "" : loan.getUang_muka().longValue()).replace(' ', '0') + "00";

					Double uang_pokok = loan.getPlafon_disetujui();
//					Double uang_margin = loan.getMargin_pengajuan();

//					String saldo_jadwal_ulang = String.format("%13s", " ").replace(' ', '0') + "00";
//					String margin_outstanding = String.format("%13s", " ").replace(' ', '0') + "00";
//					String margin_pembiayaan = String.format("%13s", " ").replace(' ', '0') + "00";
//					String nilai_diskon = String.format("%13s", (loan.getDiskon()==null) ? "0" : loan.getDiskon().longValue()).replace(' ', '0') + "00";
//					String harga_perolehan = String.format("%13s", " ").replace(' ', '0') + "00";
					
					String saldo_jadwal_ulang = String.format("%13s", " ").replace(' ', '0') + "00";
					String margin_outstanding = String.format("%13s", " ").replace(' ', '0') + "00";
					String margin_pembiayaan = String.format("%13s", " ").replace(' ', '0') + "00";
					String nilai_diskon = String.format("%13s", " ").replace(' ', '0') + "00";
					String harga_perolehan = String.format("%13s", " ").replace(' ', '0') + "00";

					String golongan_nasabah = String.format("%-5s", bi.getGolongan_debitur());
					String hubungan_bank = String.format("%-1s", bi.getHubungan_bank()==null ? " " : bi.getHubungan_bank());
					String status_debitur = String.format("%-1s", bi.getStatus_debitur()==null ? " " : bi.getStatus_debitur());
					String kategori_debitur = String.format("%-2s", bi.getKategori_debitur());
					String kategori_portofolio = String.format("%-2s", bi.getKategori_portofolio());
					String jenis_pengguna = String.format("%-2s", bi.getJenis_penggunaan());
					String orientasi_pengguna = String.format("%-2s", bi.getOrientasi_penggunaan());
					String sektor_ekonomi = String.format("%-6s", bi.getSektor_ekonomi());
					String sifat_piutang = String.format("%-2s", (bi.getSifat_piutang()==null) ? "" : bi.getSifat_piutang());
					String jenis_piutang = String.format("%-2s", (bi.getJenis_piutang()==null) ? "" : bi.getJenis_piutang());
					String jenis_akad = String.format("%-3s", (bi.getJenis_akad()==null) ? "" : bi.getJenis_akad());
					String jenis_aset = String.format("%-3s", (bi.getJenis_aset()==null) ? "" : bi.getJenis_aset());
					String sumber_dana = String.format("%-1s", (bi.getSifat_investasi()==null) ? "" : bi.getSifat_investasi());
					String lembaga_pemeringkat = String.format("%-5s", "00");
					String nilai_peringkat = String.format("%-5s", "00");
//					String tanggal_pemeringkat = String.format("%-8s", bi.getTgl_pemeringkat().toString().replace("-", ""));
					String tanggal_pemeringkat = String.format("%-8s", "00000000").replace(' ', '0');
					String metode_bagi_hasil = String.format("%-1s", (bi.getBagi_hasil()==null) ? "" : bi.getBagi_hasil());
					String nama_debitur = String.format("%-20s", debitur.getNama());
					if(nama_debitur.length() > 20) nama_debitur = nama_debitur.substring(0, 20);
					String skema_pembiayaan = String.format("%-1s", bi.getSkim_pembiayaan());
					String kode_rekening_penghasilan = String.format("%-1s", (loan.getKode_rekening_penghasilan()==null) ? "" : loan.getKode_rekening_penghasilan());
					String norek_penghasilan = String.format("%10s", (loan.getNorek_penghasilan()==null || loan.getNorek_penghasilan().equals("")) ? "0" : loan.getNorek_penghasilan()).replace(' ', '0');
					String nominal_penghasilan = String.format("%15s", pekerjaan.getNetto().longValue()).replace(' ', '0')+"00";
					String cara_restruk = String.format("%-2s", (bi.getCara_restruktur()==null) ? "" : bi.getCara_restruktur());
					String take_over_dari = String.format("%-6s", (loan.getTake_over_nama_bank()==null) ? "" : loan.getTake_over_nama_bank());
					String kode_program_pemerintah = String.format("%-3s", bi.getProgram_pemerintah());
					String kode_sebab_macet = String.format("%-3s", (bi.getKode_sebab_macet()==null) ? "" : bi.getKode_sebab_macet());
					String freq_restruk = String.format("%3s", (bi.getFreq_restruktur()==null) ? "0" : bi.getFreq_restruktur()).replace(' ', '0');
					String sumbr_dana = String.format("%-6s", (bi.getSumber_dana()==null) ? "" : bi.getSumber_dana());
					String jenis_garansi = String.format("%-2s", (bi.getJenis_garansi()==null) ? "" : bi.getJenis_garansi());
					String kode_sektor_ekonomi = String.format("%-6s", (bi.getKode_sektor_ekonomi_bi()==null) ? "" : bi.getKode_sektor_ekonomi_bi());
					String kode_kondisi = String.format("%-2s", (bi.getKode_kondisi()==null) ? "" : bi.getKode_kondisi());
					String keterangan = String.format("%-256s", "");
					if(keterangan.length() > 256) keterangan = keterangan.substring(0, 256);
					String status_piutang = String.format("%-6s", bi.getBi_status_piutang());
					String sifat_akad = String.format("%-6s", bi.getBi_sifat_akad());

					String BIT120 = cab_pindahan + norek_loan + currency + nomor_jaminan + tanggal_awal + tanggal_akhir
							+ jumlah_bulan + keterangan_jaminan + nama_barang + berat_ukuran_jumlah + harga_dasar_emas
							+ harga_taksiran + persen_pembiayaan + max_pembiayaan + ujroh + plan + kode_aset_ijarah
							+ jangka_waktu + nomor_akad + kode_officer_2 + kode_cabang + kode_sub_dinas + kode_officer_1
							+ kode_dinas + kode_sub_sub_dinas + tgl_awal_pembiayaan + tgl_akhir_pembiayaan + plafon
							+ nilai_pencairan + nisbah_nasabah + nisbah_bank + payment_due_date + grace_period
							+ frekuensi_pembayaran_margin + kode_margin + basis_point_margin + is_biaya_adm
							+ persen_biaya_adm + total_pembayaran + payment_day_of_due + biaya_administrasi
							+ blokir_saldo + wakalah + kode_aplikasi_wakalah + norek_wakalah + kode_rek_pencairan
							+ rek_pencairan + kode_rek_proceed + rek_proceed + biaya_materai + kode_broker
							+ kode_asuransi + kode_notaris + jumlah_bayar_regular + outstanding_pokok + pokok
							+ uang_muka + saldo_jadwal_ulang + margin_outstanding + margin_pembiayaan + nilai_diskon
							+ harga_perolehan + golongan_nasabah + status_debitur + kategori_debitur
							+ kategori_portofolio + jenis_pengguna + orientasi_pengguna + sektor_ekonomi + sifat_piutang
							+ jenis_piutang + jenis_akad + jenis_aset + sumber_dana + lembaga_pemeringkat
							+ nilai_peringkat + tanggal_pemeringkat + metode_bagi_hasil + nama_debitur
							+ skema_pembiayaan + kode_rekening_penghasilan + norek_penghasilan + nominal_penghasilan
							+ cara_restruk + take_over_dari + kode_program_pemerintah + kode_sebab_macet + freq_restruk
							+ sumbr_dana + jenis_garansi + kode_sektor_ekonomi + kode_kondisi + keterangan
							+ status_piutang + sifat_akad;
					LOGGER.info("\n ************** BIT120 ************** " + "@ "
							+ BIT120 + " \n");
					String gol_debitur_sid = String.format("%-3s", (bi.getGol_debitur_sid()==null) ? " " : bi.getGol_debitur_sid().substring(0, 3));
					String jenis_aktiva_ijarah = String.format("%-3s", (bi.getJenis_aktiva_ijarah()==null) ? " " : bi.getJenis_aktiva_ijarah());
					String bagian_yang_dijamin = String.format("%-2s", (bi.getBagian_dijamin()==null) ? " " : bi.getBagian_dijamin().toString());
					String jenis_penggunaan = String.format("%-3s", (bi.getJenis_penggunaan_bi()==null) ? " " : bi.getJenis_penggunaan_bi());
					String gol_piutang = String.format("%-2s", (bi.getGolongan_piutang()==null) ? " " : bi.getGolongan_piutang());
					String sifat_kredit = String.format("%-2s", (bi.getSifat_pembiayaan()==null) ? " " : bi.getSifat_pembiayaan());
					String bi_tujuan_kredit = String.format("%-3s", (bi.getBi_tujuan_kredit()==null) ? " " : bi.getBi_tujuan_kredit());
					String terkait_tidakterkait = String.format("%-1s", hubungan_bank);
					String jenis_topup = String.format("%-1s", (loan.getJenis_pembiayaan()==null) ? " " : loan.getJenis_pembiayaan());
					String premi_asuransi_lama = String.format("%15s", (loan.getPremi_asuransi_lama()==null) ? "0" : loan.getPremi_asuransi_lama().toString().replace(".", "")).replace(' ', '0');
					String tgl_kredit_lama = String.format("%8s", (loan.getTgl_kredit_lama()==null) ? "00000000" : loan.getTgl_kredit_lama().replace("-", ""));
					String jenis_asuransi = String.format("%-3s", (loan.getBenefit_kredit_lama()==null) ? " " : loan.getBenefit_kredit_lama());
					String jk_wkt_kredit_lama = String.format("%5s", (loan.getTenor_kredit_lama()==null) ? "0" : loan.getTenor_kredit_lama()).replace(' ', '0');
					String hub_produsen_dgn_bank = String.format("%-1s", hubungan_bank);
					String usr_id = String.format("%-10s", (loan.getSubmit2_by()==null) ? " " : loan.getSubmit2_by());
					String spv_id = String.format("%-10s", user_id);
					String badan_hukum = String.format("%-6s", (bi.getBadan_hukum()==null) ? " " : bi.getBadan_hukum());
					String workstation = String.format("%-10s", "EFOSBRK");
					String lokasi_proyek = String.format("%-6s", (bi.getLokasi_pengguna()==null) ? " " : bi.getLokasi_pengguna());
					
					String BIT121 = gol_debitur_sid+jenis_aktiva_ijarah+bagian_yang_dijamin+jenis_penggunaan+gol_piutang+sifat_kredit+
							bi_tujuan_kredit+terkait_tidakterkait+jenis_topup+premi_asuransi_lama+tgl_kredit_lama+jenis_asuransi+
							jk_wkt_kredit_lama+hub_produsen_dgn_bank+usr_id+spv_id+badan_hukum+workstation+lokasi_proyek;
					LOGGER.info("\n ************** BIT121 ************** " + "@ "
							+ BIT121 + " \n");
					// ########################################
					String url = "http://"+ip+":"+port;
					JsonObject message = new JsonObject();
					JsonObject data = new JsonObject();
					data.addProperty("mti", "0200");
					data.addProperty("pan", "0000000000000000");
					data.addProperty("pcode", "660004");
					data.addProperty("amount", "000000000000");
					data.addProperty("gmt", dateTime.format(formattergmt));
					data.addProperty("stan", stan);
					data.addProperty("time", time.format(formattertime));
					data.addProperty("date", date.format(formatter));
					data.addProperty("merchant", "6010");
					data.addProperty("acquirer", "627492");
					data.addProperty("fwd", "600100");
					data.addProperty("rrn", stan + stan);
					data.addProperty("termid", "A01");
					data.addProperty("termloc", "EFOSBRK");
					data.addProperty("termno", "000119");
					data.addProperty("paydata", "");
					data.addProperty("billdata", "PENCAIRAN " + id_loan);
					data.addProperty("ccode", "360");
					data.addProperty("infdata", year.format(formatteryear));
					data.addProperty("fromacct", "");
					data.addProperty("toacct", "");
					data.addProperty("appendix1", BIT120);
					data.addProperty("appendix2", BIT121);
					message.add("RiauTRX", data);
					LOGGER.info("\n ************** JSON TO VLINK PENCAIRAN ************** " + "@ "
							+ objectMapper.writeValueAsString(message.toString()) + " \n");
					RestTemplate restTemplate = new RestTemplate();
					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.set("Content-Type", "application/json");
					HttpEntity<String> httpEntity = new HttpEntity<>(message.toString(), httpHeaders);
			        String answer = restTemplate.postForObject(url, httpEntity, String.class);

			        JsonObject jsonObject = new JsonParser().parse(answer).getAsJsonObject();
		            jsonObject = jsonObject.getAsJsonObject("RiauTRXResponse");
		            jsonObject = jsonObject.getAsJsonObject("return");

		            System.out.println("Respon Dari VLINK PENCAIRAN : " + jsonObject + "\n\n");
					// ########################################
		            String rcode = jsonObject.get("rcode").getAsString();
		            String reason = jsonObject.get("reason").getAsString();
		            String infdata = jsonObject.get("infdata").getAsString();
		            
		            if (infdata.substring(4).trim().equals("F1S0070")){
		            	rcode = "00";
		            }

	            	res.setKode(rcode);
	    			res.setPesan(reason);
	            
		            
				
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("05");
			res.setPesan("DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE POSTING ************** " + "@ "
				+ objectMapper.writeValueAsString(res) + " \n");
		return res;
	}
	
	public ResponseMessage posting_agunan(String id_loan, String user_id)
			throws ParserConfigurationException, RestClientException, DOMException, SAXException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseMessage res = new ResponseMessage();
		
		Random random = new Random(System.nanoTime() % 100000);
		int randomInt = random.nextInt(1000000);
		String stan = String.format("%6s", Integer.toString(randomInt)).replace(' ', '0');

		DateFormat dateGMT = new SimpleDateFormat("MMddHHmmss");
		Date tanggal = new Date();
		dateGMT.setTimeZone(TimeZone.getTimeZone("GMT"));

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMdd");
		LocalTime time = LocalTime.now();
		DateTimeFormatter formattertime = DateTimeFormatter.ofPattern("HHmmss");
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formattergmt = DateTimeFormatter.ofPattern("MMddHHmmss");
		LocalDate year = LocalDate.now();
		DateTimeFormatter formatteryear = DateTimeFormatter.ofPattern("yyyy");

		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
			Debitur debitur = debiturRepository.findByKtpDebitur(loan.getId_debitur());
			List<Agunan> agunan = agunanRepository.findByIdLoan(id_loan);
					
        	for (int i = 0; i < agunan.size(); i++) {
        		Agunan RAgunan = agunanRepository.findByUrut(agunan.get(i).getUrut());
        		String utama = (agunan.get(i).getIs_pokok()==null) ? "0" : agunan.get(i).getIs_pokok();
        		if (utama.equals("2")) {
		            RAgunan.setStatus_vlink("0");
	            	agunanRepository.save(RAgunan);
        		} else {
        		Random random1 = new Random(System.nanoTime() % 100000);
        		int randomInt1 = random1.nextInt(1000000);
        		String stan2 = String.format("%6s", Integer.toString(randomInt1)).replace(' ', '0');
        		
				String no_collateral = String.format("%09d", 0);
				String cab_collateral = String.format("%5s", loan.getId_cab()).replace(' ', '0');
				String collateral_class = String.format("%-6s", (agunan.get(i).getJenis()==null) ? "" : agunan.get(i).getJenis());
				String last_val_date = (agunan.get(i).getTgl_penilaian_terakhir()==null) ? "0" : agunan.get(i).getTgl_penilaian_terakhir().toString().replace("-", "");
				String last_valuation_date = String.format("%8s", last_val_date).replace(" ", "0");
				String c_exp_date = (agunan.get(i).getTgl_expired_agunan()==null) ? "0" : agunan.get(i).getTgl_expired_agunan().toString().replace("-", "");
				
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				Calendar cal1 = Calendar.getInstance();
				Date date2 = cal1.getTime();
				cal1.setTime(date2);
				cal1.add(Calendar.MONTH, loan.getTenor_disetujui());
				date2 = cal1.getTime();
				String tgl_akhir_pembiayaan = dateFormat.format(date2);
				
				String coll_exp_date = String.format("%8s", tgl_akhir_pembiayaan).replace(" ", "0");
				String user_entry =  String.format("%-10s", (agunan.get(i).getUser_entry()==null) ? "" : agunan.get(i).getUser_entry());
				String collateral_desc =  String.format("%-256s", (agunan.get(i).getDeskripsi_agunan()==null) ? "" : agunan.get(i).getDeskripsi_agunan().replace("\u00B2", "2"));
				if(collateral_desc.length() > 256) collateral_desc = collateral_desc.substring(0, 256);
				String mortgage_priority =  String.format("%-1s", (agunan.get(i).getPrioritas_agunan()==null) ? "" : agunan.get(i).getPrioritas_agunan());
				String allow_accts_attached =  String.format("%-1s", (agunan.get(i).getIzin_pengkaitan()==null) ? "" : agunan.get(i).getIzin_pengkaitan());
				String coverage_of_obligation =  String.format("%-1s", (agunan.get(i).getCakupan_kewajiban()==null) ? "" : agunan.get(i).getCakupan_kewajiban());
				String coll_status =  String.format("%-1s", (agunan.get(i).getStatus_agunan()==null) ? "" : agunan.get(i).getStatus_agunan());
				String status_of_accounting =  String.format("%-1s", (agunan.get(i).getStatus_akutansi_agunan()==null) ? "" : agunan.get(i).getStatus_akutansi_agunan());
				String cust_or_bank =  String.format("%-1s", (agunan.get(i).getBank_atau_nasabah()==null) ? "" : agunan.get(i).getBank_atau_nasabah());
				String collateral_util =  String.format("%-1s", (agunan.get(i).getKegunaan_agunan()==null) ? "" : agunan.get(i).getKegunaan_agunan());
				String insurance_comp_code1 =  String.format("%-6s", (agunan.get(i).getKode_asuransi1()==null) ? "" : agunan.get(i).getKode_asuransi1());
				String insurance_comp_code2 =  String.format("%-6s", (agunan.get(i).getKode_asuransi2()==null) ? "" : agunan.get(i).getKode_asuransi2());
				String collateral_class_bi =  String.format("%-3s", (agunan.get(i).getJenis_agunan_bi()==null) ? "" : agunan.get(i).getJenis_agunan_bi());
				String penilaian_oleh =  String.format("%-1s", (agunan.get(i).getPenilaian_oleh()==null) ? "" : agunan.get(i).getPenilaian_oleh());
				//String jumlah_bayar_regular = String.format("%15s", loan.getAngsuran_disetujui().toString().replace(".", "")).replace(' ', '0');
				String ins_start_date = (agunan.get(i).getTgl_awal_asuransi1()==null) ? "0" : agunan.get(i).getTgl_awal_asuransi1().toString().replace("-", "");
				String insurance1_start_date =  String.format("%8s", ins_start_date).replace(" ", "0");
				String ins_end_date = (agunan.get(i).getTgl_akhir_asuransi1()==null) ? "0" : agunan.get(i).getTgl_akhir_asuransi1().toString().replace("-", "");
				String insurance1_end_date =  String.format("%8s", ins_end_date).replace(" ", "0");
				
				String insurance2_start_date =  String.format("%8s", "00000000");
				String insurance2_end_date =  String.format("%8s", "00000000");
				String telp_of_appraiser =  String.format("%11s", (agunan.get(i).getTelp_penilai()==null) ? "0" : agunan.get(i).getTelp_penilai()).replace(' ', '0');
				if(telp_of_appraiser.length() > 11) telp_of_appraiser = telp_of_appraiser.substring(0, 11);
				String appraiser_company =  String.format("%-40s", (agunan.get(i).getPerusahaan_penilai()==null) ? "" : agunan.get(i).getPerusahaan_penilai());
				String address1 =  String.format("%-35s", (agunan.get(i).getAlamat1()==null) ? "" : agunan.get(i).getAlamat1());
				if(address1.length() > 35) address1 = address1.substring(0, 35);
				String address2 =  String.format("%-35s", (agunan.get(i).getAlamat2()==null) ? "" : agunan.get(i).getAlamat2());
				if(address2.length() > 35) address2 = address2.substring(0, 35);
				String address3 =  String.format("%-35s", (agunan.get(i).getAlamat3()==null) ? "" : agunan.get(i).getAlamat3());
				if(address3.length() > 35) address3 = address3.substring(0, 35);
				String address4 =  String.format("%-35s", (agunan.get(i).getAlamat4()==null) ? "" : agunan.get(i).getAlamat4());
				if(address4.length() > 35) address4 = address4.substring(0, 35);
				

				String dati2 =  String.format("%-6s", (agunan.get(i).getBi_dati2()==null) ? "" : String.format("%4s", agunan.get(i).getBi_dati2()).replace(' ', '0'));
				String name_of_lawyer =  String.format("%-40s", (agunan.get(i).getNama_pengacara()==null) ? "" : agunan.get(i).getNama_pengacara());
				if(name_of_lawyer.length() > 40) name_of_lawyer = name_of_lawyer.substring(0, 40);
				System.out.println("############### collateral_value "+agunan.get(i).getHarga_pasar());
				System.out.println("############### landing_value "+agunan.get(i).getNilai_taksasi());
				System.out.println("############### collateral_limit "+agunan.get(i).getLimit_agunan_bi());
				System.out.println("############### insurance_amount1 "+agunan.get(i).getAsuransi_amount1());
				
				String collateral_value =  String.format("%13s", (agunan.get(i).getHarga_pasar()==null) ? "0" : agunan.get(i).getHarga_pasar().longValue()).replace(' ', '0')+"00";
				String landing_value =  String.format("%13s", (agunan.get(i).getNilai_taksasi()==null) ? "0" : agunan.get(i).getNilai_taksasi().longValue()).replace(' ', '0')+"00";
				String collateral_limit =  String.format("%13s", (agunan.get(i).getLimit_agunan_bi()==null) ? "0" : agunan.get(i).getLimit_agunan_bi().longValue()).replace(' ', '0')+"00";
				String insurance_amount1 =  String.format("%13s", (agunan.get(i).getAsuransi_amount1()==null) ? "0" : agunan.get(i).getAsuransi_amount1().longValue()).replace(' ', '0')+"00";
//							String insurance_amount2 =  String.format("%8s", (agunan.get(i).getAsuransi_amount2()==null) ? "0" : agunan.get(i).getAsuransi_amount2()).replace(' ', '0');
				
				LocalDateTime tgl_akad1 = loan.getAkad_date();
				DateTimeFormatter formattertglakad = DateTimeFormatter.ofPattern("yyyyMMdd");
				String tgl_akad_agunan = tgl_akad1.format(formattertglakad);
				
				String entry_date =  String.format("%8s", tgl_akad_agunan);
				String entry_workstation =  String.format("%-10s", (agunan.get(i).getWorkstation()==null) ? "" : agunan.get(i).getWorkstation());
				String date_last_maintenance =  String.format("%8s", tgl_akad_agunan).replace(' ', '0');
				String liquidation_amount =  String.format("%13s", (agunan.get(i).getLiquidation_amount()==null) ? "0" : agunan.get(i).getLiquidation_amount().longValue()).replace(' ', '0')+"00";
				String apht_dt = (agunan.get(i).getTgl_apht()==null) ? "0" : agunan.get(i).getTgl_apht().toString().replace("-", "");
				String apht_date =  String.format("%8s", apht_dt).replace(" ", "0");
				String apht_amount =  String.format("%13s", (agunan.get(i).getApht_amount()==null) ? "0" : agunan.get(i).getApht_amount().longValue()).replace(' ', '0')+"00";
				String channel_code =  String.format("%-6s", (agunan.get(i).getKode_kanal()==null) ? "" : agunan.get(i).getKode_kanal());
				String document_type_code =  String.format("%-6s", (agunan.get(i).getKode_tipe_dokumen()==null) ? "0" : agunan.get(i).getKode_tipe_dokumen());
				String document_number =  String.format("%-20s", (agunan.get(i).getNomor_dokumen()==null) ? "0" : agunan.get(i).getNomor_dokumen());
				String kode_pengikatan_internal =  String.format("%-6s", (agunan.get(i).getKode_pengikatan_internal()==null) ? "" : agunan.get(i).getKode_pengikatan_internal());
				String kode_pengikatan_notarial =  String.format("%-6s", (agunan.get(i).getKode_pengikatan_notarial()==null) ? "" : agunan.get(i).getKode_pengikatan_notarial());
				
				LocalDate tgl_pengikat_intern = agunan.get(i).getTgl_pengikatan_internal();
				LocalDate tgl_pengikat_notar = agunan.get(i).getTgl_pengikatan_notarial();
				DateTimeFormatter formattertglikat = DateTimeFormatter.ofPattern("yyyyMMdd");
				String tgl_peng_int = "";
				
				if (agunan.get(i).getTgl_pengikatan_internal()==null) {
					tgl_peng_int = "00000000";
				} else {
					tgl_peng_int = tgl_pengikat_intern.format(formattertglikat);
				}
				
				String tgl_peng_notar = "";
				if (agunan.get(i).getTgl_pengikatan_notarial()==null) {
					tgl_peng_notar = "00000000";
				} else {
					tgl_peng_notar = tgl_pengikat_notar.format(formattertglikat);;
				}
				
				String tgl_pengikatan_internal =  String.format("%8s", tgl_peng_int);
				String tgl_pengikatan_notarial =  String.format("%8s", tgl_peng_notar);
				String nomor_pengikatan_internal =  String.format("%-25s", (agunan.get(i).getNomor_pengikatan_internal()==null) ? "" : agunan.get(i).getNomor_pengikatan_internal());
				String nomor_pengikatan_notarial =  String.format("%-25s", (agunan.get(i).getNomor_pengikatan_notarial()==null) ? "" : agunan.get(i).getNomor_pengikatan_notarial());
				String jumlah_agunan =  String.format("%-40s", (agunan.get(i).getJumlah_agunan()==null) ? "" : agunan.get(i).getJumlah_agunan());
				String agree_date = agunan.get(i).getAgreement_date()==null ? " " : agunan.get(i).getAgreement_date().toString().replace("-", "");
				String agreement_date =  String.format("%8s", agree_date).replace(' ', '0');
				String jenis_agunan_ppap =  String.format("%-6s", (agunan.get(i).getJenis_agunan_ppap()==null) ? "" : agunan.get(i).getJenis_agunan_ppap());
				String date_collateral_registered =  String.format("%8s", tgl_akad_agunan);
				String utilized_amount =  String.format("%13s", (agunan.get(i).getUtilized_amount()==null) ? "" : agunan.get(i).getUtilized_amount().longValue()).replace(' ', '0')+"00";
				String cif = String.format("%15s", (debitur.getCif()==null) ? "" : debitur.getCif()).replace(' ', '0');
				String rek_loan = String.format("%11s", (loan.getNorek_loan()==null) ? "" : loan.getNorek_loan()).replace(' ', '0');
				String kodecab_loan = String.format("%5s", (loan.getId_cab()==null) ? "" : loan.getId_cab()).replace(' ', '0');
				String basel2 = String.format("%3s", (agunan.get(i).getBasel2_jenis_agunan()==null) ? "" : agunan.get(i).getBasel2_jenis_agunan()).replace(' ', '0');
				String sifat_agunan = String.format("%1s", (agunan.get(i).getSifat_agunan()==null) ? "" : agunan.get(i).getSifat_agunan()).replace(' ', '0');
				String pnrbt_agun = agunan.get(i).getPenerbit_agunan()==null ? " " : agunan.get(i).getPenerbit_agunan();
				String penerbit_agunan =  String.format("%3s", pnrbt_agun);
				String cash_non_cash = String.format("%1s", (agunan.get(i).getCash_non_cash()==null) ? "" : agunan.get(i).getCash_non_cash()).replace(' ', '0');
				String kepemilikan=  String.format("%-25s", (agunan.get(i).getKepemilikan()==null) ? "" : agunan.get(i).getKepemilikan());
				if(kepemilikan.length() > 25) kepemilikan = kepemilikan.substring(0, 25);
				String account =  String.format("%11s", (loan.getNorek_pencairan()==null) ? "" : loan.getNorek_pencairan()).replace(" ", "0");
				String entry_time = String.format("%6s", time.format(formattertime)).replace(' ', '0');
				
				String BIT120_agun = no_collateral+cab_collateral+collateral_class+last_valuation_date+coll_exp_date+user_entry+
						collateral_desc+mortgage_priority+allow_accts_attached+coverage_of_obligation+coll_status+status_of_accounting+
						cust_or_bank+collateral_util+insurance_comp_code1+insurance_comp_code2+collateral_class_bi+penilaian_oleh+
						insurance1_start_date+insurance1_end_date+insurance2_start_date+insurance2_end_date+telp_of_appraiser+
						appraiser_company+address1+address2+address3+address4+dati2+name_of_lawyer+collateral_value+landing_value+
						collateral_limit+insurance_amount1+entry_date+entry_workstation+date_last_maintenance+
						liquidation_amount+apht_date+apht_amount+channel_code+document_type_code+document_number+kode_pengikatan_internal+
						kode_pengikatan_notarial+tgl_pengikatan_internal+tgl_pengikatan_notarial+nomor_pengikatan_internal+
						nomor_pengikatan_notarial+jumlah_agunan+agreement_date+jenis_agunan_ppap+date_collateral_registered+
						utilized_amount+cif+rek_loan+kodecab_loan+basel2+sifat_agunan+penerbit_agunan+cash_non_cash+kepemilikan+account
						+entry_time;
				
				// ########################################
				String url1 = "http://"+ip+":"+port;
				JsonObject message1 = new JsonObject();
				JsonObject data1 = new JsonObject();
				data1.addProperty("mti", "0200");
				data1.addProperty("pan", "0000000000000000");
				data1.addProperty("pcode", "660005");
				data1.addProperty("amount", "000000000000");
				data1.addProperty("gmt", dateTime.format(formattergmt));
				data1.addProperty("stan", stan2);
				data1.addProperty("time", time.format(formattertime));
				data1.addProperty("date", date.format(formatter));
				data1.addProperty("merchant", "6010");
				data1.addProperty("acquirer", "627492");
				data1.addProperty("fwd", "600100");
				data1.addProperty("rrn", stan2 + stan2);
				data1.addProperty("termid", "A01");
				data1.addProperty("termloc", "EFOSBRK");
				data1.addProperty("termno", "000119");
				data1.addProperty("paydata", "");
				data1.addProperty("billdata", "AGUNAN " + id_loan);
				data1.addProperty("ccode", "360");
				data1.addProperty("infdata", year.format(formatteryear));
				data1.addProperty("fromacct", "");
				data1.addProperty("toacct", "");
				data1.addProperty("appendix1", BIT120_agun);
				message1.add("RiauTRX", data1);
				LOGGER.info("\n ************** JSON TO VLINK AGUNAN ************** " + "@ " +i+" "
						+ message1.toString() + " \n");
				RestTemplate restTemplate1 = new RestTemplate();
				HttpHeaders httpHeaders1 = new HttpHeaders();
				httpHeaders1.set("Content-Type", "application/json");
				HttpEntity<String> httpEntity1 = new HttpEntity<>(message1.toString(), httpHeaders1);
		        String answer1 = restTemplate1.postForObject(url1, httpEntity1, String.class);

		        JsonObject jsonObject1 = new JsonParser().parse(answer1).getAsJsonObject();
	            jsonObject1 = jsonObject1.getAsJsonObject("RiauTRXResponse");
	            jsonObject1 = jsonObject1.getAsJsonObject("return");

	            System.out.println("Respon Dari VLINK AGUNAN : " +i+" "+ jsonObject1 + "\n\n");
				// ########################################
	            String rcode1 = jsonObject1.get("rcode").getAsString();
	            String reason1 = jsonObject1.get("reason").getAsString();
	            
	            
	            RAgunan.setStatus_vlink(rcode1);
            	agunanRepository.save(RAgunan);

        		}
			}
        
    	List<Agunan> agunan_sts = agunanRepository.findByIdLoanAndSts(id_loan, "00");
		
    	if (agunan.size() == agunan_sts.size()) {
    		res.setKode("00");
			res.setPesan("APPROVE PENCAIRAN BERHASIL");
    	} else {
    		res.setKode("99");
			res.setPesan("GAGAL POSTING AGUNAN SILAHKAN CEK DAN POSTING ULANG AGUNAN YANG BELUM DIPOSTING");
    	}
		
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("05");
			res.setPesan("DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE POSTING ************** " + "@ "
				+ objectMapper.writeValueAsString(res) + " \n");
		return res;
	}
	
	@PostMapping("/pencairan/efos/review")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> review(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/review ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String keterangan = param.get("keterangan");

		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				loan.setStatus("22");
				loan.setReview2_by(user_id);
				loan.setReview2_date(LocalDateTime.now());
				loan.setReview2_desc(keterangan);
				loan.setReview_nama(user_nama);
				loanRepository.save(loan);

				response.put("kode", "00");
				response.put("pesan", "REVIEW BERHASIL");

			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/review ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/pencairan/efos/reviewpinsi")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> reviewpinsi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/reviewpinsi ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String keterangan = param.get("keterangan");

		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				lampiran_fileRepository.updateceklistByIdLoan(id_loan);
//				Data_lampiran_file har = lampiran_fileRepository.findByIdSelect(Integer.valueOf(aArray.getString("id")));
				loan.setStatus("15");
				loan.setReview2_by(user_id);
				loan.setReview2_date(LocalDateTime.now());
				loan.setReview2_desc(keterangan);
//				loan.setNomor_akad(null);
				loanRepository.save(loan);

				response.put("kode", "00");
				response.put("pesan", "REVIEW BERHASIL");

			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			LOGGER.info("\n ************** RESPONSE TO FRONTEND (Exception) /mkm/pencairan/reviewpinsi ************** " + "@ "
					+ e + " \n");
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/reviewpinsi ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/pencairan/efos/setbiayaagunanbyid")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> setbiayaagunanbyid(@Valid @RequestBody String param,
			@AuthenticationPrincipal Jwt jwt, HttpServletRequest request)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> response = new HashMap<>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			JSONObject jsonObject = new JSONObject(param.toString());
			String json = jsonObject.toString();
			LOGGER.info("\n ************** REQUEST FROM FRONTEND "+request.getRemoteAddr()+" /pencairan/efos/setbiayaagunanbyid ************** " + "@ " + json + " \n");
			
			String id_loan = jsonObject.get("id_loan").toString().equals("") ? null : jsonObject.get("id_loan").toString(); 
			Loan loan = loanRepository.findByIdLoan(id_loan);
			
			JSONArray agunan = new JSONArray(jsonObject.getString("agunan"));
			
			
			if (agunan.length() >= 1) {
				for (int i = 0; i < agunan.length(); i++) {
					JSONObject aArray = agunan.getJSONObject(i);
					LOGGER.info("!! SET BIAYA AGUNAN !! " + "@ " + i + " \n");
					Integer urut = aArray.get("urut").toString().equals("") ? 0 : Integer.parseInt(aArray.get("urut").toString());
					Agunan agun = agunanRepository.findByUrut(urut);
					
					String is_asuransi = aArray.get("is_asuransi").toString().equals("") ? null : aArray.get("is_asuransi").toString();
					String kode_asuransi1 = aArray.get("kode_asuransi1").toString().equals("") ? null : aArray.get("kode_asuransi1").toString();
					LocalDate tgl_awal_asuransi1 = aArray.get("tgl_awal_asuransi1").toString().equals("") ? null : LocalDate.parse(aArray.getString("tgl_awal_asuransi1"), formatter);
					LocalDate tgl_akhir_asuransi1 = aArray.get("tgl_akhir_asuransi1").toString().equals("") ? null : LocalDate.parse(aArray.getString("tgl_akhir_asuransi1"), formatter);
					Double asuransi_amount1 = aArray.get("asuransi_amount1").toString().equals("") ? null : Double.parseDouble(aArray.get("asuransi_amount1").toString());
					
					agun.setIs_asuransi(is_asuransi);
					agun.setKode_asuransi1(kode_asuransi1);
					agun.setTgl_awal_asuransi1(tgl_awal_asuransi1);
					agun.setTgl_akhir_asuransi1(tgl_akhir_asuransi1);
					agun.setAsuransi_amount1(asuransi_amount1);
					agunanRepository.save(agun);
				}
			}
//
//			loan.setStatus("25");
//			loanRepository.save(loan);
			response.put("kode", "00");
			response.put("pesan", "DATA BERHASIL SIMPAN");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND "+request.getRemoteAddr()+" /pencairan/efos/setbiayaagunanbyid ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/pencairan/efos/setallbiaya")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setallbiaya(@Valid @RequestBody String param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();

		Map<String, Object> response = new HashMap<>();
		try {
			JSONObject jsonObject = new JSONObject(param.toString());
			String json = jsonObject.toString();
			LOGGER.info("\n ************** REQUEST FROM FRONTEND /pencairan/efos/setallbiaya ************** " + "@ " + json
					+ " \n");

			String is_biaya_adm = jsonObject.getString("is_biaya_adm");
			String id_loan = jsonObject.getString("id_loan");
			Double biaya_materai = jsonObject.getString("biaya_materai").equals("") ? null : Double.parseDouble(jsonObject.getString("biaya_materai"));
			Double persentase_biaya_adm = jsonObject.getString("persentase_biaya_adm").equals("") ? null : Double.parseDouble(jsonObject.getString("persentase_biaya_adm"));
			Double biaya_adm = jsonObject.getString("biaya_adm").equals("") ? null : Double.parseDouble(jsonObject.getString("biaya_adm"));
			Double biaya_asuransi_jiwa = jsonObject.getString("biaya_asuransi_jiwa").equals("") ? null : Double.parseDouble(jsonObject.getString("biaya_asuransi_jiwa"));
			Double biaya_asuransi_kebakaran = jsonObject.getString("biaya_asuransi_kebakaran").equals("") ? null : Double.parseDouble(jsonObject.getString("biaya_asuransi_kebakaran"));
			Double biaya_asuransi_pembiayaan = jsonObject.getString("biaya_asuransi_pembiayaan").equals("") ? null : Double.parseDouble(jsonObject.getString("biaya_asuransi_pembiayaan"));
			
			Loan dataloan = loanRepository.findByIdLoan(id_loan);
			dataloan.setIs_biaya_adm(is_biaya_adm);
			dataloan.setBiaya_materai(biaya_materai);
			dataloan.setPersentase_biaya_adm(persentase_biaya_adm);
			dataloan.setBiaya_adm(biaya_adm);
			dataloan.setBiaya_asuransi_jiwa(biaya_asuransi_jiwa);
			dataloan.setBiaya_asuransi_kebakaran(biaya_asuransi_kebakaran);
			dataloan.setBiaya_asuransi_pembiayaan(biaya_asuransi_pembiayaan);
//			dataloan.setStatus("26");
			loanRepository.save(dataloan);
			
			response.put("kode", "00");
			response.put("pesan", "SET BIAYA BERHASIL");


		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /pencairan/efos/setallbiaya ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/pencairan/cair/manual")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setCairManual(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND PENCAIRAN MANUAL ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String cab = param.get("cab");
		String user_nama = param.get("user_nama");
//		String keterangan = param.get("keterangan");

		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
			if (loan != null) {

				System.out.println("##### DATA LOAN ##### ");
				loan.setStatus("23");
				loan.setCair_by(user_id);
				loan.setCair_date(LocalDateTime.now());
				loan.setApprove_cair1_by(user_id);
				loan.setApprove_cair1_name(user_nama);
				loan.setApprove_cair1_date(LocalDateTime.now());
//				loan.set_desc(keterangan);
				loanRepository.save(loan);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL");

			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND PENCAIRAN MANUAL ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/pencairan/cair/pembatalan")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> pembatalan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /pencairan/cair/pembatalan ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String keterangan = param.get("keterangan");

		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				loan.setStatus("24");
				loan.setReview2_by(user_id);
				loan.setReview2_date(LocalDateTime.now());
				loan.setReview2_desc(keterangan);
				loanRepository.save(loan);

				response.put("kode", "00");
				response.put("pesan", "BATAL BERHASIL");

			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /pencairan/cair/pembatalan ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
