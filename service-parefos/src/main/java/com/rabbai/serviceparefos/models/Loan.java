package com.rabbai.serviceparefos.models;

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

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "data_loan_i")
public class Loan implements Serializable{
 
    /**
     * 
     */
    private static final long serialVersionUID = -2517851941873251699L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String id_debitur;
    private Integer id_kategori_produk;
    private Integer id_plan;
    private BigDecimal plafon_pengajuan;
    private Integer tenor_pengajuan;
    private Float margin_pengajuan;
    private Float angsuran_pengajuan;
    private String tujuan_pembiayaan;
    private String no_loan;
    private String ideb;
    private String input_by;
    private LocalDateTime input_date;
    private String no_scoring;
    private LocalDateTime tgl_scoring;
    private String input_scoring_by;
    private String review_by;
    private LocalDateTime review_date;
    private String review_desc;
    private String submit_by;
    private LocalDateTime submit_date;
    private String approve1_by;
    private LocalDateTime approve1_date;
    private String input2_by;
    private LocalDateTime input2_date;
    private String submit2_by;
    private LocalDateTime submit2_date;
    private String review2_by;
    private LocalDateTime review2_date;
    private String review2_desc;
    private String approve12_by;
    private LocalDateTime approve12_date;
    private String approve2_by;
    private LocalDateTime approve2_date;
    private String approve3_by;
    private LocalDateTime approve3_date;
    private String akad_by;
    private LocalDateTime akad_date;
    private String cair_by;
    private LocalDateTime cair_date;
    private String status;
    private LocalDateTime tgl_pengajuan;
    private String id_cab;
 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_debitur() {
		return id_debitur;
	}
	public void setId_debitur(String id_debitur) {
		this.id_debitur = id_debitur;
	}

	public BigDecimal getPlafon_pengajuan() {
		return plafon_pengajuan;
	}
	public void setPlafon_pengajuan(BigDecimal plafon_pengajuan) {
		this.plafon_pengajuan = plafon_pengajuan;
	}
	public Integer getTenor_pengajuan() {
		return tenor_pengajuan;
	}
	public void setTenor_pengajuan(Integer tenor_pengajuan) {
		this.tenor_pengajuan = tenor_pengajuan;
	}

	public Float getAngsuran_pengajuan() {
		return angsuran_pengajuan;
	}
	public void setAngsuran_pengajuan(Float angsuran_pengajuan) {
		this.angsuran_pengajuan = angsuran_pengajuan;
	}
	public String getTujuan_pembiayaan() {
		return tujuan_pembiayaan;
	}
	public void setTujuan_pembiayaan(String tujuan_pembiayaan) {
		this.tujuan_pembiayaan = tujuan_pembiayaan;
	}
	public String getNo_loan() {
		return no_loan;
	}
	public void setNo_loan(String no_loan) {
		this.no_loan = no_loan;
	}
	public String getNo_scoring() {
		return no_scoring;
	}
	public void setNo_scoring(String no_scoring) {
		this.no_scoring = no_scoring;
	}
	public LocalDateTime getTgl_scoring() {
		return tgl_scoring;
	}
	public void setTgl_scoring(LocalDateTime tgl_scoring) {
		this.tgl_scoring = tgl_scoring;
	}
	public String getInput_scoring_by() {
		return input_scoring_by;
	}
	public void setInput_scoring_by(String input_scoring_by) {
		this.input_scoring_by = input_scoring_by;
	}

	public Float getMargin_pengajuan() {
		return margin_pengajuan;
	}
	public void setMargin_pengajuan(Float margin_pengajuan) {
		this.margin_pengajuan = margin_pengajuan;
	}

	public String getIdeb() {
		return ideb;
	}

	public void setIdeb(String ideb) {
		this.ideb = ideb;
	}

	public String getInput_by() {
		return input_by;
	}

	public void setInput_by(String input_by) {
		this.input_by = input_by;
	}

	public LocalDateTime getInput_date() {
		return input_date;
	}

	public void setInput_date(LocalDateTime input_date) {
		this.input_date = input_date;
	}

	public String getReview_by() {
		return review_by;
	}

	public void setReview_by(String review_by) {
		this.review_by = review_by;
	}

	public LocalDateTime getReview_date() {
		return review_date;
	}

	public void setReview_date(LocalDateTime review_date) {
		this.review_date = review_date;
	}

	public String getReview_desc() {
		return review_desc;
	}

	public void setReview_desc(String review_desc) {
		this.review_desc = review_desc;
	}

	public String getSubmit_by() {
		return submit_by;
	}

	public void setSubmit_by(String submit_by) {
		this.submit_by = submit_by;
	}

	public LocalDateTime getSubmit_date() {
		return submit_date;
	}

	public void setSubmit_date(LocalDateTime submit_date) {
		this.submit_date = submit_date;
	}

	public String getApprove1_by() {
		return approve1_by;
	}

	public void setApprove1_by(String approve1_by) {
		this.approve1_by = approve1_by;
	}

	public LocalDateTime getApprove1_date() {
		return approve1_date;
	}

	public void setApprove1_date(LocalDateTime approve1_date) {
		this.approve1_date = approve1_date;
	}

	public String getInput2_by() {
		return input2_by;
	}

	public void setInput2_by(String input2_by) {
		this.input2_by = input2_by;
	}

	public LocalDateTime getInput2_date() {
		return input2_date;
	}

	public void setInput2_date(LocalDateTime input2_date) {
		this.input2_date = input2_date;
	}

	public String getSubmit2_by() {
		return submit2_by;
	}

	public void setSubmit2_by(String submit2_by) {
		this.submit2_by = submit2_by;
	}

	public LocalDateTime getSubmit2_date() {
		return submit2_date;
	}

	public void setSubmit2_date(LocalDateTime submit2_date) {
		this.submit2_date = submit2_date;
	}

	public String getReview2_by() {
		return review2_by;
	}

	public void setReview2_by(String review2_by) {
		this.review2_by = review2_by;
	}

	public LocalDateTime getReview2_date() {
		return review2_date;
	}

	public void setReview2_date(LocalDateTime review2_date) {
		this.review2_date = review2_date;
	}

	public String getReview2_desc() {
		return review2_desc;
	}

	public void setReview2_desc(String review2_desc) {
		this.review2_desc = review2_desc;
	}

	public String getApprove12_by() {
		return approve12_by;
	}

	public void setApprove12_by(String approve12_by) {
		this.approve12_by = approve12_by;
	}

	public LocalDateTime getApprove12_date() {
		return approve12_date;
	}

	public void setApprove12_date(LocalDateTime approve12_date) {
		this.approve12_date = approve12_date;
	}

	public String getApprove2_by() {
		return approve2_by;
	}

	public void setApprove2_by(String approve2_by) {
		this.approve2_by = approve2_by;
	}

	public LocalDateTime getApprove2_date() {
		return approve2_date;
	}

	public void setApprove2_date(LocalDateTime approve2_date) {
		this.approve2_date = approve2_date;
	}

	public String getApprove3_by() {
		return approve3_by;
	}

	public void setApprove3_by(String approve3_by) {
		this.approve3_by = approve3_by;
	}

	public LocalDateTime getApprove3_date() {
		return approve3_date;
	}

	public void setApprove3_date(LocalDateTime approve3_date) {
		this.approve3_date = approve3_date;
	}

	public String getAkad_by() {
		return akad_by;
	}

	public void setAkad_by(String akad_by) {
		this.akad_by = akad_by;
	}

	public LocalDateTime getAkad_date() {
		return akad_date;
	}

	public void setAkad_date(LocalDateTime akad_date) {
		this.akad_date = akad_date;
	}

	public String getCair_by() {
		return cair_by;
	}

	public void setCair_by(String cair_by) {
		this.cair_by = cair_by;
	}

	public LocalDateTime getCair_date() {
		return cair_date;
	}

	public void setCair_date(LocalDateTime cair_date) {
		this.cair_date = cair_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getTgl_pengajuan() {
		return tgl_pengajuan;
	}

	public void setTgl_pengajuan(LocalDateTime tgl_pengajuan) {
		this.tgl_pengajuan = tgl_pengajuan;
	}


	public Integer getId_kategori_produk() {
		return id_kategori_produk;
	}

	public void setId_kategori_produk(Integer id_kategori_produk) {
		this.id_kategori_produk = id_kategori_produk;
	}

	public Integer getId_plan() {
		return id_plan;
	}

	public void setId_plan(Integer id_plan) {
		this.id_plan = id_plan;
	}

	public String getId_cab() {
		return id_cab;
	}

	public void setId_cab(String id_cab) {
		this.id_cab = id_cab;
	}
    
}
