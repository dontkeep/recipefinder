package com.nicelydone.recipefinder.model.networks.Local.Entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavouriteRecipeEntity(
   @field:ColumnInfo(name = "id")
   @field:PrimaryKey
   val id: String,

   @field:ColumnInfo(name = "title")
   val title: String,

   @field:ColumnInfo(name = "readyInMinutes")
   val readyInMinutes: String,

   @field:ColumnInfo(name = "image")
   val image: String,

   @field:ColumnInfo(name = "rating")
   val rating: String
): Parcelable
