package com.taufik.servicereporting.models.mkm;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "par_kode_referal")
public class M_par_kode_referal {

	@Id
	private String id;
	private String name;
	private String input_by;
	private String input_date;
	private Double target_mkm;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "par_kode_referal")
	private List<M_data_loan> loans = new ArrayList<>();
	
	

	public List<M_data_loan> getLoans() {
		return loans;
	}
	
	public Long getRealisasi() {
		Double realisasi = Double.parseDouble("0") ;
		for (int i = 0; i < loans.size(); i++) {
			realisasi += loans.get(i).getPlafon_disetujui();
//			realisasi = realisasi.add(loans.get(i).getPlafon_disetujui());
		}
		realisasi=realisasi;
		
		return realisasi.longValue();
	}

	public void setLoans(List<M_data_loan> loans) {
		this.loans = loans;
	}

	

	public String getInput_by() {
		return input_by;
	}

	public void setInput_by(String input_by) {
		this.input_by = input_by;
	}

	public String getInput_date() {
		return input_date;
	}

	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTarget_mkm() {
		return target_mkm;
	}

	public void setTarget_mkm(Double target_mkm) {
		this.target_mkm = target_mkm;
	}



}
