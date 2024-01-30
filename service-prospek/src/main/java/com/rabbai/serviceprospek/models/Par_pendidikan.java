package com.rabbai.serviceprospek.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "par_pendidikan")
public class Par_pendidikan {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String userid;
    private LocalDateTime datepost;
    private LocalDateTime datepost_update;
    private String ket;
    
  //CHILD DEBITUR
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "par_pendidikan")
    private List<Debitur> debitur;
    
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
