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
@Table(name = "par_bv_jenis_garansi", schema = "parameter")
public class Par_bv_jenis_garansi {

	@Id
	private String id;
	@Column(name = "keterangan")
	private String name;
	private Integer input_by;
	private String input_date;
	private String update_date;
	private Integer update_by;

 
}
