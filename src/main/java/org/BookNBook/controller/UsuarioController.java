package org.BookNBook.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.BookNBook.controller.dao.ListadoDAO;
import org.BookNBook.controller.dao.ListadoUsuarioDAO;
import org.BookNBook.controller.dao.LoginDAO;
import org.BookNBook.controller.dao.NoDataResponse;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.dao.Usuario;
import org.BookNBook.persistence.manager.ManagerUsuario;
import org.BookNBook.service.impl.UsuarioServiceImpl;

import java.util.ArrayList;

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
        Boolean exist = usuarioService.newUsuario(con, usuario);
        return exist ?
                Response.ok().entity(NoDataResponse.builder().ok(true).message("Usuario registrado correctamente!").build()).build() :
                Response.ok().entity(NoDataResponse.builder().ok(false).message("Error al crear el usuario").build()).build();
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
    public Response deleteUsuario(@PathParam("usuario") Integer usuario) {
        MySQLConnector con = new MySQLConnector();
        Boolean exist = usuarioService.deleteUsuario(con, usuario);
        return exist ?
            Response.ok().entity(NoDataResponse.builder().ok(true).message("Usuario " + usuario + " eliminado correctamente!").build()).build() :
            Response.ok().entity(NoDataResponse.builder().ok(false).message("Error al eliminar el usuario: " + usuario).build()).build();

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
        ListadoUsuarioDAO listado = new ListadoUsuarioDAO();
        listado.setListado((ArrayList) usuarioService.listarUsuarios(con));
        listado.setMessage("Listado leido correctamente");
        return listado != null ?
                Response.ok().entity(listado).build() :
                Response.status(400).entity(ListadoDAO.builder().message("Error al leer listado")).build() ;
    }

    @GET
    @Path("/register/email")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response existEmail(@QueryParam("email") String email) {
        MySQLConnector con = new MySQLConnector();
        Boolean exist = usuarioService.existEmail(con, email);
        return exist ?
                Response.ok().entity(NoDataResponse.builder().ok(true).message("Email ya registrado").build()).build() :
                Response.ok().entity(NoDataResponse.builder().ok(false).message("Email no registrado").build()).build();
    }

    /**
     * Obtener datos de un usuario
     * @param usuario Identificador del usuario
     * @return Response con el usuario o con una negativa de dicha acción
     */
    @GET
    @Path("/{usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response getUsuario(@PathParam("usuario") Integer usuario) {
        MySQLConnector con = new MySQLConnector();
        Usuario usuarioLogin = usuarioService.getUsuario(con, usuario);
        return usuarioLogin!=null ?
                Response.status(200).entity(usuarioLogin).build() :
                Response.status(400).entity("Usuario no encontrado").build();
    }

}
