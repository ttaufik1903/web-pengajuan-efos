package com.rabbai.serviceparefos.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
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

@IdClass(Sub_dinas_Id.class)
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
	private String input_by;
	private LocalDateTime input_date;
	private String update_by;
	private LocalDateTime update_date;
	
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

	public String getInput_by() {
		return input_by;
	}

	public void setInput_by(String input_by) {
		this.input_by = input_by;
	}

	public LocalDateTime getInput_date() {
		return input_date;
	}

	public void setInput_date(LocalDateTime input_date) {
		this.input_date = input_date;
	}

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	public LocalDateTime getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(LocalDateTime update_date) {
		this.update_date = update_date;
	}
	
	
	
}
