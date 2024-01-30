package com.rabbai.serviceprospek.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cif_data")
public class CifData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String KTP;
	private String NAMA;
	private String GOL_DEB;
	private String PASSPORT;
	private String NPWP;
	private String PENDIDIKAN;
	private String STATUS_PERNIKAHAN;
	private String TMP_LAHIR;
	private String TGL_LAHIR;
	private String KELAMIN;
	private String KEBANGSAAN;
	private String ALAMAT;
	private String NO_TELP;
	private String PROFESI;
	private String NAMA_INSTANSI;
	private String BIDANG_USAHA;
	private String NAMA_PASANGAN;
	private String TGL_BUKA;
	

	public CifData(String KTP, String NAMA, String GOL_DEB, String PASSPORT, String NPWP, String PENDIDIKAN,
			String STATUS_PERNIKAHAN, String TMP_LAHIR, String TGL_LAHIR, String KELAMIN, String KEBANGSAAN,
			String ALAMAT, String NO_TELP, String PROFESI, String NAMA_INSTANSI, String BIDANG_USAHA,String NAMA_PASANGAN, String TGL_BUKA
			) {
		super();
		this.KTP = KTP;
		this.NAMA = NAMA;
		this.GOL_DEB = GOL_DEB;
		this.PASSPORT = PASSPORT;
		this.NPWP = NPWP;
		this.PENDIDIKAN = PENDIDIKAN;
		this.STATUS_PERNIKAHAN = STATUS_PERNIKAHAN;
		this.TMP_LAHIR = TMP_LAHIR;
		this.TGL_LAHIR = TGL_LAHIR;
		this.KELAMIN = KELAMIN;
		this.KEBANGSAAN = KEBANGSAAN;
		this.ALAMAT = ALAMAT;
		this.NO_TELP = NO_TELP;
		this.PROFESI = PROFESI;
		this.NAMA_INSTANSI = NAMA_INSTANSI;
		this.BIDANG_USAHA = BIDANG_USAHA;
		this.NAMA_PASANGAN = NAMA_PASANGAN;
		this.TGL_BUKA = TGL_BUKA;
		
	}

	public String getKTP() {
		return KTP;
	}

	public void setKTP(String kTP) {
		KTP = kTP;
	}

	public String getNAMA() {
		return NAMA;
	}

	public void setNAMA(String nAMA) {
		NAMA = nAMA;
	}

	public String getGOL_DEB() {
		return GOL_DEB;
	}

	public void setGOL_DEB(String gOL_DEB) {
		GOL_DEB = gOL_DEB;
	}

	public String getPASSPORT() {
		return PASSPORT;
	}

	public void setPASSPORT(String pASSPORT) {
		PASSPORT = pASSPORT;
	}

	public String getNPWP() {
		return NPWP;
	}

	public void setNPWP(String nPWP) {
		NPWP = nPWP;
	}

	public String getPENDIDIKAN() {
		return PENDIDIKAN;
	}

	public void setPENDIDIKAN(String pENDIDIKAN) {
		PENDIDIKAN = pENDIDIKAN;
	}

	public String getSTATUS_PERNIKAHAN() {
		return STATUS_PERNIKAHAN;
	}

	public void setSTATUS_PERNIKAHAN(String sTATUS_PERNIKAHAN) {
		STATUS_PERNIKAHAN = sTATUS_PERNIKAHAN;
	}

	public String getTMP_LAHIR() {
		return TMP_LAHIR;
	}

	public void setTMP_LAHIR(String tMP_LAHIR) {
		TMP_LAHIR = tMP_LAHIR;
	}

	public String getTGL_LAHIR() {
		return TGL_LAHIR;
	}

	public void setTGL_LAHIR(String tGL_LAHIR) {
		TGL_LAHIR = tGL_LAHIR;
	}

	public String getKELAMIN() {
		return KELAMIN;
	}

	public void setKELAMIN(String kELAMIN) {
		KELAMIN = kELAMIN;
	}

	public String getKEBANGSAAN() {
		return KEBANGSAAN;
	}

	public void setKEBANGSAAN(String kEBANGSAAN) {
		KEBANGSAAN = kEBANGSAAN;
	}

	public String getALAMAT() {
		return ALAMAT;
	}

	public void setALAMAT(String aLAMAT) {
		ALAMAT = aLAMAT;
	}

	public String getNO_TELP() {
		return NO_TELP;
	}

	public void setNO_TELP(String nO_TELP) {
		NO_TELP = nO_TELP;
	}

	public String getPROFESI() {
		return PROFESI;
	}

	public void setPROFESI(String pROFESI) {
		PROFESI = pROFESI;
	}

	public String getNAMA_INSTANSI() {
		return NAMA_INSTANSI;
	}

	public void setNAMA_INSTANSI(String nAMA_INSTANSI) {
		NAMA_INSTANSI = nAMA_INSTANSI;
	}

	public String getBIDANG_USAHA() {
		return BIDANG_USAHA;
	}

	public void setBIDANG_USAHA(String bIDANG_USAHA) {
		BIDANG_USAHA = bIDANG_USAHA;
	}

	public String getTGL_BUKA() {
		return TGL_BUKA;
	}

	public void setTGL_BUKA(String tGL_BUKA) {
		TGL_BUKA = tGL_BUKA;
	}

	public String getNAMA_PASANGAN() {
		return NAMA_PASANGAN;
	}

	public void setNAMA_PASANGAN(String nAMA_PASANGAN) {
		NAMA_PASANGAN = nAMA_PASANGAN;
	}

}
