package com.becooni.todoapp.repository

import com.becooni.todoapp.model.Todo
import com.becooni.todoapp.persistence.TodoDao
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
) : Repository {

    override fun getAll() = todoDao.getAll()

    override fun getActive() = todoDao.getActive()

    override fun getCompleted() = todoDao.getCompleted()

    override fun insert(vararg items: Todo) = todoDao.insert(*items)

    override fun delete(vararg items: Todo) = todoDao.delete(*items)

    override fun update(vararg items: Todo) = todoDao.update(*items)
}