package com.nicelydone.recipefinder.model.networks.Local.Entity.Relationship

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.nicelydone.recipefinder.model.networks.Local.Entity.IngreEntity
import com.nicelydone.recipefinder.model.networks.Local.Entity.OwnRecipeEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeandIngreEntity (
   @Embedded val dataRecipe: OwnRecipeEntity,
   @Relation(
      parentColumn = "id",
      entityColumn = "recipeId"
   )
   val ingredients: List<IngreEntity>
): Parcelable