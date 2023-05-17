package org.BookNBook.manager;

import org.BookNBook.conector.MySQLConnector;
import org.BookNBook.model.Autor;
import org.BookNBook.model.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerAutor {

    /**
     * Conocer si existe un autor en la base de datos a partir de su pseudonimo
     *
     * @param con
     * @param pseudonimo
     * @return <ul>
     * <li>true si existe el autor en la BBDD</li>ç
     * <li>false si no existe el autor en la BBDD</li>
     * </ul>
     */
    public boolean existeAutor(MySQLConnector con, String pseudonimo) {
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT count(id) as num FROM Autor where pesudonimo = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, pseudonimo);

            ResultSet result = stmt.executeQuery();
            result.beforeFirst();
            if (result.next()) {
                System.out.println(result.getString("num"));
                if (result.getInt("num") > 0) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return false;
    }

    /**
     * Añadir un autor quw no exista en la BBDD
     *
     * @param con
     * @param autor
     * @return <ul>
     * <li>true si no existe el autor, se añade</li>
     * <li>false si existe el autor, no se añade</li>
     * </ul>
     */
    // Si la localidad es nula que coño hago
    public boolean addAutor(MySQLConnector con, Autor autor) {
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO autor(`pseudonimo`, `localidad`) VALUES (?,?)";

        if (!existeAutor(con, autor.getPseudonimo()) && !"".equals(autor.getPseudonimo())) {
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, autor.getPseudonimo());
                stmt.setString(2, autor.getLocalidad());
                stmt.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            return false;
        }
        return false;
    }

    /**
     * Buscar en BBDD el autor por su pseudonimo
     * @param con
     * @param pseudonimo
     * @return <ul>
     *              <li>Autor en caso de que exista en la BBDD</li>
     *              <li>Null en caso de que no exista en la BBDD</li>
     * </ul>
     */
    public Autor buscarAutor(MySQLConnector con, String pseudonimo) {
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM autor WHERE pseudonimo LIKE ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, "%" + pseudonimo + "%");
            ResultSet result = stmt.executeQuery();

            result.beforeFirst();
            result.next();
            Autor autor = new Autor(result);
            return autor;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

