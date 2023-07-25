package com.example.androidfundamental.database

import androidx.lifecycle.LiveData
import com.example.androidfundamental.api.ApiService
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class noteRepository (apiService: ApiService,dao : NoteDao) {
    private val mNotesDao: NoteDao = dao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun insert(note: NoteEntity) {
        executorService.execute { mNotesDao.insert(note) }
    }
    fun delete(note: NoteEntity) {
        executorService.execute { mNotesDao.delete(note) }
    }
    fun getAllUser() : LiveData<List<NoteEntity>>{
       return mNotesDao.getAllUser()
   }
    fun getFavoriteUserByUsername(username : String): LiveData<NoteEntity>{
        return mNotesDao.getFavoriteUserByUsername(username)
    }




}