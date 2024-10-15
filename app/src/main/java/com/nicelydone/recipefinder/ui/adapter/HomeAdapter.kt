package com.nicelydone.recipefinder.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nicelydone.recipefinder.databinding.RecipeItemBinding
import com.nicelydone.recipefinder.model.RecipesData
import com.nicelydone.recipefinder.model.networks.Response.ResponseSearchByIngreItem
import com.nicelydone.recipefinder.ui.activity.DetailActivity

class HomeAdapter: ListAdapter<ResponseSearchByIngreItem, HomeAdapter.ViewHolder>(DIFF_CALLBACK) {
   class ViewHolder(private val binding: RecipeItemBinding): RecyclerView.ViewHolder(binding.root) {
      fun bind(item: ResponseSearchByIngreItem) {
         binding.recipeTitleTextView.text = item.title
         binding.usedIngredientsCountTextView.text = item.usedIngredientCount.toString()
         binding.missedIngredientsCountTextView.text = item.missedIngredientCount.toString()
         Glide.with(binding.recipeImageView.context).load(item.image).into(binding.recipeImageView)
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val binding = RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return ViewHolder(binding)
   }

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val item = getItem(position)
      holder.bind(item)

      val data = item.id?.let {
         RecipesData(
            it,
         )
      }
      holder.itemView.setOnClickListener {
         val intent = Intent(holder.itemView.context, DetailActivity::class.java)
         intent.putExtra(DetailActivity.EXTRA_RECIPE, data)
         holder.itemView.context.startActivity(intent)
      }
   }

   companion object {
      val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResponseSearchByIngreItem>() {
         override fun areItemsTheSame(
            oldItem: ResponseSearchByIngreItem,
            newItem: ResponseSearchByIngreItem
         ): Boolean {
            return oldItem == newItem
         }

         override fun areContentsTheSame(
            oldItem: ResponseSearchByIngreItem,
            newItem: ResponseSearchByIngreItem
         ): Boolean {
            return oldItem == newItem
         }
      }
   }
}