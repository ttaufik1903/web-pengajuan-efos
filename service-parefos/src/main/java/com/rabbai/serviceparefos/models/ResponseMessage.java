package com.rabbai.serviceparefos.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import org.springframework.data.domain.Page;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage {

    private String kode;
    private String pesan;
    private Par_agama par_agama;
    private Page<Par_agama> par_agama_page;
    private List<Par_agama> par_agama_list;
    private String data;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

	public Par_agama getPar_agama() {
		return par_agama;
	}

	public void setPar_agama(Par_agama par_agama) {
		this.par_agama = par_agama;
	}

	public Page<Par_agama> getPar_agama_page() {
		return par_agama_page;
	}

	public void setPar_agama_page(Page<Par_agama> par_agama_page) {
		this.par_agama_page = par_agama_page;
	}

	public List<Par_agama> getPar_agama_list() {
		return par_agama_list;
	}

	public void setPar_agama_list(List<Par_agama> par_agama_list) {
		this.par_agama_list = par_agama_list;
	}




}
