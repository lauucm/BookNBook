package org.BookNBook.controller.dao;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.BookNBook.persistence.dao.Libro;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ListadoDAO extends ErrorMessage implements Serializable {

    private ArrayList<Libro> listado;

}
