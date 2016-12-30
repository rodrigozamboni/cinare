package com.cinare.controller;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloController {

    @Path("/jair")
    public Response hello() {
        return Response.ok("Hello Jair").build();
    }

}
