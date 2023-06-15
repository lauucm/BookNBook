package org.BookNBook.service;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Usuario;

import java.util.List;

/**
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
public interface UsuarioService {

    /**
     *
     * @param con
     * @param usuario
     * @return
     */
    boolean newUsuario (MySQLConnector con, Usuario usuario);

    /**
     *
     * @param con
     * @param usuario
     * @param passwd
     * @return
     */
    Usuario logging(MySQLConnector con, String usuario, String passwd);

    /**
     *
     * @param con
     * @return
     */
    List<Usuario> listarUsuarios(MySQLConnector con);

    /**
     *
     * @param con
     * @param usuario
     * @return
     */
    boolean deleteUsuario(MySQLConnector con, Integer usuario);

    /**
     *
     * @param con
     * @param correo
     * @return
     */
    boolean existEmail(MySQLConnector con, String correo);

    /**
     *
     * @param con
     * @param id
     * @return
     */
    Usuario getUsuario(MySQLConnector con, Integer id);

}
