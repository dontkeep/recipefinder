package com.nicelydone.recipefinder.model.networks.Local.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nicelydone.recipefinder.model.networks.Local.Entity.FavouriteRecipeEntity


@Dao
interface RecipeDao {
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   fun insert(favouriteRecipeEntity: FavouriteRecipeEntity)

   @Update
   fun update(favouriteRecipeEntity: FavouriteRecipeEntity)

   @Delete
   fun delete(favouriteRecipeEntity: FavouriteRecipeEntity)

   @Query("SELECT * FROM FavouriteRecipeEntity")
   fun getAllFavouriteRecipe(): LiveData<List<FavouriteRecipeEntity>>

   @Query("SELECT * FROM FavouriteRecipeEntity WHERE id = :id")
   fun getFavouriteRecipeById(id: String): LiveData<FavouriteRecipeEntity?>
}