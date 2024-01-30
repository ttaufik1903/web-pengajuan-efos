package com.taufik.servicereporting.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "par_agunan_prioritas_agunan", schema="parameter")
public class Par_agunan_prioritas_agunan {

	@Id
	private String id;
	private String name;
	private Integer input_by;
	private String input_date;
	private String update_date;
	private Integer update_by;

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

	public Integer getInput_by() {
		return input_by;
	}

	public void setInput_by(Integer input_by) {
		this.input_by = input_by;
	}

	public String getInput_date() {
		return input_date;
	}

	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public Integer getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(Integer update_by) {
		this.update_by = update_by;
	}

}
