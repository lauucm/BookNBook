package org.BookNBook.persistence.dao;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dinamica {
    private Libro libro;
    private Usuario usuario;
    private Integer pagActual;
    private Integer dias;

    public Dinamica(ResultSet result) throws SQLException {
        try {
//            this.libro = new Libro(result.getInt("id_libro"));
//            this.usuario = new Usuario(result.getInt("id_usuario"));
            this.pagActual = result.getInt("pag_actual");
            this.dias = result.getInt("dias");
        } catch (SQLException e) {
            e.getStackTrace();
            throw e;
        }
    }

}
