package com.rabbai.serviceprospek.services.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.rabbai.serviceprospek.models.CifData;
import com.rabbai.serviceprospek.models.Debitur;
import com.rabbai.serviceprospek.services.CoreBankingService;

@Service(value = "coreBankingService")
public class CoreBankingServiceImpl implements CoreBankingService {
	Connection conn = null;
	@Value("${bv.ip}")
	private String ip;
	@Value("${bv.user}")
	private String user;
	@Value("${bv.pass}")
	private String pass;

	public ArrayList<CifData> inquiryAllData(String ktp)
			throws ParserConfigurationException, RestClientException, DOMException, SAXException, IOException {

		Connection conn = this.konek();
		Statement stmt = null;
		ResultSet rs = null;
		String KTP, NAMA, GOL_DEB, PASSPORT, NPWP, PENDIDIKAN, STATUS_PERNIKAHAN, TMP_LAHIR, TGL_LAHIR, KELAMIN,
				KEBANGSAAN, ALAMAT, NO_TELP, PROFESI, NAMA_INSTANSI, BIDANG_USAHA, NAMA_PASANGAN, TGL_BUKA = null;

		ArrayList<CifData> datajson = new ArrayList<CifData>();
		String sSQL = "";
		try {
			stmt = conn.createStatement();
			sSQL = String.format(
					"SELECT a.CIGVID AS KTP, b.CNNAME AS NAMA, b.CNCDTY AS GOL_DEB, a.CINOPP AS PASSPORT, b.CNNOSS AS NPWP, d.CEPEND AS PENDIDIKAN, "
							+ "a.CICDMS AS STATUS_PERNIKAHAN, a.CIPLBR AS TMP_LAHIR, a.CIDTBR AS TGL_LAHIR, a.CICDFM AS KELAMIN, d.CEBANG AS KEBANGSAAN, "
							+ "c.CAADD1 AS ALAMAT, b.CNNOHP AS NO_TELP, a.CICDPF AS PROFESI, d.CENMPR AS NAMA_INSTANSI, e.CCBUPR AS BIDANG_USAHA, d.CENMSS AS NAMA_PASANGAN, b.CNDTOP AS TGL_BUKA "
							+ "FROM VISIONB.CINDV a " + "LEFT JOIN VISIONB.CNAME b ON a.CINAMK = b.CNNAMK "
							+ "LEFT JOIN VISIONB.CADDR c ON a.CINAMK = c.CANAMK AND c.CAADDK = (SELECT MAX(DISTINCT(CXADDK)) FROM VISIONB.CXREF WHERE CXNAMK = a.CINAMK) "
							+ "LEFT JOIN VISIONB.CNMIX d ON a.CINAMK = d.CENAMK "
							+ "LEFT JOIN VISIONB.CNMCX e ON a.CINAMK = e.CCNAMK WHERE a.CIGVID = '%s' ORDER BY b.CNDTOP DESC  ",
					ktp);
			System.out.println("SQL " + sSQL);

			rs = stmt.executeQuery(sSQL);
			while (rs.next()) {
				KTP = rs.getString("KTP");
				NAMA = rs.getString("NAMA");
				GOL_DEB = rs.getString("GOL_DEB");
				PASSPORT = rs.getString("PASSPORT");
				NPWP = rs.getString("NPWP");
				PENDIDIKAN = rs.getString("PENDIDIKAN");
				STATUS_PERNIKAHAN = rs.getString("STATUS_PERNIKAHAN");
				TMP_LAHIR = rs.getString("TMP_LAHIR");
				TGL_LAHIR = rs.getString("TGL_LAHIR");
				KELAMIN = rs.getString("KELAMIN");
				KEBANGSAAN = rs.getString("KEBANGSAAN");
				ALAMAT = rs.getString("ALAMAT");
				NO_TELP = rs.getString("NO_TELP");
				PROFESI = rs.getString("PROFESI");
				NAMA_INSTANSI = rs.getString("NAMA_INSTANSI");
				BIDANG_USAHA = rs.getString("BIDANG_USAHA");
				NAMA_PASANGAN = rs.getString("NAMA_PASANGAN");
				TGL_BUKA = rs.getString("TGL_BUKA");
				datajson.add(new CifData(KTP, NAMA, GOL_DEB, PASSPORT, NPWP, PENDIDIKAN, STATUS_PERNIKAHAN, TMP_LAHIR,
						TGL_LAHIR, KELAMIN, KEBANGSAAN, ALAMAT, NO_TELP, PROFESI, NAMA_INSTANSI, BIDANG_USAHA,
						NAMA_PASANGAN, TGL_BUKA));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<CifData> value = new ArrayList<CifData>();
		value.addAll(datajson);

		try {
			conn.close();
			System.out.println("DB2 Database Disconnected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}

	public String getPremi(String tgl, String from, String to)
			throws ParserConfigurationException, RestClientException, DOMException, SAXException, IOException {

		Connection conn = this.konek();
		Statement stmt = null;
		ResultSet rs = null;
		String PREMI = null;
		String sSQL = "";
		try {
			stmt = conn.createStatement();
			System.out.print("NOREK ############# " + from + "\n");
			if (from.substring(0, 1).equals("8")) {
				sSQL = String.format(
						"SELECT DHAMT AS PREMI FROM VISIONB.EHISL0 WHERE DHTXCD='2004' and DHACCT='%s' AND DHSACT='%s' AND DHPSDT='%s' AND DHREVS='N' order by DHPSDT asc",
						from, to, tgl);
			} else {
				sSQL = String.format(
						"SELECT DHAMT AS PREMI FROM VISIONB.DHISL0 WHERE DHTXCD='2004' and DHACCT='%s' AND DHSACT='%s' AND DHPSDT='%s' AND DHREVS='N' order by DHPSDT asc",
						from, to, tgl);
			}
			// UNTUK BUS
			// sSQL = String.format("SELECT DHAMT AS PREMI FROM VISIONB.EHISL0 WHERE
			// DHTXCD='2004' and DHACCT='%s' AND DHSACT='%s' AND DHPSDT='%s' AND DHREVS='N'
			// order by DHPSDT asc", from, to, tgl);
			System.out.println("SQL " + sSQL);

			rs = stmt.executeQuery(sSQL);
			if (rs.next()) {
				PREMI = rs.getString("PREMI");

			} else {
				PREMI = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			conn.close();
			System.out.println("DB2 Database Disconnected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return PREMI;
	}

	@Override
	public Connection konek() {
		try {
			Class.forName("com.ibm.as400.access.AS400JDBCDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Please include Classpath  Where your DB2 Driver is located");
			e.printStackTrace();
		}
		// System.out.println("DB2 driver is loaded successfully");

		try {
			conn = DriverManager.getConnection("jdbc:as400://" + ip + ";naming=sql;errors=full", user, pass);

			System.out.println("DB2 " + conn);
			if (conn != null) {
				System.out.println("DB2 Database Connected");
				return conn;
			} else {
				System.out.println("Db2 connection Failed ");
			}
		} catch (SQLException e) {
			System.out.println("DB2 Database connection Failed");
			e.printStackTrace();
			return conn;
		}

		return conn;
	}

	@Override
	public CifData inquiryBroker(String tgl_buka, String plan, String norekening) throws SQLException {
		return null;
	}


}
