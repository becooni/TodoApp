package com.becooni.todoapp.repository

import com.becooni.todoapp.model.Todo
import io.reactivex.Completable
import io.reactivex.Maybe

interface TodoRepository {

    fun getAll(): Maybe<List<Todo>>

    fun getActive(): Maybe<List<Todo>>

    fun getCompleted(): Maybe<List<Todo>>

    fun insert(vararg items: Todo): Completable

    fun delete(vararg items: Todo): Completable

    fun update(vararg items: Todo): Completable
}