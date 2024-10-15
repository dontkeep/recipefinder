package com.nicelydone.recipefinder.model.networks

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.nicelydone.recipefinder.BuildConfig

class ApiConfig {
   companion object {
      val base_url = BuildConfig.BASE_URL
      fun ApiConfig(): ApiService {
         val loggingInterceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
         )

         val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

         val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

         return retrofit.create(ApiService::class.java)
      }

   }
}