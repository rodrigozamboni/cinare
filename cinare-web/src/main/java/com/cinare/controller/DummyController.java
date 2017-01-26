package com.cinare.controller;

import com.cinare.repository.DummyRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/dummy")
public class DummyController {

    @GET
    @Path("/h")
    public Response hello() {
        return Response.ok().build();
    }

    @GET
    @Path("/n/{nome}")
    public Response hello2(@PathParam("nome") String nome) {
        new DummyRepository().put(nome);
        return Response.ok("Hello Belo Jair").build();
    }
}
