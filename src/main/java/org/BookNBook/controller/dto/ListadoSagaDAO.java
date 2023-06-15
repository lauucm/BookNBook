package org.BookNBook.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.BookNBook.persistence.dao.Saga;

import java.io.Serializable;
import java.util.List;

/**
 * @author m.escribano.verde
 * @author l.cabrera.mora
 * @date 2023/06/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ListadoSagaDAO extends ErrorMessage implements Serializable {

    /**
     *
     */
    private List<Saga> listado;

}
