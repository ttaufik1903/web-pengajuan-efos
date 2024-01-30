package com.brk.servicepencairan.models.mkm;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "m_data_aspek_keuangan")
public class M_data_aspek_keuangan implements Serializable {

	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String id_loan;
	private String tahun;
	private Double kg_ha_thn;
	private Double kg_ha_bln;
	private Double luas_lahan_ha;
	private Double asumsi_harga;
	private Double pdptn_bln;
	private Double angsuran_bln;
	private Double by_pemeliharaan_bln;
	private Double sisa_pendapatan;
	private Double caoi;
	private Double persentase;
	
	
	public M_data_aspek_keuangan() {
		super();
	}
	
	public M_data_aspek_keuangan (String id_loan, String tahun, Double kg_ha_thn, Double kg_ha_bln, Double luas_lahan_ha, Double asumsi_harga, 
			Double pdptn_bln, Double angsuran_bln, Double by_pemeliharaan_bln, Double sisa_pendapatan, Double caoi, Double persentase) {
		
		this.id_loan=id_loan;
		this.tahun=tahun;
		this.kg_ha_thn=kg_ha_thn;
		this.kg_ha_bln=kg_ha_bln;
		this.luas_lahan_ha=luas_lahan_ha;
		this.asumsi_harga=asumsi_harga;
		this.pdptn_bln=pdptn_bln;
		this.angsuran_bln=angsuran_bln;
		this.by_pemeliharaan_bln=by_pemeliharaan_bln;
		this.sisa_pendapatan=sisa_pendapatan;
		this.caoi=caoi;
		this.persentase=persentase;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getId_loan() {
		return id_loan;
	}
	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}
	public String getTahun() {
		return tahun;
	}
	public void setTahun(String tahun) {
		this.tahun = tahun;
	}
	public Double getKg_ha_thn() {
		return kg_ha_thn;
	}
	public void setKg_ha_thn(Double kg_ha_thn) {
		this.kg_ha_thn = kg_ha_thn;
	}
	public Double getKg_ha_bln() {
		return kg_ha_bln;
	}
	public void setKg_ha_bln(Double kg_ha_bln) {
		this.kg_ha_bln = kg_ha_bln;
	}
	public Double getLuas_lahan_ha() {
		return luas_lahan_ha;
	}
	public void setLuas_lahan_ha(Double luas_lahan_ha) {
		this.luas_lahan_ha = luas_lahan_ha;
	}
	public Double getAsumsi_harga() {
		return asumsi_harga;
	}
	public void setAsumsi_harga(Double asumsi_harga) {
		this.asumsi_harga = asumsi_harga;
	}
	public Double getPdptn_bln() {
		return pdptn_bln;
	}
	public void setPdptn_bln(Double pdptn_bln) {
		this.pdptn_bln = pdptn_bln;
	}
	public Double getAngsuran_bln() {
		return angsuran_bln;
	}
	public void setAngsuran_bln(Double angsuran_bln) {
		this.angsuran_bln = angsuran_bln;
	}
	public Double getBy_pemeliharaan_bln() {
		return by_pemeliharaan_bln;
	}
	public void setBy_pemeliharaan_bln(Double by_pemeliharaan_bln) {
		this.by_pemeliharaan_bln = by_pemeliharaan_bln;
	}
	public Double getSisa_pendapatan() {
		return sisa_pendapatan;
	}
	public void setSisa_pendapatan(Double sisa_pendapatan) {
		this.sisa_pendapatan = sisa_pendapatan;
	}

	public Double getCaoi() {
		return caoi;
	}

	public void setCaoi(Double caoi) {
		this.caoi = caoi;
	}

	public Double getPersentase() {
		return persentase;
	}

	public void setPersentase(Double persentase) {
		this.persentase = persentase;
	}

}
