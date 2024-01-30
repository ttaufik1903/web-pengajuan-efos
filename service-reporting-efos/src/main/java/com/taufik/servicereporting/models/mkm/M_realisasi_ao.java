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
@Table(name = "view_realisasi_ao_mkm")
public class M_realisasi_ao {

	@Id
	private String id;
	private String name;
	private String id_cabang;
	private Double target_konsumer;
	private Double realisasi;
	
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
	

}
