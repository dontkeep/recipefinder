package com.nicelydone.recipefinder.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nicelydone.recipefinder.model.networks.Local.Entity.IngreEntity
import com.nicelydone.recipefinder.model.networks.Local.Entity.OwnRecipeEntity
import com.nicelydone.recipefinder.model.networks.Local.Entity.Relationship.RecipeandIngreEntity
import com.nicelydone.recipefinder.model.networks.Local.OwnRecipeRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OwnRecipeViewModel(application: Application): AndroidViewModel(application) {
   private val repository = OwnRecipeRepository(application)
   private val _deletedIngredient = MutableSharedFlow<IngreEntity>()
   val deletedIngredient = _deletedIngredient.asSharedFlow()


   fun insertRecipeWithIngredients(recipe: OwnRecipeEntity, ingredients: List<IngreEntity>) {
      viewModelScope.launch {
         val recipeId = repository.insertRecipe(recipe)
         ingredients.forEach { ingredient ->
            ingredient.recipeId = recipeId.toInt() // Set the recipeId for each ingredient
            repository.insertIngredient(ingredient)
         }
      }
   }

   fun updateRecipe(recipe: OwnRecipeEntity) {
      viewModelScope.launch {
         repository.updateRecipe(recipe)
      }
   }

   fun getAllOwnRecipe(): LiveData<List<RecipeandIngreEntity>> {
      return repository.getAllOwnRecipe().asLiveData()
   }

   fun deleteIngredient(ingredient: IngreEntity) {
      viewModelScope.launch {
         repository.deleteIngredient(ingredient)
         _deletedIngredient.emit(ingredient)
      }
   }

   fun updateRecipeWithIngredients(recipe: OwnRecipeEntity, ingredients: List<IngreEntity>) {
      viewModelScope.launch {
         repository.updateRecipeWithIngredients(recipe, ingredients)
      }
   }

   fun deleteRecipe(recipe: OwnRecipeEntity) {
      viewModelScope.launch {
         repository.deleteRecipe(recipe)
      }
   }
}