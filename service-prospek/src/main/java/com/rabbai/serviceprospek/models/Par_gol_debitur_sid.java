package com.rabbai.serviceprospek.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "par_gol_debitur_sid", schema = "pelaporan")
public class Par_gol_debitur_sid {

	@Id
	private String id;
//	@Column(name = "keterangan")
	private String name;
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


}
