package com.rabbai.serviceusulan.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "data_usulan")
public class Usulan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String id_debitur;
	private Integer id_kategori_produk;
	private Integer id_plan;
	private Double plafon_pengajuan;
	private Integer tenor_pengajuan;
	private BigDecimal margin_pengajuan;
	private Double angsuran_pengajuan;
	private Float exp_rate;
	private String tujuan_pembiayaan;
	private String no_loan;
	private String ideb;
	private String input_by;
	private LocalDateTime input_date;
	private String no_scoring;
	private LocalDateTime tgl_scoring;
	private String input_scoring_by;
	private String review_by;
	private LocalDateTime review_date;
	private String review_desc;
	private String submit_by;
	private LocalDateTime submit_date;
	private String approve1_by;
	private LocalDateTime approve1_date;
	private String input2_by;
	private LocalDateTime input2_date;
	private String submit2_by;
	private LocalDateTime submit2_date;
	private String review2_by;
	private LocalDateTime review2_date;
	private String review2_desc;
	private String approve12_by;
	private LocalDateTime approve12_date;
	private String approve2_by;
	private LocalDateTime approve2_date;
	private String approve3_by;
	private LocalDateTime approve3_date;
	private String akad_by;
	private LocalDateTime akad_date;
	private String cair_by;
	private LocalDateTime cair_date;
	private String status;
	private LocalDateTime tgl_pengajuan;
	private String id_cab;
	private Integer kuasa_pemotongan;
	private Integer persentase_kuasa_pemotongan;
	private Double nilai_harta_thp_kredit;
	private Double saldo_rata_bulanan;
	private String kode_referal;
	private String slik_ref_pengguna;
	private String slik_nomor_laporan;
	private LocalDate slik_tgl_data_terakhir;
	private LocalDate slik_tgl_permintaan;
	private String input_nama;
	private String submit_nama;
	private String review_nama;
	private String approve1_nama;
	private String approve2_nama;
	private String approve3_nama;
	private String rekomendasi;
	private String keputusan;
	private Double harga_perolehan;
	private Double pajak;
	private Double diskon;
	private Double uang_muka;
	private Double biaya_adm;
	private Double biaya_asuransi_jiwa;
	private Double biaya_asuransi_kebakaran;
	private Double biaya_asuransi_pembiayaan;
	private Integer is_take_over;
	private String take_over_nama_bank;
	private Double take_over_total;
	private String norek;
	private String nama_bank;
	private String atas_nama;
	private Integer status_rekening;
	private LocalDate tgl_kunjungan;
	private String officer_bank;
	private String no_sp3;
	private String no_sp4;
	private String kode_broker;
	private String benefit_broker;
	private String kode_asuransi;
	private String benefit_asuransi;
	private Double plafon_disetujui;
	private Integer tenor_disetujui;
	private Double angsuran_disetujui;
	private String akad_file;
	private String upload_akad_by;
	private LocalDateTime upload_akad_date;
	private String id_loan;
	private String id_old_usulan;

	// KEBUTUHAN FIELD PENCAIRAN BV
	private String kode_notaris;
	private String kode_officer_2;
	private String kode_officer_1;
	private Integer grace_period;
	private String frequensi_pembayaran_margin;
	private Integer frequensi_pembayaran_number;
	private String kode_margin;

	private Float basis_point_margin;
	private String basis_point_margin_mark;

	private String norek_penghasilan;
	private String nama_rekening_penghasilan;
	private String kode_rekening_penghasilan;
	private String jenis_pembiayaan;
	private Double premi_asuransi_lama;
	private String tgl_kredit_lama;
	private String benefit_kredit_lama;
	private Integer tenor_kredit_lama;
	private String blokir_saldo;
	private String is_wakalah;
	private String norek_wakalah;
	private String nama_rekening_wakalah;
	private String kode_rekening_wakalah;
	private String kode_aset_ijarah;

	private String is_biaya_adm;
	private Double persentase_biaya_adm;
	private Double biaya_materai;

	/* final yang kurang dipencairan belum ada */
	private String nomor_akad;
	private String norek_proceed;
	private String nama_rekening_proceed;
	private String kode_rekening_proceed;
	private String norek_loan;
	private String nama_rekening_loan;
	private String kode_rekening_loan;
	private Double nisbah_bank;
	private Double nisbah_nasabah;
	private String sp4_file;
	private String barang;
	private Double baki_debet_lama;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = ScoringLoan.class)
	@JoinColumn(name = "id_loan", referencedColumnName = "id_loan", insertable = false, updatable = false)
	private List<ScoringLoan> ScoringLoan;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Harta.class)
	@JoinColumn(name = "id_loan", referencedColumnName = "id_loan", insertable = false, updatable = false)
	private List<Harta> harta;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Agunan.class)
	@JoinColumn(name = "id_loan", referencedColumnName = "id_loan", insertable = false, updatable = false)
	private List<Agunan> agunan;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Data_bi.class)
	@JoinColumn(name = "id_loan", referencedColumnName = "id_loan", insertable = false, updatable = false)
	private List<Data_bi> data_bi;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Append_call.class)
	@JoinColumn(name = "id_loan", referencedColumnName = "id", insertable = false, updatable = false)
	private List<Append_call> append_call;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Append_fasilitas_pembiayaan.class)
	@JoinColumn(name = "id_loan", referencedColumnName = "id", insertable = false, updatable = false)
	private List<Append_fasilitas_pembiayaan> append_fasilitas_pembiayaan;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Append_survey.class)
	@JoinColumn(name = "id_loan", referencedColumnName = "id", insertable = false, updatable = false)
	private List<Append_survey> append_survey;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Append_map.class)
	@JoinColumn(name = "id_loan", referencedColumnName = "id", insertable = false, updatable = false)
	private List<Append_map> append_map;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Append_scoring.class)
	@JoinColumn(name = "id_loan", referencedColumnName = "id", insertable = false, updatable = false)
	private List<Append_scoring> append_scoring;

	// PARENT PAR par_agunan_basel2_jenis_agunan
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kode_margin", insertable = false, updatable = false)
	private Par_kode_margin desc_kode_margin;

	@JsonProperty("desc_kode_margin")
	public String getPar_kode_margin() {
		if (desc_kode_margin == null) {
			return "";
		} else {
			return desc_kode_margin.getName();
		}
	}

	// PARENT DEBITUR
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
	@JoinColumn(name = "id_debitur", insertable = false, updatable = false)
	private Debitur debitur;

	// PARENT CABANG
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
	@JoinColumn(name = "id_cab", insertable = false, updatable = false)
	private Par_cabang cabang;

	// PARENT PAR KATEGORI PRODUK
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_kategori_produk", insertable = false, updatable = false, nullable = true)
	private Par_kategori_produk par_kategori_produk;

	@JsonProperty("nama_kategori_produk")
	public String getNama_kategori_produk() {
		return par_kategori_produk.getName();
	}

	// PARENT PAR PLAN
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_plan", insertable = false, updatable = false, nullable = true)
	private Par_plan par_plan;

	@JsonProperty("nama_sub_produk")
	public String getPar_plan() {
		if (par_plan == null) {
			return "";
		} else {
			return par_plan.getPar_sub_produk();
		}
	}

	@JsonProperty("id_sub_produk")
	public Integer getId_sub_produk() {
		if (par_plan == null) {
			return 0;
		} else {
			return par_plan.getId_sub_produk();
		}
	}

	@JsonProperty("nama_plan")
	public String getPar_plan_name() {
		if (par_plan == null) {
			return "";
		} else {
			return par_plan.getName();
		}
	}
	
	@JsonProperty("type_plan")
	public Integer getPar_plan_type() {
		if (par_plan == null) {
			return null;
		} else {
			return par_plan.getType();
		}
	}

	@JsonProperty("nama_produk")
	public String getPar_produk() {
		if (par_plan == null) {
			return "";
		} else {
			return par_plan.getPar_produk();
		}
	}

	@JsonProperty("id_produk")
	public Integer getId_produk() {
		if (par_plan == null) {
			return 0;
		} else {
			return par_plan.getId_produk();
		}
	}

	@JsonProperty("id_template_dokumen")
	public Integer getId_template_dokumen() {
		if (par_plan == null) {
			return 0;
		} else {
			return par_plan.getId_template_dokumen();
		}
	}
	
	@JsonProperty("nama_template_dokumen")
	public String getNama_template_dokumen() {
		if (par_plan == null) {
			return null;
		} else {
			return par_plan.getNama_template_dokumen();
		}
	}

	@JsonProperty("inisial_akad")
	public String getInisial_akad() {
		if (par_plan == null) {
			return "";
		} else {
			return par_plan.getInisial_akad();
		}
	}

	// PARENT PAR BROKER
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kode_broker", insertable = false, updatable = false)
	private Par_broker par_broker;

	@JsonProperty("nama_broker")
	public String getNama_broker() {
		if (par_broker == null) {
			return "";
		} else {
			return par_broker.getName();
		}

	}

	// PARENT PAR ASURANSI
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kode_asuransi", insertable = false, updatable = false)
	private Par_asu par_asuransi;

	@JsonProperty("nama_asuransi")
	public String getNama_asuransi() {
		System.out.println("ORIIIII " + par_asuransi);
		if (par_asuransi == null) {
			return "";
		} else {
			return par_asuransi.getName();
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_debitur() {
		return id_debitur;
	}

	public void setId_debitur(String id_debitur) {
		this.id_debitur = id_debitur;
	}

	public Double getPlafon_pengajuan() {
		return plafon_pengajuan;
	}

	public void setPlafon_pengajuan(Double plafon_pengajuan) {
		this.plafon_pengajuan = plafon_pengajuan;
	}

	public Integer getTenor_pengajuan() {
		return tenor_pengajuan;
	}

	public void setTenor_pengajuan(Integer tenor_pengajuan) {
		this.tenor_pengajuan = tenor_pengajuan;
	}

	public Double getAngsuran_pengajuan() {
		return angsuran_pengajuan;
	}

	public void setAngsuran_pengajuan(Double angsuran_pengajuan) {
		this.angsuran_pengajuan = angsuran_pengajuan;
	}

	public String getTujuan_pembiayaan() {
		return tujuan_pembiayaan;
	}

	public void setTujuan_pembiayaan(String tujuan_pembiayaan) {
		this.tujuan_pembiayaan = tujuan_pembiayaan;
	}

	public String getNo_loan() {
		return no_loan;
	}

	public void setNo_loan(String no_loan) {
		this.no_loan = no_loan;
	}

	public String getNo_scoring() {
		return no_scoring;
	}

	public void setNo_scoring(String no_scoring) {
		this.no_scoring = no_scoring;
	}

	public LocalDateTime getTgl_scoring() {
		return tgl_scoring;
	}

	public void setTgl_scoring(LocalDateTime tgl_scoring) {
		this.tgl_scoring = tgl_scoring;
	}

	public String getInput_scoring_by() {
		return input_scoring_by;
	}

	public void setInput_scoring_by(String input_scoring_by) {
		this.input_scoring_by = input_scoring_by;
	}

//	public List<ScoringLoan> getScoringLoan() {
//		return ScoringLoan;
//	}
//
//	public void setScoringLoan(List<ScoringLoan> scoringLoan) {
//		ScoringLoan = scoringLoan;
//	}


	public String getIdeb() {
		return ideb;
	}

	public void setIdeb(String ideb) {
		this.ideb = ideb;
	}

	public String getInput_by() {
		return input_by;
	}

	public void setInput_by(String input_by) {
		this.input_by = input_by;
	}

	public LocalDateTime getInput_date() {
		return input_date;
	}

	public void setInput_date(LocalDateTime input_date) {
		this.input_date = input_date;
	}

	public String getReview_by() {
		return review_by;
	}

	public void setReview_by(String review_by) {
		this.review_by = review_by;
	}

	public LocalDateTime getReview_date() {
		return review_date;
	}

	public void setReview_date(LocalDateTime review_date) {
		this.review_date = review_date;
	}

	public String getReview_desc() {
		return review_desc;
	}

	public void setReview_desc(String review_desc) {
		this.review_desc = review_desc;
	}

	public String getSubmit_by() {
		return submit_by;
	}

	public void setSubmit_by(String submit_by) {
		this.submit_by = submit_by;
	}

	public LocalDateTime getSubmit_date() {
		return submit_date;
	}

	public void setSubmit_date(LocalDateTime submit_date) {
		this.submit_date = submit_date;
	}

	public String getApprove1_by() {
		return approve1_by;
	}

	public void setApprove1_by(String approve1_by) {
		this.approve1_by = approve1_by;
	}

	public LocalDateTime getApprove1_date() {
		return approve1_date;
	}

	public void setApprove1_date(LocalDateTime approve1_date) {
		this.approve1_date = approve1_date;
	}

	public String getInput2_by() {
		return input2_by;
	}

	public void setInput2_by(String input2_by) {
		this.input2_by = input2_by;
	}

	public LocalDateTime getInput2_date() {
		return input2_date;
	}

	public void setInput2_date(LocalDateTime input2_date) {
		this.input2_date = input2_date;
	}

	public String getSubmit2_by() {
		return submit2_by;
	}

	public void setSubmit2_by(String submit2_by) {
		this.submit2_by = submit2_by;
	}

	public LocalDateTime getSubmit2_date() {
		return submit2_date;
	}

	public void setSubmit2_date(LocalDateTime submit2_date) {
		this.submit2_date = submit2_date;
	}

	public String getReview2_by() {
		return review2_by;
	}

	public void setReview2_by(String review2_by) {
		this.review2_by = review2_by;
	}

	public LocalDateTime getReview2_date() {
		return review2_date;
	}

	public void setReview2_date(LocalDateTime review2_date) {
		this.review2_date = review2_date;
	}

	public String getReview2_desc() {
		return review2_desc;
	}

	public void setReview2_desc(String review2_desc) {
		this.review2_desc = review2_desc;
	}

	public String getApprove12_by() {
		return approve12_by;
	}

	public void setApprove12_by(String approve12_by) {
		this.approve12_by = approve12_by;
	}

	public LocalDateTime getApprove12_date() {
		return approve12_date;
	}

	public void setApprove12_date(LocalDateTime approve12_date) {
		this.approve12_date = approve12_date;
	}

	public String getApprove2_by() {
		return approve2_by;
	}

	public void setApprove2_by(String approve2_by) {
		this.approve2_by = approve2_by;
	}

	public LocalDateTime getApprove2_date() {
		return approve2_date;
	}

	public void setApprove2_date(LocalDateTime approve2_date) {
		this.approve2_date = approve2_date;
	}

	public String getApprove3_by() {
		return approve3_by;
	}

	public void setApprove3_by(String approve3_by) {
		this.approve3_by = approve3_by;
	}

	public LocalDateTime getApprove3_date() {
		return approve3_date;
	}

	public void setApprove3_date(LocalDateTime approve3_date) {
		this.approve3_date = approve3_date;
	}

	public String getAkad_by() {
		return akad_by;
	}

	public void setAkad_by(String akad_by) {
		this.akad_by = akad_by;
	}

	public LocalDateTime getAkad_date() {
		return akad_date;
	}

	public void setAkad_date(LocalDateTime akad_date) {
		this.akad_date = akad_date;
	}

	public String getCair_by() {
		return cair_by;
	}

	public void setCair_by(String cair_by) {
		this.cair_by = cair_by;
	}

	public LocalDateTime getCair_date() {
		return cair_date;
	}

	public void setCair_date(LocalDateTime cair_date) {
		this.cair_date = cair_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getTgl_pengajuan() {
		return tgl_pengajuan;
	}

	public void setTgl_pengajuan(LocalDateTime tgl_pengajuan) {
		this.tgl_pengajuan = tgl_pengajuan;
	}

//	public void setPar_plan(Par_plan par_plan) {
//		this.par_plan = par_plan;
//	}

	public Debitur getDebitur() {
		return debitur;
	}

	public void setDebitur(Debitur debitur) {
		this.debitur = debitur;
	}

	public Integer getId_kategori_produk() {
		return id_kategori_produk;
	}

	public void setId_kategori_produk(Integer id_kategori_produk) {
		this.id_kategori_produk = id_kategori_produk;
	}

	public Integer getId_plan() {
		return id_plan;
	}

	public void setId_plan(Integer id_plan) {
		this.id_plan = id_plan;
	}

	public String getId_cab() {
		return id_cab;
	}

	public void setId_cab(String id_cab) {
		this.id_cab = id_cab;
	}

	public Integer getKuasa_pemotongan() {
		return kuasa_pemotongan;
	}

	public void setKuasa_pemotongan(Integer kuasa_pemotongan) {
		this.kuasa_pemotongan = kuasa_pemotongan;
	}

	public Integer getPersentase_kuasa_pemotongan() {
		return persentase_kuasa_pemotongan;
	}

	public void setPersentase_kuasa_pemotongan(Integer persentase_kuasa_pemotongan) {
		this.persentase_kuasa_pemotongan = persentase_kuasa_pemotongan;
	}

	public Double getNilai_harta_thp_kredit() {
		return nilai_harta_thp_kredit;
	}

	public void setNilai_harta_thp_kredit(Double nilai_harta_thp_kredit) {
		this.nilai_harta_thp_kredit = nilai_harta_thp_kredit;
	}

	public Double getSaldo_rata_bulanan() {
		return saldo_rata_bulanan;
	}

	public void setSaldo_rata_bulanan(Double saldo_rata_bulanan) {
		this.saldo_rata_bulanan = saldo_rata_bulanan;
	}

	public String getKode_referal() {
		return kode_referal;
	}

	public void setKode_referal(String kode_referal) {
		this.kode_referal = kode_referal;
	}

	public String getSlik_ref_pengguna() {
		return slik_ref_pengguna;
	}

	public void setSlik_ref_pengguna(String slik_ref_pengguna) {
		this.slik_ref_pengguna = slik_ref_pengguna;
	}

	public String getSlik_nomor_laporan() {
		return slik_nomor_laporan;
	}

	public void setSlik_nomor_laporan(String slik_nomor_laporan) {
		this.slik_nomor_laporan = slik_nomor_laporan;
	}

	public LocalDate getSlik_tgl_data_terakhir() {
		return slik_tgl_data_terakhir;
	}

	public void setSlik_tgl_data_terakhir(LocalDate slik_tgl_data_terakhir) {
		this.slik_tgl_data_terakhir = slik_tgl_data_terakhir;
	}

	public LocalDate getSlik_tgl_permintaan() {
		return slik_tgl_permintaan;
	}

	public void setSlik_tgl_permintaan(LocalDate slik_tgl_permintaan) {
		this.slik_tgl_permintaan = slik_tgl_permintaan;
	}

	public String getInput_nama() {
		return input_nama;
	}

	public void setInput_nama(String input_nama) {
		this.input_nama = input_nama;
	}

	public String getSubmit_nama() {
		return submit_nama;
	}

	public void setSubmit_nama(String submit_nama) {
		this.submit_nama = submit_nama;
	}

	public String getReview_nama() {
		return review_nama;
	}

	public void setReview_nama(String review_nama) {
		this.review_nama = review_nama;
	}

	public String getApprove1_nama() {
		return approve1_nama;
	}

	public void setApprove1_nama(String approve1_nama) {
		this.approve1_nama = approve1_nama;
	}

	public String getApprove2_nama() {
		return approve2_nama;
	}

	public void setApprove2_nama(String approve2_nama) {
		this.approve2_nama = approve2_nama;
	}

	public String getApprove3_nama() {
		return approve3_nama;
	}

	public void setApprove3_nama(String approve3_nama) {
		this.approve3_nama = approve3_nama;
	}

	public String getRekomendasi() {
		return rekomendasi;
	}

	public void setRekomendasi(String rekomendasi) {
		this.rekomendasi = rekomendasi;
	}

	public String getKeputusan() {
		return keputusan;
	}

	public void setKeputusan(String keputusan) {
		this.keputusan = keputusan;
	}

	public Par_cabang getCabang() {
		return cabang;
	}

	public void setCabang(Par_cabang cabang) {
		this.cabang = cabang;
	}

	public Double getPajak() {
		return pajak;
	}

	public void setPajak(Double pajak) {
		this.pajak = pajak;
	}

	public Double getDiskon() {
		return diskon;
	}

	public void setDiskon(Double diskon) {
		this.diskon = diskon;
	}

	public Double getUang_muka() {
		return uang_muka;
	}

	public void setUang_muka(Double uang_muka) {
		this.uang_muka = uang_muka;
	}

	public Double getBiaya_adm() {
		return biaya_adm;
	}

	public void setBiaya_adm(Double biaya_adm) {
		this.biaya_adm = biaya_adm;
	}

	public Double getBiaya_asuransi_jiwa() {
		return biaya_asuransi_jiwa;
	}

	public void setBiaya_asuransi_jiwa(Double biaya_asuransi_jiwa) {
		this.biaya_asuransi_jiwa = biaya_asuransi_jiwa;
	}

	public Double getBiaya_asuransi_kebakaran() {
		return biaya_asuransi_kebakaran;
	}

	public void setBiaya_asuransi_kebakaran(Double biaya_asuransi_kebakaran) {
		this.biaya_asuransi_kebakaran = biaya_asuransi_kebakaran;
	}

	public Double getBiaya_asuransi_pembiayaan() {
		return biaya_asuransi_pembiayaan;
	}

	public void setBiaya_asuransi_pembiayaan(Double biaya_asuransi_pembiayaan) {
		this.biaya_asuransi_pembiayaan = biaya_asuransi_pembiayaan;
	}

	public Double getHarga_perolehan() {
		return harga_perolehan;
	}

	public void setHarga_perolehan(Double harga_perolehan) {
		this.harga_perolehan = harga_perolehan;
	}

	public Integer getIs_take_over() {
		return is_take_over;
	}

	public void setIs_take_over(Integer is_take_over) {
		this.is_take_over = is_take_over;
	}

	public String getTake_over_nama_bank() {
		return take_over_nama_bank;
	}

	public void setTake_over_nama_bank(String take_over_nama_bank) {
		this.take_over_nama_bank = take_over_nama_bank;
	}

	public Double getTake_over_total() {
		return take_over_total;
	}

	public void setTake_over_total(Double take_over_total) {
		this.take_over_total = take_over_total;
	}

	public String getNorek() {
		return norek;
	}

	public void setNorek(String norek) {
		this.norek = norek;
	}

	public String getNama_bank() {
		return nama_bank;
	}

	public void setNama_bank(String nama_bank) {
		this.nama_bank = nama_bank;
	}

	public String getAtas_nama() {
		return atas_nama;
	}

	public void setAtas_nama(String atas_nama) {
		this.atas_nama = atas_nama;
	}

	public Integer getStatus_rekening() {
		return status_rekening;
	}

	public void setStatus_rekening(Integer status_rekening) {
		this.status_rekening = status_rekening;
	}

	public LocalDate getTgl_kunjungan() {
		return tgl_kunjungan;
	}

	public void setTgl_kunjungan(LocalDate tgl_kunjungan) {
		this.tgl_kunjungan = tgl_kunjungan;
	}

	public String getOfficer_bank() {
		return officer_bank;
	}

	public void setOfficer_bank(String officer_bank) {
		this.officer_bank = officer_bank;
	}

//	public List<Hub_perbankan_detail> getHub_perbankan_detail() {
//		return hub_perbankan_detail;
//	}
//
//	public void setHub_perbankan_detail(List<Hub_perbankan_detail> hub_perbankan_detail) {
//		this.hub_perbankan_detail = hub_perbankan_detail;
//	}

	public String getNo_sp3() {
		return no_sp3;
	}

	public void setNo_sp3(String no_sp3) {
		this.no_sp3 = no_sp3;
	}

	public String getNo_sp4() {
		return no_sp4;
	}

	public void setNo_sp4(String no_sp4) {
		this.no_sp4 = no_sp4;
	}

	public String getKode_broker() {
		return kode_broker;
	}

	public void setKode_broker(String kode_broker) {
		this.kode_broker = kode_broker;
	}

	public String getBenefit_broker() {
		return benefit_broker;
	}

	public void setBenefit_broker(String benefit_broker) {
		this.benefit_broker = benefit_broker;
	}

	public String getKode_asuransi() {
		return kode_asuransi;
	}

	public void setKode_asuransi(String kode_asuransi) {
		this.kode_asuransi = kode_asuransi;
	}

	public String getBenefit_asuransi() {
		return benefit_asuransi;
	}

	public void setBenefit_asuransi(String benefit_asuransi) {
		this.benefit_asuransi = benefit_asuransi;
	}

//	public List<Agunan> getAgunan() {
//		return agunan;
//	}
//
//	public void setAgunan(List<Agunan> agunan) {
//		this.agunan = agunan;
//	}

	public Double getPlafon_disetujui() {
		return plafon_disetujui;
	}

	public void setPlafon_disetujui(Double plafon_disetujui) {
		this.plafon_disetujui = plafon_disetujui;
	}

	public Integer getTenor_disetujui() {
		return tenor_disetujui;
	}

	public void setTenor_disetujui(Integer tenor_disetujui) {
		this.tenor_disetujui = tenor_disetujui;
	}

	public Double getAngsuran_disetujui() {
		return angsuran_disetujui;
	}

	public void setAngsuran_disetujui(Double angsuran_disetujui) {
		this.angsuran_disetujui = angsuran_disetujui;
	}

//	public List<Harta> getHarta() {
//		return harta;
//	}
//
//	public void setHarta(List<Harta> harta) {
//		this.harta = harta;
//	}

	public String getAkad_file() {
		return akad_file;
	}

	public void setAkad_file(String akad_file) {
		this.akad_file = akad_file;
	}

	public String getUpload_akad_by() {
		return upload_akad_by;
	}

	public void setUpload_akad_by(String upload_akad_by) {
		this.upload_akad_by = upload_akad_by;
	}

	public LocalDateTime getUpload_akad_date() {
		return upload_akad_date;
	}

	public void setUpload_akad_date(LocalDateTime upload_akad_date) {
		this.upload_akad_date = upload_akad_date;
	}

	public String getId_old_usulan() {
		return id_old_usulan;
	}

	public void setId_old_usulan(String id_old_usulan) {
		this.id_old_usulan = id_old_usulan;
	}

	public Float getExp_rate() {
		return exp_rate;
	}

	public void setExp_rate(Float exp_rate) {
		this.exp_rate = exp_rate;
	}

	public String getId_loan() {
		return id_loan;
	}

	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}

	public String getKode_notaris() {
		return kode_notaris;
	}

	public void setKode_notaris(String kode_notaris) {
		this.kode_notaris = kode_notaris;
	}

	public String getKode_officer_2() {
		return kode_officer_2;
	}

	public void setKode_officer_2(String kode_officer_2) {
		this.kode_officer_2 = kode_officer_2;
	}

	public String getKode_officer_1() {
		return kode_officer_1;
	}

	public void setKode_officer_1(String kode_officer_1) {
		this.kode_officer_1 = kode_officer_1;
	}

	public Integer getGrace_period() {
		return grace_period;
	}

	public void setGrace_period(Integer grace_period) {
		this.grace_period = grace_period;
	}

	public String getFrequensi_pembayaran_margin() {
		return frequensi_pembayaran_margin;
	}

	public void setFrequensi_pembayaran_margin(String frequensi_pembayaran_margin) {
		this.frequensi_pembayaran_margin = frequensi_pembayaran_margin;
	}

	public Integer getFrequensi_pembayaran_number() {
		return frequensi_pembayaran_number;
	}

	public void setFrequensi_pembayaran_number(Integer frequensi_pembayaran_number) {
		this.frequensi_pembayaran_number = frequensi_pembayaran_number;
	}

	public String getKode_margin() {
		return kode_margin;
	}

	public void setKode_margin(String kode_margin) {
		this.kode_margin = kode_margin;
	}

	public Float getBasis_point_margin() {
		return basis_point_margin;
	}

	public void setBasis_point_margin(Float basis_point_margin) {
		this.basis_point_margin = basis_point_margin;
	}

	public String getBasis_point_margin_mark() {
		return basis_point_margin_mark;
	}

	public void setBasis_point_margin_mark(String basis_point_margin_mark) {
		this.basis_point_margin_mark = basis_point_margin_mark;
	}

	public String getNorek_penghasilan() {
		return norek_penghasilan;
	}

	public void setNorek_penghasilan(String norek_penghasilan) {
		this.norek_penghasilan = norek_penghasilan;
	}

	public String getNama_rekening_penghasilan() {
		return nama_rekening_penghasilan;
	}

	public void setNama_rekening_penghasilan(String nama_rekening_penghasilan) {
		this.nama_rekening_penghasilan = nama_rekening_penghasilan;
	}

	public String getKode_rekening_penghasilan() {
		return kode_rekening_penghasilan;
	}

	public void setKode_rekening_penghasilan(String kode_rekening_penghasilan) {
		this.kode_rekening_penghasilan = kode_rekening_penghasilan;
	}

	public String getJenis_pembiayaan() {
		return jenis_pembiayaan;
	}

	public void setJenis_pembiayaan(String jenis_pembiayaan) {
		this.jenis_pembiayaan = jenis_pembiayaan;
	}

	public Double getPremi_asuransi_lama() {
		return premi_asuransi_lama;
	}

	public void setPremi_asuransi_lama(Double premi_asuransi_lama) {
		this.premi_asuransi_lama = premi_asuransi_lama;
	}

	public String getTgl_kredit_lama() {
		return tgl_kredit_lama;
	}

	public void setTgl_kredit_lama(String tgl_kredit_lama) {
		this.tgl_kredit_lama = tgl_kredit_lama;
	}

	public String getBenefit_kredit_lama() {
		return benefit_kredit_lama;
	}

	public void setBenefit_kredit_lama(String benefit_kredit_lama) {
		this.benefit_kredit_lama = benefit_kredit_lama;
	}

	public Integer getTenor_kredit_lama() {
		return tenor_kredit_lama;
	}

	public void setTenor_kredit_lama(Integer tenor_kredit_lama) {
		this.tenor_kredit_lama = tenor_kredit_lama;
	}

	public String getBlokir_saldo() {
		return blokir_saldo;
	}

	public void setBlokir_saldo(String blokir_saldo) {
		this.blokir_saldo = blokir_saldo;
	}

	public String getIs_wakalah() {
		return is_wakalah;
	}

	public void setIs_wakalah(String is_wakalah) {
		this.is_wakalah = is_wakalah;
	}

	public String getNama_rekening_wakalah() {
		return nama_rekening_wakalah;
	}

	public void setNama_rekening_wakalah(String nama_rekening_wakalah) {
		this.nama_rekening_wakalah = nama_rekening_wakalah;
	}

	public String getKode_rekening_wakalah() {
		return kode_rekening_wakalah;
	}

	public void setKode_rekening_wakalah(String kode_rekening_wakalah) {
		this.kode_rekening_wakalah = kode_rekening_wakalah;
	}

	public String getKode_aset_ijarah() {
		return kode_aset_ijarah;
	}

	public void setKode_aset_ijarah(String kode_aset_ijarah) {
		this.kode_aset_ijarah = kode_aset_ijarah;
	}

	public String getIs_biaya_adm() {
		return is_biaya_adm;
	}

	public void setIs_biaya_adm(String is_biaya_adm) {
		this.is_biaya_adm = is_biaya_adm;
	}

	public Double getPersentase_biaya_adm() {
		return persentase_biaya_adm;
	}

	public void setPersentase_biaya_adm(Double persentase_biaya_adm) {
		this.persentase_biaya_adm = persentase_biaya_adm;
	}

	public Double getBiaya_materai() {
		return biaya_materai;
	}

	public void setBiaya_materai(Double biaya_materai) {
		this.biaya_materai = biaya_materai;
	}

	public String getNomor_akad() {
		return nomor_akad;
	}

	public void setNomor_akad(String nomor_akad) {
		this.nomor_akad = nomor_akad;
	}

	public String getNorek_proceed() {
		return norek_proceed;
	}

	public void setNorek_proceed(String norek_proceed) {
		this.norek_proceed = norek_proceed;
	}

	public String getNama_rekening_proceed() {
		return nama_rekening_proceed;
	}

	public void setNama_rekening_proceed(String nama_rekening_proceed) {
		this.nama_rekening_proceed = nama_rekening_proceed;
	}

	public String getKode_rekening_proceed() {
		return kode_rekening_proceed;
	}

	public void setKode_rekening_proceed(String kode_rekening_proceed) {
		this.kode_rekening_proceed = kode_rekening_proceed;
	}

	public String getNorek_loan() {
		return norek_loan;
	}

	public void setNorek_loan(String norek_loan) {
		this.norek_loan = norek_loan;
	}

	public String getNama_rekening_loan() {
		return nama_rekening_loan;
	}

	public void setNama_rekening_loan(String nama_rekening_loan) {
		this.nama_rekening_loan = nama_rekening_loan;
	}

	public String getKode_rekening_loan() {
		return kode_rekening_loan;
	}

	public void setKode_rekening_loan(String kode_rekening_loan) {
		this.kode_rekening_loan = kode_rekening_loan;
	}

	public Double getNisbah_bank() {
		return nisbah_bank;
	}

	public void setNisbah_bank(Double nisbah_bank) {
		this.nisbah_bank = nisbah_bank;
	}

	public Double getNisbah_nasabah() {
		return nisbah_nasabah;
	}

	public void setNisbah_nasabah(Double nisbah_nasabah) {
		this.nisbah_nasabah = nisbah_nasabah;
	}

	public String getNorek_wakalah() {
		return norek_wakalah;
	}

	public void setNorek_wakalah(String norek_wakalah) {
		this.norek_wakalah = norek_wakalah;
	}

	public List<ScoringLoan> getScoringLoan() {
		return ScoringLoan;
	}

	public List<Harta> getHarta() {
		return harta;
	}

	public void setHarta(List<Harta> harta) {
		this.harta = harta;
	}

	public List<Agunan> getAgunan() {
		return agunan;
	}

	public void setAgunan(List<Agunan> agunan) {
		this.agunan = agunan;
	}

	public List<Data_bi> getData_bi() {
		return data_bi;
	}

	public void setData_bi(List<Data_bi> data_bi) {
		this.data_bi = data_bi;
	}

	public String getSp4_file() {
		return sp4_file;
	}

	public void setSp4_file(String sp4_file) {
		this.sp4_file = sp4_file;
	}

	public String getBarang() {
		return barang;
	}

	public void setBarang(String barang) {
		this.barang = barang;
	}

	public BigDecimal getMargin_pengajuan() {
		return margin_pengajuan;
	}

	public void setMargin_pengajuan(BigDecimal margin_pengajuan) {
		this.margin_pengajuan = margin_pengajuan;
	}

	public List<Append_call> getAppend_call() {
		return append_call;
	}

	public void setAppend_call(List<Append_call> append_call) {
		this.append_call = append_call;
	}

	public List<Append_fasilitas_pembiayaan> getAppend_fasilitas_pembiayaan() {
		return append_fasilitas_pembiayaan;
	}

	public void setAppend_fasilitas_pembiayaan(List<Append_fasilitas_pembiayaan> append_fasilitas_pembiayaan) {
		this.append_fasilitas_pembiayaan = append_fasilitas_pembiayaan;
	}

	public List<Append_survey> getAppend_survey() {
		return append_survey;
	}

	public void setAppend_survey(List<Append_survey> append_survey) {
		this.append_survey = append_survey;
	}

	public List<Append_map> getAppend_map() {
		return append_map;
	}

	public void setAppend_map(List<Append_map> append_map) {
		this.append_map = append_map;
	}

	public List<Append_scoring> getAppend_scoring() {
		return append_scoring;
	}

	public void setAppend_scoring(List<Append_scoring> append_scoring) {
		this.append_scoring = append_scoring;
	}

	public Double getBaki_debet_lama() {
		return baki_debet_lama;
	}

	public void setBaki_debet_lama(Double baki_debet_lama) {
		this.baki_debet_lama = baki_debet_lama;
	}



}
