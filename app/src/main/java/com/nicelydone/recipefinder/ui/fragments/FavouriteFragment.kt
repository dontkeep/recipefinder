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
import com.google.android.material.snackbar.Snackbar
import com.nicelydone.recipefinder.databinding.FragmentFavouriteBinding
import com.nicelydone.recipefinder.model.RecipesData
import com.nicelydone.recipefinder.model.helper.NetworkUtils
import com.nicelydone.recipefinder.model.helper.OnItemClickListener
import com.nicelydone.recipefinder.model.networks.Local.Entity.FavouriteRecipeEntity
import com.nicelydone.recipefinder.ui.activity.DetailActivity
import com.nicelydone.recipefinder.ui.adapter.FavouriteAdapter
import com.nicelydone.recipefinder.viewmodel.DetailViewModel

class FavouriteFragment : Fragment(), OnItemClickListener {
   private lateinit var binding: FragmentFavouriteBinding
   private val detailViewModel: DetailViewModel by viewModels<DetailViewModel> {
      DetailViewModel.Factory((activity as AppCompatActivity).application)
   }
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      // Inflate the layout for this fragment
      binding = FragmentFavouriteBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      val layoutManager = LinearLayoutManager(activity)
      binding.rvFav.layoutManager = layoutManager
      val adapter = FavouriteAdapter(this)

      detailViewModel.getAllFav().observe(viewLifecycleOwner) { item ->
         if (item != null) {
            adapter.submitList(item)
            binding.rvFav.adapter = adapter
         }
      }
   }

   override fun onItemClick(item: FavouriteRecipeEntity) {
      if(NetworkUtils.isInternetAvailable(requireContext())) {
         val data = item.id.let {
            RecipesData(
               it.toInt()
            )
         }
         val intent = Intent(requireContext(), DetailActivity::class.java)
         intent.putExtra(DetailActivity.EXTRA_RECIPE, data)
         startActivity(intent)
      } else {
         Snackbar.make(requireView(), "No Internet Connection", Snackbar.LENGTH_SHORT).show()
      }
   }
}