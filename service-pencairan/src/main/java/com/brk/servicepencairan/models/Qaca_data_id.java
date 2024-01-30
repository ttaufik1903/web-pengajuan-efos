package com.brk.servicepencairan.models;

import java.io.Serializable;

public class Qaca_data_id implements Serializable{
 
    /**
     * 
     */
    private static final long serialVersionUID = -2517851941873251699L;
    
    private String id_loan;
    private Integer id_qaca;
    
	public String getId_loan() {
		return id_loan;
	}
	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}
	public Integer getId_qaca() {
		return id_qaca;
	}
	public void setId_qaca(Integer id_qaca) {
		this.id_qaca = id_qaca;
	}


}
