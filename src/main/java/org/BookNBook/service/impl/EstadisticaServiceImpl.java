package org.BookNBook.service.impl;

import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Estadistica;
import org.BookNBook.persistence.manager.ManagerEstadistica;
import org.BookNBook.service.EstadisticaService;

import java.time.LocalDate;

/**
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@AllArgsConstructor
public class EstadisticaServiceImpl implements EstadisticaService {

    /**
     *
     */
    ManagerEstadistica managerEstadistica;

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @return
     */
    @Override
    public Estadistica getEstadistica(MySQLConnector con, Integer idLibro, Integer idUsuario) {
        return managerEstadistica.getEstadistica(con, idLibro, idUsuario);
    }

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @return
     */
    @Override
    public boolean addEstadistica(MySQLConnector con, Integer idLibro, Integer idUsuario) {
        return managerEstadistica.addEstadistica(con, idLibro, idUsuario);
    }

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @param calificacion
     * @return
     */
    @Override
    public boolean updateCalificacion(MySQLConnector con, Integer idLibro, Integer idUsuario, Double calificacion) {
        return managerEstadistica.updateCalificacion(con, idLibro, idUsuario, calificacion);
    }

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @param fecha
     * @return
     */
    @Override
    public boolean updateFechaInicio(MySQLConnector con, Integer idLibro, Integer idUsuario, String fecha) {
        return managerEstadistica.updateFechaInicio(con, idLibro, idUsuario, fecha);
    }

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @param fecha
     * @return
     */
    @Override
    public boolean updateFechaFinal(MySQLConnector con, Integer idLibro, Integer idUsuario, String fecha) {
        return managerEstadistica.updateFechaFinal(con, idLibro, idUsuario, fecha);
    }

    /**
     *
     * @param con
     * @param idLibro
     * @return
     */
    @Override
    public Double calificacionMedia(MySQLConnector con, Integer idLibro) {
        return managerEstadistica.calificacionMedia(con, idLibro);
    }
}
