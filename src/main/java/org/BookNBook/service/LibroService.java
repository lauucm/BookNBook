package org.BookNBook.service;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.dao.Libro;

import java.util.List;

/**
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
public interface LibroService {

    /**
     *
     * @param con
     * @param nombre
     * @return
     */
    Libro buscarLibro(MySQLConnector con, String nombre);

    /**
     *
     * @param con
     * @param id
     * @return
     */
    Libro buscarLibro(MySQLConnector con, Integer id);

    /**
     *
     * @param con
     * @param libro
     * @param autor
     * @return
     */
    boolean addLibro(MySQLConnector con, Libro libro, Autor autor);

    /**
     *
     * @param con
     * @param genero
     * @return
     */
    List<Libro> listarLibrosTipoGenero(MySQLConnector con, String genero);

    /**
     *
     * @param con
     * @param idUsuario
     * @return
     */
    List<Libro> listarLibrosNoLeidos(MySQLConnector con, Integer idUsuario);

    /**
     *
     * @param con
     * @param idUsuario
     * @return
     */
    List<Libro> listarLibrosLeidos(MySQLConnector con, Integer idUsuario);

    /**
     *
     * @param con
     * @param idUsuario
     * @return
     */
    List<Libro> listarLibrosenLectura(MySQLConnector con, Integer idUsuario);

    /**
     *
     * @param con
     * @return
     */
    List<Libro> listarLibros(MySQLConnector con);
}