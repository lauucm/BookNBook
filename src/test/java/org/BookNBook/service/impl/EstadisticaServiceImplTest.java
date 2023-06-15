package org.BookNBook.service.impl;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.manager.ManagerEstadistica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class EstadisticaServiceImplTest {

    @InjectMocks
    private EstadisticaServiceImpl estadisticaService;

    @Mock
    private ManagerEstadistica managerEstadistica;

    private MySQLConnector con;

    @BeforeEach
    public void init(){
        con = mock(MySQLConnector.class);
    }
    
}
