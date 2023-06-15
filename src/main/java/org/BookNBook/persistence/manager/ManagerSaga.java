package org.BookNBook.persistence.manager;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Libro;
import org.BookNBook.persistence.dao.Saga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Manejador Saga para realizar todas las consultas en cuanto a Saga
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
public class ManagerSaga {

    /**
     * Añadir una Saga
     * @param con Conexión a BBDD
     * @param saga Saga
     * @return <ul>
     * <li>true se añade</li>
     * <li>false no se añade</li>
     * </ul>
     */
    public boolean addSaga(MySQLConnector con, Saga saga) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
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

    /**
     * Añadir una Saga
     * @param con Conexión a BBDD
     * @param idLibro Identificador de libro
     * @param idSaga Identificador de Saga
     * @return <ul>
     * <li>true se añade</li>
     * <li>false no se añade</li>
     * </ul>
     */
    public boolean addLibroSaga(MySQLConnector con, Integer idLibro, Integer idSaga) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
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

    /**
     * Obtener listado de libros según la saga
     * @param con Conexión BBDD
     * @param id Identificador de saga
     * @return listado de libros según saga
     */
    public List<Libro> listarLibrosSaga (MySQLConnector con, Integer id){
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql= "SELECT Libros.*, autor.pseudonimo FROM libros INNER JOIN autor ON libros.id_autor = autor.id INNER JOIN saga on saga.id = libros.id_saga where saga.id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
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

    /**
     * Obtener listado de libros según la saga
     * @return listado de libros según saga
     */
    public List<Saga> listarSaga (MySQLConnector con){
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql= "SELECT * FROM saga";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet result = stmt.executeQuery();
            ArrayList<Saga> sagas = new ArrayList<>();
            while (result.next()) {
                Saga saga = new Saga(result);
                sagas.add(saga);
            }
            return sagas;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
