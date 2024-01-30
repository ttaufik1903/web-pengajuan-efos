package com.brk.servicepencairan.models;

import com.brk.servicepencairan.mis.domain.Mis;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.data.domain.Page;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage {

    private String kode;
    private String pesan;
   
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



}
