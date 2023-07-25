package com.example.androidfundamental.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
class NoteEntity(
    @PrimaryKey(autoGenerate = false)
    var username: String = "",
    var avatarUrl: String ,
)
