package com.example.mockk

import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * 拡張関数(Extension functions)
 *
 * 拡張関数には3つのケースがあります。
 * ・クラス全体(class wide)
 * ・オブジェクト全体(object wide)
 * ・モジュール全体(module wide)
 * オブジェクトとクラスの場合、通常のモックを作成するだけで拡張関数をモックできます。
 */
class ExtendFunctionTest {
    data class Obj(val value: Int)

    class Ext {
        fun Obj.extensionFunc() = value + 5
    }

    // とりあえず、無くても動くが、必須なのか？
    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun testMockKSample() {
        with(mockk<Ext>()) {
            every { Obj(5).extensionFunc() } returns 11
            assertEquals(11, Obj(5).extensionFunc())
            verify {
                Obj(5).extensionFunc()
            }
        }
    }
}