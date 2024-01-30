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

import java.util.List;

@Entity
@Table(name = "append_scoring")
//public class Append_scoring implements Serializable {
public class Append_scoring {
	/**
	 * 
	 */
//	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	private String id_loan;
	private Integer append_scoring1;
	private Integer append_scoring2;
	private Integer append_scoring3;
	private Integer append_scoring4;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "append_scoring")
	private List<Loan> loan;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
	@JoinColumn(name = "append_scoring1", insertable = false, updatable = false)
	private RincianScoring rincian1;
	
	@JsonProperty("desc_append_scoring1")
	public String descrincian1() {
		if (rincian1 == null) {
			return "";
		} else {
			return rincian1.getRincian();
		}
	}
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
	@JoinColumn(name = "append_scoring2", insertable = false, updatable = false)
	private RincianScoring rincian2;
	
	@JsonProperty("desc_append_scoring2")
	public String descrincian2() {
		if (rincian2 == null) {
			return "";
		} else {
			return rincian2.getRincian();
		}
	}
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
	@JoinColumn(name = "append_scoring3", insertable = false, updatable = false)
	private RincianScoring rincian3;
	
	@JsonProperty("desc_append_scoring3")
	public String descrincian3() {
		if (rincian3 == null) {
			return "";
		} else {
			return rincian3.getRincian();
		}
	}
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
	@JoinColumn(name = "append_scoring4", insertable = false, updatable = false)
	private RincianScoring rincian4;
	
	@JsonProperty("desc_append_scoring4")
	public String descrincian4() {
		if (rincian4 == null) {
			return "";
		} else {
			return rincian4.getRincian();
		}
	}
	
	public String getId_loan() {
		return id_loan;
	}
	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}

	public Integer getAppend_scoring1() {
		return append_scoring1;
	}

	public void setAppend_scoring1(Integer append_scoring1) {
		this.append_scoring1 = append_scoring1;
	}

	public Integer getAppend_scoring2() {
		return append_scoring2;
	}

	public void setAppend_scoring2(Integer append_scoring2) {
		this.append_scoring2 = append_scoring2;
	}

	public Integer getAppend_scoring3() {
		return append_scoring3;
	}

	public void setAppend_scoring3(Integer append_scoring3) {
		this.append_scoring3 = append_scoring3;
	}

	public Integer getAppend_scoring4() {
		return append_scoring4;
	}

	public void setAppend_scoring4(Integer append_scoring4) {
		this.append_scoring4 = append_scoring4;
	}
	
}
