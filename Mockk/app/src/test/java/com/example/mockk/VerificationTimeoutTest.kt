package com.example.mockk

import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

/**
 * 検証タイムアウト(Verification timeout)
 */
class VerificationTimeoutTest {

    class Mockcls {
        fun sum(arg1: Int, arg2: Int): Int {
            return arg1 + arg2
        }
    }

    // とりあえず、無くても動くが、必須なのか？
    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun verificationTimeout() {
        mockk<Mockcls> {
            every { sum(1,2) } returns 4

            Thread {
                sum(1, 2)       // 逆にするとverifyは通らない
                Thread.sleep(4000)  // 逆にするとverifyは通らない
            }.start()
            // 検証をパスするか、タイムアウトになるまで待機する
            verify(timeout = 3000) { sum(1,2) }
        }
    }
}