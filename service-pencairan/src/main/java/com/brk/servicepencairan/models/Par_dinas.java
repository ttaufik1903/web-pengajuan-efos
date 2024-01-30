package com.brk.servicepencairan.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "par_dinas")
public class Par_dinas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String id_cabang;
    private String name;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_cabang() {
		return id_cabang;
	}
	public void setId_cabang(String id_cabang) {
		this.id_cabang = id_cabang;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
	
}

