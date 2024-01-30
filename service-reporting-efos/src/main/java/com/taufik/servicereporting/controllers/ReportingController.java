package com.taufik.servicereporting.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taufik.servicereporting.models.Data_prospek;
import com.taufik.servicereporting.models.Proses_admin;
import com.taufik.servicereporting.models.Proses_ao;
import com.taufik.servicereporting.models.Realisasi_akad;
import com.taufik.servicereporting.models.Realisasi_akad_detail;
import com.taufik.servicereporting.models.Realisasi_all;
import com.taufik.servicereporting.models.Realisasi_all_detail;
import com.taufik.servicereporting.models.Realisasi_ao;
import com.taufik.servicereporting.models.Realisasi_ao_detail;
import com.taufik.servicereporting.models.Realisasi_cabang;
import com.taufik.servicereporting.models.Realisasi_cabang_detail;
import com.taufik.servicereporting.models.Realisasi_plan;
import com.taufik.servicereporting.models.Realisasi_plan_detail;
import com.taufik.servicereporting.models.Realisasi_qacakonsumtif_detail;
import com.taufik.servicereporting.models.Realisasi_qacakonsumtif_per_cabang;
import com.taufik.servicereporting.models.Realisasi_qacakonsumtif_rekap;
import com.taufik.servicereporting.models.Realisasi_qacaproduktif_detail;
import com.taufik.servicereporting.models.Realisasi_qacaproduktif_per_cabang;
import com.taufik.servicereporting.models.Realisasi_qacaproduktif_rekap;
import com.taufik.servicereporting.models.Reporting;
import com.taufik.servicereporting.models.Req_detail_akad;
import com.taufik.servicereporting.models.Req_detail_ao;
import com.taufik.servicereporting.models.Req_detail_cabang;
import com.taufik.servicereporting.models.Req_detail_plan;
import com.taufik.servicereporting.models.Req_qaca;
import com.taufik.servicereporting.models.Req_qaca_cab_detail;
import com.taufik.servicereporting.models.ResponseMessage;
import com.taufik.servicereporting.models.ResponseMessageProduktif;
import com.taufik.servicereporting.models.mkm.M_data_loan;
import com.taufik.servicereporting.models.mkm.M_par_akad_toloan;
import com.taufik.servicereporting.models.mkm.M_par_cabang;
import com.taufik.servicereporting.models.mkm.M_par_kode_referal;
import com.taufik.servicereporting.models.mkm.M_par_plan;
import com.taufik.servicereporting.models.mkm.M_proses_admin;
import com.taufik.servicereporting.models.mkm.M_proses_ao;
import com.taufik.servicereporting.models.mkm.M_realisasi_akad;
import com.taufik.servicereporting.models.mkm.M_realisasi_akad_detail;
import com.taufik.servicereporting.models.mkm.M_realisasi_all;
import com.taufik.servicereporting.models.mkm.M_realisasi_all_detail;
import com.taufik.servicereporting.models.mkm.M_realisasi_ao;
import com.taufik.servicereporting.models.mkm.M_realisasi_ao_detail;
import com.taufik.servicereporting.models.mkm.M_realisasi_cabang;
import com.taufik.servicereporting.models.mkm.M_realisasi_cabang_detail;
import com.taufik.servicereporting.models.mkm.M_realisasi_plan;
import com.taufik.servicereporting.models.mkm.M_realisasi_plan_detail;
import com.taufik.servicereporting.repository.Proses_adminRepository;
import com.taufik.servicereporting.repository.Proses_aoRepository;
import com.taufik.servicereporting.repository.ProspekRepository;
import com.taufik.servicereporting.repository.Realisasi_akadRepository;
import com.taufik.servicereporting.repository.Realisasi_akad_detailRepository;
import com.taufik.servicereporting.repository.Realisasi_allRepository;
import com.taufik.servicereporting.repository.Realisasi_all_detailRepository;
import com.taufik.servicereporting.repository.Realisasi_aoRepository;
import com.taufik.servicereporting.repository.Realisasi_ao_detailRepository;
import com.taufik.servicereporting.repository.Realisasi_cabangRepository;
import com.taufik.servicereporting.repository.Realisasi_cabang_detailRepository;
import com.taufik.servicereporting.repository.Realisasi_planRepository;
import com.taufik.servicereporting.repository.Realisasi_plan_detailRepository;
import com.taufik.servicereporting.repository.Realisasi_qacakonsumtif_allRepository;
import com.taufik.servicereporting.repository.Realisasi_qacakonsumtif_per_cabangRepository;
import com.taufik.servicereporting.repository.Realisasi_qacakonsumtif_rekapRepository;
import com.taufik.servicereporting.repository.Realisasi_qacaproduktif_allRepository;
import com.taufik.servicereporting.repository.Realisasi_qacaproduktif_per_cabangRepository;
import com.taufik.servicereporting.repository.Realisasi_qacaproduktif_rekapRepository;
import com.taufik.servicereporting.repository.mkm.M_data_loanRepository;
import com.taufik.servicereporting.repository.mkm.M_par_akadtoloanRepository;
import com.taufik.servicereporting.repository.mkm.M_par_cabangRepository;
import com.taufik.servicereporting.repository.mkm.M_par_kode_referalRepository;
import com.taufik.servicereporting.repository.mkm.M_par_planRepository;
import com.taufik.servicereporting.repository.mkm.M_proses_adminRepository;
import com.taufik.servicereporting.repository.mkm.M_proses_aoRepository;
import com.taufik.servicereporting.repository.mkm.M_realisasi_akadRepository;
import com.taufik.servicereporting.repository.mkm.M_realisasi_akad_detailRepository;
import com.taufik.servicereporting.repository.mkm.M_realisasi_allRepository;
import com.taufik.servicereporting.repository.mkm.M_realisasi_all_detailRepository;
import com.taufik.servicereporting.repository.mkm.M_realisasi_aoRepository;
import com.taufik.servicereporting.repository.mkm.M_realisasi_ao_detailRepository;
import com.taufik.servicereporting.repository.mkm.M_realisasi_cabangRepository;
import com.taufik.servicereporting.repository.mkm.M_realisasi_cabang_detailRepository;
import com.taufik.servicereporting.repository.mkm.M_realisasi_planRepository;
import com.taufik.servicereporting.repository.mkm.M_realisasi_plan_detailRepository;

@RestController
public class ReportingController {

	@Autowired
	M_data_loanRepository m_data_loan_repo;
	@Autowired
	M_par_cabangRepository m_cabang_repo;
	@Autowired
	M_par_kode_referalRepository m_kode_referalRepository;
	@Autowired
	M_par_planRepository m_plan_repo;
	@Autowired
	M_par_akadtoloanRepository m_akad_repo_toloan;
	@Autowired
	Realisasi_aoRepository realisasi_aoRepository;
	@Autowired
	Realisasi_ao_detailRepository realisasi_ao_detailRepository;
	@Autowired
	Realisasi_cabangRepository realisasi_cabangRepository;
	@Autowired
	Realisasi_cabang_detailRepository realisasi_cabang_detailRepository;
	@Autowired
	Realisasi_allRepository realisasi_allRepository;
	@Autowired
	Realisasi_all_detailRepository realisasi_all_detailRepository;
	@Autowired
	Realisasi_planRepository realisasi_planRepository;
	@Autowired
	Realisasi_plan_detailRepository realisasi_plan_detailRepository;
	@Autowired
	Realisasi_akadRepository realisasi_akadRepository;
	@Autowired
	Realisasi_akad_detailRepository realisasi_akad_detailRepository;
	@Autowired
	Realisasi_qacakonsumtif_rekapRepository realisasi_qacakonsumtif_rekapRepository;
	@Autowired
	Realisasi_qacakonsumtif_per_cabangRepository realisasi_qacakonsumtif_per_cabangRepository;
	@Autowired
	Realisasi_qacakonsumtif_allRepository realisasi_qacakonsumtif_allRepository;
	@Autowired
	Realisasi_qacaproduktif_rekapRepository realisasi_qacaproduktif_rekapRepository;
	@Autowired
	Realisasi_qacaproduktif_per_cabangRepository realisasi_qacaproduktif_per_cabangRepository;
	@Autowired
	Realisasi_qacaproduktif_allRepository realisasi_qacaproduktif_allRepository;
	@Autowired
	M_realisasi_aoRepository m_realisasi_aoRepository;
	@Autowired
	M_realisasi_ao_detailRepository m_realisasi_ao_detailRepository;
	@Autowired
	M_realisasi_cabangRepository m_realisasi_cabangRepository;
	@Autowired
	M_realisasi_cabang_detailRepository m_realisasi_cabang_detailRepository;
	@Autowired
	M_realisasi_allRepository m_realisasi_allRepository;
	@Autowired
	M_realisasi_all_detailRepository m_realisasi_all_detailRepository;
	@Autowired
	M_realisasi_planRepository m_realisasi_planRepository;
	@Autowired
	M_realisasi_plan_detailRepository m_realisasi_plan_detailRepository;
	@Autowired
	M_realisasi_akadRepository m_realisasi_akadRepository;
	@Autowired
	M_realisasi_akad_detailRepository m_realisasi_akad_detailRepository;
	@Autowired
	Proses_aoRepository proses_aoRepository;
	@Autowired
	Proses_adminRepository proses_adminRepository;
	@Autowired
	M_proses_aoRepository m_proses_aoRepository;
	@Autowired
	M_proses_adminRepository m_proses_adminRepository;
	@Autowired
	ProspekRepository prospek_Repository;
	List<Data_prospek> datas;

	@PostMapping("/reporting/laporan-pencairan-per-ao")
	@ResponseBody
	public ResponseMessage getLoans(@Valid @RequestBody Reporting report) {

		ResponseMessage res = new ResponseMessage();
		try {
//			List<Par_kode_referal> referals = kode_referalRepository.findAllByStatusLoan(report.getStatus(),
//					report.getTgl_awal(), report.getTgl_akhir());
			List<Realisasi_ao> referals = realisasi_aoRepository.findAllByStatusLoan(report.getTgl_awal(),
					report.getTgl_akhir());
			if (referals != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setKode_referal(referals);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-per-ao-detail")
	@ResponseBody
	public ResponseMessage getDetailAo(@Valid @RequestBody Req_detail_ao req) {

		ResponseMessage res = new ResponseMessage();
		try {
//			List<Par_kode_referal_detail> referal_details = kode_referal_detailRepository.findAllByStatusLoan(req.getKode_referal(), "20", "23",
//					req.getTgl_awal(), req.getTgl_akhir());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);
			List<Realisasi_ao_detail> referal_details = realisasi_ao_detailRepository
					.findAllByStatusLoan(req.getKode_referal(), tanggal1, tanggal2);

			if (referal_details != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
//				res.setKode_referal_detail(referal_details);
				res.setKode_referal_detail(referal_details);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-per-cabang")
	@ResponseBody
	public ResponseMessage getCabangs(@Valid @RequestBody Reporting report) {
		ResponseMessage res = new ResponseMessage();
		try {
//			List<Par_cabang> cabangs = cabang_repo.findAllByStatusLoan("20", report.getTgl_awal(), report.getTgl_akhir());
			List<Realisasi_cabang> cabangs = realisasi_cabangRepository.findAllByGroup(report.getTgl_awal(),
					report.getTgl_akhir());
			if (cabangs != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setCabang(cabangs);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-per-cabang-detail")
	@ResponseBody
	public ResponseMessage getDetailCabang(@Valid @RequestBody Req_detail_cabang req) {

		ResponseMessage res = new ResponseMessage();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);
			List<Realisasi_cabang_detail> cabang_details = realisasi_cabang_detailRepository
					.findAllByGroup(req.getKode_cabang(), tanggal1, tanggal2);

			if (cabang_details != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setCabang_detail(cabang_details);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-per-plan")
	@ResponseBody
	public ResponseMessage getPlans(@Valid @RequestBody Reporting report) {

		ResponseMessage res = new ResponseMessage();
		try {
			List<Realisasi_plan> plans = realisasi_planRepository.findAllByGroup(report.getTgl_awal(),
					report.getTgl_akhir());

			if (plans != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setPlan(plans);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;
	}

	@PostMapping("/reporting/laporan-pencairan-per-plan-detail")
	@ResponseBody
	public ResponseMessage getDetailPlan(@Valid @RequestBody Req_detail_plan req) {

		ResponseMessage res = new ResponseMessage();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			Integer id = Integer.parseInt(req.getKode_plan());
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);
			List<Realisasi_plan_detail> plan_details = realisasi_plan_detailRepository.findAllByGroup(id, tanggal1,
					tanggal2);

			if (plan_details != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setPlan_detail(plan_details);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-per-akad")
	@ResponseBody
	public ResponseMessage getAkad(@Valid @RequestBody Reporting report) {

		ResponseMessage res = new ResponseMessage();
		try {
			List<Realisasi_akad> akads = realisasi_akadRepository.findAllByGroup(report.getTgl_awal(),
					report.getTgl_akhir());

			if (akads != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setAkad(akads);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-per-akad-detail")
	@ResponseBody
	public ResponseMessage getDetailAkad(@Valid @RequestBody Req_detail_akad req) {

		ResponseMessage res = new ResponseMessage();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			Integer id = Integer.parseInt(req.getKode_akad());
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);
			List<Realisasi_akad_detail> akad_details = realisasi_akad_detailRepository.findAllByGroup(id, tanggal1,
					tanggal2);

			if (akad_details != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setAkad_detail(akad_details);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-rekap")
	@ResponseBody
	public ResponseMessage getRekap(@Valid @RequestBody Reporting report) {
		ResponseMessage res = new ResponseMessage();
		try {
			List<Realisasi_all> loans = realisasi_allRepository.findAllByGroup(report.getTgl_awal(),
					report.getTgl_akhir());
			if (loans != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setLoan(loans);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-rekap-all")
	@ResponseBody
	public ResponseMessage getRekap_all(@Valid @RequestBody Reporting report) {
		ResponseMessage res = new ResponseMessage();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate tanggal1 = LocalDate.parse(report.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(report.getTgl_akhir(), formatter);
			List<Realisasi_all_detail> loans = realisasi_all_detailRepository.findAllByGroup(tanggal1, tanggal2);
			if (loans != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setLoan_detail(loans);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/mkm/report/getall")
	@ResponseBody
	public ResponseMessage getall(@Valid @RequestBody Reporting report) {
		ResponseMessage res = new ResponseMessage();
		try {
			List<M_data_loan> loans = m_data_loan_repo.findByStatus("20", report.getTgl_awal(), report.getTgl_akhir());
			if (loans != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setM_data_loan(loans);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/mkm/report/getpercabang")
	@ResponseBody
	public ResponseMessage getpercabang(@Valid @RequestBody Reporting report) {
		ResponseMessage res = new ResponseMessage();
		try {
			List<M_par_cabang> cabangs = m_cabang_repo.findAllByStatusLoan("20", report.getTgl_awal(),
					report.getTgl_akhir());
			if (cabangs != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setM_cabang(cabangs);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/mkm/report/getperao")
	@ResponseBody
	public ResponseMessage getperao(@Valid @RequestBody Reporting report) {

		ResponseMessage res = new ResponseMessage();
		try {
			List<M_par_kode_referal> referals = m_kode_referalRepository.findAllByStatusLoan("20", report.getTgl_awal(),
					report.getTgl_akhir());
			if (referals != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setM_kode_referal(referals);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/mkm/report/getperplan")
	@ResponseBody
	public ResponseMessage getperplan(@Valid @RequestBody Reporting report) {

		ResponseMessage res = new ResponseMessage();
		try {
			List<M_par_plan> plans = m_plan_repo.findAllByStatusLoan("20", report.getTgl_awal(), report.getTgl_akhir());

			if (plans != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setM_plans(plans);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/mkm/report/getperakad")
	@ResponseBody
	public ResponseMessage getperakad(@Valid @RequestBody Reporting report) {

		ResponseMessage res = new ResponseMessage();
		try {
			List<M_par_akad_toloan> akads = m_akad_repo_toloan.findReportingByAkad("20", report.getTgl_awal(),
					report.getTgl_akhir());

			if (akads != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setM_akads(akads);
//				res.setAkadtoloan(akads);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-qacakonsumtif-rekap")
	@ResponseBody
	public ResponseMessage getRekapQacaKonsumtif(@Valid @RequestBody Req_qaca req) {
		ObjectMapper objectMapper = new ObjectMapper();

		ResponseMessage res = new ResponseMessage();
		try {
			System.out.println(
					"\n ************** REQUEST FROM FRONTEND /reporting/laporan-qacakonsumtif-rekap ************** "
							+ "@ " + objectMapper.writeValueAsString(req) + " \n");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String status_review = req.getStatus_review();
			Realisasi_qacakonsumtif_rekap qaca_rekap = null;
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);
			if (status_review.equals("2")) {
				qaca_rekap = realisasi_qacakonsumtif_rekapRepository.findAllNoa(tanggal1, tanggal2);
			} else if (status_review.equals("1")) {
				qaca_rekap = realisasi_qacakonsumtif_rekapRepository.findAllNoa(tanggal1, tanggal2, status_review);
			} else if (status_review.equals("0")) {
				qaca_rekap = realisasi_qacakonsumtif_rekapRepository.findAllNoaNull(tanggal1, tanggal2);
			}

			if (qaca_rekap != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setNoa(qaca_rekap);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-qacakonsumtif-per-cabang")
	@ResponseBody
	public ResponseMessage getqacakonsumtifpercabang(@Valid @RequestBody Req_qaca req) {
		ResponseMessage res = new ResponseMessage();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(
					"\n ************** REQUEST FROM FRONTEND /reporting/laporan-qacakonsumtif-per-cabang ************** "
							+ "@ " + objectMapper.writeValueAsString(req) + " \n");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String status_review = req.getStatus_review();
			List<Realisasi_qacakonsumtif_per_cabang> cabangs = new ArrayList<Realisasi_qacakonsumtif_per_cabang>();
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);

			if (status_review.equals("2")) {
				cabangs = realisasi_qacakonsumtif_per_cabangRepository.findAllByCabangNoStatus(tanggal1, tanggal2);
			} else if (status_review.equals("1")) {
				cabangs = realisasi_qacakonsumtif_per_cabangRepository.findAllByCabang(tanggal1, tanggal2,
						status_review);
			} else if (status_review.equals("0")) {
				cabangs = realisasi_qacakonsumtif_per_cabangRepository.findAllByCabangNull(tanggal1, tanggal2);
			}

			if (cabangs != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setQaca_cabang(cabangs);
				System.out.println(
						"\n ************** RESPONSE FROM FRONTEND /reporting/laporan-qacakonsumtif-per-cabang ************** "
								+ "@ " + objectMapper.writeValueAsString(res) + " \n");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}

		return res;

	}

	@PostMapping("/reporting/laporan-qacakonsumtif-all")
	@ResponseBody
	public ResponseMessage getqacakonsumtifAll(@Valid @RequestBody Req_qaca req) {
		ResponseMessage res = new ResponseMessage();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(
					"\n ************** REQUEST FROM FRONTEND /reporting/laporan-qacakonsumtif-all ************** "
							+ "@ " + objectMapper.writeValueAsString(req) + " \n");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String status_review = req.getStatus_review();
			List<Realisasi_qacakonsumtif_detail> all = null;
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);

			if (status_review.equals("2")) {
				all = realisasi_qacakonsumtif_allRepository.findAllByQaca(tanggal1, tanggal2);
			} else if (status_review.equals("1")) {
				all = realisasi_qacakonsumtif_allRepository.findAllByQaca(tanggal1, tanggal2, status_review);
			} else if (status_review.equals("0")) {
				all = realisasi_qacakonsumtif_allRepository.findAllByQacaNull(tanggal1, tanggal2);
			}

			if (all != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setQaca_detail(all);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-qacakonsumtif-per-cabang-detail")
	@ResponseBody
	public ResponseMessage getqacakonsumtifCabangDetail(@Valid @RequestBody Req_qaca_cab_detail req) {
		ResponseMessage res = new ResponseMessage();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(
					"\n ************** REQUEST FROM FRONTEND /reporting/laporan-qacakonsumtif-per-cabang-detail ************** "
							+ "@ " + objectMapper.writeValueAsString(req) + " \n");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String status_review = req.getStatus_review();
			String kode_cabang = req.getKode_cabang();
			List<Realisasi_qacakonsumtif_detail> all = null;
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);

			if (status_review.equals("2")) {
				all = realisasi_qacakonsumtif_allRepository.findAllByQacaCab(tanggal1, tanggal2, kode_cabang);
			} else if (status_review.equals("1")) {
				all = realisasi_qacakonsumtif_allRepository.findAllByQacaCab(tanggal1, tanggal2, status_review,
						kode_cabang);
			} else if (status_review.equals("0")) {
				all = realisasi_qacakonsumtif_allRepository.findAllByQacaCabNull(tanggal1, tanggal2, kode_cabang);
			}

			if (all != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setQaca_cabang_detail(all);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-qacaproduktif-rekap")
	@ResponseBody
	public ResponseMessageProduktif getRekapQacaproduktif(@Valid @RequestBody Req_qaca req) {
		ObjectMapper objectMapper = new ObjectMapper();

		ResponseMessageProduktif res = new ResponseMessageProduktif();
		try {
			System.out.println(
					"\n ************** REQUEST FROM FRONTEND /reporting/laporan-qacaproduktif-rekap ************** "
							+ "@ " + objectMapper.writeValueAsString(req) + " \n");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String status_review = req.getStatus_review();
			Realisasi_qacaproduktif_rekap qaca_rekap = null;
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);
			if (status_review.equals("2")) {
				qaca_rekap = realisasi_qacaproduktif_rekapRepository.findAllNoa(tanggal1, tanggal2);
			} else if (status_review.equals("1")) {
				qaca_rekap = realisasi_qacaproduktif_rekapRepository.findAllNoa(tanggal1, tanggal2, status_review);
			} else if (status_review.equals("0")) {
				qaca_rekap = realisasi_qacaproduktif_rekapRepository.findAllNoaNull(tanggal1, tanggal2);
			}

			if (qaca_rekap != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setNoa(qaca_rekap);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-qacaproduktif-per-cabang")
	@ResponseBody
	public ResponseMessageProduktif getqacaproduktifpercabang(@Valid @RequestBody Req_qaca req) {
		ResponseMessageProduktif res = new ResponseMessageProduktif();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(
					"\n ************** REQUEST FROM FRONTEND /reporting/laporan-qacaproduktif-per-cabang ************** "
							+ "@ " + objectMapper.writeValueAsString(req) + " \n");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String status_review = req.getStatus_review();
			List<Realisasi_qacaproduktif_per_cabang> cabangs = new ArrayList<Realisasi_qacaproduktif_per_cabang>();
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);

			if (status_review.equals("2")) {
				cabangs = realisasi_qacaproduktif_per_cabangRepository.findAllByCabangNoStatus(tanggal1, tanggal2);
			} else if (status_review.equals("1")) {
				cabangs = realisasi_qacaproduktif_per_cabangRepository.findAllByCabang(tanggal1, tanggal2,
						status_review);
			} else if (status_review.equals("0")) {
				cabangs = realisasi_qacaproduktif_per_cabangRepository.findAllByCabangNull(tanggal1, tanggal2);
			}

			if (cabangs != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setQaca_cabang(cabangs);
				System.out.println(
						"\n ************** RESPONSE FROM FRONTEND /reporting/laporan-qacaproduktif-per-cabang ************** "
								+ "@ " + objectMapper.writeValueAsString(res) + " \n");
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}

		return res;

	}

	@PostMapping("/reporting/laporan-qacaproduktif-all")
	@ResponseBody
	public ResponseMessageProduktif getqacaproduktifAll(@Valid @RequestBody Req_qaca req) {
		ResponseMessageProduktif res = new ResponseMessageProduktif();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(
					"\n ************** REQUEST FROM FRONTEND /reporting/laporan-qacaproduktif-all ************** "
							+ "@ " + objectMapper.writeValueAsString(req) + " \n");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String status_review = req.getStatus_review();
			List<Realisasi_qacaproduktif_detail> all = null;
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);

			if (status_review.equals("2")) {
				all = realisasi_qacaproduktif_allRepository.findAllByQaca(tanggal1, tanggal2);
			} else if (status_review.equals("1")) {
				all = realisasi_qacaproduktif_allRepository.findAllByQaca(tanggal1, tanggal2, status_review);
			} else if (status_review.equals("0")) {
				all = realisasi_qacaproduktif_allRepository.findAllByQacaNull(tanggal1, tanggal2);
			}

			if (all != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setQaca_detail(all);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-qacaproduktif-per-cabang-detail")
	@ResponseBody
	public ResponseMessageProduktif getqacaproduktifCabangDetail(@Valid @RequestBody Req_qaca_cab_detail req) {
		ResponseMessageProduktif res = new ResponseMessageProduktif();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(
					"\n ************** REQUEST FROM FRONTEND /reporting/laporan-qacaproduktif-per-cabang-detail ************** "
							+ "@ " + objectMapper.writeValueAsString(req) + " \n");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String status_review = req.getStatus_review();
			String kode_cabang = req.getKode_cabang();
			List<Realisasi_qacaproduktif_detail> all = null;
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);

			if (status_review.equals("2")) {
				all = realisasi_qacaproduktif_allRepository.findAllByQacaCab(tanggal1, tanggal2, kode_cabang);
			} else if (status_review.equals("1")) {
				all = realisasi_qacaproduktif_allRepository.findAllByQacaCab(tanggal1, tanggal2, status_review,
						kode_cabang);
			} else if (status_review.equals("0")) {
				all = realisasi_qacaproduktif_allRepository.findAllByQacaCabNull(tanggal1, tanggal2, kode_cabang);
			}

			if (all != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setQaca_cabang_detail(all);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-produktif-per-ao")
	@ResponseBody
	public ResponseMessageProduktif getMkmLoans(@Valid @RequestBody Reporting report) {

		ResponseMessageProduktif res = new ResponseMessageProduktif();
		try {
			List<M_realisasi_ao> referals = m_realisasi_aoRepository.findAllByStatusLoan(report.getTgl_awal(),
					report.getTgl_akhir());
			if (referals != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setKode_referal(referals);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-produktif-per-ao-detail")
	@ResponseBody
	public ResponseMessageProduktif getMkmDetailAo(@Valid @RequestBody Req_detail_ao req) {

		ResponseMessageProduktif res = new ResponseMessageProduktif();
		try {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);
			List<M_realisasi_ao_detail> referal_details = m_realisasi_ao_detailRepository
					.findAllByStatusLoan(req.getKode_referal(), tanggal1, tanggal2);

			if (referal_details != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setKode_referal_detail(referal_details);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-produktif-per-cabang")
	@ResponseBody
	public ResponseMessageProduktif getMkmCabangs(@Valid @RequestBody Reporting report) {
		ResponseMessageProduktif res = new ResponseMessageProduktif();
		try {
			List<M_realisasi_cabang> cabangs = m_realisasi_cabangRepository.findAllByGroup(report.getTgl_awal(),
					report.getTgl_akhir());
			if (cabangs != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setCabang(cabangs);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-produktif-per-cabang-detail")
	@ResponseBody
	public ResponseMessageProduktif getMkmDetailCabang(@Valid @RequestBody Req_detail_cabang req) {

		ResponseMessageProduktif res = new ResponseMessageProduktif();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);
			List<M_realisasi_cabang_detail> cabang_details = m_realisasi_cabang_detailRepository
					.findAllByGroup(req.getKode_cabang(), tanggal1, tanggal2);

			if (cabang_details != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setCabang_detail(cabang_details);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-produktif-per-plan")
	@ResponseBody
	public ResponseMessageProduktif getMkmPlans(@Valid @RequestBody Reporting report) {

		ResponseMessageProduktif res = new ResponseMessageProduktif();
		try {
			List<M_realisasi_plan> plans = m_realisasi_planRepository.findAllByGroup(report.getTgl_awal(),
					report.getTgl_akhir());

			if (plans != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setPlan(plans);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;
	}

	@PostMapping("/reporting/laporan-pencairan-produktif-per-plan-detail")
	@ResponseBody
	public ResponseMessageProduktif getMkmDetailPlan(@Valid @RequestBody Req_detail_plan req) {

		ResponseMessageProduktif res = new ResponseMessageProduktif();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			Integer id = Integer.parseInt(req.getKode_plan());
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);
			List<M_realisasi_plan_detail> plan_details = m_realisasi_plan_detailRepository.findAllByGroup(id, tanggal1,
					tanggal2);

			if (plan_details != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setPlan_detail(plan_details);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-produktif-per-akad")
	@ResponseBody
	public ResponseMessageProduktif getMkmAkad(@Valid @RequestBody Reporting report) {

		ResponseMessageProduktif res = new ResponseMessageProduktif();
		try {
			List<M_realisasi_akad> akads = m_realisasi_akadRepository.findAllByGroup(report.getTgl_awal(),
					report.getTgl_akhir());

			if (akads != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setAkad(akads);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-produktif-per-akad-detail")
	@ResponseBody
	public ResponseMessageProduktif getMkmDetailAkad(@Valid @RequestBody Req_detail_akad req) {

		ResponseMessageProduktif res = new ResponseMessageProduktif();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			Integer id = Integer.parseInt(req.getKode_akad());
			LocalDate tanggal1 = LocalDate.parse(req.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(req.getTgl_akhir(), formatter);
			List<M_realisasi_akad_detail> akad_details = m_realisasi_akad_detailRepository.findAllByGroup(id, tanggal1,
					tanggal2);

			if (akad_details != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setAkad_detail(akad_details);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-produktif-rekap")
	@ResponseBody
	public ResponseMessageProduktif getMkmRekap(@Valid @RequestBody Reporting report) {
		ResponseMessageProduktif res = new ResponseMessageProduktif();
		try {
			List<M_realisasi_all> loans = m_realisasi_allRepository.findAllByGroup(report.getTgl_awal(),
					report.getTgl_akhir());
			if (loans != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setLoan(loans);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-pencairan-produktif-rekap-all")
	@ResponseBody
	public ResponseMessageProduktif getMkmRekap_all(@Valid @RequestBody Reporting report) {
		ResponseMessageProduktif res = new ResponseMessageProduktif();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate tanggal1 = LocalDate.parse(report.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(report.getTgl_akhir(), formatter);
			List<M_realisasi_all_detail> loans = m_realisasi_all_detailRepository.findAllByGroup(tanggal1, tanggal2);
			if (loans != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setLoan_detail(loans);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/laporan-prospek-produktif")
	@ResponseBody
	public ResponseMessageProduktif getlaporanprospekproduktif(@Valid @RequestBody Reporting report) {
		ResponseMessageProduktif res = new ResponseMessageProduktif();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate tanggal1 = LocalDate.parse(report.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(report.getTgl_akhir(), formatter);
			Integer cab = Integer.valueOf(report.getCab());
			String cab2 = report.getCab().toString();

			if (cab2.equals("0000") || cab2.equals("001") || cab2.equals("002") || cab2.equals("003")
					|| cab2.equals("004") || cab2.equals("005") || cab2.equals("100")) {
				datas = prospek_Repository.findDataProspekForReportAll(tanggal1, tanggal2, "MKM");
			}else {
				datas = prospek_Repository.findDataProspekForReport(tanggal1, tanggal2, "MKM",cab);
 			}

			if (datas != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setData_prospek(datas);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}
	
	@PostMapping("/reporting/laporan-prospek-konsumtif")
	@ResponseBody
	public ResponseMessageProduktif reportinglaporanprospekkonsumtif(@Valid @RequestBody Reporting report) {
		ResponseMessageProduktif res = new ResponseMessageProduktif();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate tanggal1 = LocalDate.parse(report.getTgl_awal(), formatter);
			LocalDate tanggal2 = LocalDate.parse(report.getTgl_akhir(), formatter);
			Integer cab = Integer.valueOf(report.getCab());
			String cab2 = report.getCab().toString();

			if (cab2.equals("0000") || cab2.equals("001") || cab2.equals("002") || cab2.equals("003")
					|| cab2.equals("004") || cab2.equals("005") || cab2.equals("100")) {
				datas = prospek_Repository.findDataProspekForReportAll(tanggal1, tanggal2, "KONSUMER");
			}else {
				datas = prospek_Repository.findDataProspekForReport(tanggal1, tanggal2, "KONSUMER",cab);
 			}

			if (datas != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setData_prospek(datas);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/proses-ao")
	@ResponseBody
	public ResponseMessage getProsesAo(@Valid @RequestBody Reporting report) {

		ResponseMessage res = new ResponseMessage();
		try {
			List<Proses_ao> proses = proses_aoRepository.findAllByStatusLoan(report.getTgl_awal(),
					report.getTgl_akhir());
			if (proses != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setProses_ao(proses);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/proses-admin")
	@ResponseBody
	public ResponseMessage getProsesAdmin(@Valid @RequestBody Reporting report) {

		ResponseMessage res = new ResponseMessage();
		try {
			List<Proses_admin> proses = proses_adminRepository.findAllByStatusLoan(report.getTgl_awal(),
					report.getTgl_akhir());
			if (proses != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setProses_admin(proses);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/proses-ao-produktif")
	@ResponseBody
	public ResponseMessageProduktif getProsesAoMkm(@Valid @RequestBody Reporting report) {

		ResponseMessageProduktif res = new ResponseMessageProduktif();
		try {
			List<M_proses_ao> proses = m_proses_aoRepository.findAllByStatusLoan(report.getTgl_awal(),
					report.getTgl_akhir());
			if (proses != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setProses_ao(proses);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

	@PostMapping("/reporting/proses-admin-produktif")
	@ResponseBody
	public ResponseMessageProduktif getProsesAdminMkm(@Valid @RequestBody Reporting report) {

		ResponseMessageProduktif res = new ResponseMessageProduktif();
		try {
			List<M_proses_admin> proses = m_proses_adminRepository.findAllByStatusLoan(report.getTgl_awal(),
					report.getTgl_akhir());
			if (proses != null) {
				System.out.println("##### TAMPILKAN DATA ##### ");
				res.setKode("00");
				res.setPesan("Yes Berhasil View Data");
				res.setProses_admin(proses);
			} else {
				System.out.println("##### TIDAK ADA ##### ");
				res.setKode("05");
				res.setPesan("Oops.. Username yang ini gak ada, coba yang lain ya !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setKode("99");
			res.setPesan("Oops.. Sepertinya ada yang salah, coba lagi ya !!!");
		}
		return res;

	}

}
