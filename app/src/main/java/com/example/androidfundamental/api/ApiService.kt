package com.example.androidfundamental.api
import com.example.androidfundamental.ResponseUser
import com.example.androidfundamental.ItemsItem
import com.example.androidfundamental.response.ResponseDetailUser
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getAccount(
        @Query("q") query: String
    ): Call<ResponseUser>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<ResponseDetailUser>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<ItemsItem>>
    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>

}