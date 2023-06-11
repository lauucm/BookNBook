package org.BookNBook.persistence.dao;


import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import lombok.*;

/**
 * Clase Estadística
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estadistica implements Serializable {
    /**
     * Libro
     */
    private Libro libro;
    /**
     * Usuario
     */
    private Usuario usuario;
    /**
     * Calificación del usuario
     */
    private Double calificacionActual;
    /**
     * Fecha final de lectura
     */
    private LocalDate fechaFinal;
    /**
     * Fecha de añadido
     */
    private LocalDate fechaAdd;
    /**
     * Fecha de inicio de lectura
     */
    private LocalDate fechaInicio;

    /**
     * Constructor del autor a partir de la BBDD
     * @param result datos de la BBDD
     * @throws SQLException
     */
    public Estadistica(ResultSet result) throws SQLException {
        try {
            this.libro = new Libro(result);
            this.usuario = new Usuario(result);
            this.calificacionActual = result.getDouble("calificacion_personal");
            this.fechaFinal = LocalDate.parse(result.getString("fecha_final"));
            this.fechaAdd = LocalDate.parse(result.getString("fecha_add"));
            this.fechaInicio = LocalDate.parse(result.getString("fecha_inicio"));
        } catch (SQLException e) {
            e.getStackTrace();
            throw e;
        }
    }
}
