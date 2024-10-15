package com.nicelydone.recipefinder.model.networks.Local

import android.app.Application
import com.nicelydone.recipefinder.model.networks.Local.Entity.IngreEntity
import com.nicelydone.recipefinder.model.networks.Local.Entity.OwnRecipeEntity
import com.nicelydone.recipefinder.model.networks.Local.Entity.Relationship.RecipeandIngreEntity
import com.nicelydone.recipefinder.model.networks.Local.Room.db.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class OwnRecipeRepository(application: Application) {
   private val ownRecipeDao = RecipeDatabase.getDatabase(application).ownRecipeDao()
   private val ingredientsDao = RecipeDatabase.getDatabase(application).ingredientsDao()

   fun getAllOwnRecipe(): Flow<List<RecipeandIngreEntity>> =
      ownRecipeDao.getAllWithIngredients()

   suspend fun insertRecipe(recipe: OwnRecipeEntity): Long {
      return withContext(Dispatchers.IO) {
         ownRecipeDao.insertRecipe(recipe)
      }
   }

   suspend fun insertIngredient(ingredient: IngreEntity) {
      withContext(Dispatchers.IO) {
         ingredientsDao.insertIngredients(ingredient)
      }
   }

   suspend fun deleteRecipe(recipe: OwnRecipeEntity) {
      withContext(Dispatchers.IO) {
         ownRecipeDao.deleteRecipe(recipe)
      }
   }

   suspend fun deleteIngredient(ingredient: IngreEntity) {
      withContext(Dispatchers.IO) {
         ingredientsDao.deleteIngredients(ingredient)
      }
   }

   suspend fun updateRecipeWithIngredients(recipe: OwnRecipeEntity, ingredients: List<IngreEntity>) {
      withContext(Dispatchers.IO) {
         ownRecipeDao.updateRecipe(recipe)
         ingredientsDao.deleteIngredientsByRecipeId(recipe.id)
         ingredients.forEach { ingredient ->
            ingredient.recipeId = recipe.id
            ingredientsDao.insertIngredients(ingredient)
         }
      }
   }

   suspend fun updateRecipe(recipe: OwnRecipeEntity) {
      withContext(Dispatchers.IO) {
         ownRecipeDao.updateRecipe(recipe)
      }
   }
}