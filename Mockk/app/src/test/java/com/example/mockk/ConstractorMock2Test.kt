package com.example.mockk

import io.mockk.*
import io.mockk.junit4.MockKRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * コンストラクタモック(Constructor mocks)
 */
class ConstractorMock2Test {

    class MockCls(private val a: Int = 0) {
        constructor(x: String) : this(x.toInt())
        fun add(b: Int) = a + b
    }

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun constractorMock2() {
        mockkConstructor(MockCls::class)

        every { constructedWith<MockCls>().add(1) } returns 2
        every {
            constructedWith<MockCls>(OfTypeMatcher<String>(String::class)).add(2) // Mocks the constructor which takes a String
        } returns 3
        every {
            constructedWith<MockCls>(EqMatcher(4)).add(any()) // Mocks the constructor which takes an Int
        } returns 4

        assertEquals(2, MockCls().add(1)) // 何故1になる？
        assertEquals(3, MockCls("2").add(2))
        assertEquals(4, MockCls(4).add(7))

        verify {
            constructedWith<MockCls>().add(1)
            constructedWith<MockCls>(EqMatcher("2")).add(2)
            constructedWith<MockCls>(EqMatcher(4)).add(7)
        }
    }
}