package org.BookNBook.persistence.manager;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.dao.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerLibro {

    private final static String COMMA_STR = "'";
    private final static String PERCENT_STR = "%";


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
            stmt.setString(1, PERCENT_STR + nombre + PERCENT_STR);
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

    public boolean addLibro(MySQLConnector con, Libro libro, Autor autor) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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

    public List<Libro> listarLibrosTipoGenero(MySQLConnector con, String tipo, String genero) {
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM libros where libros.tipo LIKE ? and libros.tematica LIKE ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, COMMA_STR + tipo + COMMA_STR);
            stmt.setString(2, COMMA_STR + genero + COMMA_STR);
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

    public List<Libro> listarLibrosNoLeidos(MySQLConnector con, Integer idUsuario) {
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM libros inner join dinamica on dinamica.id_libro = libros.id where dinamica.pag_actual <1 AND dinamica.id_usuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
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

    public List<Libro> listarLibrosLeidos(MySQLConnector con, Integer idUsuario){
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM libros inner join dinamica on dinamica.id_libro = libros.id where dinamica.pag_actual = libros.pag_total AND dinamica.id_usuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
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

    public List<Libro> listarLibrosenLectura(MySQLConnector con, Integer idUsuario){
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM libros inner join dinamica on dinamica.id_libro = libros.id where dinamica.pag_actual > 0 AND dinamica.id_usuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
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
