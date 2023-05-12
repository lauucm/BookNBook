package org.BookNBook.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Autor {

    private Integer id;
    private String pseudonimo;
    private String localidad;

    public Autor(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }
}
