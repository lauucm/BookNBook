package org.BookNBook.service;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Usuario;

public interface UsuarioService {

    boolean newUsuario (MySQLConnector con, Usuario usuario);

    Usuario logging(MySQLConnector con, String usuario, String passwd);

}
