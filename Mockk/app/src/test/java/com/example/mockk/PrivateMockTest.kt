package com.example.mockk

import io.mockk.*
import io.mockk.junit4.MockKRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * プライベート関数のモック / 動的呼び出し(Private functions mocking / dynamic calls)
 */
class PrivateMockTest {

    class DriveCar {
        fun drive() = accelerate()
        private fun accelerate() = "going faster"

        private fun openDoor(doorLR: String, doorFR: String): String {
            return "NG"
        }
        fun doorStatus(doorLR: String, doorFR: String): String {
            return openDoor(doorLR, doorFR)
        }
    }

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun testMockKSample() {
        // プライベート呼び出しを検証する場合は、recordPrivateCalls = trueでspykを作成する必要がある
        val mock = spyk<DriveCar>(recordPrivateCalls = true)
        every { mock["accelerate"]() } returns "going not so fast"
        assertEquals("going not so fast", mock.drive())
        verifySequence {
            mock.drive()
            mock["accelerate"]()
        }

        // private関数が引数付きの場合、値でMockする
        every { mock invoke "openDoor" withArguments listOf("left", "rear") } returns "OK"
        assertEquals("OK", mock.doorStatus("left", "rear"))
        assertEquals("NG", mock.doorStatus("right", "rear"))

        // private関数が引数付きの場合、型でMockする、他にもMatcherで使えるものがある
        every { mock invoke "openDoor" withArguments listOf(ofType<String>(), ofType<String>()) } returns "OOPS!"
        assertEquals("OOPS!", mock.doorStatus("left", "rear"))
    }
}