package com.rabbai.serviceusulan.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "par_ceklist")
public class Par_ceklist implements Serializable{
 
    /**
     * 
     */
    private static final long serialVersionUID = -2517851941873251699L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer id_template_dokumen;
    private Boolean is_mandatory;
    private String name;
    
  //CHILD DATA LAMPIRAN
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "par_ceklist")
    private List<Data_lampiran_file> data_lampiran_file;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId_kategori_produk() {
		return id_template_dokumen;
	}
	public void setId_kategori_produk(Integer id_kategori_produk) {
		this.id_template_dokumen = id_kategori_produk;
	}
	public Boolean getIs_mandatory() {
		return is_mandatory;
	}
	public void setIs_mandatory(Boolean is_mandatory) {
		this.is_mandatory = is_mandatory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
	
 
}
