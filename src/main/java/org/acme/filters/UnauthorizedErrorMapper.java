package org.acme.filters;

import io.quarkus.security.UnauthorizedException;
import io.vertx.core.http.HttpServerRequest;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

/**
 * The type Unauthorized error mapper.
 * Requires quarkus 2.14.+ to work.
 */
@Provider
public class UnauthorizedErrorMapper implements ExceptionMapper<UnauthorizedException> {
  @Inject
  Logger log;

  @Context
  UriInfo info;

  @Context
  HttpServerRequest request;
  private static final Response.Status status = Response.Status.UNAUTHORIZED;

  @Override
  public Response toResponse(UnauthorizedException exception) {
    log.warn(exception.getMessage(), exception);
    String message = exception.getMessage();
    int code = status.getStatusCode();
    String unauthorized = status.getReasonPhrase().toLowerCase();
    var error = new ErrorResponse(code, message, unauthorized);
    return Response
        .status(code)
        .entity(error)
        .build();
  }
}
