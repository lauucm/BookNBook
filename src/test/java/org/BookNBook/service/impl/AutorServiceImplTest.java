package org.BookNBook.service.impl;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.dao.Usuario;
import org.BookNBook.persistence.manager.ManagerAutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AutorServiceImplTest {

    @InjectMocks
    private AutorServiceImpl autorService;

    @Mock
    private ManagerAutor managerAutor;

    private MySQLConnector con;

    @BeforeEach
    public void init(){
        con = mock(MySQLConnector.class);
    }

    @Test
    public void addAutor(){
        when(managerAutor.addAutor(any(), any())).thenReturn(true);
        assertTrue(autorService.addAutor(con, new Autor()));
    }
    
}
