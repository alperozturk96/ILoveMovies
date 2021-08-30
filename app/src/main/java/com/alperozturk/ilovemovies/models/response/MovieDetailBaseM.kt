package com.alperozturk.ilovemovies.models.response

import com.fasterxml.jackson.annotation.JsonProperty

data class MovieDetailBaseM(

	@field:JsonProperty("original_language")
	val originalLanguage: String? = null,

	@field:JsonProperty("imdb_id")
	val imdbId: String? = null,

	@field:JsonProperty("video")
	val video: Boolean? = null,

	@field:JsonProperty("title")
	val title: String? = null,

	@field:JsonProperty("backdrop_path")
	val backdropPath: String? = null,

	@field:JsonProperty("revenue")
	val revenue: Int? = null,

	@field:JsonProperty("genres")
	val genres: List<GenresItem?>? = null,

	@field:JsonProperty("popularity")
	val popularity: Double? = null,

	@field:JsonProperty("production_countries")
	val productionCountries: List<ProductionCountriesItem?>? = null,

	@field:JsonProperty("id")
	val id: Int? = null,

	@field:JsonProperty("vote_count")
	val voteCount: Int? = null,

	@field:JsonProperty("budget")
	val budget: Int? = null,

	@field:JsonProperty("overview")
	val overview: String? = null,

	@field:JsonProperty("original_title")
	val originalTitle: String? = null,

	@field:JsonProperty("runtime")
	val runtime: Int? = null,

	@field:JsonProperty("poster_path")
	val posterPath: String? = null,

	@field:JsonProperty("spoken_languages")
	val spokenLanguages: List<SpokenLanguagesItem?>? = null,

	@field:JsonProperty("production_companies")
	val productionCompanies: List<ProductionCompaniesItem?>? = null,

	@field:JsonProperty("release_date")
	val releaseDate: String? = null,

	@field:JsonProperty("vote_average")
	val voteAverage: Double? = null,

	@field:JsonProperty("belongs_to_collection")
	val belongsToCollection: BelongsToCollection? = null,

	@field:JsonProperty("tagline")
	val tagline: String? = null,

	@field:JsonProperty("adult")
	val adult: Boolean? = null,

	@field:JsonProperty("homepage")
	val homepage: String? = null,

	@field:JsonProperty("status")
	val status: String? = null
)

data class BelongsToCollection(

	@field:JsonProperty("backdrop_path")
	val backdropPath: String? = null,

	@field:JsonProperty("name")
	val name: String? = null,

	@field:JsonProperty("id")
	val id: Int? = null,

	@field:JsonProperty("poster_path")
	val posterPath: String? = null
)

data class ProductionCompaniesItem(

	@field:JsonProperty("logo_path")
	val logoPath: String? = null,

	@field:JsonProperty("name")
	val name: String? = null,

	@field:JsonProperty("id")
	val id: Int? = null,

	@field:JsonProperty("origin_country")
	val originCountry: String? = null
)

data class GenresItem(

	@field:JsonProperty("name")
	val name: String? = null,

	@field:JsonProperty("id")
	val id: Int? = null
)

data class SpokenLanguagesItem(

	@field:JsonProperty("name")
	val name: String? = null,

	@field:JsonProperty("iso_639_1")
	val iso6391: String? = null,

	@field:JsonProperty("english_name")
	val englishName: String? = null
)

data class ProductionCountriesItem(

	@field:JsonProperty("iso_3166_1")
	val iso31661: String? = null,

	@field:JsonProperty("name")
	val name: String? = null
)
