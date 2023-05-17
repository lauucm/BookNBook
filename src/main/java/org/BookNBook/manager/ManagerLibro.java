package org.BookNBook.manager;

import org.BookNBook.conector.MySQLConnector;
import org.BookNBook.model.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerLibro {

    public Libro buscarLibro(MySQLConnector con, String nombre) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM libros WHERE nombre LIKE ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            ResultSet result = stmt.executeQuery();

            result.beforeFirst();
            result.next();
            Libro libro = new Libro(result);
            return libro;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addLibro(MySQLConnector con, Libro libro) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //AUTOR ?
        String sql = "INSERT INTO Libros(`nombre`,`descripcion`,`fecha_publicacion`, `pag_total`, `tipologiaLibro`, `tematicaLibro`) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, libro.getNombre());
            stmt.setString(2, libro.getDescripcion());
            //stmt.setString(3, libro.getFechaPublicacion());
            stmt.setInt(4, libro.getPaginaTotal());
            //stmt.setString(5, libro.getTipologiaLibro());
            //stmt.setString(6, libro.getTematicaLibro());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}