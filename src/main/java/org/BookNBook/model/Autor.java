package org.BookNBook.model;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Builder
public class Autor {

    private Integer id;
    private String pseudonimo;
    private String localidad;

    public Autor(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }

    public Autor(String pseudonimo, String localidad) {
        this.pseudonimo = pseudonimo;
        this.localidad = localidad;
    }

    public Autor(ResultSet result) throws SQLException {
        try {
            this.id = result.getInt("id");
            this.pseudonimo = result.getString("pseudonimo");
            this.localidad = result.getString(("localidad"));
        } catch (SQLException e) {
            e.getStackTrace();
            throw e;
        }
    }
}
