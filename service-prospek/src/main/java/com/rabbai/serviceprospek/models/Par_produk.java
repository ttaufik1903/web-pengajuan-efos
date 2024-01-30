package com.rabbai.serviceprospek.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name = "par_produk")
public class Par_produk {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    
    //CHILD PAR PLAN
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "par_produk")
    private List<Par_sub_produk> par_sub_produk;
    
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
    
    
}

