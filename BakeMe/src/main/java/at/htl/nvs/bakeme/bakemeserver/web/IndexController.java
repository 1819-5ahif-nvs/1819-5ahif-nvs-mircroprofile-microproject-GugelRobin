package at.htl.nvs.bakeme.bakemeserver.web;

import at.htl.nvs.bakeme.bakemeserver.business.RecipeFacade;
import at.htl.nvs.bakeme.bakemeserver.entity.Recipe;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class IndexController implements Serializable {

    @Inject
    private RecipeFacade facade;


    private Date bakeDate;
    private List<Recipe> allRecipes;
    private List<Recipe> availableRecipes;
    private Recipe selectedRecipe;

    @PostConstruct
    private void init() {
        this.allRecipes = facade.getAllRecipes();
        availableRecipes = Collections.emptyList();
    }

    public void onDateSelect(SelectEvent event) {
        availableRecipes = bakeDate == null ?
                Collections.emptyList() :
                allRecipes.stream()
                        .filter(recipe ->
                                recipe.getAvailableMonths().contains(
                                        bakeDate.toInstant().atZone(ZoneId.systemDefault()).getMonth()))
                        .collect(Collectors.toList());
    }

    public void onRecipeSelect() {

    }

    public Date getBakeDate() {
        return bakeDate;
    }

    public void setBakeDate(Date bakeDate) {
        this.bakeDate = bakeDate;
    }

    public List<Recipe> getAvailableRecipes() {
        return availableRecipes;
    }

    public void setAvailableRecipes(List<Recipe> availableRecipes) {
        this.availableRecipes = availableRecipes;
    }

    public Recipe getSelectedRecipe() {
        return selectedRecipe;
    }

    public void setSelectedRecipe(Recipe selectedRecipe) {
        this.selectedRecipe = selectedRecipe;
    }
}
