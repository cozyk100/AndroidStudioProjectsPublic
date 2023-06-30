package com.example.mockk

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

/**
 * 基本、DSLの通り
 */
class BasicDslTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun basicDsl() {
        // モックインスタンスを生成する
        val car = mockk<Car>()
        // モックインスタンスにパターンを設定
        // この場合は「carインスタンスのdriveメソッドが引数Direction.NORTHで呼ばれた場合はOutcome.OKを返却する」となる
        every { car.drive(Direction.NORTH) } returns Outcome.OK
        // 実際に呼び出す（Outcome.OKが返却される）
        car.drive(Direction.NORTH) // returns OK
        // メソッド呼び出しのチェック
        // この場合は「carインスタンスのdriveメソッドが引数Direction.NORTHで呼び出されている」という意味
        // verify(exactly = 1) { car.drive(Direction.NORTH) }というようにexactlyで呼び出されている回数を指定してチェックすることもできる
        verify { car.drive(Direction.NORTH) }
        // verifyで設定した全ての検証が完了していることをチェックする
        // 呼び出されていないものがあると例外をスローする
        confirmVerified(car)
    }
}