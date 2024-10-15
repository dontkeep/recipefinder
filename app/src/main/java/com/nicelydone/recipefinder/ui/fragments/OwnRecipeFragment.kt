package com.nicelydone.recipefinder.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicelydone.recipefinder.R
import com.nicelydone.recipefinder.databinding.FragmentOwnRecipeBinding
import com.nicelydone.recipefinder.ui.activity.AddandEditRecipeActivity
import com.nicelydone.recipefinder.ui.adapter.RecipeAdapter
import com.nicelydone.recipefinder.viewmodel.OwnRecipeViewModel

class OwnRecipeFragment : Fragment() {
   private var _binding: FragmentOwnRecipeBinding? = null
   private val binding get() = _binding!!
   private val viewModel: OwnRecipeViewModel by viewModels()

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      _binding = FragmentOwnRecipeBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      //hide navigation bar
      val bottomNavigationView = (activity as? AppCompatActivity)?.findViewById<BottomNavigationView>(R.id.bottomNavigationView) // Replace with your BottomNavigationView's ID
      bottomNavigationView?.visibility = View.GONE


      setupRecyclerview()

      binding.fabGotoAddNew.setOnClickListener {
         val intent = Intent(requireContext(), AddandEditRecipeActivity::class.java)
         startActivity(intent)
      }

      viewModel.getAllOwnRecipe().observe(viewLifecycleOwner) { recipes ->
         (binding.rvRecipe.adapter as? RecipeAdapter)?.submitList(recipes)
      }
   }

   private fun setupRecyclerview() {
      binding.rvRecipe.apply {
         layoutManager = LinearLayoutManager(requireContext())
         adapter = RecipeAdapter(viewModel)
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

   companion object {

   }
}