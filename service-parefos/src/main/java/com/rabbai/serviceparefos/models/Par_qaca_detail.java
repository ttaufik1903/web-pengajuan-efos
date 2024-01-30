package com.rabbai.serviceparefos.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "par_qaca_detail")
public class Par_qaca_detail implements Serializable{
 
    /**
     * 
     */
    private static final long serialVersionUID = -2517851941873251699L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String dukument_id;
    private String name;
    private Integer type;
 
    
    @ManyToOne(fetch = FetchType.LAZY)
 	@JoinColumn(name = "dukument_id", insertable = false, updatable = false, nullable = true)
 	private Par_qaca_group par_qaca_group;

 	@JsonProperty("desc_qaca_group")
 	public String getDesc_qaca_group() {
 		return par_qaca_group.getName();
 	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDukument_id() {
		return dukument_id;
	}

	public void setDukument_id(String dukument_id) {
		this.dukument_id = dukument_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
    
 
}
