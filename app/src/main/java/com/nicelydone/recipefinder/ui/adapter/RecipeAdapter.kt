package com.nicelydone.recipefinder.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nicelydone.recipefinder.databinding.OwnRecipeLayoutBinding
import com.nicelydone.recipefinder.model.networks.Local.Entity.Relationship.RecipeandIngreEntity
import com.nicelydone.recipefinder.ui.activity.AddandEditRecipeActivity
import com.nicelydone.recipefinder.viewmodel.OwnRecipeViewModel

class RecipeAdapter(private val viewModel: OwnRecipeViewModel): ListAdapter<RecipeandIngreEntity, RecipeAdapter.ViewHolder>(DIFF_CALLBACK) {
   class ViewHolder(private val binding: OwnRecipeLayoutBinding): RecyclerView.ViewHolder(binding.root) {
      fun bind(item: RecipeandIngreEntity, viewModel: OwnRecipeViewModel) {
         binding.recipeTitleTextView.text = item.dataRecipe.title
         binding.recipeDescriptionTextView.text = item.dataRecipe.description

         binding.root.setOnClickListener{
            val intent = Intent(itemView.context, AddandEditRecipeActivity::class.java)
            intent.putExtra("recipe", item)
            itemView.context.startActivity(intent)
         }

         binding.editButton.setOnClickListener {
            val intent = Intent(itemView.context, AddandEditRecipeActivity::class.java)
            intent.putExtra("recipe", item)
            itemView.context.startActivity(intent)
         }

         binding.deleteButton.setOnClickListener {
            viewModel.deleteRecipe(item.dataRecipe)
         }
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val binding = OwnRecipeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return ViewHolder(binding)
   }

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val item = getItem(position)
      holder.bind(item, viewModel)
   }

   companion object {
      val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecipeandIngreEntity>() {
         override fun areItemsTheSame(
            oldItem: RecipeandIngreEntity,
            newItem: RecipeandIngreEntity
         ): Boolean {
            return oldItem == newItem
         }

         override fun areContentsTheSame(
            oldItem: RecipeandIngreEntity,
            newItem: RecipeandIngreEntity
         ): Boolean {
            return oldItem == newItem
         }
      }
   }
}