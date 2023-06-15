package org.BookNBook.service;

import org.BookNBook.controller.dto.SagaDAO;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Libro;
import org.BookNBook.persistence.dao.Saga;

import java.util.List;

/**
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
public interface SagaService {

    /**
     *
     * @param con
     * @param saga
     * @return
     */
    boolean addSaga(MySQLConnector con, Saga saga);

    /**
     *
     * @param con
     * @param saga
     * @return
     */
    boolean addLibroSaga(MySQLConnector con, SagaDAO saga);

    /**
     *
     * @param con
     * @param id
     * @return
     */
    List<Libro> ListarLibrosSaga (MySQLConnector con, Integer id);

    /**
     *
     * @param con
     * @return
     */
    List<Saga> listarSaga (MySQLConnector con);
}
