package org.BookNBook.service.impl;

import org.BookNBook.controller.dto.SagaDAO;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Saga;
import org.BookNBook.persistence.manager.ManagerSaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SagaServiceImplTest {

    @InjectMocks
    private SagaServiceImpl sagaService;

    @Mock
    private ManagerSaga sagaManager;

    private MySQLConnector con;

    @BeforeEach
    public void init(){
        con = mock(MySQLConnector.class);
    }

    @Test
    public void addSaga(){
        when(sagaManager.addSaga(any(), any())).thenReturn(true);
        assertTrue(sagaManager.addSaga(con, new Saga()));
    }

    @Test
    public void addLibroSaga(){
        when(sagaManager.addLibroSaga(any(), any())).thenReturn(true);
        assertTrue(sagaManager.addLibroSaga(con, new SagaDAO()));
    }
    
}
