package com.cinare.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/dummy")
public class DummyController {

    @GET
    @Path("/h")
    public Response hello() {
        return Response.ok().build();
    }
}
