package com.mycompany.pdv.util.mail;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package br.com.icone.martan.util.mail;
//
//import com.outjected.email.api.SessionConfig;
//import com.outjected.email.impl.SimpleMailConfig;
//import java.io.IOException;
//import java.util.Properties;
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Produces;
//
///**
// *
// * @author Gleywson
// */
//public class MailConfigProducer {
//    @Produces
//    @ApplicationScoped
//    public SessionConfig getMailConfig() throws IOException {
//        Properties properties = new Properties();
//        properties.load(getClass().getResourceAsStream("/mail.properties"));
//        
//        SimpleMailConfig config = new SimpleMailConfig();
//        config.setServerHost(properties.getProperty("mail.server.host"));
//        config.setServerPort(Integer.parseInt(properties.getProperty("mail.server.port")));
//        config.setEnableSsl(Boolean.parseBoolean(properties.getProperty("mail.enable.ssl")));
//        config.setAuth(Boolean.parseBoolean(properties.getProperty("mail.auth")));
//        config.setUsername(properties.getProperty("mail.username"));
//        config.setPassword(properties.getProperty("mail.password"));
//        
//        return config;
//    }
//}
