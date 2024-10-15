package com.nicelydone.recipefinder.model.networks.Local

import android.app.Application
import androidx.lifecycle.LiveData
import com.nicelydone.recipefinder.model.networks.Local.Entity.FavouriteRecipeEntity
import com.nicelydone.recipefinder.model.networks.Local.Room.Dao.RecipeDao
import com.nicelydone.recipefinder.model.networks.Local.Room.db.RecipeDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RecipeRepository(application: Application) {
   private val favouriteRecipeDao: RecipeDao
   private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

   init {
      val db = RecipeDatabase.getDatabase(application)
      favouriteRecipeDao = db.recipesDao()
   }

   fun getAll(): LiveData<List<FavouriteRecipeEntity>> = favouriteRecipeDao.getAllFavouriteRecipe()

   fun getById(id: String): LiveData<FavouriteRecipeEntity?> = favouriteRecipeDao.getFavouriteRecipeById(id)

   fun insert(favouriteRecipeEntity: FavouriteRecipeEntity) = executorService.execute { favouriteRecipeDao.insert(favouriteRecipeEntity) }

   fun delete(favouriteRecipeEntity: FavouriteRecipeEntity) = executorService.execute { favouriteRecipeDao.delete(favouriteRecipeEntity) }
}