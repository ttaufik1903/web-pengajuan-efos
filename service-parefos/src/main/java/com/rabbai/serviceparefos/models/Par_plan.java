package com.rabbai.serviceparefos.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "par_plan")
public class Par_plan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer id_akad;
    private Integer id_sub_produk;
    private Integer id_template_dokumen;
    private String template_notisi;
    private String frequensi_pembayaran_margin;
    private String frequensi_pembayaran_number;
    private Integer type;

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
    @JoinColumn(name = "id_sub_produk", insertable = false, updatable = false)
    private Par_sub_produk par_sub_produk;
	
	@JsonProperty("nama_sub_produk")
 	public String getNama_sub_produk() {
 		return par_sub_produk.getName();
 	}
	
	@JsonProperty("id_produk")
 	public Integer getId_produk() {
 		return par_sub_produk.getId_produk();
 	}
	
	@JsonProperty("id_kategori_produk")
 	public Integer getId_kategori_produk() {
 		return par_sub_produk.getId_kategori_produk();
 	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_akad", insertable = false, updatable = false)
    private Par_akad par_akad;
	
	@JsonProperty("nama_akad")
 	public String getNama_akad() {
 		return par_akad.getName();
 	}
	
	@JsonProperty("inisial_akad")
 	public String getInisial_akad() {
 		return par_akad.getInisial();
 	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_template_dokumen", insertable = false, updatable = false)
    private Par_template_dokumen par_template_dokumen;
	
	@JsonProperty("nama_template_dokumen")
 	public String getNama_template_dokumen() {
 		return par_template_dokumen.getName();
 	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId_akad() {
		return id_akad;
	}
	public void setId_akad(Integer id_akad) {
		this.id_akad = id_akad;
	}
	public Integer getId_sub_produk() {
		return id_sub_produk;
	}
	public void setId_sub_produk(Integer id_sub_produk) {
		this.id_sub_produk = id_sub_produk;
	}
	public Integer getId_template_dokumen() {
		return id_template_dokumen;
	}
	public void setId_template_dokumen(Integer id_template_dokumen) {
		this.id_template_dokumen = id_template_dokumen;
	}
	public String getTemplate_notisi() {
		return template_notisi;
	}
	public void setTemplate_notisi(String template_notisi) {
		this.template_notisi = template_notisi;
	}

	public String getFrequensi_pembayaran_margin() {
		return frequensi_pembayaran_margin;
	}

	public void setFrequensi_pembayaran_margin(String frequensi_pembayaran_margin) {
		this.frequensi_pembayaran_margin = frequensi_pembayaran_margin;
	}

	public String getFrequensi_pembayaran_number() {
		return frequensi_pembayaran_number;
	}

	public void setFrequensi_pembayaran_number(String frequensi_pembayaran_number) {
		this.frequensi_pembayaran_number = frequensi_pembayaran_number;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
   
	

}
