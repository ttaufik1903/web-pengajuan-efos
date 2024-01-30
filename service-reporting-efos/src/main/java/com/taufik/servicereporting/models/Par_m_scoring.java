package com.taufik.servicereporting.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "par_m_scoring")
public class Par_m_scoring {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String status;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "par_m_scoring")
    private List<Par_rincian_scoring> rincian_scoring;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Par_rincian_scoring> getRincian_scoring() {
		return rincian_scoring;
	}
	public void setRincian_scoring(List<Par_rincian_scoring> rincian_scoring) {
		this.rincian_scoring = rincian_scoring;
	}
	
}
