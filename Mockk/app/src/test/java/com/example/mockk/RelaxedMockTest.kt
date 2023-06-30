package com.example.mockk

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Relaxed mock
 *
 * relaxed mockは、すべての関数に対して単純な値を返すモックです。
 * これにより、各ケースの動作の指定をスキップしながら、必要なものをスタブ化できます。
 * 参照型の場合、連鎖モックが返されます
 */
class RelaxedMockTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    // Annotationの場合
    @RelaxedMockK
    lateinit var car2: Car

    // Annotationの場合
    @MockK(relaxed = true)
    // @MockK(relaxUnitFun = true) // 戻り値がUnitのものだけRelaxed mockしてくれる
    lateinit var car3: Car

    // Annotationの初期化
    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun rleaxedMockTest() {
        val car = mockk<Car>(relaxed = true)
        // val car = mockk<Car>(relaxUnitFun = true) // 戻り値がUnitのものだけRelaxed mockしてくれる
        every { car.drive(Direction.NORTH) } returns Outcome.OK
        assertEquals(Outcome.OK, car.drive(Direction.NORTH))
        verify { car.drive(Direction.NORTH) }
        confirmVerified(car)
    }

    // ジェネリクスを使った場合はClassCastExceptionが発生する
    interface Factory {
        fun <T> create(): T
    }

//    @Test
//    fun rleaxedMockTest2() {
//        val factory = mockk<Factory>(relaxed = true)
//        val car = factory.create<Car>()  // ここで ClassCastException
//    }

    @Test
    fun rleaxedMockTest3() {
        val factory = mockk<Factory>(relaxed = true)
        every { factory.create<Car>() } returns Car() // これが必要
        val car = factory.create<Car>()
    }
}