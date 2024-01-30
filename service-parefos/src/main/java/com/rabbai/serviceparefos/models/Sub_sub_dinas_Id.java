package com.rabbai.serviceparefos.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Sub_sub_dinas_Id implements Serializable{
 
    /**
     * 
     */
    private static final long serialVersionUID = -2517851941873251699L;
    
    private Integer id;
    private String id_cabang;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getId_cabang() {
		return id_cabang;
	}
	public void setId_cabang(String id_cabang) {
		this.id_cabang = id_cabang;
	}

	

}
