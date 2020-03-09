package com.github.alunicus.cinemalist.feature.search

import com.github.alunicus.cinemalist.data.dto.SearchResultsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/movie")
    suspend fun getSearchResult(@Query("query") query: String, @Query("api_key") apiKey: String): SearchResultsDto
}