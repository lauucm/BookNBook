package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.service.AutorService;

/**
 * Controlador para manejar las acciones realizadas con los autores
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@AllArgsConstructor
@Path("/autor")
public class AutorController {

    /**
     * Clase servicio
     */
    private AutorService autorService;

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
        return (autorService.addAutor(con, autor)) ?
            Response.ok().entity("Autor añadido correctamente").build() :
            Response.notModified().entity("El autor mo se ha añadido").build();
    }

    /**
     * Busqueda de un autor por su pseudonimo
     * @param pseudonimo Nombre autor
     * @return Response con el autor encontrado según el nombre o con una negativa de no haber encontrado nada en búsqueda
     */
    @GET
    @Path("/{pseudonimo}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAutor(@PathParam(value = "pseudonimo") String pseudonimo) {
        MySQLConnector con = new MySQLConnector();
        Autor autor = autorService.buscarAutor(con, pseudonimo);
        return (autor!=null) ?
             Response.ok().entity(autor).build() :
             Response.notModified().entity("No se ha encontrado el autor buscado").build();
    }

    /**
     * Obtiene los libros de un autor determinado
     * @param idAutor Identificador de autor
     * @return Response con una lista de los libros escritos por un autor determinado
     */
    @GET
    @Path("/{idAutor}/libros")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getLibrosAutor(@PathParam(value="idAutor") Integer idAutor) {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(autorService.listarLibrosAutor(con, idAutor)).build();
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
        return Response.ok().entity(autorService.listarAutores(con)).build();
    }

}
