package com.taufik.servicereporting.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "par_akad")
public class Par_akad {

	@Id
	private Integer id;
	private String name;
	private String inisial;

	@OneToMany(mappedBy = "par_akad", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Par_plan> plans;
	
	public Integer getId() {
		return id;
	}

	public Set<Par_plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Par_plan> plans) {
		this.plans = (Set<Par_plan>) plans;
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

}
