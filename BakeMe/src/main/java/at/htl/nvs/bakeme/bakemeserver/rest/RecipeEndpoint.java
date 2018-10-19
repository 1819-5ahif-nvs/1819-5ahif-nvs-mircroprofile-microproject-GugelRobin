package at.htl.nvs.bakeme.bakemeserver.rest;

import at.htl.nvs.bakeme.bakemeserver.business.RecipeFacade;
import at.htl.nvs.bakeme.bakemeserver.business.RecipeToJsonConverter;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("recipe")
public class RecipeEndpoint {

    @Inject
    private RecipeFacade facade;

    @Inject
    private RecipeToJsonConverter converter;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getAllRecipeNamesWithAvailableMonths() {
        return converter.extractNameAndAvailableMonth(facade.getAllRecipes());
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getRecipe(@PathParam("name") String name) {
        return converter.toJsonObject(facade.getRecipeByName(name));
    }

}
