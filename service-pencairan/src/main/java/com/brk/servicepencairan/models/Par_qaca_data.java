package com.brk.servicepencairan.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.springframework.data.annotation.Reference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "par_qaca_data")
@IdClass(Qaca_data_id.class)
public class Par_qaca_data implements Serializable{
 
    private static final long serialVersionUID = -2517851941873251699L;
    @Id
    @Column(name = "id_loan", unique = true, nullable = false)
    private String id_loan;
    @Id
    private Integer id_qaca;
    private Integer status;
    private String keterangan;
    
    
   // PARENT LOAN
// 	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
// 	@ManyToOne
// 	@JoinColumn(name = "id_loan", insertable = false, updatable = false)
// 	private Loan loan;
 	
 	@ManyToOne(fetch = FetchType.LAZY)
 	@JoinColumn(name = "id_qaca", insertable = false, updatable = false, nullable = true)
 	private Par_qaca_detail par_qaca_detail;

 	@JsonProperty("id_qaca_group")
 	public String getId_qaca_group() {
 		return par_qaca_detail.getDukument_id();
 	}
 	
 	@JsonProperty("desc_qaca_detail")
 	public String getDesc_qaca_detail() {
 		return par_qaca_detail.getName();
 	}
 	
 	@JsonProperty("desc_qaca_group")
 	public String getDesc_qaca_group() {
 		return par_qaca_detail.getDesc_qaca_group();
 	}

	public String getId_loan() {
		return id_loan;
	}

	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}

	public Integer getId_qaca() {
		return id_qaca;
	}

	public void setId_qaca(Integer id_qaca) {
		this.id_qaca = id_qaca;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

//	public Loan getLoan() {
//		return loan;
//	}
//
//	public void setLoan(Loan loan) {
//		this.loan = loan;
//	}
//    
	
}
