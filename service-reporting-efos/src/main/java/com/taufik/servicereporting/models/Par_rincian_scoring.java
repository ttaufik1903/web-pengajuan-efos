package com.taufik.servicereporting.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "par_rincian_scoring")
public class Par_rincian_scoring implements Serializable{
 
    /**
     * 
     */
    private static final long serialVersionUID = -2517851941873251699L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String id_m_scoring;
    private String rincian;
    private String skor;
    private String type;
    private String value;
    private Integer min;
    private Integer max;


 
 	
    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_m_scoring", insertable = false, updatable = false)
    private Par_m_scoring par_m_scoring;
	
	@JsonProperty("nama_scoring")
 	public String getNama_scoring() {
 		return par_m_scoring.getName();
 	}
    
//    @OneToMany
//    @JoinColumn(name = "id", referencedColumnName = "id_m_scoring", insertable = false, updatable = false)
//    @JsonIgnore
//    private List<Par_m_scoring> nama_scoring;
//    
//    @JsonProperty("nama_scoring")
//	public String getNama_scoring() {
//		return nama_scoring.get(0).getName();
//	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getId_m_scoring() {
		return id_m_scoring;
	}
	public void setId_m_scoring(String id_m_scoring) {
		this.id_m_scoring = id_m_scoring;
	}
	public String getRincian() {
		return rincian;
	}
	public void setRincian(String rincian) {
		this.rincian = rincian;
	}
	public String getSkor() {
		return skor;
	}
	public void setSkor(String skor) {
		this.skor = skor;
	}

	
}
