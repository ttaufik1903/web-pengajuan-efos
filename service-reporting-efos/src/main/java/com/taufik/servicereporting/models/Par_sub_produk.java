package com.taufik.servicereporting.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "par_sub_produk")
public class Par_sub_produk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer id_produk;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
	@JoinColumn(name = "id_produk", referencedColumnName = "id", insertable = false, updatable = false)
	private Par_produk par_produk;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonProperty("nama_produk")
	public String getNama_produk() {
		if (par_produk == null) {
			return "";
		} else {
			return par_produk.getName();
		}

	}

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonProperty("id_kategori_produk")
	public Integer getId_kategori_produk() {
		if (par_produk == null) {
			return 0;
		} else {
			return par_produk.getId_kategori_produk();
		}

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

	public Integer getId_produk() {
		return id_produk;
	}

	public void setId_produk(Integer id_produk) {
		this.id_produk = id_produk;
	}

}
