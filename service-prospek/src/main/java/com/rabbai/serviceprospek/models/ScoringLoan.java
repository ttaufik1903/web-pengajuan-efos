package com.rabbai.serviceprospek.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.springframework.data.annotation.Reference;

@Entity
@Table(name = "scoring_loan")
@IdClass(DebiturId.class)
public class ScoringLoan implements Serializable{
 
    /**
     * 
     */
    private static final long serialVersionUID = -2517851941873251699L;
    @Id
    @Column(name = "id_loan", unique = true, nullable = false)
    private String id_loan;
    @Id
    private Integer id_rincian_scoring;
    private Integer nilai;
    
//    @OneToMany
//    @JoinColumn(name = "id", referencedColumnName = "id_rincian_scoring")
//    private List<RincianScoring> RincianScoring;
    
	public String getId_loan() {
		return id_loan;
	}
	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}
	public Integer getId_rincian_scoring() {
		return id_rincian_scoring;
	}
	public void setId_rincian_scoring(Integer id_rincian_scoring) {
		this.id_rincian_scoring = id_rincian_scoring;
	}
//	public List<RincianScoring> getRincianScoring() {
//		return RincianScoring;
//	}
//	public void setRincianScoring(List<RincianScoring> rincianScoring) {
//		RincianScoring = rincianScoring;
//	}
	public Integer getNilai() {
		return nilai;
	}
	public void setNilai(Integer nilai) {
		this.nilai = nilai;
	}
 
}
