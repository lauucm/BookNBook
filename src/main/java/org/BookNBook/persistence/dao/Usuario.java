package org.BookNBook.persistence.dao;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.BookNBook.controller.dto.ErrorMessage;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase Usuario
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Usuario extends ErrorMessage implements Serializable {
    /**
     * Identificador
     */
    private Integer id;
    /**
     * Nombre
     */
    private String nombre;
    /**
     * Primer apellido
     */
    private String apellido1;
    /**
     * Segundo apellido
     */
    private String apellido2;
    /**
     * Email
     */
    private String email;
    /**
     * Contraseña
     */
    private String password;
    /**
     * Tipo
     */
    private TipoUsuario tipoUsuario;

    /**
     * Constructor de usuario
     * @param id Identificador
     * @param nombre Nombre
     * @param apellido1 Primer apellido
     * @param apellido2 Segundo apellido
     * @param email Email
     * @param password contraseña
     */
    public Usuario(int id, String nombre, String apellido1, String apellido2, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.password = password;
        this.tipoUsuario = TipoUsuario.NORMAL;
    }


    /**
     *
     * @param result
     * @throws SQLException
     */
    public Usuario(ResultSet result) throws SQLException {
        try {
            this.id = result.getInt("id");
            this.nombre = result.getString("nombre");
            this.apellido1 = result.getString("apellido1");
            this.apellido2 = result.getString("apellido2");
            this.email = result.getString("correo");
            this.password = result.getString("password");
            this.tipoUsuario = TipoUsuario.valueOf(result.getString("tipo_usuario"));
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
