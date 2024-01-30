package com.taufik.servicereporting.models.mkm;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taufik.servicereporting.models.Par_bagi_hasil;

import com.taufik.servicereporting.models.Par_gol_pihak_lawan;
import com.taufik.servicereporting.models.Par_jenis_aset;
import com.taufik.servicereporting.models.Par_jenis_suku_imbalan;
import com.taufik.servicereporting.models.Par_jenis_valuta;
import com.taufik.servicereporting.models.Par_kategori_portofolio;
import com.taufik.servicereporting.models.Par_kategori_debitur;
import com.taufik.servicereporting.models.Par_lembaga_pemeringkat;
import com.taufik.servicereporting.models.Par_metode_amortisasi;
import com.taufik.servicereporting.models.Par_orientasi_penggunaan;
import com.taufik.servicereporting.models.Par_periode_sewa;
import com.taufik.servicereporting.models.Par_program_pemerintah;
import com.taufik.servicereporting.models.Par_sandi_bi;
import com.taufik.servicereporting.models.Par_sektor_ekonomi;
import com.taufik.servicereporting.models.Par_sektor_ekonomi_kur;
import com.taufik.servicereporting.models.Par_sifat_investasi;
import com.taufik.servicereporting.models.Par_sifat_pembiayaan;
import com.taufik.servicereporting.models.Par_skim_pembiayaan;
import com.taufik.servicereporting.models.Par_sumber_dana;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "m_data_bi", schema = "pelaporan")
public class M_data_bi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	private String id_loan;
	private String kategori_debitur;
	private Integer kategori_portofolio;
	private String skim_pembiayaan;
	private String sumber_dana;
	private String sifat_investasi;
	private String bagi_hasil;
	private Integer persentase_nisbah;
	private Double persentase_rbh;
	private Integer sifat_pembiayaan;
	private Integer orientasi_penggunaan;
	private BigInteger jenis_valuta;
	private String program_pemerintah;
	private String sektor_ekonomi_kur;
	private String sektor_ekonomi;
	private String lokasi_pengguna;
	private String jenis_aset;
	private LocalDate waktu_perolehan_aset;
	private String jenis_valuta_aset;
	private Double harga_perolehan_aset;
	private Double jumlah_amortisasi;
	private String metode_amortisasi;
	private String periode_sewa;
	private Integer nilai_sewa_periode;
	private Integer amortisasi;
	private Double pecadangan_penurunan_aset;
	private Double uang_muka_ijarah;
	private Integer jenis_suku_imbalan;
	private Double persentase_imbalan_awal_kontrak;
	private Double persentase_imbalan_bulan_laporan;
	private String bagian_dijamin;
	private String golongan_pihak_lawan;
	private String lembaga_pemeringkat;
	private String rating_pihak_lawan;
	private String status_debitur;
	private String jenis_penggunaan;
	private String jenis_piutang;
	private String jenis_akad;
	private String bi_status_piutang;
	private String bi_sifat_akad;
	private String golongan_debitur;
	private String jenis_aktiva_ijarah;
	private String golongan_piutang;
	private String sifat_kredit_bi;
	private String bi_tujuan_kredit;
	private String tujuan_garansi;
	private String tgl_pemeringkat;
	private String cara_restruktur;
	private String kode_sebab_macet;
	private String freq_restruktur;
	private String jenis_garansi;
	private String kode_kondisi;
	private String is_restruktur;
	private String keterangan;
	private String sumber_dana_bi;
	private String kode_sektor_ekonomi_bi;
	private String gol_debitur_sid;
	private String jenis_penggunaan_bi;
	private String sifat_piutang;
	private String badan_hukum;

	

	public String getId_loan() {
		return id_loan;
	}

	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}


	public String getSkim_pembiayaan() {
		return skim_pembiayaan;
	}

	public void setSkim_pembiayaan(String skim_pembiayaan) {
		this.skim_pembiayaan = skim_pembiayaan;
	}

	public String getSumber_dana() {
		return sumber_dana;
	}

	public void setSumber_dana(String sumber_dana) {
		this.sumber_dana = sumber_dana;
	}

	public String getSifat_investasi() {
		return sifat_investasi;
	}

	public void setSifat_investasi(String sifat_investasi) {
		this.sifat_investasi = sifat_investasi;
	}

	public String getBagi_hasil() {
		return bagi_hasil;
	}

	public void setBagi_hasil(String bagi_hasil) {
		this.bagi_hasil = bagi_hasil;
	}

	public Integer getPersentase_nisbah() {
		return persentase_nisbah;
	}

	public void setPersentase_nisbah(Integer persentase_nisbah) {
		this.persentase_nisbah = persentase_nisbah;
	}

	public Double getPersentase_rbh() {
		return persentase_rbh;
	}

	public void setPersentase_rbh(String persentase_rbh) {
		if (persentase_rbh.equals("") || persentase_rbh.equals("null")) {
			this.persentase_rbh = null;
		} else {
			this.persentase_rbh = Double.valueOf(persentase_rbh);
		}
	}

	public String getSektor_ekonomi_kur() {
		return sektor_ekonomi_kur;
	}

	public void setSektor_ekonomi_kur(String sektor_ekonomi_kur) {
		this.sektor_ekonomi_kur = sektor_ekonomi_kur;
	}

	public String getSektor_ekonomi() {
		return sektor_ekonomi;
	}

	public void setSektor_ekonomi(String sektor_ekonomi) {
		this.sektor_ekonomi = sektor_ekonomi;
	}

	public String getLokasi_pengguna() {
		return lokasi_pengguna;
	}

	public void setLokasi_pengguna(String lokasi_pengguna) {
		this.lokasi_pengguna = lokasi_pengguna;
	}

	public String getJenis_aset() {
		return jenis_aset;
	}

	public void setJenis_aset(String jenis_aset) {
		this.jenis_aset = jenis_aset;
	}

	public LocalDate getWaktu_perolehan_aset() {
		return waktu_perolehan_aset;
	}

	public void setWaktu_perolehan_aset(LocalDate waktu_perolehan_aset) {
		this.waktu_perolehan_aset = waktu_perolehan_aset;
	}

	public String getJenis_valuta_aset() {
		return jenis_valuta_aset;
	}

	public void setJenis_valuta_aset(String jenis_valuta_aset) {
		this.jenis_valuta_aset = jenis_valuta_aset;
	}





	public String getMetode_amortisasi() {
		return metode_amortisasi;
	}

	public void setMetode_amortisasi(String metode_amortisasi) {
		this.metode_amortisasi = metode_amortisasi;
	}

	public String getPeriode_sewa() {
		return periode_sewa;
	}

	public void setPeriode_sewa(String periode_sewa) {
		this.periode_sewa = periode_sewa;
	}

	public Integer getNilai_sewa_periode() {
		return nilai_sewa_periode;
	}

	public void setNilai_sewa_periode(Integer nilai_sewa_periode) {
		this.nilai_sewa_periode = nilai_sewa_periode;
	}



	public Double getPecadangan_penurunan_aset() {
		return pecadangan_penurunan_aset;
	}

	public void setPecadangan_penurunan_aset(Double pecadangan_penurunan_aset) {

		this.pecadangan_penurunan_aset = pecadangan_penurunan_aset;

	}

	public Double getUang_muka_ijarah() {
		return uang_muka_ijarah;
	}

	public void setUang_muka_ijarah(Double uang_muka_ijarah) {
		this.uang_muka_ijarah = uang_muka_ijarah;
	}


	public String getGolongan_pihak_lawan() {
		return golongan_pihak_lawan;
	}

	public void setGolongan_pihak_lawan(String golongan_pihak_lawan) {
		this.golongan_pihak_lawan = golongan_pihak_lawan;
	}

	public String getLembaga_pemeringkat() {
		return lembaga_pemeringkat;
	}

	public void setLembaga_pemeringkat(String lembaga_pemeringkat) {
		this.lembaga_pemeringkat = lembaga_pemeringkat;
	}

	public String getRating_pihak_lawan() {
		return rating_pihak_lawan;
	}

	public void setRating_pihak_lawan(String rating_pihak_lawan) {
		this.rating_pihak_lawan = rating_pihak_lawan;
	}

	public String getStatus_debitur() {
		return status_debitur;
	}

	public void setStatus_debitur(String status_debitur) {
		this.status_debitur = status_debitur;
	}

	public String getJenis_penggunaan() {
		return jenis_penggunaan;
	}

	public void setJenis_penggunaan(String jenis_penggunaan) {
		this.jenis_penggunaan = jenis_penggunaan;
	}

	public String getJenis_piutang() {
		return jenis_piutang;
	}

	public void setJenis_piutang(String jenis_piutang) {
		this.jenis_piutang = jenis_piutang;
	}

	public String getJenis_akad() {
		return jenis_akad;
	}

	public void setJenis_akad(String jenis_akad) {
		this.jenis_akad = jenis_akad;
	}

	public String getBi_status_piutang() {
		return bi_status_piutang;
	}

	public void setBi_status_piutang(String bi_status_piutang) {
		this.bi_status_piutang = bi_status_piutang;
	}

	public String getBi_sifat_akad() {
		return bi_sifat_akad;
	}

	public void setBi_sifat_akad(String bi_sifat_akad) {
		this.bi_sifat_akad = bi_sifat_akad;
	}

	public String getGolongan_debitur() {
		return golongan_debitur;
	}

	public void setGolongan_debitur(String golongan_debitur) {
		this.golongan_debitur = golongan_debitur;
	}

	public String getJenis_aktiva_ijarah() {
		return jenis_aktiva_ijarah;
	}

	public void setJenis_aktiva_ijarah(String jenis_aktiva_ijarah) {
		this.jenis_aktiva_ijarah = jenis_aktiva_ijarah;
	}

	public String getGolongan_piutang() {
		return golongan_piutang;
	}

	public void setGolongan_piutang(String golongan_piutang) {
		this.golongan_piutang = golongan_piutang;
	}

	public String getSifat_kredit_bi() {
		return sifat_kredit_bi;
	}

	public void setSifat_kredit_bi(String sifat_kredit_bi) {
		this.sifat_kredit_bi = sifat_kredit_bi;
	}

	public String getBi_tujuan_kredit() {
		return bi_tujuan_kredit;
	}

	public void setBi_tujuan_kredit(String bi_tujuan_kredit) {
		this.bi_tujuan_kredit = bi_tujuan_kredit;
	}

	public String getTujuan_garansi() {
		return tujuan_garansi;
	}

	public void setTujuan_garansi(String tujuan_garansi) {
		this.tujuan_garansi = tujuan_garansi;
	}

	public String getTgl_pemeringkat() {
		return tgl_pemeringkat;
	}

	public void setTgl_pemeringkat(String tgl_pemeringkat) {
		this.tgl_pemeringkat = tgl_pemeringkat;
	}

	public String getCara_restruktur() {
		return cara_restruktur;
	}

	public void setCara_restruktur(String cara_restruktur) {
		this.cara_restruktur = cara_restruktur;
	}

	public String getKode_sebab_macet() {
		return kode_sebab_macet;
	}

	public void setKode_sebab_macet(String kode_sebab_macet) {
		this.kode_sebab_macet = kode_sebab_macet;
	}

	public String getFreq_restruktur() {
		return freq_restruktur;
	}

	public void setFreq_restruktur(String freq_restruktur) {
		this.freq_restruktur = freq_restruktur;
	}

	public String getJenis_garansi() {
		return jenis_garansi;
	}

	public void setJenis_garansi(String jenis_garansi) {
		this.jenis_garansi = jenis_garansi;
	}

	public String getKode_kondisi() {
		return kode_kondisi;
	}

	public void setKode_kondisi(String kode_kondisi) {
		this.kode_kondisi = kode_kondisi;
	}

	public String getIs_restruktur() {
		return is_restruktur;
	}

	public void setIs_restruktur(String is_restruktur) {
		this.is_restruktur = is_restruktur;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getSumber_dana_bi() {
		return sumber_dana_bi;
	}

	public void setSumber_dana_bi(String sumber_dana_bi) {
		this.sumber_dana_bi = sumber_dana_bi;
	}

	public String getKode_sektor_ekonomi_bi() {
		return kode_sektor_ekonomi_bi;
	}

	public void setKode_sektor_ekonomi_bi(String kode_sektor_ekonomi_bi) {
		this.kode_sektor_ekonomi_bi = kode_sektor_ekonomi_bi;
	}

	public String getGol_debitur_sid() {
		return gol_debitur_sid;
	}

	public void setGol_debitur_sid(String gol_debitur_sid) {
		this.gol_debitur_sid = gol_debitur_sid;
	}

	public String getJenis_penggunaan_bi() {
		return jenis_penggunaan_bi;
	}

	public void setJenis_penggunaan_bi(String jenis_penggunaan_bi) {
		this.jenis_penggunaan_bi = jenis_penggunaan_bi;
	}

	public String getSifat_piutang() {
		return sifat_piutang;
	}

	public void setSifat_piutang(String sifat_piutang) {
		this.sifat_piutang = sifat_piutang;
	}

	public String getBadan_hukum() {
		return badan_hukum;
	}

	public void setBadan_hukum(String badan_hukum) {
		this.badan_hukum = badan_hukum;
	}

	public Integer getKategori_portofolio() {
		return kategori_portofolio;
	}

	public void setKategori_portofolio(Integer kategori_portofolio) {
		this.kategori_portofolio = kategori_portofolio;
	}

	public Integer getSifat_pembiayaan() {
		return sifat_pembiayaan;
	}

	public void setSifat_pembiayaan(Integer sifat_pembiayaan) {
		this.sifat_pembiayaan = sifat_pembiayaan;
	}

	public Integer getOrientasi_penggunaan() {
		return orientasi_penggunaan;
	}

	public void setOrientasi_penggunaan(Integer orientasi_penggunaan) {
		this.orientasi_penggunaan = orientasi_penggunaan;
	}

	public void setPersentase_rbh(Double persentase_rbh) {
		this.persentase_rbh = persentase_rbh;
	}

	public String getProgram_pemerintah() {
		return program_pemerintah;
	}

	public void setProgram_pemerintah(String program_pemerintah) {
		this.program_pemerintah = program_pemerintah;
	}

	public Double getHarga_perolehan_aset() {
		return harga_perolehan_aset;
	}

	public void setHarga_perolehan_aset(Double harga_perolehan_aset) {
		this.harga_perolehan_aset = harga_perolehan_aset;
	}

	public Double getJumlah_amortisasi() {
		return jumlah_amortisasi;
	}

	public void setJumlah_amortisasi(Double jumlah_amortisasi) {
		this.jumlah_amortisasi = jumlah_amortisasi;
	}

	public Integer getAmortisasi() {
		return amortisasi;
	}

	public void setAmortisasi(Integer amortisasi) {
		this.amortisasi = amortisasi;
	}

	public Integer getJenis_suku_imbalan() {
		return jenis_suku_imbalan;
	}

	public void setJenis_suku_imbalan(Integer jenis_suku_imbalan) {
		this.jenis_suku_imbalan = jenis_suku_imbalan;
	}

	public Double getPersentase_imbalan_awal_kontrak() {
		return persentase_imbalan_awal_kontrak;
	}

	public void setPersentase_imbalan_awal_kontrak(Double persentase_imbalan_awal_kontrak) {
		this.persentase_imbalan_awal_kontrak = persentase_imbalan_awal_kontrak;
	}

	public String getBagian_dijamin() {
		return bagian_dijamin;
	}

	public void setBagian_dijamin(String bagian_dijamin) {
		this.bagian_dijamin = bagian_dijamin;
	}

	public String getKategori_debitur() {
		return kategori_debitur;
	}

	public void setKategori_debitur(String kategori_debitur) {
		this.kategori_debitur = kategori_debitur;
	}

}
