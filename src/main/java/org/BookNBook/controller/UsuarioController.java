package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.BookNBook.controller.dao.LoginDAO;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Usuario;
import org.BookNBook.persistence.manager.ManagerUsuario;
import org.BookNBook.service.impl.UsuarioServiceImpl;

/**
 * Controlador para manejar las acciones realizadas con los usuarios
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@Path("/usuario")
public class UsuarioController {

    /**
     * Clase servicio
     */
    private UsuarioServiceImpl usuarioService;

    /**
     * Constructor del Controlador de usuarios
     */
    public UsuarioController(){
        usuarioService = new UsuarioServiceImpl(new ManagerUsuario());
    }
    /**
     * Loging de un usuario
     * @param usuario email y contraseña de un usuario
     * @return Response con el usuario o con una negativa de dicha acción
     */
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginDAO usuario) {
        MySQLConnector con = new MySQLConnector();
        Usuario usuarioLogin = usuarioService.logging(con, usuario.getUsuario(), usuario.getPassword());
        return usuarioLogin!=null ?
                Response.status(200).entity(usuarioLogin).build() :
                Response.status(400).entity("Usuario no logueado").build();
    }


    /**
     * Registro o añadido de un usuario
     * @param usuario objeto usuario
     * @return Response con un texto afirmativo o negativo sobre el registro/añadido de un usuario
     */
    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Usuario usuario) {
        MySQLConnector con = new MySQLConnector();
        return (usuarioService.newUsuario(con, usuario)) ?
            Response.ok().entity("Usuario registrado correctamente!").build() :
            Response.status(400).entity("Error al crear el usuario").build();

    }

    /**
     * Borrado de un usuario
     * @param usuario email del usuario
     * @return Response con un texto afirmativo o negativo sobre el borrado de un usuario
     */
    @DELETE
    @Path("/delete/{usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUsuario(@PathParam("usuario") String usuario) {
        MySQLConnector con = new MySQLConnector();
        return (usuarioService.deleteUsuario(con, usuario)) ?
                Response.ok().entity("Usuario " + usuario + " eliminado correctamente!").build() :
                Response.status(400).entity("Error al eliminar el usuario: " + usuario).build();
    }

    /**
     * Obtener un listado de los usuarios registrados
     * @return Response con un listado de usuarios
     */
    @GET
    @Path("/listado")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        MySQLConnector con = new MySQLConnector();
        return Response.ok().entity(usuarioService.listarUsuarios(con)).build();
    }
}
