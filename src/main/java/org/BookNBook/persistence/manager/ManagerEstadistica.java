package org.BookNBook.persistence.manager;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Estadistica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

/**
 * Clase Manejador Estadísticas para realizar todas las consultas en cuanto a estadísticas
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
public class ManagerEstadistica {

    /**
     * Obtener los datos de una estadística
     * @param con Conexión a BBDD
     * @param idLibro identificador de libro
     * @param idUsuario identificador de usuario
     * @return Estadística determinada
     */
    public Estadistica getEstadistica(MySQLConnector con, Integer idLibro, Integer idUsuario){

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql= "SELECT * FROM estadistica WHERE id_libro = ? AND id_usuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idLibro);
            stmt.setInt(2, idUsuario);

            ResultSet result = stmt.executeQuery();
            result.beforeFirst();
            result.next();
            return new Estadistica(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Añadir una estadística en la BBDD
     * @param con conexión BBDD
     * @param idLibro identificador de libro
     * @param idUsuario identificador de usuario
     * @return <ul>
     * <li>true se añade</li>
     * <li>false no se añade</li>
     * </ul>
     */
    public boolean addEstadistica(MySQLConnector con, Integer idLibro, Integer idUsuario) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO estadistica(id_libro, id_usuario, fecha_add) VALUES(?, ?, ?);";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idLibro);
            stmt.setInt(2, idUsuario);
            stmt.setString(3, String.valueOf(LocalDate.now()));
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Actualización de la calificación media de un libro por los usuarios
     * @param con conexión BBDD
     * @param idLibro identificador de libro
     * @param idUsuario identificador de usuario
     * @param calificacion calificación media
     * @return <ul>
     * <li>true se ha actualizado</li>
     * <li>false no se ha actualizado</li>
     * </ul>
     */
    public boolean updateCalificacion(MySQLConnector con, Integer idLibro, Integer idUsuario, Double calificacion) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "UPDATE estadistica SET estadistica.calificacion_personal = ? WHERE id_libro = ? AND id_usuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setDouble(1, calificacion);
            stmt.setInt(2, idLibro);
            stmt.setInt(3, idUsuario);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Actualización de la fecha de inicio de lectura de un libro por un usuario
     * @param con conexión BBDD
     * @param idLibro identificador de libro
     * @param idUsuario identificador de usuario
     * @param fecha fecha de inicio de lectura
     * @return <ul>
     * <li>true se ha actualizado</li>
     * <li>false no se ha actualizado</li>
     * </ul>
     */
    public boolean updateFechaInicio(MySQLConnector con, Integer idLibro, Integer idUsuario, String fecha) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "UPDATE estadistica SET estadistica.fecha_inicio = ? WHERE id_libro = ? AND id_usuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, fecha.toString());
            stmt.setInt(2, idLibro);
            stmt.setInt(3, idUsuario);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Actualización de la fecha de final de lectura de un libro por un usuario
     * @param con conexión BBDD
     * @param idLibro identificador de libro
     * @param idUsuario identificador de usuario
     * @param fecha fecha de final de lectura
     * @return <ul>
     * <li>true se ha actualizado</li>
     * <li>false no se ha actualizado</li>
     * </ul>
     */
    public boolean updateFechaFinal(MySQLConnector con, Integer idLibro, Integer idUsuario, String fecha) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "UPDATE estadistica SET estadistica.fecha_final = ? WHERE id_libro = ? AND id_usuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, fecha.toString());
            stmt.setInt(2, idLibro);
            stmt.setInt(3, idUsuario);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Obtener la calificación media de un libro dada por los usuarios
     * @param con conexión BBDD
     * @param idLibro identificador de libro
     * @return calificación media de un libro
     */
    public Double calificacionMedia(MySQLConnector con, Integer idLibro){
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql= "SELECT AVG(calificacion_personal) as calificacion FROM dinamica WHERE id_libro = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idLibro);

            ResultSet result = stmt.executeQuery();
            result.beforeFirst();
            result.next();
            return result.getDouble("calificacion");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
