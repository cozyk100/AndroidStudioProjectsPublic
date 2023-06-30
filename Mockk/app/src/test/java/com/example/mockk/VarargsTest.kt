package com.example.mockk

import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * 可変引数(Varargs)
 * バージョン1.9.1から、さらに拡張された可変引数処理が可能になった
 */
class VarargsTest {
    interface ClsWithManyMany{
        fun manyMany(vararg x: Any): Int
    }

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun varargs() {
        val obj = mockk<ClsWithManyMany>()

        // varargAll(matcher)	すべての要素がマッチャーに一致する場合に一致
        // 5, 6 に続く引数が7で連続していればマッチ
        every { obj.manyMany(5, 6, *varargAll { it == 7}) } returns 3

        assertEquals(3, (obj.manyMany(5, 6, 7)) )
        assertEquals(3, obj.manyMany(5, 6, 7, 7))
        assertEquals(3, obj.manyMany(5, 6, 7, 7, 7))

        // anyVararg()	可変引数の任意の要素に一致します
        // 5, 6 と 7の間の引数は何でもマッチ
        every { obj.manyMany(5, 6, *anyVararg(), 7 ) } returns 4

        assertEquals(4, obj.manyMany(5, 6, 1, 7))
        assertEquals(4, obj.manyMany(5, 6, 2, 3, 7))
        assertEquals(4, obj.manyMany(5, 6, 4, 5, 6,  7))

        // varargAny...(matcher)	いずれかの要素がマッチャーに一致する場合に一致します（プリミティブ型に固有）
        // nArgs 引数の数
        // 引数の数が5より多く、5, 6 ・・・ 7で終わればマッチ
        every { obj.manyMany(5, 6, *varargAny {nArgs > 5}, 7) } returns 5

        assertEquals(5, obj.manyMany(5, 6, 4, 5, 6, 7))
        assertEquals(5, obj.manyMany(5, 6, 4, 5, 6, 7, 7))

        // position 引数の位置(0始まり)
        // 5, 6 で始まり、posistion=3より前だったら3、それより後ろだったら4、終わりが7だったらマッチ
        every {
            obj.manyMany(5, 6, *varargAny {
                if (position < 3) it == 3 else it == 4
            }, 7)
        } returns 6

        assertEquals(6, obj.manyMany(5, 6, 3, 4, 7))
        assertEquals(6, obj.manyMany(5, 6, 3, 4, 4, 7))
    }
}