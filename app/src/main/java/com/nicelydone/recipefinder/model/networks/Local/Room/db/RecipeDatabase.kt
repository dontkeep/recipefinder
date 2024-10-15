package com.nicelydone.recipefinder.model.networks.Local.Room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nicelydone.recipefinder.model.networks.Local.Entity.FavouriteRecipeEntity
import com.nicelydone.recipefinder.model.networks.Local.Room.Dao.RecipeDao
import com.nicelydone.recipefinder.model.networks.Local.Entity.OwnRecipeEntity
import com.nicelydone.recipefinder.model.networks.Local.Entity.IngreEntity
import com.nicelydone.recipefinder.model.networks.Local.Room.Dao.IngredientsDao
import com.nicelydone.recipefinder.model.networks.Local.Room.Dao.OwnRecipeDao

@Database(entities = [FavouriteRecipeEntity::class, OwnRecipeEntity::class, IngreEntity::class], version = 4)
abstract class RecipeDatabase: RoomDatabase() {
   abstract fun recipesDao(): RecipeDao
   abstract fun ingredientsDao(): IngredientsDao
   abstract fun ownRecipeDao(): OwnRecipeDao
   companion object {
      @Volatile
      private var INSTANCE: RecipeDatabase? = null
      @JvmStatic
      fun getDatabase(context: Context): RecipeDatabase {
         if (INSTANCE == null){
            synchronized(RecipeDatabase::class.java){
               INSTANCE = Room.databaseBuilder(context.applicationContext,
                  RecipeDatabase::class.java, "recipe_database")
                  .addMigrations(MIGRATION_2_3)
                  .addMigrations(MIGRATION_3_4)
                  .build()
            }
         }
         return INSTANCE as RecipeDatabase
      }
      val MIGRATION_2_3 = object : Migration(2, 3) {
         override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("DROP TABLE FavouriteRecipeEntity")

            // Recreate the table with the correct schema
            database.execSQL(
               """
            CREATE TABLE FavouriteRecipeEntity (
                id TEXT NOT NULL PRIMARY KEY,
                title TEXT NOT NULL,
                readyInMinutes TEXT NOT NULL,
                image TEXT NOT NULL,
                rating TEXT NOT NULL
            )
            """
            )
         }
      }
      val MIGRATION_3_4 = object : Migration(3, 4) {
         override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
               """CREATE TABLE OwnRecipeEntity (
                    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    title TEXT NOT NULL,
                    readyInMinutes TEXT NOT NULL,
                    Description TEXT NOT NULL
                )"""
            )
            database.execSQL(
               """
            CREATE TABLE IngreEntity (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                recipeId INTEGER NOT NULL,
                name TEXT NOT NULL,
                amount TEXT NOT NULL,
                unit TEXT NOT NULL,
                FOREIGN KEY (recipeId) REFERENCES OwnRecipeEntity(id) ON DELETE CASCADE
            )
            """
            )
         }
      }
   }
}