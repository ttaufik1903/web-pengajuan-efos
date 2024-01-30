package com.rabbai.serviceusulan.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "par_kec")
public class Par_kec implements Serializable{
 
    /**
     * 
     */
    private static final long serialVersionUID = -2517851941873251699L;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer id_dati2;
    private String name;
    private String userid;
    private LocalDateTime datepost;
    private LocalDateTime datepost_update;
    private String ket;
    
    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "id_dati2")
    @JsonIgnore
    private List<Par_dati2> nama_kabupaten;
    
    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "id_dati2")
    @JsonIgnore
    private List<Par_dati2> id_provinsi;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_dati2() {
		return id_dati2;
	}
	public void setId_dati2(Integer id_dati2) {
		this.id_dati2 = id_dati2;
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
	
	@JsonProperty("nama_kabupaten")
	public String getNama_kabupaten() {
		return nama_kabupaten.get(0).getName();
	}
	
	@JsonProperty("id_provinsi")
	public Integer getId_provinsi() {
		return id_provinsi.get(0).getId_provinsi();
	}
		

    
}
