package com.becooni.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    @ColumnInfo(name = "text", typeAffinity = ColumnInfo.TEXT) val text: String,
    @ColumnInfo(name = "completed", typeAffinity = ColumnInfo.INTEGER, defaultValue = "0")
    val completed: Boolean = false,
)
