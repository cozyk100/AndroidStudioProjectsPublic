package com.example.mockk

import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockkConstructor
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * コンストラクタモック(Constructor mocks)
 *
 * 場合によっては、特に所有していないコードでは、新しく作成したオブジェクトをモックする必要があります。
 * この目的のために、次の構成が提供されます。
 */
class ConstractorMockTest {
    class MockCls {
        fun add(a: Int, b: Int) = a + b
    }

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun constractorMock() {
        mockkConstructor(MockCls::class)
        every { anyConstructed<MockCls>().add(1, 2) } returns 4
        assertEquals(4, MockCls().add(1, 2)) // 新しいオブジェクトが作成されることに注意してください
        verify { anyConstructed<MockCls>().add(1, 2) }
    }
}