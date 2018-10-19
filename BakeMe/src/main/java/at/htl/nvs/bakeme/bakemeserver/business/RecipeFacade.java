package at.htl.nvs.bakeme.bakemeserver.business;

import at.htl.nvs.bakeme.bakemeserver.entity.Ingredient;
import at.htl.nvs.bakeme.bakemeserver.entity.ProductionStep;
import at.htl.nvs.bakeme.bakemeserver.entity.Recipe;
import at.htl.nvs.bakeme.bakemeserver.entity.helperentity.ProductionStep_Ingredient;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Stateless
public class RecipeFacade {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Recipe> getAllRecipes() {
        return entityManager.createNamedQuery("Recipe.getAll", Recipe.class).getResultList();
    }

    public Recipe getRecipeByName(String name) {
        TypedQuery<Recipe> query = entityManager.createNamedQuery("Recipe.getRecipeByName", Recipe.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    public void addRecipe(Recipe recipe) {;
        /*for (ProductionStep p: recipe.getProductionSteps()) {
            Collection<ProductionStep_Ingredient> tmp = p.getNeededIngredientsRaw();
            p.setNeededIngredients(new ArrayList<>());
            entityManager.persist(p);
            for (ProductionStep_Ingredient it : tmp) {
                entityManager.persist(it.getIngredient());
                entityManager.persist(it);
            }
            p.setNeededIngredientsRaw(tmp);
            entityManager.merge(p);
        }*/
        for (Ingredient  i: recipe.getNeededIngredients()) {
            addIngredient(i);
        }
        entityManager.persist(recipe);
    }

    public void addIngredient(Ingredient i){
        entityManager.persist(i);
    }

    public void addProductionStep(ProductionStep p) {
        entityManager.persist(p);
    }

    public void updateRecipe(Recipe recipe) {
        entityManager.merge(recipe);
    }
}
