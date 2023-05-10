package org.BookNBook.model;


import java.time.LocalDate;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

public class Estadistica {
    private Libro libro;
    private Usuario usuario;
    private Double calificacionActual;
    private Integer pagActual;
    private LocalDate fechaFinal;
    private LocalDate fechaAdd;
    private LocalDate fechaInicio;
}
