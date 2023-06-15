package org.BookNBook.persistence.manager;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.dao.Libro;
import org.BookNBook.persistence.dao.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Manejador Libro para realizar todas las consultas en cuanto a libros
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
public class ManagerLibro {

    /**
     * Obtener un libro según el nombre
     * @param con conexión BBDD
     * @param nombre nombre del libro
     * @return libro en cuestión
     */
    public Libro buscarLibro(MySQLConnector con, String nombre) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT libros.*, autor.pseudonimo, saga.nombre FROM libros INNER JOIN autor ON libros.id_autor = autor.id LEFT JOIN saga ON libros.id_saga = saga.id WHERE libros.nombre LIKE CONCAT( '%',?,'%')";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet result = stmt.executeQuery();

            result.next();
            Libro libro = new Libro(result);
            return libro;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Obtener un libro según el id
     * @param con conexión BBDD
     * @param id Identificador del libro
     * @return libro en cuestión
     */
    public Libro buscarLibro(MySQLConnector con, Integer id) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT libros.*, autor.pseudonimo, saga.nombre FROM libros INNER JOIN autor ON libros.id_autor = autor.id LEFT JOIN saga ON libros.id_saga = saga.id WHERE libros.id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1,id);
            ResultSet result = stmt.executeQuery();

            Libro libro = null;
            if (result.next()) {
                libro = new Libro(result);
                return libro;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Añadir un libro
     * @param con conexión a BBDD
     * @param libro libro
     * @param autor autor del libro
     * @return <ul>
     * <li>true se añade</li>
     * <li>false no se añade</li>
     * </ul>
     */
    public boolean addLibro(MySQLConnector con, Libro libro, Autor autor) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO Libros(`nombre`,`descripcion`,`fecha_publicacion`, `pag_total`, `tipologiaLibro`, `tematicaLibro`, `id_autor`, `url`) VALUES(?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, libro.getNombre());
            stmt.setString(2, libro.getDescripcion());
            stmt.setString(3, libro.getFechaPublicacion().toString());
            stmt.setInt(4, libro.getPaginaTotal());
            stmt.setString(5, libro.getTipologiaLibro().toString());
            stmt.setString(6, libro.getTematicaLibro().toString());
            stmt.setInt(7, autor.getId());
            stmt.setString(8, libro.getUrl());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Obtener una lista de libros según temática y tipo
     * @param con conexión BBDD
     * @param genero género o temática de libro
     * @return listado de libros según el Tipo y la temática
     */
    public List<Libro> listarLibrosTipoGenero(MySQLConnector con, String genero) {
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT Libros.*, autor.pseudonimo FROM libros INNER JOIN autor ON libros.id_autor = autor.id where libros.tematica LIKE '" + genero + "'";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
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
     * Obtener un listado de los libros no leidos por el usuario
     * @param con conexión a BBDD
     * @param idUsuario Identificador de usuario
     * @return listado de libros no leidos por el usuario
     */
    public List<Libro> listarLibrosNoLeidos(MySQLConnector con, Integer idUsuario) {
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT Libros.*, autor.pseudonimo FROM libros INNER JOIN autor ON libros.id_autor = autor.id RIGHT JOIN estadistica ON estadistica.id_libro = libros.id WHERE estadistica.fecha_inicio IS NULL AND estadistica.id_usuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
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
     * Obtener un listado de los libros leidos por el usuario
     * @param con conexión a BBDD
     * @param idUsuario Identificador de usuario
     * @return listado de libros leidos por el usuario
     */
    public List<Libro> listarLibrosLeidos(MySQLConnector con, Integer idUsuario){
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT Libros.*, autor.pseudonimo FROM libros INNER JOIN autor ON libros.id_autor = autor.id INNER JOIN dinamica on dinamica.id_libro = libros.id WHERE dinamica.pag_actual = libros.pag_total AND dinamica.id_usuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
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
     * Obtener un listado de los libros en lectura por el usuario
     * @param con conexión a BBDD
     * @param idUsuario Identificador de usuario
     * @return listado de libros en lectura por el usuario
     */
    public List<Libro> listarLibrosenLectura(MySQLConnector con, Integer idUsuario){
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT Libros.*, autor.pseudonimo FROM libros INNER JOIN autor ON libros.id_autor = autor.id INNER JOIN dinamica on dinamica.id_libro = libros.id where dinamica.pag_actual > 0 AND dinamica.pag_actual < libros.pag_total AND dinamica.id_usuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
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
     * Obtener una lista de libros
     * @param con conexión BBDD
     * @return listado de libros
     */
    public List<Libro> listarLibros(MySQLConnector con) {
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Libro> libros = new ArrayList<>();
        String sql = "SELECT libros.*, autor.pseudonimo, saga.nombre FROM libros INNER JOIN autor ON libros.id_autor = autor.id INNER JOIN saga ON libros.id_saga = saga.id";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet result = stmt.executeQuery();

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
