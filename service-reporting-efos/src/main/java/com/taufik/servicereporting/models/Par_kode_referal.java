package com.taufik.servicereporting.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "par_kode_referal")
public class Par_kode_referal {

	@Id
	private String id;
	private String name;
	private String input_by;
	private String input_date;
	private Double target_konsumer;
	private String id_cabang;

	


	

	public String getInput_by() {
		return input_by;
	}

	public void setInput_by(String input_by) {
		this.input_by = input_by;
	}

	public String getInput_date() {
		return input_date;
	}

	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}

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

	public Double getTarget_konsumer() {
		return target_konsumer;
	}

	public void setTarget_konsumer(Double target_konsumer) {
		this.target_konsumer = target_konsumer;
	}

	public String getId_cabang() {
		return id_cabang;
	}

	public void setId_cabang(String id_cabang) {
		this.id_cabang = id_cabang;
	}
}
