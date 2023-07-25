package com.example.androidfundamental.Favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidfundamental.database.noteRepository

class favoriteViewModel(private val noteRepository: noteRepository) : ViewModel() {

    fun getUserFavorite() = noteRepository.getAllUser()

}