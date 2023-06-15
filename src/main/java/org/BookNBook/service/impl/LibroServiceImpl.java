package org.BookNBook.service.impl;

import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.dao.Libro;
import org.BookNBook.persistence.manager.ManagerLibro;
import org.BookNBook.service.LibroService;

import java.util.List;

/**
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@AllArgsConstructor
public class LibroServiceImpl implements LibroService {

    /**
     *
     */
    ManagerLibro managerLibro;

    /**
     *
     * @param con
     * @param nombre
     * @return
     */
    @Override
    public Libro buscarLibro(MySQLConnector con, String nombre) {
        return managerLibro.buscarLibro(con, nombre);
    }

    /**
     *
     * @param con
     * @param id
     * @return
     */
    @Override
    public Libro buscarLibro(MySQLConnector con, Integer id) {
        return managerLibro.buscarLibro(con, id);
    }

    /**
     *
     * @param con
     * @param libro
     * @param autor
     * @return
     */
    @Override
    public boolean addLibro(MySQLConnector con, Libro libro, Autor autor) {
        return managerLibro.addLibro(con, libro, autor);
    }

    /**
     *
     * @param con
     * @param genero
     * @return
     */
    @Override
    public List<Libro> listarLibrosTipoGenero(MySQLConnector con, String genero) {
        return managerLibro.listarLibrosTipoGenero(con, genero);
    }

    /**
     *
     * @param con
     * @param idUsuario
     * @return
     */
    @Override
    public List<Libro> listarLibrosNoLeidos(MySQLConnector con, Integer idUsuario) {
        return managerLibro.listarLibrosNoLeidos(con, idUsuario);
    }

    /**
     *
     * @param con
     * @param idUsuario
     * @return
     */
    @Override
    public List<Libro> listarLibrosLeidos(MySQLConnector con, Integer idUsuario) {
        return managerLibro.listarLibrosLeidos(con, idUsuario);
    }

    /**
     *
     * @param con
     * @param idUsuario
     * @return
     */
    @Override
    public List<Libro> listarLibrosenLectura(MySQLConnector con, Integer idUsuario) {
        return managerLibro.listarLibrosenLectura(con, idUsuario);
    }

    /**
     *
     * @param con
     * @return
     */
    @Override
    public List<Libro> listarLibros(MySQLConnector con) {
        return managerLibro.listarLibros(con);
    }
}