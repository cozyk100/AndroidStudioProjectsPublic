package com.example.mockk

import io.mockk.*
import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.Test

/**
 * キャプチャ(Capturing)
 *
 * CapturingSlotまたはMutableListへの引数をキャプチャできる(mockitoのcaptureと同じようなもんか？)
 */
class CaptureMockTest {
    // とりあえず、無くても動くが、必須なのか？
    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun captureMock() {
        val car = mockk<Car>()
        val slot = slot<Double>() // sppedをslotにcaptureする
        val list = mutableListOf<Double>() // sppedをlistにcaptureする

        every {
            car.recordTelemetry(
                speed = capture(slot), // sppedをslotにcaptureする
                direction = Direction.NORTH
            )
        } answers {
            println(slot.captured) // captureしたものを表示
            Outcome.RECORDED
        }

        every {
            car.recordTelemetry(
                speed = capture(list), // sppedをlistにcaptureする
                direction = Direction.SOUTH
            )
        } answers {
            println(list) // captureしたものを表示
            Outcome.RECORDED
        }

        car.recordTelemetry(speed = 15.0, direction = Direction.NORTH) // 15.0
        car.recordTelemetry(speed = 16.0, direction = Direction.SOUTH) // 16.0

        verify(exactly = 2) { car.recordTelemetry(speed = or(15.0, 16.0), direction = any()) }

        confirmVerified()
    }
}