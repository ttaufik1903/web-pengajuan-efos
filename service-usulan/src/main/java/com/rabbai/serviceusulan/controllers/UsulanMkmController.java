package com.rabbai.serviceusulan.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.rabbai.serviceusulan.models.Par_cabang;
import com.rabbai.serviceusulan.models.mkm.M_data_aspek_keuangan;
import com.rabbai.serviceusulan.models.mkm.M_data_loan;
import com.rabbai.serviceusulan.models.mkm.M_data_neraca;
import com.rabbai.serviceusulan.models.mkm.M_data_usulan;
import com.rabbai.serviceusulan.models.mkm.M_data_wallet;
import com.rabbai.serviceusulan.repository.Par_cabangRepository;
import com.rabbai.serviceusulan.repository.Par_ceklistRepository;
import com.rabbai.serviceusulan.repository.Par_rincian_scoringRepository;
import com.rabbai.serviceusulan.repository.mkm.MScoringLoanRepository;
import com.rabbai.serviceusulan.repository.mkm.M_data_agunanRepository;
import com.rabbai.serviceusulan.repository.mkm.M_data_aspek_keuanganRepository;
import com.rabbai.serviceusulan.repository.mkm.M_data_debiturRepository;
import com.rabbai.serviceusulan.repository.mkm.M_data_lampiran_fileRepository;
import com.rabbai.serviceusulan.repository.mkm.M_data_loanRepository;
import com.rabbai.serviceusulan.repository.mkm.M_data_neracaRepository;
import com.rabbai.serviceusulan.repository.mkm.M_data_pasanganRepository;
import com.rabbai.serviceusulan.repository.mkm.M_data_penghasilanRepository;
import com.rabbai.serviceusulan.repository.mkm.M_data_usulanRepository;
import com.rabbai.serviceusulan.repository.mkm.M_data_walletRepository;

@RestController
public class UsulanMkmController {

	@Autowired M_data_debiturRepository m_data_debiturRepository;
//	@Autowired UsersRepository usersRepository;
	@Autowired M_data_penghasilanRepository m_data_penghasilanRepository;
	@Autowired M_data_loanRepository m_data_loanRepository;
	@Autowired MScoringLoanRepository mscoringloanRepository;
//	@Autowired MailService notificationService;
//	@Autowired FilesStorageService storageService;
	@Autowired M_data_pasanganRepository m_data_pasanganRepository;
	@Autowired M_data_lampiran_fileRepository m_data_lampiran_fileRepository;
	@Autowired Par_ceklistRepository par_ceklistRepository;
	@Autowired Par_rincian_scoringRepository par_rincian_scoringRepository;
	@Autowired Par_cabangRepository par_cabangRepository;
	@Autowired M_data_usulanRepository m_data_usulanRepository;
	@Autowired M_data_neracaRepository m_data_neracaRepository;
	@Autowired M_data_walletRepository m_data_walletRepository;
	@Autowired M_data_aspek_keuanganRepository m_data_aspek_keuanganRepository;
	@Autowired M_data_agunanRepository m_data_agunanRepository;
	
	
//	@Autowired CoreBankingService cbsService;
	
	private static Logger LOGGER = Logger.getLogger(UsulanMkmController.class.getName());

	@PostMapping("/mkm/usulan/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listDebitur(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/usulan/list ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		String cab = param.get("cab");

		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));

		try {
			List<M_data_usulan> usul = new ArrayList<M_data_usulan>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<M_data_usulan> pageInsts;

			if (keyword != null) {
				pageInsts = m_data_usulanRepository.findKeywordDebiturWithPagination(paging, keyword, cab);
				filtered = m_data_usulanRepository.getCount(cab);
			} else {
				pageInsts = m_data_usulanRepository.findAllDebiturWithPagination(paging, cab);
				filtered = pageInsts.getNumberOfElements();
			}
			usul = pageInsts.getContent();

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("usulan", usul);
			response.put("currentPage", pageInsts.getNumber());
			response.put("totalItems", filtered);
			response.put("totalPages", pageInsts.getTotalPages());
			response.put("kode", "00");
			response.put("pesan", "BERHASIL");
//            TimeUnit.SECONDS.sleep(50);
			LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/usulan/list ************** " + "@ "
					+ objectMapper.writeValueAsString(response) + " \n");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
			LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/usulan/list ************** " + "@ "
					+ objectMapper.writeValueAsString(response) + " \n");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}



	@PostMapping("/mkm/usulan/getbyid")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getbyid(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/usulan/getbyid ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		try {
			M_data_usulan usul = m_data_usulanRepository.findByIdUsulan(id_usulan);
			if (usul != null) {
				
				M_data_neraca neraca = m_data_neracaRepository.findByIdNeraca(usul.getId_neraca());
				M_data_wallet wallet = m_data_walletRepository.findByIdWallet(usul.getId_wallet());
				List<M_data_aspek_keuangan> aspek = m_data_aspek_keuanganRepository.findByIdLoan(usul.getId_loan());
				M_data_loan loan = m_data_loanRepository.findByIdLoan(usul.getId_loan());
				System.out.println("##### TAMPILKAN SCORING ##### ");
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
				response.put("usulan", usul);
				response.put("neraca", neraca);
				response.put("wallet", wallet);
				response.put("aspek_keuangan", aspek);
				response.put("loan", loan);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_usulan + "  ini gak ada, coba yang lain ya !!!");

			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/usulan/getbyid ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/mkm/usulan/review")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setReviewUsulan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/usulan/review ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String keterangan = param.get("keterangan");
		try {
			M_data_usulan usulan = m_data_usulanRepository.findByIdUsulan(id_usulan);
			if (usulan != null) {
				String id_loan = usulan.getId_loan();
				Integer status = Integer.parseInt(usulan.getStatus());
				if (status.equals(10)) {
					System.out.println("##### DATA LOAN ##### ");

					m_data_usulanRepository.updateUsulan("11", user_id, user_nama, LocalDateTime.now(), keterangan, id_loan);
					M_data_loan loan = m_data_loanRepository.findByIdLoan(usulan.getId_loan());
					loan.setStatus("6");
					loan.setReview2_by(user_id);
					loan.setReview2_nama(user_nama);
					loan.setReview2_date(LocalDateTime.now());
					loan.setReview2_desc(keterangan);
					m_data_loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "REVIEW BERHASIL");
				} else if (status.equals(14) || status.equals(15) || status.equals(16)) {
					System.out.println("##### DATA LOAN ##### ");

					Integer update = m_data_usulanRepository.updateUsulanPimpinan("11", user_id, user_nama, LocalDateTime.now(), keterangan, id_loan);
					System.out.println("############## "+ update);
					M_data_loan loan = m_data_loanRepository.findByIdLoan(usulan.getId_loan());
					loan.setStatus("6");
					loan.setReview2_by(user_id);
					loan.setReview2_nama(user_nama);
					loan.setReview2_date(LocalDateTime.now());
					loan.setReview2_desc(keterangan);
					m_data_loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "REVIEW BERHASIL");
				} 
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_usulan + "  ini gak ada, coba yang lain ya !!!");

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/usulan/review ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/mkm/usulan/reject")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setrejectUsulan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/usulan/reject ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String keterangan = param.get("keterangan");
		try {
			M_data_usulan usulan = m_data_usulanRepository.findByIdUsulan(id_usulan);
			if (usulan != null) {
				Integer status = Integer.parseInt(usulan.getStatus());
				if (status.equals(10)) {
					System.out.println("##### DATA LOAN ##### ");
					usulan.setStatus("12");
					usulan.setCancel_by(user_id);
					usulan.setCancel_nama(user_nama);
					usulan.setCancel_date(LocalDateTime.now());
					usulan.setCancel_desc(keterangan);
					m_data_usulanRepository.save(usulan);
					
					M_data_loan loan = m_data_loanRepository.findByIdLoan(usulan.getId_loan());
					loan.setStatus("12");
					m_data_loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "REVIEW BERHASIL");
				} else if (status.equals(14) || status.equals(15) || status.equals(16)) {
					System.out.println("##### DATA LOAN ##### ");
					usulan.setStatus("12");
					usulan.setCancel_by(user_id);
					usulan.setCancel_nama(user_nama);
					usulan.setCancel_date(LocalDateTime.now());
					usulan.setCancel_desc(keterangan);
					m_data_usulanRepository.save(usulan);
					
					M_data_loan loan = m_data_loanRepository.findByIdLoan(usulan.getId_loan());
					loan.setStatus("12");
					m_data_loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "REVIEW BERHASIL");
				} 
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_usulan + "  ini gak ada, coba yang lain ya !!!");

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/usulan/reject ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/mkm/usulan/setusulan")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setusulan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/usulan/setusulan ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		try {
			M_data_usulan usulan = m_data_usulanRepository.findByIdUsulan(id_usulan);
			if (usulan != null) {
				usulan.setStatus("13");
				usulan.setInput_by(user_id);
				usulan.setInput_nama(user_nama);
				usulan.setInput_date(LocalDateTime.now());
				m_data_usulanRepository.save(usulan);
					
				M_data_loan loan = m_data_loanRepository.findByIdLoan(usulan.getId_loan());
				loan.setStatus("13");
				m_data_loanRepository.save(loan);

				response.put("kode", "00");
				response.put("pesan", "USULAN BERHASIL DISET");
				
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_usulan + "  ini gak ada, coba yang lain ya !!!");

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/usulan/setusulan ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/mkm/usulan/submit")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> submit(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/usulan/submit ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		try {
			M_data_usulan usulan = m_data_usulanRepository.findByIdUsulan(id_usulan);
			if (usulan != null) {
				usulan.setStatus("14");
				usulan.setSubmit_by(user_id);
				usulan.setSubmit_nama(user_nama);
				usulan.setSubmit_date(LocalDateTime.now());
				m_data_usulanRepository.save(usulan);
					
				M_data_loan loan = m_data_loanRepository.findByIdLoan(usulan.getId_loan());
				loan.setStatus("14");
				m_data_loanRepository.save(loan);

				response.put("kode", "00");
				response.put("pesan", "USULAN BERHASIL DIPROSES");
				
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_usulan + "  ini gak ada, coba yang lain ya !!!");

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/usulan/submit ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

	@PostMapping("/mkm/usulan/approve1")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setApprove1(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/usulan/approve1 ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String cab = param.get("cab");
		String rekomendasi = param.get("rekomendasi");
		try {
			M_data_usulan usulan = m_data_usulanRepository.findByIdUsulan(id_usulan);
			Optional<Par_cabang> datacab = par_cabangRepository.findById(cab);
			String sts = "14";
			if (datacab.isPresent() && datacab.get().getLevel_approve().equals("4")) {
				sts = "15";
			} else {
				sts = "16";
			}

			if (usulan != null) {
				usulan.setStatus(sts);
				usulan.setApprove1_by(user_id);
				usulan.setApprove1_date(LocalDateTime.now());
				usulan.setApprove1_nama(user_nama);
				usulan.setRekomendasi(rekomendasi);
				m_data_usulanRepository.save(usulan);
				M_data_loan loan = m_data_loanRepository.findByIdLoan(usulan.getId_loan());
					System.out.println("##### DATA LOAN ##### ");
					loan.setStatus(sts);
//					loan.setApprove1_by(user_id);
//					loan.setApprove1_date(LocalDateTime.now());
//					loan.setApprove1_nama(user_nama);
//					loan.setRekomendasi(rekomendasi);
					m_data_loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "APPROVE BERHASIL");
				
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_usulan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/usulan/approve1 ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/mkm/usulan/approve2")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setApprove2(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/usulan/approve2 ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
//		String cab = param.get("cab");
		try {
			M_data_usulan usulan = m_data_usulanRepository.findByIdUsulan(id_usulan);
//			Optional<Par_cabang> datacab = par_cabangRepository.findById(cab);
			String sts = "16";

			if (usulan != null) {
				usulan.setStatus(sts);
				usulan.setApprove2_by(user_id);
				usulan.setApprove2_date(LocalDateTime.now());
				usulan.setApprove2_nama(user_nama);
				m_data_usulanRepository.save(usulan);
				M_data_loan loan = m_data_loanRepository.findByIdLoan(usulan.getId_loan());
					System.out.println("##### DATA LOAN ##### ");
					loan.setStatus(sts);
//					loan.setApprove1_by(user_id);
//					loan.setApprove1_date(LocalDateTime.now());
//					loan.setApprove1_nama(user_nama);
					m_data_loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "APPROVE BERHASIL");
				
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_usulan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/usulan/approve2 ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/mkm/usulan/approve3")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> setApprove3(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/usulan/approve3 ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String cab = param.get("cab");
		String user_nama = param.get("user_nama");
		String keputusan = param.get("keputusan");
		
		try {
			M_data_usulan usulan = m_data_usulanRepository.findByIdUsulan(id_usulan);
			
			if (usulan != null) {
				M_data_loan loan = m_data_loanRepository.findByIdLoan(usulan.getId_loan());
				Optional<Par_cabang> datacab = par_cabangRepository.findById(cab);
				String kode_asuransi = loan.getKode_asuransi();
				Double plafon_disetujui = loan.getPlafon_disetujui();
				Integer tenor_disetujui = loan.getTenor_disetujui();
				Double angsuran_disetujui = loan.getAngsuran_disetujui();
				String sts = "17";
				String msg = "APPROVE BERHASIL";
				String cab_induk = "";
				String rc = "00";
//				if (datacab.isPresent() && loan.getPlafon_pengajuan() <= datacab.get().getLimit_konsumtif()) {
				if (datacab.isPresent() && plafon_disetujui <= datacab.get().getLimit_produktif()) {
					sts = "17";
					rc = "00";
					msg = "APPROVE BERHASIL SILAHKAN LANJUTKAN PENCAIRAN";
//				} else if (datacab.isPresent() && loan.getPlafon_pengajuan() > datacab.get().getLimit_konsumtif()) {
				} else if (datacab.isPresent() && plafon_disetujui > datacab.get().getLimit_produktif()) {
					// MELEBIHI LIMIT KANTOR
					if (datacab.get().getJenis_cab().equals("KEDAI") || datacab.get().getJenis_cab().equals("CAPEM")) {
						cab_induk = datacab.get().getCab_induk();
						sts = "17";
						rc = "00";
						msg = "APPROVE BERHASIL, PLAFON MELEBIHI LIMIT, MENUNGGU PERSETUJUAN CABANG";
						// SIMPAN KE DATA USULAN
						DateTimeFormatter formatteryear = DateTimeFormatter.ofPattern("yyyy");
						LocalDate year = LocalDate.now();

						String format_id_loan = "U." + cab + "." + year.format(formatteryear) + ".";
						String lastLoan = m_data_usulanRepository.getMaxLoan(format_id_loan, cab);
						Integer maxLoan = 1;
						if (lastLoan != null) {
							maxLoan = Integer.parseInt(lastLoan.substring(11, 18)) + 1;
						}
						String noReg = String.format("%7s", maxLoan).replace(' ', '0');
						String id_usul = format_id_loan + noReg;
						
						
						
						M_data_usulan usul = new M_data_usulan();
						
						usul.setTenor_pengajuan(tenor_disetujui);
						usul.setAngsuran_pengajuan(angsuran_disetujui);
						usul.setId(id_usul);
//						usul.setId_cab(loan.getId_cab());
						usul.setTgl_pengajuan(loan.getTgl_pengajuan());
						usul.setId_debitur(loan.getId_debitur());
						usul.setId_wallet(loan.getId_wallet());
						usul.setId_neraca(loan.getId_neraca());
						usul.setId_penghasilan(loan.getId_penghasilan());
						usul.setId_pasangan(loan.getId_pasangan());
						usul.setCaoi(loan.getCaoi());
						usul.setPcda(loan.getPcda());
						usul.setIdeb(loan.getIdeb());
						usul.setHubungan_bank(loan.getHubungan_bank());
						usul.setNorek(loan.getNorek());
						usul.setNama_bank(loan.getNama_bank());
						usul.setAtas_nama(loan.getAtas_nama());
						usul.setStatus_rekening(loan.getStatus_rekening());
						usul.setSaldo_rata_bulanan(loan.getSaldo_rata_bulanan());
						usul.setHubungan_bank_lain(loan.getHubungan_bank_lain());
						usul.setNama_bank_lain(loan.getNama_bank_lain());
						usul.setSlik_ref_pengguna(loan.getSlik_ref_pengguna());
						usul.setSlik_nomor_laporan(loan.getSlik_nomor_laporan());
						usul.setSlik_tgl_data_terakhir(loan.getSlik_tgl_data_terakhir());
						usul.setSlik_tgl_permintaan(loan.getSlik_tgl_permintaan());
						usul.setIs_take_over(loan.getIs_take_over());
						usul.setTake_over_nama_bank(loan.getTake_over_nama_bank());
						usul.setTake_over_total(loan.getTake_over_total());
						usul.setCp_jenis1(loan.getCp_jenis1());
						usul.setCp_plafon1(loan.getCp_plafon1());
						usul.setCp_baki_debet1(loan.getCp_baki_debet1());
						usul.setCp_jangka_waktu1(loan.getCp_jangka_waktu1());
						usul.setCp_margin1(loan.getCp_margin1());
						usul.setCp_angsuran1(loan.getCp_angsuran1());
						usul.setCp_tujuan_pinjaman1(loan.getCp_tujuan_pinjaman1());
						usul.setCp_tunggakan1(loan.getCp_tunggakan1());
						usul.setCp_kolektibilitas1(loan.getCp_kolektibilitas1());
						usul.setCp_jenis2(loan.getCp_jenis2());
						usul.setCp_plafon2(loan.getCp_plafon2());
						usul.setCp_baki_debet2(loan.getCp_baki_debet2());
						usul.setCp_jangka_waktu2(loan.getCp_jangka_waktu2());
						usul.setCp_margin2(loan.getCp_margin2());
						usul.setCp_angsuran2(loan.getCp_angsuran2());
						usul.setCp_tujuan_pinjaman2(loan.getCp_tujuan_pinjaman2());
						usul.setCp_tunggakan2(loan.getCp_tunggakan2());
						usul.setCp_kolektibilitas2(loan.getCp_kolektibilitas2());
						usul.setCp_jenis3(loan.getCp_jenis3());
						usul.setCp_plafon3(loan.getCp_plafon3());
						usul.setCp_baki_debet3(loan.getCp_baki_debet3());
						usul.setCp_jangka_waktu3(loan.getCp_jangka_waktu3());
						usul.setCp_margin3(loan.getCp_margin3());
						usul.setCp_angsuran3(loan.getCp_angsuran3());
						usul.setCp_tujuan_pinjaman3(loan.getCp_tujuan_pinjaman3());
						usul.setCp_tunggakan3(loan.getCp_tunggakan3());
						usul.setCp_kolektibilitas3(loan.getCp_kolektibilitas3());
						usul.setJenis_pembiayaan(loan.getJenis_pembiayaan());
						usul.setPremi_asuransi_lama(loan.getPremi_asuransi_lama());
						usul.setTgl_kredit_lama(loan.getTgl_kredit_lama());
						usul.setBenefit_kredit_lama(loan.getBenefit_kredit_lama());
						usul.setTenor_kredit_lama(loan.getTenor_kredit_lama());
						usul.setKuasa_pemotongan(loan.getKuasa_pemotongan());
						usul.setPersentase_kuasa_pemotongan(loan.getPersentase_kuasa_pemotongan());
						usul.setKode_officer_1(loan.getKode_officer_1());
						usul.setKode_officer_2(loan.getKode_officer_2());
						usul.setKode_referal(loan.getKode_referal());
						usul.setKode_notaris(loan.getKode_notaris());
						usul.setTujuan_pembiayaan(loan.getTujuan_pembiayaan());
						usul.setId_kategori_produk(loan.getId_kategori_produk());
						usul.setId_plan(loan.getId_plan());
						usul.setKategori_template(loan.getKategori_template());
						usul.setFrequensi_pembayaran_margin(loan.getFrequensi_pembayaran_margin());
						usul.setFrequensi_pembayaran_number(loan.getFrequensi_pembayaran_number());
						usul.setGrace_period(loan.getGrace_period());
						usul.setHarga_perolehan(loan.getHarga_perolehan());
						usul.setPajak(loan.getPajak());
						usul.setDiskon(loan.getDiskon());
						usul.setPlafon_pengajuan(loan.getPlafon_pengajuan());
						usul.setUang_muka(loan.getUang_muka());
						usul.setTenor_pengajuan(loan.getTenor_pengajuan());
						usul.setKode_margin(loan.getKode_margin());
						usul.setMargin_pengajuan(loan.getMargin_pengajuan());
						usul.setBasis_point_margin(loan.getBasis_point_margin());
						usul.setBasis_point_margin_mark(loan.getBasis_point_margin_mark());
						usul.setExp_rate(loan.getExp_rate());
						usul.setNisbah_bank(loan.getNisbah_bank());
						usul.setNisbah_nasabah(loan.getNisbah_nasabah());
						usul.setAngsuran_pengajuan(loan.getAngsuran_pengajuan());
						usul.setUd_nama_usaha(loan.getUd_nama_usaha());
						usul.setUd_bidang_usaha(loan.getUd_bidang_usaha());
						usul.setUd_lama_usaha(loan.getUd_lama_usaha());
						usul.setUd_status_tempat_usaha(loan.getUd_status_tempat_usaha());
						usul.setUd_omset_perbulan(loan.getUd_omset_perbulan());
						usul.setUd_profit_perbulan(loan.getUd_profit_perbulan());
						usul.setS_kepemilikan_agunan(loan.getS_kepemilikan_agunan());
						usul.setS_jenis_agunan(loan.getS_jenis_agunan());
						usul.setS_status_kepemilikan_agunan(loan.getS_status_kepemilikan_agunan());
						usul.setS_kriteria_agunan(loan.getS_kriteria_agunan());
						usul.setBlokir_saldo(loan.getBlokir_saldo());
						usul.setIs_wakalah(loan.getIs_wakalah());
						usul.setNorek_wakalah(loan.getNorek_wakalah());
						usul.setKode_rekening_wakalah(loan.getKode_rekening_wakalah());
						usul.setNama_rekening_wakalah(loan.getNama_rekening_wakalah());
						usul.setTgl_kunjungan(loan.getTgl_kunjungan());
						usul.setOfficer_bank(loan.getOfficer_bank());
						usul.setKode_broker(loan.getKode_broker());
						usul.setKode_asuransi(loan.getKode_asuransi());
						usul.setNilai_harta_thp_kredit(loan.getNilai_harta_thp_kredit());
						usul.setAp_aspek_manajemen(loan.getAp_aspek_manajemen()); 
						usul.setAp_orang_yang_ditemui(loan.getAp_orang_yang_ditemui()); 
						usul.setAp_aspek_pemasaran(loan.getAp_aspek_pemasaran()); 
						usul.setAp_aspek_teknis(loan.getAp_aspek_teknis()); 
						usul.setAp_aspek_syariah(loan.getAp_aspek_syariah()); 
						usul.setCopph(loan.getCopph()); 
						usul.setJumlah_cost_of_project(loan.getJumlah_cost_of_project()); 
						usul.setBy_mp_kebun(loan.getBy_mp_kebun()); 
						usul.setDana_bantuan(loan.getDana_bantuan()); 
						usul.setSelf_financing(loan.getSelf_financing()); 
						usul.setCreated_by(user_id);
						usul.setCreated_nama(user_nama);
						usul.setCreated_date(LocalDateTime.now());
						usul.setPlafon_disetujui(loan.getPlafon_disetujui());
						usul.setTenor_disetujui(loan.getTenor_disetujui());
						usul.setAngsuran_disetujui(loan.getAngsuran_disetujui());
						usul.setRekomendasi(loan.getRekomendasi());
						usul.setKeputusan(loan.getKeputusan());
						usul.setSp3_file(loan.getSp3_file());
						usul.setNo_sp3(loan.getNo_sp3());
						usul.setNo_sp4(loan.getNo_sp4());
						usul.setSp4_file(loan.getSp4_file());
						usul.setIs_biaya_adm(loan.getIs_biaya_adm());
						usul.setPersentase_biaya_adm(loan.getPersentase_biaya_adm());
						usul.setBiaya_adm(loan.getBiaya_adm());
						usul.setBiaya_asuransi_jiwa(loan.getBiaya_asuransi_jiwa());
						usul.setBiaya_asuransi_kebakaran(loan.getBiaya_asuransi_kebakaran());
						usul.setBiaya_asuransi_pembiayaan(loan.getBiaya_asuransi_pembiayaan());
						usul.setId_template_dokumen(loan.getId_template_dokumen());
						usul.setNotisi_file(loan.getNotisi_file());
						usul.setStatus("10");
						usul.setTgl_pengajuan(LocalDateTime.now());
						usul.setId_cab(cab_induk);
						usul.setId_loan(usulan.getId_loan());
						usul.setId_old_usulan(id_usul);
						
						usul.setKategori_template(loan.getKategori_template());
						m_data_usulanRepository.save(usul);
						
						

					} else if (datacab.get().getJenis_cab().equals("CABANG")) {
						sts = usulan.getStatus();
						rc = "10";
						msg = "TRANSAKSI MELEBIHI LIMIT CABANG, SILAHKAN DITERUSKAN KE KANTOR PUSAT";
					} else if (datacab.get().getJenis_cab().equals("DIVISI")) {
						sts = usulan.getStatus();
						rc = "10";
						msg = "TRANSAKSI MELEBIHI LIMIT DIVISI, SILAHKAN DITERUSKAN KE DIREKSI";
					}
				}

				if (Integer.parseInt(usulan.getStatus()) == 14 || Integer.parseInt(usulan.getStatus()) == 15
						|| Integer.parseInt(usulan.getStatus()) == 16) {
					usulan.setStatus(sts);
					usulan.setApprove3_by(user_id);
					usulan.setApprove3_nama(user_nama);
					usulan.setApprove3_date(LocalDateTime.now());
					usulan.setKeputusan(keputusan);
					usulan.setKode_asuransi(kode_asuransi);
					usulan.setPlafon_disetujui(plafon_disetujui);
					usulan.setTenor_disetujui(tenor_disetujui);
					usulan.setAngsuran_disetujui(angsuran_disetujui);
					m_data_usulanRepository.save(usulan);
					
					loan.setStatus(sts);
					m_data_loanRepository.save(loan);
				

					response.put("kode", rc);
					response.put("pesan", msg);
				} else if (Integer.parseInt(usulan.getStatus()) < 14) {
					response.put("kode", "18");
					response.put("pesan", "Oops.. Anda belum bisa di Approve !!!");
				} else {
					response.put("kode", "13");
					response.put("pesan", "Oops.. Status pembiayaan tidak sesuai!!!");
				}
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_usulan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/usulan/approve3 ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/mkm/usulan/forward")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setForward(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/usulan/forward ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String cab = param.get("cab");
		String forward_to = param.get("forward_to");

		try {
			M_data_usulan usulan = m_data_usulanRepository.findByIdUsulan(id_usulan);
			if (usulan != null) {
				M_data_loan loan = m_data_loanRepository.findByIdLoan(usulan.getId_loan());
//				String kode_asuransi = loan.getKode_asuransi();
//				Double plafon_disetujui = loan.getPlafon_disetujui();
				Integer tenor_disetujui = loan.getTenor_disetujui();
				Double angsuran_disetujui = loan.getAngsuran_disetujui();
				
					String sts = "17";
					String msg = "FORWARD BERHASIL, MENUNGGU PERSETUJUAN";

					// SIMPAN KE DATA USULAN
					DateTimeFormatter formatteryear = DateTimeFormatter.ofPattern("yyyy");
					LocalDate year = LocalDate.now();

					String format_id_loan = "U." + cab + "." + year.format(formatteryear) + ".";
					String lastLoan = m_data_usulanRepository.getMaxLoan(format_id_loan, cab);
					Integer maxLoan = 1;
					if (lastLoan != null) {
						maxLoan = Integer.parseInt(lastLoan.substring(11, 18)) + 1;
					}
					String noReg = String.format("%7s", maxLoan).replace(' ', '0');
					String id_usul = format_id_loan + noReg;
					String old_id_usulan = null;
					String last_loan = m_data_usulanRepository.lastLoan(loan.getId());
					if (last_loan == null) {
						old_id_usulan = format_id_loan + noReg;
					} else {
						old_id_usulan = last_loan;
						M_data_usulan usul = m_data_usulanRepository.findByIdUsulan(old_id_usulan);
						usul.setIs_forward("1");
						m_data_usulanRepository.save(usul);
					}
					System.out.println("####### SIMPAN USULAN ##########");
					M_data_usulan usul = new M_data_usulan();
					
					usul.setTenor_pengajuan(loan.getTenor_pengajuan());
					usul.setAngsuran_pengajuan(loan.getAngsuran_pengajuan());
					usul.setId(id_usul);
//					usul.setId_cab(loan.getId_cab());
					usul.setTgl_pengajuan(loan.getTgl_pengajuan());
					usul.setId_debitur(loan.getId_debitur());
					usul.setId_wallet(loan.getId_wallet());
					usul.setId_neraca(loan.getId_neraca());
					usul.setId_penghasilan(loan.getId_penghasilan());
					usul.setId_pasangan(loan.getId_pasangan());
					usul.setCaoi(loan.getCaoi());
					usul.setPcda(loan.getPcda());
					usul.setIdeb(loan.getIdeb());
					usul.setHubungan_bank(loan.getHubungan_bank());
					usul.setNorek(loan.getNorek());
					usul.setNama_bank(loan.getNama_bank());
					usul.setAtas_nama(loan.getAtas_nama());
					usul.setStatus_rekening(loan.getStatus_rekening());
					usul.setSaldo_rata_bulanan(loan.getSaldo_rata_bulanan());
					usul.setHubungan_bank_lain(loan.getHubungan_bank_lain());
					usul.setNama_bank_lain(loan.getNama_bank_lain());
					usul.setSlik_ref_pengguna(loan.getSlik_ref_pengguna());
					usul.setSlik_nomor_laporan(loan.getSlik_nomor_laporan());
					usul.setSlik_tgl_data_terakhir(loan.getSlik_tgl_data_terakhir());
					usul.setSlik_tgl_permintaan(loan.getSlik_tgl_permintaan());
					usul.setIs_take_over(loan.getIs_take_over());
					usul.setTake_over_nama_bank(loan.getTake_over_nama_bank());
					usul.setTake_over_total(loan.getTake_over_total());
					usul.setCp_jenis1(loan.getCp_jenis1());
					usul.setCp_plafon1(loan.getCp_plafon1());
					usul.setCp_baki_debet1(loan.getCp_baki_debet1());
					usul.setCp_jangka_waktu1(loan.getCp_jangka_waktu1());
					usul.setCp_margin1(loan.getCp_margin1());
					usul.setCp_angsuran1(loan.getCp_angsuran1());
					usul.setCp_tujuan_pinjaman1(loan.getCp_tujuan_pinjaman1());
					usul.setCp_tunggakan1(loan.getCp_tunggakan1());
					usul.setCp_kolektibilitas1(loan.getCp_kolektibilitas1());
					usul.setCp_jenis2(loan.getCp_jenis2());
					usul.setCp_plafon2(loan.getCp_plafon2());
					usul.setCp_baki_debet2(loan.getCp_baki_debet2());
					usul.setCp_jangka_waktu2(loan.getCp_jangka_waktu2());
					usul.setCp_margin2(loan.getCp_margin2());
					usul.setCp_angsuran2(loan.getCp_angsuran2());
					usul.setCp_tujuan_pinjaman2(loan.getCp_tujuan_pinjaman2());
					usul.setCp_tunggakan2(loan.getCp_tunggakan2());
					usul.setCp_kolektibilitas2(loan.getCp_kolektibilitas2());
					usul.setCp_jenis3(loan.getCp_jenis3());
					usul.setCp_plafon3(loan.getCp_plafon3());
					usul.setCp_baki_debet3(loan.getCp_baki_debet3());
					usul.setCp_jangka_waktu3(loan.getCp_jangka_waktu3());
					usul.setCp_margin3(loan.getCp_margin3());
					usul.setCp_angsuran3(loan.getCp_angsuran3());
					usul.setCp_tujuan_pinjaman3(loan.getCp_tujuan_pinjaman3());
					usul.setCp_tunggakan3(loan.getCp_tunggakan3());
					usul.setCp_kolektibilitas3(loan.getCp_kolektibilitas3());
					usul.setJenis_pembiayaan(loan.getJenis_pembiayaan());
					usul.setPremi_asuransi_lama(loan.getPremi_asuransi_lama());
					usul.setTgl_kredit_lama(loan.getTgl_kredit_lama());
					usul.setBenefit_kredit_lama(loan.getBenefit_kredit_lama());
					usul.setTenor_kredit_lama(loan.getTenor_kredit_lama());
					usul.setKuasa_pemotongan(loan.getKuasa_pemotongan());
					usul.setPersentase_kuasa_pemotongan(loan.getPersentase_kuasa_pemotongan());
					usul.setKode_officer_1(loan.getKode_officer_1());
					usul.setKode_officer_2(loan.getKode_officer_2());
					usul.setKode_referal(loan.getKode_referal());
					usul.setKode_notaris(loan.getKode_notaris());
					usul.setTujuan_pembiayaan(loan.getTujuan_pembiayaan());
					usul.setId_kategori_produk(loan.getId_kategori_produk());
					usul.setId_plan(loan.getId_plan());
					usul.setKategori_template(loan.getKategori_template());
					usul.setFrequensi_pembayaran_margin(loan.getFrequensi_pembayaran_margin());
					usul.setFrequensi_pembayaran_number(loan.getFrequensi_pembayaran_number());
					usul.setGrace_period(loan.getGrace_period());
					usul.setHarga_perolehan(loan.getHarga_perolehan());
					usul.setPajak(loan.getPajak());
					usul.setDiskon(loan.getDiskon());
					usul.setPlafon_pengajuan(loan.getPlafon_pengajuan());
					usul.setUang_muka(loan.getUang_muka());
					usul.setTenor_pengajuan(loan.getTenor_pengajuan());
					usul.setKode_margin(loan.getKode_margin());
					usul.setMargin_pengajuan(loan.getMargin_pengajuan());
					usul.setBasis_point_margin(loan.getBasis_point_margin());
					usul.setBasis_point_margin_mark(loan.getBasis_point_margin_mark());
					usul.setExp_rate(loan.getExp_rate());
					usul.setNisbah_bank(loan.getNisbah_bank());
					usul.setNisbah_nasabah(loan.getNisbah_nasabah());
					usul.setAngsuran_pengajuan(loan.getAngsuran_pengajuan());
					usul.setUd_nama_usaha(loan.getUd_nama_usaha());
					usul.setUd_bidang_usaha(loan.getUd_bidang_usaha());
					usul.setUd_lama_usaha(loan.getUd_lama_usaha());
					usul.setUd_status_tempat_usaha(loan.getUd_status_tempat_usaha());
					usul.setUd_omset_perbulan(loan.getUd_omset_perbulan());
					usul.setUd_profit_perbulan(loan.getUd_profit_perbulan());
					usul.setS_kepemilikan_agunan(loan.getS_kepemilikan_agunan());
					usul.setS_jenis_agunan(loan.getS_jenis_agunan());
					usul.setS_status_kepemilikan_agunan(loan.getS_status_kepemilikan_agunan());
					usul.setS_kriteria_agunan(loan.getS_kriteria_agunan());
					usul.setBlokir_saldo(loan.getBlokir_saldo());
					usul.setIs_wakalah(loan.getIs_wakalah());
					usul.setNorek_wakalah(loan.getNorek_wakalah());
					usul.setKode_rekening_wakalah(loan.getKode_rekening_wakalah());
					usul.setNama_rekening_wakalah(loan.getNama_rekening_wakalah());
					usul.setTgl_kunjungan(loan.getTgl_kunjungan());
					usul.setOfficer_bank(loan.getOfficer_bank());
					usul.setKode_broker(loan.getKode_broker());
					usul.setKode_asuransi(loan.getKode_asuransi());
					usul.setNilai_harta_thp_kredit(loan.getNilai_harta_thp_kredit());
					usul.setAp_aspek_manajemen(loan.getAp_aspek_manajemen()); 
					usul.setAp_orang_yang_ditemui(loan.getAp_orang_yang_ditemui()); 
					usul.setAp_aspek_pemasaran(loan.getAp_aspek_pemasaran()); 
					usul.setAp_aspek_teknis(loan.getAp_aspek_teknis()); 
					usul.setAp_aspek_syariah(loan.getAp_aspek_syariah()); 
					usul.setCopph(loan.getCopph()); 
					usul.setJumlah_cost_of_project(loan.getJumlah_cost_of_project()); 
					usul.setBy_mp_kebun(loan.getBy_mp_kebun()); 
					usul.setDana_bantuan(loan.getDana_bantuan()); 
					usul.setSelf_financing(loan.getSelf_financing()); 
					usul.setCreated_by(user_id);
					usul.setCreated_nama(user_nama);
					usul.setCreated_date(LocalDateTime.now());
					usul.setPlafon_disetujui(loan.getPlafon_disetujui());
					usul.setTenor_disetujui(tenor_disetujui);
					usul.setAngsuran_disetujui(angsuran_disetujui);
					usul.setRekomendasi(loan.getRekomendasi());
					usul.setKeputusan(loan.getKeputusan());
					usul.setSp3_file(loan.getSp3_file());
					usul.setNo_sp3(loan.getNo_sp3());
					usul.setNo_sp4(loan.getNo_sp4());
					usul.setSp4_file(loan.getSp4_file());
					usul.setIs_biaya_adm(loan.getIs_biaya_adm());
					usul.setPersentase_biaya_adm(loan.getPersentase_biaya_adm());
					usul.setBiaya_adm(loan.getBiaya_adm());
					usul.setBiaya_asuransi_jiwa(loan.getBiaya_asuransi_jiwa());
					usul.setBiaya_asuransi_kebakaran(loan.getBiaya_asuransi_kebakaran());
					usul.setBiaya_asuransi_pembiayaan(loan.getBiaya_asuransi_pembiayaan());
					usul.setId_template_dokumen(loan.getId_template_dokumen());
					usul.setNotisi_file(loan.getNotisi_file());
					usul.setStatus("10");
					usul.setTgl_pengajuan(LocalDateTime.now());
					usul.setId_cab(forward_to);
					usul.setId_loan(usulan.getId_loan());
					usul.setId_old_usulan(old_id_usulan);
					
					usul.setKategori_template(loan.getKategori_template());
					m_data_usulanRepository.save(usul);

					System.out.println("##### SIMPAN LOAN ##### ");
					loan.setStatus(sts);
					
					m_data_loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", msg);
				
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_usulan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/usulan/forward ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("/mkm/usulan/uploadsp4")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setuploadsp4(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/usulan/approve1 ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String id_loan = param.get("id_loan");
		String sp4_file = param.get("sp4_file");
		try {
			M_data_usulan usulan = m_data_usulanRepository.findByIdUsulan(id_usulan);
			
			if (usulan != null) {
				usulan.setSp4_file(sp4_file);
				usulan.setStatus("18");
				m_data_usulanRepository.save(usulan);
				M_data_loan loan = m_data_loanRepository.findByIdLoan(id_loan);
					System.out.println("##### DATA LOAN ##### ");
					loan.setSp4_file(sp4_file);
					loan.setStatus("18");
					m_data_loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "APPROVE BERHASIL");
				
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_usulan + "  ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/usulan/approve1 ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("mkm/usulan/setfrreview")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setfrreview(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND mkm/usulan/setfrreview ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String fr_file_review = param.get("fr_file_review");
		String fr_reviewer = param.get("fr_reviewer");
		String fr_hasil_review = param.get("fr_hasil_review");
		String fr_tgl_review = param.get("fr_tgl_review");
		try {
			M_data_usulan usulan = m_data_usulanRepository.findByIdUsulan(id_usulan);
			
			if (usulan != null) {
				M_data_loan loan = m_data_loanRepository.findByIdLoan(usulan.getId_loan());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate tglreview = LocalDate.parse(fr_tgl_review, formatter);
				usulan.setFr_file_review(fr_file_review);
				usulan.setFr_reviewer(fr_reviewer);
				usulan.setFr_hasil_review(fr_hasil_review);
				usulan.setFr_tgl_review(tglreview);
				m_data_usulanRepository.save(usulan);
				
				System.out.println("##### DATA LOAN ##### ");
					loan.setFr_file_review(fr_file_review);
					loan.setFr_reviewer(fr_reviewer);
					loan.setFr_hasil_review(fr_hasil_review);
					loan.setFr_tgl_review(tglreview);
					m_data_loanRepository.save(loan);

					response.put("kode", "00");
					response.put("pesan", "SAVE REVIEW FR BERHASIL");
				
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Register " + id_usulan + "  ini gak ada, coba yang lain ya !!!");

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND mkm/usulan/setfrreview ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
