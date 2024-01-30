package com.taufik.servicereporting.models;

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
@Table(name = "view_qacaproduktif_report")
public class Realisasi_qacaproduktif_rekap {

	@Id
	private String noa;

	
	public String getNoa() {
		return noa;
	}
	public void setNoa(String noa) {
		this.noa = noa;
	}
}
