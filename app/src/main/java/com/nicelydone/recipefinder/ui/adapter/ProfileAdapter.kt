package com.nicelydone.recipefinder.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nicelydone.recipefinder.databinding.ProfileItemBinding
import com.nicelydone.recipefinder.model.Profile


class ProfileAdapter: ListAdapter<Profile, ProfileAdapter.ProfileViewHolder>(DIFF_CALLBACK) {
   class ProfileViewHolder(private val binding: ProfileItemBinding): RecyclerView.ViewHolder(binding.root) {
      fun bind(profile: Profile) {
         binding.profileName.text = profile.name
         binding.profileNim.text = profile.nim
         Glide.with(binding.profileImage.context).load(profile.image).into(binding.profileImage)

         binding.root.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.github.com/${profile.github}"))
            itemView.context.startActivity(browserIntent)
         }
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
      val binding = ProfileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return ProfileViewHolder(binding)
   }

   override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
      val profile = getItem(position)
      holder.bind(profile)
   }

   companion object {
      val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Profile>() {
         override fun areItemsTheSame(
            oldItem: Profile,
            newItem: Profile
         ): Boolean {
            return oldItem == newItem
         }

         override fun areContentsTheSame(
            oldItem: Profile,
            newItem: Profile
         ): Boolean {
            return oldItem == newItem
         }
      }
   }
}