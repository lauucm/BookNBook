package org.BookNBook.utilidades.mail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Sender para enviar los correos
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
public class Sender {
    @Setter
    @Getter
    Properties mailProp = new Properties();

    @Setter
    @Getter
    Properties credentialProp = new Properties();

    /**
     * Constructor de la clase donde se cargan las propiedades del mail y los credenciales
     */
    public Sender() {
        try {
            mailProp.load(getClass().getClassLoader().getResourceAsStream("mail.properties"));
            credentialProp.load(getClass().getClassLoader().getResourceAsStream("credentials.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Autenticar las credenciales
     * @return sesión
     */
    private Session createSession() {
        Session session = Session.getInstance(mailProp, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(credentialProp.getProperty(CredentialsConstants.USER),
                        credentialProp.getProperty(CredentialsConstants.PASSWD));
            }
        });

        session.setDebug(true);
        return session;
    }

    /**
     * Enviar el email, al que le pasamos las direcciones, el asunto y el texto
     * @param from    direccion del emisor
     * @param to      direccion del receptor
     * @param subject asunto del correo
     * @param text    cuerpo de texto
     * @return true o false si el mensaje es enviado con exito o no
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean send(String from, String to, String subject, String text)
            throws FileNotFoundException, IOException {
        Session session = createSession();
        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject(subject);

            message.setContent(text, "text/html");

            System.out.println("ENVIANDO...");

            Transport.send(message);
            System.out.println("MENSAJE ENVIADO CON EXITO");

            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }

    }

    public static void main(String[] args) throws IOException {
        new Sender().send("booknbook2023@gmail.com", "booknbook2023@gmail.com", "Prueba BookNBook",
                "Prueba");

    }

}
