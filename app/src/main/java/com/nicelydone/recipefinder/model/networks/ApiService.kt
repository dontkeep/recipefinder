package com.nicelydone.recipefinder.model.networks

import com.nicelydone.recipefinder.model.networks.Response.ResponseDetailRecipe
import com.nicelydone.recipefinder.model.networks.Response.ResponseSearchByIngreItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
   @GET("recipes/findByIngredients")
   fun getRecipesByIngredients(@Query("apiKey") apiKey: String, @Query("ingredients") ingredients: String)
      : Call<List<ResponseSearchByIngreItem>>

   @GET("recipes/{id}/information")
   fun getRecipesById(@Path("id") id: String, @Query("apiKey") apiKey: String, @Query("includeNutrition") includeNutrition: Boolean, @Query("addWinePairing") addWinePairing: Boolean, @Query("addTasteData") addTasteData: Boolean)
      : Call<ResponseDetailRecipe>
}