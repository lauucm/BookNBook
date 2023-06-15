package org.BookNBook.service.impl;

import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.dao.Libro;
import org.BookNBook.persistence.manager.ManagerAutor;
import org.BookNBook.service.AutorService;

import java.util.List;

/**
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@AllArgsConstructor
public class AutorServiceImpl implements AutorService {

    /**
     *
     */
    ManagerAutor managerAutor;

    /**
     *
     * @param con
     * @param autor
     * @return
     */
    @Override
    public boolean addAutor(MySQLConnector con, Autor autor) {
        return managerAutor.addAutor(con, autor);
    }

    /**
     *
     * @param con
     * @param pseudonimo
     * @return
     */
    @Override
    public Autor buscarAutor(MySQLConnector con, String pseudonimo) {
        return managerAutor.buscarAutor(con, pseudonimo);
    }

    /**
     *
     * @param con
     * @param id
     * @return
     */
    @Override
    public List<Libro> listarLibrosAutor(MySQLConnector con, Integer id) {
        return managerAutor.listarLibrosAutor(con, id);
    }

    /**
     *
     * @param con
     * @return
     */
    @Override
    public List<Autor> listarAutores(MySQLConnector con) {
        return managerAutor.listarAutores(con);
    }

}