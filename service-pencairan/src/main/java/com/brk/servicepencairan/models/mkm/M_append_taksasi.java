package com.brk.servicepencairan.models.mkm;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "m_append_taksasi")
public class M_append_taksasi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer id_agunan;
	private String append_taksasi1;
	private float append_taksasi2;
	private String append_taksasi3;
	private float append_taksasi4;
	private String append_taksasi5;
	private float append_taksasi6;
	private float append_taksasi7;
	private float append_taksasi8;
	private float append_taksasi9;
	private String append_taksasi10;
	private float append_taksasi11;
	private float append_taksasi12;
	private float append_taksasi13;
	private LocalDate append_taksasi14;
	private float append_taksasi15;
	private String append_taksasi16;
	private String append_taksasi17;
	private String append_taksasi18;
	private String append_taksasi19;
	private String append_taksasi20;
	private String append_taksasi21;
	private String append_taksasi22;
	private String append_taksasi23;
	private String append_taksasi24;
	private String append_taksasi25;
	private String append_taksasi26;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId_agunan() {
		return id_agunan;
	}
	public void setId_agunan(Integer id_agunan) {
		this.id_agunan = id_agunan;
	}
	public String getAppend_taksasi1() {
		return append_taksasi1;
	}
	public void setAppend_taksasi1(String append_taksasi1) {
		this.append_taksasi1 = append_taksasi1;
	}
	public float getAppend_taksasi2() {
		return append_taksasi2;
	}
	public void setAppend_taksasi2(float append_taksasi2) {
		this.append_taksasi2 = append_taksasi2;
	}
	public String getAppend_taksasi3() {
		return append_taksasi3;
	}
	public void setAppend_taksasi3(String append_taksasi3) {
		this.append_taksasi3 = append_taksasi3;
	}
	public float getAppend_taksasi4() {
		return append_taksasi4;
	}
	public void setAppend_taksasi4(float append_taksasi4) {
		this.append_taksasi4 = append_taksasi4;
	}
	public String getAppend_taksasi5() {
		return append_taksasi5;
	}
	public void setAppend_taksasi5(String append_taksasi5) {
		this.append_taksasi5 = append_taksasi5;
	}
	public float getAppend_taksasi6() {
		return append_taksasi6;
	}
	public void setAppend_taksasi6(float append_taksasi6) {
		this.append_taksasi6 = append_taksasi6;
	}
	public float getAppend_taksasi7() {
		return append_taksasi7;
	}
	public void setAppend_taksasi7(float append_taksasi7) {
		this.append_taksasi7 = append_taksasi7;
	}

	public float getAppend_taksasi9() {
		return append_taksasi9;
	}
	public void setAppend_taksasi9(float append_taksasi9) {
		this.append_taksasi9 = append_taksasi9;
	}
	public String getAppend_taksasi10() {
		return append_taksasi10;
	}
	public void setAppend_taksasi10(String append_taksasi10) {
		this.append_taksasi10 = append_taksasi10;
	}
	public float getAppend_taksasi11() {
		return append_taksasi11;
	}
	public void setAppend_taksasi11(float append_taksasi11) {
		this.append_taksasi11 = append_taksasi11;
	}
	public float getAppend_taksasi12() {
		return append_taksasi12;
	}
	public void setAppend_taksasi12(float append_taksasi12) {
		this.append_taksasi12 = append_taksasi12;
	}
	public float getAppend_taksasi13() {
		return append_taksasi13;
	}
	public void setAppend_taksasi13(float append_taksasi13) {
		this.append_taksasi13 = append_taksasi13;
	}
	public LocalDate getAppend_taksasi14() {
		return append_taksasi14;
	}
	public void setAppend_taksasi14(LocalDate append_taksasi14) {
		this.append_taksasi14 = append_taksasi14;
	}
	public float getAppend_taksasi15() {
		return append_taksasi15;
	}
	public void setAppend_taksasi15(float append_taksasi15) {
		this.append_taksasi15 = append_taksasi15;
	}
	public float getAppend_taksasi8() {
		return append_taksasi8;
	}
	public void setAppend_taksasi8(float append_taksasi8) {
		this.append_taksasi8 = append_taksasi8;
	}
	public String getAppend_taksasi16() {
		return append_taksasi16;
	}
	public void setAppend_taksasi16(String append_taksasi16) {
		this.append_taksasi16 = append_taksasi16;
	}
	public String getAppend_taksasi17() {
		return append_taksasi17;
	}
	public void setAppend_taksasi17(String append_taksasi17) {
		this.append_taksasi17 = append_taksasi17;
	}
	public String getAppend_taksasi18() {
		return append_taksasi18;
	}
	public void setAppend_taksasi18(String append_taksasi18) {
		this.append_taksasi18 = append_taksasi18;
	}
	public String getAppend_taksasi19() {
		return append_taksasi19;
	}
	public void setAppend_taksasi19(String append_taksasi19) {
		this.append_taksasi19 = append_taksasi19;
	}
	public String getAppend_taksasi20() {
		return append_taksasi20;
	}
	public void setAppend_taksasi20(String append_taksasi20) {
		this.append_taksasi20 = append_taksasi20;
	}
	public String getAppend_taksasi21() {
		return append_taksasi21;
	}
	public void setAppend_taksasi21(String append_taksasi21) {
		this.append_taksasi21 = append_taksasi21;
	}
	public String getAppend_taksasi22() {
		return append_taksasi22;
	}
	public void setAppend_taksasi22(String append_taksasi22) {
		this.append_taksasi22 = append_taksasi22;
	}
	public String getAppend_taksasi23() {
		return append_taksasi23;
	}
	public void setAppend_taksasi23(String append_taksasi23) {
		this.append_taksasi23 = append_taksasi23;
	}
	public String getAppend_taksasi24() {
		return append_taksasi24;
	}
	public void setAppend_taksasi24(String append_taksasi24) {
		this.append_taksasi24 = append_taksasi24;
	}
	public String getAppend_taksasi25() {
		return append_taksasi25;
	}
	public void setAppend_taksasi25(String append_taksasi25) {
		this.append_taksasi25 = append_taksasi25;
	}
	public String getAppend_taksasi26() {
		return append_taksasi26;
	}
	public void setAppend_taksasi26(String append_taksasi26) {
		this.append_taksasi26 = append_taksasi26;
	}
	
}
