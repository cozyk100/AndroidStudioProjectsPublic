package com.example.mockk

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

/**
 * 部分引数マッチング(Partial argument matching)
 */
class PartialArgumentMatchingTest {
    // とりあえず、無くても動くが、必須なのか？
    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun partialArgumentMatching() {
        val car = mockk<Car>()
        every {
            car.recordTelemetry(
                speed = more(50.0), // 50より大きい
                direction = Direction.NORTH,
                lat = any(), // 何でも
                long = any() // 何でも
            )
        } returns Outcome.RECORDED
        car.recordTelemetry(60.0, Direction.NORTH, 51.1377382, 17.0257142)
        verify {car.recordTelemetry(60.0, Direction.NORTH, 51.1377382, 17.0257142)}
        confirmVerified(car)
    }
}