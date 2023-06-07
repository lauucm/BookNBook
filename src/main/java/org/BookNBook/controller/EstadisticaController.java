package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Estadistica;
import org.BookNBook.service.EstadisticaService;

import java.time.LocalDate;

@AllArgsConstructor
@Path("/estadistica")
public class EstadisticaController {

    EstadisticaService estadisticaService;

    @GET
    @Path("/{idUsuario}/{idLibro}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getEstadistica(@PathParam(value="idLibro") Integer idLibro, @PathParam(value="idUsuario") Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        Estadistica estadistica = estadisticaService.getEstadistica(con, idLibro, idUsuario);
        return (estadistica != null) ?  Response.ok().entity(estadistica).build() :
                Response.noContent().entity("Usuario " + idUsuario + " o libro " + idLibro + " no encontrado" ).build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEstadistica(Integer idLibro, Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        return (estadisticaService.addEstadistica(con, idLibro, idUsuario)) ?
                Response.ok().entity("Estadistica añadida correctamente").build() :
                Response.notModified().entity("La estadistica no se ha añadido").build();
    }

    @POST
    @Path("/{idUsuario}/{idLibro}/updatecalificacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCalificacion(@PathParam(value="idLibro") Integer idLibro, @PathParam(value="idUsuario") Integer idUsuario, Double calificacion) {
        MySQLConnector con = new MySQLConnector();
        return (estadisticaService.updateCalificacion(con, idLibro, idUsuario, calificacion)) ?
                Response.ok().entity("Calificacion actualizadas").build() :
                Response.notModified().entity("Calificacion no actualizadas").build();
    }

    @POST
    @Path("/{idUsuario}/{idLibro}/updatefechaInicio")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFechaInicio(@PathParam(value="idLibro") Integer idLibro, @PathParam(value="idUsuario") Integer idUsuario, LocalDate fecha) {
        MySQLConnector con = new MySQLConnector();
        return (estadisticaService.updateFechaInicio(con, idLibro, idUsuario, fecha)) ?
                Response.ok().entity("Fecha actualizadas").build() :
                Response.notModified().entity("Fecha no actualizadas").build();
    }

    @POST
    @Path("/{idUsuario}/{idLibro}/updatefechaFinal")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFechaFinal(@PathParam(value="idLibro") Integer idLibro, @PathParam(value="idUsuario") Integer idUsuario, LocalDate fecha) {
        MySQLConnector con = new MySQLConnector();
        return (estadisticaService.updateFechaFinal(con, idLibro, idUsuario, fecha)) ?
                Response.ok().entity("Fecha actualizadas").build() :
                Response.notModified().entity("Fecha no actualizadas").build();
    }

    @POST
    @Path("/{idLibro}/calificacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response calificacionMedia(@PathParam(value="idLibro") Integer idLibro) {
        MySQLConnector con = new MySQLConnector();
        Double calificacionavg = estadisticaService.calificacionMedia(con, idLibro);
        return (calificacionavg != null) ?
                Response.ok().entity(calificacionavg).build() :
                Response.notModified().entity("Calificacion no encontrada").build();
    }

}
