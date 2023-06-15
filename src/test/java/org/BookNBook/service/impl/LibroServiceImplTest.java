package org.BookNBook.service.impl;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.manager.ManagerLibro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class LibroServiceImplTest {

    @InjectMocks
    private LibroServiceImpl libroService;

    @Mock
    private ManagerLibro libroManager;

    private MySQLConnector con;

    @BeforeEach
    public void init(){
        con = mock(MySQLConnector.class);
    }

    
}
