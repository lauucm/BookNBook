package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.dao.Libro;
import org.BookNBook.service.LibroService;

/**
 * Controlador para manejar las acciones realizadas con los libros
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@AllArgsConstructor
@Path("/libro")
public class LibroController {

    /**
     * Clase servicio
     */
    private LibroService libroService;

    /**
     * Buscar un libro por su nombre
     * @param nombre nombre del libro
     * @return Response con el libro en cuestión o una negativa de la búsqueda realizada
     */
    @GET
    @Path("/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buscarLibro(@PathParam(value="nombre") String nombre) {
        MySQLConnector con = new MySQLConnector();
        nombre = nombre.replaceAll("_"," ");
        Libro libro = libroService.buscarLibro(con, nombre);
        return (libro != null) ?  Response.ok().entity(libro).build() :
                Response.noContent().entity("El libro "+ libro + "no se ha encontrado").build();
    }

//    /**
//     * Añadir un libro
//     * @param libro libro a añadir
//     * @param autor autor del libro
//     * @return Response con una afirmativa o negativa sobre el añadido del libro
//     */
//    @POST
//    @Path("/add")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response addLibro(Libro libro, Autor autor) {
//        MySQLConnector con = new MySQLConnector();
//        return (libroService.addLibro(con, libro, autor)) ?
//                Response.ok().entity("Libro añadido correctamente").build() :
//                Response.notModified().entity("El libro mo se ha añadido").build();
//    }

    /**
     * Obtener un listado de las lecturas según tipo y género
     * @param tipo tipo de lectura
     * @param genero género o temática de la lectura
     * @return Response con una lista de las lecturas
     */
    @GET
    @Path("/{tipo}/{genero}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarLibrosTipoGenero(@PathParam(value="tipo") String tipo, @PathParam(value="genero")  String genero) {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(libroService.listarLibrosTipoGenero(con, tipo, genero)).build();
    }

    /**
     * Obtener listado de los libros no leídos (añadidos al perfil) por un usuario
     * @param idUsuario Identificador de usuario
     * @return Response con una lista de las lecturas
     */
    @GET
    @Path("/{idUsuario}/noleidos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarLibrosNoLeidos(@PathParam(value="idUsuario") Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(libroService.listarLibrosNoLeidos(con, idUsuario)).build();
    }

    /**
     * Obtener listado de libros leídos por un usuario
     * @param idUsuario Identificador de usuario
     * @return Response con una lista de las lecturas
     */
    @GET
    @Path("/{idUsuario}/leidos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarLibrosLeidos(@PathParam(value="idUsuario") Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(libroService.listarLibrosLeidos(con, idUsuario)).build();
    }

    /**
     * Obtener listado de libros en lectura por un usuario
     * @param idUsuario Identificador de usuario
     * @return Response con una lista de las lecturas
     */
    @GET
    @Path("/{idUsuario}/activos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarLibrosenLectura(@PathParam(value="idUsuario") Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(libroService.listarLibrosenLectura(con, idUsuario)).build();
    }

}
