package com.taufik.servicereporting.models.mkm;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "view_realisasi_ao_mkm")
public class M_realisasi_ao_detail {

	@Id
	private String id_loan;
	private String id;
	private String name;
	private String id_cabang;
	private Double target_konsumer;
	private Double realisasi;
	private LocalDateTime approve_cair1_date;
	private String cif;
	private String no_telp;
	private String nama;
	private String tgl_lahir;
	private String id_plan;
	private String desc_id_plan;
	private String tenor_disetujui;
	private String plafon_disetujui;
	private String akad_date;
	private String margin_pengajuan;
	private String angsuran_disetujui;
	private String input_date;
	private String id_cab;
	private String cabang;
	private String cab_induk;
	private String norek_pencairan;
	private String sektor_ekonomi;
	private String desc_sektor_ekonomi;
	private String norek_loan;
	private String inisial;
	private String uang_muka;
	private String exp_rate;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId_cabang() {
		return id_cabang;
	}
	public void setId_cabang(String id_cabang) {
		this.id_cabang = id_cabang;
	}
	public Double getTarget_konsumer() {
		return target_konsumer;
	}
	public void setTarget_konsumer(Double target_konsumer) {
		this.target_konsumer = target_konsumer;
	}
	public Double getRealisasi() {
		return realisasi;
	}
	public void setRealisasi(Double realisasi) {
		this.realisasi = realisasi;
	}
	public LocalDateTime getApprove_cair1_date() {
		return approve_cair1_date;
	}
	public void setApprove_cair1_date(LocalDateTime approve_cair1_date) {
		this.approve_cair1_date = approve_cair1_date;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getNo_telp() {
		return no_telp;
	}
	public void setNo_telp(String no_telp) {
		this.no_telp = no_telp;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getTgl_lahir() {
		return tgl_lahir;
	}
	public void setTgl_lahir(String tgl_lahir) {
		this.tgl_lahir = tgl_lahir;
	}
	public String getId_plan() {
		return id_plan;
	}
	public void setId_plan(String id_plan) {
		this.id_plan = id_plan;
	}
	public String getDesc_id_plan() {
		return desc_id_plan;
	}
	public void setDesc_id_plan(String desc_id_plan) {
		this.desc_id_plan = desc_id_plan;
	}
	public String getTenor_disetujui() {
		return tenor_disetujui;
	}
	public void setTenor_disetujui(String tenor_disetujui) {
		this.tenor_disetujui = tenor_disetujui;
	}
	public String getPlafon_disetujui() {
		return plafon_disetujui;
	}
	public void setPlafon_disetujui(String plafon_disetujui) {
		this.plafon_disetujui = plafon_disetujui;
	}
	public String getAkad_date() {
		return akad_date;
	}
	public void setAkad_date(String akad_date) {
		this.akad_date = akad_date;
	}
	public String getMargin_pengajuan() {
		return margin_pengajuan;
	}
	public void setMargin_pengajuan(String margin_pengajuan) {
		this.margin_pengajuan = margin_pengajuan;
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
	public String getNorek_pencairan() {
		return norek_pencairan;
	}
	public void setNorek_pencairan(String norek_pencairan) {
		this.norek_pencairan = norek_pencairan;
	}
	public String getSektor_ekonomi() {
		return sektor_ekonomi;
	}
	public void setSektor_ekonomi(String sektor_ekonomi) {
		this.sektor_ekonomi = sektor_ekonomi;
	}
	public String getDesc_sektor_ekonomi() {
		return desc_sektor_ekonomi;
	}
	public void setDesc_sektor_ekonomi(String desc_sektor_ekonomi) {
		this.desc_sektor_ekonomi = desc_sektor_ekonomi;
	}
	public String getNorek_loan() {
		return norek_loan;
	}
	public void setNorek_loan(String norek_loan) {
		this.norek_loan = norek_loan;
	}
	public String getInisial() {
		return inisial;
	}
	public void setInisial(String inisial) {
		this.inisial = inisial;
	}
	public String getUang_muka() {
		return uang_muka;
	}
	public void setUang_muka(String uang_muka) {
		this.uang_muka = uang_muka;
	}
	public String getExp_rate() {
		return exp_rate;
	}
	public void setExp_rate(String exp_rate) {
		this.exp_rate = exp_rate;
	}
	public String getId_loan() {
		return id_loan;
	}
	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}
	

}
