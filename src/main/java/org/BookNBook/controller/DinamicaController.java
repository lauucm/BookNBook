package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Dinamica;
import org.BookNBook.service.DinamicaService;

@AllArgsConstructor
@Path("/dinamica")
public class DinamicaController {

    private DinamicaService dinamicaService;

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

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDinamica(Integer idLibro, Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        return (dinamicaService.addDinamica(con, idLibro, idUsuario)) ?
                Response.ok().entity("Dinamica añadida correctamente").build() :
                Response.notModified().entity("La dinamica no se ha añadido").build();
    }

    @POST
    @Path("/update/paginas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePag(Integer idLibro, Integer idUsuario, Integer paginas) {
        MySQLConnector con = new MySQLConnector();
        return (dinamicaService.updatePag(con, idLibro, idUsuario, paginas)) ?
                Response.ok().entity("Paginas actualizadas").build() :
                Response.notModified().entity("Paginas no actualizadas").build();
    }

    @GET
    @Path("/{idUsuario}/paginas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginasLeidas(@PathParam(value="idUsuario") Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(dinamicaService.paginasLeidas(con, idUsuario)).build();
    }

}
