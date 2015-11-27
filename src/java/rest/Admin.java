
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;
import entity.User;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("admin")
@RolesAllowed("Admin")
public class Admin
{

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU-Local");

    @GET
    @Path("/users")
    @Produces("application/json")
    public Response getAllUsers()
    {
        String jsonFormatUsers;
        EntityManager em = getEntityManager();
        try {
            List<User> users = em.createQuery("SELECT u FROM User u").getResultList();
            JsonArray jArray = new JsonArray();
            for (User user : users) {
                JsonObject jsonObj = new JsonObject();
                if (!"admin".equals(user.getUserName())) {
                    jsonObj.addProperty("name", user.getUserName());
                    jArray.add(jsonObj);
                }
            }
            jsonFormatUsers = gson.toJson(jArray);
        } finally {
            em.close();
        }

        return Response.ok(jsonFormatUsers).build();
    }

    @DELETE
    @Path("/user/{name}")
    @Produces("application/json")
    public Response deleteUser(@PathParam("name") String name)
    {
        //boolean isDeleted = false;
        Response updatedListOfUsers;
        EntityManager em = getEntityManager();
        try {
            User user = em.find(User.class, name);
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
            //isDeleted = true;
            updatedListOfUsers = getAllUsers();
        } finally {
            em.close();
        }
        return updatedListOfUsers;
    }

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

}
