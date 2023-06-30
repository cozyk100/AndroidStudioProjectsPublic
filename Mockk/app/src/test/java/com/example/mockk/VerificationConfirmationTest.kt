package com.example.mockk

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

/**
 * 検証確認(Verification confirmation)
 *
 * これらの検証メソッドは、検証付きのすべての呼び出しを網羅しているため、verifySequenceおよびverifyAllに使用することはあまり意味がない
 * いくつかの呼び出しが検証なしで残っている場合、例外をスローします
 */
class VerificationConfirmationTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun verificationConfirmation() {
        val car = mockk<Car>()

        every { car.drive(Direction.NORTH) } returns Outcome.OK
        every { car.drive(Direction.SOUTH) } returns Outcome.OK

        car.drive(Direction.NORTH)
        car.drive(Direction.SOUTH)

        verify {
            car.drive(Direction.SOUTH) // どっちかがverifyしていないと、confirmVerifiedで例外がthrowされる
            car.drive(Direction.NORTH) // どっちかがverifyしていないと、confirmVerifiedで例外がthrowされる
        }

        confirmVerified(car)
    }
}