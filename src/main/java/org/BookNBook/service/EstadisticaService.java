package org.BookNBook.service;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Estadistica;

import java.time.LocalDate;

public interface EstadisticaService {

    Estadistica getEstadistica(MySQLConnector con, Integer idLibro, Integer idUsuario);

    boolean addEstadistica(MySQLConnector con, Integer idLibro, Integer idUsuario);

    boolean updateCalificacion(MySQLConnector con, Integer idLibro, Integer idUsuario, Double calificacion);

    boolean updateFechaInicio(MySQLConnector con, Integer idLibro, Integer idUsuario, LocalDate fecha);

    boolean updateFechaFinal(MySQLConnector con, Integer idLibro, Integer idUsuario, LocalDate fecha);

    Double calificacionMedia(MySQLConnector con, Integer idLibro);

}
