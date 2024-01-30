package com.brk.servicepencairan.models;

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
@Table(name = "par_bv_takeover_dari", schema = "parameter")
public class Par_bv_takeover_dari {

	@Id
	private String id;
	private String keterangan;
	private Integer input_by;
	private String input_date;
	private String update_date;
	private Integer update_by;

	 

}
