package com.brk.servicepencairan.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "pelaporan.par_kode_sektor_ekonomi_bi")
public class Par_kode_sektor_ekonomi_bi {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String kode_akad;
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
	public String getKode_akad() {
		return kode_akad;
	}
	public void setKode_akad(String kode_akad) {
		this.kode_akad = kode_akad;
	}
	
    
}
