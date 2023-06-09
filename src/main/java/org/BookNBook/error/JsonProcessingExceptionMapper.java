package org.BookNBook.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * @author m.escribano.verde
 * @author l.cabrera.mora
 * @date 2023/06/15
 */
@Provider
public class JsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException> {

    /**
     *
     */
    public static class Error {
        public String key;
        public String message;
    }

    /**
     *
     * @param exception
     * @return
     */
    @Override
    public Response toResponse(JsonProcessingException exception) {
        Error error = new Error();
        error.key = "bad-json";
        error.message = exception.getMessage();
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}
