package com.cinare.controller;

import com.cinare.model.Library;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloController {

    @GET
    @Path("/jair")
    public Response hello() {
        return Response.ok("Hello Jair").build();
    }

    @GET
    @Path("/jairson")
    @Produces(MediaType.APPLICATION_JSON)
    public Library jairToJson() {

        Library jair = new Library();
        jair.setAge("300");
        jair.setName("Luiz");

        return jair;
    }

}
