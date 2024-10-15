package com.nicelydone.recipefinder.model.networks.Local.Entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(foreignKeys = [ForeignKey(
   entity = OwnRecipeEntity::class,
   parentColumns = ["id"],
   childColumns = ["recipeId"],
   onDelete = ForeignKey.CASCADE
)]
)
@Parcelize
data class IngreEntity(
   @PrimaryKey(autoGenerate = true)
   @ColumnInfo(name = "id")
   val id: Int = 0,

   @ColumnInfo(name = "name")
   val name: String = "",

   @ColumnInfo(name = "amount")
   val amount: String = "",

   @ColumnInfo(name = "unit")
   val unit: String = "",

   @ColumnInfo(name = "recipeId") //for foreign key
   var recipeId: Int = 0
): Parcelable
