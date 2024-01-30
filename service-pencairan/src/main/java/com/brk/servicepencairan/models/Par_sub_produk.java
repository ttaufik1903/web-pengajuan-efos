package com.brk.servicepencairan.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "par_sub_produk")
public class Par_sub_produk {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable=true)
    private Integer id;
    private String name;
    
	private Integer id_produk;
	
	//CHILD PAR PLAN
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "par_sub_produk")
    private List<Par_plan> par_plan;
    
    //PARENT PAR SUB PRODUK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produk", insertable = false, updatable = false)
    private Par_produk par_produk;
  
    @JsonProperty("nama_produk")
	public String getPar_produk() {
		return par_produk.getName();
	}
	
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

	public Integer getId_produk() {
		return id_produk;
	}
	public void setId_produk(Integer id_produk) {
		this.id_produk = id_produk;
	}
	
	
}
