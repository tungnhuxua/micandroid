package ningbo.media.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ningbo.media.springmvc.dao.IngredientDao;
import ningbo.media.springmvc.domain.Ingredient;
import ningbo.media.springmvc.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientDao ingredientDao;
	
	
	@Transactional
	public int insertIngredient(Ingredient ingredient) {
		return ingredientDao.insertIngredient(ingredient);
	}

	
	@Transactional
	public Ingredient getIngredient(Integer ingredientId) {
		return ingredientDao.getIngredient(ingredientId);
	}

	
	@Transactional
	public boolean updateIngredient(Ingredient ingredient) {
		return ingredientDao.updateIngredient(ingredient);
	}

	
	@Transactional
	public boolean deleteIngredient(Ingredient ingredient) {
		return ingredientDao.deleteIngredient(ingredient);
	}

}
