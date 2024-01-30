package com.rabbai.serviceusulan.models.mkm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "m_data_hub_perbankan_detail")
@IdClass(M_data_hub_bank_Id.class)
public class M_data_hub_perbankan_detail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_loan", unique = true, nullable = false)
	private String id_loan;
	private String periode;
	private Double debet;
	private Double kredit;
	private Double saldo;
	
	public M_data_hub_perbankan_detail() {
		super();
	}
	
	public M_data_hub_perbankan_detail (String id_loan, String periode,Double debet, Double kredit, Double saldo) {
		this.id_loan=id_loan;
		this.periode=periode;
		this.debet=debet;
		this.kredit=kredit;
		this.saldo=saldo;
	}
	
	public String getId_loan() {
		return id_loan;
	}
	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}
	public String getPeriode() {
		return periode;
	}
	public void setPeriode(String periode) {
		this.periode = periode;
	}
	public Double getDebet() {
		return debet;
	}
	public void setDebet(Double debet) {
		this.debet = debet;
	}
	public Double getKredit() {
		return kredit;
	}
	public void setKredit(Double kredit) {
		this.kredit = kredit;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
}
