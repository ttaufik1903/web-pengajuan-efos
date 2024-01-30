package com.taufik.servicereporting.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "view_qacaproduktif_report")
public class Realisasi_qacaproduktif_per_cabang {

	@Id
	
	private String id_cab;
	private String cabang;
	private String noa;
	
	public String getId_cab() {
		return id_cab;
	}
	public void setId_cab(String id_cab) {
		this.id_cab = id_cab;
	}
	public String getCabang() {
		return cabang;
	}
	public void setCabang(String cabang) {
		this.cabang = cabang;
	}
	public String getNoa() {
		return noa;
	}
	public void setNoa(String noa) {
		this.noa = noa;
	}
	
}
