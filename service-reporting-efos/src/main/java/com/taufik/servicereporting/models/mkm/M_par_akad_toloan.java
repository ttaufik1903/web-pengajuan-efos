package com.taufik.servicereporting.models.mkm;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "par_akad")
public class M_par_akad_toloan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;

	@Id
	private Integer id;
	private String name;
	private String inisial;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loans")
//	private List<Agunan> agunans = new ArrayList<>();
//	
	
	@OneToMany(mappedBy = "m_par_akad_toloan", fetch = FetchType.LAZY)
	// @Fetch(value = FetchMode.SUBSELECT)
//	private List<Par_plan> plans = new ArrayList<>();
	
	private Set<M_par_plan> plans;

	
//	@JsonProperty("Loans")
//	public List<Loan> getLoans() {
//		return (List<Loan>) plans.get(0).getLoans();
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

	public String getInisial() {
		return inisial;
	}

	public void setInisial(String inisial) {
		this.inisial = inisial;
	}

	public Set<M_par_plan> getPlans() {
		if (plans == null) {
			return null;
		} else {
		return plans;
		}
	}

	public void setPlans(Set<M_par_plan> plans) {
		this.plans = plans;
	}

}
