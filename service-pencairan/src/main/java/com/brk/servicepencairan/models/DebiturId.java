package com.brk.servicepencairan.models;

import java.io.Serializable;

public class DebiturId implements Serializable{
 
    /**
     * 
     */
    private static final long serialVersionUID = -2517851941873251699L;
    
    private String id_loan;
    private Integer id_rincian_scoring;
	public String getId_loan() {
		return id_loan;
	}
	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}
	public Integer getId_rincian_scoring() {
		return id_rincian_scoring;
	}
	public void setId_rincian_scoring(Integer id_rincian_scoring) {
		this.id_rincian_scoring = id_rincian_scoring;
	}

}
