package net.javaguides.springboot.model;

import java.util.Date;

public class Pendaftaran {

	private String ktp;
	private String nama;
	private String npwp;
	private String status_pernikahan;
	private String tmp_lahir;
	private String tgl_lahir;
	private String kelamin;
	private String alamat;
	private String cab;
	private String no_telp;
	private String email;
	private String tujuan_pembiayaan;
	private String tujuan_pembiayaan_lainnya;

	private String nomor_tiket;
	private String plafon_pengajuan;
	private String tenor_pengajuan;
	private String jenis_pembiayaan;

	/* CR 2023 */
	private String jumlah_penghasilan;
	private String pekerjaan;
	private String wilayah_kabupaten;
 
	public String getJenis_pembiayaan() {
		return jenis_pembiayaan;
	}

	public void setJenis_pembiayaan(String jenis_pembiayaan) {
		this.jenis_pembiayaan = jenis_pembiayaan;
	}

	public String getPlafon_pengajuan() {
		return plafon_pengajuan;
	}

	public void setPlafon_pengajuan(String plafon_pengajuan) {
		this.plafon_pengajuan = plafon_pengajuan;
	}

	public String getTenor_pengajuan() {
		return tenor_pengajuan;
	}

	public void setTenor_pengajuan(String tenor_pengajuan) {
		this.tenor_pengajuan = tenor_pengajuan;
	}

	public String getKtp() {
		return ktp;
	}

	public void setKtp(String ktp) {
		this.ktp = ktp;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNpwp() {
		return npwp;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public String getStatus_pernikahan() {
		return status_pernikahan;
	}

	public void setStatus_pernikahan(String status_pernikahan) {
		this.status_pernikahan = status_pernikahan;
	}

	public String getTmp_lahir() {
		return tmp_lahir;
	}

	public void setTmp_lahir(String tmp_lahir) {
		this.tmp_lahir = tmp_lahir;
	}

	public String getTgl_lahir() {
		return tgl_lahir;
	}

	public void setTgl_lahir(String tgl_lahir) {
		this.tgl_lahir = tgl_lahir;
	}

	public String getKelamin() {
		return kelamin;
	}

	public void setKelamin(String kelamin) {
		this.kelamin = kelamin;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getCab() {
		return cab;
	}

	public void setCab(String cab) {
		this.cab = cab;
	}

	public String getNo_telp() {
		return no_telp;
	}

	public void setNo_telp(String no_telp) {
		this.no_telp = no_telp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTujuan_pembiayaan() {
		return tujuan_pembiayaan;
	}

	public void setTujuan_pembiayaan(String tujuan_pembiayaan) {
		this.tujuan_pembiayaan = tujuan_pembiayaan;
	}

	public String getNomor_tiket() {
		return nomor_tiket;
	}

	public void setNomor_tiket(String nomor_tiket) {
		this.nomor_tiket = nomor_tiket;
	}

	public String getTujuan_pembiayaan_lainnya() {
		return tujuan_pembiayaan_lainnya;
	}

	public void setTujuan_pembiayaan_lainnya(String tujuan_pembiayaan_lainnya) {
		this.tujuan_pembiayaan_lainnya = tujuan_pembiayaan_lainnya;
	}

	public String getJumlah_penghasilan() {
		return jumlah_penghasilan;
	}

	public void setJumlah_penghasilan(String jumlah_penghasilan) {
		this.jumlah_penghasilan = jumlah_penghasilan;
	}

	public String getPekerjaan() {
		return pekerjaan;
	}

	public void setPekerjaan(String pekerjaan) {
		this.pekerjaan = pekerjaan;
	}

	public String getWilayah_kabupaten() {
		return wilayah_kabupaten;
	}

	public void setWilayah_kabupaten(String wilayah_kabupaten) {
		this.wilayah_kabupaten = wilayah_kabupaten;
	}

}
