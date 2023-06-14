package org.BookNBook.controller.dao;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class NoDataResponse extends ErrorMessage implements Serializable{

    private Boolean ok;

}
