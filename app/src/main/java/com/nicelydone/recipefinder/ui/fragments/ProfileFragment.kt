package com.nicelydone.recipefinder.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicelydone.recipefinder.R
import com.nicelydone.recipefinder.databinding.FragmentProfileBinding
import com.nicelydone.recipefinder.model.Profile
import com.nicelydone.recipefinder.ui.adapter.ProfileAdapter

class ProfileFragment : Fragment() {
   private lateinit var binding: FragmentProfileBinding
   private val profiles = listOf(
      Profile("Moch Reki Hadiyanto", "211351083", R.drawable.foto_reki, "RexBaee"),
      Profile("Nurlisa Widyaningsih", "211351108", R.drawable.foto_ica, "NurlisaWidya"),
      Profile("Syams Muhammad H", "211351144", R.drawable.foto_syam, "SyamsMH")
   )
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      binding = FragmentProfileBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      val adapter = ProfileAdapter()
      val layoutManager = LinearLayoutManager(activity)
      val layoutDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)

      binding.rvProfile.layoutManager = layoutManager
      binding.rvProfile.addItemDecoration(layoutDecoration)
      adapter.submitList(profiles)
      binding.rvProfile.adapter = adapter
   }
}