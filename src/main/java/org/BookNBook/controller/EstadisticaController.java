package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.BookNBook.controller.dto.*;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Estadistica;
import org.BookNBook.persistence.manager.ManagerEstadistica;
import org.BookNBook.service.EstadisticaService;
import org.BookNBook.service.impl.EstadisticaServiceImpl;

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
     * Obtener datos de estadística específicos para un usuario con un libro
     * @param dato Identificador de libro + Identificador de usuario
     * @return Response con los datos de estadísticas o con una negativa de la búsqueda realizada
     */
    @POST
    @Path("/obtener")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buscarEstadistica(EstadisticaDAO dato) {
        MySQLConnector con = new MySQLConnector();
        Estadistica estadistica = estadisticaService.getEstadistica(con, dato.getIdLibro(), dato.getIdUsuario());
        return (estadistica != null) ?
                Response.status(200).entity(estadistica).build() :
                Response.status(400).entity("Usuario o libro no encontrado").build();
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
    public Response addEstadistica(EstadisticaSinFechaDAO dato) {
        MySQLConnector con = new MySQLConnector();
        Boolean exist = estadisticaService.addEstadistica(con, dato.getIdLibro(), dato.getIdUsuario());
        return exist ?
                Response.ok().entity(NoDataResponse.builder().ok(true).message("Estadistica añadida").build()).build() :
                Response.status(400).entity(NoDataResponse.builder().ok(false).message("Error al añadir estadistica").build()).build();
    }

    /**
     * Actualizar la calificación personal del usuario con un libro
     * @param dato Identificador de libro + Identificador de usuario + Calificación personal del usuario
     * @return Response con una afirmativa o negativa sobre la actualización
     */
    @POST
    @Path("/updateCalificacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCalificacion(EstadisticaCalificacionDAO dato) {
        MySQLConnector con = new MySQLConnector();
        Boolean exist = estadisticaService.updateCalificacion(con, dato.getIdLibro(), dato.getIdUsuario(), dato.getCalificacion());
        return exist ?
                Response.ok().entity(NoDataResponse.builder().ok(true).message("Calificacion actualizadas").build()).build() :
                Response.status(400).entity(NoDataResponse.builder().ok(false).message("Calificacion no actualizadas").build()).build();
    }

    /**
     * Actualización de la fecha de inicio de lectura
     * @param dato Identificador de libro + Identificador de usuario + Fecha de inicio de lectura
     * @return Response con la afirmativa o negativa de la actualización
     */
    @POST
    @Path("/updatefechaInicio")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFechaInicio(EstadisticaDAO dato) {
        MySQLConnector con = new MySQLConnector();
        Boolean exist = estadisticaService.updateFechaInicio(con, dato.getIdUsuario(), dato.getIdLibro(), dato.getFecha());
        return exist ?
                Response.ok().entity(NoDataResponse.builder().ok(true).message("Actualización realizada").build()).build() :
                Response.ok().entity(NoDataResponse.builder().ok(false).message("Fallo de actualización").build()).build();
    }

    /**
     * Actualización de la fecha de fin de lectura
     * @param dato Identificador de libro + Identificador de usuario + Fecha de inicio de lectura
     * @return Response con la afirmativa o negativa de la actualización
     */
    @POST
    @Path("/updatefechaFinal")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFechaFinal(EstadisticaDAO dato) {
        MySQLConnector con = new MySQLConnector();
        Boolean exist = estadisticaService.updateFechaFinal(con, dato.getIdUsuario(), dato.getIdLibro(), dato.getFecha());
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
        CalificacionDAO calificacion = new CalificacionDAO(calificacionavg);
        return (calificacion != null) ?
                Response.status(200).entity(calificacion).build() :
                Response.status(400).entity("Estadistica no encontrada").build();
    }

}
