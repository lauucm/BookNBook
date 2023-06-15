package org.BookNBook.service;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.dao.Libro;

import java.util.List;

/**
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
public interface AutorService {

    /**
     *
     * @param con
     * @param autor
     * @return
     */
    boolean addAutor(MySQLConnector con, Autor autor);

    /**
     *
     * @param con
     * @param pseudonimo
     * @return
     */
    Autor buscarAutor(MySQLConnector con, String pseudonimo);

    /**
     *
     * @param con
     * @param id
     * @return
     */
    List<Libro> listarLibrosAutor(MySQLConnector con, Integer id);

    /**
     *
     * @param con
     * @return
     */
    List<Autor> listarAutores(MySQLConnector con);
}