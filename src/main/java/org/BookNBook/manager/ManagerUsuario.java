package org.BookNBook.manager;

import org.BookNBook.conector.MySQLConnector;
import org.BookNBook.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class ManagerUsuario {

    /**
     * Conocer si existe un usuario en la base de datos a partir de su email
     * @param con
     * @param email
     * @return <ul>
     *     <li>true si existe un usuario con el email</li>ç
     *     <li>false si no existe un usuario con el email</li>
     * </ul>
     *
     */
    public boolean existeUsuario(MySQLConnector con, String email) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT count(id) as num FROM Usuario WHERE email = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, email);

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
     * Ingresar un nuevo usuario dentro de la base datos
     * @param con
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param email
     * @param password
     * @return <ul>
     *      <li>true se ha podido crear un usuario</li>ç
     *      <li>false si no se ha podido crear un usuario</li>
     * </ul>
     */
    // tipo usuario ?? id ??
    public boolean newUsuario (MySQLConnector con, String nombre, String apellido1, String apellido2, String email, String password) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (!existeUsuario(con, email) && !email.equals("")) {
            String sql = "INSERT INTO Usuario (`nombre`,`apellido1`,`apellido2`,`email`,`password`) VALUES(?,?,?,?,?)";

            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.setString(2, apellido1);
                stmt.setString(3, apellido2);
                stmt.setString(4, email);
                stmt.setString(5, password);
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
     * @param con
     * @param usuario
     * @param passwd
     * @return <ul>
     *     <li>true si el correo y la contraseña son validos</li>
     *     <li>false si el correo o contraseña no son validos</li>
     * </ul>
     */
    public boolean validarUsuario(MySQLConnector con, String usuario, String passwd) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM Usuario WHERE email=? AND password=?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, passwd);
            ResultSet result = stmt.executeQuery();
            if(result.next()) {
                String user = result.getString("email");
                String pass = result.getString("password");
                if (user.equals(usuario) && pass.equals(passwd)) {
                    return true;
                } else {
                    return false;
                }
            }else {
                return false;
            }
        } catch (SQLException esql) {
            System.out.println(esql.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Comprobación del usuario que ha iniciado sesion
     * @param con
     * @param usuario
     * @param passwd
     * @return <ul>
     *     <li>usuario en caso de ser posible el login dentro de la app</li>
     *     <li>null en caso de no ser posible</li>
     * </ul>
     */
    public Usuario logging(MySQLConnector con, String usuario, String passwd) {

        Connection conexion = null;
        try {
            conexion = con.getMySQLConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (validarUsuario(con, usuario, passwd)) {
            String sql = "SELECT * FROM Usuario WHERE email =? AND password=?";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, usuario);
                stmt.setString(2, passwd);
                ResultSet result = stmt.executeQuery();
                if (result.next()) {
                    System.out.println(
                            "Inicio de usuario realizado: " + result.getInt("id") + " " + result.getString("email"));
                    return new Usuario(result);
                } else {
                    System.out.println("Usuario no encontrado");
                    return null;
                }
            } catch (SQLException e) {
                e.getStackTrace();
            }
        } else {
            System.out.println("Usuario no valido");
            return null;
        }
        System.out.println("Usuario no introducido");
        return null;
    }

}
