package org.BookNBook.service;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Dinamica;

public interface DinamicaService {

    Dinamica getDinamica(MySQLConnector con, Integer idLibro, Integer idUsuario);

    boolean updatePag(MySQLConnector con, Integer idLibro, Integer idUsuario, Integer paginas);

    boolean addDinamica(MySQLConnector con, Integer idLibro, Integer idUsuario);

    Integer paginasLeidas(MySQLConnector con, Integer idUsuario);

}
