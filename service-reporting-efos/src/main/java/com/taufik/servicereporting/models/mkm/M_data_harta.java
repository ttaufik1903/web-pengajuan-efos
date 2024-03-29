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
import com.taufik.servicereporting.models.Par_agunan_jenis_agunan;

import java.io.Serializable;

@Entity
@Table(name = "m_data_harta")
public class M_data_harta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer urut;
	private String id_loan;
	private String jenis;
	private String detail_jenis;
	private Double perkiraan_nilai;
	private String keterangan;
	
	public M_data_harta() {
		super();
	}
	
	public M_data_harta (String id_loan, String jenis ,String detail_jenis, Double perkiraan_nilai, String keterangan) {
		this.id_loan=id_loan;
		this.jenis=jenis;
		this.detail_jenis=detail_jenis;
		this.perkiraan_nilai=perkiraan_nilai;
		this.keterangan=keterangan;
	}

	
	// PARENT PAR JENIS AGUNAN
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "jenis", insertable = false, updatable = false)
		private Par_agunan_jenis_agunan jenis_agunan;

		@JsonProperty("desc_agunan_jenis_agunan")
		public String getDesc_jenis() {
			if (jenis_agunan == null) {
				return "";
			} else {
				return jenis_agunan.getName();
			}
		}

//		@JsonProperty("jenis_agunan")
//		public String getJenis_agunan() {
//			if (jenis_agunan == null) {
//				return "";
//			} else {
//				return jenis_agunan.getJenis();
//			}
//		}

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

		public String getDetail_jenis() {
			return detail_jenis;
		}

		public void setDetail_jenis(String detail_jenis) {
			this.detail_jenis = detail_jenis;
		}

	
}
