package org.BookNBook.service;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.dao.Libro;

import java.util.List;

public interface LibroService {

    Libro buscarLibro(MySQLConnector con, String nombre);

    Libro buscarLibro(MySQLConnector con, Integer id);

    boolean addLibro(MySQLConnector con, Libro libro, Autor autor);

    List<Libro> listarLibrosTipoGenero(MySQLConnector con, String genero);

    List<Libro> listarLibrosNoLeidos(MySQLConnector con, Integer idUsuario);

    List<Libro> listarLibrosLeidos(MySQLConnector con, Integer idUsuario);

    List<Libro> listarLibrosenLectura(MySQLConnector con, Integer idUsuario);

    List<Libro> listarLibros(MySQLConnector con);
}