package org.BookNBook.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Autor {

    private Integer id;
    private String pseudonimo;
    private String localidad;
}
