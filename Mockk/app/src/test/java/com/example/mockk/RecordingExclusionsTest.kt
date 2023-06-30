package com.example.mockk

import io.mockk.*
import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.Test

/**
 * 記録の除外(Recording exclusions)
 *
 * それほど重要ではない呼び出しを記録から除外するには、excludeRecordsを使用できる
 */
class RecordingExclusionsTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun recordingExclusions() {
        val car = mockk<Car>()

        every { car.drive(Direction.NORTH) } returns Outcome.OK
        every { car.drive(Direction.SOUTH) } returns Outcome.OK

        excludeRecords { car.drive(Direction.SOUTH) } // 除外の指定

        car.drive(Direction.NORTH)
        car.drive(Direction.SOUTH)

        verify {
            car.drive(Direction.NORTH) // car.drive(Direction.SOUTH)が除外されている
        }

        confirmVerified(car)
    }
}