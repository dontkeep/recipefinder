package com.nicelydone.recipefinder.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nicelydone.recipefinder.databinding.IngredientItemBinding
import com.nicelydone.recipefinder.model.networks.Response.ExtendedIngredientsItem

class DetailAdapter: ListAdapter<ExtendedIngredientsItem, DetailAdapter.ViewHolder>(DIFF_CALLBACK) {
   class ViewHolder(private val binding: IngredientItemBinding): RecyclerView.ViewHolder(binding.root) {
      fun binding(item: ExtendedIngredientsItem){
         binding.ingredientNameTextView.text = item.originalName
         binding.ingredientAisleTextView.text = item.aisle
         binding.ingredientAmountTextView.text = item.measures?.metric?.amount.toString()
         binding.ingredientUnitTextView.text = item.measures?.metric?.unitLong
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val binding = IngredientItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return ViewHolder(binding)
   }

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val item = getItem(position)
      holder.binding(item)
   }
   companion object {
      val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ExtendedIngredientsItem>() {
         override fun areItemsTheSame(
            oldItem: ExtendedIngredientsItem,
            newItem: ExtendedIngredientsItem
         ): Boolean {
            return oldItem == newItem
         }

         override fun areContentsTheSame(
            oldItem: ExtendedIngredientsItem,
            newItem: ExtendedIngredientsItem
         ): Boolean {
            return oldItem == newItem
         }
      }
   }
}