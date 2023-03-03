package id.kawahedukasi.controller;

import id.kawahedukasi.model.Item;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemController {
    // List all item
    @GET
    public Response get(){
        return Response.status(Response.Status.OK).entity(Item.findAll().list()).build();
    }

    // Detail Item by id
    @Path("/{id}")
    @GET
    public Response get(@PathParam("id") Long id) {
        Optional<Item> optionalItem = Item.findByIdOptional(id);
        if (optionalItem.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "Can't find Item"))
                    .build();
        }
        Item item = optionalItem.get();

        Map<String, Object> response = new HashMap<>();
        response.put("name", item.name);
        response.put("count", item.count);
        response.put("price", item.price);
        response.put("type", item.type);
        response.put("description", item.description);
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @POST
    @Transactional
    public Response post(Map<String, Object> request){
        Item item = new Item();
        item.name = request.get("name").toString();
        item.count = Double.valueOf(request.get("count").toString());
        item.price = Double.valueOf(request.get("price").toString());
        item.type = request.get("type").toString();
        item.description = request.get("description").toString();

        // Save to db
        item.persist();

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response put(@PathParam("id") Long id, Map<String, Object> request){
        Item item = Item.findById(id);
        if(item == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        item.name = request.get("name").toString();
        item.count = Double.valueOf(request.get("count").toString());
        item.price = Double.valueOf(request.get("price").toString());
        item.type = request.get("type").toString();
        item.description = request.get("description").toString();

        // Save to db
        item.persist();

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id){
        Item item = Item.findById(id);
        if(item == null){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "ITEM_ALREADY_DELETED"))
                    .build();
        }
        //Item.deleteById(id);
        item.delete();

        return Response.status(Response.Status.NO_CONTENT).entity(Map.of("id", item.id)).build();
    }
}
