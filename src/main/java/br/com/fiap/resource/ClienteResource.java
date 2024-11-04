package br.com.fiap.resource;

import br.com.fiap.bo.ClienteBO;
import br.com.fiap.to.ClienteTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/SeguroExpress/cliente")
public class ClienteResource {
    private ClienteBO clienteBO = new ClienteBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<ClienteTO> resultado = clienteBO.findAll();
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok(); // 200 (ok)
        } else {
            response = Response.status(404); // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("codigo") Long codigo) {
        ClienteTO resultado = clienteBO.findByCodigo(codigo);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok(); // 200 - OK
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(ClienteTO cliente) {
        ClienteTO resultado = clienteBO.save(cliente);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null); // 201 - CREATED
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{codigo}")
    public Response delete(@PathParam("codigo") Long codigo) {
        Response.ResponseBuilder response = null;
        if (clienteBO.delete(codigo)) {
            response = Response.status(204); // 204 - NO CONTENT
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(ClienteTO cliente, @PathParam("codigo") Long codigo) {
        cliente.setCodigo(codigo);
        ClienteTO resultado = clienteBO.update(cliente);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null); // 201 - CREATED
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        return response.build();
    }
}
