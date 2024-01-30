package com.rabbai.serviceparefos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabbai.serviceparefos.models.Par_bagian_dijamin;
import com.rabbai.serviceparefos.models.Par_rating_pihak_lawan;

public interface Par_bagian_dijaminRepository extends JpaRepository<Par_bagian_dijamin, String> {

}
