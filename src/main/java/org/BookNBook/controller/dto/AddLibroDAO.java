package org.BookNBook.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.dao.Libro;

import java.io.Serializable;

/**
 * @author m.escribano.verde
 * @author l.cabrera.mora
 * @date 2023/06/15
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AddLibroDAO extends ErrorMessage implements Serializable {

    /**
     *
     */
    private Libro libro;
    /**
     *
     */
    private Autor autor;

}
