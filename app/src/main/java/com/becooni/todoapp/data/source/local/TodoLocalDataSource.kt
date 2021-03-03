package com.becooni.todoapp.data.source.local

import com.becooni.todoapp.data.Todo
import com.becooni.todoapp.data.TodoDao
import com.becooni.todoapp.data.source.TodoDataSource

class TodoLocalDataSource(
    private val todoDao: TodoDao
) : TodoDataSource {

    override fun getAll() = todoDao.getAll()

    override fun getActive() = todoDao.getActive()

    override fun getCompleted() = todoDao.getCompleted()

    override fun insert(vararg items: Todo) = todoDao.insert(*items)

    override fun delete(vararg items: Todo) = todoDao.delete(*items)

    override fun update(vararg items: Todo) = todoDao.update(*items)
}