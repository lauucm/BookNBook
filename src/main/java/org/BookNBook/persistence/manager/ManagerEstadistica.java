package org.BookNBook.persistence.manager;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Estadistica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class ManagerEstadistica {

    public Estadistica getEstadistica(MySQLConnector con, Integer idLibro, Integer idUsuario){

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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

    public boolean addEstadistica(MySQLConnector con, Integer idLibro, Integer idUsuario) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO Estadistica(`id_libro`, `id_usuario`, `fecha_add`) VALUES(?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idLibro);
            stmt.setInt(2, idUsuario);
            stmt.setString(3, new Date().toString());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCalificacion(MySQLConnector con, Integer idLibro, Integer idUsuario, Double calificacion) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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

    public boolean updateFechaInicio(MySQLConnector con, Integer idLibro, Integer idUsuario, LocalDate fecha) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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

    public boolean updateFechaFinal(MySQLConnector con, Integer idLibro, Integer idUsuario, LocalDate fecha) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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

    public Double calificacionMedia(MySQLConnector con, Integer idLibro){
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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
