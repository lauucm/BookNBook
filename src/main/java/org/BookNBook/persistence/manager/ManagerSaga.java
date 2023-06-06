package org.BookNBook.persistence.manager;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.dao.Libro;
import org.BookNBook.dao.Saga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerSaga {

    public boolean addSaga(MySQLConnector con, Saga saga) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO Saga(`nombre`) VALUES(?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, saga.getNombre());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addLibroSaga(MySQLConnector con, Integer idLibro, Integer idSaga) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "UPDATE Libros SET libros.id_saga = ? WHERE libros.id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idSaga);
            stmt.setInt(2, idLibro);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Libro> ListarLibrosSaga (MySQLConnector con, Integer id){
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql= "SELECT libros.* FROM saga inner join libros on saga.id = libros.id_saga where saga.id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            result.beforeFirst();
            ArrayList<Libro> libros = new ArrayList<>();
            while (result.next()) {
                Libro libro = new Libro(result);
                libros.add(libro);
            }
            return libros;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
