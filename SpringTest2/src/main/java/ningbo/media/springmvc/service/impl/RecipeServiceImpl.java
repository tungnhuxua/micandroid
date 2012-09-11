package ningbo.media.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ningbo.media.springmvc.dao.RecipeDao;
import ningbo.media.springmvc.domain.Recipe;
import ningbo.media.springmvc.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeDao recipeDao;
	
	
	@Transactional
	public int insertRecipe(Recipe recipe) {
		return recipeDao.insertRecipe(recipe);
	}

	
	@Transactional
	public Recipe getRecipe(int recipeId) {
		return recipeDao.getRecipe(recipeId);
	}

	
	@Transactional
	public List<Recipe> getRecipes() {
		return recipeDao.getRecipes();
	}

	
	@Transactional
	public boolean updateRecipe(Recipe recipe) {
		return recipeDao.updateRecipe(recipe);
	}

	
	@Transactional
	public boolean deleteRecipe(Recipe recipeId) {
		return recipeDao.deleteRecipe(recipeId);
	}

}
