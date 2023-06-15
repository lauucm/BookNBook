package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.BookNBook.controller.dao.DinamicaDAO;
import org.BookNBook.controller.dao.NoDataResponse;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Dinamica;
import org.BookNBook.persistence.manager.ManagerDinamica;
import org.BookNBook.service.DinamicaService;
import org.BookNBook.service.impl.DinamicaServiceImpl;

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
     * Constructor del Controlador de dinamicas
     */
    public DinamicaController(){
        dinamicaService = new DinamicaServiceImpl(new ManagerDinamica());
    }


    /**
     * Obtiene los datos segun un libro y un usuario
     * @param dato Identificador de libro + Identificador de usuario
     * @return Response con la dinamica específica o con la negativa de no haber encontrado los datos en la úsqueda realziada
     */
    @POST
    @Path("/obtener")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getDinamica(DinamicaDAO dato) {
        MySQLConnector con = new MySQLConnector();
        Dinamica dinamica = dinamicaService.getDinamica(con, dato.getIdLibro(), dato.getIdUsuario() );
        return (dinamica != null) ?
                Response.ok().entity(dinamica).build() :
                Response.status(400).entity("Dinamica no encontrada").build() ;
    }

    /**
     * Añadir una dinámica
     * @param dato Identificador de libro + Identificador de usuario
     * @return Response con una afirmativa o negativa acorde al añadido de los datos de dinámica
     */
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDinamica(DinamicaDAO dato) {
        MySQLConnector con = new MySQLConnector();
        Boolean exist = dinamicaService.addDinamica(con, dato.getIdLibro(), dato.getIdUsuario());
        return exist ?
                Response.ok().entity(NoDataResponse.builder().ok(true).message("Dinamica añadida correctamente").build()).build() :
                Response.ok().entity(NoDataResponse.builder().ok(false).message("Dinamica no se ha añadido").build()).build();
    }

     /**
     * Actualizar el número de páginas de lecturas actual de un usuario con un libro determinado
     * @param dato Dinamica con los datos para la consulta
     * @return Response con una afirmativa o negativa acorde al actualizado del número de páginas
     */
    @POST
    @Path("/update/paginas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePag(DinamicaDAO dato) {
        MySQLConnector con = new MySQLConnector();
        Boolean exist = dinamicaService.updatePag(con, dato.getIdLibro(), dato.getIdUsuario(), dato.getPagActual());
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
    @Consumes(MediaType.TEXT_PLAIN)
    public Response paginasLeidas(@PathParam(value="idUsuario") Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        Integer paginas = dinamicaService.paginasLeidas(con, idUsuario);
        return paginas != null?
                Response.status(200).entity(paginas).build() :
                Response.status(400).entity("Usuario no encontrado").build();
    }

}
