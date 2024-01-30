package net.javaguides.springboot.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import net.javaguides.springboot.model.Kalkulator;
import net.javaguides.springboot.model.Pendaftaran;
import net.javaguides.springboot.service.ConfigService;
import net.javaguides.springboot.service.RecaptchaService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Controller
public class PendaftaranController {

	@Autowired
	RecaptchaService captchaService;

	ConfigService configService = new ConfigService();

	private final String url_servis_paramater = configService.url_servis_paramater;
	private final String url_servis_prospek = configService.url_servis_prospek;

	@GetMapping("/cekNomorTiket")
	public String cekNomorTiket(Model model)
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {

		model.addAttribute("pendaftaran", new Pendaftaran());

		return "form_cek_tiket";
	}

	@GetMapping("/detailkonsumer")
	public String detailkonsumer(Model model)
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {
		String deskripsi = "";

		return "form_deskripsi-konsumer";
	}

	@GetMapping("/detailmkm")
	public String detailmkm(Model model)
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {

		return "form_deskripsi-mkm";
	}

	@GetMapping("/kalkulator")
	public String kalkulator(Model model)
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {

		model.addAttribute("margin", 12);
		model.addAttribute("kalkulator", new Kalkulator());

		return "form_kalkulator";
	}

	@PostMapping("/prosesKalkulator")
	public String prosesKalkulator(HttpServletRequest request,
			@RequestParam(name = "g-recaptcha-response") String recaptchaResponse,
			@ModelAttribute Kalkulator kalkulator, Model model, RedirectAttributes redirAttrs) {

		String captcha = cekCaptcha(request, recaptchaResponse, redirAttrs);

		if (!captcha.isEmpty()) {
			redirAttrs.addFlashAttribute("harga_beli", kalkulator.getHarga_beli());
			redirAttrs.addFlashAttribute("jangka_waktu", kalkulator.getJangka_waktu());
			redirAttrs.addFlashAttribute("margin", kalkulator.getMargin());
			redirAttrs.addFlashAttribute("periode", kalkulator.getPeriode());
			redirAttrs.addFlashAttribute("uang_muka", kalkulator.getUang_muka());
			redirAttrs.addFlashAttribute("error", "Anda belum memilih Google Captcha");

			return "redirect:/kalkulator";
		}

		BigDecimal harga_beli = new BigDecimal(kalkulator.getHarga_beli().replace(",", ""));
		BigDecimal uang_muka = new BigDecimal(0);
		BigDecimal margin = new BigDecimal(kalkulator.getMargin()).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
		BigDecimal harga_jual = harga_beli
				.add(harga_beli.multiply(new BigDecimal(kalkulator.getJangka_waktu() / 12)).multiply(margin));
		BigDecimal jw = new BigDecimal(kalkulator.getJangka_waktu());
		BigDecimal inyear = new BigDecimal(kalkulator.getJangka_waktu() / 12);

		BigDecimal margin_total = margin.multiply(harga_beli).multiply(inyear);
		BigDecimal angsuran = harga_jual.divide(jw, 2, RoundingMode.HALF_UP);
		BigDecimal harga_angsur = harga_jual.subtract(uang_muka);

		redirAttrs.addFlashAttribute("harga_angsur", String.format("%,.2f", harga_angsur));

		redirAttrs.addFlashAttribute("harga_jual", String.format("%,.2f", harga_jual));
		redirAttrs.addFlashAttribute("harga_beli", String.format("%,.2f", new BigDecimal(kalkulator.getHarga_beli())) );
		redirAttrs.addFlashAttribute("total_margin", String.format("%,.2f", margin_total));
		redirAttrs.addFlashAttribute("harga_perolehan",String.format("%,.2f", new BigDecimal(kalkulator.getHarga_beli())) );
		redirAttrs.addFlashAttribute("rate_margin", margin);
		redirAttrs.addFlashAttribute("uang_muka",  kalkulator.getUang_muka());
		redirAttrs.addFlashAttribute("jangka_waktu", kalkulator.getJangka_waktu().toString().concat(" Bulan"));
		redirAttrs.addFlashAttribute("angsuran", String.format("%,.2f", angsuran));

		return "redirect:/detailKalkulator";

	}

	@PostMapping("/prosesKalkulator2")
	public String prosesKalkulator2(HttpServletRequest request,
			@RequestParam(name = "g-recaptcha-response") String recaptchaResponse,
			@ModelAttribute Kalkulator kalkulator, Model model, RedirectAttributes redirAttrs) {

		String captcha = cekCaptcha(request, recaptchaResponse, redirAttrs);

		if (!captcha.isEmpty()) {
			// redirAttrs.addFlashAttribute("harga_beli", kalkulator.getHarga_beli());
			redirAttrs.addFlashAttribute("jangka_waktu", kalkulator.getJangka_waktu());
			redirAttrs.addFlashAttribute("margin", kalkulator.getMargin());
			redirAttrs.addFlashAttribute("error", "Anda belum memilih Google Captcha");

			return "redirect:/kalkulator";
		}

		BigDecimal margin = new BigDecimal(kalkulator.getMargin()).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);

		BigDecimal jwm = new BigDecimal(kalkulator.getJangka_waktu());
		BigDecimal jwt = new BigDecimal(kalkulator.getJangka_waktu() / 12);
		System.out.println("######### TOTAL MARGIN ##############" + margin);
		// ######### TOTAL MARGIN ##############0.12

		BigDecimal p1 = new BigDecimal(kalkulator.getAngsuran()).divide(margin, 2, RoundingMode.HALF_UP);
		BigDecimal p2 = p1.divide(new BigDecimal(2));
		BigDecimal maksplafon = p1.add(p2);
		// ######### TOTAL ANGSURAN ##############0.12
		System.out.println("######### TOTAL ANGSURAN ##############" + margin);

		BigDecimal margin_total = margin.multiply(maksplafon).multiply(jwt).multiply(margin);
		BigDecimal harga_beli = maksplafon.subtract(margin_total);
		BigDecimal angsuran = new BigDecimal(kalkulator.getAngsuran());

		redirAttrs.addFlashAttribute("angsuran", String.format("%,.2f", angsuran));
		redirAttrs.addFlashAttribute("margin_total", String.format("%,.2f", margin_total));
		redirAttrs.addFlashAttribute("harga_jual", String.format("%,.2f", maksplafon));
		redirAttrs.addFlashAttribute("harga_beli", String.format("%,.2f", harga_beli));
		redirAttrs.addFlashAttribute("rate_margin", margin);
		redirAttrs.addFlashAttribute("jangka_waktu", kalkulator.getJangka_waktu());

		return "redirect:/detailKalkulator2";

	}

	@GetMapping("/detailKalkulator")
	public String detailKalkulator(HttpServletRequest request, RedirectAttributes redirAttrs) {
		return "detail_kalkulator";

	}

	@GetMapping("/detailKalkulator2")
	public String detailKalkulator2(HttpServletRequest request, RedirectAttributes redirAttrs) {
		return "detail_kalkulator2";

	}

	@PostMapping("/prosesNomorTiket")
	public String prosesNomorTiket(HttpServletRequest request,
			@RequestParam(name = "g-recaptcha-response") String recaptchaResponse,
			@ModelAttribute Pendaftaran pendaftaran, Model model, RedirectAttributes redirAttrs)
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {

		String captcha = cekCaptcha(request, recaptchaResponse, redirAttrs);
		String status_pernikahan, kelamin;

		if (!captcha.isEmpty()) {

			redirAttrs.addFlashAttribute("nomor_tiket", pendaftaran.getNomor_tiket());

			redirAttrs.addFlashAttribute("error", "Anda belum memilih Google Captcha");

			return "redirect:/cekNomorTiket";
		}

		String url = url_servis_prospek.concat("prospek/caritiket");
		RestTemplate restTemplate = new RestTemplate();

		JsonObject jData = new JsonObject();
		jData.addProperty("no_tiket", pendaftaran.getNomor_tiket());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + getToken());

		HttpEntity<String> entity = new HttpEntity<String>(jData.toString(), headers);
		String answer = restTemplate.postForObject(url, entity, String.class);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = mapper.readTree(answer);
		String response = actualObj.get("pesan").asText();
		String responseKode = actualObj.get("kode").asText();
		JsonNode actualObj2 = actualObj.get("dataPermohonan");

		if (responseKode.equals("00")) {
			redirAttrs.addFlashAttribute("no_tiket", actualObj2.get("no_tiket").asText());
			redirAttrs.addFlashAttribute("ktp", actualObj2.get("ktp").asText());
			redirAttrs.addFlashAttribute("nama", actualObj2.get("nama").asText());
			redirAttrs.addFlashAttribute("npwp", actualObj2.get("npwp").asText());
			if (actualObj2.get("status_pernikahan").asText().equals("B")) {
				status_pernikahan = "Belum Menikah";
			} else if (actualObj2.get("status_pernikahan").asText().equals("M")) {
				status_pernikahan = "Belum Menikah";
			} else {
				status_pernikahan = "Duda/Janda";
			}
			if (actualObj2.get("kelamin").asText().equals("0")) {
				kelamin = "Perempuan";
			} else {
				kelamin = "Laki-Laki";
			}
			redirAttrs.addFlashAttribute("status_pernikahan", status_pernikahan);
			redirAttrs.addFlashAttribute("tmp_lahir", actualObj2.get("tmp_lahir").asText());
			redirAttrs.addFlashAttribute("tgl_lahir", actualObj2.get("tgl_lahir").asText());
			redirAttrs.addFlashAttribute("alamat", actualObj2.get("alamat").asText());
			redirAttrs.addFlashAttribute("no_telp", actualObj2.get("no_telp").asText());
			redirAttrs.addFlashAttribute("kelamin", kelamin);
			redirAttrs.addFlashAttribute("email", actualObj2.get("email").asText());

			redirAttrs.addFlashAttribute("tujuan_pembiayaan", actualObj2.get("tujuan_pembiayaan").asText());

			redirAttrs.addFlashAttribute("plafon_pengajuan",
					String.format("%,.2f", new BigDecimal(actualObj2.get("plafon_pengajuan").asText())));

			redirAttrs.addFlashAttribute("tenor_pengajuan", actualObj2.get("tenor_pengajuan").asText().toString());

			redirAttrs.addFlashAttribute("success",
					response + " " + "(" + "Nomor Tiket : " + pendaftaran.getNomor_tiket() + ")");
		} else {
			redirAttrs.addFlashAttribute("error", response);
		}
		redirAttrs.addFlashAttribute("nomor_tiket", pendaftaran.getNomor_tiket());

		return "redirect:/cekNomorTiket";

	}

	@GetMapping("/formPengajuan")
	public String viewHomePage(Model model)
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {

		RestTemplate restTemplate = new RestTemplate();
		model.addAttribute("listcabang", (JSONArray) getCabangs());
		model.addAttribute("pendaftaran", new Pendaftaran());

		return "form_pengajuan";
	}

	@GetMapping("/formPengajuanFinal")
	public String formPengajuanFinal(Model model)
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {

		model.addAttribute("listkota", (JSONArray) getWilayahKabupaten());
		model.addAttribute("listtemplate", (JSONArray) getJenisPembiayaan());
		model.addAttribute("listtujuanpembiayaan", (JSONArray) getTujuanPembiayaan());
		model.addAttribute("pendaftaran", new Pendaftaran());

		return "form_pengajuan_final";
	}

	@PostMapping("/api/getCabangBywilayah/")
	@ResponseBody
	public JSONArray getCabangBywilayah(
			@Valid @org.springframework.web.bind.annotation.RequestBody Map<String, String> param)
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {
		System.out.println("####### KOTA YANG DIPILIH ######" + param.get("kota").toString());
		JSONArray responseMessage = getCabangByWilayahKabupaten(param.get("kota").toString());
		return responseMessage;
	}

	@ResponseBody
	@GetMapping("/api/getTujuanPembiyaan/{jenis}")
	public String searchResults(@PathVariable String jenis)
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {
		System.out.println("#######JENIS PBY######" + jenis);
		String responseMessage = getTujuanPembiayaan().toJSONString();
		return responseMessage;
	}

	@GetMapping("/formPengajuanEntry")
	public String formPengajuanEntry(Model model)
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {

//		RestTemplate restTemplate = new RestTemplate();
//		String url = "http://172.100.201.10:8041/parameter/cabang/inquiryall";
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.set("Authorization", "Bearer " + getToken());
//
//		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
//
//		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
//
//		JSONParser jsonParser = new JSONParser();
//		JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
//		String value = (String) jsonObject.get("pesan");
//		JSONArray jsonArray = (JSONArray) jsonObject.get("cabang");
		model.addAttribute("listcabang", (JSONArray) getCabangs());
		// model.addAttribute("listcabang", jsonArray);
		model.addAttribute("pendaftaran", new Pendaftaran());

		return "form_pengajuan_entry";
	}

	@GetMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
	public String sayHello(RedirectAttributes redirAttrs)
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {

		RestTemplate restTemplate = new RestTemplate();
		String url = "http://172.100.201.71:8041/parameter/cabang/inquiryall";
		// String url = "https://reqbin.com/echo/get/json";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + getToken());

		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
		String value = (String) jsonObject.get("pesan");
		JSONArray jsonArray = (JSONArray) jsonObject.get("cabang");

		redirAttrs.addFlashAttribute("success", jsonArray.toString());

		return "redirect:/";
	}

	@GetMapping(path = "/api/searchnasabah/{nomor_rekening}")
	@ResponseBody
	public Map<String, Object> searchnasabah(@PathVariable String nomor_rekening)
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {

		RestTemplate restTemplate = new RestTemplate();
		String url = "http://172.100.201.71:8041/efos/findnasabahbvbyrekening/" + "0" + nomor_rekening;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + getToken());

		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = mapper.readTree(response.getBody().toString());
		String nama = actualObj.get("dataNasabah").get("nama").asText();
		String npwp = actualObj.get("dataNasabah").get("npwp").asText();
		String ktp = actualObj.get("dataNasabah").get("ktp").asText();
		String tempat_lahir = actualObj.get("dataNasabah").get("tempat_lahir").asText();
		String tanggal_lahir = actualObj.get("dataNasabah").get("tanggal_lahir").asText();
		String status_nikah = actualObj.get("dataNasabah").get("status_nikah").asText();
		String alamat = actualObj.get("dataNasabah").get("alamat").asText();
		String jenis_kelamin = actualObj.get("dataNasabah").get("jenis_kelamin").asText();
		String email = actualObj.get("dataNasabah").get("email").asText();
		String nomor_hp = actualObj.get("dataNasabah").get("nomor_hp").asText();
		String rcode = actualObj.get("dataNasabah").get("rcode").asText();

		Map<String, Object> response2 = new HashMap<>();
		response2.put("ktp", ktp);
		response2.put("npwp", npwp);
		response2.put("nama", nama);
		response2.put("tempat_lahir", tempat_lahir);
		response2.put("tanggal_lahir", tanggal_lahir);
		response2.put("status_nikah", status_nikah);
		response2.put("alamat", alamat);
		response2.put("jenis_kelamin", jenis_kelamin);
		response2.put("email", email);
		response2.put("nomor_hp", nomor_hp);
		response2.put("rcode", rcode);

		System.out.println("############ RESPON GET NASABAH ##########" + response2);
		return response2;
	}

	@PostMapping("/savePendaftaran")
	public String savePendaftaran(HttpServletRequest request,
			@RequestParam(name = "g-recaptcha-response") String recaptchaResponse,
			@ModelAttribute Pendaftaran pendaftaran, Model model, RedirectAttributes redirAttrs)
			throws JsonMappingException, JsonProcessingException, ParseException {

		String captcha = cekCaptcha(request, recaptchaResponse, redirAttrs);

		@SuppressWarnings("deprecation")
		Date date = new Date(pendaftaran.getTgl_lahir().toString());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String tglLahir2 = formatter.format(date);
		String[] parts = pendaftaran.getCab().split("-");
		System.out.println("##### SPLIT KODE CABANG #######" + pendaftaran.getCab());
		String kodeCabang = parts[0]; // 004
		String namaCabang = parts[1]; // 034556
		System.out.println("####### SPILIT jenis_pembiayaan_spilit  #####" + pendaftaran.getJenis_pembiayaan());
		String[] jenis_pembiayaan_spilit = pendaftaran.getJenis_pembiayaan().split("-");
		String id_jenis_pembaiayaan = jenis_pembiayaan_spilit[0];
		String nama_jenis_pembaiayaan = jenis_pembiayaan_spilit[1];


		String parts1_tujuan_pembiayaan = pendaftaran.getTujuan_pembiayaan();

		if (!captcha.isEmpty()) {

			System.out.println("####### KODE CABANG DARI FORM#####" + pendaftaran.getCab());

			redirAttrs.addFlashAttribute("ktp", pendaftaran.getKtp());
			redirAttrs.addFlashAttribute("nama", pendaftaran.getNama());
			redirAttrs.addFlashAttribute("npwp", pendaftaran.getNpwp());
			redirAttrs.addFlashAttribute("status_pernikahan", pendaftaran.getStatus_pernikahan());
			redirAttrs.addFlashAttribute("tmp_lahir", pendaftaran.getTmp_lahir());
			redirAttrs.addFlashAttribute("tgl_lahir", tglLahir2);
			redirAttrs.addFlashAttribute("kelamin", pendaftaran.getKelamin());
			redirAttrs.addFlashAttribute("alamat", pendaftaran.getAlamat());
			redirAttrs.addFlashAttribute("kodeCabang", kodeCabang);
			redirAttrs.addFlashAttribute("namaCabang", namaCabang);
			redirAttrs.addFlashAttribute("jenis_pembiayaan", nama_jenis_pembaiayaan);

			redirAttrs.addFlashAttribute("no_telp", pendaftaran.getNo_telp());
			redirAttrs.addFlashAttribute("email", pendaftaran.getEmail());
			redirAttrs.addFlashAttribute("kode_tujuan_pembiayaan", parts1_tujuan_pembiayaan);
			redirAttrs.addFlashAttribute("nama_tujuan_pembiayaan", parts1_tujuan_pembiayaan);
			redirAttrs.addFlashAttribute("error", "Anda belum memilih Google Captcha");
			redirAttrs.addFlashAttribute("tenor_pengajuan", pendaftaran.getTenor_pengajuan());
			redirAttrs.addFlashAttribute("plafon_pengajuan", pendaftaran.getPlafon_pengajuan());

			return "redirect:/formPengajuanFinal";
		}

		String accessToken = getToken();

		RestTemplate restTemplate = new RestTemplate();

		System.out.println(pendaftaran.toString());

		System.out.println("#####ktp######" + pendaftaran.getKtp());
		System.out.println("#####NAMA######" + pendaftaran.getNama());
		System.out.println("#####ALAMAT######" + pendaftaran.getAlamat());
		System.out.println("#####NPWP######" + pendaftaran.getNpwp());
		System.out.println("#####STATUS NIKAH######" + pendaftaran.getStatus_pernikahan());

		@SuppressWarnings("deprecation")
		java.util.Date date2 = new Date(pendaftaran.getTgl_lahir().toString());
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		String tglLahir = formatter2.format(date2);
		System.out.println("#####STGL LAHIR######" + tglLahir);
		System.out.println("#####TEMPAT LAHIR######" + pendaftaran.getTmp_lahir());
		System.out.println("#####JEKEL######" + pendaftaran.getKelamin());
		System.out.println("#####CAB######" + pendaftaran.getCab());
		System.out.println("#####TELEPON######" + pendaftaran.getNo_telp());
		System.out.println("#####EMAIL######" + pendaftaran.getEmail());
		System.out.println("#####TUJUAN PBY######" + pendaftaran.getTujuan_pembiayaan());
		String[] parts1 = pendaftaran.getCab().split("-");
		String koCab = parts1[0];

		JsonObject jData = new JsonObject();
		jData.addProperty("ktp", pendaftaran.getKtp());
		jData.addProperty("nama", pendaftaran.getNama());
		jData.addProperty("npwp", pendaftaran.getNpwp());
		jData.addProperty("status_pernikahan", pendaftaran.getStatus_pernikahan());
		jData.addProperty("tmp_lahir", pendaftaran.getTmp_lahir());
		jData.addProperty("tgl_lahir", tglLahir.toString());
		jData.addProperty("kelamin", pendaftaran.getKelamin());
		jData.addProperty("alamat", pendaftaran.getAlamat());
		jData.addProperty("cab", koCab);
		jData.addProperty("no_telp", pendaftaran.getNo_telp());
		jData.addProperty("email", pendaftaran.getEmail());
		jData.addProperty("pekerjaan", pendaftaran.getPekerjaan());
		jData.addProperty("penghasilan", pendaftaran.getJumlah_penghasilan());

		if (parts1_tujuan_pembiayaan.equals("Lainnya")) {
			jData.addProperty("tujuan_pembiayaan", pendaftaran.getTujuan_pembiayaan_lainnya());
		} else {
			jData.addProperty("tujuan_pembiayaan", parts1_tujuan_pembiayaan);
		}
		jData.addProperty("plafon_pengajuan", pendaftaran.getPlafon_pengajuan().replace(",", ""));
		jData.addProperty("penghasilan", pendaftaran.getJumlah_penghasilan().replace(",", ""));

		jData.addProperty("tenor_pengajuan", pendaftaran.getTenor_pengajuan());
		
		jData.addProperty("jenis_pembiayaan", nama_jenis_pembaiayaan);
		jData.addProperty("id_jenis_pembiayaan", id_jenis_pembaiayaan);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + accessToken);
		String url = url_servis_prospek.concat("prospek/pengajuan");

		HttpEntity<String> entity = new HttpEntity<String>(jData.toString(), headers);
		String answer = restTemplate.postForObject(url, entity, String.class);

		System.out.println("#####REQUEST######");
		System.out.println(jData);
		System.out.println("#####RESPONSE######");
		System.out.println(answer);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = mapper.readTree(answer);
		String response = actualObj.get("pesan").asText();
		String responseKode = actualObj.get("kode").asText();
		if (responseKode.equals("00")) {
			redirAttrs.addFlashAttribute("success", response);
		} else {
			redirAttrs.addFlashAttribute("ktp", pendaftaran.getKtp());
			redirAttrs.addFlashAttribute("nama", pendaftaran.getNama());
			redirAttrs.addFlashAttribute("npwp", pendaftaran.getNpwp());
			redirAttrs.addFlashAttribute("status_pernikahan", pendaftaran.getStatus_pernikahan());
			redirAttrs.addFlashAttribute("tmp_lahir", pendaftaran.getTmp_lahir());
			redirAttrs.addFlashAttribute("tgl_lahir", tglLahir2);
			redirAttrs.addFlashAttribute("kelamin", pendaftaran.getKelamin());
			redirAttrs.addFlashAttribute("alamat", pendaftaran.getAlamat());
			redirAttrs.addFlashAttribute("kodeCabang", kodeCabang);
			redirAttrs.addFlashAttribute("namaCabang", namaCabang);
			redirAttrs.addFlashAttribute("no_telp", pendaftaran.getNo_telp());
			redirAttrs.addFlashAttribute("email", pendaftaran.getEmail());
			redirAttrs.addFlashAttribute("kode_tujuan_pembiayaan", parts1_tujuan_pembiayaan);
			redirAttrs.addFlashAttribute("nama_tujuan_pembiayaan", parts1_tujuan_pembiayaan);
			redirAttrs.addFlashAttribute("error", "Anda belum memilih Google Captcha");
			redirAttrs.addFlashAttribute("tenor_pengajuan", pendaftaran.getTenor_pengajuan());
			redirAttrs.addFlashAttribute("plafon_pengajuan", pendaftaran.getPlafon_pengajuan());
			redirAttrs.addFlashAttribute("error", response);
		}

		return "redirect:/formPengajuanFinal";
	}

	public String cekCaptcha(HttpServletRequest request,
			@RequestParam(name = "g-recaptcha-response") String recaptchaResponse, RedirectAttributes redirAttrs) {
		String ip = request.getRemoteAddr();
		String captchaVerifyMessage = captchaService.verifyRecaptcha(ip, recaptchaResponse);

		System.out.println("#######CAPTCHA RESPONSE#####" + captchaVerifyMessage);

		if (StringUtils.isNotEmpty(captchaVerifyMessage)) {

			return captchaVerifyMessage;

		} else {
			return "";

		}
	}

	public String getToken() throws JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();

 		String url = "http://172.100.201.71:9090/auth/realms/efos/protocol/openid-connect/token";
		String req = "grant_type=password&client_id=efos&client_secret=ee78b406-7cfe-4339-8249-cebde1ef450c&username=admin&password=P@ssw0rd";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<String> entity = new HttpEntity<String>(req, headers);
		String answer = restTemplate.postForObject(url, entity, String.class);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = mapper.readTree(answer);

		// When
		String token = actualObj.get("access_token").asText();

		System.out.println("#####REQUEST######");
		System.out.println(req);
		System.out.println("#####RESPONSE######");
		System.out.println(answer);
		System.out.println("#####TOKEN######");
		System.out.println(token);

		return token;

	}

	public JSONArray getTujuanPembiayaan()
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {

		RestTemplate restTemplate = new RestTemplate();
		String url = url_servis_paramater.concat("parameter/tujuanpembiayaan/inquiryalldata");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + getToken());

		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
		JSONArray jsonArray = (JSONArray) jsonObject.get("tujuanpembiayaan");

		return jsonArray;

	}

	public JSONArray getCabangs()
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {

		RestTemplate restTemplate = new RestTemplate();
		String url = url_servis_paramater.concat("parameter/cabang/inquiryall");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + getToken());

		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
		JSONArray listCabang = (JSONArray) jsonObject.get("cabang");

		return listCabang;

	}

	public JSONArray getWilayahKabupaten()
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {

		RestTemplate restTemplate = new RestTemplate();
		String url = url_servis_paramater.concat("parameter/listkabupatencabang");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + getToken());

		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
		JSONArray listCabang = (JSONArray) jsonObject.get("kota");

		return listCabang;

	}
	
	 
	public JSONArray getJenisPembiayaan()
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {

		String url = url_servis_paramater.concat("/parameter/jenispembiayaan/list");

		JsonObject dJson = new JsonObject();
 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + getToken());
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> httpEntity = new HttpEntity<>(dJson.toString(), headers);
		
		ResponseEntity<String> hasil = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

		
		//ResponseEntity<String> hasil = restTemplate.postForEntity(url, httpEntity, String.class);
		 
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(hasil.getBody());
		JSONArray listTemplate = (JSONArray) jsonObject.get("listTemplate");
		

		return listTemplate;

	}
	
	
	public JSONArray getCabangByWilayahKabupaten(String kota)
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException {

		String url = url_servis_paramater.concat("parameter/cabang/listcabangbywilayah");

		JsonObject dJson = new JsonObject();
		dJson.addProperty("kota", kota);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + getToken());
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> httpEntity = new HttpEntity<>(dJson.toString(), headers);
		ResponseEntity<String> hasil = restTemplate.postForEntity(url, httpEntity, String.class);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(hasil.getBody());
		JSONArray listCabang = (JSONArray) jsonObject.get("cabang");

		return listCabang;

	}

}
