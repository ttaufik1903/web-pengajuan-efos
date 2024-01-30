package com.brk.servicepencairan.models;

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
@Table(name = "par_kel")
public class Par_kel implements Serializable{
 
    /**
     * 
     */
    private static final long serialVersionUID = -2517851941873251699L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer id_kec;
    private String name;
    private String userid;
    private LocalDateTime datepost;
    private LocalDateTime datepost_update;
    private String ket;
    
    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "id_kec")
    @JsonIgnore
    private List<Par_kec> nama_kecamatan;
    
    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "id_kec")
    @JsonIgnore
    private List<Par_kec> id_provinsi;
    
    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "id_kec")
    @JsonIgnore
    private List<Par_kec> id_dati2;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getId_kec() {
		return id_kec;
	}
	public void setId_kec(Integer id_kec) {
		this.id_kec = id_kec;
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
	
	@JsonProperty("nama_kecamatan")
	public String getNama_kecamatan() {
		return nama_kecamatan.get(0).getName();
	}
	
	@JsonProperty("id_provinsi")
	public Integer getId_provinsi() {
		return id_provinsi.get(0).getId_provinsi();
	}
	
	@JsonProperty("id_dati2")
	public Integer getId_dati2() {
		return id_dati2.get(0).getId_dati2();
	}
    
}
