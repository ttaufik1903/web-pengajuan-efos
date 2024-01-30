package com.rabbai.serviceparefos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabbai.serviceparefos.models.Par_bv_golongan_piutang;
import com.rabbai.serviceparefos.models.Par_bv_tujuan_kredit;

public interface Par_bv_tujuan_kreditRepository extends JpaRepository<Par_bv_tujuan_kredit, String> {

}
