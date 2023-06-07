package org.BookNBook.service;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.dao.Libro;

import java.util.List;


public interface AutorService {
    boolean addAutor(MySQLConnector con, Autor autor);

    Autor buscarAutor(MySQLConnector con, String pseudonimo);

    List<Libro> listarLibrosAutor(MySQLConnector con, Integer id);

    List<Autor> listarAutores(MySQLConnector con);
}