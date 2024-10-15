package com.nicelydone.recipefinder.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nicelydone.recipefinder.databinding.ActivityAddandEditRecipeBinding
import com.nicelydone.recipefinder.databinding.DialogAddIngredientBinding
import com.nicelydone.recipefinder.model.networks.Local.Entity.IngreEntity
import com.nicelydone.recipefinder.model.networks.Local.Entity.OwnRecipeEntity
import com.nicelydone.recipefinder.model.networks.Local.Entity.Relationship.RecipeandIngreEntity
import com.nicelydone.recipefinder.ui.adapter.OwnIngreAdapter
import com.nicelydone.recipefinder.viewmodel.OwnRecipeViewModel
import kotlinx.coroutines.launch

class AddandEditRecipeActivity : AppCompatActivity() {
   private lateinit var binding: ActivityAddandEditRecipeBinding
   private val viewModel: OwnRecipeViewModel by viewModels()
   private var recipe: RecipeandIngreEntity? = null
   private lateinit var ingredientsAdapter: OwnIngreAdapter

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityAddandEditRecipeBinding.inflate(layoutInflater)
      setContentView(binding.root)

      recipe = intent.getParcelableExtra("recipe")

      ingredientsAdapter = OwnIngreAdapter { ingredient ->
         deleteIngredient(ingredient) //Call the delete function
      }

      lifecycleScope.launch {
         viewModel.deletedIngredient.collect { deletedIngredient ->
            val currentList = ingredientsAdapter.currentList.toMutableList()
            currentList.remove(deletedIngredient)
            ingredientsAdapter.submitList(currentList)
         }
      }

      if (recipe != null) {
         binding.titleEditText.setText(recipe!!.dataRecipe.title)
         binding.readyInMinutesEditText.setText(recipe!!.dataRecipe.readyInMinutes)
         binding.descriptionEditText.setText(recipe!!.dataRecipe.description)
         binding.ingredientsRecyclerView.adapter = ingredientsAdapter
         ingredientsAdapter.submitList(recipe!!.ingredients)
      }

      binding.ingredientsRecyclerView.apply {
         layoutManager = LinearLayoutManager(this@AddandEditRecipeActivity)
         adapter = ingredientsAdapter
      }

      binding.addIngredientButton.setOnClickListener {
         showAddIngredientDialog(ingredientsAdapter)
      }

      binding.cancelButton.setOnClickListener {
         finish()
      }

      binding.saveRecipeButton.setOnClickListener {
         saveRecipe(ingredientsAdapter)
         finish()
      }

      binding.ingredientsRecyclerView.adapter = ingredientsAdapter
   }

   private fun showAddIngredientDialog(ingredientsAdapter: OwnIngreAdapter) {
      val dialogBinding = DialogAddIngredientBinding.inflate(layoutInflater)
      val dialog = MaterialAlertDialogBuilder(this)
         .setView(dialogBinding.root)
         .setPositiveButton("Add") { _, _->
            val name = dialogBinding.ingredientNameEditText.text.toString()
            val amount = dialogBinding.amountEditText.text.toString()
            val unit = dialogBinding.unitEditText.text.toString()
            ingredientsAdapter.addIngredient(IngreEntity(name = name, amount = amount, unit = unit))
         }
         .setNegativeButton("Cancel", null)
         .create()
      dialog.show()
   }

   private fun deleteIngredient(ingredient: IngreEntity) {
      viewModel.deleteIngredient(ingredient)
   }


   private fun saveRecipe(ingredientsAdapter: OwnIngreAdapter) {
      if (recipe != null) {
         val recipe = OwnRecipeEntity(
            id = recipe!!.dataRecipe.id,
            title = binding.titleEditText.text.toString(),
            readyInMinutes = binding.readyInMinutesEditText.text.toString(),
            description = binding.descriptionEditText.text.toString()
         )
         val updatedIngredients = ingredientsAdapter.currentList

         viewModel.updateRecipeWithIngredients(recipe, updatedIngredients)
      } else {
         val title = binding.titleEditText.text.toString()
         val readyInMinutes = binding.readyInMinutesEditText.text.toString()
         val description = binding.descriptionEditText.text.toString()
         val ingredients = ingredientsAdapter.currentList

         val recipe = OwnRecipeEntity(
            title = title,
            readyInMinutes = readyInMinutes,
            description = description
         )

         viewModel.insertRecipeWithIngredients(recipe, ingredients)
      }
   }
}