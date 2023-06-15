package org.BookNBook.controller.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.BookNBook.persistence.dao.Autor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ListadoAutorDAO extends ErrorMessage implements Serializable {

    private ArrayList<Autor> listado;

}
