package org.BookNBook.persistence.manager;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.dao.Libro;
import org.BookNBook.persistence.dao.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Manejador Usuario para realizar todas las consultas en cuanto a usuarios
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
public class ManagerUsuario {

    /**
     * Conocer si existe un usuario en la base de datos a partir de su email
     * @param con conexión BBDD
     * @param correo email usuario
     * @return <ul>
     *     <li>true si existe un usuario con el email</li>ç
     *     <li>false si no existe un usuario con el email</li>
     * </ul>
     *
     */
    private boolean existeUsuario(MySQLConnector con, String correo) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM Usuario WHERE correo = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, correo);

            ResultSet result = stmt.executeQuery();
            result.beforeFirst();
            return result.next() ? true : false;
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return false;
    }

    /**
     * Ingresar un nuevo usuario dentro de la base datos
     * @param con conexión a BBDD
     * @param usuario usuario
     * @return <ul>
     *     <li>true se ha podido crear un usuario</li>
     *     <li>false si no se ha podido crear un usuario</li>
     * </ul>
     */
    public boolean newUsuario (MySQLConnector con, Usuario usuario) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        if (!existeUsuario(con, usuario.getEmail()) && ! "".equals(usuario.getEmail())) {
            String sql = "INSERT INTO Usuario (`nombre`,`apellido1`,`apellido2`,`correo`,`password`, `tipo_usuario`) VALUES(?,?,?,?,?,?)";

            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, usuario.getNombre());
                stmt.setString(2, usuario.getApellido1());
                stmt.setString(3, usuario.getApellido2());
                stmt.setString(4, usuario.getEmail());
                stmt.setString(5, usuario.getPassword());
                stmt.setString(6, usuario.getTipoUsuario().toString());
                stmt.execute();
                System.out.println("Nuevo usuario registrado");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usuario ya registrado previamente");
            return false;
        }
        return false;
    }

    /**
     * Validar si el email y la contraseña introducidos pertenecen al mismo usuario
     * @param con conexión a BBDD
     * @param usuario email usuario
     * @param passwd contraseña usuario
     * @return <ul>
     *     <li>true si el correo y la contraseña son validos</li>
     *     <li>false si el correo o contraseña no son validos</li>
     * </ul>
     */
    private boolean validarUsuario(MySQLConnector con, String usuario, String passwd) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM Usuario WHERE correo=? AND password=?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, passwd);
            ResultSet result = stmt.executeQuery();
            return result.next() ? true : false;
        } catch (SQLException esql) {
            System.out.println(esql.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Comprobación del usuario que ha iniciado sesion
     * @param con conexión BBDD
     * @param usuario email usuario
     * @param passwd contraseña usuario
     * @return <ul>
     *     <li>usuario en caso de ser posible el login dentro de la app</li>
     *     <li>null en caso de no ser posible</li>
     * </ul>
     */
    public Usuario logging(MySQLConnector con, String usuario, String passwd) {

        Usuario usuarioError = new Usuario();

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        if (validarUsuario(con, usuario, passwd)) {
            String sql = "SELECT * FROM Usuario WHERE correo =? AND password=?";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, usuario);
                stmt.setString(2, passwd);
                ResultSet result = stmt.executeQuery();
                if (result.next()) {
                    System.out.println(
                            "Inicio de usuario realizado: " + result.getInt("id") + " " + result.getString("correo"));
                    Usuario usuarioLog = new Usuario(result);
                    return usuarioLog;
                } else {
                    usuarioError.setMessage("Usuario no encontrado");
                    System.out.println("Usuario no encontrado");
                    return usuarioError;
                }
            } catch (SQLException e) {
                e.getStackTrace();
            }
        } else {
            usuarioError.setMessage("Usuario no valido");
            System.out.println("Usuario no valido");
            return usuarioError;
        }
        usuarioError.setMessage("Usuario no introducido");
        System.out.println("Usuario no introducido");
        return usuarioError;
    }

    /**
     * Borrar un usuario
     * @param con Conexión BBDD
     * @param usuario email de usuario
     * @return <ul>
     *     <li>true si el usuario ha sido borrado correctamente</li>
     *     <li>false si el usuario no ha sido borrado</li>
     * </ul>
     */
    public boolean deleteUsuario(MySQLConnector con, String usuario) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "DELETE FROM Usuario WHERE correo=?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            ResultSet result = stmt.executeQuery();
            return result.next() ? true : false;
        } catch (SQLException esql) {
            System.out.println(esql.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Obtener listado de todos los usuarios registrados
     * @param con Conexión BBDD
     * @return listado de todos los usuarios
     */
    public List<Usuario> listarUsuarios(MySQLConnector con) {
        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM Usuario";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet result = stmt.executeQuery();
            result.beforeFirst();

            ArrayList<Usuario> usuarios = new ArrayList<>();
            while (result.next()) {
                Usuario user = new Usuario(result);
                usuarios.add(user);
            }
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

