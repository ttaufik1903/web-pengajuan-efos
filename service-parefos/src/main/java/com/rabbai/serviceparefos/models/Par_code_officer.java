package com.rabbai.serviceparefos.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "par_code_officer")
@IdClass(Code_officer_Id.class)
//public class Par_code_officer {
	public class Par_code_officer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String id_cabang;
	private String name;
	private String input_by;
	private LocalDateTime input_date;
	private String update_by;
	private LocalDateTime update_date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getId_cabang() {
		return id_cabang;
	}
	public void setId_cabang(String id_cabang) {
		this.id_cabang = id_cabang;
	}
	
}
