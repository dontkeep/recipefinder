package com.nicelydone.recipefinder.model.networks.Local.Room.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.nicelydone.recipefinder.model.networks.Local.Entity.OwnRecipeEntity
import com.nicelydone.recipefinder.model.networks.Local.Entity.Relationship.RecipeandIngreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OwnRecipeDao {
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insertRecipe(ownRecipeEntity: OwnRecipeEntity): Long

   @Query("SELECT * FROM OwnRecipeEntity")
   fun getAllRecipe(): Flow<List<OwnRecipeEntity>>

   @Transaction
   @Query("SELECT * FROM OwnRecipeEntity")
   fun getAllWithIngredients(): Flow<List<RecipeandIngreEntity>>

   @Delete
   fun deleteRecipe(ownRecipeEntity: OwnRecipeEntity)

   @Update
   fun updateRecipe(ownRecipeEntity: OwnRecipeEntity)
}