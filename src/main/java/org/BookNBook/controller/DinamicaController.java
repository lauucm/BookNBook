package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.BookNBook.controller.dao.NoDataResponse;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Dinamica;
import org.BookNBook.service.DinamicaService;

/**
 * Controlador para manejar las acciones realizadas con las dinámicas
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@AllArgsConstructor
@Path("/dinamica")
public class DinamicaController {

    /**
     * Clase servicio
     */
    private DinamicaService dinamicaService;

    /**
     * Obtiene los datos acorde a un libro y un usuario
     * @param idLibro Identificador de libro
     * @param idUsuario Identificador de usuario
     * @return Response con la dinamica específica o con la negativa de no haber encontrado los datos en la úsqueda realziada
     */
    @GET
    @Path("/{idUsuario}/{idLibro}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getDinamica(@PathParam(value="idLibro") Integer idLibro, @PathParam(value="idUsuario") Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        Dinamica dinamica = dinamicaService.getDinamica(con, idLibro, idUsuario);
        return (dinamica != null) ?  Response.ok().entity(dinamica).build() :
                Response.noContent().entity("Usuario " + idUsuario + " o libro " + idLibro + " no encontrado" ).build();
    }


    /**
     * Añadir una dinámica
     * @param idLibro Identificador de libro
     * @param idUsuario Identificador de usuario
     * @return Response con una afirmativa o negativa acorde al añadido de los datos de dinámica
     */
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDinamica(@QueryParam("idLibro") Integer idLibro, @QueryParam("idUsuario") Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        return (dinamicaService.addDinamica(con, idLibro, idUsuario)) ?
                Response.ok().entity("Dinamica añadida correctamente").build() :
                Response.notModified().entity("La dinamica no se ha añadido").build();
    }

     /**
     * Actualizar el número de páginas de lecturas actual de un usuario con un libro determinado
     * @param idLibro Identificador de libro
     * @param idUsuario Identificador de usuario
     * @param paginas Número de páginas a actualizar
     * @return Response con una afirmativa o negativa acorde al actualizado del número de páginas
     */
    @POST
    @Path("/update/paginas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePag(@QueryParam("idLibro") Integer idLibro, @QueryParam("idUsuario") Integer idUsuario, @QueryParam("paginas") Integer paginas) {
        MySQLConnector con = new MySQLConnector();
        Boolean exist = dinamicaService.updatePag(con, idLibro, idUsuario, paginas);
        return exist ?
                Response.ok().entity(NoDataResponse.builder().ok(true).message("Paginas actualizadas").build()).build() :
                Response.ok().entity(NoDataResponse.builder().ok(false).message("Paginas no actualizadas").build()).build();
    }

    /**
     * Sumatorio de las páginas totales leídas por el usuario
     * @param idUsuario Identificador de usuario
     * @return Response con una lista de las páginas totales leídas por el usuario
     */
    @GET
    @Path("/{idUsuario}/paginas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginasLeidas(@PathParam(value="idUsuario") Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(dinamicaService.paginasLeidas(con, idUsuario)).build();
    }

}
