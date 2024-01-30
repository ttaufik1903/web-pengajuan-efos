package com.brk.servicepencairan.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import javax.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "par_jenis_aktiva_ijarah", schema = "pelaporan")
public class Par_jenis_aktiva_ijarah {

	@Id
	private String id;
	@Column(name = "keterangan")
	private String name;
	private Integer input_by;
	private String input_date;
	private String update_date;
	private Integer update_by;

	 
}
