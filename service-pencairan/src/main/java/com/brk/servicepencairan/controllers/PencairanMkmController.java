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
import com.brk.servicepencairan.models.CifData;
import com.brk.servicepencairan.models.Data_bi;
import com.brk.servicepencairan.models.Data_lampiran_file;
import com.brk.servicepencairan.models.Debitur;
import com.brk.servicepencairan.models.Lampiran_file;
import com.brk.servicepencairan.models.Loan;
import com.brk.servicepencairan.models.Par_cabang;
import com.brk.servicepencairan.models.Par_ceklist;
import com.brk.servicepencairan.models.Par_plan;
import com.brk.servicepencairan.models.Pasangan;
import com.brk.servicepencairan.models.Pekerjaan;
import com.brk.servicepencairan.models.ResponseMessage;
import com.brk.servicepencairan.models.RincianScoring;
import com.brk.servicepencairan.models.Scoring;
import com.brk.servicepencairan.models.ScoringLoan;
import com.brk.servicepencairan.models.Usulan;
import com.brk.servicepencairan.models.mkm.M_append_plotting;
import com.brk.servicepencairan.models.mkm.M_append_taksasi;
import com.brk.servicepencairan.models.mkm.M_data_agunan;
import com.brk.servicepencairan.models.mkm.M_data_aspek_keuangan;
import com.brk.servicepencairan.models.mkm.M_data_bi;
import com.brk.servicepencairan.models.mkm.M_data_debitur;
import com.brk.servicepencairan.models.mkm.M_data_lampiran_file;
import com.brk.servicepencairan.models.mkm.M_data_loan;
import com.brk.servicepencairan.models.mkm.M_data_neraca;
import com.brk.servicepencairan.models.mkm.M_data_pasangan;
import com.brk.servicepencairan.models.mkm.M_data_penghasilan;
import com.brk.servicepencairan.models.mkm.M_data_wallet;
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
import com.brk.servicepencairan.repository.PasanganRepository;
import com.brk.servicepencairan.repository.PekerjaanRepository;
import com.brk.servicepencairan.repository.ScoringLoanRepository;
import com.brk.servicepencairan.repository.UsersRepository;
import com.brk.servicepencairan.repository.UsulanRepository;
import com.brk.servicepencairan.repository.mkm.MScoringLoanRepository;
import com.brk.servicepencairan.repository.mkm.M_append_plottingRepository;
import com.brk.servicepencairan.repository.mkm.M_append_surveyRepository;
import com.brk.servicepencairan.repository.mkm.M_append_taksasiRepository;
import com.brk.servicepencairan.repository.mkm.M_data_agunanRepository;
import com.brk.servicepencairan.repository.mkm.M_data_aspek_keuanganRepository;
import com.brk.servicepencairan.repository.mkm.M_data_biRepository;
import com.brk.servicepencairan.repository.mkm.M_data_debiturRepository;
import com.brk.servicepencairan.repository.mkm.M_data_lampiran_fileRepository;
import com.brk.servicepencairan.repository.mkm.M_data_loanRepository;
import com.brk.servicepencairan.repository.mkm.M_data_neracaRepository;
import com.brk.servicepencairan.repository.mkm.M_data_pasanganRepository;
import com.brk.servicepencairan.repository.mkm.M_data_penghasilanRepository;
import com.brk.servicepencairan.repository.mkm.M_data_walletRepository;
import com.brk.servicepencairan.services.CoreBankingService;
import com.brk.servicepencairan.services.FilesStorageService;
import com.brk.servicepencairan.services.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@RestController
public class PencairanMkmController {

	@Autowired M_data_debiturRepository m_data_debiturRepository;
	@Autowired M_data_penghasilanRepository m_data_penghasilanRepository;
	@Autowired M_data_loanRepository m_data_loanRepository;
	@Autowired M_data_pasanganRepository m_data_pasanganRepository;
	@Autowired M_data_lampiran_fileRepository m_data_lampiran_fileRepository;
	@Autowired Par_cabangRepository par_cabangRepository;
	@Autowired M_data_biRepository m_data_biRepository;
	@Autowired Par_akadRepository par_akadRepository;
	@Autowired Par_planRepository par_planRepository;
	@Autowired M_data_agunanRepository m_data_agunanRepository;
	@Autowired M_data_neracaRepository m_data_neracaRepository;
	@Autowired M_data_walletRepository m_data_walletRepository;
	@Autowired M_data_aspek_keuanganRepository m_data_aspek_keuanganRepository;
	@Autowired M_append_surveyRepository m_append_surveyRepository;
	@Autowired M_append_taksasiRepository m_append_taksasiRepository;
	@Autowired M_append_plottingRepository m_append_plottingRepository;
	
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
	
//	private static Logger LOGGER = Logger.getLogger(PencairanMkmController.class.getName());
	Logger LOGGER = LoggerFactory.getLogger(PencairanMkmController.class);
	@PostMapping("/mkm/pencairan/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listDebitur(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();

		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		String cab = param.get("cab");

		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<M_data_loan> instansis = new ArrayList<M_data_loan>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<M_data_loan> pageInsts;

			if (keyword != null) {
				pageInsts = m_data_loanRepository.findKeywordDebiturWithPagination(paging, keyword, cab);
				filtered = m_data_loanRepository.getCount(cab);
			} else {
				pageInsts = m_data_loanRepository.findAllDebiturWithPagination(paging, cab);
				filtered = pageInsts.getNumberOfElements();
			}
			instansis = pageInsts.getContent();

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("loan", instansis);
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
			LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/list ************** " + "@ "
					+ objectMapper.writeValueAsString(response) + " \n");
			LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/list execption ************** " + "@ "
					+ objectMapper.writeValueAsString(e) + " \n");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/mkm/pencairan/getbyid")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getbyid(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/getbyid ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		try {
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				M_data_neraca neraca = m_data_neracaRepository.findByIdNeraca(loan.getId_neraca());
				M_data_wallet wallet = m_data_walletRepository.findByIdWallet(loan.getId_wallet());
				List<M_data_aspek_keuangan> aspek = m_data_aspek_keuanganRepository.findByIdLoan(id_loan);

				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
				response.put("loan", loan);
				response.put("neraca", neraca);
				response.put("wallet", wallet);
				response.put("aspek_keuangan", aspek);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");

			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/getbyid Exception ************** " + "@ "
					+ objectMapper.writeValueAsString(e) + " \n");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/getbyid ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/mkm/pencairan/review")
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
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				loan.setStatus("21");
				loan.setReview2_nama(user_nama);
				loan.setReview2_by(user_id);
				loan.setReview2_date(LocalDateTime.now());
				loan.setReview2_desc(keterangan);
				m_data_loanRepository.save(loan);
				
				m_data_lampiran_fileRepository.updateceklistByIdLoan(id_loan);

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
	
	@PostMapping("/mkm/pencairan/cancel")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> cancel(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/cancel ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String keterangan = param.get("keterangan");

		try {
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				loan.setStatus("22");
				loan.setCancel_nama(user_nama);
				loan.setCancel_by(user_id);
				loan.setCancel_date(LocalDateTime.now());
				loan.setCancel_desc(keterangan);
				m_data_loanRepository.save(loan);

				response.put("kode", "00");
				response.put("pesan", "CANCEL BERHASIL");

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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/cancel ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/mkm/pencairan/updatereview")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> updatereview(@Valid @RequestBody String param,
			@AuthenticationPrincipal Jwt jwt, HttpServletRequest request)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> response = new HashMap<>();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/updatereview ************** " + "@ " + param + " \n");
//		ResponseMessage send = null;
//		DateTimeFormatter formatteryear = DateTimeFormatter.ofPattern("yyyy");
//		LocalDate year = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			JSONObject jsonObject = new JSONObject(param.toString());
			String json = jsonObject.toString();
			LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/updatereview ************** " + "@ " + json + " \n");
			
			String id_loan = jsonObject.get("id_loan").toString().equals("") ? null : jsonObject.get("id_loan").toString(); 
			Integer golongan_bi = jsonObject.get("golongan_bi").toString().equals("") ? null : Integer.parseInt(jsonObject.get("golongan_bi").toString()); 
			String passport = jsonObject.get("passport").toString().equals("") ? null : jsonObject.get("passport").toString(); 
			String npwp = jsonObject.get("npwp").toString().equals("") ? null : jsonObject.get("npwp").toString(); 
			Integer agama = jsonObject.get("agama").toString().equals("") ? null : Integer.parseInt(jsonObject.get("agama").toString()); 
			String status_pernikahan = jsonObject.get("status_pernikahan").toString().equals("") ? null : jsonObject.get("status_pernikahan").toString(); 
			Integer kebangsaan = jsonObject.get("kebangsaan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("kebangsaan").toString()); 
			String alamat_ktp = jsonObject.get("alamat_ktp").toString().equals("") ? null : jsonObject.get("alamat_ktp").toString(); 
			Integer provinsi_ktp = jsonObject.get("provinsi_ktp").toString().equals("") ? null : Integer.parseInt(jsonObject.get("provinsi_ktp").toString()); 
			Integer dati2_ktp = jsonObject.get("dati2_ktp").toString().equals("") ? null : Integer.parseInt(jsonObject.get("dati2_ktp").toString()); 
			Integer kecamatan_ktp = jsonObject.get("kecamatan_ktp").toString().equals("") ? null : Integer.parseInt(jsonObject.get("kecamatan_ktp").toString()); 
			BigDecimal kelurahan_ktp = jsonObject.get("kelurahan_ktp").toString().equals("") ? null : new BigDecimal(jsonObject.get("kelurahan_ktp").toString()); 
			Integer kdpos_ktp = jsonObject.get("kdpos_ktp").toString().equals("") ? null : Integer.parseInt(jsonObject.get("kdpos_ktp").toString()); 
			Integer tanggungan = jsonObject.get("tanggungan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("tanggungan").toString()); 
//			Double biaya_tanggungan = jsonObject.get("biaya_tanggungan").toString().equals("") ? null : Double.parseDouble(jsonObject.get("biaya_tanggungan").toString());
			Integer penghasilan = jsonObject.get("penghasilan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("penghasilan").toString()); 
			 String no_telp=jsonObject.get("no_telp").toString().equals("") ? null : jsonObject.get("no_telp").toString(); 
			 String no_telp_kantor=jsonObject.get("no_telp_kantor").toString().equals("") ? null : jsonObject.get("no_telp_kantor").toString(); 
			 String profesi=jsonObject.get("profesi").toString().equals("") ? null : jsonObject.get("profesi").toString(); 
			 String alamat=jsonObject.get("alamat").toString().equals("") ? null : jsonObject.get("alamat").toString(); 
			 String kode_post=jsonObject.get("kode_post").toString().equals("") ? null : jsonObject.get("kode_post").toString(); 
			 String status_perusahaan=jsonObject.get("status_perusahaan").toString().equals("") ? null : jsonObject.get("status_perusahaan").toString(); 
//			 Double gaji=jsonObject.get("gaji").toString().equals("") ? null : Double.parseDouble(jsonObject.get("gaji").toString());
//			 Double tunjangan=jsonObject.get("tunjangan").toString().equals("") ? null : Double.parseDouble(jsonObject.get("tunjangan").toString());
//			 Double potongan=jsonObject.get("potongan").toString().equals("") ? null : Double.parseDouble(jsonObject.get("potongan").toString());
//			 Double netto=jsonObject.get("netto").toString().equals("") ? null : Double.parseDouble(jsonObject.get("netto").toString());
//			 String share=jsonObject.get("share").toString().equals("") ? null : jsonObject.get("share").toString(); 
			 String kolektif=jsonObject.get("kolektif").toString().equals("") ? null : jsonObject.get("kolektif").toString(); 
			 String kode_dinas=jsonObject.get("kode_dinas").toString().equals("") ? null : jsonObject.get("kode_dinas").toString(); 
			 String kode_sub_dinas=jsonObject.get("kode_sub_dinas").toString().equals("") ? null : jsonObject.get("kode_sub_dinas").toString(); 
			 String kode_sub_sub_dinas=jsonObject.get("kode_sub_sub_dinas").toString().equals("") ? null : jsonObject.get("kode_sub_sub_dinas").toString(); 
			 String sistem_byr_cust=jsonObject.get("sistem_byr_cust").toString().equals("") ? null : jsonObject.get("sistem_byr_cust").toString(); 
			 String sistem_byr_supp=jsonObject.get("sistem_byr_supp").toString().equals("") ? null : jsonObject.get("sistem_byr_supp").toString(); 
			 String nomor_kapling=jsonObject.get("nomor_kapling").toString().equals("") ? null : jsonObject.get("nomor_kapling").toString(); 
			 Double luas_kebun_m=jsonObject.get("luas_kebun_m").toString().equals("") ? Double.parseDouble("0") : Double.parseDouble(jsonObject.get("luas_kebun_m").toString());
			 String jenis_kebun=jsonObject.get("jenis_kebun").toString().equals("") ? null : jsonObject.get("jenis_kebun").toString(); 
			 String off_taker=jsonObject.get("off_taker").toString().equals("") ? null : jsonObject.get("off_taker").toString(); 
			 String no_off_taker=jsonObject.get("no_off_taker").toString().equals("") ? null : jsonObject.get("no_off_taker").toString(); 
			 LocalDate tgl_off_taker=jsonObject.get("tgl_off_taker").toString().equals("") ? null : LocalDate.parse(jsonObject.getString("tgl_off_taker"),
						formatter);
			 Double luas_kebun_h=jsonObject.get("luas_kebun_h").toString().equals("") ? null : Double.parseDouble(jsonObject.get("luas_kebun_h").toString());
			 String legalitas_usaha1=jsonObject.get("legalitas_usaha1").toString().equals("") ? null : jsonObject.get("legalitas_usaha1").toString(); 
			 String no_legalitas_usaha1=jsonObject.get("no_legalitas_usaha1").toString().equals("") ? null : jsonObject.get("no_legalitas_usaha1").toString(); 
			 String legalitas_usaha2=jsonObject.get("legalitas_usaha2").toString().equals("") ? null : jsonObject.get("legalitas_usaha2").toString(); 
			 String no_legalitas_usaha2=jsonObject.get("no_legalitas_usaha2").toString().equals("") ? null : jsonObject.get("no_legalitas_usaha2").toString(); 
			 String legalitas_usaha3=jsonObject.get("legalitas_usaha3").toString().equals("") ? null : jsonObject.get("legalitas_usaha3").toString(); 
			 String no_legalitas_usaha3=jsonObject.get("no_legalitas_usaha3").toString().equals("") ? null : jsonObject.get("no_legalitas_usaha3").toString(); 
			 String legalitas_usaha4=jsonObject.get("legalitas_usaha4").toString().equals("") ? null : jsonObject.get("legalitas_usaha4").toString(); 
			 String no_legalitas_usaha4=jsonObject.get("no_legalitas_usaha4").toString().equals("") ? null : jsonObject.get("no_legalitas_usaha4").toString(); 
			 String legalitas_usaha5=jsonObject.get("legalitas_usaha5").toString().equals("") ? null : jsonObject.get("legalitas_usaha5").toString(); 
			 String no_legalitas_usaha5=jsonObject.get("no_legalitas_usaha5").toString().equals("") ? null : jsonObject.get("no_legalitas_usaha5").toString(); 
			 String legalitas_usaha6=jsonObject.get("legalitas_usaha6").toString().equals("") ? null : jsonObject.get("legalitas_usaha6").toString(); 
			 String no_legalitas_usaha6=jsonObject.get("no_legalitas_usaha6").toString().equals("") ? null : jsonObject.get("no_legalitas_usaha6").toString(); 
			 String ip_kpmu=jsonObject.get("ip_kpmu").toString().equals("") ? null : jsonObject.get("ip_kpmu").toString(); 
			 String ip_kper=jsonObject.get("ip_kper").toString().equals("") ? null : jsonObject.get("ip_kper").toString(); 
			 String ip_gupa=jsonObject.get("ip_gupa").toString().equals("") ? null : jsonObject.get("ip_gupa").toString(); 
			 String ip_lain1=jsonObject.get("ip_lain1").toString().equals("") ? null : jsonObject.get("ip_lain1").toString(); 
			 String ku_izun=jsonObject.get("ku_izun").toString().equals("") ? null : jsonObject.get("ku_izun").toString(); 
			 String ku_ccmp=jsonObject.get("ku_ccmp").toString().equals("") ? null : jsonObject.get("ku_ccmp").toString(); 
			 String ku_pmup=jsonObject.get("ku_pmup").toString().equals("") ? null : jsonObject.get("ku_pmup").toString(); 
			 String ku_lain=jsonObject.get("ku_lain").toString().equals("") ? null : jsonObject.get("ku_lain").toString(); 
			 String ku_pryd=jsonObject.get("ku_pryd").toString().equals("") ? null : jsonObject.get("ku_pryd").toString(); 
			 String ku_ppsp=jsonObject.get("ku_ppsp").toString().equals("") ? null : jsonObject.get("ku_ppsp").toString(); 
			 String ku_stpe=jsonObject.get("ku_stpe").toString().equals("") ? null : jsonObject.get("ku_stpe").toString(); 
			 String ku_lain2=jsonObject.get("ku_lain2").toString().equals("") ? null : jsonObject.get("ku_lain2").toString(); 
			 String ku_spyd=jsonObject.get("ku_spyd").toString().equals("") ? null : jsonObject.get("ku_spyd").toString(); 
			 String ku_lumd=jsonObject.get("ku_lumd").toString().equals("") ? null : jsonObject.get("ku_lumd").toString(); 
			 String ku_jtkd=jsonObject.get("ku_jtkd").toString().equals("") ? null : jsonObject.get("ku_jtkd").toString(); 
			 String ku_lain3=jsonObject.get("ku_lain3").toString().equals("") ? null : jsonObject.get("ku_lain3").toString(); 
			 String pu_pepa=jsonObject.get("pu_pepa").toString().equals("") ? null : jsonObject.get("pu_pepa").toString(); 
			 String pu_prpa=jsonObject.get("pu_prpa").toString().equals("") ? null : jsonObject.get("pu_prpa").toString(); 
			 String nama_koperasi=jsonObject.get("nama_koperasi").toString().equals("") ? null : jsonObject.get("nama_koperasi").toString(); 
			 String status_kepemilikan_kebun=jsonObject.get("status_kepemilikan_kebun").toString().equals("") ? null : jsonObject.get("status_kepemilikan_kebun").toString(); 
			
			 Integer hubungan_bank_lain = jsonObject.get("hubungan_bank_lain").toString().equals("") ? null : Integer.parseInt(jsonObject.get("hubungan_bank_lain").toString()); 
			 String nama_bank_lain=jsonObject.get("nama_bank_lain").toString().equals("") ? null : jsonObject.get("nama_bank_lain").toString(); 
			 String ud_nama_usaha=jsonObject.get("ud_nama_usaha").toString().equals("") ? null : jsonObject.get("ud_nama_usaha").toString(); 
			 String ud_bidang_usaha=jsonObject.get("ud_bidang_usaha").toString().equals("") ? null : jsonObject.get("ud_bidang_usaha").toString(); 
			 Integer ud_lama_usaha= jsonObject.get("ud_lama_usaha").toString().equals("") ? null : Integer.parseInt(jsonObject.get("ud_lama_usaha").toString()); 
			 String ud_status_tempat_usaha=jsonObject.get("ud_status_tempat_usaha").toString().equals("") ? null : jsonObject.get("ud_status_tempat_usaha").toString(); 
			 Double ud_omset_perbulan =jsonObject.get("ud_omset_perbulan").toString().equals("") ? null : Double.parseDouble(jsonObject.get("ud_omset_perbulan").toString());
			 Double ud_profit_perbulan=jsonObject.get("ud_profit_perbulan").toString().equals("") ? null : Double.parseDouble(jsonObject.get("ud_profit_perbulan").toString());
			 String ap_aspek_manajemen=jsonObject.get("ap_aspek_manajemen").toString().equals("") ? null : jsonObject.get("ap_aspek_manajemen").toString(); 
			 String ap_orang_yang_ditemui=jsonObject.get("ap_orang_yang_ditemui").toString().equals("") ? null : jsonObject.get("ap_orang_yang_ditemui").toString(); 
			 String ap_aspek_pemasaran=jsonObject.get("ap_aspek_pemasaran").toString().equals("") ? null : jsonObject.get("ap_aspek_pemasaran").toString(); 
			 String ap_aspek_teknis=jsonObject.get("ap_aspek_teknis").toString().equals("") ? null : jsonObject.get("ap_aspek_teknis").toString(); 
			 String ap_aspek_syariah=jsonObject.get("ap_aspek_syariah").toString().equals("") ? null : jsonObject.get("ap_aspek_syariah").toString(); 
			 Double copph=jsonObject.get("copph").toString().equals("") ? null : Double.parseDouble(jsonObject.get("copph").toString());
			 Double jumlah_cost_of_project=jsonObject.get("jumlah_cost_of_project").toString().equals("") ? null : Double.parseDouble(jsonObject.get("jumlah_cost_of_project").toString());
			 Double by_mp_kebun=jsonObject.get("by_mp_kebun").toString().equals("") ? null : Double.parseDouble(jsonObject.get("by_mp_kebun").toString());
//			 Double ttl_copph_allby=jsonObject.get("ttl_copph_allby").toString().equals("") ? null : Double.parseDouble(jsonObject.get("ttl_copph_allby").toString());
			 Double dana_bantuan=jsonObject.get("dana_bantuan").toString().equals("") ? null : Double.parseDouble(jsonObject.get("dana_bantuan").toString());
			 Double self_financing=jsonObject.get("self_financing").toString().equals("") ? null : Double.parseDouble(jsonObject.get("self_financing").toString());
			 String cp_jenis1=jsonObject.get("cp_jenis1").toString().equals("") ? null : jsonObject.get("cp_jenis1").toString(); 
			 Double cp_plafon1=jsonObject.get("cp_plafon1").toString().equals("") ? null : Double.parseDouble(jsonObject.get("cp_plafon1").toString());
			 Double cp_baki_debet1=jsonObject.get("cp_baki_debet1").toString().equals("") ? null : Double.parseDouble(jsonObject.get("cp_baki_debet1").toString());
			 Integer cp_jangka_waktu1= jsonObject.get("cp_jangka_waktu1").toString().equals("") ? null : Integer.parseInt(jsonObject.get("cp_jangka_waktu1").toString()); 
			 Double cp_margin1=jsonObject.get("cp_margin1").toString().equals("") ? null : Double.parseDouble(jsonObject.get("cp_margin1").toString());
			 Double cp_angsuran1=jsonObject.get("cp_angsuran1").toString().equals("") ? null : Double.parseDouble(jsonObject.get("cp_angsuran1").toString());
			 String cp_tujuan_pinjaman1=jsonObject.get("cp_tujuan_pinjaman1").toString().equals("") ? null : jsonObject.get("cp_tujuan_pinjaman1").toString(); 
			 Double cp_tunggakan1=jsonObject.get("cp_tunggakan1").toString().equals("") ? null : Double.parseDouble(jsonObject.get("cp_tunggakan1").toString());
			 String cp_kolektibilitas1=jsonObject.get("cp_kolektibilitas1").toString().equals("") ? null : jsonObject.get("cp_kolektibilitas1").toString(); 
			 String cp_jenis2=jsonObject.get("cp_jenis2").toString().equals("") ? null : jsonObject.get("cp_jenis2").toString(); 
			 Double cp_plafon2=jsonObject.get("cp_plafon2").toString().equals("") ? null : Double.parseDouble(jsonObject.get("cp_plafon2").toString());
			 Double cp_baki_debet2=jsonObject.get("cp_baki_debet2").toString().equals("") ? null : Double.parseDouble(jsonObject.get("cp_baki_debet2").toString());
			 Integer cp_jangka_waktu2= jsonObject.get("cp_jangka_waktu2").toString().equals("") ? null : Integer.parseInt(jsonObject.get("cp_jangka_waktu2").toString()); 
			 Double cp_margin2=jsonObject.get("cp_margin2").toString().equals("") ? null : Double.parseDouble(jsonObject.get("cp_margin2").toString());
			 Double cp_angsuran2=jsonObject.get("cp_angsuran2").toString().equals("") ? null : Double.parseDouble(jsonObject.get("cp_angsuran2").toString());
			 String cp_tujuan_pinjaman2=jsonObject.get("cp_tujuan_pinjaman2").toString().equals("") ? null : jsonObject.get("cp_tujuan_pinjaman2").toString(); 
			 Double cp_tunggakan2=jsonObject.get("cp_tunggakan2").toString().equals("") ? null : Double.parseDouble(jsonObject.get("cp_tunggakan2").toString());
			 String cp_kolektibilitas2=jsonObject.get("cp_kolektibilitas2").toString().equals("") ? null : jsonObject.get("cp_kolektibilitas2").toString(); 
			 String cp_jenis3=jsonObject.get("cp_jenis3").toString().equals("") ? null : jsonObject.get("cp_jenis3").toString(); 
			 Double cp_plafon3=jsonObject.get("cp_plafon3").toString().equals("") ? null : Double.parseDouble(jsonObject.get("cp_plafon3").toString());
			 Double cp_baki_debet3=jsonObject.get("cp_baki_debet3").toString().equals("") ? null : Double.parseDouble(jsonObject.get("cp_baki_debet3").toString());
			 Integer cp_jangka_waktu3= jsonObject.get("cp_jangka_waktu3").toString().equals("") ? null : Integer.parseInt(jsonObject.get("cp_jangka_waktu3").toString()); 
			 Double cp_margin3=jsonObject.get("cp_margin3").toString().equals("") ? null : Double.parseDouble(jsonObject.get("cp_margin3").toString());
			 Double cp_angsuran3=jsonObject.get("cp_angsuran3").toString().equals("") ? null : Double.parseDouble(jsonObject.get("cp_angsuran3").toString());
			 String cp_tujuan_pinjaman3=jsonObject.get("cp_tujuan_pinjaman3").toString().equals("") ? null : jsonObject.get("cp_tujuan_pinjaman3").toString(); 
			 Double cp_tunggakan3=jsonObject.get("cp_tunggakan3").toString().equals("") ? null : Double.parseDouble(jsonObject.get("cp_tunggakan3").toString());
			 String cp_kolektibilitas3=jsonObject.get("cp_kolektibilitas3").toString().equals("") ? null : jsonObject.get("cp_kolektibilitas3").toString(); 
			 String jenis_pembiayaan=jsonObject.get("jenis_pembiayaan").toString().equals("") ? null : jsonObject.get("jenis_pembiayaan").toString(); 
			 Double premi_asuransi_lama=jsonObject.get("premi_asuransi_lama").toString().equals("") ? null : Double.parseDouble(jsonObject.get("premi_asuransi_lama").toString());
			 String tgl_kredit_lama=jsonObject.get("tgl_kredit_lama").toString().equals("") ? null : jsonObject.get("tgl_kredit_lama").toString(); 
			 String benefit_kredit_lama=jsonObject.get("benefit_kredit_lama").toString().equals("") ? null : jsonObject.get("benefit_kredit_lama").toString(); 
			 Integer tenor_kredit_lama= jsonObject.get("tenor_kredit_lama").toString().equals("") ? null : Integer.parseInt(jsonObject.get("tenor_kredit_lama").toString()); 
			 Integer kuasa_pemotongan= jsonObject.get("kuasa_pemotongan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("kuasa_pemotongan").toString()); 
			 Integer persentase_kuasa_pemotongan= jsonObject.get("persentase_kuasa_pemotongan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("persentase_kuasa_pemotongan").toString()); 
			 String kode_officer_1=jsonObject.get("kode_officer_1").toString().equals("") ? null : jsonObject.get("kode_officer_1").toString(); 
			 String kode_officer_2=jsonObject.get("kode_officer_2").toString().equals("") ? null : jsonObject.get("kode_officer_2").toString(); 
			 String kode_referal=jsonObject.get("kode_referal").toString().equals("") ? null : jsonObject.get("kode_referal").toString(); 
			 String kode_notaris=jsonObject.get("kode_notaris").toString().equals("") ? null : jsonObject.get("kode_notaris").toString(); 
			 String tujuan_pembiayaan=jsonObject.get("tujuan_pembiayaan").toString().equals("") ? null : jsonObject.get("tujuan_pembiayaan").toString(); 
			 Integer id_kategori_produk= jsonObject.get("id_kategori_produk").toString().equals("") ? null : Integer.parseInt(jsonObject.get("id_kategori_produk").toString()); 
			 Integer id_plan= jsonObject.get("id_plan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("id_plan").toString()); 
			 String frequensi_pembayaran_margin=jsonObject.get("frequensi_pembayaran_margin").toString().equals("") ? null : jsonObject.get("frequensi_pembayaran_margin").toString(); 
			 Integer frequensi_pembayaran_number= jsonObject.get("frequensi_pembayaran_number").toString().equals("") ? null : Integer.parseInt(jsonObject.get("frequensi_pembayaran_number").toString()); 
			 Integer grace_period= jsonObject.get("grace_period").toString().equals("") ? null : Integer.parseInt(jsonObject.get("grace_period").toString()); 
			 Double harga_perolehan=jsonObject.get("harga_perolehan").toString().equals("") ? null : Double.parseDouble(jsonObject.get("harga_perolehan").toString());
			 Double pajak=jsonObject.get("pajak").toString().equals("") ? null : Double.parseDouble(jsonObject.get("pajak").toString());
			 Double diskon=jsonObject.get("diskon").toString().equals("") ? null : Double.parseDouble(jsonObject.get("diskon").toString());
			 Double plafon_pengajuan=jsonObject.get("plafon_pengajuan").toString().equals("") ? null : Double.parseDouble(jsonObject.get("plafon_pengajuan").toString());
			 Double uang_muka=jsonObject.get("uang_muka").toString().equals("") ? null : Double.parseDouble(jsonObject.get("uang_muka").toString());
			 Integer tenor_pengajuan= jsonObject.get("tenor_pengajuan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("tenor_pengajuan").toString()); 
			 String kode_margin=jsonObject.get("kode_margin").toString().equals("") ? null : jsonObject.get("kode_margin").toString(); 
			 BigDecimal margin_pengajuan=jsonObject.get("margin_pengajuan").toString().equals("") ? null : new BigDecimal(jsonObject.get("margin_pengajuan").toString());
			 Float basis_point_margin=jsonObject.get("basis_point_margin").toString().equals("") ? null : Float.parseFloat(jsonObject.get("basis_point_margin").toString());
			 String basis_point_margin_mark=jsonObject.get("basis_point_margin_mark").toString().equals("") ? null : jsonObject.get("basis_point_margin_mark").toString(); 
			 Float exp_rate=jsonObject.get("exp_rate").toString().equals("") ? null : Float.parseFloat(jsonObject.get("exp_rate").toString());
			 Double nisbah_bank=jsonObject.get("nisbah_bank").toString().equals("") ? null : Double.parseDouble(jsonObject.get("nisbah_bank").toString());
			 Double nisbah_nasabah=jsonObject.get("nisbah_nasabah").toString().equals("") ? null : Double.parseDouble(jsonObject.get("nisbah_nasabah").toString());
			 Double angsuran_pengajuan=jsonObject.get("angsuran_pengajuan").toString().equals("") ? null : Double.parseDouble(jsonObject.get("angsuran_pengajuan").toString());
			 String blokir_saldo=jsonObject.get("blokir_saldo").toString().equals("") ? null : jsonObject.get("blokir_saldo").toString(); 
			 String is_wakalah=jsonObject.get("is_wakalah").toString().equals("") ? null : jsonObject.get("is_wakalah").toString(); 
			 String norek_wakalah=jsonObject.get("norek_wakalah").toString().equals("") ? null : jsonObject.get("norek_wakalah").toString(); 
			 String kode_rekening_wakalah=jsonObject.get("kode_rekening_wakalah").toString().equals("") ? null : jsonObject.get("kode_rekening_wakalah").toString(); 
			 String nama_rekening_wakalah=jsonObject.get("nama_rekening_wakalah").toString().equals("") ? null : jsonObject.get("nama_rekening_wakalah").toString(); 
			 LocalDate tgl_kunjungan=jsonObject.get("tgl_kunjungan").toString().equals("") ? null : LocalDate.parse(jsonObject.getString("tgl_kunjungan"), formatter);
			 String officer_bank=jsonObject.get("officer_bank").toString().equals("") ? null : jsonObject.get("officer_bank").toString(); 
//			 Double nilai_harta_thp_kredit=jsonObject.get("nilai_harta_thp_kredit").toString().equals("") ? null : Double.parseDouble(jsonObject.get("nilai_harta_thp_kredit").toString());
			 
			 String ktp_pasangan= jsonObject.get("ktp_pasangan").toString().equals("") ? null : jsonObject.get("ktp_pasangan").toString(); 
			 String nama_pasangan= jsonObject.get("nama_pasangan").toString().equals("") ? null : jsonObject.get("nama_pasangan").toString(); 
			 String golongan_deb_pasangan= jsonObject.get("golongan_deb_pasangan").toString().equals("") ? null : jsonObject.get("golongan_deb_pasangan").toString(); 
			 Integer golongan_bi_pasangan= jsonObject.get("golongan_bi_pasangan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("golongan_bi_pasangan").toString()); 
			 Integer hubungan_bank_pasangan= jsonObject.get("hubungan_bank_pasangan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("hubungan_bank_pasangan").toString()); 
			 String passport_pasangan= jsonObject.get("passport_pasangan").toString().equals("") ? null : jsonObject.get("passport_pasangan").toString(); 
			 String npwp_pasangan= jsonObject.get("npwp_pasangan").toString().equals("") ? null : jsonObject.get("npwp_pasangan").toString(); 
			 Integer pendidikan_pasangan= jsonObject.get("pendidikan_pasangan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("pendidikan_pasangan").toString()); 
			 Integer agama_pasangan= jsonObject.get("agama_pasangan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("agama_pasangan").toString()); 
			 String tmp_lahir_pasangan= jsonObject.get("tmp_lahir_pasangan").toString().equals("") ? null : jsonObject.get("tmp_lahir_pasangan").toString(); 
			 LocalDate tgl_lahir_pasangan= jsonObject.get("tgl_lahir_pasangan").toString().equals("") ? null : LocalDate.parse(jsonObject.getString("tgl_lahir_pasangan"), formatter);
			 Integer kebangsaan_pasangan= jsonObject.get("kebangsaan_pasangan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("kebangsaan_pasangan").toString()); 
			 String alamat_pasangan= jsonObject.get("alamat_pasangan").toString().equals("") ? null : jsonObject.get("alamat_pasangan").toString(); 
			 String no_telp_pasangan= jsonObject.get("no_telp_pasangan").toString().equals("") ? null : jsonObject.get("no_telp_pasangan").toString(); 
			 Integer provinsi_pasangan= jsonObject.get("provinsi_pasangan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("provinsi_pasangan").toString()); 
			 Integer dati2_pasangan= jsonObject.get("dati2_pasangan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("dati2_pasangan").toString()); 
			 Integer kecamatan_pasangan= jsonObject.get("kecamatan_pasangan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("kecamatan_pasangan").toString()); 
			 BigDecimal kelurahan_pasangan= jsonObject.get("kelurahan_pasangan").toString().equals("") ? null : new BigDecimal(jsonObject.get("kelurahan_pasangan").toString()); 
			 Integer kdpos_pasangan= jsonObject.get("kdpos_pasangan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("kdpos_pasangan").toString()); 
			 String alamat_ktp_pasangan= jsonObject.get("alamat_ktp_pasangan").toString().equals("") ? null : jsonObject.get("alamat_ktp_pasangan").toString(); 
			 Integer provinsi_ktp_pasangan= jsonObject.get("provinsi_ktp_pasangan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("provinsi_ktp_pasangan").toString()); 
			 Integer dati2_ktp_pasangan= jsonObject.get("dati2_ktp_pasangan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("dati2_ktp_pasangan").toString()); 
			 Integer kecamatan_ktp_pasangan= jsonObject.get("kecamatan_ktp_pasangan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("kecamatan_ktp_pasangan").toString()); 
			 BigDecimal kelurahan_ktp_pasangan= jsonObject.get("kelurahan_ktp_pasangan").toString().equals("") ? null : new BigDecimal(jsonObject.get("kelurahan_ktp_pasangan").toString()); 
			 Integer kdpos_ktp_pasangan= jsonObject.get("kdpos_ktp_pasangan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("kdpos_ktp_pasangan").toString()); 
			 Integer penghasilan_pasangan= jsonObject.get("penghasilan_pasangan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("penghasilan_pasangan").toString()); 
			 String profesi_pasangan= jsonObject.get("profesi_pasangan").toString().equals("") ? null : jsonObject.get("profesi_pasangan").toString(); 
			 String nama_instansi_pasangan= jsonObject.get("nama_instansi_pasangan").toString().equals("") ? null : jsonObject.get("nama_instansi_pasangan").toString(); 
			 String status_perusahaan_pasangan= jsonObject.get("status_perusahaan_pasangan").toString().equals("") ? null : jsonObject.get("status_perusahaan_pasangan").toString(); 
			 String jabatan_pasangan= jsonObject.get("jabatan_pasangan").toString().equals("") ? null : jsonObject.get("jabatan_pasangan").toString(); 
			 String bidang_usaha_pasangan= jsonObject.get("bidang_usaha_pasangan").toString().equals("") ? null : jsonObject.get("bidang_usaha_pasangan").toString(); 
			 Integer tahun_bekerja_pasangan= jsonObject.get("tahun_bekerja_pasangan").toString().equals("") ? null : Integer.parseInt(jsonObject.get("tahun_bekerja_pasangan").toString()); 
			 Double gaji_pasangan= jsonObject.get("gaji_pasangan").toString().equals("") ? null : Double.parseDouble(jsonObject.get("gaji_pasangan").toString());
			 Double tunjangan_pasangan= jsonObject.get("tunjangan_pasangan").toString().equals("") ? null : Double.parseDouble(jsonObject.get("tunjangan_pasangan").toString());
			 Double potongan_pasangan= jsonObject.get("potongan_pasangan").toString().equals("") ? null : Double.parseDouble(jsonObject.get("potongan_pasangan").toString());
			 Double netto_pasangan= jsonObject.get("netto_pasangan").toString().equals("") ? null : Double.parseDouble(jsonObject.get("netto_pasangan").toString());
			 String desc_penghasilan_lainnya1= jsonObject.get("desc_penghasilan_lainnya1").toString().equals("") ? null : jsonObject.get("desc_penghasilan_lainnya1").toString(); 
			 Double jumlah_penghasilan_lainnya1= jsonObject.get("jumlah_penghasilan_lainnya1").toString().equals("") ? null : Double.parseDouble(jsonObject.get("jumlah_penghasilan_lainnya1").toString());
			 String desc_penghasilan_lainnya2= jsonObject.get("desc_penghasilan_lainnya2").toString().equals("") ? null : jsonObject.get("desc_penghasilan_lainnya2").toString(); 
			 Double jumlah_penghasilan_lainnya2= jsonObject.get("jumlah_penghasilan_lainnya2").toString().equals("") ? null : Double.parseDouble(jsonObject.get("jumlah_penghasilan_lainnya2").toString());
			 String desc_penghasilan_lainnya3= jsonObject.get("desc_penghasilan_lainnya3").toString().equals("") ? null : jsonObject.get("desc_penghasilan_lainnya3").toString(); 
			 Double jumlah_penghasilan_lainnya3= jsonObject.get("jumlah_penghasilan_lainnya3").toString().equals("") ? null : Double.parseDouble(jsonObject.get("jumlah_penghasilan_lainnya3").toString());
			 Double kolektif_pasangan= jsonObject.get("kolektif_pasangan").toString().equals("") ? null : Double.parseDouble(jsonObject.get("kolektif_pasangan").toString());
			 String sistem_byr_cust_pasangan= jsonObject.get("sistem_byr_cust_pasangan").toString().equals("") ? null : jsonObject.get("sistem_byr_cust_pasangan").toString(); 
			 String sistem_byr_supp_pasangan= jsonObject.get("sistem_byr_supp_pasangan").toString().equals("") ? null : jsonObject.get("sistem_byr_supp_pasangan").toString(); 

			 LocalDateTime datepost = LocalDateTime.now();
			String userid = jsonObject.get("userid").toString().equals("") ? null : jsonObject.get("userid").toString(); 
			String user_nama = jsonObject.get("user_nama").toString().equals("") ? null : jsonObject.get("user_nama").toString(); 
			
			JSONArray agunan = new JSONArray(jsonObject.getString("agunan"));
			
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			String ktp = null;
			if (loan != null) {
				ktp = loan.getId_debitur();
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. id loan " + id_loan + "  ini gak ada, coba yang lain ya !!!");
				LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/updatereview ************** " + "@ " + json + " \n");
				
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			
			
			
			M_data_debitur debitur = m_data_debiturRepository.findByKtpDebitur(ktp);
			debitur.setGolongan_bi(golongan_bi); 
			debitur.setPassport(passport); 
			debitur.setNpwp(npwp); 
			debitur.setAgama(agama); 
			debitur.setTanggungan(tanggungan);
			debitur.setStatus_pernikahan(status_pernikahan); 
			debitur.setKebangsaan(kebangsaan); 
			debitur.setAlamat_ktp(alamat_ktp); 
			debitur.setNo_telp(no_telp);
			debitur.setProvinsi_ktp(provinsi_ktp); 
			debitur.setDati2_ktp(dati2_ktp); 
			debitur.setKecamatan_ktp(kecamatan_ktp); 
			debitur.setKelurahan_ktp(kelurahan_ktp); 
			debitur.setKdpos_ktp(kdpos_ktp); 
			debitur.setPenghasilan(penghasilan); 
			debitur.setUserid_edit(userid);
			debitur.setDatepost_edit(LocalDateTime.now());
			System.out.println("##### UPDATE ##### ");
			m_data_debiturRepository.save(debitur);
			
			M_data_penghasilan data_penghasilan = m_data_penghasilanRepository.findByIdPenghasilan(loan.getId_penghasilan());
			data_penghasilan.setNo_telp(no_telp_kantor);
			data_penghasilan.setProfesi(profesi); 
			data_penghasilan.setAlamat(alamat); 
			data_penghasilan.setKode_post(kode_post); 
			data_penghasilan.setStatus_perusahaan(status_perusahaan); 
			data_penghasilan.setKolektif(kolektif); 
			data_penghasilan.setKode_dinas(kode_dinas); 
			data_penghasilan.setKode_sub_dinas(kode_sub_dinas); 
			data_penghasilan.setKode_sub_sub_dinas(kode_sub_sub_dinas); 
			data_penghasilan.setSistem_byr_cust(sistem_byr_cust); 
			data_penghasilan.setSistem_byr_supp(sistem_byr_supp); 
			data_penghasilan.setNomor_kapling(nomor_kapling); 
			data_penghasilan.setLuas_kebun_m(luas_kebun_m); 
			data_penghasilan.setJenis_kebun(jenis_kebun); 
			data_penghasilan.setOff_taker(off_taker); 
			data_penghasilan.setNo_off_taker(no_off_taker); 
			data_penghasilan.setTgl_off_taker(tgl_off_taker); 
			data_penghasilan.setLuas_kebun_h(luas_kebun_h); 
			data_penghasilan.setLegalitas_usaha1(legalitas_usaha1); 
			data_penghasilan.setNo_legalitas_usaha1(no_legalitas_usaha1); 
			data_penghasilan.setLegalitas_usaha2(legalitas_usaha2); 
			data_penghasilan.setNo_legalitas_usaha2(no_legalitas_usaha2); 
			data_penghasilan.setLegalitas_usaha3(legalitas_usaha3); 
			data_penghasilan.setNo_legalitas_usaha3(no_legalitas_usaha3); 
			data_penghasilan.setLegalitas_usaha4(legalitas_usaha4); 
			data_penghasilan.setNo_legalitas_usaha4(no_legalitas_usaha4); 
			data_penghasilan.setLegalitas_usaha5(legalitas_usaha5); 
			data_penghasilan.setNo_legalitas_usaha5(no_legalitas_usaha5); 
			data_penghasilan.setLegalitas_usaha6(legalitas_usaha6); 
			data_penghasilan.setNo_legalitas_usaha6(no_legalitas_usaha6); 
			data_penghasilan.setIp_kpmu(ip_kpmu); 
			data_penghasilan.setIp_kper(ip_kper); 
			data_penghasilan.setIp_gupa(ip_gupa); 
			data_penghasilan.setIp_lain1(ip_lain1); 
			data_penghasilan.setKu_izun(ku_izun); 
			data_penghasilan.setKu_ccmp(ku_ccmp); 
			data_penghasilan.setKu_pmup(ku_pmup); 
			data_penghasilan.setKu_lain(ku_lain); 
			data_penghasilan.setKu_pryd(ku_pryd); 
			data_penghasilan.setKu_ppsp(ku_ppsp); 
			data_penghasilan.setKu_stpe(ku_stpe); 
			data_penghasilan.setKu_lain2(ku_lain2); 
			data_penghasilan.setKu_spyd(ku_spyd); 
			data_penghasilan.setKu_lumd(ku_lumd); 
			data_penghasilan.setKu_jtkd(ku_jtkd); 
			data_penghasilan.setKu_lain3(ku_lain3); 
			data_penghasilan.setPu_pepa(pu_pepa); 
			data_penghasilan.setPu_prpa(pu_prpa); 
			data_penghasilan.setNama_koperasi(nama_koperasi);
			data_penghasilan.setStatus_kepemilikan_kebun(status_kepemilikan_kebun);
			m_data_penghasilanRepository.save(data_penghasilan);
			
			String id_pasangan = loan.getId_pasangan();
			if (status_pernikahan.equals("M")) {		
			if (loan.getId_pasangan()==null) {
				M_data_pasangan pasangan_debitur = new M_data_pasangan(ktp_pasangan, loan.getId_debitur().toString(), nama_pasangan, golongan_deb_pasangan, golongan_bi_pasangan, hubungan_bank_pasangan,
						passport_pasangan, npwp_pasangan, pendidikan_pasangan, agama_pasangan, tmp_lahir_pasangan, tgl_lahir_pasangan,kebangsaan_pasangan, alamat_pasangan, no_telp_pasangan, 
						provinsi_pasangan, dati2_pasangan, kecamatan_pasangan, kelurahan_pasangan, kdpos_pasangan, alamat_ktp_pasangan, provinsi_ktp_pasangan, dati2_ktp_pasangan, kecamatan_ktp_pasangan,
						kelurahan_ktp_pasangan, kdpos_ktp_pasangan, penghasilan_pasangan, profesi_pasangan, nama_instansi_pasangan, status_perusahaan_pasangan, jabatan_pasangan, bidang_usaha_pasangan,
						tahun_bekerja_pasangan, gaji_pasangan, tunjangan_pasangan, potongan_pasangan, netto_pasangan, desc_penghasilan_lainnya1, jumlah_penghasilan_lainnya1, desc_penghasilan_lainnya2,
						jumlah_penghasilan_lainnya2, desc_penghasilan_lainnya3, jumlah_penghasilan_lainnya3, kolektif_pasangan, Integer.parseInt(loan.getId_cab()), userid, datepost, sistem_byr_cust_pasangan,
						sistem_byr_supp_pasangan);
				
				m_data_pasanganRepository.save(pasangan_debitur);
				id_pasangan = pasangan_debitur.getId();
			} else {
				M_data_pasangan data_pasangan = m_data_pasanganRepository.findByKtpPasangan(ktp_pasangan);
				data_pasangan.setNama(nama_pasangan);
				data_pasangan.setGolongan_deb(golongan_deb_pasangan);
				data_pasangan.setGolongan_bi(golongan_bi_pasangan);
				data_pasangan.setHubungan_bank(hubungan_bank_pasangan);
				data_pasangan.setPassport(passport_pasangan);
				data_pasangan.setNpwp(npwp_pasangan);
				data_pasangan.setPendidikan(pendidikan_pasangan);
				data_pasangan.setAgama(agama_pasangan);
				data_pasangan.setTmp_lahir(tmp_lahir_pasangan);
				data_pasangan.setTgl_lahir(tgl_lahir_pasangan);
				data_pasangan.setKebangsaan(kebangsaan_pasangan);
				data_pasangan.setAlamat(alamat_pasangan);
				data_pasangan.setNo_telp(no_telp_pasangan);
				data_pasangan.setProvinsi(provinsi_pasangan);
				data_pasangan.setDati2(dati2_pasangan);
				data_pasangan.setKecamatan(kecamatan_pasangan);
				data_pasangan.setKelurahan(kelurahan_pasangan);
				data_pasangan.setKdpos(kdpos_pasangan);
				data_pasangan.setAlamat_ktp(alamat_ktp_pasangan);
				data_pasangan.setProvinsi_ktp(provinsi_ktp_pasangan);
				data_pasangan.setDati2_ktp(dati2_ktp_pasangan);
				data_pasangan.setKecamatan_ktp(kecamatan_ktp_pasangan);
				data_pasangan.setKelurahan_ktp(kelurahan_ktp_pasangan);
				data_pasangan.setKdpos_ktp(kdpos_ktp_pasangan);
				data_pasangan.setPenghasilan(penghasilan_pasangan);
				data_pasangan.setProfesi(profesi_pasangan);
				data_pasangan.setNama_instansi(nama_instansi_pasangan);
				data_pasangan.setStatus_perusahaan(status_perusahaan_pasangan);
				data_pasangan.setJabatan(jabatan_pasangan);
				data_pasangan.setBidang_usaha(bidang_usaha_pasangan);
				data_pasangan.setTahun_bekerja(tahun_bekerja_pasangan);
				data_pasangan.setGaji(gaji_pasangan);
				data_pasangan.setTunjangan(tunjangan_pasangan);
				data_pasangan.setPotongan(potongan_pasangan);
				data_pasangan.setNetto(netto_pasangan);
				data_pasangan.setDesc_penghasilan_lainnya1(desc_penghasilan_lainnya1);
				data_pasangan.setJumlah_penghasilan_lainnya1(jumlah_penghasilan_lainnya1);
				data_pasangan.setDesc_penghasilan_lainnya2(desc_penghasilan_lainnya2);
				data_pasangan.setJumlah_penghasilan_lainnya2(jumlah_penghasilan_lainnya2);
				data_pasangan.setDesc_penghasilan_lainnya3(desc_penghasilan_lainnya3);
				data_pasangan.setJumlah_penghasilan_lainnya3(jumlah_penghasilan_lainnya3);
				data_pasangan.setKolektif(kolektif_pasangan);
				data_pasangan.setSistem_byr_cust(sistem_byr_cust_pasangan);
				data_pasangan.setSistem_byr_supp(sistem_byr_supp_pasangan);
				data_pasangan.setUserid_edit(userid);
				data_pasangan.setDatepost_edit(datepost);
				m_data_pasanganRepository.save(data_pasangan);
				
			}
			}
			
			loan.setId_pasangan(id_pasangan);
			loan.setHubungan_bank_lain(hubungan_bank_lain);
			loan.setNama_bank_lain(nama_bank_lain); 
			loan.setUd_nama_usaha(ud_nama_usaha);
			loan.setUd_bidang_usaha(ud_bidang_usaha);
			loan.setUd_lama_usaha(ud_lama_usaha);
			loan.setUd_status_tempat_usaha(ud_status_tempat_usaha); 
			loan.setUd_omset_perbulan(ud_omset_perbulan); 
			loan.setUd_profit_perbulan(ud_profit_perbulan); 
			loan.setAp_aspek_manajemen(ap_aspek_manajemen); 
			loan.setAp_orang_yang_ditemui(ap_orang_yang_ditemui); 
			loan.setAp_aspek_pemasaran(ap_aspek_pemasaran); 
			loan.setAp_aspek_teknis(ap_aspek_teknis); 
			loan.setAp_aspek_syariah(ap_aspek_syariah); 
			loan.setCopph(copph); 
			loan.setJumlah_cost_of_project(jumlah_cost_of_project); 
			loan.setBy_mp_kebun(by_mp_kebun); 
			loan.setDana_bantuan(dana_bantuan); 
			loan.setSelf_financing(self_financing); 
			loan.setCp_jenis1(cp_jenis1); 
			loan.setCp_plafon1(cp_plafon1); 
			loan.setCp_baki_debet1(cp_baki_debet1); 
			loan.setCp_jangka_waktu1(cp_jangka_waktu1); 
			loan.setCp_margin1(cp_margin1); 
			loan.setCp_angsuran1(cp_angsuran1); 
			loan.setCp_tujuan_pinjaman1(cp_tujuan_pinjaman1); 
			loan.setCp_tunggakan1(cp_tunggakan1); 
			loan.setCp_kolektibilitas1(cp_kolektibilitas1); 
			loan.setCp_jenis2(cp_jenis2); 
			loan.setCp_plafon2(cp_plafon2); 
			loan.setCp_baki_debet2(cp_baki_debet2); 
			loan.setCp_jangka_waktu2(cp_jangka_waktu2); 
			loan.setCp_margin2(cp_margin2); 
			loan.setCp_angsuran2(cp_angsuran2); 
			loan.setCp_tujuan_pinjaman2(cp_tujuan_pinjaman2); 
			loan.setCp_tunggakan2(cp_tunggakan2); 
			loan.setCp_kolektibilitas2(cp_kolektibilitas2); 
			loan.setCp_jenis3(cp_jenis3); 
			loan.setCp_plafon3(cp_plafon3); 
			loan.setCp_baki_debet3(cp_baki_debet3); 
			loan.setCp_jangka_waktu3(cp_jangka_waktu3); 
			loan.setCp_margin3(cp_margin3); 
			loan.setCp_angsuran3(cp_angsuran3); 
			loan.setCp_tujuan_pinjaman3(cp_tujuan_pinjaman3); 
			loan.setCp_tunggakan3(cp_tunggakan3); 
			loan.setCp_kolektibilitas3(cp_kolektibilitas3); 
			loan.setJenis_pembiayaan(jenis_pembiayaan); 
			loan.setPremi_asuransi_lama(premi_asuransi_lama); 
			loan.setTgl_kredit_lama(tgl_kredit_lama); 
			loan.setBenefit_kredit_lama(benefit_kredit_lama); 
			loan.setTenor_kredit_lama(tenor_kredit_lama); 
			loan.setKuasa_pemotongan(kuasa_pemotongan); 
			loan.setPersentase_kuasa_pemotongan(persentase_kuasa_pemotongan); 
			loan.setKode_officer_1(kode_officer_1); 
			loan.setKode_officer_2(kode_officer_2); 
			loan.setKode_referal(kode_referal); 
			loan.setKode_notaris(kode_notaris); 
			loan.setTujuan_pembiayaan(tujuan_pembiayaan); 
			loan.setId_kategori_produk(id_kategori_produk); 
			loan.setId_plan(id_plan); 
			loan.setFrequensi_pembayaran_margin(frequensi_pembayaran_margin); 
			loan.setFrequensi_pembayaran_number(frequensi_pembayaran_number); 
			loan.setGrace_period(grace_period); 
			loan.setHarga_perolehan(harga_perolehan); 
			loan.setPajak(pajak); 
			loan.setDiskon(diskon); 
			loan.setPlafon_pengajuan(plafon_pengajuan); 
			loan.setUang_muka(uang_muka); 
			loan.setTenor_pengajuan(tenor_pengajuan); 
			loan.setKode_margin(kode_margin); 
			loan.setMargin_pengajuan(margin_pengajuan); 
			loan.setBasis_point_margin(basis_point_margin); 
			loan.setBasis_point_margin_mark(basis_point_margin_mark); 
			loan.setExp_rate(exp_rate); 
			loan.setNisbah_bank(nisbah_bank); 
			loan.setNisbah_nasabah(nisbah_nasabah); 
			loan.setAngsuran_pengajuan(angsuran_pengajuan); 
			loan.setBlokir_saldo(blokir_saldo); 
			loan.setIs_wakalah(is_wakalah); 
			loan.setNorek_wakalah(norek_wakalah); 
			loan.setKode_rekening_wakalah(kode_rekening_wakalah); 
			loan.setNama_rekening_wakalah(nama_rekening_wakalah); 
			loan.setTgl_kunjungan(tgl_kunjungan); 
			loan.setOfficer_bank(officer_bank); 
			loan.setStatus("20");
			loan.setInput2_by(userid);
			loan.setInput2_nama(user_nama);
			loan.setInput2_date(LocalDateTime.now());
			m_data_loanRepository.save(loan);
			
			if (agunan.length() >= 1) {
				m_data_agunanRepository.deleteByIdLoan(id_loan);
				for (int i = 0; i < agunan.length(); i++) {
					JSONObject aArray = agunan.getJSONObject(i);
					DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LOGGER.info("!! GET DETAIL AGUNAN !! " + "@ " + i + " \n");
					String is_sk = aArray.get("is_sk").toString().equals("") ? null : aArray.get("is_sk").toString();
					String is_pokok = aArray.get("is_pokok").toString().equals("") ? null : aArray.get("is_pokok").toString();
					String jenis = aArray.get("jenis").toString().equals("") ? null : aArray.get("jenis").toString();
					String alamat_agunan = aArray.get("alamat").toString().equals("") ? null : aArray.get("alamat").toString();
					Integer provinsi = aArray.get("provinsi").toString().equals("") ? null : Integer.parseInt(aArray.get("provinsi").toString()); 
					Integer dati2 = aArray.get("dati2").toString().equals("") ? null : Integer.parseInt(aArray.get("dati2").toString()); 
					Integer kec = aArray.get("kec").toString().equals("") ? null : Integer.parseInt(aArray.get("kec").toString()); 
					BigDecimal kel = aArray.get("kel").toString().equals("") ? null : new BigDecimal(aArray.get("kel").toString()); 
					Double luas_tanah = aArray.get("luas_tanah").toString().equals("") ? null : Double.parseDouble(aArray.get("luas_tanah").toString());
					Double luas_bangunan = aArray.get("luas_bangunan").toString().equals("") ? null : Double.parseDouble(aArray.get("luas_bangunan").toString());
					String bukti = aArray.get("bukti").toString().equals("") ? null : aArray.get("bukti").toString();
					String no_bukti = aArray.get("no_bukti").toString().equals("") ? null : aArray.get("no_bukti").toString();
					Double harga_pasar = aArray.get("harga_pasar").toString().equals("") ? null : Double.parseDouble(aArray.get("harga_pasar").toString());
					Double nilai_taksasi = aArray.get("nilai_taksasi").toString().equals("") ? null : Double.parseDouble(aArray.get("nilai_taksasi").toString());
					Double persentase_taksasi = aArray.get("persentase_taksasi").toString().equals("") ? null : Double.parseDouble(aArray.get("persentase_taksasi").toString());
					String kepemilikan = aArray.get("kepemilikan").toString().equals("") ? null : aArray.get("kepemilikan").toString();
					LocalDate tgl_penilaian_terakhir = aArray.get("tgl_penilaian_terakhir").toString().equals("") ? null : LocalDate.parse(aArray.getString("tgl_penilaian_terakhir"), formatter);
					LocalDate tgl_expired_agunan = aArray.get("tgl_expired_agunan").toString().equals("") ? null : LocalDate.parse(aArray.getString("tgl_expired_agunan"), formatter);
					String deskripsi_agunan = aArray.get("deskripsi_agunan").toString().equals("") ? null : aArray.get("deskripsi_agunan").toString();
					String prioritas_agunan = aArray.get("prioritas_agunan").toString().equals("") ? null : aArray.get("prioritas_agunan").toString();
					String izin_pengkaitan = aArray.get("izin_pengkaitan").toString().equals("") ? null : aArray.get("izin_pengkaitan").toString();
					String cakupan_kewajiban = aArray.get("cakupan_kewajiban").toString().equals("") ? null : aArray.get("cakupan_kewajiban").toString();
					String status_agunan = aArray.get("status_agunan").toString().equals("") ? null : aArray.get("status_agunan").toString();
					String status_akutansi_agunan = aArray.get("status_akutansi_agunan").toString().equals("") ? null : aArray.get("status_akutansi_agunan").toString();
					String bank_atau_nasabah = aArray.get("bank_atau_nasabah").toString().equals("") ? null : aArray.get("bank_atau_nasabah").toString();
					String kegunaan_agunan = aArray.get("kegunaan_agunan").toString().equals("") ? null : aArray.get("kegunaan_agunan").toString();
					String is_asuransi = aArray.get("is_asuransi").toString().equals("") ? null : aArray.get("is_asuransi").toString();
					String kode_asuransi1 = aArray.get("kode_asuransi1").toString().equals("") ? null : aArray.get("kode_asuransi1").toString();
					LocalDate tgl_awal_asuransi1 = aArray.get("tgl_awal_asuransi1").toString().equals("") ? null : LocalDate.parse(aArray.getString("tgl_awal_asuransi1"), formatter);
					LocalDate tgl_akhir_asuransi1 = aArray.get("tgl_akhir_asuransi1").toString().equals("") ? null : LocalDate.parse(aArray.getString("tgl_akhir_asuransi1"), formatter);
					Double asuransi_amount1 = aArray.get("asuransi_amount1").toString().equals("") ? null : Double.parseDouble(aArray.get("asuransi_amount1").toString());
					String jenis_agunan_bi = aArray.get("jenis_agunan_bi").toString().equals("") ? null : aArray.get("jenis_agunan_bi").toString();
					String penilaian_oleh = aArray.get("penilaian_oleh").toString().equals("") ? null : aArray.get("penilaian_oleh").toString();
					String telp_penilai = aArray.get("telp_penilai").toString().equals("") ? null : aArray.get("telp_penilai").toString();
					String perusahaan_penilai = aArray.get("perusahaan_penilai").toString().equals("") ? null : aArray.get("perusahaan_penilai").toString();
					String alamat1 = aArray.get("alamat1").toString().equals("") ? null : aArray.get("alamat1").toString();
					String nama_pengacara = aArray.get("nama_pengacara").toString().equals("") ? null : aArray.get("nama_pengacara").toString();
					Double limit_agunan_bi = aArray.get("limit_agunan_bi").toString().equals("") ? null : Double.parseDouble(aArray.get("limit_agunan_bi").toString());
					String workstation = aArray.get("workstation").toString().equals("") ? null : aArray.get("workstation").toString();
					LocalDate date_last_maintenance = aArray.get("date_last_maintenance").toString().equals("") ? null : LocalDate.parse(aArray.getString("date_last_maintenance"), formatter);
					Double liquidation_amount = aArray.get("liquidation_amount").toString().equals("") ? null : Double.parseDouble(aArray.get("liquidation_amount").toString());
					Double utilized_amount = aArray.get("utilized_amount").toString().equals("") ? null : Double.parseDouble(aArray.get("utilized_amount").toString());
					LocalDate tgl_apht = aArray.get("tgl_apht").toString().equals("") ? null : LocalDate.parse(aArray.getString("tgl_apht"), formatter);
					Double apht_amount = aArray.get("apht_amount").toString().equals("") ? null : Double.parseDouble(aArray.get("apht_amount").toString());
					String kode_kanal = aArray.get("kode_kanal").toString().equals("") ? null : aArray.get("kode_kanal").toString();
					String kode_tipe_dokumen = aArray.get("kode_tipe_dokumen").toString().equals("") ? null : aArray.get("kode_tipe_dokumen").toString();
					String nomor_dokumen = aArray.get("nomor_dokumen").toString().equals("") ? null : aArray.get("nomor_dokumen").toString();
					String jenis_pengikatan = aArray.get("jenis_pengikatan").toString().equals("") ? null : aArray.get("jenis_pengikatan").toString();
					Double biaya_pengikatan = aArray.get("biaya_pengikatan").toString().equals("") ? null : Double.parseDouble(aArray.get("biaya_pengikatan").toString());
					String kode_pengikatan_internal = aArray.get("kode_pengikatan_internal").toString().equals("") ? null : aArray.get("kode_pengikatan_internal").toString();
					String kode_pengikatan_notarial = aArray.get("kode_pengikatan_notarial").toString().equals("") ? null : aArray.get("kode_pengikatan_notarial").toString();
					LocalDate tgl_pengikatan_internal = aArray.get("tgl_pengikatan_internal").toString().equals("") ? null : LocalDate.parse(aArray.getString("tgl_pengikatan_internal"), formatter);
					LocalDate tgl_pengikatan_notarial = aArray.get("tgl_pengikatan_notarial").toString().equals("") ? null : LocalDate.parse(aArray.getString("tgl_pengikatan_notarial"), formatter);
					String nomor_pengikatan_internal = aArray.get("nomor_pengikatan_internal").toString().equals("") ? null : aArray.get("nomor_pengikatan_internal").toString();
					String nomor_pengikatan_notarial = aArray.get("nomor_pengikatan_notarial").toString().equals("") ? null : aArray.get("nomor_pengikatan_notarial").toString();
					Double jumlah_agunan = aArray.get("jumlah_agunan").toString().equals("") ? null : Double.parseDouble(aArray.get("jumlah_agunan").toString());
					LocalDate agreement_date = aArray.get("agreement_date").toString().equals("") ? null : LocalDate.parse(aArray.getString("agreement_date"), formatter);
					String jenis_agunan_ppap = aArray.get("jenis_agunan_ppap").toString().equals("") ? null : aArray.get("jenis_agunan_ppap").toString();
					String basel2_jenis_agunan = aArray.get("basel2_jenis_agunan").toString().equals("") ? null : aArray.get("basel2_jenis_agunan").toString();
					String sifat_agunan = aArray.get("sifat_agunan").toString().equals("") ? null : aArray.get("sifat_agunan").toString();
					String penerbit_agunan = aArray.get("penerbit_agunan").toString().equals("") ? null : aArray.get("penerbit_agunan").toString();
					String cash_non_cash = aArray.get("cash_non_cash").toString().equals("") ? null : aArray.get("cash_non_cash").toString();
					String user_entry = aArray.get("user_entry").toString().equals("") ? null : aArray.get("user_entry").toString();
					String status_kepemilikan = aArray.getString("status_kepemilikan").equals("") ? null : aArray.getString("status_kepemilikan");
					String pekerjaan_pemilik = aArray.getString("pekerjaan_pemilik").equals("") ? null : aArray.getString("pekerjaan_pemilik");
					String ktp_pemilik = aArray.getString("ktp_pemilik").equals("") ? null : aArray.getString("ktp_pemilik");
					String alamat_pemilik = aArray.getString("alamat_pemilik").equals("") ? null : aArray.getString("alamat_pemilik");
					String append_desc1 = aArray.getString("append_desc1").equals("") ? null : aArray.getString("append_desc1");
					String append_desc2 = aArray.getString("append_desc2").equals("") ? null : aArray.getString("append_desc2");
					String append_desc3 = aArray.getString("append_desc3").equals("") ? null : aArray.getString("append_desc3");
					String append_desc4 = aArray.getString("append_desc4").equals("") ? null : aArray.getString("append_desc4");
					String append_desc5 = aArray.getString("append_desc5").equals("") ? null : aArray.getString("append_desc5");
					M_data_agunan agun = new M_data_agunan(id_loan, is_sk, is_pokok, jenis, alamat_agunan, provinsi, dati2, kec, kel, luas_tanah, 
							luas_bangunan, bukti, no_bukti, harga_pasar, nilai_taksasi, persentase_taksasi, kepemilikan, tgl_penilaian_terakhir, 
							tgl_expired_agunan, deskripsi_agunan, prioritas_agunan, izin_pengkaitan, cakupan_kewajiban, status_agunan, 
							status_akutansi_agunan, bank_atau_nasabah, kegunaan_agunan, is_asuransi, kode_asuransi1, tgl_awal_asuransi1, 
							tgl_akhir_asuransi1, asuransi_amount1, jenis_agunan_bi, penilaian_oleh, telp_penilai, perusahaan_penilai, alamat1, 
							nama_pengacara, limit_agunan_bi, workstation, date_last_maintenance, liquidation_amount, utilized_amount, tgl_apht, 
							apht_amount, kode_kanal, kode_tipe_dokumen, nomor_dokumen, jenis_pengikatan, biaya_pengikatan, kode_pengikatan_internal, 
							kode_pengikatan_notarial, tgl_pengikatan_internal, tgl_pengikatan_notarial, nomor_pengikatan_internal, 
							nomor_pengikatan_notarial, jumlah_agunan, agreement_date, jenis_agunan_ppap, basel2_jenis_agunan, sifat_agunan, 
							penerbit_agunan, cash_non_cash, user_entry, status_kepemilikan, pekerjaan_pemilik, ktp_pemilik, alamat_pemilik,
							append_desc1, append_desc2, append_desc3, append_desc4, append_desc5);
					
					m_data_agunanRepository.save(agun);
					
					String taksasi= aArray.getString("m_append_taksasi").equals("") ? "" : aArray.getString("m_append_taksasi");
					
					String plot= aArray.getString("m_append_plotting").equals("") ? "" : aArray.getString("m_append_plotting");
					if(!plot.equals("")) {
						JSONArray appendplot = new JSONArray(plot);
						if (appendplot.length() > 0) {
						for (int x = 0; x < appendplot.length(); x++) {
							
						JSONObject appendplotting = appendplot.getJSONObject(x);
						M_append_plotting appplotting = new M_append_plotting();
						appplotting.setId_agunan(agun.getUrut());
						appplotting.setAppend_plotting1(appendplotting.getString("append_plotting1").equals("") ? null : appendplotting.getString("append_plotting1"));
						appplotting.setAppend_plotting2(appendplotting.getString("append_plotting2").equals("") ? null : LocalDate.parse(appendplotting.getString("append_plotting2"), formatter2));
						appplotting.setAppend_plotting3(appendplotting.getString("append_plotting3").equals("") ? null : appendplotting.getString("append_plotting3"));
						appplotting.setAppend_plotting4(appendplotting.getString("append_plotting4").equals("") ? null : appendplotting.getString("append_plotting4"));
						appplotting.setAppend_plotting5(appendplotting.getString("append_plotting5").equals("") ? null : appendplotting.getString("append_plotting5"));
						appplotting.setAppend_plotting6(appendplotting.getString("append_plotting6").equals("") ? null : appendplotting.getString("append_plotting6"));
						appplotting.setAppend_plotting7(appendplotting.getString("append_plotting7").equals("") ? null : appendplotting.getString("append_plotting7"));
						appplotting.setAppend_plotting8(appendplotting.getString("append_plotting8").equals("") ? null : appendplotting.getString("append_plotting8"));
						appplotting.setAppend_plotting9(appendplotting.getString("append_plotting9").equals("") ? null : appendplotting.getString("append_plotting9"));
						appplotting.setAppend_plotting10(appendplotting.getString("append_plotting10").equals("") ? null : appendplotting.getString("append_plotting10"));
						appplotting.setAppend_plotting11(appendplotting.getString("append_plotting11").equals("") ? null : appendplotting.getString("append_plotting11"));
						appplotting.setAppend_plotting12(appendplotting.getString("append_plotting12").equals("") ? null : appendplotting.getString("append_plotting12"));
						appplotting.setAppend_plotting13(appendplotting.getString("append_plotting13").equals("") ? null : appendplotting.getString("append_plotting13"));
						appplotting.setAppend_plotting14(appendplotting.getString("append_plotting14").equals("") ? null : appendplotting.getString("append_plotting14"));
						m_append_plottingRepository.save(appplotting);
						}
						}
					}
					
					if (!taksasi.equals("")) {
						JSONArray appendtaksasi = new JSONArray(taksasi);
						if (appendtaksasi.length() > 0) {
							for (int x = 0; x < appendtaksasi.length(); x++) {
								JSONObject taks = appendtaksasi.getJSONObject(x);
								M_append_taksasi appptaksasi = new M_append_taksasi();
								appptaksasi.setId_agunan(agun.getUrut());
								appptaksasi.setAppend_taksasi1(taks.getString("append_taksasi1").equals("") ? null : taks.getString("append_taksasi1"));
								appptaksasi.setAppend_taksasi2(taks.getString("append_taksasi2").equals("") ? null : Float.valueOf(taks.getString("append_taksasi2")));
								appptaksasi.setAppend_taksasi3(taks.getString("append_taksasi3").equals("") ? null : taks.getString("append_taksasi3"));
								appptaksasi.setAppend_taksasi4(taks.getString("append_taksasi4").equals("") ? null : Float.valueOf(taks.getString("append_taksasi4")));
								appptaksasi.setAppend_taksasi5(taks.getString("append_taksasi5").equals("") ? null : taks.getString("append_taksasi5"));
								appptaksasi.setAppend_taksasi6(taks.getString("append_taksasi6").equals("") ? null : Float.valueOf(taks.getString("append_taksasi6")));
								appptaksasi.setAppend_taksasi7(taks.getString("append_taksasi7").equals("") ? null : Float.valueOf(taks.getString("append_taksasi7")));
								appptaksasi.setAppend_taksasi8(taks.getString("append_taksasi8").equals("") ? null : Float.valueOf(taks.getString("append_taksasi8")));
								appptaksasi.setAppend_taksasi9(taks.getString("append_taksasi9").equals("") ? null : Float.valueOf(taks.getString("append_taksasi9")));
								appptaksasi.setAppend_taksasi10(taks.getString("append_taksasi10").equals("") ? null : taks.getString("append_taksasi10"));
								appptaksasi.setAppend_taksasi11(taks.getString("append_taksasi11").equals("") ? null : Float.valueOf(taks.getString("append_taksasi11")));
								appptaksasi.setAppend_taksasi12(taks.getString("append_taksasi12").equals("") ? null : Float.valueOf(taks.getString("append_taksasi12")));
								appptaksasi.setAppend_taksasi13(taks.getString("append_taksasi13").equals("") ? null : Float.valueOf(taks.getString("append_taksasi13")));
								appptaksasi.setAppend_taksasi14(taks.getString("append_taksasi14").equals("") ? null : LocalDate.parse(taks.getString("append_taksasi14")));
								appptaksasi.setAppend_taksasi15(taks.getString("append_taksasi15").equals("") ? null : Float.valueOf(taks.getString("append_taksasi15")));
								appptaksasi.setAppend_taksasi16(taks.getString("append_taksasi16").equals("") ? null : taks.getString("append_taksasi16"));
								appptaksasi.setAppend_taksasi17(taks.getString("append_taksasi17").equals("") ? null : taks.getString("append_taksasi17"));
								appptaksasi.setAppend_taksasi18(taks.getString("append_taksasi18").equals("") ? null : taks.getString("append_taksasi18"));
								appptaksasi.setAppend_taksasi19(taks.getString("append_taksasi19").equals("") ? null : taks.getString("append_taksasi19"));
								appptaksasi.setAppend_taksasi20(taks.getString("append_taksasi20").equals("") ? null : taks.getString("append_taksasi20"));
								appptaksasi.setAppend_taksasi21(taks.getString("append_taksasi21").equals("") ? null : taks.getString("append_taksasi21"));
								appptaksasi.setAppend_taksasi22(taks.getString("append_taksasi22").equals("") ? null : taks.getString("append_taksasi22"));
								appptaksasi.setAppend_taksasi23(taks.getString("append_taksasi23").equals("") ? null : taks.getString("append_taksasi23"));
								appptaksasi.setAppend_taksasi24(taks.getString("append_taksasi24").equals("") ? null : taks.getString("append_taksasi24"));
								appptaksasi.setAppend_taksasi25(taks.getString("append_taksasi25").equals("") ? null : taks.getString("append_taksasi25"));
								appptaksasi.setAppend_taksasi26(taks.getString("append_taksasi26").equals("") ? null : taks.getString("append_taksasi26"));
								m_append_taksasiRepository.save(appptaksasi);
							}
						}
						}
					
				}
			}
			
			System.out.println("##### SIMPAN DETAIL NASABAH ##### ");
			response.put("kode", "00");
			response.put("pesan", "DATA BERHASIL SIMPAN");

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/updatereview ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/mkm/pencairan/approvecekdokumen")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> approvecekdokumen(@Valid @RequestBody String param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> response = new HashMap<>();

		try {
			JSONObject jsonObject = new JSONObject(param.toString());
			String json = jsonObject.toString();
			LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/approvecekdokumen ************** " + "@ " + json
					+ " \n");
			String id_loan = jsonObject.getString("id_loan");
			JSONArray ceklis_admin = new JSONArray(jsonObject.getString("ceklis_admin"));
			Integer total = 0;
			if (ceklis_admin.length() >= 1) {
				for (int i = 0; i < ceklis_admin.length(); i++) {
					JSONObject aArray = ceklis_admin.getJSONObject(i);
					LOGGER.info("!! GET DETAIL CEKLIST ADMIN !! " + "@ " + i + " \n");
					M_data_lampiran_file har = m_data_lampiran_fileRepository.findByIdSelect(Integer.valueOf(aArray.getString("id")));
					har.setCeklis_admin(Integer.valueOf(aArray.getString("status")));
					m_data_lampiran_fileRepository.save(har);

					Integer jlh = Integer.valueOf(aArray.getString("status"));
					total = total + jlh;
				}
			}
			
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			loan.setStatus("23");
			m_data_loanRepository.save(loan);
			System.out.println(total);
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/approvecekdokumen ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/mkm/pencairan/generateakad")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setnoakad(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/generateakad ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		try {
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				String produk = loan.getId_produk().toString();
				String sub_produk = loan.getId_sub_produk().toString();
				String sektor_usaha = loan.getM_data_bi().get(0).getKode_akad();
				DateTimeFormatter formatteryear = DateTimeFormatter.ofPattern("yyyy");
				LocalDate year = LocalDate.now();
				
				String jenis_penggunaan = "3";
				if (produk.equals("1")) {
					jenis_penggunaan = "3"; //1=MODAL KERJA 2=INVESTASI 3=KONSUMTIF
				} else if (produk.equals("2")) {
					if (sub_produk.equals("50")||sub_produk.equals("52")||sub_produk.equals("54")) {
						jenis_penggunaan = "1";
					} else if (sub_produk.equals("72")||sub_produk.equals("73")||sub_produk.equals("74")) {
						jenis_penggunaan = "2";
					} else {
						jenis_penggunaan = "3";
					}
						
				}
				
				
				if (loan.getNomor_akad() == null) {
					
					String lastAkad = m_data_loanRepository.getMaxNoAkad(loan.getId_cab(), year.format(formatteryear).toString());
					Par_cabang inisial = par_cabangRepository.findByIdCab(loan.getId_cab());
					Optional<Par_plan> id_akad = par_planRepository.findById(loan.getId_plan());
					Integer maxAkad = 1;
					if (lastAkad != null) {
						maxAkad = Integer.parseInt(lastAkad) + 1;
					}
					String noReg = String.format("%7s", maxAkad).replace(' ', '0');
					
					String no_akad = noReg + "." + jenis_penggunaan + "." + sektor_usaha + "."
							+ year.format(formatteryear)+ "." +loan.getId_cab();
					
	//				String no_akad = noReg + "/" + inisial.getInisial() + "/" + id_akad.get().getInisial_akad() + "/"
	//						+ year.format(formatteryear);

					if (Integer.parseInt(loan.getStatus()) >= 19) {
						System.out.println("##### DATA LOAN ##### ");
						loan.setNomor_akad(no_akad);
						loan.setAkad_by(user_id);
	//					loan.setAkad_date(LocalDateTime.now());
						loan.setStatus("24");
						m_data_loanRepository.save(loan);
	
						response.put("kode", "00");
						response.put("pesan", "GENERATE BERHASIL");
						response.put("nomor_akad", no_akad);
					} else if (Integer.parseInt(loan.getStatus()) < 19) {
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
					response.put("kode", "00");
					response.put("pesan", "GENERATE SUDAH BERHASIL");
					response.put("nomor_akad", no_akad);
					loan.setNomor_akad(no_akad);
					loan.setAkad_by(user_id);
					loan.setStatus("24");
					m_data_loanRepository.save(loan);
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/generateakad ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/mkm/pencairan/settanggalakad")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> settanggalakad(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/settanggalakad ************** " + "@ "
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
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			if (loan != null) {

//				LocalDateTime tgl_akad = LocalDateTime.now();
//				LocalDateTime tgl_akad = LocalDateTime.parse(now.format(formatter));
			
					System.out.println("##### DATA LOAN ##### ");
					loan.setAkad_by(user_id);
					loan.setAkad_date(tgl_akad);
					loan.setAkad_nama(user_nama);
					loan.setBawa_pasangan(bawa_pasangan);
					m_data_loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "GENERATE TANGGAL BERHASIL");
					response.put("tanggal_akad", tgl_akad);
				
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/settanggalakad ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/mkm/pencairan/setrekening")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setrekening(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/setrekening ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String norek_pencairan = param.get("norek_pencairan");
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
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan); 
			if (loan != null) {
			 
					System.out.println("##### DATA LOAN ##### ");
					loan.setStatus("27");
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
						if (kode_rekening_nasabah.equals("")||kode_rekening_nasabah.equals(" ") || kode_rekening_nasabah.equals(null)) {
							response.put("kode", "10");
							response.put("pesan", "Kode Rekening Nasabah Tidak boleh Kosong");
							return new ResponseEntity<>(response, HttpStatus.OK);
						}
					}
					
					
					m_data_loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "SET REKENING BERHASIL");
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/setrekening ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/mkm/pencairan/prosescair")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setProses1(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/prosescair ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		ResponseMessage send = null;
		ResponseMessage send_agunan = null;
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String cab = param.get("cab");

		try {
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
					send = posting(id_loan, user_id);
					String vlk = send.getKode();
					if (vlk.equals("00")) {
						System.out.println("##### DATA LOAN ##### ");
						loan.setStatus("29");
						loan.setApprove_cair1_by(user_id);
						loan.setApprove_cair1_date(LocalDateTime.now());
						loan.setApprove_cair1_name(user_nama);
						loan.setStatus_vlink("00");
						m_data_loanRepository.save(loan);
						
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
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_loan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			LOGGER.info("\n ************** RESPONSE TO FRONTEND(Exception) /mkm/pencairan/prosescair ************** @ "+ e +  " \n");
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/prosescair ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("/mkm/agunan/repost")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> repost(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/agunan/repost ************** " + "@ "
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
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);

			if (loan != null) {
				M_data_debitur debitur = m_data_debiturRepository.findByKtpDebitur(loan.getId_debitur());
				M_data_agunan agunan = m_data_agunanRepository.findByUrut(no_urut);
				
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
				String dati2 =  String.format("%-6s", (agunan.getBi_dati2()==null) ? "" : agunan.getBi_dati2());
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
	            	m_data_agunanRepository.save(agunan);
				
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/agunan/repost ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/mkm/pencairan/setbiayaagunanbyid")
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
			LOGGER.info("\n ************** REQUEST FROM FRONTEND "+request.getRemoteAddr()+" /mkm/pencairan/setbiayaagunanbyid ************** " + "@ " + json + " \n");
			
			String id_loan = jsonObject.get("id_loan").toString().equals("") ? null : jsonObject.get("id_loan").toString(); 
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			
			JSONArray agunan = new JSONArray(jsonObject.getString("agunan"));
			
			
			if (agunan.length() >= 1) {
				for (int i = 0; i < agunan.length(); i++) {
					JSONObject aArray = agunan.getJSONObject(i);
					LOGGER.info("!! SET BIAYA AGUNAN !! " + "@ " + i + " \n");
					Integer urut = aArray.get("urut").toString().equals("") ? 0 : Integer.parseInt(aArray.get("urut").toString());
					M_data_agunan agun = m_data_agunanRepository.findByUrut(urut);
					
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
					m_data_agunanRepository.save(agun);
				}
			}

			loan.setStatus("25");
			m_data_loanRepository.save(loan);
			response.put("kode", "00");
			response.put("pesan", "DATA BERHASIL SIMPAN");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND "+request.getRemoteAddr()+" /mkm/pencairan/setbiayaagunanbyid ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/mkm/pencairan/setallbiaya")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setallbiaya(@Valid @RequestBody String param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();

		Map<String, Object> response = new HashMap<>();
		try {
			JSONObject jsonObject = new JSONObject(param.toString());
			String json = jsonObject.toString();
			LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/setallbiaya ************** " + "@ " + json
					+ " \n");

			String is_biaya_adm = jsonObject.getString("is_biaya_adm");
			String id_loan = jsonObject.getString("id_loan");
			Double biaya_materai = jsonObject.getString("biaya_materai").equals("") ? null : Double.parseDouble(jsonObject.getString("biaya_materai"));
			Double persentase_biaya_adm = jsonObject.getString("persentase_biaya_adm").equals("") ? null : Double.parseDouble(jsonObject.getString("persentase_biaya_adm"));
			Double biaya_adm = jsonObject.getString("biaya_adm").equals("") ? null : Double.parseDouble(jsonObject.getString("biaya_adm"));
			Double biaya_asuransi_jiwa = jsonObject.getString("biaya_asuransi_jiwa").equals("") ? null : Double.parseDouble(jsonObject.getString("biaya_asuransi_jiwa"));
			Double biaya_asuransi_kebakaran = jsonObject.getString("biaya_asuransi_kebakaran").equals("") ? null : Double.parseDouble(jsonObject.getString("biaya_asuransi_kebakaran"));
			Double biaya_asuransi_pembiayaan = jsonObject.getString("biaya_asuransi_pembiayaan").equals("") ? null : Double.parseDouble(jsonObject.getString("biaya_asuransi_pembiayaan"));
			
			M_data_loan dataloan = m_data_loanRepository.findByIdLoan(id_loan);
			dataloan.setIs_biaya_adm(is_biaya_adm);
			dataloan.setBiaya_materai(biaya_materai);
			dataloan.setPersentase_biaya_adm(persentase_biaya_adm);
			dataloan.setBiaya_adm(biaya_adm);
			dataloan.setBiaya_asuransi_jiwa(biaya_asuransi_jiwa);
			dataloan.setBiaya_asuransi_kebakaran(biaya_asuransi_kebakaran);
			dataloan.setBiaya_asuransi_pembiayaan(biaya_asuransi_pembiayaan);
			dataloan.setStatus("26");
			m_data_loanRepository.save(dataloan);
			
			response.put("kode", "00");
			response.put("pesan", "SET BIAYA BERHASIL");


		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/setallbiaya ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

	
	@PostMapping("/mkm/pencairan/submit")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> submit(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/submit ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		try {
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);

			if (loan != null) {
				
					System.out.println("##### DATA LOAN ##### ");
					loan.setStatus("28");
					loan.setSubmit2_by(user_id);
					loan.setSubmit2_nama(user_nama);
					loan.setSubmit2_date(LocalDateTime.now());
					m_data_loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "SUBMIT BERHASIL");
				
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/submit ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/mkm/pencairan/uploadarsip")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> uploadarsip(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/uploadarsip  ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String akad_file = param.get("akad_file");
		try {
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				
					System.out.println("##### DATA LOAN ##### ");
					loan.setAkad_file(akad_file);
					loan.setUpload_akad_by(user_id);
					loan.setUpload_akad_date(LocalDateTime.now());
					m_data_loanRepository.save(loan);

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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/uploadarsip ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping("/mkm/pencairan/reviewpinsi")
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
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				loan.setStatus("20");
				loan.setReview2_nama(user_nama);
				loan.setReview2_by(user_id);
				loan.setReview2_date(LocalDateTime.now());
				loan.setReview2_desc(keterangan);
				m_data_loanRepository.save(loan);
				
				m_data_lampiran_fileRepository.updateceklistByIdLoan(id_loan);
				
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/reviewpinsi ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/mkm/pencairan/manual")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setProsesManual(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/manual ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		ResponseMessage send = null;
		ResponseMessage send_agunan = null;
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String cab = param.get("cab");

		try {
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
//					send = posting(id_loan, user_id);
//					String vlk = send.getKode();
//					if (vlk.equals("00")) {
						System.out.println("##### DATA LOAN ##### ");
						loan.setStatus("30");
						loan.setApprove_cair1_by(user_id);
						loan.setApprove_cair1_date(LocalDateTime.now());
						loan.setApprove_cair1_name(user_nama);
						loan.setStatus_vlink("00");
						m_data_loanRepository.save(loan);
						
//						send_agunan = posting_agunan(id_loan, user_id);
//						String vlk_agunan = send_agunan.getKode();
//						
//						if (vlk_agunan.equals("00")) {
//							response.put("kode", "00");
//							response.put("pesan", "APPROVE BERHASIL");
//			        	} else {
//			        		response.put("kode", "00");
//							response.put("pesan", "APPROVE PENCAIRAN BERHASIL, GAGAL POSTING AGUNAN SILAHKAN CEK DAN POSTING ULANG AGUNAN YANG BELUM DIPOSTING");
//			        	}
						
//					} else {
//						response.put("kode", vlk);
//						response.put("pesan", send.getPesan());
//					}
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/manual ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/mkm/pencairan/pembatalan")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> pembatalan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/pencairan/pembatalan ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String keterangan = param.get("keterangan");

		try {
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				loan.setStatus("31");
				loan.setReview2_nama(user_nama);
				loan.setReview2_by(user_id);
				loan.setReview2_date(LocalDateTime.now());
				loan.setReview2_desc(keterangan);
				m_data_loanRepository.save(loan);

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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/pencairan/pembatalan ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
//=======================================================================================================================================

//    OOOOOOOOO     RRRRRRRRRRRRRRRRR   IIIIIIIIII
//  OO:::::::::OO   R::::::::::::::::R  I::::::::I
//OO:::::::::::::OO R::::::RRRRRR:::::R I::::::::I
//O:::::::OOO:::::::ORR:::::R     R:::::RII::::::II
//O::::::O   O::::::O  R::::R     R:::::R  I::::I  
//O:::::O     O:::::O  R::::R     R:::::R  I::::I  
//O:::::O     O:::::O  R::::RRRRRR:::::R   I::::I  
//O:::::O     O:::::O  R:::::::::::::RR    I::::I  
//O:::::O     O:::::O  R::::RRRRRR:::::R   I::::I  
//O:::::O     O:::::O  R::::R     R:::::R  I::::I  
//O:::::O     O:::::O  R::::R     R:::::R  I::::I  
//O::::::O   O::::::O  R::::R     R:::::R  I::::I  
//O:::::::OOO:::::::ORR:::::R     R:::::RII::::::II
//OO:::::::::::::OO R::::::R     R:::::RI::::::::I
//  OO:::::::::OO   R::::::R     R:::::RI::::::::I
//    OOOOOOOOO     RRRRRRRR     RRRRRRRIIIIIIIIII

//=======================================================================================================================================================
	

	@PostMapping("/mkm/pencairan/detaildebitur/generatetglakad")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> settglakad(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND GENERATE TGL AKAD ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_loan = param.get("id_loan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String bawa_pasangan = param.get("bawa_pasangan");
		try {
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				LocalDate now = LocalDate.now();
				String tgl_akad = now.format(formatter);

				if (Integer.parseInt(loan.getStatus()) >= 13) {
					System.out.println("##### DATA LOAN ##### ");
					loan.setAkad_by(user_id);
					loan.setBawa_pasangan(bawa_pasangan);
					loan.setAkad_date(LocalDateTime.now());
					m_data_loanRepository.save(loan);

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

	@PostMapping("/mkm/pencairan/akad/submit")
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
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);

			if (loan != null) {
				if (Integer.parseInt(loan.getStatus()) == 15) {
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
//						JsonObject data = result.get("Result").getAsJsonObject();
						System.out.println("PESAN :" + result.get("pesan"));

						if (result.get("kode").getAsString().equals("00")) {
							System.out.println("##### DATA LOAN ##### ");
							loan.setStatus("16");
							loan.setSubmit2_by(user_id);
//							loan.setSubmit_nama(user_nama);
							loan.setSubmit2_date(LocalDateTime.now());
							m_data_loanRepository.save(loan);

							response.put("kode", "00");
							response.put("pesan", "SUBMIT BERHASIL");
						} else {
							response.put("kode", result.get("kode").getAsString());
							response.put("pesan", result.get("pesan").getAsString());
						}

					}

				} else if (Integer.parseInt(loan.getStatus()) < 15) {
					response.put("kode", "14");
					response.put("pesan", "Oops.. Debitur belum disetujui !!!");
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND SUBMIT AKAD ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	


	

	@PostMapping("/mkm/pencairan/cair/approve2")
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
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
//			Optional<Par_cabang> datacab = par_cabangRepository.findById(cab);
			String sts = "19";

			if (loan != null) {
				if (Integer.parseInt(loan.getStatus()) == 18) {
					System.out.println("##### DATA LOAN ##### ");
					loan.setStatus(sts);
					loan.setApprove_cair2_by(user_id);
					loan.setApprove_cair2_name(user_nama);
					loan.setApprove_cair2_date(LocalDateTime.now());
					m_data_loanRepository.save(loan);

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

	@PostMapping("/mkm/pencairan/cair/approve3")
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
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				if (Integer.parseInt(loan.getStatus()) == 19) {
					System.out.println("##### DATA LOAN ##### ");
					loan.setStatus("20");
					loan.setApprove_cair3_by(user_id);
					loan.setApprove_cair3_name(user_nama);
					loan.setApprove_cair3_date(LocalDateTime.now());
					m_data_loanRepository.save(loan);

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

	@PostMapping("/mkm/pencairan/ceklist/save")
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
					M_data_lampiran_file har = m_data_lampiran_fileRepository.findByIdSelect(Integer.valueOf(aArray.getString("id")));
					har.setCeklis_admin(Integer.valueOf(aArray.getString("status")));
					m_data_lampiran_fileRepository.save(har);

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

	

	@PostMapping("/mkm/pencairan/cair/proses2")
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
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);

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
						m_data_loanRepository.save(loan);
	
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
			
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND PROSES PENCAIRAN 2 ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/mkm/pencairan/cair/proses3")
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
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			if (loan != null) {
				if (Integer.parseInt(loan.getStatus()) == 19) {
					send = posting(id_loan, user_id);
					String vlk = send.getKode();
					if (vlk.equals("00")) {
						System.out.println("##### DATA LOAN ##### ");
						loan.setStatus("20");
						loan.setApprove_cair3_by(user_id);
						loan.setApprove_cair3_name(user_nama);
						loan.setApprove_cair3_date(LocalDateTime.now());
						loan.setStatus_vlink("00");
						m_data_loanRepository.save(loan);
	
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND PROSES PENCAIRAN 3 ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	

	public String getToken() {
		String token_value = "";
//		String client_id = client_id;
//		String client_secret = client_secret;
//		String username = username;
//		String password = password;
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


	@PostMapping("/mkm/pencairan/loan")
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
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
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
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			M_data_penghasilan pekerjaan = m_data_penghasilanRepository.findByIdPenghasilan(loan.getId_penghasilan());
			M_data_debitur debitur = m_data_debiturRepository.findByKtpDebitur(loan.getId_debitur());
			M_data_bi bi = m_data_biRepository.findByIdLoan(id_loan);
			List<M_data_agunan> agunan = m_data_agunanRepository.findByIdLoan(id_loan);
				
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
					String kode_aset_ijarah = String.format("%-20s", "");
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
//					String tgl_awal_pembiayaan = "20230306";

					Calendar cal1 = Calendar.getInstance();
					Date date2 = cal1.getTime();
					cal1.setTime(date2);
					cal1.add(Calendar.MONTH, loan.getTenor_disetujui());
					date2 = cal1.getTime();
					String tgl_akhir_pembiayaan = dateFormat.format(date2);
//					String tgl_akhir_pembiayaan = "20270714";

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
//					String payment_due_date = "20230306";
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
					
//					Double urbun = loan.getUang_muka()==null ? Double.valueOf("0") : loan.getUang_muka();
					Double urbun = Double.valueOf("0");
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
					if (kode_aplikasi_wakalah.equals(" ") || kode_aplikasi_wakalah.equals(null) || kode_aplikasi_wakalah.equals("")) {
						res.setKode("10");
						res.setPesan("KODE REKENING PENCAIRAN TIDAK BOLEH KOSONG");
						return res;
					}
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
					String rek_proceed = "";
					if (kode_rek_proceed.equals("2")) {
						rek_proceed = String.format("%11s", (loan.getNorek_proceed()==null) ? " " :  loan.getNorek_proceed()).replace(' ', '0');
					} else if (kode_rek_proceed.equals("3")) {
						rek_proceed = String.format("%11s", (nomorrekeningload==null) ? " " :  nomorrekeningload).replace(' ', '0');
					}

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
					String nominal_penghasilan = String.format("%15s", pekerjaan.getNetto()==null ? "" : pekerjaan.getNetto().longValue()).replace(' ', '0')+"00";
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
			M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
			M_data_debitur debitur = m_data_debiturRepository.findByKtpDebitur(loan.getId_debitur());
			List<M_data_agunan> agunan = m_data_agunanRepository.findByIdLoan(id_loan);

					
        	for (int i = 0; i < agunan.size(); i++) {
        		M_data_agunan RAgunan = m_data_agunanRepository.findByUrut(agunan.get(i).getUrut());
        		String utama = (agunan.get(i).getIs_pokok()==null) ? "0" : agunan.get(i).getIs_pokok();
        		if (utama.equals("2")) {
		            RAgunan.setStatus_vlink("0");
		            m_data_agunanRepository.save(RAgunan);
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
				
				String dati2 =  String.format("%-6s", (agunan.get(i).getBi_dati2()==null) ? "" : agunan.get(i).getBi_dati2());
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
            	m_data_agunanRepository.save(RAgunan);

        		}
			}
        	
        	List<M_data_agunan> agunan_sts = m_data_agunanRepository.findByIdLoanAndSts(id_loan, "00");
			
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

}
