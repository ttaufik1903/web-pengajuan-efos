package com.rabbai.serviceusulan.models;

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
@Table(name = "append_call")
public class Append_call {
	/**
	 * 
	 */
	@Id
	private String id_loan;
	private LocalDate append_call1;
	private String append_call2;
	private String append_call3;
	private String append_call4;
	private String append_call5;
	private String append_call6;
	private String append_call7;
	private String append_call8;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "append_call")
	private List<Loan> loan;


	public String getId_loan() {
		return id_loan;
	}


	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}




	public String getAppend_call2() {
		return append_call2;
	}


	public void setAppend_call2(String append_call2) {
		this.append_call2 = append_call2;
	}


	public String getAppend_call3() {
		return append_call3;
	}


	public void setAppend_call3(String append_call3) {
		this.append_call3 = append_call3;
	}


	public String getAppend_call4() {
		return append_call4;
	}


	public void setAppend_call4(String append_call4) {
		this.append_call4 = append_call4;
	}


	public String getAppend_call5() {
		return append_call5;
	}


	public void setAppend_call5(String append_call5) {
		this.append_call5 = append_call5;
	}


	public String getAppend_call6() {
		return append_call6;
	}


	public void setAppend_call6(String append_call6) {
		this.append_call6 = append_call6;
	}


	public String getAppend_call7() {
		return append_call7;
	}


	public void setAppend_call7(String append_call7) {
		this.append_call7 = append_call7;
	}


	public String getAppend_call8() {
		return append_call8;
	}


	public void setAppend_call8(String append_call8) {
		this.append_call8 = append_call8;
	}


	public LocalDate getAppend_call1() {
		return append_call1;
	}


	public void setAppend_call1(LocalDate append_call1) {
		this.append_call1 = append_call1;
	}



	
}
