package com.example.mockk

import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit4.MockKRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * アノテーション(Annotations)
 *
 *
 * インジェクションは、最初にプロパティを名前で照合し、次にクラスまたはスーパークラスで照合します。
 * カスタマイズのためにlookupTypeパラメータを確認してください。
 * privateが適用されている場合でも、プロパティが注入されます。
 * インジェクションのコンストラクターは、引数の最大数から最小数まで選択されます。
 * デフォルトでは、@InjectMockKsは、lateinit varまたは割り当てられていないvarのみを挿入します。
 * これを変更するには、overrideValues = trueを使用します。これは、すでに何らかの方法で初期化されている場合でも値を割り当てます。
 * valを注入するには、injectImmutable = trueを使用します。短い表記の場合、デフォルトで@InjectMockKsと同じことを行う@OverrideMockKsを使用しますが、
 * この2つのフラグをオンにします。
 */
class AnnotationsTest {
    // @BeforeでMockKAnnotations.init(this)か、どちらかが必要
    @get:Rule
    val mockkRule = MockKRule(this)

    // モックインスタンスとしてインジェクションしたい場合に使用します
    @MockK
    lateinit var car1: Car

    // Relaxedモックインスタンスとしてインジェクションしたい場合に使用します
    @RelaxedMockK
    lateinit var car2: Car

    // 戻り値がUnitのものだけRelaxed mockしてくれる
    // または、mockk<Car>(relaxUnitFun = true)
    @MockK(relaxUnitFun = true)
    lateinit var car3: Car

    // Spy用のインスタンスとしてインジェクションしたい場合に使用します
    @SpyK
    var car4 = Car()

    // 該当オブジェクトのもつ属性に対してインジェクトしたい場合に使用します
    @InjectMockKs
    var trafficSystem = TrafficSystem()

    // Annotationの初期化にはこれが必要
    // @get:Ruleを使っている場合は不要
//    @Before
//    fun setUp() = MockKAnnotations.init(this) // 通常
//    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true) // relaxUnitFun = true 戻り値がUnitのものだけRelaxed mockしてくれる

    @Test
    fun calculateAddsValues1() {
        every { car1.drive(Direction.NORTH) } returns Outcome.OK
        every { car2.drive(Direction.NORTH) } returns Outcome.OK
        every { car3.drive(Direction.NORTH) } returns Outcome.OK
        every { car4.drive(Direction.NORTH) } returns Outcome.OK
        assertEquals(Outcome.OK, car1.drive(Direction.NORTH))
        assertEquals(Outcome.OK, car2.drive(Direction.NORTH))
        assertEquals(Outcome.OK, car3.drive(Direction.NORTH))
        assertEquals(Outcome.OK, car4.drive(Direction.NORTH))
        verify(exactly = 1) { car1.drive(Direction.NORTH) }
        verify(exactly = 1) { car2.drive(Direction.NORTH) }
        verify(exactly = 1) { car3.drive(Direction.NORTH) }
        verify(exactly = 1) { car4.drive(Direction.NORTH) }
        confirmVerified(car1)
        confirmVerified(car2)
        confirmVerified(car3)
        confirmVerified(car4)
    }
}