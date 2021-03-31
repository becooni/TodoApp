package com.becooni.todoapp.util

import com.becooni.todoapp.model.Todo

object MockUtil {

    fun mockTodo() = Todo(text = "TODO test")

    fun mockTodoList() = listOf(mockTodo())
}