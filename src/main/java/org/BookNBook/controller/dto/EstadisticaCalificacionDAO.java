package org.BookNBook.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author m.escribano.verde
 * @author l.cabrera.mora
 * @date 2023/06/15
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EstadisticaCalificacionDAO extends ErrorMessage implements Serializable {

    /**
     *
     */
    private Integer idLibro;

    /**
     *
     */
    private Integer idUsuario;

    /**
     * 
     */
    private Double calificacion;

}
