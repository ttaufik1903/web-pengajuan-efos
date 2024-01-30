package com.rabbai.serviceprospek.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbai.serviceprospek.models.Data_prospek;
import com.rabbai.serviceprospek.repository.DebiturRepository;
import com.rabbai.serviceprospek.repository.HartaRepository;
import com.rabbai.serviceprospek.repository.Hub_perbankan_detailRepository;
import com.rabbai.serviceprospek.repository.Par_rincian_scoringRepository;
import com.rabbai.serviceprospek.repository.PasanganRepository;
import com.rabbai.serviceprospek.repository.PekerjaanRepository;
import com.rabbai.serviceprospek.repository.ProspekRepository;
import com.rabbai.serviceprospek.repository.ScoringLoanRepository;
import com.rabbai.serviceprospek.services.CoreBankingService;
import com.rabbai.serviceprospek.services.MailService;

@RestController
public class ProspekMkmController {

	@Autowired
	MailService notificationService;

	@Autowired
	ProspekRepository prospekRepository;

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

	private static Logger LOGGER = Logger.getLogger(ProspekMkmController.class.getName());

	Page<Data_prospek> pageInsts;

	@PostMapping("/prospek/mkm/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listProspekMkm(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/prospek/list ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String keyword = param.get("keyword");
		int page = Integer.parseInt(param.get("page"));
		int size = Integer.parseInt(param.get("size"));
		Integer cab = Integer.valueOf(param.get("cab"));
		String jenis_pembiayaan = "MKM";
		String cab2 = param.get("cab").toString();

		try {
			List<Data_prospek> data = new ArrayList<Data_prospek>();
			Pageable paging = PageRequest.of(page, size);
			Integer filtered = 0;

			if (keyword != null) {

				if (cab2.equals("0000") || cab2.equals("001") || cab2.equals("002") || cab2.equals("003")
						|| cab2.equals("004") || cab2.equals("005") || cab2.equals("100")) {

					pageInsts = prospekRepository.findKeywordDebiturAllWithPagination(paging, keyword,
							jenis_pembiayaan);
					filtered = prospekRepository.getCountAll(jenis_pembiayaan);
				} else {
					pageInsts = prospekRepository.findKeywordDebiturWithPagination(paging, keyword, cab,
							jenis_pembiayaan);
					filtered = prospekRepository.getCount(cab, jenis_pembiayaan);
				}

			} else {
				if (cab2.equals("0000") || cab2.equals("001") || cab2.equals("002") || cab2.equals("003")
						|| cab2.equals("004") || cab2.equals("005") || cab2.equals("100")) {
					pageInsts = prospekRepository.findAllDebiturWithPaginationAll(paging,
							jenis_pembiayaan.toUpperCase());
					filtered = pageInsts.getNumberOfElements();
				} else {
					pageInsts = prospekRepository.findAllDebiturWithPagination(paging, cab, jenis_pembiayaan);
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
			LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/prospek/list ************** " + "@ "
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

	@PostMapping("/prospek/mkm/getbyid")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getProspek(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/prospek/getbyid ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String no_tiket = param.get("no_tiket");
		String jenis_pembiayaan = "MKM";
		try {
			Data_prospek prospek = prospekRepository.findByNoTiket(no_tiket, jenis_pembiayaan);
			if (prospek != null) {

				response.put("kode", "00");
				response.put("pesan", "BERHASIL");
				response.put("prospek", prospek);
			} else {
				response.put("kode", "10");
				response.put("pesan", "Oops.. No Tiket " + no_tiket + "  ini gak ada, coba yang lain ya !!!");

			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("kode", "05");
			response.put("pesan", "DATA TIDAK SESUAI");
		}
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/prospek/getbyid ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/prospek/mkm/setketerangan")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setAksi(@Valid @RequestBody Map<String, String> param,
			@AuthenticationPrincipal Jwt jwt)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("\n ************** REQUEST FROM FRONTEND /mkm/prospek/setketerangan ************** " + "@ "
				+ objectMapper.writeValueAsString(param) + " \n");
		Map<String, Object> response = new HashMap<>();
		String no_tiket = param.get("no_tiket");
		String user_id = param.get("user_id");
		String ket = param.get("ket");
		String jenis_pembiayaan = "MKM";
		try {
			Data_prospek prospek = prospekRepository.findByNoTiket(no_tiket, jenis_pembiayaan);

			if (prospek != null) {
				if (Integer.parseInt(prospek.getStatus()) == 0) {
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
		LOGGER.info("\n ************** RESPONSE TO FRONTEND /mkm/prospek/setketerangan ************** " + "@ "
				+ objectMapper.writeValueAsString(response) + " \n");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
