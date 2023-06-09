package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.BookNBook.controller.dao.LoginDAO;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Usuario;
import org.BookNBook.service.impl.UsuarioServiceImpl;

@AllArgsConstructor
@Path("/usuario")
public class UsuarioController {

    private UsuarioServiceImpl usuarioService;
    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginDAO usuario) {
        MySQLConnector con = new MySQLConnector();
        Usuario usuarioLogin = usuarioService.logging(con, usuario.getUsuario(), usuario.getPassword());
        return (usuarioLogin!=null) ?
                Response.ok().entity(usuarioLogin).build() :
                Response.notModified().entity("Usuario no logueado").build();
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Usuario usuario) {
        MySQLConnector con = new MySQLConnector();
        return (usuarioService.newUsuario(con, usuario)) ?
            Response.ok().entity("Usuario registrado correctamente!").build() :
            Response.notModified().entity("Error al crear el usuario").build();

    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUsuario(String usuario) {
        MySQLConnector con = new MySQLConnector();
        return (usuarioService.deleteUsuario(con, usuario)) ?
                Response.ok().entity("Usuario " + usuario + " eliminado correctamente!").build() :
                Response.notModified().entity("Error al eliminar el usuario: " + usuario).build();
    }

    @GET
    @Path("/listado")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(usuarioService.listarUsuarios(con)).build();
    }
}
