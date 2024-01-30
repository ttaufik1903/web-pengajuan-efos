package com.rabbai.serviceusulan.models.mkm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "m_data_neraca")
public class M_data_neraca implements Serializable {

	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String id_debitur;
	private String s_tahun;
	private Double s_kas_dan_bank;
	private Double s_piutang_dagang;
	private Double s_persediaan;
	private Double s_perlengkapan;
	private Double s_total_harta_lancar;
	private Double s_harta_tetap_bersih;
	private Double s_aktiva_lain_lain;
	private Double s_total_harta_tetap;
	private Double s_total_harta;
	private Double s_kredit_bjpb;
	private Double s_kredit_bjpbl;
	private Double s_hutang_dagang;
	private Double s_total_hutang_lancar;
	private Double s_kbjpb;
	private Double s_hbjpbl;
	private Double s_thjp;
	private Double s_total_hutang;
	private Double s_modal_disetor;
	private Double s_laba_yang_ditahan;
	private Double s_laba_tahun_berjalan;
	private Double s_total_modal;
	private Double s_total_hutang_modal;
	private String f_tahun;
	private Double f_kas_dan_bank;
	private Double f_piutang_dagang;
	private Double f_persediaan;
	private Double f_perlengkapan;
	private Double f_total_harta_lancar;
	private Double f_harta_tetap_bersih;
	private Double f_aktiva_lain_lain;
	private Double f_total_harta_tetap;
	private Double f_total_harta;
	private Double f_kredit_bjpb;
	private Double f_kredit_bjpbl;
	private Double f_hutang_dagang;
	private Double f_total_hutang_lancar;
	private Double f_kbjpb;
	private Double f_hbjpbl;
	private Double f_thjp;
	private Double f_total_hutang;
	private Double f_modal_disetor;
	private Double f_laba_yang_ditahan;
	private Double f_laba_tahun_berjalan;
	private Double f_total_modal;
	private Double f_total_hutang_modal;
	
	public M_data_neraca() {
		super();
	}
	
	public M_data_neraca (String id_debitur, String s_tahun, Double s_kas_dan_bank, Double s_piutang_dagang, Double s_persediaan, 
			Double s_perlengkapan, Double s_total_harta_lancar, Double s_harta_tetap_bersih, Double s_aktiva_lain_lain, 
			Double s_total_harta_tetap, Double s_total_harta, Double s_kredit_bjpb, Double s_kredit_bjpbl, Double s_hutang_dagang, 
			Double s_total_hutang_lancar, Double s_kbjpb, Double s_hbjpbl, Double s_thjp, Double s_total_hutang, Double s_modal_disetor, 
			Double s_laba_yang_ditahan, Double s_laba_tahun_berjalan, Double s_total_modal, Double s_total_hutang_modal, String f_tahun, 
			Double f_kas_dan_bank, Double f_piutang_dagang, Double f_persediaan, Double f_perlengkapan, Double f_total_harta_lancar, 
			Double f_harta_tetap_bersih, Double f_aktiva_lain_lain, Double f_total_harta_tetap, Double f_total_harta, Double f_kredit_bjpb, 
			Double f_kredit_bjpbl, Double f_hutang_dagang, Double f_total_hutang_lancar, Double f_kbjpb, Double f_hbjpbl, Double f_thjp, 
			Double f_total_hutang, Double f_modal_disetor, Double f_laba_yang_ditahan, Double f_laba_tahun_berjalan, Double f_total_modal, 
			Double f_total_hutang_modal) {

		this.id_debitur=id_debitur;
		this.s_tahun=s_tahun;
		this.s_kas_dan_bank=s_kas_dan_bank;
		this.s_piutang_dagang=s_piutang_dagang;
		this.s_persediaan=s_persediaan;
		this.s_perlengkapan=s_perlengkapan;
		this.s_total_harta_lancar=s_total_harta_lancar;
		this.s_harta_tetap_bersih=s_harta_tetap_bersih;
		this.s_aktiva_lain_lain=s_aktiva_lain_lain;
		this.s_total_harta_tetap=s_total_harta_tetap;
		this.s_total_harta=s_total_harta;
		this.s_kredit_bjpb=s_kredit_bjpb;
		this.s_kredit_bjpbl=s_kredit_bjpbl;
		this.s_hutang_dagang=s_hutang_dagang;
		this.s_total_hutang_lancar=s_total_hutang_lancar;
		this.s_kbjpb=s_kbjpb;
		this.s_hbjpbl=s_hbjpbl;
		this.s_thjp=s_thjp;
		this.s_total_hutang=s_total_hutang;
		this.s_modal_disetor=s_modal_disetor;
		this.s_laba_yang_ditahan=s_laba_yang_ditahan;
		this.s_laba_tahun_berjalan=s_laba_tahun_berjalan;
		this.s_total_modal=s_total_modal;
		this.s_total_hutang_modal=s_total_hutang_modal;
		this.f_tahun=f_tahun;
		this.f_kas_dan_bank=f_kas_dan_bank;
		this.f_piutang_dagang=f_piutang_dagang;
		this.f_persediaan=f_persediaan;
		this.f_perlengkapan=f_perlengkapan;
		this.f_total_harta_lancar=f_total_harta_lancar;
		this.f_harta_tetap_bersih=f_harta_tetap_bersih;
		this.f_aktiva_lain_lain=f_aktiva_lain_lain;
		this.f_total_harta_tetap=f_total_harta_tetap;
		this.f_total_harta=f_total_harta;
		this.f_kredit_bjpb=f_kredit_bjpb;
		this.f_kredit_bjpbl=f_kredit_bjpbl;
		this.f_hutang_dagang=f_hutang_dagang;
		this.f_total_hutang_lancar=f_total_hutang_lancar;
		this.f_kbjpb=f_kbjpb;
		this.f_hbjpbl=f_hbjpbl;
		this.f_thjp=f_thjp;
		this.f_total_hutang=f_total_hutang;
		this.f_modal_disetor=f_modal_disetor;
		this.f_laba_yang_ditahan=f_laba_yang_ditahan;
		this.f_laba_tahun_berjalan=f_laba_tahun_berjalan;
		this.f_total_modal=f_total_modal;
		this.f_total_hutang_modal=f_total_hutang_modal;
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
	public String getS_tahun() {
		return s_tahun;
	}
	public void setS_tahun(String s_tahun) {
		this.s_tahun = s_tahun;
	}
	public Double getS_kas_dan_bank() {
		return s_kas_dan_bank;
	}
	public void setS_kas_dan_bank(Double s_kas_dan_bank) {
		this.s_kas_dan_bank = s_kas_dan_bank;
	}
	public Double getS_piutang_dagang() {
		return s_piutang_dagang;
	}
	public void setS_piutang_dagang(Double s_piutang_dagang) {
		this.s_piutang_dagang = s_piutang_dagang;
	}
	public Double getS_persediaan() {
		return s_persediaan;
	}
	public void setS_persediaan(Double s_persediaan) {
		this.s_persediaan = s_persediaan;
	}
	public Double getS_perlengkapan() {
		return s_perlengkapan;
	}
	public void setS_perlengkapan(Double s_perlengkapan) {
		this.s_perlengkapan = s_perlengkapan;
	}
	public Double getS_total_harta_lancar() {
		return s_total_harta_lancar;
	}
	public void setS_total_harta_lancar(Double s_total_harta_lancar) {
		this.s_total_harta_lancar = s_total_harta_lancar;
	}
	public Double getS_harta_tetap_bersih() {
		return s_harta_tetap_bersih;
	}
	public void setS_harta_tetap_bersih(Double s_harta_tetap_bersih) {
		this.s_harta_tetap_bersih = s_harta_tetap_bersih;
	}
	public Double getS_aktiva_lain_lain() {
		return s_aktiva_lain_lain;
	}
	public void setS_aktiva_lain_lain(Double s_aktiva_lain_lain) {
		this.s_aktiva_lain_lain = s_aktiva_lain_lain;
	}
	public Double getS_total_harta_tetap() {
		return s_total_harta_tetap;
	}
	public void setS_total_harta_tetap(Double s_total_harta_tetap) {
		this.s_total_harta_tetap = s_total_harta_tetap;
	}
	public Double getS_total_harta() {
		return s_total_harta;
	}
	public void setS_total_harta(Double s_total_harta) {
		this.s_total_harta = s_total_harta;
	}
	public Double getS_kredit_bjpb() {
		return s_kredit_bjpb;
	}
	public void setS_kredit_bjpb(Double s_kredit_bjpb) {
		this.s_kredit_bjpb = s_kredit_bjpb;
	}
	public Double getS_kredit_bjpbl() {
		return s_kredit_bjpbl;
	}
	public void setS_kredit_bjpbl(Double s_kredit_bjpbl) {
		this.s_kredit_bjpbl = s_kredit_bjpbl;
	}
	public Double getS_hutang_dagang() {
		return s_hutang_dagang;
	}
	public void setS_hutang_dagang(Double s_hutang_dagang) {
		this.s_hutang_dagang = s_hutang_dagang;
	}
	public Double getS_total_hutang_lancar() {
		return s_total_hutang_lancar;
	}
	public void setS_total_hutang_lancar(Double s_total_hutang_lancar) {
		this.s_total_hutang_lancar = s_total_hutang_lancar;
	}
	public Double getS_kbjpb() {
		return s_kbjpb;
	}
	public void setS_kbjpb(Double s_kbjpb) {
		this.s_kbjpb = s_kbjpb;
	}
	public Double getS_hbjpbl() {
		return s_hbjpbl;
	}
	public void setS_hbjpbl(Double s_hbjpbl) {
		this.s_hbjpbl = s_hbjpbl;
	}
	public Double getS_thjp() {
		return s_thjp;
	}
	public void setS_thjp(Double s_thjp) {
		this.s_thjp = s_thjp;
	}
	public Double getS_total_hutang() {
		return s_total_hutang;
	}
	public void setS_total_hutang(Double s_total_hutang) {
		this.s_total_hutang = s_total_hutang;
	}
	public Double getS_modal_disetor() {
		return s_modal_disetor;
	}
	public void setS_modal_disetor(Double s_modal_disetor) {
		this.s_modal_disetor = s_modal_disetor;
	}
	public Double getS_laba_yang_ditahan() {
		return s_laba_yang_ditahan;
	}
	public void setS_laba_yang_ditahan(Double s_laba_yang_ditahan) {
		this.s_laba_yang_ditahan = s_laba_yang_ditahan;
	}
	public Double getS_laba_tahun_berjalan() {
		return s_laba_tahun_berjalan;
	}
	public void setS_laba_tahun_berjalan(Double s_laba_tahun_berjalan) {
		this.s_laba_tahun_berjalan = s_laba_tahun_berjalan;
	}
	public Double getS_total_modal() {
		return s_total_modal;
	}
	public void setS_total_modal(Double s_total_modal) {
		this.s_total_modal = s_total_modal;
	}
	public Double getS_total_hutang_modal() {
		return s_total_hutang_modal;
	}
	public void setS_total_hutang_modal(Double s_total_hutang_modal) {
		this.s_total_hutang_modal = s_total_hutang_modal;
	}
	public String getF_tahun() {
		return f_tahun;
	}
	public void setF_tahun(String f_tahun) {
		this.f_tahun = f_tahun;
	}
	public Double getF_kas_dan_bank() {
		return f_kas_dan_bank;
	}
	public void setF_kas_dan_bank(Double f_kas_dan_bank) {
		this.f_kas_dan_bank = f_kas_dan_bank;
	}
	public Double getF_piutang_dagang() {
		return f_piutang_dagang;
	}
	public void setF_piutang_dagang(Double f_piutang_dagang) {
		this.f_piutang_dagang = f_piutang_dagang;
	}
	public Double getF_persediaan() {
		return f_persediaan;
	}
	public void setF_persediaan(Double f_persediaan) {
		this.f_persediaan = f_persediaan;
	}
	public Double getF_perlengkapan() {
		return f_perlengkapan;
	}
	public void setF_perlengkapan(Double f_perlengkapan) {
		this.f_perlengkapan = f_perlengkapan;
	}
	
	public Double getF_total_harta() {
		return f_total_harta;
	}
	public void setF_total_harta(Double f_total_harta) {
		this.f_total_harta = f_total_harta;
	}
	public Double getF_kredit_bjpb() {
		return f_kredit_bjpb;
	}
	public void setF_kredit_bjpb(Double f_kredit_bjpb) {
		this.f_kredit_bjpb = f_kredit_bjpb;
	}
	public Double getF_kredit_bjpbl() {
		return f_kredit_bjpbl;
	}
	public void setF_kredit_bjpbl(Double f_kredit_bjpbl) {
		this.f_kredit_bjpbl = f_kredit_bjpbl;
	}
	public Double getF_hutang_dagang() {
		return f_hutang_dagang;
	}
	public void setF_hutang_dagang(Double f_hutang_dagang) {
		this.f_hutang_dagang = f_hutang_dagang;
	}
	public Double getF_total_hutang_lancar() {
		return f_total_hutang_lancar;
	}
	public void setF_total_hutang_lancar(Double f_total_hutang_lancar) {
		this.f_total_hutang_lancar = f_total_hutang_lancar;
	}
	public Double getF_kbjpb() {
		return f_kbjpb;
	}
	public void setF_kbjpb(Double f_kbjpb) {
		this.f_kbjpb = f_kbjpb;
	}
	public Double getF_hbjpbl() {
		return f_hbjpbl;
	}
	public void setF_hbjpbl(Double f_hbjpbl) {
		this.f_hbjpbl = f_hbjpbl;
	}
	public Double getF_thjp() {
		return f_thjp;
	}
	public void setF_thjp(Double f_thjp) {
		this.f_thjp = f_thjp;
	}
	public Double getF_total_hutang() {
		return f_total_hutang;
	}
	public void setF_total_hutang(Double f_total_hutang) {
		this.f_total_hutang = f_total_hutang;
	}
	public Double getF_modal_disetor() {
		return f_modal_disetor;
	}
	public void setF_modal_disetor(Double f_modal_disetor) {
		this.f_modal_disetor = f_modal_disetor;
	}

	public Double getF_total_modal() {
		return f_total_modal;
	}
	public void setF_total_modal(Double f_total_modal) {
		this.f_total_modal = f_total_modal;
	}
	public Double getF_total_hutang_modal() {
		return f_total_hutang_modal;
	}
	public void setF_total_hutang_modal(Double f_total_hutang_modal) {
		this.f_total_hutang_modal = f_total_hutang_modal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getF_total_harta_lancar() {
		return f_total_harta_lancar;
	}

	public void setF_total_harta_lancar(Double f_total_harta_lancar) {
		this.f_total_harta_lancar = f_total_harta_lancar;
	}

	public Double getF_harta_tetap_bersih() {
		return f_harta_tetap_bersih;
	}

	public void setF_harta_tetap_bersih(Double f_harta_tetap_bersih) {
		this.f_harta_tetap_bersih = f_harta_tetap_bersih;
	}

	public Double getF_aktiva_lain_lain() {
		return f_aktiva_lain_lain;
	}

	public void setF_aktiva_lain_lain(Double f_aktiva_lain_lain) {
		this.f_aktiva_lain_lain = f_aktiva_lain_lain;
	}

	public Double getF_total_harta_tetap() {
		return f_total_harta_tetap;
	}

	public void setF_total_harta_tetap(Double f_total_harta_tetap) {
		this.f_total_harta_tetap = f_total_harta_tetap;
	}

	public Double getF_laba_yang_ditahan() {
		return f_laba_yang_ditahan;
	}

	public void setF_laba_yang_ditahan(Double f_laba_yang_ditahan) {
		this.f_laba_yang_ditahan = f_laba_yang_ditahan;
	}

	public Double getF_laba_tahun_berjalan() {
		return f_laba_tahun_berjalan;
	}

	public void setF_laba_tahun_berjalan(Double f_laba_tahun_berjalan) {
		this.f_laba_tahun_berjalan = f_laba_tahun_berjalan;
	}

}
