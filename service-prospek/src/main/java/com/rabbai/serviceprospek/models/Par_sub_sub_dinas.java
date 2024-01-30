package com.rabbai.serviceprospek.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "par_sub_sub_dinas")
public class Par_sub_sub_dinas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String id_sub_sub_dinas;
    private String id_sub_dinas;
    private String id_cabang;
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getId_sub_dinas() {
		return id_sub_dinas;
	}
	public void setId_sub_dinas(String id_sub_dinas) {
		this.id_sub_dinas = id_sub_dinas;
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
	public String getId_sub_sub_dinas() {
		return id_sub_sub_dinas;
	}
	public void setId_sub_sub_dinas(String id_sub_sub_dinas) {
		this.id_sub_sub_dinas = id_sub_sub_dinas;
	}
    
}
