package org.BookNBook.utilidades;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.BookNBook.utilidades.excepciones.PasswordException;

/**
 * Clase Contraseña
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@Data
@NoArgsConstructor
public class Password{
    /**
     * Contraseña
     */
    private String password;

    /**
     * Constructor de la clase
     * @param password contraseña
     */
    public Password(String password) {
        this.password = password;
    }

    /**
     * Constructor de la clase
     * @param password contraseña
     * @param email correo
     */
    public Password(String password, String email) throws PasswordException {
        try {
            checkPassword(password, email);
            this.password = password;
        } catch (PasswordException e) {
            throw new PasswordException(e.getMessage());
        }
    }

    /**
     * Comprobar longitud y caracteres que conforman la contraseña
     * @param password contraseña
     * @param email correo
     * @throws PasswordException error
     */
    public void checkPassword(String password, String email) throws PasswordException {
        if (password.length() < 5) {
            throw new PasswordException("Debe tener minimo 5 caracteres.");
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new PasswordException("Debe tener minimo una letra mayúscula.");
        }

        if (!password.matches(".*[a-z].*")) {
            throw new PasswordException("Debe tener minimo una letra minúscula.");
        }

        if (!password.matches(".*\\d.*")) {
            throw new PasswordException("Debe tener minimo un número.");
        }
    }

    @Override
    public String toString() {
        return password;
    }

}
