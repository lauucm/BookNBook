package org.BookNBook.service;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Dinamica;

/**
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
public interface DinamicaService {

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @return
     */
    Dinamica getDinamica(MySQLConnector con, Integer idLibro, Integer idUsuario);

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @param paginas
     * @return
     */
    boolean updatePag(MySQLConnector con, Integer idLibro, Integer idUsuario, Integer paginas);

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @return
     */
    boolean addDinamica(MySQLConnector con, Integer idLibro, Integer idUsuario);

    /**
     *
     * @param con
     * @param idUsuario
     * @return
     */
    Integer paginasLeidas(MySQLConnector con, Integer idUsuario);

}
