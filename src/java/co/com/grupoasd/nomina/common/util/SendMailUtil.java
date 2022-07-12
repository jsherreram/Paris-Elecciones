/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.common.util;

import co.com.grupoasd.nomina.dao.CargoDao;
import co.com.grupoasd.nomina.modelo.EnvioCorreo;
import co.com.grupoasd.nomina.modelo.ParametrosCorreo;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Grupo ASD
 */
public class SendMailUtil {
    public void enviarCorreo(EnvioCorreo envio, ParametrosCorreo parametros) {        
        final String username = envio.getUsuarioSMTP();
        final String password = envio.getPassSMTP();
                
        Properties props = new Properties();       
	
        props.put("mail.smtp.host", parametros.getHost());
        props.put("mail.smtp.ssl.enable",parametros.getEnable());
	//props.put("mail.smtp.socketFactory.port", "465");
	//props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", parametros.getAuth());
	props.put("mail.smtp.port", parametros.getPort());
        //props.put("mail.smtp.ssl.trust","*");
        props.put("mail.smtp.starttls.enable", parametros.getStarttlsEnable());

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(envio.getCorreoRemite()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(envio.getCorreoDestino()));            
            message.setSubject(envio.getTituloCorreo());
            message.setText(envio.getCuerpoCorreo());
            /*message.setText("Dear Mail Crawler,"
                    + "\n\n  No spam to my email, please!");*/            

            Transport.send(message);           

        } catch (MessagingException e) {
            //throw new RuntimeException(e);
            Logger.getLogger(SendMailUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e){
            Logger.getLogger(SendMailUtil.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    
}
