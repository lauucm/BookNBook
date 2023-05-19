package org.BookNBook.manager;

import org.BookNBook.conector.MySQLConnector;
import org.BookNBook.model.Autor;
import org.BookNBook.model.Libro;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

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

    public boolean addLibro(MySQLConnector con, Libro libro, Autor autor) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO Libros(`nombre`,`descripcion`,`fecha_publicacion`, `pag_total`, `tipologiaLibro`, `tematicaLibro`, `id_autor`) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, libro.getNombre());
            stmt.setString(2, libro.getDescripcion());
            stmt.setString(3, libro.getFechaPublicacion().toString());
            stmt.setInt(4, libro.getPaginaTotal());
            stmt.setString(5, libro.getTipologiaLibro().toString());
            stmt.setString(6, libro.getTematicaLibro().toString());
            stmt.setInt(7, autor.getId());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Libro> listarLibrosTipoGenero (MySQLConnector con, String tipo, String genero) {
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
            stmt.setString(1, "'" + tipo + "'");
            stmt.setString(2, "'" + genero + "'");
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
