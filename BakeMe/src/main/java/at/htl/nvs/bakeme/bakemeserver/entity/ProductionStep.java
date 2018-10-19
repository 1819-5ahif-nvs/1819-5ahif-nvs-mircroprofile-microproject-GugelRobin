package at.htl.nvs.bakeme.bakemeserver.entity;

import at.htl.nvs.bakeme.bakemeserver.entity.helperentity.Pair;
import at.htl.nvs.bakeme.bakemeserver.entity.helperentity.ProductionStep_Ingredient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
public class ProductionStep implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Recipe recipe;

    @OneToMany(mappedBy = "productionStep", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<ProductionStep_Ingredient> neededIngredients;

    public ProductionStep(String name, String description, Collection<Pair<Ingredient, Integer>> neededIngredients) {
        this.name = name;
        this.description = description;
        setNeededIngredients(neededIngredients);
    }

    public ProductionStep() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Pair<Ingredient, Integer>> getNeededIngredients() {
        return neededIngredients.stream().map(it -> new Pair<Ingredient, Integer>(it.getIngredient(), it.getAmount())).collect(Collectors.toList());
    }

    public void setNeededIngredients(Collection<Pair<Ingredient, Integer>> neededIngredients) {
        this.neededIngredients = neededIngredients
                .stream()
                .map(it -> new ProductionStep_Ingredient(
                        it.getKey(),
                        this,
                        it.getValue()))
                .collect(Collectors.toList());
    }

    public Collection<ProductionStep_Ingredient> getNeededIngredientsRaw() {
        return neededIngredients;
    }

    public void setNeededIngredientsRaw(Collection<ProductionStep_Ingredient> neededIngredients) {
        this.neededIngredients = neededIngredients;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
