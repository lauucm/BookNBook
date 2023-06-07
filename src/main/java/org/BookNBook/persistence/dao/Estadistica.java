package org.BookNBook.persistence.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estadistica {
    private Libro libro;
    private Usuario usuario;
    private Double calificacionActual;
    private LocalDate fechaFinal;
    private LocalDate fechaAdd;
    private LocalDate fechaInicio;

    public Estadistica(ResultSet result) throws SQLException {
        try {
//            this.libro = new Libro(result));
//            this.usuario = new Usuario(result));
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
