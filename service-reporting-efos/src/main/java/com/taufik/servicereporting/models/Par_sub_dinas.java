package com.taufik.servicereporting.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "par_sub_dinas")
public class Par_sub_dinas implements Serializable{
 
    /**
     * 
     */
    private static final long serialVersionUID = -2517851941873251699L;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String id_par_kode_dinas;
    private String id_cabang;
	private String name;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
	@JoinColumn(name = "id_cabang", insertable = false, updatable = false)
	private Par_cabang cabang;
    
    @JsonProperty("nama_cabang")
	public String getNama_cabang() {
		return cabang.getCabang();
	}
    
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "id_par_kode_dinas", referencedColumnName = "id", insertable = false, updatable = false),
		@JoinColumn(name = "id_cabang", referencedColumnName = "id_cabang", insertable = false, updatable = false) })
	private Par_dinas par_dinas;
    
    @JsonProperty("nama_dinas")
	public String getNama_dinas() {
		return par_dinas.getName();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_par_kode_dinas() {
		return id_par_kode_dinas;
	}
	public void setId_par_kode_dinas(String id_par_kode_dinas) {
		this.id_par_kode_dinas = id_par_kode_dinas;
	}
	public String getId_cabang() {
		return id_cabang;
	}
	public void setId_cabang(String id_cabang) {
		this.id_cabang = id_cabang;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
