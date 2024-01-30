package com.rabbai.serviceusulan.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "par_gol_debitur_sid", schema = "pelaporan")
public class Par_gol_debitur_sid {

	@Id
	private String id;
//	@Column(name = "keterangan")
	private String name;


}
