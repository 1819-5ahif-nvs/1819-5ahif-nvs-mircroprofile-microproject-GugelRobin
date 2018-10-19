package at.htl.nvs.bakeme.bakemeserver.business;

import at.htl.nvs.bakeme.bakemeserver.entity.Ingredient;
import at.htl.nvs.bakeme.bakemeserver.entity.ProductionStep;
import at.htl.nvs.bakeme.bakemeserver.entity.Recipe;
import at.htl.nvs.bakeme.bakemeserver.entity.helperentity.Pair;

import javax.ejb.Stateless;
import javax.json.*;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Stateless
public class RecipeToJsonConverter {

    public JsonArray extractNameAndAvailableMonth(List<Recipe> recipes) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Recipe r: recipes) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("name", r.getName());
            objectBuilder.add("availableMonths", extractAvailableMonths(r));
            arrayBuilder.add(objectBuilder.build());
        }
        return arrayBuilder.build();
    }

    public JsonObject toJsonObject(Recipe recipe) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("name", recipe.getName());
        builder.add("availableMonths", extractAvailableMonths(recipe));
        builder.add("productionSteps", parseProductionSteps(recipe));
        return builder.build();
    }

    private JsonArray parseProductionSteps(Recipe r) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (ProductionStep p: r.getProductionSteps()) {
            builder.add(parseProductionStep(p));
        }
        return builder.build();
    }

    private JsonObject parseProductionStep(ProductionStep productionStep) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("name", productionStep.getName());
        builder.add("description", productionStep.getDescription());
        JsonArrayBuilder neededIngredientsArrayBuilder = Json.createArrayBuilder();
        for (Pair<Ingredient, Integer> neededIngredients: productionStep.getNeededIngredients()) {
            JsonObjectBuilder b = Json.createObjectBuilder();
            b.add("name", neededIngredients.getKey().getName());
            b.add("amount", neededIngredients.getValue());
            neededIngredientsArrayBuilder.add(b.build());
        }
        builder.add("neededIngredients", neededIngredientsArrayBuilder.build());
        return builder.build();
    }

    private JsonArray extractAvailableMonths(Recipe r) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (Month m: r.getAvailableMonths()) {
            builder.add(m.getDisplayName(TextStyle.FULL, Locale.GERMAN));
        }
        return builder.build();
    }

}
