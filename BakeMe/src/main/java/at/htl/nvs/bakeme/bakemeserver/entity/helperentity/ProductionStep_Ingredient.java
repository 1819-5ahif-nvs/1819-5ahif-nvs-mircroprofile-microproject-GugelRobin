package at.htl.nvs.bakeme.bakemeserver.entity.helperentity;

import at.htl.nvs.bakeme.bakemeserver.entity.Ingredient;
import at.htl.nvs.bakeme.bakemeserver.entity.ProductionStep;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductionStep_Ingredient implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Ingredient ingredient;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private ProductionStep productionStep;

    private Integer amount;

    public ProductionStep_Ingredient(Ingredient ingredient, ProductionStep productionStep, int amount) {
        this.ingredient = ingredient;
        this.productionStep = productionStep;
        this.amount = amount;
    }

    public ProductionStep_Ingredient() {
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public ProductionStep getProductionStep() {
        return productionStep;
    }

    public void setProductionStep(ProductionStep productionStep) {
        this.productionStep = productionStep;
    }
}
