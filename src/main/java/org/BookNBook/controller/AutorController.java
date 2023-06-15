package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.BookNBook.controller.dao.BuscarDAO;
import org.BookNBook.controller.dao.ListadoAutorDAO;
import org.BookNBook.controller.dao.ListadoDAO;
import org.BookNBook.controller.dao.NoDataResponse;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.manager.ManagerAutor;
import org.BookNBook.service.AutorService;
import org.BookNBook.service.impl.AutorServiceImpl;

import java.util.ArrayList;

/**
 * Controlador para manejar las acciones realizadas con los autores
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */

@Path("/autor")
public class AutorController {

    /**
     * Clase servicio
     */
    private AutorService autorService;

    public AutorController(){
        autorService = new AutorServiceImpl(new ManagerAutor());
    }

    /**
     * Añade un autor
     * @param autor Elemento autor
     * @return Response con un texto afirmativo o negativo según se añade o no un autor
     */
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAutor(Autor autor) {
        MySQLConnector con = new MySQLConnector();
        Boolean exist = autorService.addAutor(con, autor);
        return exist ?
                Response.ok().entity(NoDataResponse.builder().ok(true).message("Autor añadido correctamente!").build()).build() :
                Response.ok().entity(NoDataResponse.builder().ok(false).message("El autor mo se ha añadido").build()).build();
    }

    /**
     * Busqueda de un autor por su pseudonimo
     * @param nombre Nombre autor
     * @return Response con el autor encontrado según el nombre o con una negativa de no haber encontrado nada en búsqueda
     */
    @POST
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response buscarAutor(BuscarDAO nombre) {
        MySQLConnector con = new MySQLConnector();
        Autor autor = autorService.buscarAutor(con, nombre.getDato());
        return (autor!=null) ?
                Response.status(200).entity(autor).build() :
                Response.status(400).entity("Autor no encontrado").build();
    }

    /**
     * Obtiene los libros de un autor determinado
     * @param idAutor Identificador de autor
     * @return Response con una lista de los libros escritos por un autor determinado
     */
    @GET
    @Path("/{idAutor}/libros")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response getLibrosAutor(@PathParam(value="idAutor") Integer idAutor) {
        MySQLConnector con = new MySQLConnector();
        ListadoDAO listado = new ListadoDAO();
        listado.setListado((ArrayList) autorService.listarLibrosAutor(con, idAutor));
        listado.setMessage("Listado leido correctamente");
        return (listado != null) ?
                Response.ok().entity(listado).build() :
                Response.status(400).entity(ListadoDAO.builder().message("Error al leer listado")).build() ;
    }

    /**
     * Obtiene listado de todos los autores disponibles
     * @return Response con una lista de los autores existentes
     */
    @GET
    @Path("/autores")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAutores() {
        MySQLConnector con = new MySQLConnector();
        ListadoAutorDAO listado = new ListadoAutorDAO();
        listado.setListado((ArrayList) autorService.listarAutores(con));
        listado.setMessage("Listado leido correctamente");
        return (listado != null) ?
                Response.ok().entity(listado).build() :
                Response.status(400).entity(ListadoDAO.builder().message("Error al leer listado")).build() ;
    }

}
