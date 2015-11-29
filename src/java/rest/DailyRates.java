/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import deploy.DeploymentConfiguration;
import entity.currencyDailyService.Currency;
import entity.currencyDailyService.DailyExchangeRate;
import facades.CurrencyFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.minidev.json.JSONObject;
import static org.glassfish.jersey.uri.UriComponent.Type.PATH;

/**
 * REST Web Service
 *
 * @author Cookie
 */
@Path("currency")
@RolesAllowed("User")
public class DailyRates {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
    CurrencyFacade cf = new CurrencyFacade();
//  CurrencyFacade facade = new CurrencyFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DailyRates
     */
    public DailyRates() {
    }

    public String getJsonFromCurrency(List<Currency> curr) {
//        List<JsonObject> currList = new ArrayList();
        JsonArray ja = new JsonArray();
        for (int i = 0; i < curr.size(); i++) {
            JsonObject jsObj = new JsonObject();
            jsObj.addProperty("code", curr.get(i).getCode());
            jsObj.addProperty("description", curr.get(i).getDescription());
            ja.add(jsObj);
        }

        return gson.toJson(ja);
    }

    public String getJsonFromDailyExchangeRate(List<DailyExchangeRate> curr) {
        List<JsonObject> currList = new ArrayList();
        for (int i = 0; i < curr.size(); i++) {
            JsonObject jsObj = new JsonObject();
            jsObj.addProperty("date", curr.get(i).getDate_());
            jsObj.addProperty("rate", curr.get(i).getRate());
            jsObj.addProperty("id", curr.get(i).getId());
            currList.add(jsObj);

        }
        return gson.toJson(currList);
    }

    public String both(List<Currency> curr, List<DailyExchangeRate> daily) {
//        List<JsonObject> currList = new ArrayList();
        JsonArray ja = new JsonArray();

        for (int i = 0; i < curr.size() || i < daily.size(); i++) {
            JsonObject jcurrObj = new JsonObject();
            jcurrObj.addProperty("code", curr.get(i).getCode());
            jcurrObj.addProperty("description", curr.get(i).getDescription());
            jcurrObj.addProperty("date", daily.get(i).getDate_());
            jcurrObj.addProperty("rate", daily.get(i).getRate());
            jcurrObj.addProperty("id", daily.get(i).getId());
            ja.add(jcurrObj);
        }

        return gson.toJson(ja);
    }

    /**
     * Retrieves representation of an instance of rest.DailyRates
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("dailyRates")
    @Produces(MediaType.APPLICATION_JSON)
    public String getXmlCurrencies() {

//        String jsonConverter = getJsonFromCurrency(cf.getCurr());
        String jsonConverter = both(cf.getCurr(), cf.getCurr1());
//       String jsonConverter = getJsonFromCurrency(cf.getCurr());

        return jsonConverter;
    }

    /**
     * PUT method for updating or creating an instance of DailyRates
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

    public String jsonAmount(Double amount) {
        JsonObject jsObj = new JsonObject();
        jsObj.addProperty("amount", amount);
        return gson.toJson(jsObj);
    }

    @GET
//    @Produces("application/xml")
    @Path("calculator/{amount}/{fromcurrency}/{tocurrency}")
    public String getToAndFrom(@PathParam("amount") Double amount, @PathParam("fromcurrency") String fromcurrency, @PathParam("tocurrency") String tocurrency) {
        String toAndFrom = jsonAmount(cf.Calculator(amount, fromcurrency, tocurrency));
        return toAndFrom;
    }
}
