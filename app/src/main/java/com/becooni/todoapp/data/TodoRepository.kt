package com.becooni.todoapp.data

import io.reactivex.Completable
import io.reactivex.Flowable

interface TodoRepository {

    fun getAll(): Flowable<List<Todo>>

    fun getActive(): Flowable<List<Todo>>

    fun getCompleted(): Flowable<List<Todo>>

    fun insert(vararg items: Todo): Completable

    fun delete(vararg items: Todo): Completable

    fun update(vararg items: Todo): Completable
}