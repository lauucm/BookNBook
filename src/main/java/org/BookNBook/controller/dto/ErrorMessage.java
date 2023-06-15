package org.BookNBook.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author m.escribano.verde
 * @author l.cabrera.mora
 * @date 2023/06/15
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ErrorMessage implements Serializable {

    /**
     *
     */
    private String message;

}
