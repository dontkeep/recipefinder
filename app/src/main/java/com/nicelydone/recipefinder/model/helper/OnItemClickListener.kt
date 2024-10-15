package com.nicelydone.recipefinder.model.helper

import com.nicelydone.recipefinder.model.networks.Local.Entity.FavouriteRecipeEntity

interface OnItemClickListener {
   fun onItemClick(item: FavouriteRecipeEntity)
}