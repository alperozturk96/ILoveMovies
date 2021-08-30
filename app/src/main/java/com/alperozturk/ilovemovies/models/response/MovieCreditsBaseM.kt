package com.alperozturk.ilovemovies.models.response

import com.fasterxml.jackson.annotation.JsonProperty

//All responses are generated for Jackson JSON Decoder which is fastest JSON Decoder. Moshi and Gson are other options.
data class MovieCreditsBaseM(

	@field:JsonProperty("cast")
	val cast: List<CastItem?>? = null,

	@field:JsonProperty("id")
	val id: Int? = null,

	@field:JsonProperty("crew")
	val crew: List<CrewItem?>? = null
)

data class CastItem(

	@field:JsonProperty("cast_id")
	val castId: Int? = null,

	@field:JsonProperty("character")
	val character: String? = null,

	@field:JsonProperty("gender")
	val gender: Int? = null,

	@field:JsonProperty("credit_id")
	val creditId: String? = null,

	@field:JsonProperty("known_for_department")
	val knownForDepartment: String? = null,

	@field:JsonProperty("original_name")
	val originalName: String? = null,

	@field:JsonProperty("popularity")
	val popularity: Double? = null,

	@field:JsonProperty("name")
	val name: String? = null,

	@field:JsonProperty("profile_path")
	val profilePath: String? = null,

	@field:JsonProperty("id")
	val id: Int? = null,

	@field:JsonProperty("adult")
	val adult: Boolean? = null,

	@field:JsonProperty("order")
	val order: Int? = null
)

data class CrewItem(

	@field:JsonProperty("gender")
	val gender: Int? = null,

	@field:JsonProperty("credit_id")
	val creditId: String? = null,

	@field:JsonProperty("known_for_department")
	val knownForDepartment: String? = null,

	@field:JsonProperty("original_name")
	val originalName: String? = null,

	@field:JsonProperty("popularity")
	val popularity: Double? = null,

	@field:JsonProperty("name")
	val name: String? = null,

	@field:JsonProperty("profile_path")
	val profilePath: String? = null,

	@field:JsonProperty("id")
	val id: Int? = null,

	@field:JsonProperty("adult")
	val adult: Boolean? = null,

	@field:JsonProperty("department")
	val department: String? = null,

	@field:JsonProperty("job")
	val job: String? = null
)
