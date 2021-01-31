package com.ocul.githubrepolisting.model.api

import com.ocul.githubrepolisting.model.RepoItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    //https://api.github.com/search/repositories?q=trending&sort=stars
    //https://api.github.com/users/oacikel/repos

    @GET("users/{username}/repos")
    fun getRepos(
        @Path ("username") username:String): Call<List<RepoItem>>



    @GET("search/repositories")
    fun getRepo(@Query("q") search: String = "trending", @Query("sort") sort: String = "stars"): Call<RepoItem>

}