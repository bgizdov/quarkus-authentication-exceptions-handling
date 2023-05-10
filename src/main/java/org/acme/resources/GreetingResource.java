package org.acme.resources;

import io.quarkus.security.UnauthorizedException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Path("/forbidden")
    @Produces(MediaType.APPLICATION_JSON)
    public String forbidden() {
        throw new ForbiddenException("You are not allowed to access this resource");
    }

    @GET
    @Path("/unauthorized")
    @Produces(MediaType.APPLICATION_JSON)
    public String unauthorized() {
        throw new UnauthorizedException("You are not authenticated to access this resource");
    }
}