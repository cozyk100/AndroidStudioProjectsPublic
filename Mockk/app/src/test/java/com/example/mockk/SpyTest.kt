package com.example.mockk

import io.mockk.*
import io.mockk.impl.annotations.SpyK
import io.mockk.junit4.MockKRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Spy
 * スパイはモックと実際のオブジェクトを混ぜることができます。
 */
class SpyTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    // アノテーションでspyする場合。この場合コンストラクタを呼んでいるのでinterfaceはダメ。classじゃないと。varである必要がある。
    @SpyK
    var spy1 = Car()

    // アノテーションを使わない場合。この場合はinterfaceでもイケる。
    val spy2 = spyk<Car>()

    // これは初期化時に例外がthrowされる
//    @SpyK
//    lateinit var spy3: Car

    // Annotationの初期化
    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun spytest() {
        every { spy1.drive(Direction.NORTH) } returns Outcome.OK
        every { spy2.drive(Direction.NORTH) } returns Outcome.OK
        Assert.assertEquals(Outcome.OK, spy1.drive(Direction.NORTH))
        Assert.assertEquals(Outcome.OK, spy2.drive(Direction.NORTH))
        verify(exactly = 1) { spy1.drive(Direction.NORTH) }
        verify(exactly = 1) { spy2.drive(Direction.NORTH) }
        confirmVerified(spy1)
        confirmVerified(spy2)
    }
}