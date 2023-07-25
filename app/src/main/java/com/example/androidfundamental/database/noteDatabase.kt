package com.example.androidfundamental.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class noteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    companion object {
        @Volatile
        private var INSTANCE: noteDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): noteDatabase {
            if (INSTANCE == null) {
                synchronized(noteDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        noteDatabase::class.java, "note_database")
                        .build()
                }
            }
            return INSTANCE as noteDatabase
        }
    }
}
