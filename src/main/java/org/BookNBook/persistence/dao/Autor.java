package org.BookNBook.persistence.dao;

import lombok.*;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase Autor
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Autor implements Serializable {

    /**
     * ID del autor
     */
    private Integer id;
    /**
     * Nombre dle autor
     */
    private String pseudonimo;
    /**
     * Localidad del autor
     */
    private String localidad;
    /**
     * Listado de libros del autor
     */
    private ArrayList<Saga> libros;

    /**
     * Constructor del autor
     * @param pseudonimo nombre del autor
     */
    public Autor(String pseudonimo) {
        this.pseudonimo = pseudonimo;
        libros = new ArrayList<>();
    }

    /**
     * Constructor del autor
     * @param pseudonimo nombre del autor
     * @param localidad localidad del autor
     * @param libros listado de libros
     */
    public Autor(String pseudonimo, String localidad,ArrayList<Saga> libros) {
        this.pseudonimo = pseudonimo;
        this.localidad = localidad;
        this.libros=libros;
    }

    /**
     * Constructor del autor
     * @param pseudonimo nombre dle autor
     * @param localidad listado de libros
     */
    public Autor(String pseudonimo, String localidad) {
        this.pseudonimo = pseudonimo;
        this.localidad = localidad;
        libros = new ArrayList<>();
    }

    /**
     * Constructor del autor a partir de la BBDD
     * @param result datos de la BBDD
     * @throws SQLException
     */
    public Autor(ResultSet result) throws SQLException {
        this.libros = new ArrayList<>();
        try {
            this.id = result.getInt("id");
            this.pseudonimo = result.getString("pseudonimo");
            this.localidad = result.getString(("localidad"));
        } catch (SQLException e) {
            e.getStackTrace();
            throw e;
        }
    }
}
