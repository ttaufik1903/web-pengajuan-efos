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
@Table(name = "append_plotting")
public class Append_plotting {
	/**
	 * 
	 */
	@Id
	private Integer id_agunan;
	private String append_plotting1;
	private LocalDate append_plotting2;
	private String append_plotting3;
	private String append_plotting4;
	private String append_plotting5;
	private String append_plotting6;
	private String append_plotting7;
	private String append_plotting8;
	private String append_plotting9;
	private String append_plotting10;
	private String append_plotting11;
	private String append_plotting12;
	private String append_plotting13;
	private String append_plotting14;
	

	public String getAppend_plotting1() {
		return append_plotting1;
	}


	public void setAppend_plotting1(String append_plotting1) {
		this.append_plotting1 = append_plotting1;
	}



	public String getAppend_plotting3() {
		return append_plotting3;
	}


	public void setAppend_plotting3(String append_plotting3) {
		this.append_plotting3 = append_plotting3;
	}


	public String getAppend_plotting4() {
		return append_plotting4;
	}


	public void setAppend_plotting4(String append_plotting4) {
		this.append_plotting4 = append_plotting4;
	}


	public String getAppend_plotting5() {
		return append_plotting5;
	}


	public void setAppend_plotting5(String append_plotting5) {
		this.append_plotting5 = append_plotting5;
	}


	public String getAppend_plotting6() {
		return append_plotting6;
	}


	public void setAppend_plotting6(String append_plotting6) {
		this.append_plotting6 = append_plotting6;
	}


	public String getAppend_plotting7() {
		return append_plotting7;
	}


	public void setAppend_plotting7(String append_plotting7) {
		this.append_plotting7 = append_plotting7;
	}


	public String getAppend_plotting8() {
		return append_plotting8;
	}


	public void setAppend_plotting8(String append_plotting8) {
		this.append_plotting8 = append_plotting8;
	}


	public String getAppend_plotting9() {
		return append_plotting9;
	}


	public void setAppend_plotting9(String append_plotting9) {
		this.append_plotting9 = append_plotting9;
	}


	public String getAppend_plotting10() {
		return append_plotting10;
	}


	public void setAppend_plotting10(String append_plotting10) {
		this.append_plotting10 = append_plotting10;
	}


	public String getAppend_plotting11() {
		return append_plotting11;
	}


	public void setAppend_plotting11(String append_plotting11) {
		this.append_plotting11 = append_plotting11;
	}


	public String getAppend_plotting12() {
		return append_plotting12;
	}


	public void setAppend_plotting12(String append_plotting12) {
		this.append_plotting12 = append_plotting12;
	}


	public String getAppend_plotting13() {
		return append_plotting13;
	}


	public void setAppend_plotting13(String append_plotting13) {
		this.append_plotting13 = append_plotting13;
	}


	public String getAppend_plotting14() {
		return append_plotting14;
	}


	public void setAppend_plotting14(String append_plotting14) {
		this.append_plotting14 = append_plotting14;
	}


	public LocalDate getAppend_plotting2() {
		return append_plotting2;
	}


	public void setAppend_plotting2(LocalDate append_plotting2) {
		this.append_plotting2 = append_plotting2;
	}


	public Integer getId_agunan() {
		return id_agunan;
	}


	public void setId_agunan(Integer id_agunan) {
		this.id_agunan = id_agunan;
	}



}
