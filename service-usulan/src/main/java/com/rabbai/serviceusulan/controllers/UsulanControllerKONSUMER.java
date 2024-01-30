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
import com.rabbai.serviceusulan.models.Data_lampiran_file;
import com.rabbai.serviceusulan.models.Debitur;
import com.rabbai.serviceusulan.models.Loan;
import com.rabbai.serviceusulan.models.Par_cabang;
import com.rabbai.serviceusulan.models.Par_ceklist;
import com.rabbai.serviceusulan.models.Usulan;
import com.rabbai.serviceusulan.repository.Data_lampiran_fileRepository;
import com.rabbai.serviceusulan.repository.DebiturRepository;
import com.rabbai.serviceusulan.repository.LoanRepository;
import com.rabbai.serviceusulan.repository.Par_cabangRepository;
import com.rabbai.serviceusulan.repository.Par_ceklistRepository;
import com.rabbai.serviceusulan.repository.Par_rincian_scoringRepository;
import com.rabbai.serviceusulan.repository.PasanganRepository;
import com.rabbai.serviceusulan.repository.PekerjaanRepository;
import com.rabbai.serviceusulan.repository.ScoringLoanRepository;
import com.rabbai.serviceusulan.repository.UsersRepository;
import com.rabbai.serviceusulan.repository.UsulanRepository;
import com.rabbai.serviceusulan.services.CoreBankingService;
import com.rabbai.serviceusulan.services.FilesStorageService;
import com.rabbai.serviceusulan.services.MailService;

@RestController
public class UsulanControllerKONSUMER {

	@Autowired
	DebiturRepository debiturRepository;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	PekerjaanRepository pekerjaanRepository;

	@Autowired
	LoanRepository loanRepository;

	@Autowired
	ScoringLoanRepository scoringloanRepository;

	@Autowired
	MailService notificationService;

	@Autowired
	FilesStorageService storageService;

	@Autowired
	PasanganRepository pasanganRepository;

	@Autowired
	Data_lampiran_fileRepository lampiran_fileRepository;

	@Autowired
	Par_ceklistRepository par_ceklistRepository;

	@Autowired
	Par_rincian_scoringRepository par_rincian_scoringRepository;

	@Autowired
	Par_cabangRepository par_cabangRepository;

	@Autowired
	UsulanRepository usulanRepository;

	@Autowired
	CoreBankingService cbsService;
	private static Logger LOGGER = Logger.getLogger(UsulanControllerKONSUMER.class.getName());

	@PostMapping("/usulan1/calondebitur/list")
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
			List<Usulan> usul = new ArrayList<Usulan>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;
			Page<Usulan> pageInsts;

			if (keyword != null) {
				pageInsts = usulanRepository.findKeywordDebiturWithPagination(paging, keyword, cab);
				filtered = usulanRepository.getCount(cab);
			} else {
				pageInsts = usulanRepository.findAllDebiturWithPagination(paging, cab);
				filtered = pageInsts.getNumberOfElements();
			}
			usul = pageInsts.getContent();

			response.put("totalFilter", pageInsts.getTotalElements());
			response.put("calondebitur", usul);
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

	@PostMapping("/usulan1/calondebitur/inquiry")
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
			List<Usulan> exist = usulanRepository.findByKtp(ktp);
			LOGGER.info("!!! INQUIRY DEBITUR CEK LOAN !!! " + "@ " + objectMapper.writeValueAsString(exist) + " \n");
			System.out.println("##### LOAN IS PRESENT ???? ##### " + exist.size());
			if (exist.size() > 0 && (exist.get(0).getStatus().equals("12") || exist.get(0).getStatus().equals("19")
					|| exist.get(0).getStatus().equals("1"))) {
				Debitur debitur = debiturRepository.findByKtpDebitur(ktp);
				System.out.println("##### TAMPILKAN DEBITUR 0##### ");
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
				response.put("calondebitur", debitur);

			} else if (exist.size() > 0
					&& (!exist.get(0).getStatus().equals("12") || !exist.get(0).getStatus().equals("19")
							|| !exist.get(0).getStatus().equals("1") || !exist.get(0).getStatus().equals("18"))) {

				System.out.println("##### SUDAH ADA ##### ");
				response.put("kode", "88");
				response.put("pesan",
						"Oops.. Nomor KTP " + ktp + " ini sudah ada, Selesaikan dulu pengajuan sebelumnya ya !!!");
			} else {

				System.out.println("##### TIDAK ADA ##### ");
				response.put("kode", "10");
				response.put("pesan", "Oops.. No KTP " + ktp + "  ini gak ada, coba yang lain ya !!!");

				LOGGER.info("\n ************** RESPONSE TO FRONTEND INQUIRY DEBITUR ************** " + "@ "
						+ objectMapper.writeValueAsString(response) + " \n");
				return new ResponseEntity<>(response, HttpStatus.OK);

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

	@PostMapping("/usulan1/calondebitur/inquiryusulanbyid")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getloan(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND INQUIRY USULAN BY ID ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		try {
			Usulan usul = usulanRepository.findByIdLoan(id_usulan);
			if (usul != null) {

//				LOGGER.info("!! LOAN (INQUIRY LOAN BY ID) !! " + "@ " + objectMapper.writeValueAsString(usul) + " \n");

				System.out.println("##### TAMPILKAN SCORING ##### ");
				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
				response.put("usulan", usul);
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND INQUIRY LOAN BY ID ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/usulan1/calondebitur/deleteloanbyid/{id_loan}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteLoan(@PathVariable String id_loan,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND DELETE LOAN BY ID ************** " + "@ "
				+ objectMapper.writeValueAsString(id_loan) + " \n");
		Map<String, Object> response = new HashMap<>();
		try {
			Loan loan = loanRepository.findByIdLoan(id_loan);

			if (loan == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data debitur ini gak ada, coba yang lain ya !!!");
			} else {
				loanRepository.delete(loan);
//            instansi.setInstansi_status("9");
//            debiturRepository.save(instansi);
				response.put("kode", "00");
				response.put("pesan", "HAPUS PEMBIAYAAN BERHASIL");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND DELETE LOAN BY ID ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/usulan1/calondebitur/delete/{ktp}")
	@Transactional
	public ResponseEntity<Map<String, Object>> deleteDebitur(@PathVariable String ktp, @AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND DELETE BY KTP ************** " + "@ "
				+ objectMapper.writeValueAsString(ktp) + " \n");

		Map<String, Object> response = new HashMap<>();
		try {
			Debitur debitur = debiturRepository.findByKtpDebitur(ktp);

			if (debitur == null) {
				response.put("kode", "10");
				response.put("pesan", "Oops.. Data debitur ini gak ada, coba yang lain ya !!!");
			} else {
				debiturRepository.delete(debitur);
//            instansi.setInstansi_status("9");
//            debiturRepository.save(instansi);
				response.put("kode", "00");
				response.put("pesan", "HAPUS DEBITUR BERHASIL");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND DELETE BY KTP ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Transactional
	@PostMapping("/usulan1/calondebitur/submit")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setSubmit(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND PROSES USULAN ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		try {
			Usulan usul = usulanRepository.findByIdLoan(id_usulan);

			if (usul != null) {
				if (usul.getStatus().equals("3") || usul.getStatus().equals("5")) {
					String jenis_id_loan = usul.getId_loan().substring(0, 1);
					System.out.println("##### DATA USULAN TABEL DATA_USULAN_I ##### ");
					usul.setStatus("4");
					usul.setSubmit_by(user_id);
					usul.setSubmit_nama(user_nama);
					usul.setSubmit_date(LocalDateTime.now());
					usulanRepository.save(usul);
					if (jenis_id_loan.equals("U")) {
						Usulan old_usul = usulanRepository.findByIdLoan(usul.getId_loan());
						System.out.println("##### DATA USULAN LAMA ##### ");
						old_usul.setStatus("9");
						usulanRepository.save(old_usul);
					}

					response.put("kode", "00");
					response.put("pesan", "SUBMIT BERHASIL");
				} else if (Integer.parseInt(usul.getStatus()) < 7) {
					response.put("kode", "14");
					response.put("pesan", "Oops.. Debitur belum melakukan usulan !!!");
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND PROSES USULAN ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/usulan1/calondebitur/review")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setReview(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND REVIEW CALON DEBITUR ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String keterangan = param.get("keterangan");
		String user_nama = param.get("user_nama");
		try {
			Usulan usul = usulanRepository.findByIdLoan(id_usulan);

			if (usul != null) {
				if (usul.getStatus().equals("4")) {

					System.out.println("##### DATA LOAN ##### ");
					usul.setStatus("5");
					usul.setReview_by(user_id);
					usul.setReview_date(LocalDateTime.now());
					usul.setReview_desc(keterangan);
					usul.setReview_nama(user_nama);
					usulanRepository.save(usul);

					response.put("kode", "00");
					response.put("pesan", "REVIEW BERHASIL");
				} else if (Integer.parseInt(usul.getStatus()) < 8) {
					response.put("kode", "15");
					response.put("pesan", "Oops.. Debitur belum di Submit !!!");
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND REVIEW CALON DEBITUR ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/usulan1/notisi/reject")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setNotisiReject(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND REVIEW CALON DEBITUR ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String keterangan = param.get("keterangan");
		String user_nama = param.get("user_nama");
		try {
			Usulan usul = usulanRepository.findByIdLoan(id_usulan);

			if (usul != null) {
				

					System.out.println("##### DATA LOAN ##### ");
					usul.setStatus("12");
					usul.setReview_by(user_id);
					usul.setReview_date(LocalDateTime.now());
					usul.setReview_desc(keterangan);
					usul.setReview_nama(user_nama);
					usulanRepository.save(usul);

					response.put("kode", "00");
					response.put("pesan", "REVIEW BERHASIL");
				
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND REVIEW CALON DEBITUR ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/usulan1/notisi/review")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setNotisiReview(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND REVIEW CALON DEBITUR ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String keterangan = param.get("keterangan");
		String user_nama = param.get("user_nama");
		try {
			Usulan usul = usulanRepository.findByIdLoan(id_usulan);

			if (usul != null) {
				

					System.out.println("##### DATA LOAN ##### ");
					usul.setStatus("5");
					usul.setReview_by(user_id);
					usul.setReview_date(LocalDateTime.now());
					usul.setReview_desc(keterangan);
					usul.setReview_nama(user_nama);
					usulanRepository.save(usul);

					response.put("kode", "00");
					response.put("pesan", "REVIEW BERHASIL");
				
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND REVIEW CALON DEBITUR ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@PostMapping("/usulan1/notisi/approve1")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setApprove1(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND APPROVE USULAN 1 ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String cab = param.get("cab");
		String rekomendasi = param.get("rekomendasi");
		try {
			Usulan usul = usulanRepository.findByIdLoan(id_usulan);

			if (usul != null) {

				Optional<Par_cabang> datacab = par_cabangRepository.findById(cab);
				String sts = usul.getStatus();
				String odl_sts = "";
				if (datacab.isPresent() && datacab.get().getLevel_approve().equals("4")) {
					sts = "6";
					odl_sts = "10";
				} else {
					sts = "7";
					odl_sts = "11";
				}

				if (usul.getStatus().equals("4")) {
					String jenis_id_loan = usul.getId_loan().substring(0, 1);
					System.out.println("##### DATA LOAN ##### ");
					usul.setStatus(sts);
					usul.setApprove1_by(user_id);
					usul.setApprove1_date(LocalDateTime.now());
					usul.setApprove1_nama(user_nama);
					usul.setRekomendasi(rekomendasi);
					usulanRepository.save(usul);

//					if (jenis_id_loan.equals("R")) {
//						Loan loan = loanRepository.findByIdLoan(usul.getId_loan());
//						System.out.println("##### DATA LOAN ##### ");
//						loan.setStatus(odl_sts);
//						loanRepository.save(loan);
//					} else 
					if (jenis_id_loan.equals("U")) {
						Usulan old_usul = usulanRepository.findByIdLoan(usul.getId_loan());
						System.out.println("##### DATA USULAN LAMA ##### ");
						old_usul.setStatus(odl_sts);
						usulanRepository.save(old_usul);
					}

					response.put("kode", "00");
					response.put("pesan", "APPROVE USULAN BERHASIL");

				} else if (usul.getStatus().equals("5")) {
					response.put("kode", "16");
					response.put("pesan", "Oops.. Debitur belum di Review !!!");
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND APPROVE USULAN 1 ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Transactional
	@PostMapping("/usulan1/notisi/approve2")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setApprove2(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND APPROVE USULAN 2 ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		try {
			Usulan usul = usulanRepository.findByIdLoan(id_usulan);

			if (usul != null) {
				if (usul.getStatus().equals("6")) {
					String jenis_id_loan = usul.getId_loan().substring(0, 1);
					System.out.println("##### DATA LOAN ##### ");
					usul.setStatus("7");
					usul.setApprove1_by(user_id);
					usul.setApprove1_date(LocalDateTime.now());
					usul.setApprove1_nama(user_nama);
					usulanRepository.save(usul);

//					if (jenis_id_loan.equals("R")) {
//						Loan loan = loanRepository.findByIdLoan(usul.getId_loan());
//						System.out.println("##### DATA LOAN ##### ");
//						loan.setStatus("11");
//						loanRepository.save(loan);
//					} else 
					if (jenis_id_loan.equals("U")) {
						Usulan old_usul = usulanRepository.findByIdLoan(usul.getId_loan());
						System.out.println("##### DATA USULAN LAMA ##### ");
						old_usul.setStatus("11");
						usulanRepository.save(old_usul);
					}

					response.put("kode", "00");
					response.put("pesan", "APPROVE BERHASIL");

				} else if (usul.getStatus().equals("5")) {
					response.put("kode", "16");
					response.put("pesan", "Oops.. Debitur belum di Review !!!");
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND APPROVE USULAN2 ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Transactional
	@PostMapping("/usulan1/notisi/approve3")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setApprove3(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND APPROVE USULAN 3 ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String cab = param.get("cab");
		String keputusan = param.get("keputusan");
		try {
			Usulan usul = usulanRepository.findByIdLoan(id_usulan);
			if (usul != null) {
				Optional<Par_cabang> datacab = par_cabangRepository.findById(cab);
				String jenis_id_loan = usul.getId_loan().substring(0, 1);
				String sts = usul.getStatus();
				String old_status = "";
				String msg = "APPROVE BERHASIL";
				String rc = "00";
				if (datacab.isPresent() && usul.getPlafon_pengajuan() <= datacab.get().getLimit_konsumtif()) {
					sts = "13";
					old_status = "13";
					rc = "00";
					msg = "APPROVE BERHASIL SILAHKAN LANJUTKAN PENCAIRAN";
				} else if (datacab.isPresent() && usul.getPlafon_pengajuan() > datacab.get().getLimit_konsumtif()) {
					// MELEBIHI LIMIT KANTOR
					if (datacab.get().getJenis_cab().equals("CABANG")) {
						sts = usul.getStatus();
						old_status = "7";
						rc = "10";
						msg = "TRANSAKSI MELEBIHI LIMIT CABANG, SILAHKAN DITERUSKAN KE KANTOR PUSAT";
					} else if (datacab.get().getJenis_cab().equals("DIVISI")) {
						sts = usul.getStatus();
						old_status = "7";
						rc = "10";
						msg = "TRANSAKSI MELEBIHI LIMIT DIVISI, SILAHKAN DITERUSKAN KE DIREKSI";
					}
				}

				if (usul.getStatus().equals("7") || usul.getStatus().equals("4")) {
					System.out.println("##### DATA LOAN ##### ");

					usul.setStatus(sts);
					usul.setApprove3_by(user_id);
					usul.setApprove3_date(LocalDateTime.now());
					usul.setApprove3_nama(user_nama);
					usul.setKeputusan(keputusan);
					usulanRepository.save(usul);

					if (jenis_id_loan.equals("R")) {
						// Loan loan = loanRepository.findByIdLoan(usul.getId_loan());
						System.out.println("##### DATA LOAN DIAPPROVE 3##### " + old_status + " " + usul.getId_loan());
						loanRepository.updateStatusUsulanLoan(old_status, usul.getId_loan());
						// loan.setStatus(old_status);
						// loanRepository.save(loan);
					} else if (jenis_id_loan.equals("U")) {
						Usulan old_usul = usulanRepository.findByIdLoan(usul.getId_loan());
						System.out.println("##### DATA USULAN LAMA ##### ");
						old_usul.setStatus(old_status);
						usulanRepository.save(old_usul);
					}

					response.put("kode", rc);
					response.put("pesan", msg);
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND APPROVE USULAN 3 ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/usulan1/notisi/forward")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setForward(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND FORWARD USULAN ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String id_usulan = param.get("id_usulan");
		String user_id = param.get("user_id");
		String user_nama = param.get("user_nama");
		String cab = param.get("cab");
		String forward_to = param.get("forward_to");
		try {
			Usulan data = usulanRepository.findByIdLoan(id_usulan);
			if (data != null) {
				String jenis_id_loan = data.getId_loan().substring(0, 1);
				String id_loan = data.getId_loan();
				System.out.println("##### ID LOAN RETRIEVE ##### " + id_loan);
				if (data.getStatus().equals("7")) {
					DateTimeFormatter formatteryear = DateTimeFormatter.ofPattern("yyyy");
					LocalDate year = LocalDate.now();

					String format_id_loan = "U." + cab + "." + year.format(formatteryear) + ".";
					String lastLoan = usulanRepository.getMaxLoan(format_id_loan, cab);
					Integer maxLoan = 1;
					if (lastLoan != null) {
						maxLoan = Integer.parseInt(lastLoan.substring(11, 18)) + 1;
					}
					String noReg = String.format("%7s", maxLoan).replace(' ', '0');
					String id_usul = format_id_loan + noReg;

					if (jenis_id_loan.equals("R")) {
						Loan loan = loanRepository.findByIdLoan(id_loan);
						System.out.println("##### DATA LOAN ##### ");
						Usulan usul = new Usulan();
						usul.setId(id_usul);
						usul.setId_debitur(loan.getId_debitur());
						usul.setId_kategori_produk(loan.getId_kategori_produk());
						usul.setId_plan(loan.getId_plan());
						usul.setPlafon_pengajuan(loan.getPlafon_pengajuan());
						usul.setTenor_pengajuan(loan.getTenor_pengajuan());
						usul.setMargin_pengajuan(loan.getMargin_pengajuan());
						usul.setAngsuran_pengajuan(loan.getAngsuran_pengajuan());
						usul.setTujuan_pembiayaan(loan.getTujuan_pembiayaan());
						usul.setId_loan(loan.getId());
						usul.setIdeb(loan.getIdeb());
						usul.setStatus("3");
						usul.setTgl_pengajuan(LocalDateTime.now());
						usul.setId_cab(forward_to);
						usul.setKuasa_pemotongan(loan.getKuasa_pemotongan());
						usul.setPersentase_kuasa_pemotongan(loan.getPersentase_kuasa_pemotongan());
						usul.setNilai_harta_thp_kredit(loan.getNilai_harta_thp_kredit());
						usul.setSaldo_rata_bulanan(loan.getSaldo_rata_bulanan());
						usul.setKode_referal(loan.getKode_referal());
						usul.setSlik_ref_pengguna(loan.getSlik_ref_pengguna());
						usul.setSlik_nomor_laporan(loan.getSlik_nomor_laporan());
						usul.setSlik_tgl_data_terakhir(loan.getSlik_tgl_data_terakhir());
						usul.setSlik_tgl_permintaan(loan.getSlik_tgl_permintaan());
						usul.setInput_by(user_id);
						usul.setInput_date(LocalDateTime.now());
						usul.setInput_nama(user_nama);
						usul.setRekomendasi(loan.getRekomendasi());
						usul.setKeputusan(loan.getKeputusan());
						usul.setHarga_perolehan(loan.getHarga_perolehan());
						usul.setPajak(loan.getPajak());
						usul.setDiskon(loan.getDiskon());
						usul.setUang_muka(loan.getUang_muka());
						usul.setBiaya_adm(loan.getBiaya_adm());
						usul.setBiaya_asuransi_jiwa(loan.getBiaya_asuransi_jiwa());
						usul.setBiaya_asuransi_kebakaran(loan.getBiaya_asuransi_kebakaran());
						usul.setBiaya_asuransi_pembiayaan(loan.getBiaya_asuransi_pembiayaan());
						usul.setIs_take_over(loan.getIs_take_over());
						usul.setTake_over_nama_bank(loan.getTake_over_nama_bank());
						usul.setTake_over_total(loan.getTake_over_total());
						usul.setNorek(loan.getNorek());
						usul.setNama_bank(loan.getNama_bank());
						usul.setAtas_nama(loan.getAtas_nama());
						usul.setStatus_rekening(loan.getStatus_rekening());
						usul.setTgl_kunjungan(loan.getTgl_kunjungan());
						usul.setOfficer_bank(loan.getOfficer_bank());
						usul.setKode_broker(loan.getKode_broker());
						usul.setKode_asuransi(loan.getKode_asuransi());
						usul.setBenefit_broker(loan.getBenefit_broker());
						usul.setBenefit_asuransi(loan.getBenefit_asuransi());
						usul.setPlafon_disetujui(loan.getPlafon_disetujui());
						usul.setTenor_disetujui(loan.getTenor_disetujui());
						usul.setAngsuran_disetujui(loan.getAngsuran_disetujui());
						usulanRepository.save(usul);
						loan.setStatus("8");
						loanRepository.save(loan);
					} else if (jenis_id_loan.equals("U")) {
						Usulan old_usul = usulanRepository.findByIdLoan(id_loan);
						System.out.println("##### DATA USULAN LAMA ##### ");
						Usulan usul = new Usulan();
						usul.setId(id_usul);
						usul.setId_debitur(old_usul.getId_debitur());
						usul.setId_kategori_produk(old_usul.getId_kategori_produk());
						usul.setId_plan(old_usul.getId_plan());
						usul.setPlafon_pengajuan(old_usul.getPlafon_pengajuan());
						usul.setTenor_pengajuan(old_usul.getTenor_pengajuan());
						usul.setMargin_pengajuan(old_usul.getMargin_pengajuan());
						usul.setAngsuran_pengajuan(old_usul.getAngsuran_pengajuan());
						usul.setTujuan_pembiayaan(old_usul.getTujuan_pembiayaan());
						usul.setId_loan(old_usul.getId_loan());
						usul.setIdeb(old_usul.getIdeb());
						usul.setStatus("3");
						usul.setTgl_pengajuan(LocalDateTime.now());
						usul.setId_cab(forward_to);
						usul.setKuasa_pemotongan(old_usul.getKuasa_pemotongan());
						usul.setPersentase_kuasa_pemotongan(old_usul.getPersentase_kuasa_pemotongan());
						usul.setNilai_harta_thp_kredit(old_usul.getNilai_harta_thp_kredit());
						usul.setSaldo_rata_bulanan(old_usul.getSaldo_rata_bulanan());
						usul.setKode_referal(old_usul.getKode_referal());
						usul.setSlik_ref_pengguna(old_usul.getSlik_ref_pengguna());
						usul.setSlik_nomor_laporan(old_usul.getSlik_nomor_laporan());
						usul.setSlik_tgl_data_terakhir(old_usul.getSlik_tgl_data_terakhir());
						usul.setSlik_tgl_permintaan(old_usul.getSlik_tgl_permintaan());
						usul.setInput_by(user_id);
						usul.setInput_date(LocalDateTime.now());
						usul.setInput_nama(user_nama);
						usul.setId_old_usulan(old_usul.getId());
						usul.setRekomendasi(old_usul.getRekomendasi());
						usul.setKeputusan(old_usul.getKeputusan());
						usul.setHarga_perolehan(old_usul.getHarga_perolehan());
						usul.setPajak(old_usul.getPajak());
						usul.setDiskon(old_usul.getDiskon());
						usul.setUang_muka(old_usul.getUang_muka());
						usul.setBiaya_adm(old_usul.getBiaya_adm());
						usul.setBiaya_asuransi_jiwa(old_usul.getBiaya_asuransi_jiwa());
						usul.setBiaya_asuransi_kebakaran(old_usul.getBiaya_asuransi_kebakaran());
						usul.setBiaya_asuransi_pembiayaan(old_usul.getBiaya_asuransi_pembiayaan());
						usul.setIs_take_over(old_usul.getIs_take_over());
						usul.setTake_over_nama_bank(old_usul.getTake_over_nama_bank());
						usul.setTake_over_total(old_usul.getTake_over_total());
						usul.setNorek(old_usul.getNorek());
						usul.setNama_bank(old_usul.getNama_bank());
						usul.setAtas_nama(old_usul.getAtas_nama());
						usul.setStatus_rekening(old_usul.getStatus_rekening());
						usul.setTgl_kunjungan(old_usul.getTgl_kunjungan());
						usul.setOfficer_bank(old_usul.getOfficer_bank());
						usul.setKode_broker(old_usul.getKode_broker());
						usul.setKode_asuransi(old_usul.getKode_asuransi());
						usul.setBenefit_broker(old_usul.getBenefit_broker());
						usul.setBenefit_asuransi(old_usul.getBenefit_asuransi());
						usul.setPlafon_disetujui(old_usul.getPlafon_disetujui());
						usul.setTenor_disetujui(old_usul.getTenor_disetujui());
						usul.setAngsuran_disetujui(old_usul.getAngsuran_disetujui());
						usulanRepository.save(usul);
						old_usul.setStatus("8");
						usulanRepository.save(old_usul);
					}

					String sts = "8";
					String msg = "FORWARD BERHASIL, MENUNGGU PERSETUJUAN";

					// SIMPAN KE DATA USULAN
					System.out.println("##### DATA LOAN ##### ");
					data.setStatus(sts);
					data.setApprove3_by(user_id);
					data.setApprove3_date(LocalDateTime.now());
					data.setApprove3_nama(user_nama);
					usulanRepository.save(data);

					response.put("kode", "00");
					response.put("pesan", msg);
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND FORWARD USULAN ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
