package org.BookNBook.persistence.dao;
import lombok.*;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase Dinámica
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dinamica implements Serializable {
    /**
     * Libro
     */
    private Libro libro;
    /**
     * Usuario
     */
    private Usuario usuario;
    /**
     * Página actual de lectura
     */
    private Integer pagActual;
    /**
     * Días totales de lectura
     */
    private Integer dias;

    /**
     * Constructor del autor a partir de la BBDD
     * @param result datos de la BBDD
     * @throws SQLException
     */
    public Dinamica(ResultSet result) throws SQLException {
        try {
            this.libro = new Libro(result);
            this.usuario = new Usuario(result);
            this.pagActual = result.getInt("pag_actual");
            this.dias = result.getInt("dias");
        } catch (SQLException e) {
            e.getStackTrace();
            throw e;
        }
    }

}
