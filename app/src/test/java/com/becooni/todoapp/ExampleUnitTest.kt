package com.becooni.todoapp

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun stringEquals() {
        val literal = "string"
        val instance = String(StringBuilder("string"))

        val result = literal == instance

        assertThat(result, `is`(true))
    }
    @Test
    fun stringIndexOf() {
        val literal = "string"
        literal.replace(Regex(""), "")

    }
}