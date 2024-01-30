package com.brk.servicepencairan.models.mkm;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brk.servicepencairan.models.Par_agunan_bank_atau_nasabah;
import com.brk.servicepencairan.models.Par_agunan_basel2_jenis_agunan;
import com.brk.servicepencairan.models.Par_agunan_cakupan_kewajiban;
import com.brk.servicepencairan.models.Par_agunan_cash_non_cash;
import com.brk.servicepencairan.models.Par_agunan_izin_pengkaitan;
import com.brk.servicepencairan.models.Par_agunan_jenis_agunan;
import com.brk.servicepencairan.models.Par_agunan_jenis_agunan_bi;
import com.brk.servicepencairan.models.Par_agunan_jenis_agunan_ppap;
import com.brk.servicepencairan.models.Par_agunan_kegunaan_agunan;
import com.brk.servicepencairan.models.Par_agunan_kode_kanal;
import com.brk.servicepencairan.models.Par_agunan_kode_pengikatan_internal;
import com.brk.servicepencairan.models.Par_agunan_kode_pengikatan_notarial;
import com.brk.servicepencairan.models.Par_agunan_kode_tipe_dokumen;
import com.brk.servicepencairan.models.Par_agunan_penerbit_agunan;
import com.brk.servicepencairan.models.Par_agunan_penilaian_oleh;
import com.brk.servicepencairan.models.Par_agunan_prioritas_agunan;
import com.brk.servicepencairan.models.Par_agunan_sifat_agunan;
import com.brk.servicepencairan.models.Par_agunan_status_agunan;
import com.brk.servicepencairan.models.Par_agunan_status_akutansi_agun;
import com.brk.servicepencairan.models.Par_asu;
import com.brk.servicepencairan.models.Par_dati2;
import com.brk.servicepencairan.models.Par_kec;
import com.brk.servicepencairan.models.Par_kel;
import com.brk.servicepencairan.models.Par_provinsi;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "m_data_agunan")
public class M_data_agunan implements Serializable {

	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer urut;
	private String id_loan;
	private String jenis;
	private String alamat;
	private BigDecimal kel;
	private Integer kec;
	private Integer dati2;
	private Integer provinsi;
	private Double luas_tanah;
	private Double luas_bangunan;
	private String bukti;
	private String no_bukti;
	private Double harga_pasar;
	private Double nilai_taksasi;
	private Double persentase_taksasi;
	private Double biaya_pengikatan;
	private String file;
	private String status_kepemilikan;
	private String kepemilikan;
	private String kode_pengikatan_internal;
	private String kode_pengikatan_notarial;

	/* penyesuaian spek vlink */
	private LocalDate tgl_penilaian_terakhir;
	private LocalDate tgl_expired_agunan;
	private String deskripsi_agunan;
	private String bank_atau_nasabah;
	private String is_asuransi;
	private LocalDate tgl_awal_asuransi1;
	private LocalDate tgl_akhir_asuransi1;
	private Double asuransi_amount1;
	private String telp_penilai;
	private String perusahaan_penilai;
	private String alamat1;
	private String nama_pengacara;
	private Double limit_agunan_bi;
	private String workstation;
	private LocalDate date_last_maintenance;
	private Double liquidation_amount;
	private Double utilized_amount;
	private LocalDate tgl_apht;
	private Double apht_amount;
	private String nomor_dokumen;
	private LocalDate tgl_pengikatan_internal;
	private LocalDate tgl_pengikatan_notarial;
	private String nomor_pengikatan_internal;
	private String nomor_pengikatan_notarial;
	private Double jumlah_agunan;
	private LocalDate agreement_date;
	private String sifat_agunan;
	private String user_entry;
	private String is_pokok;
	private String is_sk;
	private String jenis_pengikatan;
	private String prioritas_agunan;
	private String izin_pengkaitan;
	private String cakupan_kewajiban;
	private String status_agunan;
	private String status_akutansi_agunan;
	private String kegunaan_agunan;
	private String kode_asuransi1;
	private String kode_asuransi2;
	private String jenis_agunan_bi;
	private String penilaian_oleh;
	private LocalDate tgl_awal_asuransi2;
	private LocalDate tgl_akhir_asuransi2;
	private String alamat2;
	private String alamat3;
	private String alamat4;
	private Double asuransi_amount2;
	private String kode_kanal;
	private String kode_tipe_dokumen;
	private String jenis_agunan_ppap;
	private String cash_non_cash;
	private String penerbit_agunan;
	private String basel2_jenis_agunan;
	private String status_vlink;
	private String pekerjaan_pemilik;
	private String ktp_pemilik;
	private String alamat_pemilik;
	private String append_desc1;
	private String append_desc2;
	private String append_desc3;
	private String append_desc4;
	private String append_desc5;
	
	public M_data_agunan() {
		super();
	}
	
	public M_data_agunan (String id_loan,
			String is_sk,
			String is_pokok,
			String jenis,
			String alamat,
			Integer provinsi,
			Integer dati2,
			Integer kec,
			BigDecimal kel,
			Double luas_tanah,
			Double luas_bangunan,
			String bukti,
			String no_bukti,
			Double harga_pasar,
			Double nilai_taksasi,
			Double persentase_taksasi,
			String kepemilikan,
			LocalDate tgl_penilaian_terakhir,
			LocalDate tgl_expired_agunan,
			String deskripsi_agunan,
			String prioritas_agunan,
			String izin_pengkaitan,
			String cakupan_kewajiban,
			String status_agunan,
			String status_akutansi_agunan,
			String bank_atau_nasabah,
			String kegunaan_agunan,
			String is_asuransi,
			String kode_asuransi1,
			LocalDate tgl_awal_asuransi1,
			LocalDate tgl_akhir_asuransi1,
			Double asuransi_amount1,
			String jenis_agunan_bi,
			String penilaian_oleh,
			String telp_penilai,
			String perusahaan_penilai,
			String alamat1,
			String nama_pengacara,
			Double limit_agunan_bi,
			String workstation,
			LocalDate date_last_maintenance,
			Double liquidation_amount,
			Double utilized_amount,
			LocalDate tgl_apht,
			Double apht_amount,
			String kode_kanal,
			String kode_tipe_dokumen,
			String nomor_dokumen,
			String jenis_pengikatan,
			Double biaya_pengikatan,
			String kode_pengikatan_internal,
			String kode_pengikatan_notarial,
			LocalDate tgl_pengikatan_internal,
			LocalDate tgl_pengikatan_notarial,
			String nomor_pengikatan_internal,
			String nomor_pengikatan_notarial,
			Double jumlah_agunan,
			LocalDate agreement_date,
			String jenis_agunan_ppap,
			String basel2_jenis_agunan,
			String sifat_agunan,
			String penerbit_agunan,
			String cash_non_cash,
			String user_entry,
			String status_kepemilikan,
			String pekerjaan_pemilik,
			String ktp_pemilik,
			String alamat_pemilik,
			String append_desc1,
			String append_desc2,
			String append_desc3,
			String append_desc4,
			String append_desc5
			) {
		this.id_loan=id_loan;
		this.is_sk=is_sk;
		this.is_pokok=is_pokok;
		this.jenis=jenis;
		this.alamat=alamat;
		this.provinsi=provinsi;
		this.dati2=dati2;
		this.kec=kec;
		this.kel=kel;
		this.luas_tanah=luas_tanah;
		this.luas_bangunan=luas_bangunan;
		this.bukti=bukti;
		this.no_bukti=no_bukti;
		this.harga_pasar=harga_pasar;
		this.nilai_taksasi=nilai_taksasi;
		this.persentase_taksasi=persentase_taksasi;
		this.kepemilikan=kepemilikan;
		this.tgl_penilaian_terakhir=tgl_penilaian_terakhir;
		this.tgl_expired_agunan=tgl_expired_agunan;
		this.deskripsi_agunan=deskripsi_agunan;
		this.prioritas_agunan=prioritas_agunan;
		this.izin_pengkaitan=izin_pengkaitan;
		this.cakupan_kewajiban=cakupan_kewajiban;
		this.status_agunan=status_agunan;
		this.status_akutansi_agunan=status_akutansi_agunan;
		this.bank_atau_nasabah=bank_atau_nasabah;
		this.kegunaan_agunan=kegunaan_agunan;
		this.is_asuransi=is_asuransi;
		this.kode_asuransi1=kode_asuransi1;
		this.tgl_awal_asuransi1=tgl_awal_asuransi1;
		this.tgl_akhir_asuransi1=tgl_akhir_asuransi1;
		this.asuransi_amount1=asuransi_amount1;
		this.jenis_agunan_bi=jenis_agunan_bi;
		this.penilaian_oleh=penilaian_oleh;
		this.telp_penilai=telp_penilai;
		this.perusahaan_penilai=perusahaan_penilai;
		this.alamat1=alamat1;
		this.nama_pengacara=nama_pengacara;
		this.limit_agunan_bi=limit_agunan_bi;
		this.workstation=workstation;
		this.date_last_maintenance=date_last_maintenance;
		this.liquidation_amount=liquidation_amount;
		this.utilized_amount=utilized_amount;
		this.tgl_apht=tgl_apht;
		this.apht_amount=apht_amount;
		this.kode_kanal=kode_kanal;
		this.kode_tipe_dokumen=kode_tipe_dokumen;
		this.nomor_dokumen=nomor_dokumen;
		this.jenis_pengikatan=jenis_pengikatan;
		this.biaya_pengikatan=biaya_pengikatan;
		this.kode_pengikatan_internal=kode_pengikatan_internal;
		this.kode_pengikatan_notarial=kode_pengikatan_notarial;
		this.tgl_pengikatan_internal=tgl_pengikatan_internal;
		this.tgl_pengikatan_notarial=tgl_pengikatan_notarial;
		this.nomor_pengikatan_internal=nomor_pengikatan_internal;
		this.nomor_pengikatan_notarial=nomor_pengikatan_notarial;
		this.jumlah_agunan=jumlah_agunan;
		this.agreement_date=agreement_date;
		this.jenis_agunan_ppap=jenis_agunan_ppap;
		this.basel2_jenis_agunan=basel2_jenis_agunan;
		this.sifat_agunan=sifat_agunan;
		this.penerbit_agunan=penerbit_agunan;
		this.cash_non_cash=cash_non_cash;
		this.user_entry=user_entry;
		this.status_kepemilikan=status_kepemilikan;
		this.pekerjaan_pemilik=pekerjaan_pemilik;
		this.ktp_pemilik=ktp_pemilik;
		this.alamat_pemilik=alamat_pemilik;
		this.append_desc1=append_desc1;
		this.append_desc2=append_desc2;
		this.append_desc3=append_desc3;
		this.append_desc4=append_desc4;
		this.append_desc5=append_desc5;

	}

	// PARENT PAR par_agunan_bank_atau_nasabah
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_atau_nasabah", insertable = false, updatable = false)
	private Par_agunan_bank_atau_nasabah desc_agunan_bank_atau_nasabah;

	@JsonProperty("desc_agunan_bank_atau_nasabah")
	public String getPar_agunan_bank_atau_nasabah() {
		if (desc_agunan_bank_atau_nasabah == null) {
			return "";
		} else {
			return desc_agunan_bank_atau_nasabah.getName();
		}
	}

	// PARENT PAR par_agunan_basel2_jenis_agunan
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "basel2_jenis_agunan", insertable = false, updatable = false)
	private Par_agunan_basel2_jenis_agunan desc_agunan_basel2_jenis_agunan;

	@JsonProperty("desc_agunan_basel2_jenis_agunan")
	public String getPar_agunan_basel2_jenis_agunan() {
		if (desc_agunan_basel2_jenis_agunan == null) {
			return "";
		} else {
			return desc_agunan_basel2_jenis_agunan.getName();
		}
	}

	// PARENT PAR cakupan_kewajiban
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cakupan_kewajiban", insertable = false, updatable = false)
	private Par_agunan_cakupan_kewajiban desc_agunan_cakupan_kewajiban;

	@JsonProperty("desc_agunan_cakupan_kewajiban")
	public String getPar_agunan_cakupan_kewajiban() {
		if (desc_agunan_cakupan_kewajiban == null) {
			return "";
		} else {
			return desc_agunan_cakupan_kewajiban.getName();
		}
	}

	// PARENT PAR desc_agunan_cash_non_cash
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cash_non_cash", insertable = false, updatable = false)
	private Par_agunan_cash_non_cash desc_agunan_cash_non_cash;

	@JsonProperty("desc_agunan_cash_non_cash")
	public String getPar_agunan_cash_non_cash() {
		if (desc_agunan_cash_non_cash == null) {
			return "";
		} else {
			return desc_agunan_cash_non_cash.getName();
		}
	}

	// PARENT PAR desc_agunan_izin_pengkaitan
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "izin_pengkaitan", insertable = false, updatable = false)
	private Par_agunan_izin_pengkaitan desc_agunan_izin_pengkaitan;

	@JsonProperty("desc_agunan_izin_pengkaitan")
	public String getPar_agunan_izin_pengkaitan() {
		if (desc_agunan_izin_pengkaitan == null) {
			return "";
		} else {
			return desc_agunan_izin_pengkaitan.getName();
		}
	}

	// PARENT PAR jenis_agunan_bi
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jenis_agunan_bi", insertable = false, updatable = false)
	private Par_agunan_jenis_agunan_bi desc_par_agunan_jenis_agunan_bi;

	@JsonProperty("desc_par_agunan_jenis_agunan_bi")
	public String getPar_agunan_jenis_agunan_bi() {
		if (desc_par_agunan_jenis_agunan_bi == null) {
			return "";
		} else {
			return desc_par_agunan_jenis_agunan_bi.getName();
		}
	}

	// PARENT PAR jenis_agunan_ppap
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jenis_agunan_ppap", insertable = false, updatable = false)
	private Par_agunan_jenis_agunan_ppap desc_agunan_jenis_agunan_ppap;

	@JsonProperty("desc_agunan_jenis_agunan_ppap")
	public String getPar_agunan_jenis_agunan_ppap() {
		if (desc_agunan_jenis_agunan_ppap == null) {
			return "";
		} else {
			return desc_agunan_jenis_agunan_ppap.getName();
		}
	}

	// PARENT PAR par_agunan_jenis_agunan
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jenis", insertable = false, updatable = false)
	private Par_agunan_jenis_agunan desc_jenis;

	@JsonProperty("desc_jenis")
	public String getPar_agunan_jenis_agunan() {
		if (desc_jenis == null) {
			return "";
		} else if (desc_jenis.equals("")) {
			return "";
		} else {	
			return desc_jenis.getName();
		}
	}

	// PARENT PAR par_agunan_kegunaan_agunan
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kegunaan_agunan", insertable = false, updatable = false)
	private Par_agunan_kegunaan_agunan desc_agunan_kegunaan_agunan;

	@JsonProperty("desc_agunan_kegunaan_agunan")
	public String getPar_agunan_kegunaan_agunan() {
		if (desc_agunan_kegunaan_agunan == null) {
			return "";
		} else {
			return desc_agunan_kegunaan_agunan.getName();
		}
	}

	// PARENT PAR kode_kanal
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kode_kanal", insertable = false, updatable = false)
	private Par_agunan_kode_kanal desc_agunan_kode_kanal;

	@JsonProperty("desc_agunan_kode_kanal")
	public String getPar_agunan_kode_kanal() {
		if (desc_agunan_kode_kanal == null) {
			return "";
		} else {
			return desc_agunan_kode_kanal.getName();
		}
	}

	// PARENT PAR _agunan_kode_pengikatan_internal
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kode_pengikatan_internal", insertable = false, updatable = false)
	private Par_agunan_kode_pengikatan_internal desc_agunan_kode_pengikatan_internal;

	@JsonProperty("desc_agunan_kode_pengikatan_internal")
	public String gePar_agunan_kode_pengikatan_internal() {
		if (desc_agunan_kode_pengikatan_internal == null) {
			return "";
		} else {
			return desc_agunan_kode_pengikatan_internal.getName();
		}
	}

	// PARENT PAR par_agunan_kode_pengikatan_notarial
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kode_pengikatan_notarial", insertable = false, updatable = false)
	private Par_agunan_kode_pengikatan_notarial desc_agunan_kode_pengikatan_notarial;

	@JsonProperty("desc_agunan_kode_pengikatan_notarial")
	public String gePar_agunan_kode_pengikatan_notarial() {
		if (desc_agunan_kode_pengikatan_notarial == null) {
			return "";
		} else {
			return desc_agunan_kode_pengikatan_notarial.getName();
		}
	}

	// PARENT PAR desc_agunan_kode_tipe_dokumen
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kode_tipe_dokumen", insertable = false, updatable = false)
	private Par_agunan_kode_tipe_dokumen desc_agunan_kode_tipe_dokumen;

	@JsonProperty("desc_agunan_kode_tipe_dokumen")
	public String gePar_agunan_kode_tipe_dokumen() {
		if (desc_agunan_kode_tipe_dokumen == null) {
			return "";
		} else {
			return desc_agunan_kode_tipe_dokumen.getName();
		}
	}

	// PARENT PAR Par_agunan_penerbit_agunan
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "penerbit_agunan", insertable = false, updatable = false)
	private Par_agunan_penerbit_agunan desc_agunan_penerbit_agunan;

	@JsonProperty("desc_agunan_penerbit_agunan")
	public String gePar_agunan_penerbit_agunan() {
		if (desc_agunan_penerbit_agunan == null) {
			return "";
		} else {
			return desc_agunan_penerbit_agunan.getName();
		}
	}

	// PARENT PAR par_agunan_penilaian_oleh
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "penilaian_oleh", insertable = false, updatable = false)
	private Par_agunan_penilaian_oleh desc_agunan_penilaian_oleh;

	@JsonProperty("desc_agunan_penilaian_oleh")
	public String getPar_agunan_penilaian_oleh() {
		if (desc_agunan_penilaian_oleh == null) {
			return "";
		} else {
			return desc_agunan_penilaian_oleh.getName();
		}
	}

	// PARENT PAR par_agunan_prioritas_agunan
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prioritas_agunan", insertable = false, updatable = false)
	private Par_agunan_prioritas_agunan desc_agunan_prioritas_agunan;

	@JsonProperty("desc_agunan_prioritas_agunan")
	public String getPar_agunan_prioritas_agunan() {
		if (desc_agunan_prioritas_agunan == null) {
			return "";
		} else {
			return desc_agunan_prioritas_agunan.getName();
		}
	}

	// PARENT PAR par_agunan_prioritas_agunan
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sifat_agunan", insertable = false, updatable = false)
	private Par_agunan_sifat_agunan desc_agunan_sifat_agunan;

	@JsonProperty("desc_agunan_sifat_agunan")
	public String getPar_agunan_sifat_agunan() {
		if (desc_agunan_sifat_agunan == null) {
			return "";
		} else {
			return desc_agunan_sifat_agunan.getName();
		}
	}

	// PARENT PAR status_agunan
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_agunan", insertable = false, updatable = false)
	private Par_agunan_status_agunan desc_agunan_status_agunan;

	@JsonProperty("desc_agunan_status_agunan")
	public String getPar_agunan_status_agunan() {
		if (desc_agunan_status_agunan == null) {
			return "";
		} else {
			return desc_agunan_status_agunan.getName();
		}
	}

	// PARENT PAR status_akutansi_agunan
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_akutansi_agunan", insertable = false, updatable = false)
	private Par_agunan_status_akutansi_agun desc_agunan_status_akutansi_agun;

	@JsonProperty("desc_agunan_status_akutansi_agun")
	public String getPar_agunan_status_akutansi_agun() {
		if (desc_agunan_status_akutansi_agun == null) {
			return "";
		} else {
			return desc_agunan_status_akutansi_agun.getName();
		}
	}

	// PARENT PAR par_asu asuransi
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kode_asuransi1", insertable = false, updatable = false)
	private Par_asu desc_asu;

	@JsonProperty("desc_asu1")
	public String getPar_asu() {
		if (desc_asu == null) {
			return "";
		} else {
			return desc_asu.getName();
		}
	}

	// PARENT PAR PROVINSI
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provinsi", insertable = false, updatable = false)
	private Par_provinsi desc_provinsi;

	@JsonProperty("desc_provinsi")
	public String getDesc_provinsi() {
		if (desc_provinsi == null) {
			return "";
		} else {
			return desc_provinsi.getName();
		}
	}

	// PARENT PAR DATI 2
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dati2", insertable = false, updatable = false)
	private Par_dati2 desc_dati2;

	@JsonProperty("desc_dati2")
	public String getDesc_dati2() {
		if (desc_dati2 == null) {
			return "";
		} else {
			return desc_dati2.getName();
		}
	}
	
	@JsonProperty("bi_dati2")
	public String getBi_dati2() {
		if (desc_dati2 == null) {
			return "";
		} else {
			return desc_dati2.getId_bi();
		}
	}

	// PARENT PAR KECAMATAN
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kec", insertable = false, updatable = false)
	private Par_kec desc_kecamatan;

	@JsonProperty("desc_kecamatan")
	public String getDesc_kecamatan() {
		if (desc_kecamatan == null) {
			return "";
		} else {
			return desc_kecamatan.getName();
		}
	}

	// PARENT PAR KELURAHAN
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kel", insertable = false, updatable = false)
	private Par_kel desc_kelurahan;

	@JsonProperty("desc_kelurahan")
	public String getDesc_kelurahan() {
		if (desc_kelurahan == null) {
			return "";
		} else {
			return desc_kelurahan.getName();
		}
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = M_append_taksasi.class)
	@JoinColumn(name = "id_agunan", referencedColumnName = "urut", insertable = false, updatable = false)
	private List<M_append_taksasi> m_append_taksasi;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = M_append_plotting.class)
	@JoinColumn(name = "id_agunan", referencedColumnName = "urut", insertable = false, updatable = false)
	private List<M_append_plotting> m_append_plotting;
	

	public Integer getUrut() {
		return urut;
	}

	public void setUrut(Integer urut) {
		this.urut = urut;
	}

	public String getId_loan() {
		return id_loan;
	}

	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}

	public String getJenis() {
		return jenis;
	}

	public void setJenis(String jenis) {
		this.jenis = jenis;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public BigDecimal getKel() {
		return kel;
	}

	public void setKel(BigDecimal kel) {
		this.kel = kel;
	}

	public Integer getKec() {
		return kec;
	}

	public void setKec(Integer kec) {
		this.kec = kec;
	}

	public Integer getDati2() {
		return dati2;
	}

	public void setDati2(Integer dati2) {
		this.dati2 = dati2;
	}

	public Integer getProvinsi() {
		return provinsi;
	}

	public void setProvinsi(Integer provinsi) {
		this.provinsi = provinsi;
	}


	public String getBukti() {
		return bukti;
	}

	public void setBukti(String bukti) {
		this.bukti = bukti;
	}

	public String getNo_bukti() {
		return no_bukti;
	}

	public void setNo_bukti(String no_bukti) {
		this.no_bukti = no_bukti;
	}

	public Double getBiaya_pengikatan() {
		return biaya_pengikatan;
	}

	public void setBiaya_pengikatan(Double biaya_pengikatan) {
		this.biaya_pengikatan = biaya_pengikatan;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getKepemilikan() {
		return kepemilikan;
	}

	public void setKepemilikan(String kepemilikan) {
		this.kepemilikan = kepemilikan;
	}

	public Double getHarga_pasar() {
		return harga_pasar;
	}

	public void setHarga_pasar(Double harga_pasar) {
		this.harga_pasar = harga_pasar;
	}

	public Double getNilai_taksasi() {
		return nilai_taksasi;
	}

	public void setNilai_taksasi(Double nilai_taksasi) {
		this.nilai_taksasi = nilai_taksasi;
	}

	public Double getPersentase_taksasi() {
		return persentase_taksasi;
	}

	public void setPersentase_taksasi(Double persentase_taksasi) {
		this.persentase_taksasi = persentase_taksasi;
	}

	public LocalDate getTgl_penilaian_terakhir() {
		return tgl_penilaian_terakhir;
	}

	public void setTgl_penilaian_terakhir(LocalDate tgl_penilaian_terakhir2) {
		this.tgl_penilaian_terakhir = tgl_penilaian_terakhir2;
	}

	public LocalDate getTgl_expired_agunan() {
		return tgl_expired_agunan;
	}

	public void setTgl_expired_agunan(LocalDate tgl_expired_agunan2) {
		this.tgl_expired_agunan = tgl_expired_agunan2;
	}

	public String getDeskripsi_agunan() {
		return deskripsi_agunan;
	}

	public void setDeskripsi_agunan(String deskripsi_agunan) {
		this.deskripsi_agunan = deskripsi_agunan;
	}

	public String getBank_atau_nasabah() {
		return bank_atau_nasabah;
	}

	public void setBank_atau_nasabah(String bank_atau_nasabah) {
		this.bank_atau_nasabah = bank_atau_nasabah;
	}

	public String getIs_asuransi() {
		return is_asuransi;
	}

	public void setIs_asuransi(String is_asuransi) {
		this.is_asuransi = is_asuransi;
	}

	public LocalDate getTgl_awal_asuransi1() {
		return tgl_awal_asuransi1;
	}

	public void setTgl_awal_asuransi1(LocalDate tgl_awal_asuransi12) {
		this.tgl_awal_asuransi1 = tgl_awal_asuransi12;
	}

	public LocalDate getTgl_akhir_asuransi1() {
		return tgl_akhir_asuransi1;
	}

	public void setTgl_akhir_asuransi1(LocalDate tgl_awal_asuransi12) {
		this.tgl_akhir_asuransi1 = tgl_awal_asuransi12;
	}

	public Double getAsuransi_amount1() {
		return asuransi_amount1;
	}

	public void setAsuransi_amount1(Double double1) {
		this.asuransi_amount1 = double1;
	}

	public String getTelp_penilai() {
		return telp_penilai;
	}

	public void setTelp_penilai(String telp_penilai) {
		this.telp_penilai = telp_penilai;
	}

	public String getPerusahaan_penilai() {
		return perusahaan_penilai;
	}

	public void setPerusahaan_penilai(String perusahaan_penilai) {
		this.perusahaan_penilai = perusahaan_penilai;
	}

	public String getAlamat1() {
		return alamat1;
	}

	public void setAlamat1(String alamat1) {
		this.alamat1 = alamat1;
	}

	public String getNama_pengacara() {
		return nama_pengacara;
	}

	public void setNama_pengacara(String nama_pengacara) {
		this.nama_pengacara = nama_pengacara;
	}

	public Double getLimit_agunan_bi() {
		return limit_agunan_bi;
	}

	public void setLimit_agunan_bi(Double double1) {
		this.limit_agunan_bi = double1;
	}

	public String getWorkstation() {
		return workstation;
	}

	public void setWorkstation(String workstation) {
		this.workstation = workstation;
	}

	public LocalDate getDate_last_maintenance() {
		return date_last_maintenance;
	}

	public Double getLiquidation_amount() {
		return liquidation_amount;
	}

	public void setLiquidation_amount(Double liquidation_amount) {
		this.liquidation_amount = liquidation_amount;
	}

	public Double getUtilized_amount() {
		return utilized_amount;
	}

	public void setUtilized_amount(Double utilized_amount) {
		this.utilized_amount = utilized_amount;
	}

	public LocalDate getTgl_apht() {
		return tgl_apht;
	}

	public void setTgl_apht(LocalDate tgl_apht2) {
		this.tgl_apht = tgl_apht2;
	}

	public Double getApht_amount() {
		return apht_amount;
	}

	public void setApht_amount(Double double1) {
		this.apht_amount = double1;
	}

	public String getNomor_dokumen() {
		return nomor_dokumen;
	}

	public void setNomor_dokumen(String nomor_dokumen) {
		this.nomor_dokumen = nomor_dokumen;
	}

	public LocalDate getTgl_pengikatan_internal() {
		return tgl_pengikatan_internal;
	}

	public void setTgl_pengikatan_internal(LocalDate tgl_pengikatan_internal2) {
		this.tgl_pengikatan_internal = tgl_pengikatan_internal2;
	}

	public LocalDate getTgl_pengikatan_notarial() {
		return tgl_pengikatan_notarial;
	}

	public void setTgl_pengikatan_notarial(LocalDate tgl_pengikatan_notarial2) {
		this.tgl_pengikatan_notarial = tgl_pengikatan_notarial2;
	}

	public String getNomor_pengikatan_internal() {
		return nomor_pengikatan_internal;
	}

	public void setNomor_pengikatan_internal(String nomor_pengikatan_internal) {
		this.nomor_pengikatan_internal = nomor_pengikatan_internal;
	}

	public String getNomor_pengikatan_notarial() {
		return nomor_pengikatan_notarial;
	}

	public void setNomor_pengikatan_notarial(String nomor_pengikatan_notarial) {
		this.nomor_pengikatan_notarial = nomor_pengikatan_notarial;
	}

	public Double getJumlah_agunan() {
		return jumlah_agunan;
	}

	public void setJumlah_agunan(Double double1) {
		this.jumlah_agunan = double1;
	}

	public LocalDate getAgreement_date() {
		return agreement_date;
	}

	public void setAgreement_date(LocalDate agreement_date2) {
		this.agreement_date = agreement_date2;
	}

	public String getSifat_agunan() {
		return sifat_agunan;
	}

	public void setSifat_agunan(String sifat_agunan) {
		this.sifat_agunan = sifat_agunan;
	}

	public String getUser_entry() {
		return user_entry;
	}

	public void setUser_entry(String user_entry) {
		this.user_entry = user_entry;
	}

	public void setDesc_provinsi(Par_provinsi desc_provinsi) {
		this.desc_provinsi = desc_provinsi;
	}

	public void setDesc_dati2(Par_dati2 desc_dati2) {
		this.desc_dati2 = desc_dati2;
	}

	public void setDesc_kecamatan(Par_kec desc_kecamatan) {
		this.desc_kecamatan = desc_kecamatan;
	}

	public void setDesc_kelurahan(Par_kel desc_kelurahan) {
		this.desc_kelurahan = desc_kelurahan;
	}

	public String getIs_pokok() {
		return is_pokok;
	}

	public void setIs_pokok(String is_pokok) {
		this.is_pokok = is_pokok;
	}

	public String getIs_sk() {
		return is_sk;
	}

	public void setIs_sk(String is_sk) {
		this.is_sk = is_sk;
	}

	public String getJenis_pengikatan() {
		return jenis_pengikatan;
	}

	public void setJenis_pengikatan(String jenis_pengikatan) {
		this.jenis_pengikatan = jenis_pengikatan;
	}

	public String getKode_pengikatan_internal() {
		return kode_pengikatan_internal;
	}

	public String getKode_pengikatan_notarial() {
		return kode_pengikatan_notarial;
	}

	public Double getLuas_tanah() {
		return luas_tanah;
	}

	public void setLuas_tanah(Double luas_tanah) {
		this.luas_tanah = luas_tanah;
	}

	public Double getLuas_bangunan() {
			return luas_bangunan;
	}

	public void setLuas_bangunan(Double luas_bangunan) {
		this.luas_bangunan = luas_bangunan;
	}

	public String getPrioritas_agunan() {
		return prioritas_agunan;
	}

	public void setPrioritas_agunan(String prioritas_agunan) {
		this.prioritas_agunan = prioritas_agunan;
	}

	public String getIzin_pengkaitan() {
		return izin_pengkaitan;
	}

	public void setIzin_pengkaitan(String izin_pengkaitan) {
		this.izin_pengkaitan = izin_pengkaitan;
	}

	public String getCakupan_kewajiban() {
		return cakupan_kewajiban;
	}

	public void setCakupan_kewajiban(String cakupan_kewajiban) {
		this.cakupan_kewajiban = cakupan_kewajiban;
	}

	public String getStatus_agunan() {
		return status_agunan;
	}

	public void setStatus_agunan(String status_agunan) {
		this.status_agunan = status_agunan;
	}

	public String getStatus_akutansi_agunan() {
		return status_akutansi_agunan;
	}

	public void setStatus_akutansi_agunan(String status_akutansi_agunan) {
		this.status_akutansi_agunan = status_akutansi_agunan;
	}

	public String getKegunaan_agunan() {
		return kegunaan_agunan;
	}

	public void setKegunaan_agunan(String kegunaan_agunan) {
		this.kegunaan_agunan = kegunaan_agunan;
	}

	public String getKode_asuransi1() {
		return kode_asuransi1;
	}

	public void setKode_asuransi1(String kode_asuransi1) {
		this.kode_asuransi1 = kode_asuransi1;
	}

	public String getKode_asuransi2() {
		return kode_asuransi2;
	}

	public void setKode_asuransi2(String kode_asuransi2) {
		this.kode_asuransi2 = kode_asuransi2;
	}

	public String getJenis_agunan_bi() {
		return jenis_agunan_bi;
	}

	public void setJenis_agunan_bi(String jenis_agunan_bi) {
		this.jenis_agunan_bi = jenis_agunan_bi;
	}

	public String getPenilaian_oleh() {
		return penilaian_oleh;
	}

	public void setPenilaian_oleh(String penilaian_oleh) {
		this.penilaian_oleh = penilaian_oleh;
	}

	public LocalDate getTgl_awal_asuransi2() {
		return tgl_awal_asuransi2;
	}

	public void setTgl_awal_asuransi2(LocalDate tgl_awal_asuransi2) {
		this.tgl_awal_asuransi2 = tgl_awal_asuransi2;
	}

	public LocalDate getTgl_akhir_asuransi2() {
		return tgl_akhir_asuransi2;
	}

	public void setTgl_akhir_asuransi2(LocalDate tgl_akhir_asuransi2) {
		this.tgl_akhir_asuransi2 = tgl_akhir_asuransi2;
	}

	public String getAlamat2() {
		return alamat2;
	}

	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}

	public String getAlamat3() {
		return alamat3;
	}

	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}

	public String getAlamat4() {
		return alamat4;
	}

	public void setAlamat4(String alamat4) {
		this.alamat4 = alamat4;
	}

	public Double getAsuransi_amount2() {
		return asuransi_amount2;
	}

	public void setAsuransi_amount2(Double asuransi_amount2) {
		this.asuransi_amount2 = asuransi_amount2;
	}

	public String getKode_kanal() {
		return kode_kanal;
	}

	public void setKode_kanal(String kode_kanal) {
		this.kode_kanal = kode_kanal;
	}

	public String getKode_tipe_dokumen() {
		return kode_tipe_dokumen;
	}

	public void setKode_tipe_dokumen(String kode_tipe_dokumen) {
		this.kode_tipe_dokumen = kode_tipe_dokumen;
	}

	public String getJenis_agunan_ppap() {
		return jenis_agunan_ppap;
	}

	public void setJenis_agunan_ppap(String jenis_agunan_ppap) {
		this.jenis_agunan_ppap = jenis_agunan_ppap;
	}

	public String getCash_non_cash() {
		return cash_non_cash;
	}

	public void setCash_non_cash(String cash_non_cash) {
		this.cash_non_cash = cash_non_cash;
	}

	public String getPenerbit_agunan() {
		return penerbit_agunan;
	}

	public void setPenerbit_agunan(String penerbit_agunan) {
		this.penerbit_agunan = penerbit_agunan;
	}

	public String getBasel2_jenis_agunan() {
		return basel2_jenis_agunan;
	}

	public void setBasel2_jenis_agunan(String basel2_jenis_agunan) {
		this.basel2_jenis_agunan = basel2_jenis_agunan;
	}

	public void setKode_pengikatan_internal(String kode_pengikatan_internal) {
		this.kode_pengikatan_internal = kode_pengikatan_internal;
	}

	public void setKode_pengikatan_notarial(String kode_pengikatan_notarial) {
		this.kode_pengikatan_notarial = kode_pengikatan_notarial;
	}

	public String getStatus_vlink() {
		return status_vlink;
	}

	public void setStatus_vlink(String status_vlink) {
		this.status_vlink = status_vlink;
	}
	
	public void setDate_last_maintenance(LocalDate date_last_maintenance) {
		this.date_last_maintenance = date_last_maintenance;
	}

	public void setDesc_agunan_bank_atau_nasabah(Par_agunan_bank_atau_nasabah desc_agunan_bank_atau_nasabah) {
		this.desc_agunan_bank_atau_nasabah = desc_agunan_bank_atau_nasabah;
	}

	public void setDesc_agunan_basel2_jenis_agunan(Par_agunan_basel2_jenis_agunan desc_agunan_basel2_jenis_agunan) {
		this.desc_agunan_basel2_jenis_agunan = desc_agunan_basel2_jenis_agunan;
	}

	public void setDesc_agunan_cash_non_cash(Par_agunan_cash_non_cash desc_agunan_cash_non_cash) {
		this.desc_agunan_cash_non_cash = desc_agunan_cash_non_cash;
	}

	public void setDesc_agunan_izin_pengkaitan(Par_agunan_izin_pengkaitan desc_agunan_izin_pengkaitan) {
		this.desc_agunan_izin_pengkaitan = desc_agunan_izin_pengkaitan;
	}

	public void setDesc_par_agunan_jenis_agunan_bi(Par_agunan_jenis_agunan_bi desc_par_agunan_jenis_agunan_bi) {
		this.desc_par_agunan_jenis_agunan_bi = desc_par_agunan_jenis_agunan_bi;
	}

	public void setDesc_agunan_jenis_agunan_ppap(Par_agunan_jenis_agunan_ppap desc_agunan_jenis_agunan_ppap) {
		this.desc_agunan_jenis_agunan_ppap = desc_agunan_jenis_agunan_ppap;
	}

	public void setDesc_jenis(Par_agunan_jenis_agunan desc_jenis) {
		this.desc_jenis = desc_jenis;
	}

	public void setDesc_agunan_kegunaan_agunan(Par_agunan_kegunaan_agunan desc_agunan_kegunaan_agunan) {
		this.desc_agunan_kegunaan_agunan = desc_agunan_kegunaan_agunan;
	}

	public void setDesc_agunan_kode_kanal(Par_agunan_kode_kanal desc_agunan_kode_kanal) {
		this.desc_agunan_kode_kanal = desc_agunan_kode_kanal;
	}

	public void setDesc_agunan_kode_pengikatan_internal(
			Par_agunan_kode_pengikatan_internal desc_agunan_kode_pengikatan_internal) {
		this.desc_agunan_kode_pengikatan_internal = desc_agunan_kode_pengikatan_internal;
	}

	public void setDesc_agunan_kode_pengikatan_notarial(
			Par_agunan_kode_pengikatan_notarial desc_agunan_kode_pengikatan_notarial) {
		this.desc_agunan_kode_pengikatan_notarial = desc_agunan_kode_pengikatan_notarial;
	}

	public void setDesc_agunan_kode_tipe_dokumen(Par_agunan_kode_tipe_dokumen desc_agunan_kode_tipe_dokumen) {
		this.desc_agunan_kode_tipe_dokumen = desc_agunan_kode_tipe_dokumen;
	}

	public void setDesc_agunan_penerbit_agunan(Par_agunan_penerbit_agunan desc_agunan_penerbit_agunan) {
		this.desc_agunan_penerbit_agunan = desc_agunan_penerbit_agunan;
	}

	public void setDesc_agunan_penilaian_oleh(Par_agunan_penilaian_oleh desc_agunan_penilaian_oleh) {
		this.desc_agunan_penilaian_oleh = desc_agunan_penilaian_oleh;
	}

	public void setDesc_agunan_prioritas_agunan(Par_agunan_prioritas_agunan desc_agunan_prioritas_agunan) {
		this.desc_agunan_prioritas_agunan = desc_agunan_prioritas_agunan;
	}

	public void setDesc_agunan_sifat_agunan(Par_agunan_sifat_agunan desc_agunan_sifat_agunan) {
		this.desc_agunan_sifat_agunan = desc_agunan_sifat_agunan;
	}

	public void setDesc_agunan_status_agunan(Par_agunan_status_agunan desc_agunan_status_agunan) {
		this.desc_agunan_status_agunan = desc_agunan_status_agunan;
	}

	public void setDesc_agunan_status_akutansi_agun(Par_agunan_status_akutansi_agun desc_agunan_status_akutansi_agun) {
		this.desc_agunan_status_akutansi_agun = desc_agunan_status_akutansi_agun;
	}



	public List<M_append_taksasi> getM_append_taksasi() {
		return m_append_taksasi;
	}

	public void setM_append_taksasi(List<M_append_taksasi> m_append_taksasi) {
		this.m_append_taksasi = m_append_taksasi;
	}

	public List<M_append_plotting> getM_append_plotting() {
		return m_append_plotting;
	}

	public void setM_append_plotting(List<M_append_plotting> m_append_plotting) {
		this.m_append_plotting = m_append_plotting;
	}

	public String getStatus_kepemilikan() {
		return status_kepemilikan;
	}

	public void setStatus_kepemilikan(String status_kepemilikan) {
		this.status_kepemilikan = status_kepemilikan;
	}

	public String getPekerjaan_pemilik() {
		return pekerjaan_pemilik;
	}

	public void setPekerjaan_pemilik(String pekerjaan_pemilik) {
		this.pekerjaan_pemilik = pekerjaan_pemilik;
	}

	public String getKtp_pemilik() {
		return ktp_pemilik;
	}

	public void setKtp_pemilik(String ktp_pemilik) {
		this.ktp_pemilik = ktp_pemilik;
	}

	public String getAlamat_pemilik() {
		return alamat_pemilik;
	}

	public void setAlamat_pemilik(String alamat_pemilik) {
		this.alamat_pemilik = alamat_pemilik;
	}

	public String getAppend_desc1() {
		return append_desc1;
	}

	public void setAppend_desc1(String append_desc1) {
		this.append_desc1 = append_desc1;
	}

	public String getAppend_desc2() {
		return append_desc2;
	}

	public void setAppend_desc2(String append_desc2) {
		this.append_desc2 = append_desc2;
	}

	public String getAppend_desc3() {
		return append_desc3;
	}

	public void setAppend_desc3(String append_desc3) {
		this.append_desc3 = append_desc3;
	}

	public String getAppend_desc4() {
		return append_desc4;
	}

	public void setAppend_desc4(String append_desc4) {
		this.append_desc4 = append_desc4;
	}

	public String getAppend_desc5() {
		return append_desc5;
	}

	public void setAppend_desc5(String append_desc5) {
		this.append_desc5 = append_desc5;
	}

}
