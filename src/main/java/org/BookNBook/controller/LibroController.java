package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.BookNBook.controller.dao.ListadoDAO;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Libro;
import org.BookNBook.persistence.manager.ManagerLibro;
import org.BookNBook.service.LibroService;
import org.BookNBook.service.impl.LibroServiceImpl;

import java.util.ArrayList;

/**
 * Controlador para manejar las acciones realizadas con los libros
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@Path("/libro")
public class LibroController {

    /**
     * Clase servicio
     */
    private LibroService libroService;

    /**
     * Constructor del Controlador de libros
     */
    public LibroController(){
        libroService = new LibroServiceImpl(new ManagerLibro());
    }

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
     * @param genero género o temática de la lectura
     * @return Response con una lista de las lecturas
     */
    @GET
    @Path("/listado/{genero}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response listarLibrosTipoGenero(@PathParam(value="genero")  String genero) {
        MySQLConnector con = new MySQLConnector();
        ListadoDAO listado = new ListadoDAO();
        listado.setListado((ArrayList) libroService.listarLibrosTipoGenero(con, genero));
        listado.setMessage("Listado leido correctamente");
        return (listado != null) ?
                Response.ok().entity(listado).build() :
                Response.status(400).entity(ListadoDAO.builder().message("Error al leer listado")).build() ;
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
        ListadoDAO listado = new ListadoDAO();
        listado.setListado((ArrayList) libroService.listarLibrosNoLeidos(con, idUsuario));
        listado.setMessage("Listado leido correctamente");
        return listado != null ?
                Response.ok().entity(listado).build() :
                Response.status(400).entity(ListadoDAO.builder().message("Error al leer listado")).build() ;
    }

    /**
     * Obtener listado de libros leídos por un usuario
     * @param idUsuario Identificador de usuario
     * @return Response con una lista de las lecturas
     */
    @GET
    @Path("/{idUsuario}/leidos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response listarLibrosLeidos(@PathParam(value="idUsuario") Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        ListadoDAO listado = new ListadoDAO();
        listado.setListado((ArrayList) libroService.listarLibrosLeidos(con, idUsuario));
        listado.setMessage("Listado leido correctamente");
        return listado != null ?
                Response.ok().entity(listado).build() :
                Response.status(400).entity(ListadoDAO.builder().message("Error al leer listado")).build() ;
    }

    /**
     * Obtener listado de libros en lectura por un usuario
     * @param idUsuario Identificador de usuario
     * @return Response con una lista de las lecturas
     */
    @GET
    @Path("/{idUsuario}/activos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response listarLibrosenLectura(@PathParam(value="idUsuario") Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        ListadoDAO listado = new ListadoDAO();
        listado.setListado((ArrayList) libroService.listarLibrosenLectura(con, idUsuario));
        listado.setMessage("Listado leido correctamente");
        return listado != null ?
                Response.ok().entity(listado).build() :
                Response.status(400).entity(ListadoDAO.builder().message("Error al leer listado")).build() ;
    }

    /**
     * Obtener un listado de libros
     * @return Response con una lista de libros
     */
    @GET
    @Path("/listado")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response listarLibros() {
        MySQLConnector con = new MySQLConnector();
        ListadoDAO listado = new ListadoDAO();
        listado.setListado((ArrayList) libroService.listarLibros(con));
        listado.setMessage("Listado leido correctamente");
        return listado != null ?
                Response.ok().entity(listado).build() :
                Response.status(400).entity(ListadoDAO.builder().message("Error al leer listado")).build() ;
    }

    /**
     * Buscar un libro por su id
     * @param id Identificador del libro
     * @return Response con el libro en cuestión o una negativa de la búsqueda realizada
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response buscarLibro(@PathParam(value="id") Integer id) {
        MySQLConnector con = new MySQLConnector();
        Libro libro = libroService.buscarLibro(con, id);
        return (libro != null) ?  Response.ok().entity(libro).build() :
                Response.noContent().entity("El libro " + id + "no se ha encontrado").build();
    }

}
