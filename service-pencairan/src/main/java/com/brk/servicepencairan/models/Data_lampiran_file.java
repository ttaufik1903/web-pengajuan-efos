package com.brk.servicepencairan.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "data_lampiran_file")
public class Data_lampiran_file {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer id_ceklis;
	private String nama_file;
	private String upload_by;
	private LocalDateTime tgl_upload;
	private String id_loan;
	private String path_file;
	private String desc_file;
	private Integer ceklis_admin;

	// PARENT PAR ceklist
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ceklis", insertable = false, updatable = false, nullable = true)
	@OrderBy("id ASC")
	private Par_ceklist par_ceklist;

	@JsonProperty("nama_ceklist")
	public String getNama_ceklist() {
		if (par_ceklist == null) {
			return "";
		} else {
			return par_ceklist.getName();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_ceklis() {
		return id_ceklis;
	}

	public void setId_ceklis(Integer id_ceklis) {
		this.id_ceklis = id_ceklis;
	}

	public String getNama_file() {
		return nama_file;
	}

	public void setNama_file(String nama_file) {
		this.nama_file = nama_file;
	}

	public String getUpload_by() {
		return upload_by;
	}

	public void setUpload_by(String upload_by) {
		this.upload_by = upload_by;
	}

	public LocalDateTime getTgl_upload() {
		return tgl_upload;
	}

	public void setTgl_upload(LocalDateTime tgl_upload) {
		this.tgl_upload = tgl_upload;
	}

	public String getId_loan() {
		return id_loan;
	}

	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}

	public String getPath_file() {
		return path_file;
	}

	public void setPath_file(String path_file) {
		this.path_file = path_file;
	}

	public String getDesc_file() {
		return desc_file;
	}

	public void setDesc_file(String desc_file) {
		this.desc_file = desc_file;
	}

	public Integer getCeklis_admin() {
		return ceklis_admin;
	}

	public void setCeklis_admin(Integer ceklis_admin) {
		this.ceklis_admin = ceklis_admin;
	}



}
