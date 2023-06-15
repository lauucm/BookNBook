package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.BookNBook.controller.dto.ListadoDAO;
import org.BookNBook.controller.dto.ListadoSagaDAO;
import org.BookNBook.controller.dto.NoDataResponse;
import org.BookNBook.controller.dto.SagaDAO;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Saga;
import org.BookNBook.persistence.manager.ManagerSaga;
import org.BookNBook.service.SagaService;
import org.BookNBook.service.impl.SagaServiceImpl;

/**
 * Controlador para manejar las acciones realizadas con las sagas
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@Path("/saga")
public class SagaController {

    /**
     * Clase servicio
     */
    private SagaService sagaService;

    public SagaController(){
        sagaService = new SagaServiceImpl(new ManagerSaga());
    }

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
        Boolean exist = sagaService.addSaga(con, saga);
        return exist ?
                Response.ok().entity(NoDataResponse.builder().ok(true).message("Saga añadida correctamente").build()).build() :
                Response.ok().entity(NoDataResponse.builder().ok(false).message("Saga no se ha añadido").build()).build();
    }

    /**
     * Añadir un libro a una saga
     * @param saga Identificador de libro + Identificador de Saga
     * @return Response con un texto afirmativo o negativo según se añade o no un libro a una saga
     */
    @POST
    @Path("/add/libro")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLibroSaga(SagaDAO saga) {
        MySQLConnector con = new MySQLConnector();
        Boolean exist = sagaService.addLibroSaga(con, saga);
        return exist ?
                Response.ok().entity(NoDataResponse.builder().ok(true).message("Libro añadido correctamente").build()).build() :
                Response.ok().entity(NoDataResponse.builder().ok(false).message("Libro no se ha añadido").build()).build();
    }

    /**
     * Obtiene los libros de una saga determinada
     * @param idSaga Identificador de Saga
     * @return Response con una lista de los libros de una saga
     */
    @GET
    @Path("/{idSaga}/libros")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response ListarLibrosSaga(@PathParam(value="idSaga") Integer idSaga) {
        MySQLConnector con = new MySQLConnector();
        ListadoDAO listado = new ListadoDAO();
        listado.setListado(sagaService.ListarLibrosSaga(con, idSaga));
        listado.setMessage("Listado leido correctamente");
        return listado != null ?
                Response.ok().entity(listado).build() :
                Response.status(400).entity(ListadoDAO.builder().message("Error al leer listado")).build() ;
    }

    /**
     * Obtiene los libros de una saga determinada
     * @return Response con una lista de los libros de una saga
     */
    @GET
    @Path("/sagas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response ListarSaga() {
        MySQLConnector con = new MySQLConnector();
        ListadoSagaDAO listado = new ListadoSagaDAO();
        listado.setListado(sagaService.listarSaga(con));
        listado.setMessage("Listado leido correctamente");
        return listado != null ?
                Response.ok().entity(listado).build() :
                Response.status(400).entity(ListadoSagaDAO.builder().message("Error al leer listado")).build() ;
    }
}
