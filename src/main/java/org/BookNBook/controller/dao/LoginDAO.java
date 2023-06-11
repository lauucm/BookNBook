package org.BookNBook.controller.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LoginDAO para gestión de usuarios
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDAO {

    /**
     * Email de usuario
     */
    String usuario;
    /**
     * Contraseña de usuario
     */
    String password;
}
