package com.rabbai.serviceusulan.models.mkm;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "m_data_wallet")
public class M_data_wallet implements Serializable {

	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String id_debitur;
	private String lr_start;
	private String lr_finish;
	private Double lr_ppbd;
	private Double lr_phpp;
	private Double lr_hppe;
	private Double lr_lako;
	private Double lr_bygk;
	private Double lr_byse;
	private Double lr_byte;
	private Double lr_bype;
	private Double lr_byli;
	private Double lr_bytr;
	private Double lr_byla;
	private Double lr_tbeu;
	private Double lr_lbdu;
	private String lr_kpla1;
	private String lr_kpla2;
	private String lr_kpla3;
	private String lr_kpla4;
	private String lr_kpla5;
	private Double lr_pela1;
	private Double lr_pela2;
	private Double lr_pela3;
	private Double lr_pela4;
	private Double lr_pela5;
	private Double lr_anbl;
	private Double lr_bert;
	private Double lr_bela;
	private Double lr_pbela;
	private Double lr_laru;
	private Double lr_lbpb;
	private Double lr_pajk;
	private Double lr_lbsp;
	private Double lr_ebda;
	private Double wa_laba;
	private Double wa_posa;
	private Double wa_emco;
	private Double wa_toco;
	private Double rk_cura;
	private Double rk_dsra;
	private Double rk_dsco;
	
	public M_data_wallet() {
		super();
	}
	
	public M_data_wallet (String id_debitur, String lr_start, String lr_finish, Double lr_ppbd, Double lr_phpp, Double lr_hppe, 
			Double lr_lako, Double lr_bygk, Double lr_byse, Double lr_byte, Double lr_bype, Double lr_byli, Double lr_bytr, 
			Double lr_byla, Double lr_tbeu, Double lr_lbdu, String lr_kpla1, String lr_kpla2, String lr_kpla3, String lr_kpla4, 
			String lr_kpla5, Double lr_pela1, Double lr_pela2, Double lr_pela3, Double lr_pela4, Double lr_pela5, Double lr_anbl, 
			Double lr_bert, Double lr_bela, Double lr_pbela, Double lr_laru, Double lr_lbpb, Double lr_pajk, Double lr_lbsp, 
			Double lr_ebda, Double wa_laba, Double wa_posa, Double wa_emco, Double wa_toco, Double rk_cura, Double rk_dsra, Double rk_dsco) {
		
		this.id_debitur= id_debitur;
		this.lr_start= lr_start;
		this.lr_finish= lr_finish;
		this.lr_ppbd= lr_ppbd;
		this.lr_phpp= lr_phpp;
		this.lr_hppe= lr_hppe;
		this.lr_lako= lr_lako;
		this.lr_bygk= lr_bygk;
		this.lr_byse= lr_byse;
		this.lr_byte= lr_byte;
		this.lr_bype= lr_bype;
		this.lr_byli= lr_byli;
		this.lr_bytr= lr_bytr;
		this.lr_byla= lr_byla;
		this.lr_tbeu= lr_tbeu;
		this.lr_lbdu= lr_lbdu;
		this.lr_kpla1= lr_kpla1;
		this.lr_kpla2= lr_kpla2;
		this.lr_kpla3= lr_kpla3;
		this.lr_kpla4= lr_kpla4;
		this.lr_kpla5= lr_kpla5;
		this.lr_pela1= lr_pela1;
		this.lr_pela2= lr_pela2;
		this.lr_pela3= lr_pela3;
		this.lr_pela4= lr_pela4;
		this.lr_pela5= lr_pela5;
		this.lr_anbl= lr_anbl;
		this.lr_bert= lr_bert;
		this.lr_bela= lr_bela;
		this.lr_pbela= lr_pbela;
		this.lr_laru= lr_laru;
		this.lr_lbpb= lr_lbpb;
		this.lr_pajk= lr_pajk;
		this.lr_lbsp= lr_lbsp;
		this.lr_ebda= lr_ebda;
		this.wa_laba= wa_laba;
		this.wa_posa= wa_posa;
		this.wa_emco= wa_emco;
		this.wa_toco= wa_toco;
		this.rk_cura= rk_cura;
		this.rk_dsra= rk_dsra;
		this.rk_dsco= rk_dsco;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getId_debitur() {
		return id_debitur;
	}
	public void setId_debitur(String id_debitur) {
		this.id_debitur = id_debitur;
	}
	public String getLr_start() {
		return lr_start;
	}
	public void setLr_start(String lr_start) {
		this.lr_start = lr_start;
	}
	public String getLr_finish() {
		return lr_finish;
	}
	public void setLr_finish(String lr_finish) {
		this.lr_finish = lr_finish;
	}
	public Double getLr_ppbd() {
		return lr_ppbd;
	}
	public void setLr_ppbd(Double lr_ppbd) {
		this.lr_ppbd = lr_ppbd;
	}
	public Double getLr_phpp() {
		return lr_phpp;
	}
	public void setLr_phpp(Double lr_phpp) {
		this.lr_phpp = lr_phpp;
	}
	public Double getLr_hppe() {
		return lr_hppe;
	}
	public void setLr_hppe(Double lr_hppe) {
		this.lr_hppe = lr_hppe;
	}
	public Double getLr_lako() {
		return lr_lako;
	}
	public void setLr_lako(Double lr_lako) {
		this.lr_lako = lr_lako;
	}
	public Double getLr_bygk() {
		return lr_bygk;
	}
	public void setLr_bygk(Double lr_bygk) {
		this.lr_bygk = lr_bygk;
	}
	public Double getLr_byse() {
		return lr_byse;
	}
	public void setLr_byse(Double lr_byse) {
		this.lr_byse = lr_byse;
	}
	public Double getLr_byte() {
		return lr_byte;
	}
	public void setLr_byte(Double lr_byte) {
		this.lr_byte = lr_byte;
	}
	public Double getLr_bype() {
		return lr_bype;
	}
	public void setLr_bype(Double lr_bype) {
		this.lr_bype = lr_bype;
	}
	public Double getLr_byli() {
		return lr_byli;
	}
	public void setLr_byli(Double lr_byli) {
		this.lr_byli = lr_byli;
	}
	public Double getLr_bytr() {
		return lr_bytr;
	}
	public void setLr_bytr(Double lr_bytr) {
		this.lr_bytr = lr_bytr;
	}
	public Double getLr_byla() {
		return lr_byla;
	}
	public void setLr_byla(Double lr_byla) {
		this.lr_byla = lr_byla;
	}
	public Double getLr_tbeu() {
		return lr_tbeu;
	}
	public void setLr_tbeu(Double lr_tbeu) {
		this.lr_tbeu = lr_tbeu;
	}
	public Double getLr_lbdu() {
		return lr_lbdu;
	}
	public void setLr_lbdu(Double lr_lbdu) {
		this.lr_lbdu = lr_lbdu;
	}

	public Double getLr_anbl() {
		return lr_anbl;
	}
	public void setLr_anbl(Double lr_anbl) {
		this.lr_anbl = lr_anbl;
	}
	public Double getLr_bert() {
		return lr_bert;
	}
	public void setLr_bert(Double lr_bert) {
		this.lr_bert = lr_bert;
	}
	public Double getLr_bela() {
		return lr_bela;
	}
	public void setLr_bela(Double lr_bela) {
		this.lr_bela = lr_bela;
	}
	public Double getLr_pbela() {
		return lr_pbela;
	}
	public void setLr_pbela(Double lr_pbela) {
		this.lr_pbela = lr_pbela;
	}
	public Double getLr_laru() {
		return lr_laru;
	}
	public void setLr_laru(Double lr_laru) {
		this.lr_laru = lr_laru;
	}
	public Double getLr_lbpb() {
		return lr_lbpb;
	}
	public void setLr_lbpb(Double lr_lbpb) {
		this.lr_lbpb = lr_lbpb;
	}
	public Double getLr_pajk() {
		return lr_pajk;
	}
	public void setLr_pajk(Double lr_pajk) {
		this.lr_pajk = lr_pajk;
	}
	public Double getLr_lbsp() {
		return lr_lbsp;
	}
	public void setLr_lbsp(Double lr_lbsp) {
		this.lr_lbsp = lr_lbsp;
	}
	public Double getLr_ebda() {
		return lr_ebda;
	}
	public void setLr_ebda(Double lr_ebda) {
		this.lr_ebda = lr_ebda;
	}
	public Double getWa_laba() {
		return wa_laba;
	}
	public void setWa_laba(Double wa_laba) {
		this.wa_laba = wa_laba;
	}
	public Double getWa_posa() {
		return wa_posa;
	}
	public void setWa_posa(Double wa_posa) {
		this.wa_posa = wa_posa;
	}
	public Double getWa_emco() {
		return wa_emco;
	}
	public void setWa_emco(Double wa_emco) {
		this.wa_emco = wa_emco;
	}
	public Double getWa_toco() {
		return wa_toco;
	}
	public void setWa_toco(Double wa_toco) {
		this.wa_toco = wa_toco;
	}
	public Double getRk_cura() {
		return rk_cura;
	}
	public void setRk_cura(Double rk_cura) {
		this.rk_cura = rk_cura;
	}
	public Double getRk_dsra() {
		return rk_dsra;
	}
	public void setRk_dsra(Double rk_dsra) {
		this.rk_dsra = rk_dsra;
	}
	public Double getRk_dsco() {
		return rk_dsco;
	}
	public void setRk_dsco(Double rk_dsco) {
		this.rk_dsco = rk_dsco;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getLr_kpla1() {
		return lr_kpla1;
	}
	public void setLr_kpla1(String lr_kpla1) {
		this.lr_kpla1 = lr_kpla1;
	}
	public String getLr_kpla2() {
		return lr_kpla2;
	}
	public void setLr_kpla2(String lr_kpla2) {
		this.lr_kpla2 = lr_kpla2;
	}
	public String getLr_kpla3() {
		return lr_kpla3;
	}
	public void setLr_kpla3(String lr_kpla3) {
		this.lr_kpla3 = lr_kpla3;
	}
	public String getLr_kpla4() {
		return lr_kpla4;
	}
	public void setLr_kpla4(String lr_kpla4) {
		this.lr_kpla4 = lr_kpla4;
	}
	public String getLr_kpla5() {
		return lr_kpla5;
	}
	public void setLr_kpla5(String lr_kpla5) {
		this.lr_kpla5 = lr_kpla5;
	}
	public Double getLr_pela1() {
		return lr_pela1;
	}
	public void setLr_pela1(Double lr_pela1) {
		this.lr_pela1 = lr_pela1;
	}
	public Double getLr_pela2() {
		return lr_pela2;
	}
	public void setLr_pela2(Double lr_pela2) {
		this.lr_pela2 = lr_pela2;
	}
	public Double getLr_pela3() {
		return lr_pela3;
	}
	public void setLr_pela3(Double lr_pela3) {
		this.lr_pela3 = lr_pela3;
	}
	public Double getLr_pela4() {
		return lr_pela4;
	}
	public void setLr_pela4(Double lr_pela4) {
		this.lr_pela4 = lr_pela4;
	}
	public Double getLr_pela5() {
		return lr_pela5;
	}
	public void setLr_pela5(Double lr_pela5) {
		this.lr_pela5 = lr_pela5;
	}

}
