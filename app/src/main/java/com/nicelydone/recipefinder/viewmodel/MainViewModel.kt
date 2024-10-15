package com.nicelydone.recipefinder.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nicelydone.recipefinder.model.networks.ApiConfig
import com.nicelydone.recipefinder.model.networks.Response.ResponseSearchByIngreItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val application: Application): ViewModel() {
   private val _recipeList = MutableLiveData<List<ResponseSearchByIngreItem>?>()
   private val _isLoading = MutableLiveData<Boolean>()

   val isLoading: LiveData<Boolean> = _isLoading
   val recipeList: LiveData<List<ResponseSearchByIngreItem>?> = _recipeList

   init {
      _isLoading.value = false
   }

   // ingredients are coma separated
   fun fetchRecipes(apiKey: String, ingredients: String) {
      _isLoading.value = true
      Log.d("Running : ", "FetchRecipes")
      val client = ApiConfig.ApiConfig().getRecipesByIngredients(apiKey, ingredients)
      client.enqueue(object : Callback<List<ResponseSearchByIngreItem>>{

         // response handler untuk response
         override fun onResponse(
            call: Call<List<ResponseSearchByIngreItem>>,
            response: Response<List<ResponseSearchByIngreItem>>
         ) {
            // menyembunyikan loading indicator
            _isLoading.value = false
            Log.e(TAG, "Res: $response")

            if (response.isSuccessful) {
               val responseBody = response.body()
               if (responseBody != null) {
                  _recipeList.value = responseBody
               }
            } else {
               Log.e(TAG, "Error: ${response.message()}")
            }

         }
         // response handler saat terjadi gagal koneksi
         override fun onFailure(call: Call<List<ResponseSearchByIngreItem>>, t: Throwable) {
            // menyembunyikan loading indicator
            _isLoading.value = false
         }
      })
   }

   class Factory(private val application: Application): ViewModelProvider.Factory{
      @Suppress("UNCHECKED_CAST")
      override fun <T : ViewModel> create(modelClass: Class<T>): T =
         MainViewModel(application) as T
   }

   companion object {
      private val TAG = "MainViewModel"
   }
}
