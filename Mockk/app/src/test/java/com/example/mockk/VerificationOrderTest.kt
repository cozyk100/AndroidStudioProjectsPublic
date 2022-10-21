package com.example.mockk

import io.mockk.*
import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.Test

/**
 * 検証順序(Verification order)
 */
class VerificationOrderTest {
    class MockedClass {
        fun sum(a: Int, b: Int) = a + b
    }

    // とりあえず、無くても動くが、必須なのか？
    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun verificationOrder() {
        val obj = mockk<MockedClass>()
        val slot = slot<Int>()

        every {
            obj.sum(any(), capture(slot))
        } answers {
            // arg(n)	n番目の引数
            // firstArg()	第一引数
            // secondArg()	第二引数
            // thirdArg()	第三引数
            // lastArg()	最後の引数
            1 + firstArg<Int>() + slot.captured
        }

        obj.sum(1, 2)
        obj.sum(1, 3)
        obj.sum(2, 2)

        // verifyAllは、順序を確認せずにすべての呼び出しが行われたことを検証
        verifyAll {
            obj.sum(1,3)
            obj.sum(1,2)
            obj.sum(2,2)
        }

        // verifySequenceは、指定された順序で呼び出しが行われたことを検証(中抜けを許さない)
        verifySequence {
            obj.sum(1, 2)
            obj.sum(1, 3)
            obj.sum(2, 2)
        }

        // verifyOrderは、呼び出しが特定の順序で発生したことを検証(中抜けを許す)
        verifyOrder {
            obj.sum(1, 2)
            obj.sum(2, 2)
        }
        confirmVerified(obj)

        val obj2 = mockk<MockedClass>()
        val obj3 = mockk<MockedClass>()

        // wasNot Calledは、モックまたはモックのリストがまったく呼び出されなかったことを検証
        verify {
            listOf(obj2, obj3) wasNot Called
        }
    }
}