package com.becooni.todoapp.data

import com.becooni.todoapp.data.source.local.TodoLocalDataSource

class TodoRepositoryImpl(
    private val todoLocalDataSource: TodoLocalDataSource
) : TodoRepository {

    override fun getAll() = todoLocalDataSource.getAll()

    override fun getActive() = todoLocalDataSource.getActive()

    override fun getCompleted() = todoLocalDataSource.getCompleted()

    override fun insert(vararg items: Todo) = todoLocalDataSource.insert(*items)

    override fun delete(vararg items: Todo) = todoLocalDataSource.delete(*items)

    override fun update(vararg items: Todo) = todoLocalDataSource.update(*items)
}