package com.github.alunicus.cinemalist

import com.github.alunicus.cinemalist.data.SearchResultResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchApi {
    @GET("3/search/movie")
    suspend fun listRepos(@Path("query") query: String, @Path("api_key") apiKey: String): List<SearchResultResponse>
}