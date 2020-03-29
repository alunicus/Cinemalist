package com.github.alunicus.cinemalist.feature.search.data

import com.github.alunicus.cinemalist.feature.search.domain.model.dto.SearchResultsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/movie")
    suspend fun getSearchResult(@Query("query") query: String): SearchResultsDto
}