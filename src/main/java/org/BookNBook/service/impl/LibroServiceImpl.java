package org.BookNBook.service.impl;

import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.dao.Libro;
import org.BookNBook.persistence.manager.ManagerLibro;
import org.BookNBook.service.LibroService;

import java.util.List;

@AllArgsConstructor
public class LibroServiceImpl implements LibroService {

    ManagerLibro managerLibro;

    @Override
    public Libro buscarLibro(MySQLConnector con, String nombre) {
        return managerLibro.buscarLibro(con, nombre);
    }

    @Override
    public Libro buscarLibro(MySQLConnector con, Integer id) {
        return managerLibro.buscarLibro(con, id);
    }

    @Override
    public boolean addLibro(MySQLConnector con, Libro libro, Autor autor) {
        return managerLibro.addLibro(con, libro, autor);
    }

    @Override
    public List<Libro> listarLibrosTipoGenero(MySQLConnector con, String genero) {
        return managerLibro.listarLibrosTipoGenero(con, genero);
    }

    @Override
    public List<Libro> listarLibrosNoLeidos(MySQLConnector con, Integer idUsuario) {
        return managerLibro.listarLibrosNoLeidos(con, idUsuario);
    }

    @Override
    public List<Libro> listarLibrosLeidos(MySQLConnector con, Integer idUsuario) {
        return managerLibro.listarLibrosLeidos(con, idUsuario);
    }

    @Override
    public List<Libro> listarLibrosenLectura(MySQLConnector con, Integer idUsuario) {
        return managerLibro.listarLibrosenLectura(con, idUsuario);
    }

    @Override
    public List<Libro> listarLibros(MySQLConnector con) {
        return managerLibro.listarLibros(con);
    }
}