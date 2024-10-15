package com.nicelydone.recipefinder.model.networks.Local.Room.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nicelydone.recipefinder.model.networks.Local.Entity.IngreEntity

@Dao
interface IngredientsDao {
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   fun insertIngredients(ingredientsEntity: IngreEntity)

   @Delete
   fun deleteIngredients(ingredientsEntity: IngreEntity)

   @Query("DELETE FROM IngreEntity WHERE recipeId = :recipeId")
   suspend fun deleteIngredientsByRecipeId(recipeId: Int)
}