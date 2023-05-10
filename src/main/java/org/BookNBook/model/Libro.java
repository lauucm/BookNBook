package org.BookNBook.model;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Libro {

    private Integer id;
    private String nombre;
    private Autor autor;
    private String descripcion;
    private LocalDate fechaPublicaicon;
    private Double calificacionMedia;
    private Integer paginaTotal;
    private TipologiaLibro tipologiaLibro;
    private TematicaLibro tematicaLibro;
}
