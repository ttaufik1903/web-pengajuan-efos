package com.taufik.servicereporting.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "par_status_perusahaan")
public class Par_status_perusahaan {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String userid;
    private LocalDateTime datepost;
    private LocalDateTime datepost_update;
    private String ket;
    
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
    
}
