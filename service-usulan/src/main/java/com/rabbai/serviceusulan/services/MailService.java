package com.rabbai.serviceusulan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailService {
    @Autowired
    private JavaMailSender sender;

    /*
     * The Spring Framework provides an easy abstraction for sending email by using
     * the JavaMailSender interface, and Spring Boot provides auto-configuration for
     * it as well as a starter module.
     */
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

//    public void sendEmailPlain(Instansis instansis, Users userSpv, String password, String passwordSpv) throws MailException {
//        SimpleMailMessage mail = new SimpleMailMessage();
//        mail.setFrom("brkqris@bankriaukepri.co.id");
//        mail.setTo(instansis.getInstansi_email());
//        mail.setSubject("Registrasi Virtual Account Bank Riau Kepri");
//        mail.setText("Berikut ini adalah Informasi untuk Log in ke API Virtual Account Bank Riau Kepri : " +
//                "\n \nNama Instansi : " + instansis.getInstansi_nama() + "\n" +
//                "Nama Rekening : " + instansis.getInstansi_namarek() + "\n" +
//                "Nomor Rekening : " + instansis.getInstansi_norek() + "\n" +
//                "Username Operator : " + instansis.getInstansi_username() + "\n" +
//                "Password Operator : " + password + "\n" +
//                "Username Supervisor : " + userSpv.getUsername() + "\n" +
//                "Password Supervisor : " + passwordSpv + "\n" +
//                "Email : " + instansis.getInstansi_email() + "\n");
//
//        javaMailSender.send(mail);
//    }
//
//    public void sendEmailPlainH2h(Instansis instansis, String password) throws MailException {
//        SimpleMailMessage mail = new SimpleMailMessage();
//        mail.setFrom("brkqris@bankriaukepri.co.id");
//        mail.setTo(instansis.getInstansi_email());
//        mail.setSubject("Registrasi Virtual Account Bank Riau Kepri");
//        mail.setText("Berikut ini adalah Informasi untuk Log in ke API Virtual Account Bank Riau Kepri : " +
//                "\n \nNama Instansi : " + instansis.getInstansi_nama() + "\n" +
//                "Nama Rekening : " + instansis.getInstansi_namarek() + "\n" +
//                "Nomor Rekening : " + instansis.getInstansi_norek() + "\n" +
//                "grant_type : password \n" +
//                "client_id : " + instansis.getInstansi_clientid() + "\n" +
//                "client_secret : " + instansis.getInstansi_clientsecret() + "\n" +
//                "username : " + instansis.getInstansi_kode() + "\n" +
//                "password : " + password + "\n"
//        );
//
//        javaMailSender.send(mail);
//    }
}