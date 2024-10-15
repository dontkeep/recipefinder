package com.nicelydone.recipefinder.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nicelydone.recipefinder.R
import com.nicelydone.recipefinder.databinding.FavouriteItemBinding
import com.nicelydone.recipefinder.model.helper.OnItemClickListener
import com.nicelydone.recipefinder.model.networks.Local.Entity.FavouriteRecipeEntity
import java.text.DecimalFormat

class FavouriteAdapter(private val listener: OnItemClickListener): ListAdapter<FavouriteRecipeEntity, FavouriteAdapter.FavouriteViewHolder>(DIFF_CALLBACK) {
   class FavouriteViewHolder(private val binding: FavouriteItemBinding): RecyclerView.ViewHolder(binding.root) {
      fun bind(recipe: FavouriteRecipeEntity){
         binding.recipeTitle.text = recipe.title
         binding.recipeReadyInMinutes.text = "Ready in: " + recipe.readyInMinutes + " minutes"


         val decimalFormat = DecimalFormat("#.##")
         val formattedScore = decimalFormat.format(recipe.rating.toDouble())
         binding.recipeRating.text = "Score: " + formattedScore

         Glide.with(binding.recipeImage.context).load(recipe.image).placeholder(R.drawable.placeholder).error(R.drawable.image_error).into(binding.recipeImage)
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
      val binding = FavouriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return FavouriteViewHolder(binding)
   }

   override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
      val recipe = getItem(position)
      holder.bind(recipe)

      holder.itemView.setOnClickListener {
         listener.onItemClick(recipe)
      }
   }

   companion object {
      val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavouriteRecipeEntity>() {
         override fun areItemsTheSame(
            oldItem: FavouriteRecipeEntity,
            newItem: FavouriteRecipeEntity
         ): Boolean {
            return oldItem == newItem
         }

         override fun areContentsTheSame(
            oldItem: FavouriteRecipeEntity,
            newItem: FavouriteRecipeEntity
         ): Boolean {
            return oldItem == newItem
         }
      }
   }
}