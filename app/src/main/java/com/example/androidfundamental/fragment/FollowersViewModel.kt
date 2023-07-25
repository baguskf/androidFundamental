package com.example.androidfundamental.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidfundamental.ItemsItem
import com.example.androidfundamental.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {

    private val _followUser = MutableLiveData<List<ItemsItem>>()
    val followUser : LiveData<List<ItemsItem>> = _followUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun findFollowing(username : String){
        _isLoading.value=true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading.value=false
                if (response.isSuccessful){
                    val responseBody=response.body()
                    if (responseBody !=null ){
                        _followUser.value = (responseBody)
                    }
                }
            }
            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value=false
            }
        })
    }

    fun findFollower(username : String){
        _isLoading.value=true
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading.value=false
                if (response.isSuccessful){
                    val responseBody=response.body()
                    if (responseBody !=null ){
                        _followUser.value = (responseBody)
                    }
                }
            }
            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value=false
            }
        })
    }
}