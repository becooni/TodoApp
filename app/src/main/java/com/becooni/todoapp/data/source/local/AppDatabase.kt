package com.becooni.todoapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.becooni.todoapp.data.Todo
import com.becooni.todoapp.data.TodoDao

@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}