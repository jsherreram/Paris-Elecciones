/**
 */
package co.com.grupoasd.nomina.negocio.ordenes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Security;
import java.util.Date;
import javax.mail.Message;
import javax.mail.Session;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 * @author Erika Parra
 */
public class JavaMail {

    private String from = "";//tu_correo@gmail.com
    private String password = "";//tu password: 123456 :) 
    // destinatario1@hotmail.com,destinatario2@hotmail.com, destinatario_n@hotmail.com
    private InternetAddress[] addressTo;
    private String Subject = "";//titulo del mensaje
    private String MessageMail = "";//contenido del mensaje
    private byte[] adjunto;
   //private String adjunto;
    private String nombreAdjunto = "";

    public JavaMail() {
    }

    public boolean SEND(final Properties datos) throws Exception {
        try {

            //Busca el Path donde se encuentra la aplicacion
            File path = new File(".").getAbsoluteFile();
            // Crea y carga el archivo properties
         //   final Properties datos = new Properties();
////            datos.load(new FileInputStream(path.getCanonicalPath() + "/datosSuplencia.properties"));
          // datos.load(new FileInputStream(path.getCanonicalPath() + "/src/UTIL/datosSuplencia.properties"));
            

            //Crea las propiedades de conexion
            Properties props = new Properties();
            props.put("mail.smtp.host", datos.getProperty("host"));
            props.put("mail.smtp.starttls.enable", datos.getProperty("starttls.enable"));
            props.put("mail.smtp.ssl.enable", datos.getProperty("enable"));
            props.put("mail.smtp.auth", datos.getProperty("auth"));
            props.put("mail.smtp.user", datos.getProperty("userEmailSmtp"));
            props.put("mail.smtp.port", datos.getProperty("port"));

            // Crea la sesion con las propiedades y los datos de usuario
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(datos.getProperty("userEmailSmtp"), getPassword());
                }
            });

            
            MimeMessage mimemessage = new MimeMessage(session);
            InternetAddress addressFrom = new InternetAddress(getFrom());
            mimemessage.setFrom(addressFrom);
            mimemessage.setRecipients(Message.RecipientType.TO, addressTo);
            mimemessage.setSubject(getSubject());
            // Se crea el contenido del mensaje
            MimeBodyPart mimebodypart = new MimeBodyPart();
            String charset = "charset=ISO-8859-1";
            String contentType = "html";

            mimebodypart.setText(getMessage(), charset, contentType);
            mimebodypart.setContent(getMessage(), "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimebodypart);

            //Se adjunta el archivo
            if (!getNombreAdjunto().isEmpty()) {
                mimebodypart = new MimeBodyPart();
                //Agrega el array de bytes al Data Handler correspondiente al PDF
                mimebodypart.setDataHandler(new DataHandler(new ByteArrayDataSource(getAdjunto(), "application/pdf")));
                mimebodypart.setFileName("attachment.pdf");
                mimebodypart.setFileName(getNombreAdjunto());
                multipart.addBodyPart(mimebodypart);
            }
            mimemessage.setContent(multipart);
            mimemessage.setSentDate(new Date());

            
            //Envia el Mensaje por SMTP
            Transport.send(mimemessage);
            /*Transport t=session.getTransport("smtp");
            t.connect(getFrom(), getPassword());
            t.sendMessage(mimemessage, mimemessage.getAllRecipients());
            t.close();*/

            /**
            //Crea la conexión imap con las carpetas del correo cliente(Rackspace) y crea una copia en la carpeta especificada
            Store store = session.getStore("imap");
            store.connect(datos.getProperty("host"), datos.getProperty("userEmailSmtp"), datos.getProperty("passwordEmail"));

            Folder f = store.getFolder("Ordenes Suplencias");

            if (!f.exists()) {
                f.create(Folder.HOLDS_MESSAGES);
            }
            //coloca el mensaje como leido
            mimemessage.setFlag(Flags.Flag.SEEN, true);
            f.appendMessages(new Message[]{mimemessage});//coloca el mensaje en la carpeta
            store.close();
*/
            return true;

        } catch (MessagingException e) {
            //throw new RuntimeException(e);
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } catch (Exception e) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }
    //remitente

    public void setFrom(String mail) {
        this.from = mail;
    }

    public String getFrom() {
        return this.from;
    }
    //Contraseña

    public void setPassword(String value) {
        this.password = new String(value);
    }

    public String getPassword() {
        return this.password;
    }
    //destinatarios

    public void setTo(String mails) {
        String[] tmp = mails.split(",");
        addressTo = new InternetAddress[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            try {
                addressTo[i] = new InternetAddress(tmp[i]);
            } catch (AddressException ex) {
                System.out.println(ex);
            }
        }
    }

    public InternetAddress[] getTo() {
        return this.addressTo;
    }
    //titulo correo

    public void setSubject(String value) {
        this.Subject = value;
    }

    public String getSubject() {
        return this.Subject;
    }
    //contenido del mensaje

    public void setMessage(String value) {
        this.MessageMail = value;
    }

    public String getMessage() {
        return this.MessageMail;
    }

    
   /* public String getAdjunto() {
        return adjunto;
    }

    
    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }*/
    
    public byte[] getAdjunto() {
    return adjunto;
    }
    public void setAdjunto(byte[] adjunto) {
    this.adjunto = adjunto;
    }
   

    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

}
