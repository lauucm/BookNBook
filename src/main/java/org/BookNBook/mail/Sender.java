package org.BookNBook.mail;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Properties;

/**
 * Clase Sender para enviar los correos
 *
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
     * Constructor de la clase donde se cargan las propiedades del mail y los
     * credenciales
     */
    public Sender() {
        try {
            mailProp.load(getClass().getClassLoader().getResourceAsStream("mail.properties"));
            credentialProp.load(getClass().getClassLoader().getResourceAsStream("credentials.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
