package at.htl.nvs.bakeme.bakemeserver.web.converter;


import at.htl.nvs.bakeme.bakemeserver.business.RecipeFacade;
import at.htl.nvs.bakeme.bakemeserver.entity.Recipe;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class RecipeConverter implements Converter {

    @Inject
    private RecipeFacade facade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return facade.getRecipeByName(name);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || !(value instanceof Recipe)) {
            return "";
        }
        return ((Recipe)value).getName();
    }
}
