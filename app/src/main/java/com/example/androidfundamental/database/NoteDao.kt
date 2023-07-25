package com.example.androidfundamental.database

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: NoteEntity)
    @Update
    fun update(note: NoteEntity)
    @Delete
    fun delete(note: NoteEntity)

    @Query("SELECT * from NoteEntity ORDER BY username ASC")
    fun getAllUser(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity WHERE username = :username")
    fun getFavoriteUserByUsername(username: String): LiveData<NoteEntity>

}