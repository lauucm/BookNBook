package org.BookNBook.service;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Libro;
import org.BookNBook.persistence.dao.Saga;

import java.util.List;

public interface SagaService {

    boolean addSaga(MySQLConnector con, Saga saga);

    boolean addLibroSaga(MySQLConnector con, Integer idLibro, Integer idSaga);

    List<Libro> ListarLibrosSaga (MySQLConnector con, Integer id);

    List<Saga> listarSaga (MySQLConnector con);
}
