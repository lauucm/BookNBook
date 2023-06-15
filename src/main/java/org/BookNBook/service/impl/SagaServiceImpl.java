package org.BookNBook.service.impl;

import lombok.AllArgsConstructor;
import org.BookNBook.controller.dto.SagaDAO;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Libro;
import org.BookNBook.persistence.dao.Saga;
import org.BookNBook.persistence.manager.ManagerSaga;
import org.BookNBook.service.SagaService;

import java.util.List;

/**
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@AllArgsConstructor
public class SagaServiceImpl implements SagaService{

    /**
     *
     */
    ManagerSaga managerSaga;

    /**
     *
     * @param con
     * @param saga
     * @return
     */
    @Override
    public boolean addSaga(MySQLConnector con, Saga saga) {
        return managerSaga.addSaga(con, saga);
    }

    /**
     *
     * @param con
     * @param saga
     * @return
     */
    @Override
    public boolean addLibroSaga(MySQLConnector con, SagaDAO saga){
        return managerSaga.addLibroSaga(con, saga);
    }

    /**
     *
     * @param con
     * @param id
     * @return
     */
    @Override
    public List<Libro> ListarLibrosSaga (MySQLConnector con, Integer id){
        return managerSaga.listarLibrosSaga(con, id);
    }

    /**
     *
     * @param con
     * @return
     */
    @Override
    public List<Saga> listarSaga(MySQLConnector con) {
        return managerSaga.listarSaga(con);
    }
}
