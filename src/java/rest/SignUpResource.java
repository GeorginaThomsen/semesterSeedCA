/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Role;
import entity.User;
import facades.UserFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author terziev
 */
@Path("signUp")
public class SignUpResource {

    private static Gson gson;
    private UserFacade facade;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SignUpResource
     */
    public SignUpResource() {
        gson = gson = new GsonBuilder().setPrettyPrinting().create();
        facade = new UserFacade();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response signUp(String userDetails) {

        JsonObject jo = new JsonParser().parse(userDetails).getAsJsonObject();

        String username = jo.get("username").getAsString();
        String password = jo.get("password").getAsString();

        JsonObject jnewUser = new JsonObject();
        if (!username.trim().equals("") || !password.trim().equals("")) {

            User newUser = new User(username, password);
            User newUserReturned = facade.signUp(newUser);
            jnewUser.addProperty("username", newUserReturned.getUserName());
            jnewUser.addProperty("role", newUserReturned.getRoles().get(0).getRoleName());
        }

        return Response.ok(gson.toJson(jnewUser)).build();
    }
}
