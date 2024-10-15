package com.nicelydone.recipefinder.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nicelydone.recipefinder.model.networks.ApiConfig
import com.nicelydone.recipefinder.model.networks.Local.Entity.FavouriteRecipeEntity
import com.nicelydone.recipefinder.model.networks.Local.RecipeRepository
import com.nicelydone.recipefinder.model.networks.Response.ResponseDetailRecipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val application: Application): ViewModel() {
   private val _isLoading = MutableLiveData<Boolean>()
   val isLoading: LiveData<Boolean> = _isLoading

   private val _result = MutableLiveData<ResponseDetailRecipe?>()
   val result: LiveData<ResponseDetailRecipe?> = _result

   private val mRecipeRepo: RecipeRepository = RecipeRepository(application)

   fun insert(recipe: FavouriteRecipeEntity) = mRecipeRepo.insert(recipe)

   fun deleteFav(recipe: FavouriteRecipeEntity) = mRecipeRepo.delete(recipe)

   fun getAllFav() = mRecipeRepo.getAll()

   fun getFavById(id: String) = mRecipeRepo.getById(id)

   init {
      _isLoading.value = false
   }

   fun getDetailRecipe(apiKey: String, id: String, includeNutrition: Boolean, addWinePairing: Boolean, addTasteData: Boolean){
      _isLoading.value = true
      val client = ApiConfig.ApiConfig().getRecipesById(id, apiKey, includeNutrition, addWinePairing, addTasteData)
      client.enqueue(object : Callback<ResponseDetailRecipe> {
         override fun onResponse(
            call: Call<ResponseDetailRecipe>,
            response: Response<ResponseDetailRecipe>
         ) {
            _isLoading.value = false
            if (response.isSuccessful) {
               val responseBody = response.body()
               if (responseBody != null) {
                  _result.value = responseBody
               }else {
                  Log.e(TAG, "Error: ${response.message()}")
               }
            }
         }
         override fun onFailure(call: Call<ResponseDetailRecipe>, t: Throwable) {
            _isLoading.value = false
            Log.e(TAG, "Error: ${t.message}")
         }
      })
   }
   companion object {
      private val TAG = "DetailViewModel"
   }

   class Factory(private val application: Application) : ViewModelProvider.Factory {
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
         if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(application) as T
         }
         throw IllegalArgumentException("Unknown ViewModel class")
      }
   }
}
