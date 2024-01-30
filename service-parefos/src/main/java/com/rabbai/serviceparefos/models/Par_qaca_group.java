package com.rabbai.serviceparefos.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "par_qaca_group")
public class Par_qaca_group {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String userid;
    private LocalDateTime datepost;
    private LocalDateTime datepost_update;
    private String ket;
    private Integer type;
    
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public LocalDateTime getDatepost() {
		return datepost;
	}
	public void setDatepost(LocalDateTime datepost) {
		this.datepost = datepost;
	}
	public LocalDateTime getDatepost_update() {
		return datepost_update;
	}
	public void setDatepost_update(LocalDateTime datepost_update) {
		this.datepost_update = datepost_update;
	}
	public String getKet() {
		return ket;
	}
	public void setKet(String ket) {
		this.ket = ket;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
    
    
}
