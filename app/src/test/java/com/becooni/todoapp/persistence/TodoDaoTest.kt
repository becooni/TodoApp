package com.becooni.todoapp.persistence

import com.becooni.todoapp.model.Todo
import com.becooni.todoapp.util.MockUtil
import io.reactivex.rxkotlin.subscribeBy
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class TodoDaoTest : AppDatabaseTest() {

    private lateinit var todoDao: TodoDao

    @Before
    fun init() {
        todoDao = db.todoDao()
    }

    @Test
    fun insertAndSelectTodoList() {
        val mockDataList = MockUtil.mockTodoList()
        todoDao.insert(mockDataList.first()).subscribe()

        val result = mutableListOf<Todo>()
        todoDao.getAll()
            .doOnSuccess { println("$it") }
            .subscribeBy(
                onSuccess = { result += it },
                onError = Throwable::printStackTrace,
                onComplete = {}
            )

        assertThat(result.toString(), `is`(mockDataList.toString()))

        val mockData = MockUtil.mockTodo()
        assertThat(result.first(), `is`(mockData))
    }
}