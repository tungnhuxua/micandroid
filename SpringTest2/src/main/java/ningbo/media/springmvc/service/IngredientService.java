package ningbo.media.springmvc.service;

import ningbo.media.springmvc.domain.Ingredient;

public interface IngredientService {

	public int insertIngredient(Ingredient ingredient);

	public Ingredient getIngredient(Integer ingredientId);

	public boolean updateIngredient(Ingredient ingredient);

	public boolean deleteIngredient(Ingredient ingredient);
	
}
