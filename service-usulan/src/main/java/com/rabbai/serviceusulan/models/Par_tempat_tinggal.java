package com.rabbai.serviceusulan.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "par_tempat_tinggal")
public class Par_tempat_tinggal {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    
  //CHILD DEBITUR
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "par_tempat_tinggal")
    private List<Debitur> debitur;
    
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
   
}
