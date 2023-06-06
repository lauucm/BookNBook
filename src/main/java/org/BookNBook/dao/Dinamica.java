package org.BookNBook.dao;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dinamica {
    private Libro libro;
    private Usuario usuario;
    private Integer pagActual;
    private Integer dias;
}
