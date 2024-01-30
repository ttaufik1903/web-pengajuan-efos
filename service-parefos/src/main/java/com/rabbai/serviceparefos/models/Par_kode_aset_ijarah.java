package com.rabbai.serviceparefos.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "par_kode_aset_ijarah", schema = "public")
public class Par_kode_aset_ijarah {

	@Id
	private String id;
	private String name;
	private String userid;
	private String datepost;
	private String ket;

}
