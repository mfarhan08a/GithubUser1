package com.mfarhan08a.githubuser.data.remote.retrofit

import com.mfarhan08a.githubuser.data.remote.response.DetailUserResponse
import com.mfarhan08a.githubuser.GithubResponse
import com.mfarhan08a.githubuser.ItemsItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun getUserListByUsername(
        @Query("q") username: String
    ): GithubResponse

    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") username: String
    ): DetailUserResponse

    @GET("users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") username: String
    ): List<ItemsItem>

    @GET("users/{username}/following")
    suspend fun getFollowing(
        @Path("username") username: String
    ): List<ItemsItem>

}