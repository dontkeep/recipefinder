package com.nicelydone.recipefinder.model.networks.Response

import com.google.gson.annotations.SerializedName

data class ResponseSimilar(

	@field:SerializedName("ResponseSimilar")
	val responseSimilar: List<ResponseSimilarItem?>? = null
)

data class ResponseSimilarItem(

	@field:SerializedName("readyInMinutes")
	val readyInMinutes: Int? = null,

	@field:SerializedName("sourceUrl")
	val sourceUrl: String? = null,

	@field:SerializedName("servings")
	val servings: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("imageType")
	val imageType: String? = null
)
