package com.nicelydone.recipefinder.model.networks.Local.Entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity()
@Parcelize
data class OwnRecipeEntity (
   @PrimaryKey(autoGenerate = true)
   @ColumnInfo(name = "id")
   val id: Int = 0,

   @ColumnInfo(name = "title")
   val title: String = "",

   @ColumnInfo(name = "readyInMinutes")
   val readyInMinutes: String = "",

   @ColumnInfo(name = "Description")
   val description: String = "",

): Parcelable