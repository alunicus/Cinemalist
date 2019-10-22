package com.github.alunicus.cinemalist

import com.github.alunicus.cinemalist.data.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("3/search/movie")
    suspend fun getSearchResult(@Query("query") query: String, @Query("api_key") apiKey: String): SearchResult
}