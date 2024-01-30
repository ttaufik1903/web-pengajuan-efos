package com.brk.servicepencairan.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "append_survey")
public class Append_survey {
	/**
	 * 
	 */
	@Id
	private String id_loan;
	private String append_survey1;
	private LocalDate append_survey2;
	private String append_survey3;
	private String append_survey4;
	private Integer append_survey5;
	private Integer append_survey6;
	private String append_survey7;
	private String append_survey8;
	private Integer append_survey9;
	private Integer append_survey10;
	private String append_survey11;
	private String append_survey12;
	
//	@OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "append_survey")
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "append_survey")
//	private List<Loan> loan;



	public String getAppend_survey1() {
		return append_survey1;
	}


	public void setAppend_survey1(String append_survey1) {
		this.append_survey1 = append_survey1;
	}




	public String getAppend_survey3() {
		return append_survey3;
	}


	public void setAppend_survey3(String append_survey3) {
		this.append_survey3 = append_survey3;
	}


	public String getAppend_survey4() {
		return append_survey4;
	}


	public void setAppend_survey4(String append_survey4) {
		this.append_survey4 = append_survey4;
	}


	public Integer getAppend_survey5() {
		return append_survey5;
	}


	public void setAppend_survey5(Integer append_survey5) {
		this.append_survey5 = append_survey5;
	}


	public Integer getAppend_survey6() {
		return append_survey6;
	}


	public void setAppend_survey6(Integer append_survey6) {
		this.append_survey6 = append_survey6;
	}


	public String getAppend_survey7() {
		return append_survey7;
	}


	public void setAppend_survey7(String append_survey7) {
		this.append_survey7 = append_survey7;
	}


	public String getAppend_survey8() {
		return append_survey8;
	}


	public void setAppend_survey8(String append_survey8) {
		this.append_survey8 = append_survey8;
	}


	public Integer getAppend_survey9() {
		return append_survey9;
	}


	public void setAppend_survey9(Integer append_survey9) {
		this.append_survey9 = append_survey9;
	}


	public Integer getAppend_survey10() {
		return append_survey10;
	}


	public void setAppend_survey10(Integer append_survey10) {
		this.append_survey10 = append_survey10;
	}


	public String getAppend_survey11() {
		return append_survey11;
	}


	public void setAppend_survey11(String append_survey11) {
		this.append_survey11 = append_survey11;
	}


	public String getAppend_survey12() {
		return append_survey12;
	}


	public void setAppend_survey12(String append_survey12) {
		this.append_survey12 = append_survey12;
	}


	public String getId_loan() {
		return id_loan;
	}


	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}


	public LocalDate getAppend_survey2() {
		return append_survey2;
	}


	public void setAppend_survey2(LocalDate append_survey2) {
		this.append_survey2 = append_survey2;
	}


	
}
