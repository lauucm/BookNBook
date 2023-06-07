package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Saga;
import org.BookNBook.service.SagaService;

@AllArgsConstructor
@Path("/saga")
public class SagaController {

    private SagaService sagaService;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSaga(Saga saga) {
        MySQLConnector con = new MySQLConnector();
        return (sagaService.addSaga(con, saga)) ?
                Response.ok().entity("Saga añadida correctamente").build() :
                Response.notModified().entity("La Saga no se ha añadido").build();
    }

    @POST
    @Path("/{idSaga}/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLibroSaga(Integer idLibro, @PathParam(value="idSaga") Integer idSaga) {
        MySQLConnector con = new MySQLConnector();
        return (sagaService.addLibroSaga(con, idLibro, idSaga)) ?
                Response.ok().entity("Libro añadido a la saga correctamente").build() :
                Response.notModified().entity("El libro no se ha añadido a la saga").build();
    }

    @GET
    @Path("/{idSaga}/libros")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ListarLibrosSaga(@PathParam(value="idSaga") Integer idSaga) {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(sagaService.ListarLibrosSaga(con, idSaga)).build();
    }
}
