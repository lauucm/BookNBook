package org.BookNBook.service.impl;

import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Usuario;
import org.BookNBook.persistence.manager.ManagerUsuario;
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
public class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private ManagerUsuario managerUsuario;

    private MySQLConnector con;

    @BeforeEach
    public void init(){
        con = mock(MySQLConnector.class);
    }

    /**
     *
     */
    @Test
    public void newUsuarioTest(){
        when(managerUsuario.newUsuario(any(), any())).thenReturn(true);
        assertTrue(usuarioService.newUsuario(con, new Usuario(1, "x", "x", "x", "x", "x")));
    }

    @Test
    public void deleteUsuario(){
        when(managerUsuario.deleteUsuario(any(), any())).thenReturn(true);
        assertTrue(usuarioService.deleteUsuario(con, new Usuario().getId()));
    }

    @Test
    public void existEmail(){
        when(managerUsuario.existEmail(any(), any())).thenReturn(true);
        assertTrue(usuarioService.existEmail(con, new Usuario().getEmail()));
    }

}
