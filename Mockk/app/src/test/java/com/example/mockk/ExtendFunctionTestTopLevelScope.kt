package com.example.mockk

import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * 拡張関数(Extension functions)
 *
 * モジュール全体の拡張機能をモックするには、モジュールのクラス名を引数としてmockkStatic(...)をビルドする必要があります。
 */
class ExtendFunctionTestTopLevelScope {
    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun testMockKSample() {
        mockkStatic("com.example.mockk.ExtendFunctionKt")

        every {
            Obj2(5).extensionFunc()
        } returns 11

        assertEquals(11, Obj2(5).extensionFunc())

        verify {
            Obj2(5).extensionFunc()
        }
    }
}