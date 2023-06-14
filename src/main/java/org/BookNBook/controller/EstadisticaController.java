package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.BookNBook.controller.dao.EstadisticaDAO;
import org.BookNBook.controller.dao.NoDataResponse;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Estadistica;
import org.BookNBook.persistence.dao.Usuario;
import org.BookNBook.persistence.manager.ManagerEstadistica;
import org.BookNBook.service.EstadisticaService;
import org.BookNBook.service.impl.EstadisticaServiceImpl;

import java.time.LocalDate;

/**
 * Controlador para manejar las acciones realizadas con las estadísticas
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@Path("/estadistica")
public class EstadisticaController {

    /**
     * Clase servicio
     */
    EstadisticaService estadisticaService;

    public EstadisticaController(){
        estadisticaService = new EstadisticaServiceImpl(new ManagerEstadistica());
    }

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

    /**
     * Añadir una estadística
     * @param dato Objeto con identificador de libro y de usuario
     * @return Response con una afirmativa o negativa en base al añadido de los datos de estadísticas
     */
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEstadistica(EstadisticaDAO dato) {
        MySQLConnector con = new MySQLConnector();
        Boolean exist = estadisticaService.addEstadistica(con, dato.getIdLibro(), dato.getIdUsuario());
        return exist ?
                Response.ok().entity(NoDataResponse.builder().ok(true).message("Estadistica añadida").build()).build() :
                Response.status(400).entity(NoDataResponse.builder().ok(false).message("Error al añadir estadistica").build()).build();
    }

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
    @Consumes(MediaType.TEXT_PLAIN)
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
    @Consumes(MediaType.TEXT_PLAIN)
    public Response updateFechaInicio(@PathParam(value="idLibro") Integer idLibro, @PathParam(value="idUsuario") Integer idUsuario, String fecha) {
        MySQLConnector con = new MySQLConnector();
        Boolean exist = estadisticaService.updateFechaInicio(con, idLibro, idUsuario, fecha);
        return exist ?
                Response.ok().entity(NoDataResponse.builder().ok(true).message("Actualización realizada").build()).build() :
                Response.ok().entity(NoDataResponse.builder().ok(false).message("Fallo de actualización").build()).build();
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
    @Consumes(MediaType.TEXT_PLAIN)
    public Response updateFechaFinal(@PathParam(value="idLibro") Integer idLibro, @PathParam(value="idUsuario") Integer idUsuario, String fecha) {
        MySQLConnector con = new MySQLConnector();
        Boolean exist = estadisticaService.updateFechaFinal(con, idLibro, idUsuario, fecha);
        return exist ?
                Response.ok().entity(NoDataResponse.builder().ok(true).message("Actualización realizada").build()).build() :
                Response.ok().entity(NoDataResponse.builder().ok(false).message("Fallo de actualización").build()).build();
    }

    /**
     * Calificación media dada por los usuarios a un libro
     * @param idLibro Identificador de libro
     * @return Response con la calificación media alcanzada o con la negativa sobre la búsqueda realizada
     */
    @GET
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
