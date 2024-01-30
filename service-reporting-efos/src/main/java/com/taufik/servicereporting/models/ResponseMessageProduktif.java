package com.taufik.servicereporting.models;

import com.fasterxml.jackson.annotation.JsonInclude;
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

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessageProduktif {

	private String kode;
	private String pesan;	
	
	private List<M_realisasi_ao> kode_referal;
	private List<M_realisasi_ao_detail> kode_referal_detail;
	private List<M_realisasi_cabang> cabang;
	private List<M_realisasi_cabang_detail> cabang_detail;
	private List<M_realisasi_all> loan;
	private List<M_realisasi_all_detail> loan_detail;
	private List<M_realisasi_plan> plan;
	private List<M_realisasi_plan_detail> plan_detail;
	private List<M_realisasi_akad> akad;
	private List<M_realisasi_akad_detail> akad_detail;
	
	private Realisasi_qacaproduktif_rekap noa;
	private List<Realisasi_qacaproduktif_per_cabang> qaca_cabang;
	private List<Realisasi_qacaproduktif_detail> qaca_detail;
	private List<Realisasi_qacaproduktif_detail> qaca_cabang_detail;
	
	private List<M_proses_ao> proses_ao;
	private List<M_proses_admin> proses_admin;
	private List<Data_prospek> data_prospek;
	
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public String getPesan() {
		return pesan;
	}
	public void setPesan(String pesan) {
		this.pesan = pesan;
	}
	public Realisasi_qacaproduktif_rekap getNoa() {
		return noa;
	}
	public void setNoa(Realisasi_qacaproduktif_rekap noa) {
		this.noa = noa;
	}
	public List<Realisasi_qacaproduktif_per_cabang> getQaca_cabang() {
		return qaca_cabang;
	}
	public void setQaca_cabang(List<Realisasi_qacaproduktif_per_cabang> qaca_cabang) {
		this.qaca_cabang = qaca_cabang;
	}
	public List<Realisasi_qacaproduktif_detail> getQaca_detail() {
		return qaca_detail;
	}
	public void setQaca_detail(List<Realisasi_qacaproduktif_detail> qaca_detail) {
		this.qaca_detail = qaca_detail;
	}
	public List<Realisasi_qacaproduktif_detail> getQaca_cabang_detail() {
		return qaca_cabang_detail;
	}
	public void setQaca_cabang_detail(List<Realisasi_qacaproduktif_detail> qaca_cabang_detail) {
		this.qaca_cabang_detail = qaca_cabang_detail;
	}
	public List<M_realisasi_ao> getKode_referal() {
		return kode_referal;
	}
	public void setKode_referal(List<M_realisasi_ao> kode_referal) {
		this.kode_referal = kode_referal;
	}
	public List<M_realisasi_ao_detail> getKode_referal_detail() {
		return kode_referal_detail;
	}
	public void setKode_referal_detail(List<M_realisasi_ao_detail> kode_referal_detail) {
		this.kode_referal_detail = kode_referal_detail;
	}
	public List<M_realisasi_cabang> getCabang() {
		return cabang;
	}
	public void setCabang(List<M_realisasi_cabang> cabang) {
		this.cabang = cabang;
	}
	public List<M_realisasi_cabang_detail> getCabang_detail() {
		return cabang_detail;
	}
	public void setCabang_detail(List<M_realisasi_cabang_detail> cabang_detail) {
		this.cabang_detail = cabang_detail;
	}
	public List<M_realisasi_all> getLoan() {
		return loan;
	}
	public void setLoan(List<M_realisasi_all> loan) {
		this.loan = loan;
	}
	public List<M_realisasi_all_detail> getLoan_detail() {
		return loan_detail;
	}
	public void setLoan_detail(List<M_realisasi_all_detail> loan_detail) {
		this.loan_detail = loan_detail;
	}
	public List<M_realisasi_plan> getPlan() {
		return plan;
	}
	public void setPlan(List<M_realisasi_plan> plan) {
		this.plan = plan;
	}
	public List<M_realisasi_plan_detail> getPlan_detail() {
		return plan_detail;
	}
	public void setPlan_detail(List<M_realisasi_plan_detail> plan_detail) {
		this.plan_detail = plan_detail;
	}
	public List<M_realisasi_akad> getAkad() {
		return akad;
	}
	public void setAkad(List<M_realisasi_akad> akad) {
		this.akad = akad;
	}
	public List<M_realisasi_akad_detail> getAkad_detail() {
		return akad_detail;
	}
	public void setAkad_detail(List<M_realisasi_akad_detail> akad_detail) {
		this.akad_detail = akad_detail;
	}
	public List<M_proses_ao> getProses_ao() {
		return proses_ao;
	}
	public void setProses_ao(List<M_proses_ao> proses_ao) {
		this.proses_ao = proses_ao;
	}
	public List<M_proses_admin> getProses_admin() {
		return proses_admin;
	}
	public void setProses_admin(List<M_proses_admin> proses_admin) {
		this.proses_admin = proses_admin;
	}
	public List<Data_prospek> getData_prospek() {
		return data_prospek;
	}
	public void setData_prospek(List<Data_prospek> data_prospek) {
		this.data_prospek = data_prospek;
	}
	
}
