package org.BookNBook.model;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Dinamica {
    private Libro libro;
    private Usuario usuario;
    private Integer pagActual;
    private Integer dias;
}
