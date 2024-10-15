package com.nicelydone.recipefinder.model.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


object NetworkUtils {
   fun isInternetAvailable(context: Context): Boolean {
      val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val network = connectivityManager.activeNetwork ?: return false // No active network
      val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false // No capabilities

      return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
          networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
   }
}
