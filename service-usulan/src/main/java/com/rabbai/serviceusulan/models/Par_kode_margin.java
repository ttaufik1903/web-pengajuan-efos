package com.rabbai.serviceusulan.models;

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
@Table(name = "par_kode_margin", schema = "public")
public class Par_kode_margin {

	@Id
	private String id;
	private String name;
	private BigDecimal nilai;
	private String userid;
	private String datepost;
	private String ket;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "par_kode_margin_akad", joinColumns = @JoinColumn(name = "id_kode_margin"), inverseJoinColumns = @JoinColumn(name = "id_akad"))
	private Set<Par_akad> par_akads = new HashSet<>();
}
