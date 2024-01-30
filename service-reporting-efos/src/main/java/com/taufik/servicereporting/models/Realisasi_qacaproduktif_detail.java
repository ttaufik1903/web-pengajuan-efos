package com.taufik.servicereporting.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "view_qacaproduktif_report")
public class Realisasi_qacaproduktif_detail {

	@Id
	private String id;
	private String ktp;
	private String nama;
	private String tmp_lahir;
	private String tgl_lahir;
	private String id_cab;
	private String cabang;
	private String cab_induk;
	private String nama_cab_induk;
	private String id_plan;
	private String plan;
	private String plafon_disetujui;
	private String tenor_disetujui;
	private String angsuran_disetujui;
	private String input_date;
	private String approve_cair1_date;
	private String tgl_akhir_sla;
	private String qaca_review_date;
	private String status_qaca;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKtp() {
		return ktp;
	}
	public void setKtp(String ktp) {
		this.ktp = ktp;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getTmp_lahir() {
		return tmp_lahir;
	}
	public void setTmp_lahir(String tmp_lahir) {
		this.tmp_lahir = tmp_lahir;
	}
	public String getTgl_lahir() {
		return tgl_lahir;
	}
	public void setTgl_lahir(String tgl_lahir) {
		this.tgl_lahir = tgl_lahir;
	}
	public String getId_cab() {
		return id_cab;
	}
	public void setId_cab(String id_cab) {
		this.id_cab = id_cab;
	}
	public String getCabang() {
		return cabang;
	}
	public void setCabang(String cabang) {
		this.cabang = cabang;
	}
	public String getCab_induk() {
		return cab_induk;
	}
	public void setCab_induk(String cab_induk) {
		this.cab_induk = cab_induk;
	}
	public String getNama_cab_induk() {
		return nama_cab_induk;
	}
	public void setNama_cab_induk(String nama_cab_induk) {
		this.nama_cab_induk = nama_cab_induk;
	}
	public String getId_plan() {
		return id_plan;
	}
	public void setId_plan(String id_plan) {
		this.id_plan = id_plan;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getPlafon_disetujui() {
		return plafon_disetujui;
	}
	public void setPlafon_disetujui(String plafon_disetujui) {
		this.plafon_disetujui = plafon_disetujui;
	}
	public String getTenor_disetujui() {
		return tenor_disetujui;
	}
	public void setTenor_disetujui(String tenor_disetujui) {
		this.tenor_disetujui = tenor_disetujui;
	}
	public String getAngsuran_disetujui() {
		return angsuran_disetujui;
	}
	public void setAngsuran_disetujui(String angsuran_disetujui) {
		this.angsuran_disetujui = angsuran_disetujui;
	}
	public String getInput_date() {
		return input_date;
	}
	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}
	public String getApprove_cair1_date() {
		return approve_cair1_date;
	}
	public void setApprove_cair1_date(String approve_cair1_date) {
		this.approve_cair1_date = approve_cair1_date;
	}
	public String getTgl_akhir_sla() {
		return tgl_akhir_sla;
	}
	public void setTgl_akhir_sla(String tgl_akhir_sla) {
		this.tgl_akhir_sla = tgl_akhir_sla;
	}
	public String getQaca_review_date() {
		return qaca_review_date;
	}
	public void setQaca_review_date(String qaca_review_date) {
		this.qaca_review_date = qaca_review_date;
	}
	public String getStatus_qaca() {
		return status_qaca;
	}
	public void setStatus_qaca(String status_qaca) {
		this.status_qaca = status_qaca;
	}
	
}
