package com.example.androidfundamental.Main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.androidfundamental.DarkMode.SettingPreferences
import com.example.androidfundamental.ItemsItem
import com.example.androidfundamental.ResponseUser
import com.example.androidfundamental.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val pref: SettingPreferences) : ViewModel() {

    private val _listUser = MutableLiveData<List<ItemsItem>>()
    val listUser: LiveData<List<ItemsItem>> = _listUser


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getTheme() = pref.getThemeSetting().asLiveData()

    val searchQuery = MutableLiveData<String>()

    companion object {
        const val TAG = "MainActivity"
        private const val QUERY = "a"
    }

    init {
        findUser(QUERY)
    }

    fun findUser(input: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getAccount(input)
        client.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _listUser.value = (responseBody.items)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

}