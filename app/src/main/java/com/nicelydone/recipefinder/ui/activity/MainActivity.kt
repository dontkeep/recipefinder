package com.nicelydone.recipefinder.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicelydone.recipefinder.R
import com.nicelydone.recipefinder.databinding.ActivityMainBinding
import com.nicelydone.recipefinder.ui.fragments.FavouriteFragmentListener
import com.nicelydone.recipefinder.ui.fragments.HomeFragment
import com.nicelydone.recipefinder.ui.fragments.ProfileFragment

class MainActivity : AppCompatActivity(), FavouriteFragmentListener {
   private lateinit var binding: ActivityMainBinding
   lateinit var bottomNavigationView: BottomNavigationView

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)

      val fragmentManager = supportFragmentManager
      val homeFragment = HomeFragment()
      val fragment = fragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

      if(fragment !is HomeFragment) {
         fragmentManager.commit {
            replace(R.id.main_frame_container, homeFragment, HomeFragment::class.java.simpleName)
         }
      }
      bottomNavigationView= binding.bottomNavigationView

      bottomNavigationView.setOnNavigationItemSelectedListener { item ->
         when (item.itemId) {
            R.id.navigation_home -> {
               supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE) // Clear back stack
               replaceFragment(HomeFragment(), false)
               true
            }
            R.id.navigation_profile-> {
               replaceFragment(ProfileFragment(), false) // Add to back stack
               true
            }
            else -> false
         }
      }
      val handler = Handler(Looper.getMainLooper())
      supportFragmentManager.addOnBackStackChangedListener {
         val currentFragment = supportFragmentManager.findFragmentById(R.id.main_frame_container)
         handler.postDelayed({
            when (currentFragment) {
               is ProfileFragment -> // Only update if it's NOT HomeFragment
                  bottomNavigationView.selectedItemId = R.id.navigation_profile
            }
         }, 100)
      }
   }
   private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {supportFragmentManager.commit {
      replace(R.id.main_frame_container, fragment)
      if (addToBackStack) {
         addToBackStack(null)
         }
      }
   }

   override fun onFavouriteFragmentOpened() {
      bottomNavigationView.visibility = View.GONE
   }
}