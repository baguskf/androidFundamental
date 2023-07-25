package com.example.androidfundamental

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.androidfundamental.DarkMode.SettingPreferences
import com.example.androidfundamental.api.ApiConfig
import com.example.androidfundamental.database.noteDatabase
import com.example.androidfundamental.database.noteRepository


private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "setting")
object Injection {
    fun provideRepository(context: Context): noteRepository {
        val apiService = ApiConfig.getApiService()
        val database = noteDatabase.getDatabase(context)
        val dao = database.noteDao()
        return noteRepository(apiService,dao)
    }
    fun preference(context: Context) : SettingPreferences{
        return SettingPreferences.getInstance(context.dataStore)
    }

}