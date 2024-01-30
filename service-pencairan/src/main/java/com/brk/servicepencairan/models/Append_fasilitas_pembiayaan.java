package com.brk.servicepencairan.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "append_fasilitas_pembiayaan")
public class Append_fasilitas_pembiayaan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String id_loan;
	private String append_fp1;
	private String append_fp2;
	private String append_fp3;
	private float append_fp4;
	private LocalDate append_fp5;
	private String append_fp6;

	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "append_fasilitas_pembiayaan")
	private List<Loan> loan;



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getId_loan() {
		return id_loan;
	}



	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}



	public String getAppend_fp1() {
		return append_fp1;
	}



	public void setAppend_fp1(String append_fp1) {
		this.append_fp1 = append_fp1;
	}



	public String getAppend_fp2() {
		return append_fp2;
	}



	public void setAppend_fp2(String append_fp2) {
		this.append_fp2 = append_fp2;
	}



	public String getAppend_fp3() {
		return append_fp3;
	}



	public void setAppend_fp3(String append_fp3) {
		this.append_fp3 = append_fp3;
	}



	public float getAppend_fp4() {
		return append_fp4;
	}



	public void setAppend_fp4(float append_fp4) {
		this.append_fp4 = append_fp4;
	}






	public String getAppend_fp6() {
		return append_fp6;
	}



	public void setAppend_fp6(String append_fp6) {
		this.append_fp6 = append_fp6;
	}



	public LocalDate getAppend_fp5() {
		return append_fp5;
	}



	public void setAppend_fp5(LocalDate append_fp5) {
		this.append_fp5 = append_fp5;
	}



}
