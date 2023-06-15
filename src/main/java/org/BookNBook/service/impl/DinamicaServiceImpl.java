package org.BookNBook.service.impl;

import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Dinamica;
import org.BookNBook.persistence.manager.ManagerDinamica;
import org.BookNBook.service.DinamicaService;

@AllArgsConstructor
public class DinamicaServiceImpl implements DinamicaService {

    ManagerDinamica managerDinamica;

    @Override
    public Dinamica getDinamica(MySQLConnector con, Integer idLibro, Integer idUsuario) {
        return managerDinamica.getDinamica(con, idLibro, idUsuario);
    }

    @Override
    public boolean updatePag(MySQLConnector con, Integer idLibro, Integer idUsuario, Integer paginas) {
        return managerDinamica.updatePag(con, idLibro, idUsuario, paginas);
    }

    @Override
    public boolean addDinamica(MySQLConnector con, Integer idLibro, Integer idUsuario) {
        return managerDinamica.addDinamica(con, idLibro, idUsuario);
    }

    @Override
    public Integer paginasLeidas(MySQLConnector con, Integer idUsuario) {
        return managerDinamica.paginasLeidas(con, idUsuario);
    }
}
