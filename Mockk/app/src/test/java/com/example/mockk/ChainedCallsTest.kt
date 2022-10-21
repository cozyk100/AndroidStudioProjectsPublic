package com.example.mockk

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

/**
 * 連鎖呼び出し(Chained calls)
 */
class ChainedCallsTest {
    // とりあえず、無くても動くが、必須なのか？
    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun chainedCalls() {
        val car = mockk<Car>()

        every { car.door(DoorType.FRONT_LEFT).windowState() } returns WindowState.UP

        car.door(DoorType.FRONT_LEFT) // Doorの連鎖モックを返します ⇛ ちょっと意味不明
        car.door(DoorType.FRONT_LEFT).windowState() // returns WindowState.UP

        verify { car.door(DoorType.FRONT_LEFT).windowState() }

        confirmVerified(car)
    }
}