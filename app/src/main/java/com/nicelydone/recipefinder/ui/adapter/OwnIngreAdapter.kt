package com.nicelydone.recipefinder.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nicelydone.recipefinder.databinding.OwnIngreItemBinding
import com.nicelydone.recipefinder.model.networks.Local.Entity.IngreEntity

class OwnIngreAdapter(private val onDeleteClickListener: (IngreEntity) -> Unit): ListAdapter<IngreEntity, OwnIngreAdapter.ViewHolder>(DIFF_CALLBACK) {

   class ViewHolder(private val binding: OwnIngreItemBinding): RecyclerView.ViewHolder(binding.root) {
      fun bind(item: IngreEntity, onDeleteClickListener: (IngreEntity) -> Unit) {
         binding.ingredientNameTextView.text = item.name
         binding.ingredientAmountTextView.text = item.amount
         binding.ingredientUnitTextView.text = item.unit
         binding.deleteIngredientButton.setOnClickListener {
            Log.d("Delete Button", "Button Clicked")
            onDeleteClickListener(item)
         }
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OwnIngreAdapter.ViewHolder {
      val binding = OwnIngreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return ViewHolder(binding)
   }

   override fun onBindViewHolder(holder: OwnIngreAdapter.ViewHolder, position: Int) {
      val item = getItem(position)
      holder.bind(item, onDeleteClickListener)
   }

   fun addIngredient(ingredient: IngreEntity) {
      val currentList = currentList.toMutableList()
      currentList.add(ingredient)
      submitList(currentList)
   }

   companion object {
      val DIFF_CALLBACK = object : DiffUtil.ItemCallback<IngreEntity>() {
         override fun areItemsTheSame(
            oldItem: IngreEntity,
            newItem: IngreEntity
         ): Boolean {
            return oldItem == newItem
         }

         override fun areContentsTheSame(
            oldItem: IngreEntity,
            newItem: IngreEntity
         ): Boolean {
            return oldItem == newItem
         }
      }
   }
}