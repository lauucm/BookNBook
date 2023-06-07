package org.BookNBook.persistence.dao;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    private Integer id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String password;
    private TipoUsuario tipoUsuario;

    public Usuario(int id, String nombre, String apellido1, String apellido2, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.password = password;
        this.tipoUsuario = TipoUsuario.NORMAL;
    }

    public Usuario(ResultSet result) throws SQLException {
        try {
            this.id = result.getInt("id");
            this.nombre = result.getString("nombre");
            this.apellido1 = result.getString("apellido1");
            this.apellido2 = result.getString("apellido2");
            this.email = result.getString("email");
            this.password = result.getString("password");
            this.tipoUsuario = TipoUsuario.NORMAL;
        }catch(SQLException e) {
            e.getStackTrace();
            throw e;
        }
    }

}
