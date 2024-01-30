package com.brk.servicepencairan.models.mkm;

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
@Table(name = "m_append_survey")
public class M_append_survey {
	/**
	 * 
	 */
	@Id
	private String id_loan;
	private LocalDate append_survey1;
	private String append_survey2;
	private String append_survey3;
	private String append_survey4;
	private String append_survey5;
	private String append_survey6;
	private String append_survey7;
	private String append_survey8;
	private String append_survey9;
	private String append_survey10;
	private String append_survey11;
	private String append_survey12;
	private String append_survey13;
	private String append_survey14;
	private String append_survey15;
	private String append_survey16;
	private String append_survey17;
	private String append_survey18;
	private String append_survey19;
	private String append_survey20;
	
	public String getId_loan() {
		return id_loan;
	}
	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}
	public LocalDate getAppend_survey1() {
		return append_survey1;
	}
	public void setAppend_survey1(LocalDate append_survey1) {
		this.append_survey1 = append_survey1;
	}
	public String getAppend_survey2() {
		return append_survey2;
	}
	public void setAppend_survey2(String append_survey2) {
		this.append_survey2 = append_survey2;
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
	public String getAppend_survey5() {
		return append_survey5;
	}
	public void setAppend_survey5(String append_survey5) {
		this.append_survey5 = append_survey5;
	}
	public String getAppend_survey6() {
		return append_survey6;
	}
	public void setAppend_survey6(String append_survey6) {
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
	public String getAppend_survey9() {
		return append_survey9;
	}
	public void setAppend_survey9(String append_survey9) {
		this.append_survey9 = append_survey9;
	}
	public String getAppend_survey10() {
		return append_survey10;
	}
	public void setAppend_survey10(String append_survey10) {
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
	public String getAppend_survey13() {
		return append_survey13;
	}
	public void setAppend_survey13(String append_survey13) {
		this.append_survey13 = append_survey13;
	}
	public String getAppend_survey14() {
		return append_survey14;
	}
	public void setAppend_survey14(String append_survey14) {
		this.append_survey14 = append_survey14;
	}
	public String getAppend_survey15() {
		return append_survey15;
	}
	public void setAppend_survey15(String append_survey15) {
		this.append_survey15 = append_survey15;
	}
	public String getAppend_survey16() {
		return append_survey16;
	}
	public void setAppend_survey16(String append_survey16) {
		this.append_survey16 = append_survey16;
	}
	public String getAppend_survey17() {
		return append_survey17;
	}
	public void setAppend_survey17(String append_survey17) {
		this.append_survey17 = append_survey17;
	}
	public String getAppend_survey18() {
		return append_survey18;
	}
	public void setAppend_survey18(String append_survey18) {
		this.append_survey18 = append_survey18;
	}
	public String getAppend_survey19() {
		return append_survey19;
	}
	public void setAppend_survey19(String append_survey19) {
		this.append_survey19 = append_survey19;
	}
	public String getAppend_survey20() {
		return append_survey20;
	}
	public void setAppend_survey20(String append_survey20) {
		this.append_survey20 = append_survey20;
	}
	

	
}
