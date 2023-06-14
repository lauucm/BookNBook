package org.BookNBook.service;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Usuario;

import java.util.List;

public interface UsuarioService {

    boolean newUsuario (MySQLConnector con, Usuario usuario);

    Usuario logging(MySQLConnector con, String usuario, String passwd);

    List<Usuario> listarUsuarios(MySQLConnector con);

    boolean deleteUsuario(MySQLConnector con, Integer usuario);

    boolean existEmail(MySQLConnector con, String correo);

    Usuario getUsuario(MySQLConnector con, Integer id);

}
