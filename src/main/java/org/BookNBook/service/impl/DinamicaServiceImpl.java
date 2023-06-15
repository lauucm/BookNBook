package org.BookNBook.service.impl;

import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Dinamica;
import org.BookNBook.persistence.manager.ManagerDinamica;
import org.BookNBook.service.DinamicaService;

/**
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@AllArgsConstructor
public class DinamicaServiceImpl implements DinamicaService {

    /**
     *
     */
    ManagerDinamica managerDinamica;

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @return
     */
    @Override
    public Dinamica getDinamica(MySQLConnector con, Integer idLibro, Integer idUsuario) {
        return managerDinamica.getDinamica(con, idLibro, idUsuario);
    }

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @param paginas
     * @return
     */
    @Override
    public boolean updatePag(MySQLConnector con, Integer idLibro, Integer idUsuario, Integer paginas) {
        return managerDinamica.updatePag(con, idLibro, idUsuario, paginas);
    }

    /**
     *
     * @param con
     * @param idLibro
     * @param idUsuario
     * @return
     */
    @Override
    public boolean addDinamica(MySQLConnector con, Integer idLibro, Integer idUsuario) {
        return managerDinamica.addDinamica(con, idLibro, idUsuario);
    }

    /**
     * 
     * @param con
     * @param idUsuario
     * @return
     */
    @Override
    public Integer paginasLeidas(MySQLConnector con, Integer idUsuario) {
        return managerDinamica.paginasLeidas(con, idUsuario);
    }
}
