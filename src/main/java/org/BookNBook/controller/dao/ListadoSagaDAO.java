package org.BookNBook.controller.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.BookNBook.persistence.dao.Saga;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ListadoSagaDAO extends ErrorMessage implements Serializable {

    private List<Saga> listado;

}
