package com.example.mockk

import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockkObject
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * 列挙モック(Enumeration mocks)
 */
class EnumerationMockTest {
    enum class Enumeration(val goodInt: Int) {
        CONSTANT(35),
        OTHER_CONSTANT(45);
    }

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun enumerationMock() {
        mockkObject(Enumeration.CONSTANT)
        every { Enumeration.CONSTANT.goodInt } returns 42
        assertEquals(42, Enumeration.CONSTANT.goodInt)
    }
}