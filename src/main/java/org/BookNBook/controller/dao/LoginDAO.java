package org.BookNBook.controller.dao;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * LoginDAO para gestión de usuarios
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LoginDAO implements Serializable {

    /**
     * Email de usuario
     */
    String usuario;
    /**
     * Contraseña de usuario
     */
    String password;

}
