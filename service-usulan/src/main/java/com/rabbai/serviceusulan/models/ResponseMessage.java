package com.rabbai.serviceusulan.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rabbai.serviceusulan.mis.domain.Mis;

import org.springframework.data.domain.Page;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage {

    private String kode;
    private String pesan;
    private Debitur debitur;
    private Mis mis;
    private Page<Debitur> instansis_list;

    public Mis getMis() {
        return mis;
    }

    public void setMis(Mis mis) {
        this.mis = mis;
    }

    public Debitur getDebitur() {
        return debitur;
    }

    public void setDebitur(Debitur debitur) {
        this.debitur = debitur;
    }

    public Page<Debitur> getInstansisList() {
        return instansis_list;
    }

    public void setInstansisList(Page<Debitur> instansisList) {
        this.instansis_list = instansisList;
    }

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

	/*public Instansis getSaldo() {
		return instansis;
	}

	public void setSaldo(Instansis instansis) {
		this.instansis = instansis;
	}*/


}
