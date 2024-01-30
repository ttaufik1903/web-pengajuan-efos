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
@Table(name = "view_realisasi_plan_mkm")
public class M_realisasi_plan {

	@Id
	private String id;
	private String name;
	private String noa;
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
	public String getNoa() {
		return noa;
	}
	public void setNoa(String noa) {
		this.noa = noa;
	}
	public Double getRealisasi() {
		return realisasi;
	}
	public void setRealisasi(Double realisasi) {
		this.realisasi = realisasi;
	}
	

}
