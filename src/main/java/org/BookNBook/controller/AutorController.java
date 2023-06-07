package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Autor;
import org.BookNBook.service.AutorService;


@AllArgsConstructor
@Path("/autor")
public class AutorController {

    private AutorService autorService;

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

    @GET
    @Path("/{idAutor}/libros")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getLibrosAutor(@PathParam(value="idAutor") Integer idAutor) {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(autorService.listarLibrosAutor(con, idAutor)).build();
    }

    @GET
    @Path("/autores")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAutores() {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(autorService.listarAutores(con)).build();
    }

}
