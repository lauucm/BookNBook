package org.BookNBook.utilidades.mail;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.BookNBook.utilidades.mail.excepciones.PasswordException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Data
@NoArgsConstructor
public class Password {
    private String password;

    public Password(String password) {
        this.password = password;
    }

    public Password(String password, String email) throws PasswordException {
        try {
            checkPassword(password, email);
            this.password = password;
        } catch (PasswordException e) {
            throw new PasswordException(e.getMessage());
        }
    }

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
