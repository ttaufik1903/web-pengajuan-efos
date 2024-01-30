package com.brk.servicepencairan.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "par_bv_golongan_piutang", schema = "pelaporan")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Par_bv_golongan_piutang {

	@Id
	private String id;
	@Column(name = "keterangan")
	private String name;
	private Integer input_by;
	private String input_date;
	private String update_date;
	private Integer update_by;

	 

}
