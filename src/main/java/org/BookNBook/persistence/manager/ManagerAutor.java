package org.BookNBook.persistence.manager;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.dao.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Manejador Autor para realizar todas las consultas en cuanto a autores
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
public class ManagerAutor {

    /**
     * Conocer si existe un autor en la base de datos a partir de su pseudonimo
     * @param con Conexión BBDD
     * @param pseudonimo Nombre del autor
     * @return <ul>
     * <li>true si existe el autor en la BBDD</li>ç
     * <li>false si no existe el autor en la BBDD</li>
     * </ul>
     */
    private boolean existeAutor(MySQLConnector con, String pseudonimo) {
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
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
     * Añadir un autor que no exista en la BBDD
     * @param con conexión BBDD
     * @param autor Autor
     * @return <ul>
     * <li>true si no existe el autor, se añade</li>
     * <li>false si existe el autor, no se añade</li>
     * </ul>
     */
    public boolean addAutor(MySQLConnector con, Autor autor) {
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
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
     * @param con conexión BBDD
     * @param pseudonimo Nombre Autor
     * @return <ul>
     * <li>Autor en caso de que exista en la BBDD</li>
     * <li>Null en caso de que no exista en la BBDD</li>
     * </ul>
     */
    public Autor buscarAutor(MySQLConnector con, String pseudonimo) {
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
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

    /**
     * Lista de todos los libros de un autor
     * @param con conexión BBDD
     * @param id identificador del autor
     * @return <ul>
     * <li>libros cuando el autor tiene libros</li>
     * <li>null si el autor no tiene libros</li>
     * </ul>
     */
    public List<Libro> listarLibrosAutor(MySQLConnector con, Integer id) {
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT libros.* from libros inner join autor on libros.autor= autor.id where autor.id=? ";

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

    /**
     * Listar el total de autores existentes en la BBDD
     * @param con Conexión a la BBDD
     * @return Listado de autores
     */
    public List<Autor> listarAutores(MySQLConnector con) {
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * from autor";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet result = stmt.executeQuery();
            result.beforeFirst();
            ArrayList<Autor> autores = new ArrayList<>();
            while (result.next()) {
                Autor autor = new Autor(result);
                autores.add(autor);
            }
            return autores;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

