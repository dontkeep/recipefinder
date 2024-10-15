package com.nicelydone.recipefinder.model.networks.Response

import com.google.gson.annotations.SerializedName

data class ResponseDetailRecipe(

	@field:SerializedName("instructions")
	val instructions: String? = null,

	@field:SerializedName("sustainable")
	val sustainable: Boolean? = null,

	@field:SerializedName("analyzedInstructions")
	val analyzedInstructions: List<Any?>? = null,

	@field:SerializedName("glutenFree")
	val glutenFree: Boolean? = null,

	@field:SerializedName("veryPopular")
	val veryPopular: Boolean? = null,

	@field:SerializedName("healthScore")
	val healthScore: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("diets")
	val diets: List<Any?>? = null,

	@field:SerializedName("aggregateLikes")
	val aggregateLikes: Int? = null,

	@field:SerializedName("creditsText")
	val creditsText: String? = null,

	@field:SerializedName("readyInMinutes")
	val readyInMinutes: Int? = null,

	@field:SerializedName("sourceUrl")
	val sourceUrl: String? = null,

	@field:SerializedName("dairyFree")
	val dairyFree: Boolean? = null,

	@field:SerializedName("servings")
	val servings: Int? = null,

	@field:SerializedName("vegetarian")
	val vegetarian: Boolean? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("preparationMinutes")
	val preparationMinutes: Any? = null,

	@field:SerializedName("imageType")
	val imageType: String? = null,

	@field:SerializedName("summary")
	val summary: String? = null,

	@field:SerializedName("cookingMinutes")
	val cookingMinutes: Any? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("veryHealthy")
	val veryHealthy: Boolean? = null,

	@field:SerializedName("vegan")
	val vegan: Boolean? = null,

	@field:SerializedName("cheap")
	val cheap: Boolean? = null,

	@field:SerializedName("extendedIngredients")
	val extendedIngredients: List<ExtendedIngredientsItem?>? = null,

	@field:SerializedName("dishTypes")
	val dishTypes: List<String?>? = null,

	@field:SerializedName("gaps")
	val gaps: String? = null,

	@field:SerializedName("cuisines")
	val cuisines: List<Any?>? = null,

	@field:SerializedName("lowFodmap")
	val lowFodmap: Boolean? = null,

	@field:SerializedName("license")
	val license: String? = null,

	@field:SerializedName("weightWatcherSmartPoints")
	val weightWatcherSmartPoints: Int? = null,

	@field:SerializedName("occasions")
	val occasions: List<Any?>? = null,

	@field:SerializedName("pricePerServing")
	val pricePerServing: Double? = null,

	@field:SerializedName("spoonacularScore")
	val spoonacularScore: Double? = null,

	@field:SerializedName("sourceName")
	val sourceName: String? = null,

	@field:SerializedName("originalId")
	val originalId: Any? = null,

	@field:SerializedName("spoonacularSourceUrl")
	val spoonacularSourceUrl: String? = null
)

data class Measures(

	@field:SerializedName("metric")
	val metric: Metric? = null,

	@field:SerializedName("us")
	val us: Us? = null
)

data class Metric(

	@field:SerializedName("amount")
	val amount: Float? = null,

	@field:SerializedName("unitShort")
	val unitShort: String? = null,

	@field:SerializedName("unitLong")
	val unitLong: String? = null
)

data class Us(

	@field:SerializedName("amount")
	val amount: Float? = null,

	@field:SerializedName("unitShort")
	val unitShort: String? = null,

	@field:SerializedName("unitLong")
	val unitLong: String? = null
)

data class ExtendedIngredientsItem(

	@field:SerializedName("originalName")
	val originalName: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("amount")
	val amount: Float? = null,

	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("measures")
	val measures: Measures? = null,

	@field:SerializedName("nameClean")
	val nameClean: String? = null,

	@field:SerializedName("original")
	val original: String? = null,

	@field:SerializedName("meta")
	val meta: List<Any?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("aisle")
	val aisle: String? = null,

	@field:SerializedName("consistency")
	val consistency: String? = null
)
