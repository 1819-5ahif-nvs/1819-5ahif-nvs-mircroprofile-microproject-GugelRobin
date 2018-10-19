package at.htl.nvs.bakeme.bakemeserver.business;

import at.htl.nvs.bakeme.bakemeserver.entity.Ingredient;
import at.htl.nvs.bakeme.bakemeserver.entity.ProductionStep;
import at.htl.nvs.bakeme.bakemeserver.entity.Recipe;
import at.htl.nvs.bakeme.bakemeserver.entity.helperentity.Pair;
import at.htl.nvs.bakeme.bakemeserver.entity.helperentity.ProductionStep_Ingredient;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Month;
import java.util.Arrays;
import java.util.EnumSet;

@Startup
@Singleton
public class InitBean {

    @Inject
    private RecipeFacade facade;

    public InitBean() {

    }

    @PostConstruct
    private void addChocolateCornflakes() {
        Recipe chocolateCornflakes = new Recipe("Chocolate Cornflakes", "Some easy to make Chocolate Cornflakes");
        Ingredient seasonedCornflakes = new Ingredient("Seasoned Cornflakes", EnumSet.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER));
        Ingredient seasonedChocolate = new Ingredient("Seasoned Chocolate", EnumSet.of(Month.NOVEMBER, Month.DECEMBER, Month.JANUARY, Month.FEBRUARY));
        ProductionStep meltChocolate = new ProductionStep("Melt Chocolate",
                "Melt the Chocolate in the Oven on 50 deg Celcius",
                Arrays.asList(new Pair<>(seasonedChocolate, 100)));
        meltChocolate.setRecipe(chocolateCornflakes);
        ProductionStep mixChocolateAndCornflakes = new ProductionStep("Mix", "Put the melted Chocolate over the Cornflakes",
                Arrays.asList(new Pair<>(seasonedCornflakes, 300), new Pair<>(seasonedChocolate, 100)));
        ProductionStep enjoy = new ProductionStep("Enjoy", "Eat and enjoy", Arrays.asList());

        chocolateCornflakes.setProductionSteps(Arrays.asList(meltChocolate, mixChocolateAndCornflakes, enjoy));
        facade.addRecipe(chocolateCornflakes);

        Recipe strawberryChocolateCake = new Recipe("Strawberry Chocolate Cake", "Very Delicious Cake");
        Ingredient allYearStrawberry = new Ingredient("All Year Strawberry", EnumSet.allOf(Month.class));
        ProductionStep makeCake = new ProductionStep("Make the Cake", "Make Mixture and bake cake",
                Arrays.asList(new Pair(seasonedChocolate, 300), new Pair(allYearStrawberry, 10)));
        strawberryChocolateCake.setProductionSteps(Arrays.asList(makeCake));
        facade.addRecipe(strawberryChocolateCake);
    }
}
