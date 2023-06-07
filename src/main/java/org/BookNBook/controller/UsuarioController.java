package org.BookNBook.controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginDAO usuario) {
        MySQLConnector con = new MySQLConnector();
        Usuario usuarioLogin = usuarioService.logging(con, usuario.getUsuario(), usuario.getPassword());
        return (usuarioLogin!=null) ?
                Response.ok().entity(usuarioLogin).build() :
                Response.notModified().entity("El autor no se ha añadido").build();
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
}
