package com.rabbai.serviceparefos.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "par_akad")
public class Par_akad {

	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String inisial;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "par_akads")
	@JsonIgnore
	private Set<Par_kode_margin> par_kode_margins = new HashSet<>();

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

	public Set<Par_kode_margin> getPar_kode_margins() {
		return par_kode_margins;
	}

	public void setPar_kode_margins(Set<Par_kode_margin> par_kode_margins) {
		this.par_kode_margins = par_kode_margins;
	}

}
