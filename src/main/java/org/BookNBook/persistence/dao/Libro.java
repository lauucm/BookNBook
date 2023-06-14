package org.BookNBook.persistence.dao;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Clase Libro
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Libro extends Saga implements Serializable {

    /**
     * Identificador
     */
    private Integer id;
    /**
     * Nombre
     */
    private String nombre;
    /**
     * Autor
     */
    private Autor autor;
    /**
     * Descripción
     */
    private String descripcion;
    /**
     * Fecha de publicación
     */
    private String fechaPublicacion;
    /**
     * Calificación media del libro
     */
    private Double calificacionMedia;
    /**
     * Páginas totales
     */
    private Integer paginaTotal;
    /**
     * Tipo
     */
    private TipologiaLibro tipologiaLibro;
    /**
     * Temática
     */
    private TematicaLibro tematicaLibro;
    /**
     * Portada
     */
    private String url;

    /**
     *
     * @param id Identificador del libro
     * @param nombre Nombre dle libro
     * @param pseudonimo Autor
     * @param descripcion Descripción
     * @param fechaPublicacion Fecha
     * @param paginaTotal Páginas totales
     * @param tipologiaLibro Tipo de libro
     * @param tematicaLibro Temática del libro
     * @param nombreSaga Saga
     * @param url Portada
     */
    public Libro(int id, String nombre, String pseudonimo, String descripcion, String fechaPublicacion, Integer paginaTotal, TipologiaLibro tipologiaLibro, TematicaLibro tematicaLibro, String nombreSaga, String url) {
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

    /**
     * Constructor del autor a partir de la BBDD
     * @param result datos de la BBDD
     * @throws SQLException
     */
    public Libro(ResultSet result) throws SQLException {
        super(result);
        try {
            this.id = result.getInt("id");
            this.nombre = result.getString("nombre");
            this.autor = new Autor(result.getString("pseudonimo"));
            this.descripcion = result.getString("descripcion");
            this.fechaPublicacion =  result.getString("fecha_publicacion");
            this.paginaTotal = result.getInt("pag_total");
            this.tipologiaLibro = TipologiaLibro.valueOf(result.getString("tipo"));
            this.tematicaLibro = TematicaLibro.valueOf(result.getString("tematica"));
            this.url = result.getString("url");
        }catch(SQLException e) {
            e.getStackTrace();
            throw e;
        }
    }
}
