package com.rabbai.serviceparefos.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "par_sub_sub_dinas")
//@IdClass(Sub_sub_dinas_Id.class)
public class Par_sub_sub_dinas implements Serializable{
 
    /**
     * 
     */
    private static final long serialVersionUID = -2517851941873251699L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String id_sub_sub_dinas;
    private String id_sub_dinas;
    private String id_cabang;
	private String name;
	private String bendahara;
	private String telp;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
	@JoinColumn(name = "id_cabang", insertable = false, updatable = false)
	private Par_cabang cabang;
    
    @JsonProperty("nama_cabang")
	public String getNama_cabang() {
		return cabang.getCabang();
	}
    
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "id_sub_dinas", referencedColumnName = "id", insertable = false, updatable = false),
		@JoinColumn(name = "id_cabang", referencedColumnName = "id_cabang", insertable = false, updatable = false) })
	private Par_sub_dinas par_sub_dinas;
    
    @JsonProperty("nama_sub_dinas")
	public String getNama_sub_dinas() {
    	if (par_sub_dinas == null) {
			return "";
		} else {
			return par_sub_dinas.getName();
		}
		
	}
    
    @JsonProperty("id_dinas")
	public String getId_dinas() {
    	if (par_sub_dinas == null) {
			return "";
		} else {
			return par_sub_dinas.getId_par_kode_dinas();
		}
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getId_sub_dinas() {
		return id_sub_dinas;
	}
	public void setId_sub_dinas(String id_sub_dinas) {
		this.id_sub_dinas = id_sub_dinas;
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

	public String getBendahara() {
		return bendahara;
	}

	public void setBendahara(String bendahara) {
		this.bendahara = bendahara;
	}

	public String getTelp() {
		return telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}

	public String getId_sub_sub_dinas() {
		return id_sub_sub_dinas;
	}

	public void setId_sub_sub_dinas(String id_sub_sub_dinas) {
		this.id_sub_sub_dinas = id_sub_sub_dinas;
	}
    
}
