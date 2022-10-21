package com.example.mockk

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockkClass
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * クラスモック(Class mock)
 *
 * 時には、任意のクラスのモックが必要です。そのような場合にはmockkClassを使用してください。
 */
class ClassMockTest {
    // とりあえず、無くても動くが、必須なのか？
    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun classMock() {
        val car = mockkClass(Car::class)
        every { car.drive(Direction.NORTH) } returns Outcome.OK
        assertEquals(Outcome.OK, car.drive(Direction.NORTH)) // returns OK
        verify { car.drive(Direction.NORTH) }
        confirmVerified(car)
    }
}