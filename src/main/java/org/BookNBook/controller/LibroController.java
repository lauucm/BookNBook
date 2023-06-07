package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.persistence.dao.Libro;
import org.BookNBook.service.LibroService;

@AllArgsConstructor
@Path("/libro")
public class LibroController {

    private LibroService libroService;

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

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLibro(Libro libro, Autor autor) {
        MySQLConnector con = new MySQLConnector();
        return (libroService.addLibro(con, libro, autor)) ?
                Response.ok().entity("Libro añadido correctamente").build() :
                Response.notModified().entity("El libro mo se ha añadido").build();
    }

    @GET
    @Path("/{tipo}/{genero}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarLibrosTipoGenero(@PathParam(value="tipo") String tipo, @PathParam(value="genero")  String genero) {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(libroService.listarLibrosTipoGenero(con, tipo, genero)).build();
    }

    @GET
    @Path("/{idUsuario}/noleidos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarLibrosNoLeidos(@PathParam(value="idUsuario") Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(libroService.listarLibrosNoLeidos(con, idUsuario)).build();
    }

    @GET
    @Path("/{idUsuario}/leidos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarLibrosLeidos(@PathParam(value="idUsuario") Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(libroService.listarLibrosLeidos(con, idUsuario)).build();
    }

    @GET
    @Path("/{idUsuario}/activos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarLibrosenLectura(@PathParam(value="idUsuario") Integer idUsuario) {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(libroService.listarLibrosenLectura(con, idUsuario)).build();
    }

}
