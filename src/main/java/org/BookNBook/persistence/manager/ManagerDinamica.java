package org.BookNBook.persistence.manager;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Dinamica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase Manejador Dinámica para realizar todas las consultas en cuanto a dinámicas
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
public class ManagerDinamica {

    /**
     * Obtener los datos de una dinámica determinada
     * @param con conexión BBDD
     * @param idLibro Identificador de libro
     * @param idUsuario Identificador de usuario
     * @return Dinámica específica
     */
    public Dinamica getDinamica(MySQLConnector con, Integer idLibro, Integer idUsuario){

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql= "SELECT * FROM dinamica WHERE id_libro = ? AND id_usuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idLibro);
            stmt.setInt(2, idUsuario);

            ResultSet result = stmt.executeQuery();
            result.beforeFirst();
            result.next();
            return new Dinamica(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Actualizar el número de páginas actuales de lectura de un libro por un usuario
     * @param con Conexión BBDD
     * @param idLibro Identificador de libro
     * @param idUsuario Identificador de usuario
     * @param paginas Página actual de lectura
     * @return <ul>
     * <li>true si se ha actualizado</li>
     * <li>false si no se ha actualizado</li>
     * </ul>
     */
    public boolean updatePag(MySQLConnector con, Integer idLibro, Integer idUsuario, Integer paginas) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "UPDATE dinamica SET pag_actual = ? AND dias = ? WHERE id_libro = ? AND id_usuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            Dinamica dinamica = getDinamica(con, idLibro, idUsuario);
            Integer dias =  dinamica.getDias();
            stmt.setInt(1, paginas);
            stmt.setInt(2, ++dias);
            stmt.setInt(3, idLibro);
            stmt.setInt(4, idUsuario);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Añadir una dinámica
     * @param con Conexión BBDD
     * @param idLibro Identificador de libro
     * @param idUsuario Identificador de usuario
     * @return <ul>
     * <li>true si se ha añadido</li>
     * <li>false si no se ha añadido</li>
     * </ul>
     */
    public boolean addDinamica(MySQLConnector con, Integer idLibro, Integer idUsuario) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO Dinamica(`id_libro`, `id_usuario`) VALUES(?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idLibro);
            stmt.setInt(2, idUsuario);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Obtener el número de páginas totales leídas por un usuario
     * @param con conexión BBDD
     * @param idUsuario identificador de usuario
     * @return Número de páginas totales leídas
     */
    public Integer paginasLeidas(MySQLConnector con, Integer idUsuario){
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql= "SELECT SUM(pag_actual) as paginas FROM dinamica WHERE id_usuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);

            ResultSet result = stmt.executeQuery();
            result.beforeFirst();
            result.next();
            return result.getInt("paginas");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
