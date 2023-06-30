package com.example.mockk

import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * オブジェクトモック(Object mocks)
 */
class ObjectMockTest {
    object MockObj {
        fun add(a: Int, b: Int) = a + b
    }

    @get:Rule
    val mockkRule = MockKRule(this)

    @Before
    fun setUp() {
        mockkObject(MockObj)  // これでモックになる
    }

    @Test
    fun testMockKSample() {
        assertEquals(3, MockObj.add(1, 2))
        every { MockObj.add(1, 2) } returns 55
        assertEquals(55, MockObj.add(1, 2))
    }

    @After
    fun tearDown() {
        unmockkAll()  // or unmockkObject(MockObj)
    }
}