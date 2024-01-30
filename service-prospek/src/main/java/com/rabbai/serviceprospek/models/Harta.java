package com.rabbai.serviceprospek.models;

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

@Entity
@Table(name = "data_harta_i")
public class Harta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer urut;
	private String id_loan;
	private String jenis;
	private Double perkiraan_nilai;
	private String keterangan;
	

	
	// PARENT PAR JENIS AGUNAN
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "jenis", insertable = false, updatable = false)
		private Par_jns_agunan jns_agunan;

		@JsonProperty("desc_jenis")
		public String getDesc_jenis() {
			if (jns_agunan == null) {
				return "";
			} else {
				return jns_agunan.getName();
			}
		}

		@JsonProperty("jenis_agunan")
		public String getJenis_agunan() {
			if (jns_agunan == null) {
				return "";
			} else {
				return jns_agunan.getJenis();
			}
		}

		public Integer getUrut() {
			return urut;
		}

		public void setUrut(Integer urut) {
			this.urut = urut;
		}

		public String getId_loan() {
			return id_loan;
		}

		public void setId_loan(String id_loan) {
			this.id_loan = id_loan;
		}

		public String getJenis() {
			return jenis;
		}

		public void setJenis(String jenis) {
			this.jenis = jenis;
		}

		public Double getPerkiraan_nilai() {
			return perkiraan_nilai;
		}

		public void setPerkiraan_nilai(Double perkiraan_nilai) {
			this.perkiraan_nilai = perkiraan_nilai;
		}

		public String getKeterangan() {
			return keterangan;
		}

		public void setKeterangan(String keterangan) {
			this.keterangan = keterangan;
		}

	
}
