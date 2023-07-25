package com.example.androidfundamental.Detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidfundamental.api.ApiConfig
import com.example.androidfundamental.database.NoteEntity
import com.example.androidfundamental.database.noteRepository
import com.example.androidfundamental.response.ResponseDetailUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val noteRepository: noteRepository) : ViewModel() {


    private val _detailUser = MutableLiveData<ResponseDetailUser>()
    val detailUser : LiveData<ResponseDetailUser> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun Insert(user: NoteEntity){
        noteRepository.insert(user)
    }

    fun delete(user: NoteEntity){
        noteRepository.delete(user)
    }

    fun FindUserById(usrname : String) = noteRepository.getFavoriteUserByUsername(usrname)

    fun findData(username : String){
        _isLoading.value=true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<ResponseDetailUser>{
            override fun onResponse(
                call: Call<ResponseDetailUser>,
                response: Response<ResponseDetailUser>
            ) {
                _isLoading.value=false
                if (response.isSuccessful){
                    val responseBody=response.body()
                    if (responseBody !=null ){
                        _detailUser.value = (responseBody)
                    }
                }
            }
            override fun onFailure(call: Call<ResponseDetailUser>, t: Throwable) {
                _isLoading.value=false
            }
        })
    }



}