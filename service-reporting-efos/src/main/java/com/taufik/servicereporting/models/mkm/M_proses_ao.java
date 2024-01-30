package com.taufik.servicereporting.models.mkm;

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
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "view_proses_ao_cabang_mkm")
public class M_proses_ao {

	@Id
	private String id;
	private String cabang;
	private String noa;
	private String realisasi;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCabang() {
		return cabang;
	}
	public void setCabang(String cabang) {
		this.cabang = cabang;
	}
	public String getNoa() {
		return noa;
	}
	public void setNoa(String noa) {
		this.noa = noa;
	}
	public String getRealisasi() {
		return realisasi;
	}
	public void setRealisasi(String realisasi) {
		this.realisasi = realisasi;
	}


}
