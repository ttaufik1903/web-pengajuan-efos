package com.rabbai.serviceusulan.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Hub_bank_Id implements Serializable{
 
    /**
     * 
     */
    private static final long serialVersionUID = -2517851941873251699L;
    
    private String id_loan;
    private String periode;
	public String getId_loan() {
		return id_loan;
	}
	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}
	public String getPeriode() {
		return periode;
	}
	public void setPeriode(String periode) {
		this.periode = periode;
	}
	

}
