package com.rabbai.serviceparefos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabbai.serviceparefos.models.Par_bv_golongan_piutang;
import com.rabbai.serviceparefos.models.Par_lokasi_pengguna;

public interface Par_lokasi_penggunaRepository  extends JpaRepository<Par_lokasi_pengguna, String>{

}
