package com.example.mockk

import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

/**
 * 最小、最大、または正確な回数の検証(Verification atLeast, atMost or exactly times)
 * 呼び出しカウントは、atLeast、atMost、またはexactlyパラメーターで確認できる
 */
class VerificationTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun verification() {
        val car = mockk<Car>(relaxed = true)

        car.accelerate(fromSpeed = 10.0, toSpeed = 20.0)
        car.accelerate(fromSpeed = 10.0, toSpeed = 30.0)
        car.accelerate(fromSpeed = 20.0, toSpeed = 30.0)

        // any() 任意の引数に一致します
        // allAny() 単純な引数として提供されるマッチャーにeq()の代わりにany()を使用する特別なマッチャー
        // or 論理和で2つのマッチャーを結合
        //
        // exactly 呼び出しが正確にn回実行されたことを順不同で検証
        // atMost 呼び出しが最大n回実行されたことを順不同で検証
        // atLeast 呼び出しが少なくともn回実行されたことを順不同で検証
        verify(atLeast = 3) {car.accelerate(allAny(), allAny())}
        verify(atMost = 2)  {car.accelerate(fromSpeed = 10.0, toSpeed = or(20.0, 30.0)) }
        verify(exactly = 1) {car.accelerate(fromSpeed = 10.0, toSpeed = 20.0) }
        verify(exactly = 0) {car.accelerate(fromSpeed = 30.0, toSpeed = 10.0) }
    }
}