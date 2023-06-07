package org.BookNBook.service.impl;

import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Usuario;
import org.BookNBook.persistence.manager.ManagerUsuario;
import org.BookNBook.service.UsuarioService;

@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    ManagerUsuario managerUsuario;

    @Override
    public boolean newUsuario(MySQLConnector con, Usuario usuario){
        return managerUsuario.newUsuario(con, usuario);
    }

    @Override
    public Usuario logging(MySQLConnector con, String usuario, String passwd){
        return managerUsuario.logging(con, usuario, passwd);
    }

}
