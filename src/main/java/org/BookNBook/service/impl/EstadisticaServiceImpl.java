package org.BookNBook.service.impl;

import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Estadistica;
import org.BookNBook.persistence.manager.ManagerEstadistica;
import org.BookNBook.service.EstadisticaService;

import java.time.LocalDate;

@AllArgsConstructor
public class EstadisticaServiceImpl implements EstadisticaService {

    ManagerEstadistica managerEstadistica;

    @Override
    public Estadistica getEstadistica(MySQLConnector con, Integer idLibro, Integer idUsuario) {
        return managerEstadistica.getEstadistica(con, idLibro, idUsuario);
    }

    @Override
    public boolean addEstadistica(MySQLConnector con, Integer idLibro, Integer idUsuario) {
        return managerEstadistica.addEstadistica(con, idLibro, idUsuario);
    }

    @Override
    public boolean updateCalificacion(MySQLConnector con, Integer idLibro, Integer idUsuario, Double calificacion) {
        return managerEstadistica.updateCalificacion(con, idLibro, idUsuario, calificacion);
    }

    @Override
    public boolean updateFechaInicio(MySQLConnector con, Integer idLibro, Integer idUsuario, String fecha) {
        return managerEstadistica.updateFechaInicio(con, idLibro, idUsuario, fecha);
    }

    @Override
    public boolean updateFechaFinal(MySQLConnector con, Integer idLibro, Integer idUsuario, String fecha) {
        return managerEstadistica.updateFechaFinal(con, idLibro, idUsuario, fecha);
    }

    @Override
    public Double calificacionMedia(MySQLConnector con, Integer idLibro) {
        return managerEstadistica.calificacionMedia(con, idLibro);
    }
}
