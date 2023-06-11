package org.BookNBook.persistence.conector;

import lombok.Getter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase responsable de la conexion con bbdd.
 *
 */
public class MySQLConnector {

    @Getter
    private Properties prop = new Properties();

    /**
     * Constructor del conector
     */
    public MySQLConnector(){
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Método para conectarse con MySQL indicando el driver que vamos a usar y creando la conexión basada en la URL
     * @return conexión basada en la URL
     */
    public Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        try{
            Class.forName(prop.getProperty(MySQLConstants.DRIVER));

            return DriverManager.getConnection(getURL());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Obtener la URL para conectar la base de datos
     * @return URL
     */
    private String getURL(){
        //jdbc:mysql://localhost:3306/world?user=sa&password=12345678&useSSL=false;
        return new StringBuilder().append(prop.getProperty(MySQLConstants.URL_PREFIX))
                .append(prop.getProperty(MySQLConstants.URL_HOST)).append(":")
                .append(prop.getProperty(MySQLConstants.URL_PORT)).append("/")
                .append(prop.getProperty(MySQLConstants.URL_SCHEMA)).append("?user=")
                .append(prop.getProperty(MySQLConstants.USER)).append("&password=")
                .append(prop.getProperty(MySQLConstants.PASSWD)).append("&useSSL=")
                .append(prop.getProperty(MySQLConstants.URL_SSL)).append(("&allowPublicKeyRetrieval="))
                .append(prop.getProperty(MySQLConstants.ALLOW_PUBLIC_KEY_RETRIEVAL)).append(("&useJDBCCompliantTimezoneShift="))
                .append(prop.getProperty(MySQLConstants.USE_JDBC_COMPLIANT_TIMEZONE_SHIFT)).append(("&useLegacyDatetimeCode="))
                .append(prop.getProperty(MySQLConstants.USE_LEGACY_DATE_TIME_CODE)).append(("&serverTimezone="))
                .append(prop.getProperty(MySQLConstants.SERVER_TIMEZONE)).toString();
    }

}
