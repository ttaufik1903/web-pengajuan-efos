package com.rabbai.serviceparefos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "par_bv_jenis_agunan", schema = "pelaporan")
public class Par_bv_jenis_agunan {

	@Id
	private String id;
	@Column(name = "keterangan")
	private String name;
	private Integer input_by;
	private String input_date;
	private String update_date;
	private Integer update_by;

	 

}
