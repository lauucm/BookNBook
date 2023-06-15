package org.BookNBook.service;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Estadistica;

import java.time.LocalDate;

/**
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
public interface EstadisticaService {

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @return
     */
    Estadistica getEstadistica(MySQLConnector con, Integer idLibro, Integer idUsuario);

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @return
     */
    boolean addEstadistica(MySQLConnector con, Integer idLibro, Integer idUsuario);

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @param calificacion
     * @return
     */
    boolean updateCalificacion(MySQLConnector con, Integer idLibro, Integer idUsuario, Double calificacion);

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @param fecha
     * @return
     */
    boolean updateFechaInicio(MySQLConnector con, Integer idLibro, Integer idUsuario, String fecha);

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @param fecha
     * @return
     */
    boolean updateFechaFinal(MySQLConnector con, Integer idLibro, Integer idUsuario, String fecha);

    /**
     *
     * @param con
     * @param idLibro
     * @return
     */
    Double calificacionMedia(MySQLConnector con, Integer idLibro);

}
