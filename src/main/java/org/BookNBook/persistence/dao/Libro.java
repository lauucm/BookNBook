package org.BookNBook.persistence.dao;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro extends Saga{

    private Integer id;
    private String nombre;
    private Autor autor;
    private String descripcion;
    private LocalDate fechaPublicacion;
    private Double calificacionMedia;
    private Integer paginaTotal;
    private TipologiaLibro tipologiaLibro;
    private TematicaLibro tematicaLibro;
    private String url;

    public Libro(int id, String nombre, String pseudonimo, String descripcion, LocalDate fechaPublicacion, Integer paginaTotal, TipologiaLibro tipologiaLibro, TematicaLibro tematicaLibro, String nombreSaga, String url) {
        super(nombreSaga);
        this.id = id;
        this.nombre = nombre;
        this.autor = new Autor(pseudonimo);
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.paginaTotal = paginaTotal;
        this.tipologiaLibro = tipologiaLibro;
        this.tematicaLibro = tematicaLibro;
        this.url = url;
    }

    public Libro(ResultSet result) throws SQLException {
        super(result);
        try {

            this.id = result.getInt("id");
            this.nombre = result.getString("nombre");
            this.autor = new Autor(result.getString("pseudonimo"));
            this.descripcion = result.getString("descripcion");
            this.fechaPublicacion =  LocalDate.parse(result.getString("fecha_publicacion"));
            this.paginaTotal = result.getInt("pag_total");
            this.tipologiaLibro = TipologiaLibro.valueOf(result.getString("tipologiaLibro"));
            this.tematicaLibro = TematicaLibro.valueOf(result.getString("tematicaLibro"));
            this.url = result.getString("url");
        }catch(SQLException e) {
            e.getStackTrace();
            throw e;
        }
    }
}
