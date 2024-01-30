package com.rabbai.serviceparefos.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
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
@Table(name = "par_bv_kode_notaris", schema = "parameter")
public class Par_bv_kode_notaris {

	@Id
	private String id;
	private String name;
	private String input_by;
	private LocalDateTime input_date;
	private LocalDateTime update_date;
	private String update_by;

}
