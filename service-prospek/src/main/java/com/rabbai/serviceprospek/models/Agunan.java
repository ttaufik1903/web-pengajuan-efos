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
@Table(name = "data_agunan_i")
public class Agunan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer urut;
	private String id_loan;
	private String jenis;
	private String alamat;
	private Integer kel;
	private Integer kec;
	private Integer dati2;
	private Integer provinsi;
  	private Integer luas_tanah;
	private Integer luas_bangunan;
	private String bukti;
	private String no_bukti;
	private Double harga_pasar;
	private Double nilai_taksasi;
	// private Double persentase_taksasi_1;
	 
	private Double biaya_pengikatan;
	private String file;
	private String kepemilikan;
	// private Double harga_pasar_2;
	// private Double nilai_taksasi_2;
	// private Double persentase_taksasi_2;

	// PARENT PAR PROVINSI
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provinsi", insertable = false, updatable = false)
	private Par_provinsi desc_provinsi;

	@JsonProperty("desc_provinsi")
	public String getDesc_provinsi() {
		if (desc_provinsi == null) {
			return "";
		} else {
			return desc_provinsi.getName();
		}
	}

	// PARENT PAR PROVINSI
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

	// PARENT PAR DATI 2
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dati2", insertable = false, updatable = false)
	private Par_dati2 desc_dati2;

	@JsonProperty("desc_dati2")
	public String getDesc_dati2() {
		if (desc_dati2 == null) {
			return "";
		} else {
			return desc_dati2.getName();
		}
	}

	// PARENT PAR KECAMATAN
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kec", insertable = false, updatable = false)
	private Par_kec desc_kecamatan;

	@JsonProperty("desc_kecamatan")
	public String getDesc_kecamatan() {
		if (desc_kecamatan == null) {
			return "";
		} else {
			return desc_kecamatan.getName();
		}
	}

	// PARENT PAR KELURAHAN
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kel", insertable = false, updatable = false)
	private Par_kel desc_kelurahan;

	@JsonProperty("desc_kelurahan")
	public String getDesc_kelurahan() {
		if (desc_kelurahan == null) {
			return "";
		} else {
			return desc_kelurahan.getName();
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

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public Integer getKel() {
		return kel;
	}

	public void setKel(Integer kel) {
		this.kel = kel;
	}

	public Integer getKec() {
		return kec;
	}

	public void setKec(Integer kec) {
		this.kec = kec;
	}

	public Integer getDati2() {
		return dati2;
	}

	public void setDati2(Integer dati2) {
		this.dati2 = dati2;
	}

	public Integer getProvinsi() {
		return provinsi;
	}

	public void setProvinsi(Integer provinsi) {
		this.provinsi = provinsi;
	}
 
	 

	public Integer getLuas_tanah() {
		return luas_tanah;
	}

	public void setLuas_tanah(Integer luas_tanah) {
		this.luas_tanah = luas_tanah;
	}

	public Integer getLuas_bangunan() {
		return luas_bangunan;
	}

	public void setLuas_bangunan(Integer luas_bangunan) {
		this.luas_bangunan = luas_bangunan;
	}

	public String getBukti() {
		return bukti;
	}

	public void setBukti(String bukti) {
		this.bukti = bukti;
	}

	public String getNo_bukti() {
		return no_bukti;
	}

	public void setNo_bukti(String no_bukti) {
		this.no_bukti = no_bukti;
	}

	 

	public Double getBiaya_pengikatan() {
		return biaya_pengikatan;
	}

	public void setBiaya_pengikatan(Double biaya_pengikatan) {
		this.biaya_pengikatan = biaya_pengikatan;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getKepemilikan() {
		return kepemilikan;
	}

	public void setKepemilikan(String kepemilikan) {
		this.kepemilikan = kepemilikan;
	}

	public Double getHarga_pasar() {
		return harga_pasar;
	}

	public void setHarga_pasar(Double harga_pasar) {
		this.harga_pasar = harga_pasar;
	}

	public Double getNilai_taksasi() {
		return nilai_taksasi;
	}

	public void setNilai_taksasi(Double nilai_taksasi) {
		this.nilai_taksasi = nilai_taksasi;
	}

}
