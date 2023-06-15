package org.BookNBook.service.impl;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Usuario;
import org.BookNBook.persistence.manager.ManagerUsuario;
import org.BookNBook.service.UsuarioService;
import org.BookNBook.utilidades.mail.Sender;

import java.io.IOException;
import java.util.List;

/**
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
public class UsuarioServiceImpl implements UsuarioService {

    /**
     *
     */
    private ManagerUsuario managerUsuario;

    /**
     *
     * @param managerUsuario
     */
    public UsuarioServiceImpl(ManagerUsuario managerUsuario){
        this.managerUsuario = managerUsuario;
    }

    /**
     *
     * @param con
     * @param usuario
     * @return
     */
    @Override
    public boolean newUsuario(MySQLConnector con, Usuario usuario){
        Boolean usuariocreado = managerUsuario.newUsuario(con, usuario);
        if (usuariocreado) {
            try {
                new Sender().send("booknbook2023@gmail.com", usuario.getEmail(), "Bienvenido a BookNBook!",
                        "Bienvenido a Book N Book!<br/>Usuario: " + usuario.getEmail() + "<br/>Contraseña: " + usuario.getPassword());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return usuariocreado;
    }

    /**
     *
     * @param con
     * @param usuario
     * @param passwd
     * @return
     */
    @Override
    public Usuario logging(MySQLConnector con, String usuario, String passwd){
        return managerUsuario.logging(con, usuario, passwd);
    }

    /**
     *
     * @param con
     * @return
     */
    @Override
    public List<Usuario> listarUsuarios(MySQLConnector con) {
        return managerUsuario.listarUsuarios(con);
    }

    /**
     *
     * @param con
     * @param usuario
     * @return
     */
    @Override
    public boolean deleteUsuario(MySQLConnector con, Integer usuario) {
        return managerUsuario.deleteUsuario(con, usuario);
    }

    /**
     *
     * @param con
     * @param correo
     * @return
     */
    @Override
    public boolean existEmail(MySQLConnector con, String correo) {
        return managerUsuario.existEmail(con, correo);
    }

    /**
     *
     * @param con
     * @param id
     * @return
     */
    @Override
    public Usuario getUsuario(MySQLConnector con, Integer id) {
        return managerUsuario.getUsuario(con, id);
    }
}
