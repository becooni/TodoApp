package com.becooni.todoapp.data

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAll(): Flowable<List<Todo>>

    @Query("SELECT * FROM todo WHERE completed = 0")
    fun getActive(): Flowable<List<Todo>>

    @Query("SELECT * FROM todo WHERE completed = 1")
    fun getCompleted(): Flowable<List<Todo>>

    @Insert
    fun insert(vararg items: Todo): Completable

    @Delete
    fun delete(vararg items: Todo): Completable

    @Update
    fun update(vararg items: Todo): Completable
}