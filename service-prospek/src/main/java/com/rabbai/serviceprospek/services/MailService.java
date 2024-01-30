package com.rabbai.serviceprospek.services;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.rabbai.serviceprospek.controllers.ProspekController;
import com.rabbai.serviceprospek.models.Data_prospek;
import com.rabbai.serviceprospek.models.Mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class MailService {
	@Autowired
	private JavaMailSender sender;
	@Autowired
	private Configuration MailtemplateConfig;
	/*
	 * The Spring Framework provides an easy abstraction for sending email by using
	 * the JavaMailSender interface, and Spring Boot provides auto-configuration for
	 * it as well as a starter module.
	 */
	private final JavaMailSender javaMailSender;

	private static Logger LOGGER = Logger.getLogger(MailService.class.getName());

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmailProspek(Data_prospek prospek) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("cms@bankriaukepri.co.id");
		mail.setTo(prospek.getEmail());
		mail.setSubject("Pengajuan Pembiayaan Bank Riau Kepri");
		mail.setText("Anda telah mengajukan pembiayaan ke PT Bank Riau Kepri dengan data sebagai berikut : "
				+ "\n \nNomor Tiket : " + prospek.getNo_tiket() + "\n" + "KTP : " + prospek.getKtp() + "\n" + "Nama : "
				+ prospek.getNama() + "\n" + "Tempat Lahir : " + prospek.getTmp_lahir() + "\n" + "Tanggal Lahir : "
				+ prospek.getTgl_lahir() + "\n" + "Alamat : " + prospek.getAlamat() + "\n" + "Tujuan Pembiayaan : "
				+ prospek.getTujuan_pembiayaan() + "\n" + "Nomor HP : " + prospek.getNo_telp() + "\n \n"
				+ "mohon untuk menyimpan nomor tiket tersebut untuk kebutuhan verifikasi berikutnya. \n");

		javaMailSender.send(mail);
	}

	public void sendEmailProspekHtml(Mail mail) throws MailException, TemplateException {
		MimeMessage msg = javaMailSender.createMimeMessage();
		try {
			LOGGER.info("##### PREPARE SEND EMAIL #######");

			MimeMessageHelper helper = new MimeMessageHelper(msg, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			Template t = MailtemplateConfig.getTemplate("email-template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());

			helper.setFrom("efos@brksyariah.co.id", "Bank Riau Kepri Syariah");
			helper.setTo(mail.getTo());
			helper.setText(html, true);
			helper.setSubject(mail.getSubject());

			// addInline Harus Setelah text html
			ClassPathResource file = new ClassPathResource("logo.png");
			helper.addInline("logo.png", file);

			sender.send(msg);

			LOGGER.info("#####  SEND EMAIL FINISHED #######");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}