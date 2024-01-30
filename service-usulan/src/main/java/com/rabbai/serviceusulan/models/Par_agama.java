package com.rabbai.serviceusulan.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "par_agama")
public class Par_agama {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String userid;
    private LocalDateTime datepost;
    private LocalDateTime datepost_update;
    
  //CHILD DEBITUR
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "par_agama")
    private List<Debitur> debitur;
    
    private String ket;
    
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
