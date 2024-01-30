package com.brk.servicepencairan.mis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROFILE_MASTER")
public class Mis {
    @Id
    @Column(name = "NOREK")
    private String norek;
    @Column(name = "NAMA")
    private String nama;

    public String getNorek() {
        return norek;
    }

    public void setNorek(String norek) {
        this.norek = norek;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}