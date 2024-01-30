package com.brk.servicepencairan.models;

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
@Table(name = "append_map")
public class Append_map {
	/**
	 * 
	 */
	@Id
	private String id_loan;
	private String append_map1;
	private String append_map2;
	private String append_map3;
	private String append_map4;
	private String append_map5;
	private String append_map6;
	private LocalDate append_map7;
	private String append_map8;
	private String append_map9;
	private String append_map10;
	private String append_map11;
	private String append_map12;
	private String append_map13;
	private String append_map14;
	private String append_map15;
	private String append_map16;
	private String append_map17;
	private String append_map18;
	private String append_map19;
	private String append_map20;
	private String append_map21;
	private String append_map22;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "append_map")
	private List<Loan> loan;


	public String getId_loan() {
		return id_loan;
	}


	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}


	public String getAppend_map1() {
		return append_map1;
	}


	public void setAppend_map1(String append_map1) {
		this.append_map1 = append_map1;
	}


	public String getAppend_map2() {
		return append_map2;
	}


	public void setAppend_map2(String append_map2) {
		this.append_map2 = append_map2;
	}


	public String getAppend_map3() {
		return append_map3;
	}


	public void setAppend_map3(String append_map3) {
		this.append_map3 = append_map3;
	}


	public String getAppend_map4() {
		return append_map4;
	}


	public void setAppend_map4(String append_map4) {
		this.append_map4 = append_map4;
	}


	public String getAppend_map5() {
		return append_map5;
	}


	public void setAppend_map5(String append_map5) {
		this.append_map5 = append_map5;
	}


	public String getAppend_map6() {
		return append_map6;
	}


	public void setAppend_map6(String append_map6) {
		this.append_map6 = append_map6;
	}




	public String getAppend_map8() {
		return append_map8;
	}


	public void setAppend_map8(String append_map8) {
		this.append_map8 = append_map8;
	}


	public String getAppend_map9() {
		return append_map9;
	}


	public void setAppend_map9(String append_map9) {
		this.append_map9 = append_map9;
	}


	public String getAppend_map10() {
		return append_map10;
	}


	public void setAppend_map10(String append_map10) {
		this.append_map10 = append_map10;
	}


	public String getAppend_map11() {
		return append_map11;
	}


	public void setAppend_map11(String append_map11) {
		this.append_map11 = append_map11;
	}


	public String getAppend_map12() {
		return append_map12;
	}


	public void setAppend_map12(String append_map12) {
		this.append_map12 = append_map12;
	}


	public String getAppend_map13() {
		return append_map13;
	}


	public void setAppend_map13(String append_map13) {
		this.append_map13 = append_map13;
	}


	public String getAppend_map14() {
		return append_map14;
	}


	public void setAppend_map14(String append_map14) {
		this.append_map14 = append_map14;
	}


	public String getAppend_map15() {
		return append_map15;
	}


	public void setAppend_map15(String append_map15) {
		this.append_map15 = append_map15;
	}


	public String getAppend_map16() {
		return append_map16;
	}


	public void setAppend_map16(String append_map16) {
		this.append_map16 = append_map16;
	}


	public String getAppend_map17() {
		return append_map17;
	}


	public void setAppend_map17(String append_map17) {
		this.append_map17 = append_map17;
	}


	public LocalDate getAppend_map7() {
		return append_map7;
	}


	public void setAppend_map7(LocalDate append_map7) {
		this.append_map7 = append_map7;
	}


	public String getAppend_map18() {
		return append_map18;
	}


	public void setAppend_map18(String append_map18) {
		this.append_map18 = append_map18;
	}


	public String getAppend_map19() {
		return append_map19;
	}


	public void setAppend_map19(String append_map19) {
		this.append_map19 = append_map19;
	}


	public String getAppend_map20() {
		return append_map20;
	}


	public void setAppend_map20(String append_map20) {
		this.append_map20 = append_map20;
	}


	public String getAppend_map21() {
		return append_map21;
	}


	public void setAppend_map21(String append_map21) {
		this.append_map21 = append_map21;
	}


	public String getAppend_map22() {
		return append_map22;
	}


	public void setAppend_map22(String append_map22) {
		this.append_map22 = append_map22;
	}


}
