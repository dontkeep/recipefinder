package com.nicelydone.recipefinder.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nicelydone.recipefinder.BuildConfig
import com.nicelydone.recipefinder.R
import com.nicelydone.recipefinder.databinding.ActivityDetailBinding
import com.nicelydone.recipefinder.model.RecipesData
import com.nicelydone.recipefinder.model.networks.Local.Entity.FavouriteRecipeEntity
import com.nicelydone.recipefinder.ui.adapter.DetailAdapter
import com.nicelydone.recipefinder.viewmodel.DetailViewModel
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
   private lateinit var binding: ActivityDetailBinding
   private val detailViewModel: DetailViewModel by viewModels { DetailViewModel.Factory(application) }
   private val API_KEY = BuildConfig.API_KEY
   private var recipeFavData: FavouriteRecipeEntity? = null

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityDetailBinding.inflate(layoutInflater)
      setContentView(binding.root)

      val data = intent.getParcelableExtra<RecipesData>(EXTRA_RECIPE)
      val id = data?.id.toString()

      //initialize recyclerview
      val layoutManager = LinearLayoutManager(this)
      binding.usedIngredientsRecyclerView.layoutManager = layoutManager


      detailViewModel.getFavById(id).observe(this) { favouriteEntity ->
         recipeFavData = favouriteEntity
         if (favouriteEntity != null) {
            binding.saveFab.setImageResource(R.drawable.baseline_favorite_24)
         }else {
            binding.saveFab.setImageResource(R.drawable.baseline_favorite_border_24)
         }
      }

      binding.saveFab.setOnClickListener {
         recipeFavData?.let {
            detailViewModel.deleteFav(it)
         } ?: run {
            detailViewModel.result.value?.let { apiResponse ->
               detailViewModel.insert(
                  FavouriteRecipeEntity(
                     apiResponse.id.toString(),
                     apiResponse.title.toString(),
                     apiResponse.readyInMinutes.toString(),
                     apiResponse.image.toString(),
                     apiResponse.spoonacularScore.toString()
                  )
               )
            }
         }
      }

      detailViewModel.getDetailRecipe(API_KEY, id, false, false, false)
      detailViewModel.isLoading.observe(this) {
         binding.loadingAnimationView.setAnimation(R.raw.loading)
         if (it) {
            binding.loadingAnimationView.visibility = View.VISIBLE
            binding.saveFab.visibility = View.INVISIBLE
            binding.usedIngredientsRecyclerView.visibility = View.GONE
            binding.contentLayout.visibility = View.GONE
            binding.saveFab.visibility = View.GONE
         } else {
            binding.loadingAnimationView.visibility = View.GONE
            binding.saveFab.visibility = View.VISIBLE
            binding.usedIngredientsRecyclerView.visibility = View.VISIBLE
            binding.contentLayout.visibility = View.VISIBLE
            binding.collapsingToolbar.visibility = View.VISIBLE
         }
      }

      detailViewModel.result.observe(this){ item ->
         if (item != null) {
            binding.recipeDetailNameTextView.text = item.title
            binding.recipeDetailReadyInTextView.text = item.readyInMinutes.toString() + " minutes"
            binding.recipeDetailServingsTextView.text = item.servings.toString() + " People"
            binding.recipeDetailSummaryTextView.text = item.summary?.let {
               HtmlCompat.fromHtml(
                  it,
                  HtmlCompat.FROM_HTML_MODE_LEGACY
               )
            }
            val decimalFormat = DecimalFormat("#.##")
            val formattedScore = decimalFormat.format(item.spoonacularScore)
            binding.recipeDetailSpoonacularScoreTextView.text = formattedScore
            Glide.with(binding.recipeDetailImageView.context).load(item.image).into(binding.recipeDetailImageView)

            val detailAdapter = DetailAdapter()
            detailAdapter.submitList(item.extendedIngredients)
            binding.usedIngredientsRecyclerView.adapter = detailAdapter

         }
      }
   }

   companion object {
      val EXTRA_RECIPE = ""
   }
}