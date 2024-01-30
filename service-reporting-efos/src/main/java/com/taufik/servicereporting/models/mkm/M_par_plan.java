package com.taufik.servicereporting.models.mkm;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taufik.servicereporting.models.Par_sub_produk;
import com.taufik.servicereporting.models.Par_template_dokumen;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "par_plan")
public class M_par_plan implements Serializable {

//	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	private Integer id;
	private String name;
//	 private Integer id_akad;
	private Integer id_sub_produk;
	private Integer id_template_dokumen;
	private String template_notisi;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_akad")
	private M_par_akad_toloan m_par_akad_toloan;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_akad", insertable = false, updatable = false)
	private M_par_akad m_par_akad;
 
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_akad")
//	private Par_akad par_akad;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_plan")
//	@Fetch(value = FetchMode.SUBSELECT)
	 private List<M_data_loan> loans = new ArrayList<>();
//	private Set<Loan> loans;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
	@JoinColumn(name = "id_sub_produk", insertable = false, updatable = false)
	private Par_sub_produk par_sub_produk;

	@JsonProperty("nama_sub_produk")
	public String getNama_sub_produk() {
		return par_sub_produk.getName();
	}

	@JsonProperty("id_produk")
	public Integer getId_produk() {
		return par_sub_produk.getId_produk();
	}

	@JsonProperty("id_kategori_produk")
	public Integer getId_kategori_produk() {
		return par_sub_produk.getId_kategori_produk();
	}


	@JsonProperty("nama_akad")
	public String getNama_akad() {
		return m_par_akad.getName();
	}

	@JsonProperty("inisial_akad")
	public String getInisial_akad() {
		return m_par_akad.getInisial();
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_template_dokumen", insertable = false, updatable = false)
	private Par_template_dokumen par_template_dokumen;

	@JsonProperty("nama_template_dokumen")
	public String getNama_template_dokumen() {
		return par_template_dokumen.getName();
	}

//	public Set<Loan> getLoans() {
//		return loans;
//	}
//
//	public void setLoans(List<Loan> loans) {
//		this.loans = (Set<Loan>) loans;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Integer getId_akad() {
//		return id_akad;
//	}
//
//	public void setId_akad(Integer id_akad) {
//		this.id_akad = id_akad;
//	}

	public Integer getId_sub_produk() {
		return id_sub_produk;
	}

	public void setId_sub_produk(Integer id_sub_produk) {
		this.id_sub_produk = id_sub_produk;
	}

	public Integer getId_template_dokumen() {
		return id_template_dokumen;
	}

	public void setId_template_dokumen(Integer id_template_dokumen) {
		this.id_template_dokumen = id_template_dokumen;
	}

	public String getTemplate_notisi() {
		return template_notisi;
	}

	public void setTemplate_notisi(String template_notisi) {
		this.template_notisi = template_notisi;
	}

	public List<M_data_loan> getLoans() {
		return loans;
	}

	public void setLoans(List<M_data_loan> loans) {
		this.loans = loans;
	}

}
