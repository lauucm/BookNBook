package org.BookNBook.persistence.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase Saga
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Saga implements Serializable {

    /**
     * Identificador
     */
    private Integer id;
    /**
     * Nombre
     */
    private String nombre;
    /**
     * Libros
     */
    private ArrayList<Libro> libros;

    /**
     * Constructor de saga
     * @param nombre nombre
     */
    public Saga(String nombre){
        this.nombre=nombre;
        this.libros = new ArrayList<>();
    }

    /**
     * Constructor de saga
     * @param nombre nombre
     * @param libros listado de libros
     */
    public Saga(String nombre, ArrayList<Libro>libros){
        this.nombre=nombre;
        this.libros = libros;
    }

    /**
     * Constructor del autor a partir de la BBDD
     * @param result datos de la BBDD
     * @throws SQLException
     */
    public Saga(ResultSet result) throws SQLException {
        this.libros = new ArrayList<>();
        try {
            this.id = result.getInt("id");
            this.nombre = result.getString("nombre");
        }catch(SQLException e) {
            e.getStackTrace();
            throw e;
        }
    }

}
