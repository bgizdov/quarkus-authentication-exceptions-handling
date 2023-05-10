package org.acme.filters;

import io.quarkus.security.AuthenticationFailedException;
import io.vertx.core.http.HttpServerRequest;
import java.util.Objects;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

/**
 * The type Authentication failed error mapper.
 * Requires quarkus 2.14.+ to work.
 */
@Provider
public class AuthenticationFailedErrorMapper
    implements ExceptionMapper<AuthenticationFailedException> {

  @Inject
  Logger log;

  @Context
  UriInfo info;

  @Context
  HttpServerRequest request;

  private static final Response.Status status = Response.Status.FORBIDDEN;

  @Override
  public Response toResponse(AuthenticationFailedException exception) {
    log.warn(exception.getMessage(), exception);
    String message =
        Objects.isNull(exception.getMessage()) ? "Authentication failed." : exception.getMessage();
    int code = status.getStatusCode();
    String forbidden = status.getReasonPhrase().toLowerCase();
    var error = new ErrorResponse(code, message, forbidden);
    return Response
        .status(code)
        .entity(error)
        .build();
  }
}
