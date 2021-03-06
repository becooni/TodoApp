package com.becooni.todoapp.repository

import com.becooni.todoapp.model.Todo
import io.reactivex.Completable
import io.reactivex.Flowable

interface Repository {

    fun getAll(): Flowable<List<Todo>>

    fun getActive(): Flowable<List<Todo>>

    fun getCompleted(): Flowable<List<Todo>>

    fun insert(vararg items: Todo): Completable

    fun delete(vararg items: Todo): Completable

    fun update(vararg items: Todo): Completable
}