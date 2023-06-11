package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Saga;
import org.BookNBook.service.SagaService;

/**
 * Controlador para manejar las acciones realizadas con las sagas
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@AllArgsConstructor
@Path("/saga")
public class SagaController {

    /**
     * Clase servicio
     */
    private SagaService sagaService;

    /**
     * Añadir una saga
     * @param saga saga de libros
     * @return Response con un texto afirmativo o negativo según se añade o no una saga
     */
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

    /**
     * Añadir un libro a una saga
     * @param idLibro Identificador de libro
     * @param idSaga Identificador de Saga
     * @return Response con un texto afirmativo o negativo según se añade o no un libro a una saga
     */
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

    /**
     * Obtiene los libros de una saga determinada
     * @param idSaga Identificador de Saga
     * @return Response con una lista de los libros de una saga
     */
    @GET
    @Path("/{idSaga}/libros")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ListarLibrosSaga(@PathParam(value="idSaga") Integer idSaga) {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(sagaService.ListarLibrosSaga(con, idSaga)).build();
    }
}
