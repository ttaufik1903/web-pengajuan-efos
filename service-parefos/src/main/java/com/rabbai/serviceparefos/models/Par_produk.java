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

@Entity
@Table(name = "par_produk")
public class Par_produk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer id_kategori_produk;
    
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne
    @JoinColumn(name = "id_kategori_produk", referencedColumnName = "id", insertable = false, updatable = false)
    private Par_kategori_produk par_kategori_produk;
	
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonProperty("nama_kategori")
 	public String getNama_kategori() {
		if (par_kategori_produk == null) {
			return "";
		} else {
			return par_kategori_produk.getName();
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
	public Integer getId_kategori_produk() {
		return id_kategori_produk;
	}
	public void setId_kategori_produk(Integer id_kategori_produk) {
		this.id_kategori_produk = id_kategori_produk;
	}
    
    
}

