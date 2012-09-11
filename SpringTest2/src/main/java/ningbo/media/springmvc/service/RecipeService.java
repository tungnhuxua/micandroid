package ningbo.media.springmvc.service;

import java.util.List;

import ningbo.media.springmvc.domain.Recipe;

public interface RecipeService {

	public int insertRecipe(Recipe recipe);
	
	public Recipe getRecipe(int recipeId);
	
	public List<Recipe> getRecipes();
	
	public boolean updateRecipe(Recipe recipe);
	
	public boolean deleteRecipe(Recipe recipeId);
	
}
