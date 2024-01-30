package com.taufik.servicereporting.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taufik.servicereporting.models.mkm.M_data_loan;
import com.taufik.servicereporting.models.mkm.M_par_akad_toloan;
import com.taufik.servicereporting.models.mkm.M_par_cabang;
import com.taufik.servicereporting.models.mkm.M_par_kode_referal;
import com.taufik.servicereporting.models.mkm.M_par_plan;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage {

	private String kode;
	private String pesan;	
	private List<Realisasi_ao> kode_referal;
	private List<Realisasi_ao_detail> kode_referal_detail;
	private List<Realisasi_cabang> cabang;
	private List<Realisasi_cabang_detail> cabang_detail;
	private List<Realisasi_all> loan;
	private List<Realisasi_all_detail> loan_detail;
	private List<Realisasi_plan> plan;
	private List<Realisasi_plan_detail> plan_detail;
	private List<Realisasi_akad> akad;
	private List<Realisasi_akad_detail> akad_detail;
	private Realisasi_qacakonsumtif_rekap noa;
	private List<Realisasi_qacakonsumtif_per_cabang> qaca_cabang;
	private List<Realisasi_qacakonsumtif_detail> qaca_detail;
	private List<Realisasi_qacakonsumtif_detail> qaca_cabang_detail;
	
	private List<Proses_ao> proses_ao;
	private List<Proses_admin> proses_admin;
	
	private List<M_data_loan> m_data_loan;
	private List<M_par_kode_referal> m_kode_referal;
	private List<M_par_cabang> m_cabang;
	private List<M_par_plan> m_plans;
	private List<M_par_akad_toloan> m_akads;
	

	private String data;

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}


	public List<Realisasi_cabang> getCabang() {
		return cabang;
	}

	public void setCabang(List<Realisasi_cabang> cabang) {
		this.cabang = cabang;
	}

	public String getPesan() {
		return pesan;
	}

	public void setPesan(String pesan) {
		this.pesan = pesan;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}




	public List<M_data_loan> getM_data_loan() {
		return m_data_loan;
	}

	public void setM_data_loan(List<M_data_loan> m_data_loan) {
		this.m_data_loan = m_data_loan;
	}

	public List<M_par_cabang> getM_cabang() {
		return m_cabang;
	}

	public void setM_cabang(List<M_par_cabang> m_cabang) {
		this.m_cabang = m_cabang;
	}

	public List<M_par_kode_referal> getM_kode_referal() {
		return m_kode_referal;
	}

	public void setM_kode_referal(List<M_par_kode_referal> m_kode_referal) {
		this.m_kode_referal = m_kode_referal;
	}

	public List<M_par_plan> getM_plans() {
		return m_plans;
	}

	public void setM_plans(List<M_par_plan> m_plans) {
		this.m_plans = m_plans;
	}

	public List<M_par_akad_toloan> getM_akads() {
		return m_akads;
	}

	public void setM_akads(List<M_par_akad_toloan> m_akads) {
		this.m_akads = m_akads;
	}


	public List<Realisasi_ao> getKode_referal() {
		return kode_referal;
	}

	public void setKode_referal(List<Realisasi_ao> kode_referal) {
		this.kode_referal = kode_referal;
	}

	public List<Realisasi_ao_detail> getKode_referal_detail() {
		return kode_referal_detail;
	}

	public void setKode_referal_detail(List<Realisasi_ao_detail> kode_referal_detail) {
		this.kode_referal_detail = kode_referal_detail;
	}

	public List<Realisasi_cabang_detail> getCabang_detail() {
		return cabang_detail;
	}

	public void setCabang_detail(List<Realisasi_cabang_detail> cabang_detail) {
		this.cabang_detail = cabang_detail;
	}

	public List<Realisasi_all> getLoan() {
		return loan;
	}

	public void setLoan(List<Realisasi_all> loan) {
		this.loan = loan;
	}

	public List<Realisasi_all_detail> getLoan_detail() {
		return loan_detail;
	}

	public void setLoan_detail(List<Realisasi_all_detail> loan_detail) {
		this.loan_detail = loan_detail;
	}

	public List<Realisasi_plan> getPlan() {
		return plan;
	}

	public void setPlan(List<Realisasi_plan> plan) {
		this.plan = plan;
	}

	public List<Realisasi_plan_detail> getPlan_detail() {
		return plan_detail;
	}

	public void setPlan_detail(List<Realisasi_plan_detail> plan_detail) {
		this.plan_detail = plan_detail;
	}

	public List<Realisasi_akad> getAkad() {
		return akad;
	}

	public void setAkad(List<Realisasi_akad> akad) {
		this.akad = akad;
	}

	public List<Realisasi_akad_detail> getAkad_detail() {
		return akad_detail;
	}

	public void setAkad_detail(List<Realisasi_akad_detail> akad_detail) {
		this.akad_detail = akad_detail;
	}

	public Realisasi_qacakonsumtif_rekap getNoa() {
		return noa;
	}

	public void setNoa(Realisasi_qacakonsumtif_rekap noa) {
		this.noa = noa;
	}

	public List<Realisasi_qacakonsumtif_per_cabang> getQaca_cabang() {
		return qaca_cabang;
	}

	public void setQaca_cabang(List<Realisasi_qacakonsumtif_per_cabang> qaca_cabang) {
		this.qaca_cabang = qaca_cabang;
	}

	public List<Realisasi_qacakonsumtif_detail> getQaca_detail() {
		return qaca_detail;
	}

	public void setQaca_detail(List<Realisasi_qacakonsumtif_detail> qaca_detail) {
		this.qaca_detail = qaca_detail;
	}

	public List<Realisasi_qacakonsumtif_detail> getQaca_cabang_detail() {
		return qaca_cabang_detail;
	}

	public void setQaca_cabang_detail(List<Realisasi_qacakonsumtif_detail> qaca_cabang_detail) {
		this.qaca_cabang_detail = qaca_cabang_detail;
	}

	public List<Proses_ao> getProses_ao() {
		return proses_ao;
	}

	public void setProses_ao(List<Proses_ao> proses_ao) {
		this.proses_ao = proses_ao;
	}

	public List<Proses_admin> getProses_admin() {
		return proses_admin;
	}

	public void setProses_admin(List<Proses_admin> proses_admin) {
		this.proses_admin = proses_admin;
	}


}
