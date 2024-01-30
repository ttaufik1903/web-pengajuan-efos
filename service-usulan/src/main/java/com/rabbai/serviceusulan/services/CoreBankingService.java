package com.rabbai.serviceusulan.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.web.client.RestClientException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.rabbai.serviceusulan.models.CifData;

public interface CoreBankingService {

	public CifData inquiryBroker(String tgl_buka, String plan, String norekening) throws SQLException;

	public ArrayList<CifData> inquiryAllData(String ktp)
			throws ParserConfigurationException, RestClientException, DOMException, SAXException, IOException;

	public Connection konek();

	public String getPremi(String tgl, String from, String to)
			throws ParserConfigurationException, RestClientException, DOMException, SAXException, IOException;
}
