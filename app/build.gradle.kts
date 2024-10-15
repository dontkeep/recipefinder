plugins {
   alias(libs.plugins.android.application)
   alias(libs.plugins.jetbrains.kotlin.android)
   id("kotlin-kapt")
   id("kotlin-parcelize")
}

android {
   namespace = "com.nicelydone.recipefinder"
   compileSdk = 34

   defaultConfig {
      applicationId = "com.nicelydone.recipefinder"
      minSdk = 24
      targetSdk = 34
      versionCode = 1
      versionName = "1.0"

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
   }

   buildFeatures {
      viewBinding = true
      buildConfig = true
   }

   buildTypes {
      release {
         isMinifyEnabled = false
         proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
         )
         buildConfigField("String", "BASE_URL", "\"https://api.spoonacular.com/\"")
         buildConfigField("String", "API_KEY", "\"84d24cd89a154f66a6ccba34f55d9a75\"")
      }
      debug {

         buildConfigField("String", "BASE_URL", "\"https://api.spoonacular.com/\"")
         buildConfigField("String", "API_KEY", "\"84d24cd89a154f66a6ccba34f55d9a75\"")
      }
   }
   compileOptions {
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
   }
   kotlinOptions {
      jvmTarget = "1.8"
   }
}

dependencies {
   implementation(libs.androidx.core.ktx)
   implementation(libs.androidx.appcompat)
   implementation(libs.material)
   implementation(libs.androidx.activity)
   implementation(libs.androidx.constraintlayout)
   testImplementation(libs.junit)
   androidTestImplementation(libs.androidx.junit)
   androidTestImplementation(libs.androidx.espresso.core)
   implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
   implementation("com.github.bumptech.glide:glide:4.16.0")
   implementation("androidx.viewpager2:viewpager2:1.0.0")
   implementation("com.google.android.exoplayer:extension-okhttp:2.19.1")
   implementation("com.google.android.exoplayer:exoplayer-core:2.19.1")
   implementation("com.squareup.retrofit2:retrofit:2.9.0")
   implementation("com.squareup.retrofit2:converter-gson:2.9.0")
   implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
   implementation("androidx.room:room-runtime:2.6.1")
   implementation("androidx.datastore:datastore-preferences:1.0.0")

   implementation("androidx.fragment:fragment-ktx:1.6.2")

   implementation("de.hdodenhof:circleimageview:3.1.0")

   implementation("com.airbnb.android:lottie:6.4.1")

   kapt("androidx.room:room-compiler:2.6.1")

   implementation("androidx.room:room-ktx:2.6.1")
}
