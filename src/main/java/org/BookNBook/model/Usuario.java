package org.BookNBook.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Usuario {
    private Integer id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String correo;
    private String contraseña;
    private TipoUsuario tipoUsuario;
}
