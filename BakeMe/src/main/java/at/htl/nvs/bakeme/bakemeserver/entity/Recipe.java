package at.htl.nvs.bakeme.bakemeserver.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@NamedQueries({
        @NamedQuery(name = "Recipe.getAll",
                query = "select v from Recipe v"),
        @NamedQuery(name = "Recipe.getRecipeByName",
                query = "select v from Recipe v where v.name = :name")
})
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn(name = "PRODUCTION_STEP_ORDER")
    private List<ProductionStep> productionSteps;

    public Recipe() {

    }

    public Recipe(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public Recipe(String name, String description, List<ProductionStep> productionSteps) {
        this(name, description);
        this.productionSteps = productionSteps;
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

    public List<ProductionStep> getProductionSteps() {
        return productionSteps;
    }

    public void setProductionSteps(List<ProductionStep> productionSteps) {
        for (ProductionStep p: productionSteps) {
            p.setRecipe(this);
        }
        this.productionSteps = productionSteps;
    }

    public List<Ingredient> getNeededIngredients() {
        return  productionSteps.stream()
                .map(it -> it.getNeededIngredients().stream()
                        .map(it2 -> it2.getKey())
                        .collect(Collectors.toList()))
                .collect(() -> new ArrayList<>(), (result, in) -> result.addAll(in), (result, in) -> result.addAll(in));
    }

    public EnumSet<Month> getAvailableMonths() {
        return getNeededIngredients().stream()
                .map(it -> it.getAvailableMonths())
                .collect(() -> EnumSet.allOf(Month.class), (result, in) ->  result.retainAll(in), (result, in) -> result.retainAll(in));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
