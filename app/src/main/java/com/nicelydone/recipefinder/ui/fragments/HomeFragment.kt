package com.nicelydone.recipefinder.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nicelydone.recipefinder.BuildConfig
import com.nicelydone.recipefinder.R
import com.nicelydone.recipefinder.databinding.FragmentHomeBinding
import com.nicelydone.recipefinder.model.helper.NetworkUtils
import com.nicelydone.recipefinder.ui.activity.MainActivity
import com.nicelydone.recipefinder.ui.adapter.HomeAdapter
import com.nicelydone.recipefinder.viewmodel.MainViewModel


interface FavouriteFragmentListener {
   fun onFavouriteFragmentOpened()
}

class HomeFragment : Fragment() {

   private var listener: FavouriteFragmentListener? = null

   private lateinit var binding: FragmentHomeBinding

   private val mainViewModel: MainViewModel by viewModels<MainViewModel> {
      MainViewModel.Factory((activity as AppCompatActivity).application)
   }
   private val API_KEY = BuildConfig.API_KEY
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      // Inflate the layout for this fragment
      binding = FragmentHomeBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      val fragmentManager = parentFragmentManager

      val layoutManager = LinearLayoutManager(activity)
      binding.rvRecipe.layoutManager = layoutManager

      val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
      binding.rvRecipe.addItemDecoration(itemDecoration)

      binding.searchView.visibility = View.INVISIBLE

      binding.fabGotoSearch.setOnClickListener {
         goToSearchIfIsConnected()
      }

      binding.fabGotoOwnRecipes.setOnClickListener {
         fragmentManager.commit {
            replace(R.id.main_frame_container, OwnRecipeFragment(), HomeFragment::class.java.simpleName).addToBackStack(null)
         }
      }

      binding.fabGotoRecipes.setOnClickListener {
         listener?.onFavouriteFragmentOpened()

         fragmentManager.commit {
            replace(R.id.main_frame_container, FavouriteFragment(), HomeFragment::class.java.simpleName).addToBackStack(null)
         }
      }

      if (arguments != null) {
         val recipeSearch = arguments?.getString(EXTRA_RECIPE_SEARCH)
         mainViewModel.fetchRecipes(API_KEY, recipeSearch.toString())
      }

      mainViewModel.isLoading.observe(viewLifecycleOwner) {
         binding.loadingAnimationView.setAnimation(R.raw.loading)
         if (it) {
            binding.shortDesc.visibility = View.GONE
            binding.homeLogo.visibility = View.GONE
            binding.loadingAnimationView.visibility = View.VISIBLE
         } else {
            binding.homeLogo.visibility = View.VISIBLE
            binding.shortDesc.visibility = View.VISIBLE
            binding.loadingAnimationView.visibility = View.GONE
         }
      }

      mainViewModel.recipeList.observe(viewLifecycleOwner) { item ->
         if(item != null) {
            val adapter = HomeAdapter()
            adapter.submitList(item)
            binding.rvRecipe.adapter = adapter

            binding.shortDesc.visibility = View.GONE
            binding.homeLogo.visibility = View.GONE
         }
      }
   }

   fun goToSearchIfIsConnected() {
      if(NetworkUtils.isInternetAvailable(requireContext())) {
         binding.searchView.visibility = View.VISIBLE
         binding.searchView.setIconified(false)
         binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
               query?.let{
                  mainViewModel.fetchRecipes(API_KEY, query)
               }
               return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               return false
            }
         })
      } else {
         Snackbar.make(requireView(), "No Internet Connection", Snackbar.LENGTH_SHORT).show()
      }
   }

   override fun onAttach(context: Context) {
      super.onAttach(context)
      if (context is FavouriteFragmentListener) {
         listener = context
      } else {
         throw RuntimeException("$context must implement FavouriteFragmentListener")
      }
   }

   override fun onDetach() { // Clean up the listener
      super.onDetach()
      listener = null
   }

   override fun onResume() {
      super.onResume()
      (requireActivity() as? MainActivity)?.bottomNavigationView?.visibility = View.VISIBLE
   }

   companion object {
      const val EXTRA_RECIPE_SEARCH = "extra_recipe_search"
   }
}


