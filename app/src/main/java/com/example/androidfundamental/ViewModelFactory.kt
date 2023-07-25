package com.example.androidfundamental

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidfundamental.DarkMode.SettingPreferences
import com.example.androidfundamental.DarkMode.SettingViewModel
import com.example.androidfundamental.DarkMode.setting
import com.example.androidfundamental.Detail.DetailViewModel
import com.example.androidfundamental.Favorite.favoriteViewModel
import com.example.androidfundamental.Main.MainViewModel
import com.example.androidfundamental.database.noteRepository

class ViewModelFactory private constructor(private val noteRepository: noteRepository,private val pref: SettingPreferences) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(noteRepository) as T
        }
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(pref) as T
        }
        if (modelClass.isAssignableFrom(favoriteViewModel::class.java)) {
            return favoriteViewModel(noteRepository) as T
        }
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }


    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context),Injection.preference(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}