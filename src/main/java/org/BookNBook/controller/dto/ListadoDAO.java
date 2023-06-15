package org.BookNBook.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.BookNBook.persistence.dao.Libro;

import java.io.Serializable;
import java.util.List;

/**
 * @author m.escribano.verde
 * @author l.cabrera.mora
 * @date 2023/06/15
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ListadoDAO extends ErrorMessage implements Serializable {

    /**
     *
     */
    private List<Libro> listado;

}
