package org.BookNBook.service.impl;

import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Libro;
import org.BookNBook.persistence.dao.Saga;
import org.BookNBook.persistence.manager.ManagerSaga;
import org.BookNBook.service.SagaService;

import java.util.List;

@AllArgsConstructor
public class SagaServiceImpl implements SagaService{

    ManagerSaga managerSaga;

    @Override
    public boolean addSaga(MySQLConnector con, Saga saga) {
        return managerSaga.addSaga(con, saga);
    }

    @Override
    public boolean addLibroSaga(MySQLConnector con, Integer idLibro, Integer idSaga){
        return managerSaga.addLibroSaga(con, idLibro, idSaga);
    }

    @Override
    public List<Libro> ListarLibrosSaga (MySQLConnector con, Integer id){
        return managerSaga.listarLibrosSaga(con, id);
    }
}
