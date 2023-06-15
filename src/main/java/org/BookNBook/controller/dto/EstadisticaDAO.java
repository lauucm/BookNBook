package org.BookNBook.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class EstadisticaDAO extends ErrorMessage implements Serializable{

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
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        private String fecha;

        /**
         * @param idLibro
         * @param IdUsuario
         */
        public EstadisticaDAO (Integer idLibro, Integer IdUsuario){
                super();
                this.idUsuario = IdUsuario;
                this.idLibro = idLibro;
        }

}
