package com.soda1127.unittestapplication

import com.soda1127.unittestapplication.testbase.JUnit5Test
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.assertj.core.api.Assertions.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest: JUnit5Test() {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `asdf`() = runTest {

    }

    companion object {

        fun runAndTest() {

        }

    }

}
