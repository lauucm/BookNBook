package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Estadistica;
import org.BookNBook.service.EstadisticaService;

import java.time.LocalDate;

/**
 * Controlador para manejar las acciones realizadas con las estadísticas
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@AllArgsConstructor
@Path("/estadistica")
public class EstadisticaController {

    /**
     * Clase servicio
     */
    EstadisticaService estadisticaService;

    /**
     * Obtener datos d eestadística específicos para un usuario con un libro
     * @param idLibro Identificador de libro
     * @param idUsuario Identificador de usuario
     * @return Response con los datos de estadísticas o con una negativa de la búsqueda realizada
     */
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

//    /**
//     * Añadir una estadística
//     * @param idLibro Identificador de libro
//     * @param idUsuario Identificador de usuario
//     * @return Response con una afirmativa o negativa en base al añadido de los datos de estadísticas
//     */
//    @POST
//    @Path("/add")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response addEstadistica(Integer idLibro, Integer idUsuario) {
//        MySQLConnector con = new MySQLConnector();
//        return (estadisticaService.addEstadistica(con, idLibro, idUsuario)) ?
//                Response.ok().entity("Estadistica añadida correctamente").build() :
//                Response.notModified().entity("La estadistica no se ha añadido").build();
//    }

    /**
     * Actualizar la calificación personal del usuario con un libro
     * @param idLibro Identificador de libro
     * @param idUsuario Identificador de usuario
     * @param calificacion Calificación personal del usuario
     * @return Response con una afirmativa o negativa sobre la actualización
     */
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

    /**
     * Actualización de la fecha de inicio de lectura
     * @param idLibro Identificador de libro
     * @param idUsuario Identificador de usuario
     * @param fecha Fecha de inicio de lectura
     * @return Response con la afirmativa o negativa de la actualización
     */
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

    /**
     * Actualización de la fecha de fin de lectura
     * @param idLibro Identificador de libro
     * @param idUsuario Identificador de usuario
     * @param fecha Fecha de fin de lectura
     * @return Response con la afirmativa o negativa de la actualización
     */
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

    /**
     * Calificación media dada por los usuarios a un libro
     * @param idLibro Identificador de libro
     * @return Response con la calificación media alcanzada o con la negativa sobre la búsqueda realizada
     */
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
