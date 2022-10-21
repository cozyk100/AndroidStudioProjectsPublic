package com.example.mockk

import io.mockk.*
import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.Test

/**
 * Unitを返却(Returning Unit)
 */
class ReturningUnitTest {

    class MockedClass {
        fun sum(a: Int, b: Int): Unit {
            println(a + b)
        }
    }

    // とりあえず、無くても動くが、必須なのか？
    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun returningUnit() {
        val obj = mockk<MockedClass>()

        every { obj.sum(1, 1) } returns Unit
        every { obj.sum(1, 2) } returns Unit
        // 関数がUnitを返す場合、just Runsを使用できる
        // justRun { obj.sum(any(), 3) }
        // every { obj.sum(any(), 3) } returns Unit
        // every { obj.sum(any(), 3) } answers { Unit }
        // でもイケる
        every { obj.sum(any(), 3) } just Runs

        obj.sum(1, 1)
        obj.sum(1, 2)
        obj.sum(1, 3)

        verify {
            obj.sum(1, 1)
            obj.sum(1, 2)
            obj.sum(1, 3)
        }
    }
}